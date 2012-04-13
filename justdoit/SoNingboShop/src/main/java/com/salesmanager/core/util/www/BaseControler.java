package com.salesmanager.core.util.www;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.i18n.LocaleContextHolder;

public class BaseControler {
	
	public Locale getLocale() {
		
		Locale locale = LocaleContextHolder.getLocale();
		return locale;

		
	}

}
