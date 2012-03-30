package ningbo.media.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import ningbo.media.service.SendManagerService;
import ningbo.media.util.ApplicationContextUtil;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class SendManagerServiceImpl implements SendManagerService {

	
	private FreeMarkerConfigurer freeMarker;
	private JavaMailSender mailSender;
	private SimpleMailMessage message;


	public void sendHtmlMail(final String email, final String username,
			final String userId, final String key) {

		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(mimeMessage, true, "GBK");
			helper.setTo(new InternetAddress(email));
			helper.setFrom(new InternetAddress(message.getFrom()));
			helper.setSubject(message.getSubject());
			helper.setText(getContentHtml(username, userId, key), true);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		mailSender.send(mimeMessage);
	}


	private String getContentHtml(String username, String userId, String key) {
		String htmlText = "";
		freeMarker = (FreeMarkerConfigurer) ApplicationContextUtil.getContext()
				.getBean("freeMarker");
		try {
			Template tpl = freeMarker.getConfiguration().getTemplate(
					"send_html.ftl");
			Map<String, String> map = new HashMap<String, String>();
			map.put("username", username);
			map.put("userId", userId);
			map.put("key", key);

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