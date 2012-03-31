package com.ssh2.service.user.impl;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.ssh2.DAOException;
import com.ssh2.ServiceException;
import com.ssh2.dao.user.RoleDAO;
import com.ssh2.service.user.RoleService;
import com.ssh2.utils.PaginationSupport;
import com.ssh2.utils.StringUtils;
import com.ssh2.vo.menu.MenuModel;
import com.ssh2.vo.user.RoleModel;

public class RoleServiceImpl implements RoleService {
	
	private final static Logger logger = Logger.getLogger(RoleServiceImpl.class);
	
	private RoleDAO roleDAO;
	
	/*
	 * (non-Javadoc)
	 * @see ims.mary.service.role.RoleService#getRoleById(java.lang.String)
	 */
	public RoleModel getRoleById(String id) throws ServiceException {
		try {
			return roleDAO.get(id);
		} catch (DAOException e) {
			logger.error(e.getMessage());
		} catch (Exception e){
			throw new ServiceException(e);
		}
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see ims.mary.service.role.RoleService#getRoleByName(java.lang.String)
	 */
	public RoleModel getRoleByName(String roleName) throws ServiceException {
		try {
			return roleDAO.getByRoleName(roleName);
		} catch (DAOException e) {
			logger.error(e.getMessage());
		} catch (Exception e){
			throw new ServiceException(e);
		}
		return null;
	}
	/*
	 * (non-Javadoc)
	 * @see ims.mary.service.role.RoleService#getRolesByModel(ims.mary.model.user.RoleModel, int, int, java.lang.String, java.lang.Boolean)
	 */
	public PaginationSupport<RoleModel> getRolesByModel(RoleModel model,
			int pageSize, int startIndex, String order, Boolean isDesc) throws ServiceException {
		DetachedCriteria criteria = DetachedCriteria.forClass(RoleModel.class);
		
		if(model != null){
			
			if(StringUtils.isNotEmpty(model.getRoleName())){
				criteria.add(Restrictions.eq("roleName", model.getRoleName()));
			}
			
			if(StringUtils.isNotEmpty(model.getRoleDescription())){
				criteria.add(Restrictions.eq("roleDescription", model.getRoleDescription()));
			}
			
			if(StringUtils.isNotEmpty(model.getId())){
				criteria.add(Restrictions.eq("id", model.getId()));
			}
			
			if (StringUtils.isNotEmpty(order)) {
				if (isDesc == null || !isDesc) {
					criteria.addOrder(Order.desc(order));
				} else {
					criteria.addOrder(Order.asc(order));
				}
			} else {
				criteria.addOrder(Order.asc("roleName"));
			}
		}
		
		try {
			return roleDAO.findPageByCriteria(criteria, pageSize, startIndex);
		} catch (DAOException e) {
			logger.error(e.getMessage());
		} catch (Exception e){
			throw new ServiceException(e);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see ims.mary.service.role.RoleService#removeRoleById(java.lang.String)
	 */
	public void removeRoleById(String id) throws ServiceException {
		removeRoleByModel(getRoleById(id));
	}

	/*
	 * (non-Javadoc)
	 * @see ims.mary.service.role.RoleService#removeRoleByModel(ims.mary.model.user.RoleModel)
	 */
	public void removeRoleByModel(RoleModel roleModel) throws ServiceException {
		if(roleModel != null){
			try {
				roleDAO.remove(roleModel);
			} catch (DAOException e) {
				logger.error(e.getMessage());
			} catch (Exception e){
				throw new ServiceException(e);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see ims.mary.service.role.RoleService#saveOrUpdateRole(ims.mary.model.user.RoleModel)
	 */
	public void saveOrUpdateRole(RoleModel roleModel)
			throws ServiceException {
		saveOrUpdateRole(roleModel, true);
	}
	
	/**
	 * 
	 * @param roleModel
	 * @param update
	 * @throws ServiceException
	 */
	public void saveOrUpdateRole(RoleModel roleModel, boolean update) 
			throws ServiceException {
		if(roleModel != null){
			try {
				roleDAO.saveOrUpdate(roleModel, update);
			} catch (DAOException e) {
				logger.error(e.getMessage());
			} catch (Exception e){
				throw new ServiceException(e);
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see ims.mary.service.role.RoleService#merge(ims.mary.model.user.RoleModel)
	 */
	public void merge(RoleModel roleModel) throws ServiceException {
		merge(roleModel, true);
	}
	
	/*
	 * (non-Javadoc)
	 * @see ims.mary.service.role.RoleService#merge(ims.mary.model.user.RoleModel, boolean)
	 */
	public void merge(RoleModel roleModel, boolean update) throws ServiceException {
		if(roleModel != null){
			try {
				roleDAO.merge(roleModel, update);
			} catch (DAOException e) {
				logger.error(e.getMessage());
			} catch (Exception e){
				throw new ServiceException(e);
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see ims.mary.service.role.RoleService#getRelatedRoleByUrl(java.lang.String)
	 */
	public List<RoleModel> getRelatedRoleByUrl(String url)
			throws ServiceException{
		try {
			return roleDAO.getRelatedRoleByUrl(url);
		} catch (DAOException e) {
			logger.error(e.getMessage());
		} catch (Exception e){
			throw new ServiceException(e);
		}
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see ims.mary.service.role.RoleService#getRolesByUserName(java.lang.String)
	 */
	/*public List<RoleModel> getRolesByUserName(String userName)
			throws ServiceException{
		try {
			return staffDAO.getRolesByNo(userName);
		} catch (DAOException e) {
			logger.error(e.getMessage());
		} catch (Exception e){
			throw new ServiceException(e);
		}
		return null;
	}*/
	
	/*
	 * (non-Javadoc)
	 * @see ims.mary.service.role.RoleService#getRoles()
	 */
	public List<RoleModel> getRoles()
			throws ServiceException{
		try {
			return roleDAO.getRolesByModel(null);
		} catch (DAOException e) {
			logger.error(e.getMessage());
		} catch (Exception e){
			throw new ServiceException(e);
		}
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see ims.mary.service.menu.MenuService#getMenusByRole(java.lang.String)
	 */
	public Set<MenuModel> getMenusByRole(String id) throws ServiceException {
		return getMenusByRole(id, false);
	}
	
	/*
	 * (non-Javadoc)
	 * @see ims.mary.service.menu.MenuService#getMenusByRole(java.lang.String, boolean)
	 */
	public Set<MenuModel> getMenusByRole(String id, boolean root) throws ServiceException {
		try {
			return roleDAO.getMenusByRole(id, root);
		} catch (DAOException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			throw (new ServiceException(e));
		}
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see ims.mary.service.menu.MenuService#getMenusByRole(ims.mary.model.user.RoleModel)
	 */
	public Set<MenuModel> getMenusByRole(RoleModel model) throws ServiceException {
		return getMenusByRole(model, false);
	}
	
	/*
	 * (non-Javadoc)
	 * @see ims.mary.service.menu.MenuService#getMenusByRole(ims.mary.model.user.RoleModel, boolean)
	 */
	public Set<MenuModel> getMenusByRole(RoleModel model, boolean root) throws ServiceException {
		try {
			return roleDAO.getMenusByRole(model, root);
		} catch (DAOException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			throw (new ServiceException(e));
		}
		return null;
	}
	
	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	public RoleDAO getRoleDAO() {
		return roleDAO;
	}

	public static Logger getLogger() {
		return logger;
	}

}
