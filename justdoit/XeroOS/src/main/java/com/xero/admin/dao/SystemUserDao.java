package com.xero.admin.dao;

import com.xero.admin.bean.SystemUser;
import com.xero.core.common.dao.BaseDao;
import com.xero.core.exception.DaoException;

public interface SystemUserDao extends BaseDao<SystemUser, Integer> {

	public SystemUser login(String username, String password)
			throws DaoException;
	
	public boolean checkEmail(String uemail) ;
}
