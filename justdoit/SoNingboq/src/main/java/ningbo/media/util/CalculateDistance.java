package ningbo.media.util;

import java.math.BigDecimal;

public class CalculateDistance {

	private static double EARTH_RADIUS = 6378.137;
	
	private CalculateDistance(){}

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	public static double getDistance(double lat1, double lng1, double lat2,
			double lng2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		//s = Math.round(s * 10000) / 10000;
		double ls = getDecimal(s) ;
		return ls;
	}

	public static double getDecimal(double num) {
		if (Double.isNaN(num)) {
			return 0;
		}
		BigDecimal bd = new BigDecimal(num);
		num = bd.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
		return num;
	}
	
	
	public static void main(String args[]){
		//29.8731      29.873423  29.873568  29.872871  29.873396  29.8678665
		//121.560842   121.560769 121.560859 121.560804 121.560688  121.5661315
		
		System.out.println(String.valueOf(getDistance(29.8678665,121.5661315,29.873423,121.560769))) ;
	}
}
