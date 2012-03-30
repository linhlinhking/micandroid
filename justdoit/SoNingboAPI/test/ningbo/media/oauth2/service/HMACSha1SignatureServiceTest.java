package ningbo.media.oauth2.service;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import ningbo.media.oauth2.exception.OAuthException;
import ningbo.media.oauth2.services.HMACSha1SignatureService;
import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;

public class HMACSha1SignatureServiceTest {

	private HMACSha1SignatureService service;

	@Before
	public void setup() {
		service = new HMACSha1SignatureService();
	}

	@Test
	public void shouldReturnSignatureMethodString() {
		String method = service.getSignatureMethod() ;
		
		System.out.println(method) ;
	}
	
	  @Test
	  public void shouldReturnSignature()
	  {
	    String apiSecret = "api secret";
	    String tokenSecret = "token secret";
	    String baseString = "base string";
	    System.out.println(service.getSignature(baseString, apiSecret, tokenSecret)) ;
	  }

	  @Test(expected = OAuthException.class)
	  public void shouldThrowExceptionIfBaseStringIsNull()
	  {
	    service.getSignature(null, "apiSecret", "tokenSecret");
	  }

	  @Test(expected = OAuthException.class)
	  public void shouldThrowExceptionIfBaseStringIsEmpty()
	  {
	    service.getSignature("  ", "apiSecret", "tokenSecret");
	  }

	  @Test(expected = OAuthException.class)
	  public void shouldThrowExceptionIfApiSecretIsNull()
	  {
	    service.getSignature("base string", null, "tokenSecret");
	  }

	  @Test(expected = OAuthException.class)
	  public void shouldThrowExceptionIfApiSecretIsEmpty()
	  {
	    service.getSignature("base string", "  ", "tokenSecret");
	  }
	  
	  @Test
	  public void testApiSecret(){
		  String EMPTY_STRING = "";
		  String CARRIAGE_RETURN = "\r\n";
		  String apiSecret = "api" ;
		  String token = "test" ;
		  String keyString = apiSecret + "&"+token ;
		  String toSign = "api.searchningbo.com" ;
		  
		  try {
			SecretKeySpec key = new SecretKeySpec((keyString).getBytes("UTF-8"), "HmacSHA1");
			Mac mac = Mac.getInstance("HmacSHA1");
			mac.init(key) ;
			byte[] bytes = mac.doFinal(toSign.getBytes("UTF-8")) ;
			String str = new String(Base64.encodeBase64(bytes)).replace(CARRIAGE_RETURN, EMPTY_STRING) ;
			System.out.println(str) ;
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
	  }
}
