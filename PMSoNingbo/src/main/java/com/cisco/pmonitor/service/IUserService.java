package com.cisco.pmonitor.service;

import java.util.List;
import java.util.Map;

import com.cisco.pmonitor.dao.dataobject.UserDo;
import com.cisco.pmonitor.dao.query.UserQuery;
import com.cisco.pmonitor.service.exception.ServiceException;
import com.cisco.pmonitor.service.util.Result;

/**
 * 
 * @author shuaizha
 *
 */
public interface IUserService {

	/**
	 * add a new user.
	 * @param userDo
	 * @return
	 * @throws ServiceException
	 */
	public Result<Integer> addUser(UserDo userDo) throws ServiceException;
	
	/**
	 * update an exsited user.
	 * @param userDo
	 * @return
	 * @throws ServiceException
	 */
	public Result<Integer> updateUser(UserDo userDo) throws ServiceException;
	
	/**
	 * delete a user.
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	public Result<Integer> deleteUser(int id) throws ServiceException;
	
	/**
	 * find user by id.
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	public Result<UserDo> findUserById(int id) throws ServiceException;
	
	/**
	 * find user by username.
	 * @param username
	 * @return
	 * @throws ServiceException
	 */
	public Result<UserDo> findUserByUsername(String username) 
												throws ServiceException;
	
	/**
	 * load all user info.
	 * @return
	 * @throws ServiceException
	 */
	public Result<List<UserDo>> loadAllUsers() throws ServiceException;
	
	public Result<Map<String, Object>> loadUsersByQuery(UserQuery query) throws ServiceException;
}
