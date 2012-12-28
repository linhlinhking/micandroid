package com.xero.website.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.xero.core.common.dao.impl.BaseDaoImpl;
import com.xero.core.exception.DaoException;
import com.xero.website.bean.ProjectSupplier;
import com.xero.website.dao.ProjectSupplierDao;

@Repository("projectSupplierDao")
public class ProjectSupplierDaoImpl extends
		BaseDaoImpl<ProjectSupplier, Integer> implements ProjectSupplierDao {

	private Logger logger = LoggerFactory.getLogger(getClass());

	public ProjectSupplierDaoImpl() {
		super(ProjectSupplier.class);
	}

	public List<ProjectSupplier> getSuppliersByProjectId(Integer projectId)
			throws DaoException {
		List<ProjectSupplier> lists = null;
		try {
			String hql = "from ProjectSupplier as p where 1=1 and p.projectId = ? and p.deleted = 0 ";
			lists = findByHql(hql, projectId);
		} catch (Exception ex) {
			logger.error("Get Project's Suppliers Error,Project's ID is "
					+ projectId, ex);
		}
		return lists;
	}
}
