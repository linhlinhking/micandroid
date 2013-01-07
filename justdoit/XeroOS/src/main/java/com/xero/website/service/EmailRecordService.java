package com.xero.website.service;

import com.xero.core.Response.ResponseCollection;
import com.xero.core.common.service.BaseService;
import com.xero.core.exception.ServiceException;
import com.xero.website.bean.EmailRecord;

public interface EmailRecordService extends BaseService<EmailRecord, Integer> {

	public ResponseCollection<EmailRecord> getRecordsNoReply(String supplierId)
			throws ServiceException;
}
