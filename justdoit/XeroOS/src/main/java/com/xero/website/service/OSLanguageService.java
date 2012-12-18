package com.xero.website.service;

import com.xero.core.Response.ResponseCollection;
import com.xero.core.common.service.BaseService;
import com.xero.core.exception.ServiceException;
import com.xero.website.bean.OSLanguage;

public interface OSLanguageService extends BaseService<OSLanguage, Integer> {

	public ResponseCollection<OSLanguage> getAllLanguage()
			throws ServiceException;
}
