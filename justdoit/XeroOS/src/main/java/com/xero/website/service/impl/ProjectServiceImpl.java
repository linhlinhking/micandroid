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
import com.xero.website.bean.Project;
import com.xero.website.dao.ProjectDao;
import com.xero.website.service.ProjectService;

@Service("projectService")
public class ProjectServiceImpl extends BaseServiceImpl<Project, Integer>
		implements ProjectService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private ProjectDao projectDao;

	@Autowired
	public ProjectServiceImpl(@Qualifier("projectDao") ProjectDao projectDao) {
		super(projectDao);
	}

	public ResponseCollection<Project> getProjectsById(Integer userId)
			throws ServiceException {
		ResponseCollection<Project> res = new ResponseCollection<Project>();
		try {
			List<Project> lists = projectDao.getProjectsById(userId);
			res.setData(lists);
			res.setResult(true);
			res.setMessage("OK");

		} catch (DaoException ex) {
			logger.error("Get All Project Error On Service", ex);
			res.setData(null);
			res.setResult(false);
			res.setMessage("ERROR");

		}
		return res;
	}

	public ResponseCollection<Project> getActiveProjects()
			throws ServiceException {
		ResponseCollection<Project> res = new ResponseCollection<Project>();
		try {
			List<Project> lists = projectDao.getActiveProjects() ;
			res.setData(lists);
			res.setResult(true);
			res.setMessage("OK");

		} catch (DaoException ex) {
			logger.error("Get All Project Error On Service", ex);
			res.setData(null);
			res.setResult(false);
			res.setMessage("ERROR");

		}
		return res;
	}
}
