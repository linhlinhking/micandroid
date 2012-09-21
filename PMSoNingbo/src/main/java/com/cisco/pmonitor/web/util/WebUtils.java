package com.cisco.pmonitor.web.util;

import org.apache.commons.lang.StringUtils;

public final class WebUtils {

	
	public static String filterUrl(String url) {
		if(StringUtils.isEmpty(url)) {
			return "";
		}
		url = url.replaceAll("&", "&amp;");
		return url;
	}
}
