package ningbo.media.web.api;

public class CategoryAPI extends RequestAPI{

	public String getFirstCategory() throws Exception{
		return getResource("https://api.searchningbo.com/resource/category/first/showAll", null);
	}
	
	public String getFirstCategoryById(String id) throws Exception{
		return getResource("https://api.searchningbo.com/resource/category/first/show/" + id, null);
	}
	
	/**
	public String showCategory2(String category1_id) throws Exception{
		return getResource("https://api.searchningbo.com/resource/category/second/show/"+category1_id, null);
	}
	*/
	public String showCategory(String id) throws Exception{
		return getResource("https://api.searchningbo.com/resource/category/second/show/" + id, null);
	}
	
	public String getLocationsBySecondCategoryId(String secondId) throws Exception{
		return getResource("https://api.searchningbo.com/resource/location/category/" + secondId,null) ;
	}
	
}
