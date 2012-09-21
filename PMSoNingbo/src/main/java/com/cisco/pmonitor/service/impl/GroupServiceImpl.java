package com.cisco.pmonitor.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.cisco.pmonitor.dao.IGroupDao;
import com.cisco.pmonitor.dao.dataobject.GroupDo;
import com.cisco.pmonitor.dao.expection.DaoException;
import com.cisco.pmonitor.dao.query.GroupQuery;
import com.cisco.pmonitor.service.IGroupService;
import com.cisco.pmonitor.service.exception.ServiceException;
import com.cisco.pmonitor.service.util.Result;

public class GroupServiceImpl implements IGroupService {

	private IGroupDao groupDao;
	@Override
	public Result<Integer> addGroup(GroupDo groupDo) throws ServiceException {
		Result<Integer> rs = new Result<Integer>();
		rs.setSuccess(false);
		if(null == groupDo) {
			return rs;
		}
		try {
			int id = groupDao.insert(groupDo);
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
	public Result<Integer> updateGroup(GroupDo groupDo) throws ServiceException {
		Result<Integer> rs = new Result<Integer>();
		rs.setSuccess(false);
		if(null == groupDo) {
			return rs;
		}
		try {
			int row = groupDao.update(groupDo);
			if(row == 1) {
				rs.setSuccess(true);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}

	@Override
	public Result<Integer> deleteGroup(int id) throws ServiceException {
		Result<Integer> rs = new Result<Integer>();
		rs.setSuccess(false);
		if(0 == id) {
			return rs;
		}
		try {
			int row = groupDao.delete(id);
			if(row == 1) {
				rs.setSuccess(true);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}

	@Override
	public Result<GroupDo> findGroupById(int id) throws ServiceException {
		Result<GroupDo> rs = new Result<GroupDo>();
		rs.setSuccess(false);
		if(0 == id) {
			return rs;
		}
		try {
			GroupDo groupDo = groupDao.findGroupById(id);
			if(null != groupDo) {
				rs.setSuccess(true);
				rs.setDefaultModel(groupDo);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}

	@Override
	public Result<GroupDo> findGroupByName(String name) throws ServiceException {
		Result<GroupDo> rs = new Result<GroupDo>();
		rs.setSuccess(false);
		if(StringUtils.isEmpty(name)) {
			return rs;
		}
		try {
			GroupDo groupDo = groupDao.findGroupByName(name);
			if(null != groupDo) {
				rs.setSuccess(true);
				rs.setDefaultModel(groupDo);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}

	@Override
	public Result<List<GroupDo>> loadAllGroups() throws ServiceException {
		Result<List<GroupDo>> rs = new Result<List<GroupDo>>();
		rs.setSuccess(false);
		try {
			List<GroupDo> list = groupDao.loadAllGroups();
			if(null != list) {
				rs.setSuccess(true);
				rs.setDefaultModel(list);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}

	public void setGroupDao(IGroupDao groupDao) {
		this.groupDao = groupDao;
	}

	@Override
	public Result<Map<String, Object>> loadGroupsByQuery(GroupQuery query)
			throws ServiceException {
		Result<Map<String, Object>> rs = new Result<Map<String, Object>>();
		if(null == query) {
			return rs;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<GroupQuery> list = groupDao.loadGroupsbyQuery(query);
			int total = groupDao.loadTotalNumByQuery(query);
			map.put("total", total);
			map.put("rows", list == null ? new ArrayList<GroupDo>() : list);
			rs.setSuccess(true);
			rs.setDefaultModel(map);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}

	@Override
	public Result<List<GroupDo>> loadGroupsByRoomId(int roomId)
			throws ServiceException {
		Result<List<GroupDo>> rs = new Result<List<GroupDo>>();
		rs.setSuccess(false);
		if(0 == roomId) {
			return rs;
		}
		try {
			List<GroupDo> list = groupDao.loadGroupsByRoomId(roomId);
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
