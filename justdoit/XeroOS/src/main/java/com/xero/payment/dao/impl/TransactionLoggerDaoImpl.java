package com.xero.payment.dao.impl;

import org.springframework.stereotype.Repository;

import com.xero.core.common.dao.impl.BaseDaoImpl;
import com.xero.payment.bean.TransactionLogger;
import com.xero.payment.dao.TransactionLoggerDao;

@Repository("transactionLoggerDao")
public class TransactionLoggerDaoImpl extends
		BaseDaoImpl<TransactionLogger, Integer> implements TransactionLoggerDao {

	public TransactionLoggerDaoImpl() {
		super(TransactionLogger.class);
	}

}
