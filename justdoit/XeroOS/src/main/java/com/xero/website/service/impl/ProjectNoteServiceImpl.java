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
import com.xero.core.exception.ServiceException;
import com.xero.website.bean.ProjectNote;
import com.xero.website.dao.ProjectNoteDao;
import com.xero.website.service.ProjectNoteService;

@Service("projectNoteService")
public class ProjectNoteServiceImpl extends
		BaseServiceImpl<ProjectNote, Integer> implements ProjectNoteService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private ProjectNoteDao projectNoteDao;

	@Autowired
	public ProjectNoteServiceImpl(
			@Qualifier("projectNoteDao") ProjectNoteDao projectNoteDao) {
		super(projectNoteDao);
	}

	public ResponseCollection<ProjectNote> getNotesByProjectId(Integer projectId)
			throws ServiceException {
		ResponseCollection<ProjectNote> res = new ResponseCollection<ProjectNote>();
		try {
			List<ProjectNote> lists = projectNoteDao
					.getNotesByProjectId(projectId);

			res.setData(lists);
			res.setResult(true);

		} catch (ServiceException se) {
			logger.error("Error On Service.Project'id is " + projectId, se);
			res.setResult(false);
			res.setData(null);
		}
		return res;
	}
}
