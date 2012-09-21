package com.cisco.pmonitor.web.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cisco.pmonitor.dao.utils.Constants;

public class SystemInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String url = request.getRequestURI();
		if(url.endsWith("login") || url.endsWith("index.jsp")) {
			return true;
		}
		HttpSession session = request.getSession(false);
		if(null != session && null != session.getAttribute(
											Constants.PM_USER_SESSION)) {
			return true;
		}
		response.sendRedirect(request.getContextPath() + "/index.jsp");
		return false;
	}

}
