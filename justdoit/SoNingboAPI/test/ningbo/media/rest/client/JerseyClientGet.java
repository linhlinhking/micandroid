package ningbo.media.rest.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class JerseyClientGet {
	
	public static void main(String[] args){
		try{
			Client client = Client.create() ;
			WebResource webResource = client.resource("http://localhost:8080/api/rest/json/systemUser/get") ;
			ClientResponse response = webResource.accept("application/json").get(ClientResponse.class) ;
			
			if(response.getStatus() != 200){
				throw new RuntimeException("请求失败:" + response.getStatus()) ;
			}
			String output = response.getEntity(String.class) ;
			
			System.out.println("Output for Server...\n" + output) ;
		}catch(Exception ex){
			ex.printStackTrace() ;
		}
	}

}
