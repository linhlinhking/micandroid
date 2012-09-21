package com.cisco.pmonitor.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.cisco.pmonitor.dao.IUserGroupDao;
import com.cisco.pmonitor.dao.dataobject.UserGroupDo;
import com.cisco.pmonitor.dao.expection.DaoException;
import com.ibatis.sqlmap.client.SqlMapClient;

public class UserGroupDaoImpl implements IUserGroupDao {

	private SqlMapClient sqlMapClient;
	@Override
	public int insert(UserGroupDo userGroupDo) throws DaoException {
		int row = 0;
		if(null == userGroupDo) {
			return row;
		}
		try {
			row = (Integer) sqlMapClient.insert("UserGroupDaoImpl.insert", userGroupDo);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return row;
	}

	@Override
	public int update(UserGroupDo userGroupDo) throws DaoException {
		int row = 0;
		if(null == userGroupDo) {
			return row;
		}
		try {
			row = sqlMapClient.update("UserGroupDaoImpl.update", userGroupDo);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return row;
	}

	@Override
	public int delete(int id) throws DaoException {
		int row = 0;
		if(0 == id) {
			return 0;
		}
		try {
			row = sqlMapClient.delete("UserGroupDaoImpl.delete", id);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return row;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserGroupDo> loadAllUserGroups() throws DaoException {
		try {
			return sqlMapClient.queryForList("UserGroupDaoImpl.loadAllUserGroups");
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public UserGroupDo findUserGroupById(int id) throws DaoException {
		if(0 == id) {
			return null;
		}
		try {
			return (UserGroupDo) sqlMapClient.queryForObject("UserGroupDaoImpl.findUserGroupById", id);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserGroupDo> findUserGroupByUserName(String username) throws DaoException {
		if(StringUtils.isEmpty(username)) {
			return null;
		}
		try {
			return sqlMapClient.queryForList("UserGroupDaoImpl.findUserGroupByUserName", username);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@Override
	public UserGroupDo findUserGroupByUserAndGroup(String username, int groupId)
			throws DaoException {
		if(StringUtils.isEmpty(username) || 0 == groupId) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", username);
		map.put("groupId", groupId);
		try {
			return (UserGroupDo) sqlMapClient.queryForObject("UserGroupDaoImpl.findUserGroupByUserAndGroup", map);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

}
