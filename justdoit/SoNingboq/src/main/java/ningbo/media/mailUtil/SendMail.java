package ningbo.media.mailUtil;

import ningbo.media.rest.util.DateUtil;

public class SendMail {
	
	public static final Integer SEND_PLAIN_TEXT = 1; //表示发送文本格式的邮件
	public static final Integer SEND_HTML = 2; //表示发送html格式的邮件

	/**
	 * @param method : the way to send email , 
	 * 					1.SEND_PLAIN_TEXT means 发送文本格式的邮件
	 * 					2.SEND_HTML means 发送html格式的邮件
	 * @param address 要发送的邮件地址
	 * @return true if the mail is send success, false if the mail send fail
	 */
	public static final boolean SendMailTo(Integer method, String address, Integer id, String key){
		 boolean flag = false;
		 
		 //这个类主要是设置邮件  
	     MailSenderInfo mailInfo = new MailSenderInfo();   
	     mailInfo.setMailServerHost("smtp.163.com");   
	     mailInfo.setMailServerPort("25");   
	     mailInfo.setValidate(true);   
	     mailInfo.setUserName("soningboserver@163.com");   
	     mailInfo.setPassword("soningbo123");//您的邮箱密码   
	     mailInfo.setFromAddress("soningboserver@163.com");   
	     mailInfo.setToAddress(address);   
	     mailInfo.setSubject("SoNingboMail");
	     StringBuffer buffer = new StringBuffer();
	     buffer.append("您好！<br/>感谢您注册SoNingbo帐户。为激活您的帐户，请点击一下链接：<br/>");
	     buffer.append("<a href='http://localhost:8080/user/verifystatus/"+id+"/"+key+"'><font color='red'>http://localhost:8080/user/verifystatus/"+id+"/"+key+"</font></a><br/>");
	     buffer.append("此邮件为系统发送，请勿回复！");
	     buffer.append("<div style='text-align:right'>SoNingbo<br/>" + DateUtil.date2String("yyyy-MM-dd HH:mm:ss") + "</div>");
	     mailInfo.setContent(buffer.toString());   
	     //这个类主要来发送邮件  
	     SimpleMailSender sms = new SimpleMailSender();  
	     if(method == SEND_PLAIN_TEXT){
	    	 flag = sms.sendTextMail(mailInfo);//发送文体格式
	     }else if(method == SEND_HTML){
	    	 flag = sms.sendHtmlMail(mailInfo);//发送html格式 
	     }
	     return flag;
	}
}
