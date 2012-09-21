package com.cisco.pmonitor.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.cisco.pmonitor.dao.IUserDao;
import com.cisco.pmonitor.dao.dataobject.UserDo;
import com.cisco.pmonitor.dao.expection.DaoException;
import com.cisco.pmonitor.dao.query.UserQuery;
import com.ibatis.sqlmap.client.SqlMapClient;

public class UserDaoImpl implements IUserDao {

	private SqlMapClient sqlMapClient;
	
	@Override
	public int insert(UserDo user) throws DaoException{
		int row = 0;
		if(null == user) {
			return row;
		}
		try {
			row = (Integer) sqlMapClient.insert("UserDaoImpl.insert", user);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return row;
	}

	@Override
	public int update(UserDo user) throws DaoException{
		int row = 0;
		if(null == user) {
			return row;
		}
		try {
			row = sqlMapClient.update("UserDaoImpl.update", user);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return row;
	}

	@Override
	public UserDo findUserById(int id) throws DaoException{
		if(0 == id) {
			return null;
		}
		try {
			return (UserDo) sqlMapClient.queryForObject("UserDaoImpl.findUserById", id);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public UserDo findUserByUsername(String username) throws DaoException{
		if(StringUtils.isEmpty(username)) {
			return null;
		}
		try {
			return (UserDo) sqlMapClient.queryForObject("UserDaoImpl.findUserByUsername", username);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserDo> loadAllUsers() throws DaoException{
		try {
			return sqlMapClient.queryForList("UserDaoImpl.loadAllUsers");
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int delete(int id) throws DaoException{
		int row = 0;
		if(0 == id) {
			return 0;
		}
		try {
			row = sqlMapClient.delete("UserDaoImpl.delete", id);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return row;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserQuery> loadUsersByQuery(UserQuery query) throws DaoException {
		if(null == query) {
			return null;
		}
		try {
			return sqlMapClient.queryForList("UserDaoImpl.loadUsersByQuery", query);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int loadTotalNumByQuery(UserQuery query) throws DaoException {
		if(null == query) {
			return 0;
		}
		try {
			return (Integer) sqlMapClient.queryForObject("UserDaoImpl.loadTotalNumByQuery", query);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

}
