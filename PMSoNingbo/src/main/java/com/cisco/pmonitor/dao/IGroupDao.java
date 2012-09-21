package com.cisco.pmonitor.dao;

import java.util.List;

import com.cisco.pmonitor.dao.dataobject.GroupDo;
import com.cisco.pmonitor.dao.expection.DaoException;
import com.cisco.pmonitor.dao.query.GroupQuery;

public interface IGroupDao {

	public int insert(GroupDo groupDo) throws DaoException;
	
	public int update(GroupDo groupDo) throws DaoException;
	
	public int delete(int id) throws DaoException;
	
	public List<GroupDo> loadAllGroups() throws DaoException;
	
	public List<GroupDo> loadGroupsByRoomId(int roomId) throws DaoException;
	
	public GroupDo findGroupById(int id) throws DaoException;
	
	public GroupDo findGroupByName(String name) throws DaoException;
	
	public List<GroupQuery> loadGroupsbyQuery(GroupQuery query)
												throws DaoException;
	public int loadTotalNumByQuery(GroupQuery query) throws DaoException;
}
