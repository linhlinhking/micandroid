package ningbo.media.service;

import ningbo.media.BaseTest;
import ningbo.media.util.ApplicationContextUtil;

import org.junit.Test;

public class SendMailTest extends BaseTest {

	@Test
	public void testMail() {
		SendManagerService sms = (SendManagerService) ApplicationContextUtil
				.getContext().getBean("sendMail");
		
		try{
			//sms.sendHtmlMail("leyxan.nb@qq.com","DevonNing","12","soningbo") ;
			System.out.println("SUCCESS!") ;
		}catch(Exception ex){
			ex.printStackTrace() ;
			System.out.println("Fail!") ;
		}
		
	}

}
