package com.xero.website.dao;

import java.util.List;

import com.xero.core.common.dao.BaseDao;
import com.xero.core.exception.DaoException;
import com.xero.website.bean.ProjectSupplier;

public interface ProjectSupplierDao extends BaseDao<ProjectSupplier, Integer> {

	public List<ProjectSupplier> getSuppliersByProjectId(Integer projectId)
			throws DaoException;
}
