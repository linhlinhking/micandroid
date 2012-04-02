package ningbo.media.web.util;


public class JSONUtils {

	public static boolean isArray(String json){
		boolean flag = false ;
		if(json == null){
			return flag ;
		}
		
		if(json.contains("[]")){
			flag = true ;
		}
		
		return flag ;
	}
	
	
	public static void main(String args[]){
		String t1 = "soningbo:[]" ;
		System.out.println(isArray(t1)) ;
	}
	
}
