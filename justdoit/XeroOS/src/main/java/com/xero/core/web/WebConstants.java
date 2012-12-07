package com.xero.core.web;

public final class WebConstants {
	public static final String XERO_USER_SESSION = "XERO_USER_SESSION";
	public static final int SESSION_OUT_TIME = 1800;
	public static final int SESSION_OUT_TIME_BY_WEEK = 604800 ;
	
	/**
	 * 设置cookie的域名
	 */
	public static final String COOKIE_DOMAIN_NAME = "dev.globaldesign.co.nz" ;
	/**
	 * 加密cookie时的网站自定码
	 */
	public static final String WEB_KEY = "_security_user_key";
	/**
	 * 设置cookie有效期是两个星期，根据需要自定义
	 */
	public static final int COOKIE_MAX_AGE = 60 * 60 * 24 * 7 * 2;
}
