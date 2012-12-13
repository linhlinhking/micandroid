package com.xero.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.xero.core.common.service.impl.BaseServiceImpl;
import com.xero.website.bean.Project;
import com.xero.website.dao.ProjectDao;
import com.xero.website.service.ProjectService;

@Service("projectService")
public class ProjectServiceImpl extends BaseServiceImpl<Project, Integer>
		implements ProjectService {

	@Autowired
	public ProjectServiceImpl(@Qualifier("projectDao") ProjectDao projectDao) {
		super(projectDao);
	}
}
