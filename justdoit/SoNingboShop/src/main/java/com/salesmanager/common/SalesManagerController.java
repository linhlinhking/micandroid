package com.salesmanager.common;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import com.salesmanager.core.util.LabelUtil;
import com.salesmanager.core.util.LocaleUtil;
import com.salesmanager.core.util.MessageUtil;

public abstract class SalesManagerController {
	
	
	protected String getText(HttpServletRequest request, String key) {
		
		LabelUtil l = LabelUtil.getInstance();
		Locale locale = LocaleUtil.getLocale(request);
		l.setLocale(locale);
		return l.getText(
				locale, key);
		
	}
	
	protected void setMessage(String key, HttpServletRequest request) {
		LabelUtil l = LabelUtil.getInstance();
		Locale locale = LocaleUtil.getLocale(request);
		l.setLocale(locale);
		MessageUtil.addMessage(request, l.getText(key));
		
	}
	
	protected void setErrorMessage(String key, HttpServletRequest request) {
		LabelUtil l = LabelUtil.getInstance();
		Locale locale = LocaleUtil.getLocale(request);
		l.setLocale(locale);
		MessageUtil.addErrorMessage(request, l.getText(key));
		
	}

}
