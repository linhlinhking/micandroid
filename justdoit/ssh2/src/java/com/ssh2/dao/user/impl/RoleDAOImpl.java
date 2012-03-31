package com.ssh2.dao.user.impl;

import java.util.List;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.ssh2.DAOException;
import com.ssh2.dao.BaseDAOImpl;
import com.ssh2.dao.user.RoleDAO;
import com.ssh2.utils.StringUtils;
import com.ssh2.vo.menu.MenuModel;
import com.ssh2.vo.user.RoleModel;

public class RoleDAOImpl extends BaseDAOImpl<RoleModel> implements RoleDAO {
	
	/*
	 * (non-Javadoc)
	 * @see ims.mary.dao.role.RoleDAO#getRolesByModel(ims.mary.model.user.RoleModel)
	 */
	@SuppressWarnings("unchecked")
	public List<RoleModel> getRolesByModel(RoleModel model) throws DAOException {
		DetachedCriteria criteria = DetachedCriteria.forClass(RoleModel.class);
		
		if (model != null) {
			if (StringUtils.isNotEmpty(model.getRoleName())){
				criteria.add(Restrictions.eq("roleName", model.getRoleName()));
			}
			
			if (StringUtils.isNotEmpty(model.getRoleDescription())){
				criteria.add(Restrictions.eq("roleDescription", model.getRoleDescription()));
			}
		}
		
		return getListByCriteria(criteria);
	}
	
	/*
	 * (non-Javadoc)
	 * @see ims.mary.dao.role.RoleDAO#getByRoleName(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public RoleModel getByRoleName(String roleName) throws DAOException {
		if(StringUtils.isNotEmpty(roleName)){
			DetachedCriteria criteria = DetachedCriteria.forClass(RoleModel.class);
			criteria.add(Restrictions.eq("roleName", roleName));
			List<RoleModel> result = getListByCriteria(criteria);
			return result != null && result.size() > 0 ? result.get(0) : null;
		}
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see ims.mary.dao.role.RoleDAO#getRelatedRoleByUrl(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<RoleModel> getRelatedRoleByUrl(String url) throws DAOException {
		if(StringUtils.isNotEmpty(url)){
			DetachedCriteria criteria = DetachedCriteria.forClass(RoleModel.class);
			criteria.createCriteria("resources").add(Restrictions.eq("resourceValue", url));
			return getListByCriteria(criteria);
		}
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see ims.mary.dao.menu.MenuDAO#getMenusByRole(ims.mary.model.user.RoleModel)
	 */
	public Set<MenuModel> getMenusByRole(RoleModel role) throws DAOException {
		return getMenusByRole(role, false);
	}
	
	/*
	 * (non-Javadoc)
	 * @see ims.mary.dao.menu.MenuDAO#getMenusByRole(ims.mary.model.user.RoleModel)
	 */
	@SuppressWarnings("unchecked")
	public Set<MenuModel> getMenusByRole(RoleModel role, boolean root) throws DAOException {
		if(role != null){
			DetachedCriteria criteria = DetachedCriteria.forClass(RoleModel.class);
			criteria.add(Restrictions.eq("id", role.getId()));
			if(root){
				criteria.createCriteria("menus").add(Restrictions.isNull("menuParent"));
			}
			List<RoleModel> result = getListByCriteria(criteria);
			if(result != null && result.size() > 0){
				RoleModel r = result.get(0);
				return r.getMenus();
			}
		}
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see ims.mary.dao.menu.MenuDAO#getMenusByRole(java.lang.String)
	 */
	public Set<MenuModel> getMenusByRole(String id) throws DAOException {
		return getMenusByRole(id, false);
	}
	
	/*
	 * (non-Javadoc)
	 * @see ims.mary.dao.menu.MenuDAO#getMenusByRoleName(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Set<MenuModel> getMenusByRoleName(String roleName) throws DAOException {
		if(StringUtils.isNotEmpty(roleName)){
			DetachedCriteria criteria = DetachedCriteria.forClass(RoleModel.class);
			criteria.add(Restrictions.eq("roleName", roleName));
			criteria.createCriteria("menus").addOrder(Order.asc("menuOrder"));
			List<RoleModel> result = getListByCriteria(criteria);
			if(result != null && result.size() > 0){
				RoleModel r = result.get(0);
				return r.getMenus();
			}
		}
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see ims.mary.dao.menu.MenuDAO#getMenusByRole(java.lang.String, boolean)
	 */
	@SuppressWarnings("unchecked")
	public Set<MenuModel> getMenusByRole(String id, boolean root) throws DAOException {
		if(StringUtils.isNotEmpty(id)){
			DetachedCriteria criteria = DetachedCriteria.forClass(RoleModel.class);
			criteria.add(Restrictions.eq("id", id));
			if(root){
				criteria.createCriteria("menus").add(Restrictions.isNull("menuParent"));
			}
			List<RoleModel> result = getListByCriteria(criteria);
			if(result != null && result.size() > 0){
				RoleModel r = result.get(0);
				return r.getMenus();
			}
		}
		return null;
	}
	
}
