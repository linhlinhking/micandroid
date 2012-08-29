package ningbo.media.service;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SendEmailTest{
	@Test
	public void testEmail(){
		try{
			ApplicationContext context= null;  
	        context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	        SendManagerService sms = (SendManagerService)context.getBean("sendMail") ;
			//sms.sendMail("leyxan.nb@qq.com") ;
	        //sms.sendHtmlMail("leyxan.nb@qq.com","DevonNing","12","soningbo") ;
			System.out.println("SUCCESS!") ;
		}catch(Exception ex){
			ex.printStackTrace() ;
			System.out.println("Fail!") ;
		}
		
	}
}
