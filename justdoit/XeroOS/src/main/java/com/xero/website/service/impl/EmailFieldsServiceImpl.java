package com.xero.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.xero.core.common.service.impl.BaseServiceImpl;
import com.xero.website.bean.EmailFields;
import com.xero.website.dao.EmailFieldsDao;
import com.xero.website.service.EmailFieldsService;

@Service("emailFieldsService")
public class EmailFieldsServiceImpl extends
		BaseServiceImpl<EmailFields, Integer> implements EmailFieldsService {

	@Autowired
	public EmailFieldsServiceImpl(
			@Qualifier("emailFieldsDao") EmailFieldsDao emailFieldsDao) {
		super(emailFieldsDao);
	}
}
