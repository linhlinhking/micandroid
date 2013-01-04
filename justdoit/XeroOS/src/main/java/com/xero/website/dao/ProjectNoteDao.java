package com.xero.website.dao;

import java.util.List;

import com.xero.core.common.dao.BaseDao;
import com.xero.core.exception.DaoException;
import com.xero.website.bean.ProjectNote;

public interface ProjectNoteDao extends BaseDao<ProjectNote, Integer> {

	public List<ProjectNote> getNotesByProjectId(Integer projectId) throws DaoException;
	
	//public boolean isSendNoteBySupplier() throws DaoException ;
}
