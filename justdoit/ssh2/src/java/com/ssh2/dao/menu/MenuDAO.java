package com.ssh2.dao.menu;

import java.util.List;

import com.ssh2.DAOException;
import com.ssh2.dao.BaseDAO;
import com.ssh2.vo.menu.MenuModel;


public interface MenuDAO extends BaseDAO<MenuModel> {
	
	/**
	 * 
	 * @return
	 * @throws DAOException
	 */
	List<MenuModel> getMenus() throws DAOException;
	
	/**
	 * 
	 * @return
	 */
	List<MenuModel> getMenus(boolean root) throws DAOException;
	
//	/**
//	 * 
//	 * @param role
//	 * @return
//	 * @throws DAOException
//	 */
//	List<MenuModel> getMenusByRole(RoleModel role) throws DAOException;
//	
//	/**
//	 * 
//	 * @param role
//	 * @param root
//	 * @return
//	 * @throws DAOException
//	 */
//	List<MenuModel> getMenusByRole(RoleModel role, boolean root) throws DAOException;
//	
//	/**
//	 * 
//	 * @param id
//	 * @return
//	 * @throws DAOException
//	 */
//	List<MenuModel> getMenusByRole(String id) throws DAOException;
//	
//	/**
//	 * 
//	 * @param roleName
//	 * @return
//	 * @throws DAOException
//	 */
//	List<MenuModel> getMenusByRoleName(String roleName) throws DAOException;
//	
//	/**
//	 * 
//	 * @param id
//	 * @param root
//	 * @return
//	 * @throws DAOException
//	 */
//	List<MenuModel> getMenusByRole(String id, boolean root) throws DAOException;
	
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	List<MenuModel> getMenusByParent(String id) throws DAOException;
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	List<MenuModel> getMenusByParent(MenuModel model) throws DAOException;
	
	/**
	 * 
	 * @param menuTitle
	 * @return
	 */
	List<MenuModel> getMenusByName(String menuTitle) throws DAOException;
	
}
