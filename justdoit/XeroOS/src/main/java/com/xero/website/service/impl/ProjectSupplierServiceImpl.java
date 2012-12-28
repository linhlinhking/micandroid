package com.xero.website.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.xero.core.Response.ResponseCollection;
import com.xero.core.common.service.impl.BaseServiceImpl;
import com.xero.core.exception.DaoException;
import com.xero.core.exception.ServiceException;
import com.xero.website.bean.ProjectSupplier;
import com.xero.website.dao.ProjectSupplierDao;
import com.xero.website.service.ProjectSupplierService;

@Service("projectSupplierService")
public class ProjectSupplierServiceImpl extends
		BaseServiceImpl<ProjectSupplier, Integer> implements
		ProjectSupplierService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private ProjectSupplierDao projectSupplierDao;

	@Autowired
	public ProjectSupplierServiceImpl(
			@Qualifier("projectSupplierDao") ProjectSupplierDao projectSupplierDao) {
		super(projectSupplierDao);
	}

	public ResponseCollection<ProjectSupplier> getSuppliersByProjectId(
			Integer projectId) throws ServiceException {
		ResponseCollection<ProjectSupplier> res = new ResponseCollection<ProjectSupplier>();
		try {
			List<ProjectSupplier> lists = projectSupplierDao
					.getSuppliersByProjectId(projectId);

			res.setData(lists);
			res.setResult(true);
		} catch (DaoException ex) {
			logger.error("Get Suppliers Error.", ex);
			res.setResult(false);
			res.setData(null);
		}
		return res;
	}

}
