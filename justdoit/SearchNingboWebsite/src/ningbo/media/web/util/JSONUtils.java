package ningbo.media.web.util;

import org.json.JSONObject;


public class JSONUtils {

	public static boolean isArray(JSONObject json){
		boolean flag = false ;
		if(json == null){
			return flag ;
		}
		
		if(json.toString().contains("[")){
			flag = true ;
		}
		
		return flag ;
	}
	
}
