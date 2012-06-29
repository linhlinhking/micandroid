package ningbo.media.service;

import ningbo.media.bean.enums.SendEmailType;

public interface SendManagerService {
	
	public void sendHtmlMail(String email,String username,String userId,String key,SendEmailType type);
	
}
