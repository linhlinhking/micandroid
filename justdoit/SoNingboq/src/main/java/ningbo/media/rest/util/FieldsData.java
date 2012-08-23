package ningbo.media.rest.util;


import java.util.ArrayList;
import java.util.List;

import com.sun.jersey.multipart.FormDataBodyPart;

public class FieldsData {
	
	public static List<String> getValue(List<FormDataBodyPart> values){
		List<String> listValues = new ArrayList<String>() ;
		if(null == values){
			return null ;
		}
		for(FormDataBodyPart p : values){
			listValues.add(p.getValue()) ;
		}
		return listValues ;
	}
	
}
