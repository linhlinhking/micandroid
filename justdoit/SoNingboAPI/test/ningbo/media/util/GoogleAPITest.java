package ningbo.media.util;

import ningbo.media.proxy.RequestURL;

import org.junit.Test;

public class GoogleAPITest {

	private static final String SERVICE_API_URL = "http://maps.googleapis.com/maps/api/geocode/json?";
	
	//29.8091635,121.5426935
	@Test
	public void testApi(){
		StringBuffer buffer = new StringBuffer();
		buffer.append(SERVICE_API_URL)
		.append("sensor=false&")
		.append("latlng=")
		.append(29.8091635)
		.append(",")
		.append(121.5426935);
		
		System.out.println(buffer.toString()) ;
		RequestURL req = new RequestURL();
		try {
			String json = req.get(buffer.toString(), null);
			System.out.println(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
