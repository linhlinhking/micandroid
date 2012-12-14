package com.xero.admin.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private Logger logger = LoggerFactory.getLogger(getClass());

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

	public boolean checkEmail(String uemail) throws DaoException {
		boolean flag = false;
		String reg = JoinUsType.REGISTRATION.toString();
		String hql = "from SystemUser as u where 1=1 and u.uemail = ? and u.joinInType = ? ";
		SystemUser u = (SystemUser) findUnique(hql, uemail, reg);
		if (null != u) {
			flag = true;
		}
		return flag;
	}

	public List<SystemUser> getAllUser() {
		List<SystemUser> lists = null;
		try {
			String hql = "from SystemUser as u where 1=1 order by u.username,u.uemail ";
			lists = findByHql(hql);
		} catch (DaoException ex) {
			logger.error("Get All User Error.", ex);
		}
		return lists;
	}

	public List<SystemUser> getUsersByPlanId(Integer planId, Integer companyId)
			throws DaoException {
		List<SystemUser> lists = null;
		try {
			String hql = "select u.* from tb_user as u inner join tb_company_user as c where 1=1 and u.id = c.userId and u.planId = ? and c.companyId = ? ";
			lists = findByNativeSql(hql, planId, companyId);
		} catch (DaoException ex) {
			logger.error("Get All User Error.", ex);
		}
		return lists;
	}
}
