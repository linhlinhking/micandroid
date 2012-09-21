package com.cisco.pmonitor.service;

import com.cisco.pmonitor.dao.dataobject.MailDo;


public interface IMailService {

	/**
	 * provide sending mail function.
	 * @param mail
	 * @return
	 */
	public boolean sendMail(MailDo mail);
}
