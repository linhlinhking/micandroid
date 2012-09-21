package com.cisco.pmonitor.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.cisco.pmonitor.dao.IRoomDao;
import com.cisco.pmonitor.dao.dataobject.RoomDo;
import com.cisco.pmonitor.dao.expection.DaoException;
import com.cisco.pmonitor.dao.query.RoomQuery;
import com.ibatis.sqlmap.client.SqlMapClient;

public class RoomDaoImpl implements IRoomDao {

	private SqlMapClient sqlMapClient;
	@Override
	public int insert(RoomDo roomDo) throws DaoException {
		int row = 0;
		if(null == roomDo) {
			return row;
		}
		try {
			row = (Integer) sqlMapClient.insert("RoomDaoImpl.insert", roomDo);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return row;
	}

	@Override
	public int update(RoomDo roomDo) throws DaoException {
		int row = 0;
		if(null == roomDo) {
			return row;
		}
		try {
			row = sqlMapClient.update("RoomDaoImpl.update", roomDo);
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
			row = sqlMapClient.delete("RoomDaoImpl.delete", id);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return row;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoomDo> loadAllRooms() throws DaoException {
		try {
			return sqlMapClient.queryForList("RoomDaoImpl.loadAllRooms");
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public RoomDo findRoomById(int id) throws DaoException {
		if(0 == id) {
			return null;
		}
		try {
			return (RoomDo) sqlMapClient.queryForObject("RoomDaoImpl.findRoomById", id);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public RoomDo findRoomByName(String name) throws DaoException {
		if(StringUtils.isEmpty(name)) {
			return null;
		}
		try {
			return (RoomDo) sqlMapClient.queryForObject("RoomDaoImpl.findRoomByName", name);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoomQuery> loadRoomsbyQuery(RoomQuery query)
			throws DaoException {
		if(null == query) {
			return null;
		}
		try {
			return sqlMapClient.queryForList("RoomDaoImpl.loadRoomsbyQuery", query);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int loadTotalNumByQuery(RoomQuery query) throws DaoException {
		if(null == query) {
			return 0;
		}
		try {
			return (Integer) sqlMapClient.queryForObject("RoomDaoImpl.loadTotalNumByQuery", query);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoomDo> loadRoomsByDepartmentId(int departmentId)
			throws DaoException {
		if(0 == departmentId) {
			return null;
		}
		try {
			return sqlMapClient.queryForList("RoomDaoImpl.loadRoomsByDepartmentId", departmentId);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

}
