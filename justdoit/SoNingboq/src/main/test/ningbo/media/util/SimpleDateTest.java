package ningbo.media.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public class SimpleDateTest {

	private static Map<String, SimpleDateFormat> formatMap = new HashMap<String, SimpleDateFormat>();
	static SimpleDateFormat sdf = new SimpleDateFormat(
			"EEE MMM dd HH:mm:ss z yyyy", Locale.US);

	protected static Date parseDate1(String str, String format) {
		if (str == null || "".equals(str)) {
			return null;
		}
		SimpleDateFormat sdf1 = formatMap.get(format);
		if (null == sdf1) {
			sdf1 = new SimpleDateFormat(format, Locale.US);
			sdf1.setTimeZone(TimeZone.getTimeZone("GMT"));
			formatMap.put(format, sdf1);
		}
		try {
			//synchronized (sdf1) {
			//	return sdf1.parse(str);
			//}
			return sdf1.parse(str) ;
		} catch (ParseException pe) {
			pe.printStackTrace();
			return null;
		}
	}

	public static Date parseDate(String str, String format)
			throws ParseException {
		if (str == null || "".equals(str)) {
			return null;
		}
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		return sdf.parse(str);
	}

	public static void main(String args[]) {

			System.out.println(parseDate1("2011-12-05T10:30:21+08:00",
					"yyyy-MM-dd hh:mm:ss"));
	

	}

}
