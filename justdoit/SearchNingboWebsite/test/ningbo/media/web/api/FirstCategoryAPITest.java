package ningbo.media.web.api;

import ningbo.media.web.bean.FirstCategory;

import org.json.JSONObject;
import org.junit.Test;

public class FirstCategoryAPITest {

	private  CategoryAPI api = new CategoryAPI() ;
	
	
	
	private  FirstCategory getFirstCategoryById(String id){
		FirstCategory first = new FirstCategory() ;
		try {
			String response = api.getFirstCategoryById(id) ;
			if(null == response || response.length() < 0){
				return null ;
			}
			JSONObject json = new JSONObject(response) ;
			first.setId(Integer.valueOf(json.getString("id"))) ;
			first.setName_cn(json.getString("name_cn")) ;
			first.setName_en(json.getString("name_en")) ;
			first.setDescription(json.getString("description")) ;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null ;
		}
		
		return first ;
	}
	
	
	@Test
	public void testname() throws Exception {
		FirstCategory first = getFirstCategoryById(String.valueOf(1)) ;
		System.out.println(first.getName_cn()) ;
	}
}
