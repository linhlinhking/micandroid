package ningbo.media.web.api;

//import org.json.JSONObject;

public class SystemAPITest {

	public static void main(String[] args){
		SystemUserAPI api = new SystemUserAPI() ;
		try {
			//String res = api.login("leyxan.nb@gmail.com","123456","soningbo", "112jkdu") ;
			//JSONObject json = new JSONObject(res) ;
			//System.out.println(json.toString()) ;
			
			String reg = api.register("zoopnin123", "宁波", "leyxan.nb123@gmail.com","123456", "", "", "soningbo") ;
			System.out.println(reg) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
