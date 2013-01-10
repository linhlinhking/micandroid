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

	public boolean sendHtmlMail(final String email, final String customerName,
			final String supplierCompanyName, final String poNumber,
			final String linkUrl, final String language) {
		boolean flag = true;
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper;
			helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			helper.setTo(new InternetAddress(email));
			helper.setFrom(new InternetAddress(message.getFrom()));
			helper.setSubject(message.getSubject());
			helper.setText(
					getContentHtml(customerName, supplierCompanyName, poNumber,
							linkUrl, language), true);
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			logger.error(
					"Send Email Error.Please check this email. email Address is :"
							+ email, e);
			flag = false;
		}
		return flag;
	}

	/** 1)template do the param 2)Map do the param */
	private String getContentHtml(String customerName,
			String supplierCompanyName, String poNumber, String linkUrl,
			String language) {
		String htmlText = "";
		freeMarker = (FreeMarkerConfigurer) ApplicationContextUtil.getContext()
				.getBean("freeMarker");
		try {
			Template tpl = null;
			Map<String, String> map = new HashMap<String, String>();

			tpl = freeMarker.getConfiguration().getTemplate(
					"supplier_email.ftl");
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

				map.put("companyName", cName);
				map.put("projectInfor", pInfor);
				map.put("stepName", stepName);

				map.put("stepTwoText", twoTxt);
				map.put("stepOneBtnText", oneBtnTxt);
				map.put("stepOneText", oneTxt);
				map.put("stepThreeText", threeTxt);
				map.put("stepThreeNoteTitle", threeNoteTitle);
				map.put("stepThreeNoteContent", threeNoteCnt);

			}
			map.put("customerCompanyName", customerName) ;
			map.put("supplierCompanyName", supplierCompanyName);
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