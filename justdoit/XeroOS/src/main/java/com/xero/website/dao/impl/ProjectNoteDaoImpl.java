package com.xero.website.dao.impl;

import org.springframework.stereotype.Repository;

import com.xero.core.common.dao.impl.BaseDaoImpl;
import com.xero.website.bean.ProjectNote;
import com.xero.website.dao.ProjectNoteDao;

@Repository("projectNoteDao")
public class ProjectNoteDaoImpl extends BaseDaoImpl<ProjectNote, Integer> implements ProjectNoteDao{

	public ProjectNoteDaoImpl(){
		super(ProjectNote.class);
	}
}
