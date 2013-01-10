package com.xero.website.dao.impl;

import org.springframework.stereotype.Repository;

import com.xero.core.common.dao.impl.BaseDaoImpl;
import com.xero.website.bean.EmailFields;
import com.xero.website.dao.EmailFieldsDao;

@Repository("emailFieldsDao")
public class EmailFieldsDaoImpl extends BaseDaoImpl<EmailFields, Integer>
		implements EmailFieldsDao {

	
	public EmailFieldsDaoImpl(){
		super(EmailFields.class);
	}
}
