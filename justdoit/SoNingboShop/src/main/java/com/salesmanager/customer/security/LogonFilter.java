package com.salesmanager.customer.security;

import java.io.IOException;
import java.security.Principal;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.salesmanager.core.entity.customer.Customer;
import com.salesmanager.core.module.impl.application.logon.UserPrincipal;
import com.salesmanager.core.util.LabelUtil;
import com.salesmanager.core.util.LocaleUtil;
import com.salesmanager.core.util.MessageUtil;
import com.salesmanager.core.util.www.SessionUtil;

public class LogonFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		request.setCharacterEncoding("UTF-8");
		
		//should be logged in else go to home page
		Principal principal = (UserPrincipal)req.getSession().getAttribute("PRINCIPAL");
		if(principal==null) {
			resp.sendRedirect(req.getContextPath() + "/");//home page
			LabelUtil l = LabelUtil.getInstance();
			Locale locale = LocaleUtil.getLocale(req);
			l.setLocale(locale);
			MessageUtil.addErrorMessage(req, l.getText(
					locale, "messages.notauthenticated"));
			return;
		}
		
		Customer customer = SessionUtil.getCustomer(req);
		if(customer==null) {
			resp.sendRedirect(req.getContextPath() + "/");//home page
			LabelUtil l = LabelUtil.getInstance();
			Locale locale = LocaleUtil.getLocale(req);
			l.setLocale(locale);
			MessageUtil.addErrorMessage(req, l.getText(
					locale, "messages.notauthenticated"));
			return;
		}
		
		req.getSession().removeAttribute("mainUrl");
		req.getSession().removeAttribute("subCategory");
		req.getSession().removeAttribute("categoryPath");
		req.getSession().removeAttribute("IDLIST");
		req.getSession().removeAttribute("CATEGORYPATH");

		req.getSession().setAttribute("profileUrl", "profile");
		
		chain.doFilter(request, response);

	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}
