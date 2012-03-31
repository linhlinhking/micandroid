package com.ssh2.dao.menu.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.ssh2.DAOException;
import com.ssh2.dao.BaseDAOImpl;
import com.ssh2.dao.menu.MenuDAO;
import com.ssh2.utils.StringUtils;
import com.ssh2.vo.menu.MenuModel;



public class MenuDAOImpl extends BaseDAOImpl<MenuModel> implements MenuDAO {

	/*
	 * (non-Javadoc)
	 * @see ims.mary.dao.menu.MenuDAO#getMenus()
	 */
	public List<MenuModel> getMenus() throws DAOException {
		return getMenus(false); 
	}
	
	/*
	 * (non-Javadoc)
	 * @see ims.mary.dao.menu.MenuDAO#getMenus(boolean)
	 */
	public List<MenuModel> getMenus(boolean root) throws DAOException {
		return findListByNamedQuery("menu.all" + (root ? ".root" : ""));
	}
	
//	/*
//	 * (non-Javadoc)
//	 * @see ims.mary.dao.menu.MenuDAO#getMenusByRole(ims.mary.model.user.RoleModel)
//	 */
//	public List<MenuModel> getMenusByRole(RoleModel role) throws DAOException {
//		return getMenusByRole(role, false);
//	}
//	
//	/*
//	 * (non-Javadoc)
//	 * @see ims.mary.dao.menu.MenuDAO#getMenusByRole(ims.mary.model.user.RoleModel)
//	 */
//	@SuppressWarnings("unchecked")
//	public List<MenuModel> getMenusByRole(RoleModel role, boolean root) throws DAOException {
//		if(role != null){
//			DetachedCriteria criteria = DetachedCriteria.forClass(MenuModel.class);
//			criteria.createCriteria("roles").add(Restrictions.eq("id", role.getId()));
//			if(root){
//				criteria.add(Restrictions.isNull("menuParent"));
//			}
//			List<MenuModel> result = getListByCriteria(criteria);
//			return result.isEmpty() ? null : result;
//		}
//		return null;
//	}
//	
//	/*
//	 * (non-Javadoc)
//	 * @see ims.mary.dao.menu.MenuDAO#getMenusByRole(java.lang.String)
//	 */
//	public List<MenuModel> getMenusByRole(String id) throws DAOException {
//		return getMenusByRole(id, false);
//	}
//	
//	/*
//	 * (non-Javadoc)
//	 * @see ims.mary.dao.menu.MenuDAO#getMenusByRoleName(java.lang.String)
//	 */
//	@SuppressWarnings("unchecked")
//	public List<MenuModel> getMenusByRoleName(String roleName) throws DAOException {
//		if(StringUtils.isNotEmpty(roleName)){
//			DetachedCriteria criteria = DetachedCriteria.forClass(MenuModel.class);
//			criteria.createCriteria("roles").add(Restrictions.eq("roleName", roleName));
//			List<MenuModel> result = getListByCriteria(criteria);
//			return result.isEmpty() ? null : result;
//		}
//		return null;
//	}
//	
//	/*
//	 * (non-Javadoc)
//	 * @see ims.mary.dao.menu.MenuDAO#getMenusByRole(java.lang.String, boolean)
//	 */
//	@SuppressWarnings("unchecked")
//	public List<MenuModel> getMenusByRole(String id, boolean root) throws DAOException {
//		if(StringUtils.isNotEmpty(id)){
//			DetachedCriteria criteria = DetachedCriteria.forClass(MenuModel.class);
//			criteria.createCriteria("roles").add(Restrictions.eq("id", id));
//			if(root){
//				criteria.add(Restrictions.isNull("menuParent"));
//			}
//			List<MenuModel> result = getListByCriteria(criteria);
//			return result.isEmpty() ? null : result;
//		}
//		return null;
//	}
	
	
	/*
	 * (non-Javadoc)
	 * @see ims.mary.dao.menu.MenuDAO#getMenusBYName(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<MenuModel> getMenusByName(String menuTitle) throws DAOException {
		if(StringUtils.isNotEmpty(menuTitle)){
			DetachedCriteria criteria = DetachedCriteria.forClass(MenuModel.class);
			criteria.add(Restrictions.eq("menuTitle", menuTitle));
			criteria.addOrder(Order.asc("id"));
			List<MenuModel> result = getListByCriteria(criteria);
			return result.isEmpty() ? null : result;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see ims.mary.dao.menu.MenuDAO#getMenusByParent(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<MenuModel> getMenusByParent(String id) throws DAOException {
		if(StringUtils.isNotEmpty(id)){
			DetachedCriteria criteria = DetachedCriteria.forClass(MenuModel.class);
			criteria.createCriteria("menuParent").add(Restrictions.eq("id", id));
			List<MenuModel> result = getListByCriteria(criteria);
			return result.isEmpty() ? null : result;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see ims.mary.dao.menu.MenuDAO#getMenusByParent(ims.mary.model.menu.MenuModel)
	 */
	@SuppressWarnings("unchecked")
	public List<MenuModel> getMenusByParent(MenuModel model) throws DAOException {
		if(model != null){
			DetachedCriteria criteria = DetachedCriteria.forClass(MenuModel.class);
			criteria.createCriteria("menuParent").add(Restrictions.eq("id", model.getId()));
			List<MenuModel> result = getListByCriteria(criteria);
			return result.isEmpty() ? null : result;
		}
		return null;
	}

}
