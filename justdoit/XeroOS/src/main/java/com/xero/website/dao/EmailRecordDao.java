package com.xero.website.dao;

import java.util.List;

import com.xero.core.common.dao.BaseDao;
import com.xero.core.exception.DaoException;
import com.xero.website.bean.EmailRecord;

public interface EmailRecordDao extends BaseDao<EmailRecord, Integer> {

	public List<EmailRecord> getRecordsNoReply(String supplierId) throws DaoException;
}
