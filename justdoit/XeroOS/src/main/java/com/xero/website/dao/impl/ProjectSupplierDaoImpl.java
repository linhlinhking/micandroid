package com.xero.website.dao.impl;

import org.springframework.stereotype.Repository;

import com.xero.core.common.dao.impl.BaseDaoImpl;
import com.xero.website.bean.ProjectSupplier;
import com.xero.website.dao.ProjectSupplierDao;


@Repository("projectSupplierDao")
public class ProjectSupplierDaoImpl extends
		BaseDaoImpl<ProjectSupplier, Integer> implements ProjectSupplierDao {

	public ProjectSupplierDaoImpl() {
		super(ProjectSupplier.class);
	}
}
