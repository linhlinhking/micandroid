package com.cisco.pmonitor.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.cisco.pmonitor.dao.IEquipmentDao;
import com.cisco.pmonitor.dao.dataobject.EquipmentDo;
import com.cisco.pmonitor.dao.expection.DaoException;
import com.cisco.pmonitor.dao.query.EquipmentQuery;
import com.ibatis.sqlmap.client.SqlMapClient;

public class EquipmentDaoImpl implements IEquipmentDao {

	private SqlMapClient sqlMapClient;
	@Override
	public int insert(EquipmentDo equipmentDo) throws DaoException {
		int row = 0;
		if(null == equipmentDo) {
			return row;
		}
		try {
			row = (Integer) sqlMapClient.insert("EquipmentDaoImpl.insert", equipmentDo);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return row;
	}

	@Override
	public int update(EquipmentDo equipmentDo) throws DaoException {
		int row = 0;
		if(null == equipmentDo) {
			return row;
		}
		try {
			row = sqlMapClient.update("EquipmentDaoImpl.update", equipmentDo);
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
			row = sqlMapClient.delete("EquipmentDaoImpl.delete", id);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return row;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EquipmentDo> loadAllEquipments() throws DaoException {
		try {
			return sqlMapClient.queryForList("EquipmentDaoImpl.loadAllEquipments");
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public EquipmentDo findEquipmentById(int id) throws DaoException {
		if(0 == id) {
			return null;
		}
		try {
			return (EquipmentDo) sqlMapClient.queryForObject("EquipmentDaoImpl.findEquipmentById", id);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public EquipmentDo findEquipmentByName(String name) throws DaoException {
		if(StringUtils.isEmpty(name)) {
			return null;
		}
		try {
			return (EquipmentDo) sqlMapClient.queryForObject("EquipmentDaoImpl.findEquipmentByName", name);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EquipmentQuery> loadEquipmentsbyQuery(EquipmentQuery query)
			throws DaoException {
		if(null == query) {
			return null;
		}
		try {
			return sqlMapClient.queryForList("EquipmentDaoImpl.loadEquipmentsbyQuery", query);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int loadTotalNumByQuery(EquipmentQuery query) throws DaoException {
		if(null == query) {
			return 0;
		}
		try {
			return (Integer) sqlMapClient.queryForObject("EquipmentDaoImpl.loadTotalNumByQuery", query);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EquipmentDo> loadEquipmentsByGroupId(int groupId)
			throws DaoException {
		if(0 == groupId) {
			return null;
		}
		try {
			return sqlMapClient.queryForList("EquipmentDaoImpl.loadEquipmentsByGroupId", groupId);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

}
