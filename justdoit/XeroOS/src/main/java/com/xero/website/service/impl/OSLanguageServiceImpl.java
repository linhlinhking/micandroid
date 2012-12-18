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
import com.xero.core.exception.DaoException;
import com.xero.core.exception.ServiceException;
import com.xero.website.bean.OSLanguage;
import com.xero.website.dao.OSLanguageDao;
import com.xero.website.service.OSLanguageService;

@Service("oSLanguageService")
public class OSLanguageServiceImpl extends BaseServiceImpl<OSLanguage, Integer>
		implements OSLanguageService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private OSLanguageDao oSLanguageDao;

	@Autowired
	public OSLanguageServiceImpl(
			@Qualifier("oSLanguageDao") OSLanguageDao oSLanguageDao) {
		super(oSLanguageDao);
	}

	public ResponseCollection<OSLanguage> getAllLanguage()
			throws ServiceException {
		ResponseCollection<OSLanguage> res = new ResponseCollection<OSLanguage>(
				false);
		try {
			List<OSLanguage> lists = oSLanguageDao.getAll();
			
			res.setData(lists);
			res.setResult(true);
		} catch (DaoException ex) {
			logger.error("Get All Language Error.", ex);
		}
		return res;
	}
}
