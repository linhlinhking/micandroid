package com.cisco.pmonitor.dao.impl;

import java.sql.SQLException;

import com.cisco.pmonitor.dao.IUseRatioDao;
import com.cisco.pmonitor.dao.dataobject.UseRatioDo;
import com.cisco.pmonitor.dao.expection.DaoException;
import com.ibatis.sqlmap.client.SqlMapClient;

public class UseRatioDaoImpl implements IUseRatioDao {

	private SqlMapClient sqlMapClient;
	
	@Override
	public int insert(UseRatioDo userRatioDo) throws DaoException {
		int row = 0;
		if(null == userRatioDo) {
			return row;
		}
		try {
			row = (Integer) sqlMapClient.insert("UseRatioDaoImpl.insert", userRatioDo);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return row;
	}

	@Override
	public int update(UseRatioDo userRatioDo) throws DaoException {
		int row = 0;
		if(null == userRatioDo) {
			return row;
		}
		try {
			row = sqlMapClient.update("UseRatioDaoImpl.update", userRatioDo);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return row;
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

}
