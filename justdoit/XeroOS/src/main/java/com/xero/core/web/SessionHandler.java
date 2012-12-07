package com.xero.core.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionHandler {
	/**
	 * verify the session.
	 * 
	 * @param request
	 * @param url
	 * @return
	 */
	public static String verifySession(final HttpServletRequest request,
			final String url) {
		if (verifySession(request)) {
			return url;
		}
		return "redirect:/";
	}

	public static boolean verifySession(final HttpServletRequest request) {
		boolean flag = false;
		HttpSession session = request.getSession(false);
		if (null != session
				&& null != session.getAttribute(WebConstants.XERO_USER_SESSION)) {
			flag = true;
		}
		return flag;
	}
}
