package com.xero.website.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.xero.core.common.dao.impl.BaseDaoImpl;
import com.xero.core.exception.DaoException;
import com.xero.website.bean.Project;
import com.xero.website.dao.ProjectDao;

@Repository("projectDao")
public class ProjectDaoImpl extends BaseDaoImpl<Project, Integer> implements
		ProjectDao {

	private Logger logger = LoggerFactory.getLogger(getClass());

	
	public ProjectDaoImpl() {
		super(Project.class);
	}

	public List<Project> getProjectsById(Integer userId) throws DaoException {
		List<Project> lists = null;
		try {
			//String hql = "from Project as p where 1=1 and p.deleted = 0 and p.userId = ? ";
			String hql = "from Project as p where 1=1 and p.deleted = 0 and p.companyId = ? ";
			lists = findByHql(hql, userId);
		} catch (Exception ex) {
			logger.error("Get All Project Error.", ex);
		}
		return lists;
	}

	public List<Project> getActiveProjects() throws DaoException {
		List<Project> lists = null;
		try {
			String hql = " select p.* from tb_project as p where 1=1 and p.startDate < NOW() and p.endDate > NOW() and p.status = 'ACTIVE' and p.deleted = 0 ";
			lists = findByNativeSql(hql);
		} catch (Exception ex) {
			logger.error("Get All Project By Date Error.", ex);
		}
		return lists;

	}
}
