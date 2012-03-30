package ningbo.media.web.api;


public class LocationAPI extends RequestAPI{
	
//	public  String jerseyClient(String url,String method,String input, String type){
//		Client client = Client.create();
//		WebResource webResource = client.resource(url) ;
//		if(type.isEmpty()){
//			type="application/json";
//		}
//		ClientResponse response = null;
//		if(method.equalsIgnoreCase("get")){
//			response = webResource.accept(type).get(ClientResponse.class) ;
//		}else if(method.equalsIgnoreCase("post")){
//			//String input = "{\"username\":\"zouxw\",\"password\":\"12345678\",\"email\":\"zou.xingwei@163.com\"}";
//			response = webResource.type(type).post(ClientResponse.class, input);
//		}
//		if(response.getStatus() != 200){
//			throw new RuntimeException("请求失败:" + response.getStatus()) ;
//		}
//		String output = response.getEntity(String.class);
//		return output;
//	}
//	
//	public String jerseyClientGet(){
//		Client client = Client.create();
//		WebResource webResource = client.resource("http://localhost:8080/user/show/34") ;
//		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class) ;
//		if(response.getStatus() != 200){
//			throw new RuntimeException("请求失败:" + response.getStatus()) ;
//		}
//		String output = response.getEntity(String.class) ;
//		return output;
//	}
//	
//	public String jerseyClientPost(){
//		Client client = Client.create();
//		WebResource webResource = client.resource("http://localhost:8080/user/register");
//
//		String input = "{\"username\":\"zouxw\",\"password\":\"12345678\",\"email\":\"zou.xingwei@163.com\"}";
//
//		ClientResponse response = webResource.type("application/json").post(ClientResponse.class, input);
//
//		if (response.getStatus() != 200) {
//			throw new RuntimeException("Failed : HTTP error code : "
//					+ response.getStatus());
//		}
//
//		String output = response.getEntity(String.class);
//		return output;
//	}
	
	public String showLocations(String locationname) throws Exception{
		return getResource("http://localhost:8080/resource/location/search/"+locationname, null);
	}
	
	public String getLocationCount() throws Exception{
		return getResource("http://localhost:8080/resource/location/number", null);
	}
	
}

