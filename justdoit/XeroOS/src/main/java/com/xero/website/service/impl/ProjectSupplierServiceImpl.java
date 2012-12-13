package com.xero.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.xero.core.common.service.impl.BaseServiceImpl;
import com.xero.website.bean.ProjectSupplier;
import com.xero.website.dao.ProjectSupplierDao;
import com.xero.website.service.ProjectSupplierService;

@Service("projectSupplierService")
public class ProjectSupplierServiceImpl extends
		BaseServiceImpl<ProjectSupplier, Integer> implements
		ProjectSupplierService {

	@Autowired
	public ProjectSupplierServiceImpl(
			@Qualifier("projectSupplierDao") ProjectSupplierDao projectSupplierDao) {
		super(projectSupplierDao);
	}

}
