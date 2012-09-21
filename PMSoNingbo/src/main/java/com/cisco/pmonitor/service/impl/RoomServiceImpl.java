package com.cisco.pmonitor.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.cisco.pmonitor.dao.IRoomDao;
import com.cisco.pmonitor.dao.dataobject.RoomDo;
import com.cisco.pmonitor.dao.expection.DaoException;
import com.cisco.pmonitor.dao.query.RoomQuery;
import com.cisco.pmonitor.service.IRoomService;
import com.cisco.pmonitor.service.exception.ServiceException;
import com.cisco.pmonitor.service.util.Result;

public class RoomServiceImpl implements IRoomService {

	private IRoomDao roomDao;
	@Override
	public Result<Integer> addRoom(RoomDo roomDo) throws ServiceException {
		Result<Integer> rs = new Result<Integer>();
		rs.setSuccess(false);
		if(null == roomDo) {
			return rs;
		}
		try {
			int id = roomDao.insert(roomDo);
			if(id > 0) {
				rs.setSuccess(true);
				rs.setDefaultModel(id);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}

	@Override
	public Result<Integer> updateRoom(RoomDo roomDo) throws ServiceException {
		Result<Integer> rs = new Result<Integer>();
		rs.setSuccess(false);
		if(null == roomDo) {
			return rs;
		}
		try {
			int row = roomDao.update(roomDo);
			if(row == 1) {
				rs.setSuccess(true);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}

	@Override
	public Result<Integer> deleteRoom(int id) throws ServiceException {
		Result<Integer> rs = new Result<Integer>();
		rs.setSuccess(false);
		if(0 == id) {
			return rs;
		}
		try {
			int row = roomDao.delete(id);
			if(row == 1) {
				rs.setSuccess(true);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}

	@Override
	public Result<RoomDo> findRoomById(int id) throws ServiceException {
		Result<RoomDo> rs = new Result<RoomDo>();
		rs.setSuccess(false);
		if(0 == id) {
			return rs;
		}
		try {
			RoomDo roomDo = roomDao.findRoomById(id);
			if(null != roomDo) {
				rs.setSuccess(true);
				rs.setDefaultModel(roomDo);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}

	@Override
	public Result<RoomDo> findRoomByName(String name) throws ServiceException {
		Result<RoomDo> rs = new Result<RoomDo>();
		rs.setSuccess(false);
		if(StringUtils.isEmpty(name)) {
			return rs;
		}
		try {
			RoomDo roomDo = roomDao.findRoomByName(name);
			if(null != roomDo) {
				rs.setSuccess(true);
				rs.setDefaultModel(roomDo);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}

	public void setRoomDao(IRoomDao roomDao) {
		this.roomDao = roomDao;
	}

	@Override
	public Result<Map<String, Object>> loadRoomsByQuery(RoomQuery query)
			throws ServiceException {
		Result<Map<String, Object>> rs = new Result<Map<String, Object>>();
		rs.setSuccess(false);
		if(null == query) {
			return rs;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<RoomQuery> list = roomDao.loadRoomsbyQuery(query);
			int total = roomDao.loadTotalNumByQuery(query);
			map.put("total", total);
			map.put("rows", list == null ? new ArrayList<RoomDo>() : list);
			rs.setSuccess(true);
			rs.setDefaultModel(map);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}

	@Override
	public Result<List<RoomDo>> loadAllRooms() throws ServiceException {
		Result<List<RoomDo>> rs = new Result<List<RoomDo>>();
		rs.setSuccess(false);
		try {
			List<RoomDo> list = roomDao.loadAllRooms();
			if(null != list) {
				rs.setSuccess(true);
				rs.setDefaultModel(list);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}

	@Override
	public Result<List<RoomDo>> loadRoomsByDepartmentId(int departmentId)
			throws ServiceException {
		Result<List<RoomDo>> rs = new Result<List<RoomDo>>();
		rs.setSuccess(false);
		if(0 == departmentId) {
			return rs;
		}
		try {
			List<RoomDo> list = roomDao.loadRoomsByDepartmentId(departmentId);
			if(null != list) {
				rs.setSuccess(true);
				rs.setDefaultModel(list);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}

}
