package com.cisco.pmonitor.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.cisco.pmonitor.dao.IUserGroupDao;
import com.cisco.pmonitor.dao.dataobject.UserGroupDo;
import com.cisco.pmonitor.dao.expection.DaoException;
import com.cisco.pmonitor.service.IUserGroupService;
import com.cisco.pmonitor.service.exception.ServiceException;
import com.cisco.pmonitor.service.util.Result;

public class UserGroupServiceImpl implements IUserGroupService {

	private IUserGroupDao userGroupDao;
	public void setUserGroupDao(IUserGroupDao userGroupDao) {
		this.userGroupDao = userGroupDao;
	}
	@Override
	public Result<Integer> addUserGroup(UserGroupDo userGroupDo)
			throws ServiceException {
		Result<Integer> rs = new Result<Integer>();
		rs.setSuccess(false);
		if(null == userGroupDao) {
			return rs;
		}
		try {
			int id = userGroupDao.insert(userGroupDo);
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
	public Result<Integer> updateUserGroup(UserGroupDo userGroupDo)
			throws ServiceException {
		Result<Integer> rs = new Result<Integer>();
		rs.setSuccess(false);
		if(null == userGroupDo) {
			return rs;
		}
		try {
			int row = userGroupDao.update(userGroupDo);
			if(row == 1) {
				rs.setSuccess(true);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}
	@Override
	public Result<Integer> deleteUserGroup(int id) throws ServiceException {
		Result<Integer> rs = new Result<Integer>();
		rs.setSuccess(false);
		if(0 == id) {
			return rs;
		}
		try {
			int row = userGroupDao.delete(id);
			if(row == 1) {
				rs.setSuccess(true);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}
	@Override
	public Result<UserGroupDo> findUserGroupById(int id)
			throws ServiceException {
		Result<UserGroupDo> rs = new Result<UserGroupDo>();
		rs.setSuccess(false);
		if(0 == id) {
			return rs;
		}
		try {
			UserGroupDo userGroupDo = userGroupDao.findUserGroupById(id);
			if(null != userGroupDao) {
				rs.setSuccess(true);
				rs.setDefaultModel(userGroupDo);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}
	@Override
	public Result<List<UserGroupDo>> findUserGroupByUserName(String username)
			throws ServiceException {
		Result<List<UserGroupDo>> rs = new Result<List<UserGroupDo>>();
		rs.setSuccess(false);
		if(StringUtils.isEmpty(username)) {
			return rs;
		}
		try {
			List<UserGroupDo> list = userGroupDao.findUserGroupByUserName(username);
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
	public Result<List<UserGroupDo>> loadAllUserGroups()
			throws ServiceException {
		Result<List<UserGroupDo>> rs = new Result<List<UserGroupDo>>();
		rs.setSuccess(false);
		try {
			List<UserGroupDo> list = userGroupDao.loadAllUserGroups();
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
	public Result<UserGroupDo> findUserGroupByUserAndGroup(String username,
			int groupId) throws ServiceException {
		Result<UserGroupDo> rs = new Result<UserGroupDo>();
		rs.setSuccess(false);
		if(StringUtils.isEmpty(username) || 0 == groupId) {
			return rs;
		}
		try {
			UserGroupDo userGroupDo = userGroupDao.findUserGroupByUserAndGroup(username, groupId);
			if(null != userGroupDo) {
				rs.setSuccess(true);
				rs.setDefaultModel(userGroupDo);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}

}
