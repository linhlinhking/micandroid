package com.ssh2.service.user;

import java.util.List;
import java.util.Set;

import com.ssh2.ServiceException;
import com.ssh2.utils.PaginationSupport;
import com.ssh2.vo.menu.MenuModel;
import com.ssh2.vo.user.RoleModel;

public interface RoleService {
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	RoleModel getRoleById(String id) 
		throws ServiceException;
	
	/**
	 * 
	 * @param roleName
	 * @return
	 * @throws ServiceException
	 * @author Zhao.Xiang
	 */
	RoleModel getRoleByName(String roleName)
		throws ServiceException;
	
	/**
	 * 
	 * @param model
	 * @param pageSize
	 * @param startIndex
	 * @param order
	 * @param isDesc
	 * @return
	 * @throws ServiceException
	 */
	PaginationSupport<RoleModel> getRolesByModel(
			RoleModel model, int pageSize, int startIndex, String order, Boolean isDesc) throws ServiceException;
		
	/**
	 * 
	 * @param roleModel
	 * @return
	 * @throws ServiceException
	 */
	void saveOrUpdateRole(RoleModel roleModel) 
		throws ServiceException;
	
	/**
	 * 
	 * @param roleModel
	 * @param update
	 * @throws ServiceException
	 */
	void saveOrUpdateRole(RoleModel roleModel, boolean update)
		throws ServiceException;
	
	/**
	 * 
	 * @param roleModel
	 * @throws ServiceException
	 */
	void merge(RoleModel roleModel) throws ServiceException;
	
	/**
	 * 
	 * @param roleModel
	 * @param update
	 * @throws ServiceException
	 */
	void merge(RoleModel roleModel, boolean update) throws ServiceException;
	
	/**
	 * 
	 * @param id
	 * @throws ServiceException
	 */
	void removeRoleById(String id)
		throws ServiceException;
	
	/**
	 * 
	 * @param roleModel
	 * @throws ServiceException
	 */
	void removeRoleByModel(RoleModel roleModel)
		throws ServiceException;
	
	/**
	 * 
	 * @param url
	 * @return
	 * @throws ServiceException
	 */
	List<RoleModel> getRelatedRoleByUrl(String url)
		throws ServiceException;
	
	/**
	 * 
	 * @param userName
	 * @return
	 *//*
	List<RoleModel> getRolesByUserName(String userName)
		throws ServiceException;*/
	
	/**
	 * 
	 * @return
	 * @throws ServiceException
	 */
	List<RoleModel> getRoles()
		throws ServiceException;
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	Set<MenuModel> getMenusByRole(String id) 
		throws ServiceException;
	
	/**
	 * 
	 * @param id
	 * @param root
	 * @return
	 * @throws ServiceException
	 */
	Set<MenuModel> getMenusByRole(String id, boolean root) 
		throws ServiceException;
	
	/**
	 * 
	 * @param model
	 * @return
	 * @throws ServiceException
	 */
	Set<MenuModel> getMenusByRole(RoleModel model) 
		throws ServiceException;
	
	/**
	 * 
	 * @param model
	 * @param root
	 * @return
	 * @throws ServiceException
	 */
	Set<MenuModel> getMenusByRole(RoleModel model, boolean root) 
		throws ServiceException;
}
