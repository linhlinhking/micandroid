package ningbo.media.oauth2;

import ningbo.media.oauth2.builder.Api;
import ningbo.media.oauth2.builder.ServiceBuilder;
import ningbo.media.oauth2.model.OAuthConfig;
import ningbo.media.oauth2.services.OAuthService;
import org.junit.Before;
import org.junit.Test;

public class ServiceBuilderTest {

	private ServiceBuilder server;

	@Before
	public void setUp() throws Exception {
		server = new ServiceBuilder() ;
	}



	//@Test
	public void testDefaultValue(){
		server.provider(ApiMock.class).apiKey("123").apiSecret("34556678").build() ;
		System.out.println(ApiMock.config.getApiKey()) ;
		System.out.println(ApiMock.config.getApiSecret()) ;
	}
	
	@Test
	public void testDefaultValueCallback(){
		server.provider(ApiMock.class).apiKey("123").apiSecret("34556678").callback("https://api.searchningbo.com").build() ;
		System.out.println(ApiMock.config.getApiKey()) ;
		System.out.println(ApiMock.config.getApiSecret()) ;
		System.out.println(ApiMock.config.getCallback()) ;
	}
	
	public static class ApiMock implements Api {
		public static OAuthConfig config;

		public OAuthService createService(OAuthConfig config) {
			ApiMock.config = config;
			return null;
		}
	}

}
