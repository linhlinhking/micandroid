package com.xero.website.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xero.core.common.dao.impl.BaseDaoImpl;
import com.xero.core.exception.DaoException;
import com.xero.website.bean.EmailRecord;
import com.xero.website.dao.EmailRecordDao;

@Repository("emailRecordDao")
public class EmailRecordDaoImpl extends BaseDaoImpl<EmailRecord, Integer>
		implements EmailRecordDao {

	public EmailRecordDaoImpl() {
		super(EmailRecord.class);
	}

	
	public List<EmailRecord> getRecordsNoReply(String supplierId) throws DaoException {
		String hql = "from EmailRecord as er where 1=1 and er.reply = 0 and er.supplierId = ? order by er.sendDate asc " ;
		List<EmailRecord> lists = findByHql(hql,supplierId) ;
		return lists;
	}

}
