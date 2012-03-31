package com.ssh2.service.menu;

import java.util.List;

import com.ssh2.ServiceException;
import com.ssh2.utils.PaginationSupport;
import com.ssh2.vo.menu.MenuModel;

public interface MenuService {
	
	MenuModel getMenu(String id) throws ServiceException;
	
	List<MenuModel> getMenus() throws ServiceException;
	
	List<MenuModel> getMenus(boolean root) throws ServiceException;
	
//	List<MenuModel> getMenusByRole(String id) throws ServiceException;
//	
//	List<MenuModel> getMenusByRole(String id, boolean root) throws ServiceException;
//	
//	List<MenuModel> getMenusByRole(RoleModel model) throws ServiceException;
//	
//	List<MenuModel> getMenusByRole(RoleModel model, boolean root) throws ServiceException;
	
	List<MenuModel> getMenusByParent(String id) throws ServiceException;
	
	List<MenuModel> getMenusByParent(MenuModel model) throws ServiceException;
	
	List<MenuModel> getMenusByName(String menuTitle) throws ServiceException;
	
	void remove(String id) throws ServiceException;
	
	void remove(MenuModel model) throws ServiceException;
	
	void saveOrUpdate(MenuModel model) throws ServiceException;
	
	void saveOrUpdate(MenuModel model, boolean update) throws ServiceException;
	
	/**
	 * 
	 * @param pageSize
	 * @param startIndex
	 * @param order
	 * @param isDesc
	 * @return
	 * @throws ServiceException
	 * @author yufeng
	 */
	PaginationSupport<MenuModel> getPage(int pageSize,int startIndex,String order,Boolean isDesc)throws ServiceException;

	int getTotal()throws ServiceException;
}
