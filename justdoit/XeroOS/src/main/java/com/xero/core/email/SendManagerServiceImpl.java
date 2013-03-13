package com.xero.core.email;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.xero.admin.bean.type.MailType;
import com.xero.core.Constants;
import com.xero.core.util.ApplicationContextUtil;
import com.xero.core.util.TranslateUtil;
import com.xero.website.bean.EmailFields;
import com.xero.website.service.EmailFieldsService;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class SendManagerServiceImpl implements SendManagerService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private FreeMarkerConfigurer freeMarker;
	private JavaMailSender mailSender;
	private SimpleMailMessage message;

	@Resource
	private EmailFieldsService emailFieldsService;



	/**
	 * 
	 * @param type @see com.xero.admin.bean.type.MailType
	 * @param email 
	 * @param params. 
	 * 
	 */
	public boolean sendHtmlMail(MailType type, String email,String language,Map<String, Object> params) {
		boolean flag = true;
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper;
			helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			helper.setTo(new InternetAddress(email));
			helper.setFrom(new InternetAddress(message.getFrom()));
			helper.setSubject(message.getSubject());
			Integer value = type.getValue();
			switch (value) {
			case 1:
				//Send Email To Add User
				helper.setText(commonHtmlMail(params,Constants.EMAIL_NEW_USER),true) ;
				break;
			case 2:
				helper.setText(commonHtmlMail(getEmailContent(params,language),Constants.EMAIL_SUPPLIER),true) ;
				break;
			case 3:
				//Send Email to Customer's Notes
				helper.setText(commonHtmlMail(params,Constants.EMAIL_CUSTOMER_NOTES), true) ;
				break;
			case 4:
				//Send Email To register User
				helper.setText(commonHtmlMail(params,Constants.EMAIL_REGISTER), true) ;
				break;
			}
			
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			logger.error(
					"Send Email Error.Please check this email. email Address is :" + email);
			flag = false;
		}
		return flag;
	}
	
	private String commonHtmlMail(Map<String,Object> params,String template){
		String htmlText = "" ;
		
		if(null == params){
			params = new HashMap<String,Object>() ;
		}
		freeMarker = (FreeMarkerConfigurer) ApplicationContextUtil.getContext()
				.getBean("freeMarker");
		try {
			Template tpl = null;
			
			tpl = freeMarker.getConfiguration().getTemplate(template);
			htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(tpl,
					params);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		
		return htmlText ;
		
	}


	private Map<String,Object> getEmailContent(Map<String,Object> params,String language){
		if(null == params){
			params = new HashMap<String,Object>() ;
		}
		try{
			EmailFields fd = emailFieldsService.get(1);
			if (null != fd) {
				String cName = fd.getCompanyName();
				String pInfor = fd.getProjectInfor();
				String stepName = fd.getStepName();
				String twoTxt = fd.getStepTwoText();
				String oneBtnTxt = fd.getStepOneBtnText();
				String oneTxt = fd.getStepOneText();
				String threeTxt = fd.getStepThreeText();
				String threeNoteTitle = fd.getStepThreeNoteTitle();
				String threeNoteCnt = fd.getStepThreeNoteContent();

				cName = TranslateUtil.translationContent(cName, null, language);
				pInfor = TranslateUtil.translationContent(pInfor, null,
						language);
				stepName = TranslateUtil.translationContent(stepName, null,
						language);

				twoTxt = TranslateUtil.translationContent(twoTxt, null,
						language);
				oneBtnTxt = TranslateUtil.translationContent(oneBtnTxt, null,
						language);
				oneTxt = TranslateUtil.translationContent(oneTxt, null,
						language);
				threeTxt = TranslateUtil.translationContent(threeTxt, null,
						language);
				threeNoteTitle = TranslateUtil.translationContent(
						threeNoteTitle, null, language);
				threeNoteCnt = TranslateUtil.translationContent(threeNoteCnt,
						null, language);

				params.put("companyName", cName);
				params.put("projectInfor", pInfor);
				params.put("stepName", stepName);

				params.put("stepTwoText", twoTxt);
				params.put("stepOneBtnText", oneBtnTxt);
				params.put("stepOneText", oneTxt);
				params.put("stepThreeText", threeTxt);
				params.put("stepThreeNoteTitle", threeNoteTitle);
				params.put("stepThreeNoteContent", threeNoteCnt);

			}
		
		}catch(Exception ex){
			logger.error("Translate Email Content Error.", ex) ;
		}
		
		return params ;
	}
	


	public JavaMailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public SimpleMailMessage getMessage() {
		return message;
	}

	public void setMessage(SimpleMailMessage message) {
		this.message = message;
	}

	public FreeMarkerConfigurer getFreeMarker() {
		return freeMarker;
	}

	public void setFreeMarker(FreeMarkerConfigurer freeMarker) {
		this.freeMarker = freeMarker;
	}

}