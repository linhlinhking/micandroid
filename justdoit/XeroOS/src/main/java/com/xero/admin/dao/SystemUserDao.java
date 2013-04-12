package com.xero.admin.dao;

import java.util.List;

import com.xero.admin.bean.SystemUser;
import com.xero.core.common.dao.BaseDao;
import com.xero.core.exception.DaoException;

public interface SystemUserDao extends BaseDao<SystemUser, Integer> {

	public SystemUser login(String username, String password)
			throws DaoException;

	public boolean checkEmail(String uemail) throws DaoException;

	public List<SystemUser> getAllUser() throws DaoException;
	
	@Deprecated
	public List<SystemUser> getUsersByCompanyId(Integer companyId) throws DaoException;

	public List<SystemUser> getUsersByPlanId(Integer planId, Integer companyId)
			throws DaoException;
	
}
