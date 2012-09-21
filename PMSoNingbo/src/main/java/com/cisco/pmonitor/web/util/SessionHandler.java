package com.cisco.pmonitor.web.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.cisco.pmonitor.dao.utils.Constants;

public final class SessionHandler {

	/**
	 * verify the session.
	 * @param request
	 * @param url
	 * @return
	 */
	public static String verifySession(final HttpServletRequest request, final String url) {
		HttpSession session = request.getSession(false);
		if(null != session && null != session.getAttribute(
										Constants.PM_USER_SESSION)) {
			return url;
		}
		return "login";
	}
}
