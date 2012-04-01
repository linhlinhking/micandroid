package ningbo.media.web.api;


public class LocationAPI extends RequestAPI{
	
	public String showLocations(String locationname) throws Exception{
		return getResource("https://api.searchningbo.com/resource/location/search/"+locationname, null);
	}
	
	public String getLocationCount() throws Exception{
		return getResource("https://api.searchningbo.com/resource/location/number", null);
	}
	
}

