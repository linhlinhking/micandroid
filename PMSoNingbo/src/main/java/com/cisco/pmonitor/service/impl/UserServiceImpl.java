package com.cisco.pmonitor.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.cisco.pmonitor.dao.IUserDao;
import com.cisco.pmonitor.dao.dataobject.UserDo;
import com.cisco.pmonitor.dao.expection.DaoException;
import com.cisco.pmonitor.dao.query.UserQuery;
import com.cisco.pmonitor.dao.utils.Constants;
import com.cisco.pmonitor.service.IUserService;
import com.cisco.pmonitor.service.exception.ServiceException;
import com.cisco.pmonitor.service.util.Result;

public class UserServiceImpl implements IUserService {

	private IUserDao userDao;
	
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public Result<Integer> addUser(UserDo userDo) throws ServiceException {
		Result<Integer> rs = new Result<Integer>();
		rs.setSuccess(false);
		if(null == userDo) {
			return rs;
		}
		try {
			int id = userDao.insert(userDo);
			if(id > 0) {
				rs.setSuccess(true);
				rs.setDefaultModel(id);
				rs.setMsg("The add user operation is success!");
			}
			else {
				rs.setMsg("The add user operation is error!");
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}

	@Override
	public Result<Integer> updateUser(UserDo userDo) throws ServiceException {
		Result<Integer> rs = new Result<Integer>();
		rs.setSuccess(false);
		if(null == userDo) {
			return rs;
		}
		try {
			int row = userDao.update(userDo);
			if(row == 1) {
				rs.setSuccess(true);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}

	@Override
	public Result<Integer> deleteUser(int id) throws ServiceException {
		Result<Integer> rs = new Result<Integer>();
		rs.setSuccess(false);
		if(0 == id) {
			return rs;
		}
		try {
			int row = userDao.delete(id);
			if(row == 1) {
				rs.setSuccess(true);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}

	@Override
	public Result<UserDo> findUserById(int id) throws ServiceException {
		Result<UserDo> rs = new Result<UserDo>();
		rs.setSuccess(false);
		if(0 == id) {
			return rs;
		}
		try {
			UserDo userDo = userDao.findUserById(id);
			if(null != userDo) {
				rs.setSuccess(true);
				rs.setDefaultModel(userDo);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}

	@Override
	public Result<UserDo> findUserByUsername(String username)
			throws ServiceException {
		Result<UserDo> rs = new Result<UserDo>();
		rs.setSuccess(false);
		if(StringUtils.isEmpty(username)) {
			return rs;
		}
		try {
			UserDo userDo = userDao.findUserByUsername(username);
			if(null != userDo) {
				rs.setSuccess(true);
				rs.setDefaultModel(userDo);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}

	@Override
	public Result<List<UserDo>> loadAllUsers() throws ServiceException {
		Result<List<UserDo>> rs = new Result<List<UserDo>>();
		rs.setSuccess(false);
		try {
			List<UserDo> list = userDao.loadAllUsers();
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
	public Result<Map<String, Object>> loadUsersByQuery(UserQuery query)
			throws ServiceException {
		Result<Map<String, Object>> rs = new Result<Map<String, Object>>();
		if(null == query) {
			return rs;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<UserQuery> list = userDao.loadUsersByQuery(query);
			for(UserQuery user : list) {
				Integer role = user.getRole();
				if(null != role) {
					if(role == Constants.SYSTEM_USER_ROLE) {
						user.setRoleView(Constants.SYSTEM_ROLE_VIEW);
					}
					if(role == Constants.NON_SYSTEM_USER_ROLE) {
						user.setRoleView(Constants.NON_SYSTEM_ROLE_VIEW);
					}
				}
			}
			int total = userDao.loadTotalNumByQuery(query);
			map.put("total", total);
			map.put("rows", list == null ? new ArrayList<UserDo>() : list);
			rs.setSuccess(true);
			rs.setDefaultModel(map);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}

}
