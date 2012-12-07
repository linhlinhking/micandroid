package com.xero.admin.util;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

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
     * @param beginDate 
     * @param endDate 
     * @return 
     */  
    public static int daysOfTwoDate(Date beginDate,Date endDate){  
        int days = 1;//两个日期之前的天数  
          
        Calendar beginCalendar = Calendar.getInstance();  
        Calendar endCalendar = Calendar.getInstance();  
          
        beginCalendar.setTime(beginDate);  
        endCalendar.setTime(endDate);  
        //计算天数  
        while(beginCalendar.before(endCalendar)){  
            days++;  
            beginCalendar.add(Calendar.DAY_OF_MONTH, 1);  
        }  
        return days;  
    }
	
  

}
