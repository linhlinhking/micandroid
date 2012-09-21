package com.cisco.pmonitor.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cisco.pmonitor.dao.IPowercyclerEquipmentDao;
import com.cisco.pmonitor.dao.dataobject.PowercyclerEquipmentDo;
import com.cisco.pmonitor.dao.expection.DaoException;
import com.cisco.pmonitor.dao.query.PowercyclerEquipmentQuery;
import com.ibatis.sqlmap.client.SqlMapClient;

public class PowercyclerEquipmentDaoImpl implements IPowercyclerEquipmentDao {

	private SqlMapClient sqlMapClient;
	@Override
	public int insert(PowercyclerEquipmentDo powercyclerEquipmentDo) throws DaoException {
		int row = 0;
		if(null == powercyclerEquipmentDo) {
			return row;
		}
		try {
			row = (Integer) sqlMapClient.insert("PowercyclerEquipmentDaoImpl.insert", powercyclerEquipmentDo);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return row;
	}

	@Override
	public int update(PowercyclerEquipmentDo powercyclerEquipmentDo) throws DaoException {
		int row = 0;
		if(null == powercyclerEquipmentDo) {
			return row;
		}
		try {
			row = sqlMapClient.update("PowercyclerEquipmentDaoImpl.update", powercyclerEquipmentDo);
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
			row = sqlMapClient.delete("PowercyclerEquipmentDaoImpl.delete", id);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return row;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PowercyclerEquipmentDo> loadAllPowercyclerEquipments() throws DaoException {
		try {
			return sqlMapClient.queryForList("PowercyclerEquipmentDaoImpl.loadAllPowercyclerEquipments");
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public PowercyclerEquipmentDo findPowercyclerEquipmentById(int id) throws DaoException {
		if(0 == id) {
			return null;
		}
		try {
			return (PowercyclerEquipmentDo) sqlMapClient.queryForObject("PowercyclerEquipmentDaoImpl.findPowercyclerEquipmentById", id);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PowercyclerEquipmentQuery> loadPowercyclerEquipmentsbyQuery(PowercyclerEquipmentQuery query)
			throws DaoException {
		if(null == query) {
			return null;
		}
		try {
			return sqlMapClient.queryForList("PowercyclerEquipmentDaoImpl.loadPowercyclerEquipmentsbyQuery", query);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int loadTotalNumByQuery(PowercyclerEquipmentQuery query) throws DaoException {
		if(null == query) {
			return 0;
		}
		try {
			return (Integer) sqlMapClient.queryForObject("PowercyclerEquipmentDaoImpl.loadTotalNumByQuery", query);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@Override
	public PowercyclerEquipmentDo findPowercyclerAndOutlet(int powercyclerId,
			int outlet) throws DaoException {
		if(0 == powercyclerId) {
			return null;
		}
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("powercyclerId", powercyclerId);
		map.put("outlet", outlet);
		try {
			return (PowercyclerEquipmentDo) sqlMapClient.queryForObject("PowercyclerEquipmentDaoImpl.findPowercyclerAndOutlet", map);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

}
