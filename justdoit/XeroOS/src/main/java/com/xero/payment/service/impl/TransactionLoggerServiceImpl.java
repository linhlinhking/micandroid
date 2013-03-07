package com.xero.payment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.xero.core.common.service.impl.BaseServiceImpl;
import com.xero.payment.bean.TransactionLogger;
import com.xero.payment.dao.TransactionLoggerDao;
import com.xero.payment.service.TransactionLoggerService;

@Service("transactionLoggerService")
public class TransactionLoggerServiceImpl extends
		BaseServiceImpl<TransactionLogger, Integer> implements
		TransactionLoggerService {

	@Autowired
	public TransactionLoggerServiceImpl(
			@Qualifier("transactionLoggerDao") TransactionLoggerDao transactionLoggerDao) {
		super(transactionLoggerDao);
	}
}
