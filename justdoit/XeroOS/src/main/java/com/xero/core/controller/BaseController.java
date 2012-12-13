package com.xero.core.controller;

import static org.springframework.web.context.request.RequestAttributes.SCOPE_SESSION;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;
import org.springframework.web.context.request.WebRequest;

import com.xero.admin.bean.SystemUser;
import com.xero.core.api.SessionAttributes;
import com.xero.core.util.CookieUtil;
import com.xero.core.util.encode.EncodeUtil;
import com.xero.core.web.WebConstants;

public class BaseController {

	public void setSession(HttpServletRequest request, SystemUser sysUser,
			boolean isRember) {
		HttpSession session = request.getSession(false);
		session.setAttribute(WebConstants.XERO_USER_SESSION, sysUser);
		if (isRember) {
			session.setMaxInactiveInterval(WebConstants.SESSION_OUT_TIME_BY_WEEK);
		} else {
			session.setMaxInactiveInterval(WebConstants.SESSION_OUT_TIME);
		}

	}

	public void invalidateSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (null != session
				&& null != session.getAttribute(WebConstants.XERO_USER_SESSION)) {
			session.invalidate();
		}

	}

	public String signXeroApi(WebRequest request, String url,
			OAuthService service, Verb v) {
		String jsonString = "";
		Token accessToken = (Token) request.getAttribute(
				SessionAttributes.ATTR_OAUTH_ACCESS_TOKEN, SCOPE_SESSION);
		if (null != accessToken) {
			// OAuthService service = xeroServiceProvider.getService();
			OAuthRequest oauthRequest = new OAuthRequest(v, url);
			oauthRequest.addHeader("Accept", "application/json");
			service.signRequest(accessToken, oauthRequest);
			Response oauthResponse = oauthRequest.send();
			jsonString = oauthResponse.getBody();

		}
		return jsonString;
	}

	public String postXeroApi(WebRequest request, String url,
			OAuthService service, Verb v) {
		String jsonString = "";
		Token accessToken = (Token) request.getAttribute(
				SessionAttributes.ATTR_OAUTH_ACCESS_TOKEN, SCOPE_SESSION);
		if (null != accessToken) {
			OAuthRequest oauthRequest = new OAuthRequest(v, url);
			oauthRequest.addHeader("Accept", "application/json");
			
			service.signRequest(accessToken, oauthRequest);
			Response oauthResponse = oauthRequest.send();
			jsonString = oauthResponse.getBody();

		}
		return jsonString;
	}

	/**
	 * 添加用户的cookie
	 * 
	 * @param response
	 * @param user
	 * 
	 */
	public void setCookie(HttpServletResponse response,
			HttpServletRequest request, SystemUser user, boolean isRember) {
		Integer expiry = 0;
		if (isRember) {
			expiry = WebConstants.COOKIE_MAX_AGE;
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append(user.getId()).append(":").append(user.getUemail())
				.append(":").append(WebConstants.WEB_KEY);
		String base64CookieValue = EncodeUtil.base64UrlSafeEncode(buffer
				.toString().getBytes());
		CookieUtil.setCookie(request, response,
				WebConstants.COOKIE_DOMAIN_NAME, base64CookieValue, expiry);
	}
}
