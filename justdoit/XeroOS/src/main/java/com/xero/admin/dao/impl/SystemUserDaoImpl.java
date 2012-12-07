package com.xero.admin.dao.impl;

import org.springframework.stereotype.Repository;

import com.xero.admin.bean.SystemUser;
import com.xero.admin.bean.type.JoinUsType;
import com.xero.admin.dao.SystemUserDao;
import com.xero.core.common.dao.impl.BaseDaoImpl;
import com.xero.core.exception.DaoException;
import com.xero.core.security.MD5Util;

@Repository("systemUserDao")
public class SystemUserDaoImpl extends BaseDaoImpl<SystemUser, Integer>
		implements SystemUserDao {

	public SystemUserDaoImpl() {
		super(SystemUser.class);
	}

	public SystemUser login(String username, String password)
			throws DaoException {
		String encodePassword = MD5Util.calcMD5(password);
		final String hql = "from SystemUser as model where 1=1 and model.uemail = ? and model.password = ? ";
		SystemUser u = (SystemUser) findUnique(hql, username, encodePassword);
		if (null == u) {
			return null;
		}
		return u;
	}

	public boolean checkEmail(String uemail) {
		boolean flag = false ;
		String reg = JoinUsType.REGISTRATION.toString();
		String hql = "from SystemUser as u where 1=1 and u.uemail = ? and u.joinInType = ? ";
		SystemUser u = (SystemUser)findUnique(hql, uemail, reg);
		if(null != u){
			flag = true ;
		}
		return flag;
	}
}
