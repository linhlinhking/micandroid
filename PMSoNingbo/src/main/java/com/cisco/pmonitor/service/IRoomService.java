package com.cisco.pmonitor.service;

import java.util.List;
import java.util.Map;

import com.cisco.pmonitor.dao.dataobject.RoomDo;
import com.cisco.pmonitor.dao.query.RoomQuery;
import com.cisco.pmonitor.service.exception.ServiceException;
import com.cisco.pmonitor.service.util.Result;

public interface IRoomService {

	public Result<Integer> addRoom(RoomDo roomDo) throws ServiceException;
	
	public Result<Integer> updateRoom(RoomDo roomDo) throws ServiceException;
	
	public Result<Integer> deleteRoom(int id) throws ServiceException;
	
	public Result<RoomDo> findRoomById(int id) throws ServiceException;
	
	public Result<RoomDo> findRoomByName(String name) throws ServiceException;
	
	public Result<List<RoomDo>> loadAllRooms() throws ServiceException;
	
	public Result<List<RoomDo>> loadRoomsByDepartmentId(int departmentId) throws ServiceException;
	
	public Result<Map<String, Object>> loadRoomsByQuery(RoomQuery query) throws ServiceException;
}
