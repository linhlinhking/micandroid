package com.xero.admin.controller;

import static org.springframework.web.context.request.RequestAttributes.SCOPE_SESSION;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
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
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.xero.core.api.SessionAttributes;
import com.xero.core.api.server.OAuthServiceProvider;

@Controller
public class XeroController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("xeroServiceProvider")
	private OAuthServiceProvider xeroServiceProvider;

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
			WebRequest request) {

		// getting request tocken
		OAuthService service = xeroServiceProvider.getService();
		Token requestToken = (Token) request.getAttribute(
				SessionAttributes.ATTR_OAUTH_REQUEST_TOKEN, SCOPE_SESSION);

		// getting access token
		Verifier verifier = new Verifier(oauthVerifier);
		Token accessToken = service.getAccessToken(requestToken, verifier);

		// store access token as a session attribute
		request.setAttribute(SessionAttributes.ATTR_OAUTH_ACCESS_TOKEN,
				accessToken, SCOPE_SESSION);

		// getting user profile
		OAuthRequest oauthRequest = new OAuthRequest(Verb.GET,
				"https://api.xero.com/api.xro/2.0/Users");
		oauthRequest.addHeader("Accept", "application/json");
		service.signRequest(accessToken, oauthRequest);
		Response oauthResponse = oauthRequest.send();
		String jsonString = oauthResponse.getBody();
		if(jsonString == "" || jsonString.length() < 0){
			logger.error("Can't get response's data .") ;
		}
		System.out.println(jsonString);

		ModelAndView mav = new ModelAndView("redirect:/");
		return mav;
	}
}