package ningbo.media.rest.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * handle date 
 * @author zouxw
 * @create 2012-03-01
 */
public class DateUtil {
	
	/**
	 * format the date(today's date) to string 
	 * @param dateType  like yyy-MM-dd 
	 * @return 
	 */
	public static String date2String(String dateType){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(dateType);
		return sdf.format(date);
	}
	
	/**
	 * format the date to string with dateType
	 * @param date 
	 * @param dateType  like yyy-MM-dd 
	 * @return 
	 */
	public static String date2String(Date date,String dateType){
		SimpleDateFormat sdf = new SimpleDateFormat(dateType);
		return sdf.format(date);
	}
	

	
}
