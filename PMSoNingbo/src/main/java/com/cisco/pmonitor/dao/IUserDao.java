package com.cisco.pmonitor.dao;

import java.util.List;

import com.cisco.pmonitor.dao.dataobject.UserDo;
import com.cisco.pmonitor.dao.expection.DaoException;
import com.cisco.pmonitor.dao.query.UserQuery;

/**
 * provide user data access interface.
 * @author shuaizha
 * @Date 2012-02-09
 */
public interface IUserDao {

	/**
	 * insert a new user.
	 * @param user
	 * @return user id
	 */
	public int insert(UserDo user) throws DaoException;
	
	/**
	 * update user info.
	 * @param user
	 * @return update user id
	 */
	public int update(UserDo user) throws DaoException;
	
	/**
	 * delete user by id.
	 * @param id
	 * @return
	 */
	public int delete(int id) throws DaoException;
	
	/**
	 * find user by id.
	 * @param id
	 * @return
	 */
	public UserDo findUserById(int id) throws DaoException;
	
	/**
	 * find user by username.
	 * @param username
	 * @return
	 */
	public UserDo findUserByUsername(String username) throws DaoException;
	
	/**
	 * load all user from database.
	 * @return
	 */
	public List<UserDo> loadAllUsers() throws DaoException;
	
	
	public List<UserQuery> loadUsersByQuery(UserQuery query) throws DaoException;
	
	public int loadTotalNumByQuery(UserQuery query) throws DaoException;
}
