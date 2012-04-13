package com.salesmanager.common;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.salesmanager.core.entity.merchant.MerchantStore;
import com.salesmanager.core.util.LocaleUtil;

public class SalesManagerLocaleInterceptor extends HandlerInterceptorAdapter {

	
	
	   public boolean preHandle(
	            HttpServletRequest request,
	            HttpServletResponse response,
	            Object handler) throws Exception {
		   
		   
		   MerchantStore store = (MerchantStore)request.getAttribute("STORE");
		   
		   LocaleUtil.setLocaleForRequest(request, response, store);
		   
	       return true;
	    }

}
