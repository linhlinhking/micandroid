package com.cisco.pmonitor.service;

import java.util.List;

import com.cisco.pmonitor.dao.dataobject.UserGroupDo;
import com.cisco.pmonitor.service.exception.ServiceException;
import com.cisco.pmonitor.service.util.Result;

public interface IUserGroupService {

	public Result<Integer> addUserGroup(UserGroupDo userGroupDo) throws ServiceException;
	public Result<Integer> updateUserGroup(UserGroupDo userGroupDo) throws ServiceException;
	
	public Result<Integer> deleteUserGroup(int id) throws ServiceException;
	
	public Result<UserGroupDo> findUserGroupById(int id) throws ServiceException;
	public Result<UserGroupDo> findUserGroupByUserAndGroup(String username, int groupId) throws ServiceException;
	
	public Result<List<UserGroupDo>> findUserGroupByUserName(String username) throws ServiceException;
	
	public Result<List<UserGroupDo>> loadAllUserGroups() throws ServiceException;
}
