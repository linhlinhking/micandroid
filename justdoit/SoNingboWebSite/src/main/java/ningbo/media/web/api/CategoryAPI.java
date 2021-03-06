package ningbo.media.web.api;

public class CategoryAPI extends RequestAPI{

	public String showAllCategory() throws Exception{
		return getResource("https://api.searchningbo.com/resource/category/first/showAll", null);
	}
	
	public String showCategory2(String category1_id) throws Exception{
		return getResource("https://api.searchningbo.com/resource/category/second/show/"+category1_id, null);
	}
	
	public String showCategory(String id) throws Exception{
		return getResource("http://localhost:8080/resource/category/second/show/" + id, null);
	}
	
}
