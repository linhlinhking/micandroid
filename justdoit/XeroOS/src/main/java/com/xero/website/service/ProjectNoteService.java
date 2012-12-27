package com.xero.website.service;

import com.xero.core.Response.ResponseCollection;
import com.xero.core.common.service.BaseService;
import com.xero.core.exception.ServiceException;
import com.xero.website.bean.ProjectNote;

public interface ProjectNoteService extends BaseService<ProjectNote, Integer> {

	public ResponseCollection<ProjectNote> getNotesByProjectId(Integer projectId)
			throws ServiceException;
}
