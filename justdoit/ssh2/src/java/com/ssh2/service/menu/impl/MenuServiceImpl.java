package com.ssh2.service.menu.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;

import com.ssh2.DAOException;
import com.ssh2.ServiceException;
import com.ssh2.dao.menu.MenuDAO;
import com.ssh2.service.menu.MenuService;
import com.ssh2.utils.PaginationSupport;
import com.ssh2.utils.StringUtils;
import com.ssh2.vo.menu.MenuModel;

public class MenuServiceImpl implements MenuService {
	
	private final static Logger logger = Logger.getLogger(MenuServiceImpl.class);
	private MenuDAO menuDAO;
	
	/*
	 * (non-Javadoc)
	 * @see ims.mary.service.menu.MenuService#getMenu(java.lang.String)
	 */
	public MenuModel getMenu(String id) throws ServiceException {
		try {
			return menuDAO.get(id);
		} catch (DAOException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			throw (new ServiceException(e));
		}
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see ims.mary.service.menu.MenuService#getMenus()
	 */
	public List<MenuModel> getMenus() throws ServiceException {
		return getMenus(false);
	}

	/*
	 * (non-Javadoc)
	 * @see ims.mary.service.menu.MenuService#getMenus(boolean)
	 */
	public List<MenuModel> getMenus(boolean root) throws ServiceException {
		try {
			return menuDAO.getMenus(root);
		} catch (DAOException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			throw (new ServiceException(e));
		}
		return null;
	}
	
//	/*
//	 * (non-Javadoc)
//	 * @see ims.mary.service.menu.MenuService#getMenusByRole(java.lang.String)
//	 */
//	public List<MenuModel> getMenusByRole(String id) throws ServiceException {
//		return getMenusByRole(id, false);
//	}
//	
//	/*
//	 * (non-Javadoc)
//	 * @see ims.mary.service.menu.MenuService#getMenusByRole(java.lang.String, boolean)
//	 */
//	public List<MenuModel> getMenusByRole(String id, boolean root) throws ServiceException {
//		try {
//			return menuDAO.getMenusByRole(id, root);
//		} catch (DAOException e) {
//			logger.error(e.getMessage());
//		} catch (Exception e) {
//			throw (new ServiceException(e));
//		}
//		return null;
//	}
//	
//	/*
//	 * (non-Javadoc)
//	 * @see ims.mary.service.menu.MenuService#getMenusByRole(ims.mary.model.user.RoleModel)
//	 */
//	public List<MenuModel> getMenusByRole(RoleModel model) throws ServiceException {
//		return getMenusByRole(model, false);
//	}
//	
//	/*
//	 * (non-Javadoc)
//	 * @see ims.mary.service.menu.MenuService#getMenusByRole(ims.mary.model.user.RoleModel, boolean)
//	 */
//	public List<MenuModel> getMenusByRole(RoleModel model, boolean root) throws ServiceException {
//		try {
//			menuDAO.getMenusByRole(model, root);
//		} catch (DAOException e) {
//			logger.error(e.getMessage());
//		} catch (Exception e) {
//			throw (new ServiceException(e));
//		}
//		return null;
//	}
	
	/*
	 * (non-Javadoc)
	 * @see ims.mary.service.menu.MenuService#getMenusByName(java.lang.String)
	 */
	public List<MenuModel> getMenusByName(String menuTitle)
			throws ServiceException {
		try {
			return menuDAO.getMenusByName(menuTitle);
		} catch (DAOException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			throw (new ServiceException(e));
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see ims.mary.service.menu.MenuService#getMenusByParent(java.lang.String)
	 */
	public List<MenuModel> getMenusByParent(String id) throws ServiceException {
		try {
			return menuDAO.getMenusByParent(id);
		} catch (DAOException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			throw (new ServiceException(e));
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see ims.mary.service.menu.MenuService#getMenusByParent(ims.mary.model.menu.MenuModel)
	 */
	public List<MenuModel> getMenusByParent(MenuModel model)
			throws ServiceException {
		try {
			return menuDAO.getMenusByParent(model);
		} catch (DAOException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			throw (new ServiceException(e));
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see ims.mary.service.menu.MenuService#remove(java.lang.String)
	 */
	public void remove(String id) throws ServiceException {
		try {
			remove(menuDAO.get(id));
		} catch (DAOException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			throw (new ServiceException(e));
		}
	}

	/*
	 * (non-Javadoc)
	 * @see ims.mary.service.menu.MenuService#remove(ims.mary.model.menu.MenuModel)
	 */
	public void remove(MenuModel model) throws ServiceException {
		try {
			menuDAO.remove(model);
		} catch (DAOException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			throw (new ServiceException(e));
		}
	}

	/*
	 * (non-Javadoc)
	 * @see ims.mary.service.menu.MenuService#saveOrUpdate(ims.mary.model.menu.MenuModel)
	 */
	public void saveOrUpdate(MenuModel model) throws ServiceException {
		saveOrUpdate(model, true);
	}
	
	/*
	 * (non-Javadoc)
	 * @see ims.mary.service.menu.MenuService#saveOrUpdate(ims.mary.model.menu.MenuModel, boolean)
	 */
	public void saveOrUpdate(MenuModel model, boolean update) throws ServiceException {
		try {
			menuDAO.saveOrUpdate(model, update);
		} catch (DAOException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			throw (new ServiceException(e));
		}
	}

	public void setMenuDAO(MenuDAO menuDAO) {
		this.menuDAO = menuDAO;
	}

	public MenuDAO getMenuDAO() {
		return menuDAO;
	}

	public static Logger getLogger() {
		return logger;
	}

	@Override
	public PaginationSupport<MenuModel> getPage(int pageSize, int startIndex,
			String order, Boolean isDesc) throws ServiceException {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(MenuModel.class);
		
		if (StringUtils.isNotEmpty(order)) {
			if (isDesc == null || !isDesc) {
				criteria.addOrder(Order.asc(order));
			} else {
				criteria.addOrder(Order.desc(order));
			}
		}
		
		criteria.addOrder(Order.asc("id"));
		
		try {
			return menuDAO.findPageByCriteria(criteria, pageSize, startIndex);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getStackTrace());
			return null;
		}
	}

	@Override
	public int getTotal() throws ServiceException {
		// TODO Auto-generated method stub
		String hql = "from MenuModel";
		return menuDAO.getCount(hql);
	}

}
