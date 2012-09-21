package com.cisco.pmonitor.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.cisco.pmonitor.dao.IGroupDao;
import com.cisco.pmonitor.dao.dataobject.GroupDo;
import com.cisco.pmonitor.dao.expection.DaoException;
import com.cisco.pmonitor.dao.query.GroupQuery;
import com.ibatis.sqlmap.client.SqlMapClient;

public class GroupDaoImpl implements IGroupDao {

	private SqlMapClient sqlMapClient;
	@Override
	public int insert(GroupDo groupDo) throws DaoException {
		int row = 0;
		if(null == groupDo) {
			return row;
		}
		try {
			row = (Integer) sqlMapClient.insert("GroupDaoImpl.insert", groupDo);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return row;
	}

	@Override
	public int update(GroupDo groupDo) throws DaoException {
		int row = 0;
		if(null == groupDo) {
			return row;
		}
		try {
			row = sqlMapClient.update("GroupDaoImpl.update", groupDo);
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
			row = sqlMapClient.delete("GroupDaoImpl.delete", id);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return row;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GroupDo> loadAllGroups() throws DaoException {
		try {
			return sqlMapClient.queryForList("GroupDaoImpl.loadAllGroups");
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public GroupDo findGroupById(int id) throws DaoException {
		if(0 == id) {
			return null;
		}
		try {
			return (GroupDo) sqlMapClient.queryForObject("GroupDaoImpl.findGroupById", id);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public GroupDo findGroupByName(String name) throws DaoException {
		if(StringUtils.isEmpty(name)) {
			return null;
		}
		try {
			return (GroupDo) sqlMapClient.queryForObject("GroupDaoImpl.findGroupByName", name);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GroupQuery> loadGroupsbyQuery(GroupQuery query)
			throws DaoException {
		if(null == query) {
			return null;
		}
		try {
			return sqlMapClient.queryForList("GroupDaoImpl.loadGroupsbyQuery", query);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int loadTotalNumByQuery(GroupQuery query) throws DaoException {
		if(null == query) {
			return 0;
		}
		try {
			return (Integer) sqlMapClient.queryForObject("GroupDaoImpl.loadTotalNumByQuery", query);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GroupDo> loadGroupsByRoomId(int roomId) throws DaoException {
		if(0 == roomId) {
			return null;
		}
		try {
			return sqlMapClient.queryForList("RoomDaoImpl.loadGroupsByRoomId", roomId);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

}
