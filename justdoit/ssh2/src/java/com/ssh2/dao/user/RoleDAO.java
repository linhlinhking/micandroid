package com.ssh2.dao.user;

import java.util.List;
import java.util.Set;

import com.ssh2.DAOException;
import com.ssh2.dao.BaseDAO;
import com.ssh2.vo.menu.MenuModel;
import com.ssh2.vo.user.RoleModel;

public interface RoleDAO extends BaseDAO<RoleModel> {
	
	List<RoleModel> getRolesByModel(RoleModel model) throws DAOException ;
	
	List<RoleModel> getRelatedRoleByUrl(String url) throws DAOException;
	
	RoleModel getByRoleName(String roleName) throws DAOException;
	
	/**
	 * 
	 * @param role
	 * @return
	 * @throws DAOException
	 */
	Set<MenuModel> getMenusByRole(RoleModel role) throws DAOException;
	
	/**
	 * 
	 * @param role
	 * @param root
	 * @return
	 * @throws DAOException
	 */
	Set<MenuModel> getMenusByRole(RoleModel role, boolean root) throws DAOException;
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws DAOException
	 */
	Set<MenuModel> getMenusByRole(String id) throws DAOException;
	
	/**
	 * 
	 * @param id
	 * @param root
	 * @return
	 * @throws DAOException
	 */
	Set<MenuModel> getMenusByRole(String id, boolean root) throws DAOException;
	
	/**
	 * 
	 * @param roleName
	 * @return
	 * @throws DAOException
	 */
	Set<MenuModel> getMenusByRoleName(String roleName) throws DAOException;
	
	//List<RoleModel> getRolesByUserName(String userName) throws DAOException;
	
	//List<RoleModel> getRolesByUserId(String userId) throws DAOException;
	
}
