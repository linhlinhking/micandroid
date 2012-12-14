package com.xero.website.dao;

import com.xero.core.common.dao.BaseDao;
import com.xero.core.exception.DaoException;
import com.xero.website.bean.Company;

public interface CompanyDao extends BaseDao<Company, Integer> {

	public Company getCompanyByUserId(Integer userId) throws DaoException;
}
