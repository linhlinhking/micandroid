package com.xero.website.dao.impl;

import org.springframework.stereotype.Repository;

import com.xero.core.common.dao.impl.BaseDaoImpl;
import com.xero.website.bean.Project;
import com.xero.website.dao.ProjectDao;

@Repository("projectDao")
public class ProjectDaoImpl extends BaseDaoImpl<Project, Integer> implements
		ProjectDao {

	public ProjectDaoImpl() {
		super(Project.class);
	}
}
