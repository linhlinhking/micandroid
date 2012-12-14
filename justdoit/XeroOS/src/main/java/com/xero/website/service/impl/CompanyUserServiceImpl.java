package com.xero.website.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.xero.core.common.service.impl.BaseServiceImpl;
import com.xero.website.bean.CompanyUser;
import com.xero.website.dao.CompanyUserDao;
import com.xero.website.service.CompanyUserService;

@Service("companyUserService")
public class CompanyUserServiceImpl extends
		BaseServiceImpl<CompanyUser, Integer> implements CompanyUserService {

	@Autowired
	public CompanyUserServiceImpl(
			@Qualifier("companyUserDao") CompanyUserDao companyUserDao) {
		super(companyUserDao);
	}


}
