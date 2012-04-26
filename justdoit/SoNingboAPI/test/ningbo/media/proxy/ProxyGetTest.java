package ningbo.media.proxy;

import java.util.List;

import ningbo.media.proxy.bean.FormParamter;
import org.junit.Test;
import com.google.common.collect.Lists;



public class ProxyGetTest {

	RequestURL req = new RequestURL();
	String url = "https://api.searchningbo.com/resource/user/show/27" ;
	String url1 = "http://www.soningbo.com" ;
	String register = "http://localhost:8000/resource/user/register" ;
	String local = "http://192.168.0.104:8000/resource/user/show/27" ;
	
	
	@Test
	public void testGet() {
		try {
			long t1 = System.currentTimeMillis() ;
			String res = req.get(
					local, null);
			System.out.println(res) ;
			long t2 = System.currentTimeMillis() ;
			System.out.println(t2-t1) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void testGet1() {
		try {
			String res = req.get(
					url1, null);
			System.out.println(res) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void testPost(){
		List<FormParamter> params = Lists.newArrayList() ;
		params.add(new FormParamter("username","test1")) ;
		params.add(new FormParamter("email","test1@gmail.com")) ;
		params.add(new FormParamter("password", "1234567890")) ;
		params.add(new FormParamter("key", "soningbo")) ;
		
		try {
			String res = req.post(register, params) ;
			System.out.println(res) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
