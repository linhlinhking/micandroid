package com.xero.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.xero.core.common.service.impl.BaseServiceImpl;
import com.xero.website.bean.ProjectNote;
import com.xero.website.dao.ProjectNoteDao;
import com.xero.website.service.ProjectNoteService;

@Service("projectNoteService")
public class ProjectNoteServiceImpl extends
		BaseServiceImpl<ProjectNote, Integer> implements ProjectNoteService {

	@Autowired
	public ProjectNoteServiceImpl(
			@Qualifier("projectNoteDao") ProjectNoteDao projectNoteDao) {
		super(projectNoteDao);
	}
}
