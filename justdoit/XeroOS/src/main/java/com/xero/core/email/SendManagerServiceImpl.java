package com.xero.core.email;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.xero.core.util.ApplicationContextUtil;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class SendManagerServiceImpl implements SendManagerService {

	private FreeMarkerConfigurer freeMarker;
	private JavaMailSender mailSender;
	private SimpleMailMessage message;

	public void sendHtmlMail(final String email, final String companyName,
			final  String poNumber, final String linkUrl) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			helper.setTo(new InternetAddress(email));
			helper.setFrom(new InternetAddress(message.getFrom()));
			helper.setSubject(message.getSubject());
			helper.setText(getContentHtml(companyName, poNumber, linkUrl), true);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		mailSender.send(mimeMessage);
	}

	
	/**1)template do the param 2)Map do the param*/
	private String getContentHtml(String companyName, String poNumber, String linkUrl) {
		String htmlText = "";
		freeMarker = (FreeMarkerConfigurer) ApplicationContextUtil.getContext()
				.getBean("freeMarker");
		try {
			Template tpl = null;
			Map<String, String> map = new HashMap<String, String>();

			tpl = freeMarker.getConfiguration().getTemplate("supplier_email.ftl");
			map.put("companyName", companyName);
			map.put("poNumber", poNumber);
			map.put("linkUrl", linkUrl);

			htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(tpl,
					map);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		return htmlText;
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