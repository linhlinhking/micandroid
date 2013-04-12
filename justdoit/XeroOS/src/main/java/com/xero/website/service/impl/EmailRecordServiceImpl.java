package com.xero.website.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.xero.core.Response.ResponseCollection;
import com.xero.core.common.service.impl.BaseServiceImpl;
import com.xero.core.exception.ServiceException;
import com.xero.website.bean.EmailRecord;
import com.xero.website.dao.EmailRecordDao;
import com.xero.website.service.EmailRecordService;

@Service("emailRecordService")
public class EmailRecordServiceImpl extends
		BaseServiceImpl<EmailRecord, Integer> implements EmailRecordService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private EmailRecordDao emailRecordDao;

	@Autowired
	public EmailRecordServiceImpl(
			@Qualifier("emailRecordDao") EmailRecordDao emailRecordDao) {
		super(emailRecordDao);
	}

	public ResponseCollection<EmailRecord> getRecordsNoReply(String supplierId)
			throws ServiceException {
		ResponseCollection<EmailRecord> res = new ResponseCollection<EmailRecord>();
		try {
			List<EmailRecord> lists = emailRecordDao.getRecordsNoReply(supplierId);
			res.setData(lists);
			res.setResult(true);
		} catch (ServiceException se) {
			logger.error("Get All NO reply email record Error.", se);
			res.setResult(false);
			res.setData(null);
		}
		return res;
	}
	
	
	
}
