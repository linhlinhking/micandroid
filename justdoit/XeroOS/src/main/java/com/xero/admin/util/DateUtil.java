package com.xero.admin.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public final static String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

	public final static String FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	public final static String FORMAT_YYYY_N_MM_Y_MM_R = "yyyy年MM月dd日";

	public final static String FORMAT_MM_DD_YYYY = "MM/dd/yyyy";

	public final static String FORMAT_DD_MM_YYYY = "dd/MM/yyyy";

	private DateUtil() {
		super();
	}

	public static Date addDate(Date d, long day) throws ParseException {
		long time = d.getTime();
		day = day * 24 * 60 * 60 * 1000;
		time += day;
		return new Date(time);
	}

	/**
	 * 计算两个日期之间的天数＋1
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static int daysOfTwoDate(Date beginDate, Date endDate) {
		int days = 1;// 两个日期之前的天数

		Calendar beginCalendar = Calendar.getInstance();
		Calendar endCalendar = Calendar.getInstance();

		beginCalendar.setTime(beginDate);
		endCalendar.setTime(endDate);
		// 计算天数
		while (beginCalendar.before(endCalendar)) {
			days++;
			beginCalendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		return days;
	}

	public static int daysBetweenDate(Date firstDate,Date secondDate) {
		Calendar aCalendar = Calendar.getInstance();
		aCalendar.setTime(firstDate);
		// 计算此日期是一年中的哪一天
		int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
		aCalendar.setTime(secondDate);
		int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
		// 求出两日期相隔天数
		int days = day2 - day1;
		return days;
	}

	public static int getNowYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	/**
	 * 字符串yyyy-MM-dd转换到Date类型
	 * 
	 * @param dateStr
	 *            yyyy-MM-dd
	 * @return Date
	 */
	public static Date strToEnDate(String dateStr) {
		return strToDate(dateStr, FORMAT_DD_MM_YYYY);
	}

	public static String dateToString(Date d) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DD_MM_YYYY);
		return dateFormat.format(d);
	}

	/**
	 * 字符串转换到Date类型
	 * 
	 * @param dateStr
	 *            需要转换的字符串
	 * @param format
	 *            转换格式
	 * @return Date
	 */
	public static Date strToDate(String dateStr, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		dateFormat.setLenient(false);
		Date date = dateFormat.parse(dateStr, new ParsePosition(0));
		return date;
	}

	public static Date millisToDate(String millis) {
		if (null == millis) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(Long.valueOf(millis));
		return cal.getTime();
	}

	public static void main(String args[]) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		long now = System.currentTimeMillis();
		String v = "1357269821000";
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(Long.valueOf(v));
		System.out.println(now + " = " + formatter.format(calendar.getTime()));
		
	}

}
