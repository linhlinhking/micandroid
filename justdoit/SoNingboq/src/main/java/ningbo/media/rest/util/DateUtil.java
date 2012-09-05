package ningbo.media.rest.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * handle date
 * 
 * @create 2012-03-01
 */
public class DateUtil {

	public static final String SHORT_FORMAT_TYPE = "yyyy-MM-dd";

	public static final String FULL_FORMAT_TYPE = "yyyy-MM-dd HH:mm:ss";

	/**
	 * format the date(today's date) to string
	 * 
	 * @param dateType
	 *            like yyy-MM-dd
	 * @return
	 */
	public static String date2String(String dateType) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(dateType);
		return sdf.format(date);
	}

	/**
	 * format the date to string with dateType
	 * 
	 * @param date
	 * @param dateType
	 *            like yyy-MM-dd
	 * @return
	 */
	public static String date2String(Date date, String dateType) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateType);
		return sdf.format(date);
	}

	/**
	 * 字符串转换成日期
	 * 
	 * @param str
	 * @return date
	 */
	public static Date stringToDate(String str) {
		return stringToDate(str, SHORT_FORMAT_TYPE);
	}

	/**
	 * 取当前系统日期，并按指定格式或者是默认格式返回
	 * 
	 * @param format
	 * @return
	 */
	public static String getNow(String format) {
		if (null == format || "".equals(format)) {
			format = FULL_FORMAT_TYPE;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String date = sdf.format(new Date());
		return date;
	}

	/**
	 * 将日期型转换为指定格式的字符串
	 * 
	 * @param date
	 *            日期
	 * @param format
	 *            格式
	 * @return
	 */
	public static String dateToString(Date date, String format) {
		if (null == format || "".equals(format)) {
			format = "yyyy年MM月dd日 hh点:mm分:ss秒";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 将字符型转换为指定格式日期型
	 * 
	 * @param _date
	 *            需要转换成日期的字符串
	 * @param format
	 *            与需要转换成日期的字符串相匹配的格式
	 * @return
	 */
	private static Date stringToDate(String _date, String format) {
		if (null == format || "".equals(format)) {
			format = FULL_FORMAT_TYPE;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = sdf.parse(_date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

}
