package com.xero.website.dao.impl;

import org.springframework.stereotype.Repository;

import com.xero.core.common.dao.impl.BaseDaoImpl;
import com.xero.website.bean.Company;
import com.xero.website.dao.CompanyDao;

@Repository("companyDao")
public class CompanyDaoImpl extends BaseDaoImpl<Company, Integer> implements
		CompanyDao {

	public CompanyDaoImpl(){
		super(Company.class);
	}
}
