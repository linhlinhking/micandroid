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
		return strToDate(dateStr,FORMAT_MM_DD_YYYY);
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
	
	public static void main(String args[]){
		System.out.println(strToEnDate("12/12/2012")) ;
	}

}
