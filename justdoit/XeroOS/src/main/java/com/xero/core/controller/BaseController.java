package com.xero.core.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xero.admin.bean.SystemUser;
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
	
	/**
	 * 添加用户的cookie
	 * 
	 * @param response
	 * @param user
	 * 
	 */
	public static void setCookie(HttpServletResponse response,HttpServletRequest request, SystemUser user,boolean isRember) {
		Integer expiry = 0 ;
		if(isRember){
			expiry = WebConstants.COOKIE_MAX_AGE ;
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append(user.getId()).append(":").append(user.getUemail())
				.append(":").append(WebConstants.WEB_KEY);
		String base64CookieValue = EncodeUtil.base64UrlSafeEncode(buffer
				.toString().getBytes());
		CookieUtil.setCookie(request, response, WebConstants.COOKIE_DOMAIN_NAME, base64CookieValue, expiry) ;
	}
}
