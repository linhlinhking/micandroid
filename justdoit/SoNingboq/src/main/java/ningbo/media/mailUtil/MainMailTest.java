package ningbo.media.mailUtil;

public class MainMailTest {
	public static void main(String[] args){  
//        //这个类主要是设置邮件  
//	     MailSenderInfo mailInfo = new MailSenderInfo();   
//	     mailInfo.setMailServerHost("smtp.163.com");   
//	     mailInfo.setMailServerPort("25");   
//	     mailInfo.setValidate(true);   
//	     mailInfo.setUserName("soningboserver@163.com");   
//	     mailInfo.setPassword("soningbo123");//您的邮箱密码   
//	     mailInfo.setFromAddress("soningboserver@163.com");   
//	     mailInfo.setToAddress("304635843@qq.com");   
//	     mailInfo.setSubject("SoNingboMail");   
//	     mailInfo.setContent("<a href='http://www.google.com'><font color='red'>www.google.com</font></a>");   
//	     //这个类主要来发送邮件  
//	     SimpleMailSender sms = new SimpleMailSender();  
//	     Boolean flag1 = sms.sendTextMail(mailInfo);//发送文体格式      
//	     if(flag1)  
//	     System.out.println("发送文体格式mail 成功！");  
//	     Boolean flag2 = sms.sendHtmlMail(mailInfo);//发送html格式    
//	     if(flag2)  
//	       System.out.println("发送html格式mail 成功！");  
		boolean b = SendMail.SendMailTo(SendMail.SEND_HTML, "835218491@qq.com",34,"soningbo");
		System.out.println(b);
	   }  
}
