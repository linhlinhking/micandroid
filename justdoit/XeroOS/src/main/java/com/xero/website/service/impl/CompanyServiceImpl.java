package com.xero.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.xero.core.common.service.impl.BaseServiceImpl;
import com.xero.website.bean.Company;
import com.xero.website.dao.CompanyDao;
import com.xero.website.service.CompanyService;

@Service("companyService")
public class CompanyServiceImpl extends BaseServiceImpl<Company, Integer>
		implements CompanyService {

	@Autowired
	public CompanyServiceImpl(@Qualifier("companyDao")CompanyDao companyDao){
		super(companyDao);
	}
}
