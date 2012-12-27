package com.xero.website.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.xero.core.common.dao.impl.BaseDaoImpl;
import com.xero.core.exception.DaoException;
import com.xero.website.bean.ProjectNote;
import com.xero.website.dao.ProjectNoteDao;

@Repository("projectNoteDao")
public class ProjectNoteDaoImpl extends BaseDaoImpl<ProjectNote, Integer>
		implements ProjectNoteDao {

	private Logger logger = LoggerFactory.getLogger(getClass());

	public ProjectNoteDaoImpl() {
		super(ProjectNote.class);
	}

	public List<ProjectNote> getNotesByProjectId(Integer projectId)
			throws DaoException {
		List<ProjectNote> lists = null;
		try {
			String hql = "from ProjectNote as p where 1=1 and p.projectId = ? order by p.landmarkDate asc,p.id desc ";
			lists = findByHql(hql, projectId);
		} catch (DaoException x) {
			logger.error("Get List Notes by projectId Error.Project'id is "
					+ projectId, x);
		}
		return lists;
	}

}
