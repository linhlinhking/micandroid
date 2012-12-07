package com.xero.admin.controller;

import static org.springframework.web.context.request.RequestAttributes.SCOPE_SESSION;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.xero.admin.bean.SystemUser;
import com.xero.admin.service.SystemUserService;
import com.xero.core.api.SessionAttributes;
import com.xero.core.api.server.OAuthServiceProvider;
import com.xero.core.controller.BaseController;
import com.xero.core.web.WebConstants;

@Controller
public class XeroController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("xeroServiceProvider")
	private OAuthServiceProvider xeroServiceProvider;

	@Resource
	private SystemUserService systemUserService;

	@RequestMapping(value = { "/test" }, method = RequestMethod.GET)
	public ModelAndView indexLogin() {
		ModelAndView model = new ModelAndView("login");
		return model;
	}

	@RequestMapping(value = { "/oauth/xero" }, method = RequestMethod.GET)
	public String login(WebRequest request) {
		// getting request and access token from session
		Token requestToken = (Token) request.getAttribute(
				SessionAttributes.ATTR_OAUTH_REQUEST_TOKEN, SCOPE_SESSION);
		Token accessToken = (Token) request.getAttribute(
				SessionAttributes.ATTR_OAUTH_ACCESS_TOKEN, SCOPE_SESSION);
		if (requestToken == null || accessToken == null) {
			// generate new request token
			OAuthService service = xeroServiceProvider.getService();
			requestToken = service.getRequestToken();
			request.setAttribute(SessionAttributes.ATTR_OAUTH_REQUEST_TOKEN,
					requestToken, SCOPE_SESSION);

			// redirect to link oauth page
			return "redirect:" + service.getAuthorizationUrl(requestToken);
		}
		return "redirect:/contact";
	}

	@RequestMapping(value = { "/oauth/xero/callback" }, method = RequestMethod.GET)
	public ModelAndView callback(
			@RequestParam(value = "oauth_verifier", required = false) String oauthVerifier,
			WebRequest request, NativeWebRequest nativeRequest) {
		ModelAndView mav = new ModelAndView();

		// getting request tocken
		OAuthService service = xeroServiceProvider.getService();
		Token requestToken = (Token) request.getAttribute(
				SessionAttributes.ATTR_OAUTH_REQUEST_TOKEN, SCOPE_SESSION);
		HttpServletRequest httpReuqest = nativeRequest
				.getNativeRequest(HttpServletRequest.class);
		SystemUser currentSessionUser = (SystemUser) httpReuqest.getSession(
				false).getAttribute(WebConstants.XERO_USER_SESSION);
		// if you logout,but you want to link the xero .we will tell you login
		// in first.
		if (null == currentSessionUser) {
			mav.setViewName("redirect:/");
			return mav;
		}
		// invalidateSession(httpReuqest) ;
		// request.get

		// getting access token
		Verifier verifier = new Verifier(oauthVerifier);
		Token accessToken = service.getAccessToken(requestToken, verifier);

		// store access token as a session attribute
		request.setAttribute(SessionAttributes.ATTR_OAUTH_ACCESS_TOKEN,
				accessToken, SCOPE_SESSION);

		// getting user profile
		// OAuthRequest oauthRequest = new OAuthRequest(Verb.GET,
		// "https://api.xero.com/api.xro/2.0/Users");
		// oauthRequest.addHeader("Accept", "application/json");
		// service.signRequest(accessToken, oauthRequest);
		// Response oauthResponse = oauthRequest.send();
		// String jsonString = oauthResponse.getBody();
		String jsonString = signXeroApi(request,
				"https://api.xero.com/api.xro/2.0/Users", service, Verb.GET);
		if ("" != jsonString && jsonString.length() > 0) {
			try {
				JSONObject jsonObj = new JSONObject(jsonString);
				JSONArray jsonArray = jsonObj.getJSONArray("Users");
				if (jsonArray.length() > 0) {
					JSONObject json = jsonArray.getJSONObject(0);
					String xeroId = json.getString("UserID");
					String localXeroId = currentSessionUser.getXeroId();
					if (null == localXeroId
							|| !localXeroId.equalsIgnoreCase(xeroId)) {
						currentSessionUser.setXeroId(xeroId);
						currentSessionUser.setUpdateDateTime(new Date());
						currentSessionUser = systemUserService
								.saveOrUpdate(currentSessionUser);
						setSession(httpReuqest, currentSessionUser, false);
					}
					mav.setViewName("redirect:/contact");
				}
			} catch (JSONException e) {
				logger.error("Prase Json Error Or Connection Timeout.", e);
				// 设置为异常页面
				mav.setViewName("redirect:/");
			}
		} else {
			logger.error("Can't get response's data .");
			// 设置为异常页面
			mav.setViewName("redirect:/");
		}

		return mav;
	}
}