package com.xero.website.dao.impl;

import org.springframework.stereotype.Repository;

import com.xero.core.common.dao.impl.BaseDaoImpl;
import com.xero.website.bean.CompanyUser;
import com.xero.website.dao.CompanyUserDao;

@Repository("companyUserDao")
public class CompanyUserDaoImpl extends BaseDaoImpl<CompanyUser, Integer>
		implements CompanyUserDao {

	public CompanyUserDaoImpl() {
		super(CompanyUser.class);
	}
}
