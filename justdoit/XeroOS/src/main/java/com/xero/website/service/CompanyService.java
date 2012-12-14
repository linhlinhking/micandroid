package com.xero.website.service;

import com.xero.core.common.service.BaseService;
import com.xero.core.exception.ServiceException;
import com.xero.website.bean.Company;

public interface CompanyService extends BaseService<Company, Integer> {
	public Company getCompanyByUserId(Integer userId) throws ServiceException;
}
