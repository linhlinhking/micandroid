package com.cisco.pmonitor.dao;

import java.util.List;

import com.cisco.pmonitor.dao.dataobject.RoomDo;
import com.cisco.pmonitor.dao.expection.DaoException;
import com.cisco.pmonitor.dao.query.RoomQuery;

public interface IRoomDao {

	public int insert(RoomDo roomDo) throws DaoException;
	
	public int update(RoomDo roomDo) throws DaoException;
	
	public int delete(int id) throws DaoException;
	
	public List<RoomDo> loadAllRooms() throws DaoException;
	
	public List<RoomDo> loadRoomsByDepartmentId(int departmentId) throws DaoException;
	
	public RoomDo findRoomById(int id) throws DaoException;
	
	public RoomDo findRoomByName(String name) throws DaoException;
	
	public List<RoomQuery> loadRoomsbyQuery(RoomQuery query)throws DaoException;
	
	public int loadTotalNumByQuery(RoomQuery query) throws DaoException;
}
