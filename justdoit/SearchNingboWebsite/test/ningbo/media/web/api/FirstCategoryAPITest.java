package ningbo.media.web.api;

import java.util.ArrayList;
import java.util.List;

import ningbo.media.web.bean.FirstCategory;
import ningbo.media.web.bean.SecondCategory;
import ningbo.media.web.util.JSONUtils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

public class FirstCategoryAPITest {

	private  CategoryAPI api = new CategoryAPI() ;
	
	private List<SecondCategory> getSecondCategoryById(String did) {
		List<SecondCategory> list = new ArrayList<SecondCategory>();
		try {
			String response = api.showCategory(did);
			JSONObject json = new JSONObject(response);
			if (JSONUtils.isArray(json)) {
				JSONArray array = json.getJSONArray("secondCategory");
				for (int i = 0, j = array.length(); i < j; i++) {
					SecondCategory sc = new SecondCategory();
					JSONObject temp = array.getJSONObject(i);
					sc.setId(Integer.valueOf(temp.getString("id")));
					sc.setName_cn(temp.getString("name_cn"));
					sc.setName_en(temp.getString("name_en"));
					list.add(sc);
				}
			}else{
				JSONObject jsonObj = json.getJSONObject("secondCategory") ;
				System.out.println(jsonObj) ;
				SecondCategory sc = new SecondCategory();
				sc.setId(Integer.valueOf(jsonObj.getString("id")));
				sc.setName_cn(jsonObj.getString("name_cn"));
				sc.setName_en(jsonObj.getString("name_en"));
				list.add(sc);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}
	
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
		//FirstCategory first = getFirstCategoryById(String.valueOf(1)) ;
		//System.out.println(first.getName_cn()) ;
		List<SecondCategory> list = getSecondCategoryById(String.valueOf(13)) ;
	}
}
