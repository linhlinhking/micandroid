package com.xero.website.service;

import com.xero.core.Response.ResponseCollection;
import com.xero.core.common.service.BaseService;
import com.xero.core.exception.ServiceException;
import com.xero.website.bean.Project;

public interface ProjectService extends BaseService<Project, Integer> {

	public ResponseCollection<Project> getProjectsByIds(Integer userId) throws ServiceException;
	
	public ResponseCollection<Project> getActiveProjects() throws ServiceException;
	
	public ResponseCollection<Project> getActiveProjectsByCompany(Integer companyId) throws ServiceException;
	
	
	
	
}
