package com.cisco.pmonitor.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.cisco.pmonitor.dao.IPowerCyclerDao;
import com.cisco.pmonitor.dao.dataobject.PowerCyclerDo;
import com.cisco.pmonitor.dao.expection.DaoException;
import com.cisco.pmonitor.dao.query.PowerCyclerQuery;
import com.ibatis.sqlmap.client.SqlMapClient;

public class PowerCyclerDaoImpl implements IPowerCyclerDao {
	
	private SqlMapClient sqlMapClient;
	@Override
	public int insert(PowerCyclerDo powerCyclerDo) throws DaoException {
		int row = 0;
		if(null == powerCyclerDo) {
			return row;
		}
		try {
			row = (Integer) sqlMapClient.insert("PowerCyclerDaoImpl.insert", powerCyclerDo);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return row;
	}

	@Override
	public int update(PowerCyclerDo powerCyclerDo) throws DaoException {
		int row = 0;
		if(null == powerCyclerDo) {
			return row;
		}
		try {
			row = sqlMapClient.update("PowerCyclerDaoImpl.update", powerCyclerDo);
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
			row = sqlMapClient.delete("PowerCyclerDaoImpl.delete", id);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return row;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PowerCyclerDo> loadAllPowerCyclers() throws DaoException {
		try {
			return sqlMapClient.queryForList("PowerCyclerDaoImpl.loadAllPowerCyclers");
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public PowerCyclerDo findPowerCyclerById(int id) throws DaoException {
		if(0 == id) {
			return null;
		}
		try {
			return (PowerCyclerDo) sqlMapClient.queryForObject("PowerCyclerDaoImpl.findPowerCyclerById", id);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public PowerCyclerDo findPowerCyclerByName(String name) throws DaoException {
		if(StringUtils.isEmpty(name)) {
			return null;
		}
		try {
			return (PowerCyclerDo) sqlMapClient.queryForObject("PowerCyclerDaoImpl.findPowerCyclerByName", name);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PowerCyclerQuery> loadPowerCyclersbyQuery(PowerCyclerQuery query)
			throws DaoException {
		if(null == query) {
			return null;
		}
		try {
			return sqlMapClient.queryForList("PowerCyclerDaoImpl.loadPowerCyclersbyQuery", query);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int loadTotalNumByQuery(PowerCyclerQuery query) throws DaoException {
		if(null == query) {
			return 0;
		}
		try {
			return (Integer) sqlMapClient.queryForObject("PowerCyclerDaoImpl.loadTotalNumByQuery", query);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

}
