package com.xero.website.dao;

import java.util.List;

import com.xero.core.common.dao.BaseDao;
import com.xero.core.exception.DaoException;
import com.xero.website.bean.Project;

public interface ProjectDao extends BaseDao<Project, Integer>{

	public List<Project> getProjectsById(Integer userId) throws DaoException;
	
	public List<Project> getActiveProjectsByCompany(Integer companyId,boolean isActive) throws DaoException;
	
}
