package com.ssh2.service.user;

import com.ssh2.ServiceException;
import com.ssh2.utils.PaginationSupport;
import com.ssh2.vo.user.UserModel;

public interface UserService {
	
	UserModel get(String id)throws ServiceException;
	
	void saveOrUpdate(UserModel model)throws ServiceException;
	
	void remove(String id) throws ServiceException;
	
	void remove(UserModel model) throws ServiceException;

	/**
	 * 
	 * @param userModel
	 * @param pageSize
	 * @param startIndex
	 * @param order
	 * @param isDesc
	 * @return
	 * @throws ServiceException
	 */
	PaginationSupport<UserModel> getPageByModel(UserModel userModel,int pageSize,int startIndex,String order,Boolean isDesc)throws ServiceException;

	int getTotal(UserModel userModel)throws ServiceException;
	
	/**
	 * 
	 * @param name
	 * @param pwd
	 * @return
	 * @throws ServiceException
	 */
	UserModel getByNameAndPwd(String name, String pwd)throws ServiceException;
}
