package com.xero.core.email;


public interface SendManagerService {
	
	public boolean sendHtmlMail(String email,String customerName,String username,String userId,String key,String language);
	
}
