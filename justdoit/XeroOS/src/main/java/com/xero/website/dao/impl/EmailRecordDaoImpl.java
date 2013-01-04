package com.xero.website.dao.impl;

import org.springframework.stereotype.Repository;

import com.xero.core.common.dao.impl.BaseDaoImpl;
import com.xero.website.bean.EmailRecord;
import com.xero.website.dao.EmailRecordDao;

@Repository("emailRecordDao")
public class EmailRecordDaoImpl extends BaseDaoImpl<EmailRecord, Integer>
		implements EmailRecordDao {

	public EmailRecordDaoImpl() {
		super(EmailRecord.class);
	}

}
