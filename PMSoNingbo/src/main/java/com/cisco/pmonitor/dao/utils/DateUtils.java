package com.cisco.pmonitor.dao.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public final class DateUtils {

	
	/**
	 * convert date type to string format by the special type.
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2Str(Date date, String format) {
		if(StringUtils.isEmpty(format) || null == date) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	/**
	 * convert the time to the special date.
	 * @param time
	 * @param source
	 * @param target
	 * @return
	 */
	public static String convertStr2Str(String time, String source, String target) {
		if(StringUtils.isEmpty(time) || StringUtils.isEmpty(source)) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(source);
		Date date = null;
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
		}
		return new SimpleDateFormat(target).format(date);
	}
}
