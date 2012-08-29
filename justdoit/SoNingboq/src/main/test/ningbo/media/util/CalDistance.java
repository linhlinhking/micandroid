package ningbo.media.util;

import java.math.BigDecimal;

import org.junit.Test;

public class CalDistance {
	
	private static double EARTH_RADIUS = 6378.137;
	
	public CalDistance(){}

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
		//long temp = Math.round(s * 10000) ;
		double ls = getDecimal(s) ;
		//System.out.println(temp) ;
		//System.out.println(ls) ;
		//s = Math.round(s * 10000) / 10000;
		return ls ;
	}

	public static double getDecimal(double num) {
		if (Double.isNaN(num)) {
			return 0;
		}
		BigDecimal bd = new BigDecimal(num);
		num = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return num;
	}
	
	@Test
	public void testDistance(){
		//String address1 = "宁波江东区福明街道" ;
		//121.730418,29.945105 红联
		//String address2 = "江南印象" ;
		String temp = "中国浙江 宁波市江东区中兴路505号" ;
		double lat = 29.945105 ;
		double lon = 121.730418 ;
		//double lat1 = 29.890612 ;
		//double lon1 = 121.600916 ;
		double lat1 = 29.945105 ;
		double lon1 = 121.730518 ;
		
		double dis = CalDistance.getDistance(lat,lon,lat1,lon1) ;
		System.out.println(dis) ;
		System.out.println(temp.substring(2).trim()) ;
		String s1 = temp.substring(2, 4) ;
		String s2 = temp.substring(5) ;
		StringBuffer buffer = new StringBuffer() ;
		String temp0 = buffer.append(s1).append(s2).toString() ;
		System.out.println(0.0005) ;
		System.out.println(temp0) ;
	}

}
