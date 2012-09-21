package com.cisco.pmonitor.dao;

import java.util.List;

import com.cisco.pmonitor.dao.dataobject.UserGroupDo;
import com.cisco.pmonitor.dao.expection.DaoException;

public interface IUserGroupDao {

	
	public int insert(UserGroupDo userGroupDo) throws DaoException;
	
	public int update(UserGroupDo userGroupDo) throws DaoException;
	
	public int delete(int id) throws DaoException;
	
	public List<UserGroupDo> loadAllUserGroups() throws DaoException;
	
	public UserGroupDo findUserGroupById(int id) throws DaoException;
	
	public UserGroupDo findUserGroupByUserAndGroup(String username, int groupId) throws DaoException;
	
	public List<UserGroupDo> findUserGroupByUserName(String username) throws DaoException;
}
