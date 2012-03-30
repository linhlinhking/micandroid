package ningbo.media.util;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

public class TestJsonResult {

	private String test = "{\"result\":{\"time\":\"2011-08-28 17:29:45\",\"error\":\"161\"},\"content\":{\"point\":{\"x\":\"12947422.359722\",\"y\":\"4846434.0501397\"},\"radius\":\"130\", \"addr\":{\"detail\":\"北京市海淀区上地九街\"}}}";

	
	@Test
	public void testJson(){
		try {
			JSONObject json = new JSONObject(test) ;
			JSONObject content =  json.getJSONObject("content") ;
			JSONObject result = json.getJSONObject("result") ;
			JSONObject point = content.getJSONObject("point") ;
			JSONObject addr = content.getJSONObject("addr") ;
			String address = String.valueOf(addr.get("detail")) ;
			String longitude = String.valueOf(point.getString("y")) ;
			String latitude = String.valueOf(point.getString("x")) ;
			
			
			System.out.println(json) ;
			System.out.println(content) ;
			System.out.println(result) ; //{"time":"2011-08-28 17:29:45","error":"161"}
			System.out.println(point) ; //{"y":"4846434.0501397","x":"12947422.359722"}
			System.out.println(addr) ; //{"detail":"北京市海淀区上地九街"}
			System.out.println(address) ;//北京市海淀区上地九街
			System.out.println(longitude) ;
			System.out.println(latitude) ;
			System.out.println(result.getString("time")) ;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}

}
