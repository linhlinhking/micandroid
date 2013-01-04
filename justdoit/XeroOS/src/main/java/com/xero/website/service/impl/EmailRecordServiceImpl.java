package com.xero.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.xero.core.common.service.impl.BaseServiceImpl;
import com.xero.website.bean.EmailRecord;
import com.xero.website.dao.EmailRecordDao;
import com.xero.website.service.EmailRecordService;

@Service("emailRecordService")
public class EmailRecordServiceImpl extends
		BaseServiceImpl<EmailRecord, Integer> implements EmailRecordService {

	@Autowired
	public EmailRecordServiceImpl(
			@Qualifier("emailRecordDao") EmailRecordDao emailRecordDao) {
		super(emailRecordDao);
	}
}
