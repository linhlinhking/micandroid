package com.cisco.pmonitor.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cisco.pmonitor.dao.IReserveDao;
import com.cisco.pmonitor.dao.dataobject.ReserveDo;
import com.cisco.pmonitor.dao.expection.DaoException;
import com.cisco.pmonitor.dao.query.ReserveQuery;
import com.ibatis.sqlmap.client.SqlMapClient;

public class ReserveDaoImpl implements IReserveDao {

	private SqlMapClient sqlMapClient;
	@Override
	public int insert(ReserveDo reserveDo) throws DaoException {
		int row = 0;
		if(null == reserveDo) {
			return row;
		}
		try {
			row = (Integer) sqlMapClient.insert("ReserveDaoImpl.insert", reserveDo);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return row;
	}

	@Override
	public int update(ReserveDo reserveDo) throws DaoException {
		int row = 0;
		if(null == reserveDo) {
			return row;
		}
		try {
			row = sqlMapClient.update("ReserveDaoImpl.update", reserveDo);
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
			row = sqlMapClient.delete("ReserveDaoImpl.delete", id);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return row;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReserveQuery> loadAllReserves() throws DaoException {
		try {
			return sqlMapClient.queryForList("ReserveDaoImpl.loadAllReserves");
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public ReserveDo findReserveById(int id) throws DaoException {
		if(0 == id) {
			return null;
		}
		try {
			return (ReserveDo) sqlMapClient.queryForObject("ReserveDaoImpl.findReserveById", id);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@Override
	public ReserveDo findReserveByEquipmentId(int equipmentId, int status)
			throws DaoException {
		if(0 == equipmentId) {
			return null;
		}
		try {
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("equipmentId", equipmentId);
			map.put("status", status);
			return (ReserveDo) sqlMapClient.queryForObject("ReserveDaoImpl.findReserveByEquipmentId", map);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int loadTotalNum() throws DaoException {
		try {
			return (Integer) sqlMapClient.queryForObject("ReserveDaoImpl.loadTotalNum");
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReserveQuery> loadReservesByQuery(ReserveQuery query)
			throws DaoException {
		if(null == query) {
			return null;
		}
		try {
			return sqlMapClient.queryForList("ReserveDaoImpl.loadReservesByQuery", query);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int loadTotalNumByQuery(ReserveQuery query) throws DaoException {
		if(null == query) {
			return 0;
		}
		try {
			return (Integer) sqlMapClient.queryForObject("ReserveDaoImpl.loadTotalNumByQuery", query);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}


}
