package com.xero.website.service;

import com.xero.admin.bean.type.ContactType;
import com.xero.core.Response.ResponseCollection;
import com.xero.core.common.service.BaseService;
import com.xero.core.exception.ServiceException;
import com.xero.website.bean.Contact;

public interface ContactService extends BaseService<Contact, Integer> {

	public ResponseCollection<Contact> queryContactById(Integer id,
			ContactType type) throws ServiceException;
}
