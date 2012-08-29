package ningbo.media.oauth2.utils;

import org.junit.Test;



public class BASE64EncoderTest {

	@Test
	public void testApiSecret(){
		String apiSecrete = "搜索宁波" ;//应用的名称
		String token1 = "zoopnin" ; //应用的账号
		System.out.println(BASE64Encoder.encode(apiSecrete.getBytes())) ;
		System.out.println(BASE64Encoder.encode(token1.getBytes())) ;
		
	}
}
