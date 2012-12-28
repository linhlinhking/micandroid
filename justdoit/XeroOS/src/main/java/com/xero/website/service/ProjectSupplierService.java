package com.xero.website.service;

import com.xero.core.Response.ResponseCollection;
import com.xero.core.common.service.BaseService;
import com.xero.core.exception.ServiceException;
import com.xero.website.bean.ProjectSupplier;

public interface ProjectSupplierService extends
		BaseService<ProjectSupplier, Integer> {

	public ResponseCollection<ProjectSupplier> getSuppliersByProjectId(Integer projectId) throws ServiceException;
}
