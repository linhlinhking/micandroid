package com.xero.core.email;

import java.util.Map;

import com.xero.admin.bean.type.MailType;

public interface SendManagerService {


	public boolean sendHtmlMail(MailType type, String email,
			String lanugage, Map<String, Object> params);
}
