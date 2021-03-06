package com.xero.admin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.xero.admin.bean.SystemUser;
import com.xero.admin.dao.SystemUserDao;
import com.xero.admin.service.SystemUserService;
import com.xero.core.Response.ResponseCollection;
import com.xero.core.Response.ResponseEntity;
import com.xero.core.Response.ResponseMessage;
import com.xero.core.common.service.impl.BaseServiceImpl;
import com.xero.core.exception.ServiceException;

@Service("systemUserService")
public class SystemUserServiceImpl extends BaseServiceImpl<SystemUser, Integer>
		implements SystemUserService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private SystemUserDao systemUserDao;

	@Autowired
	public SystemUserServiceImpl(
			@Qualifier("systemUserDao") SystemUserDao systemUserDao) {
		super(systemUserDao);
	}

	public ResponseEntity<SystemUser> login(String username, String password)
			throws ServiceException {
		ResponseEntity<SystemUser> res = new ResponseEntity<SystemUser>(false);
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			logger.error("username(password) is null.");
			return res;
		}
		SystemUser u = systemUserDao.login(username, password);
		if (null != u) {
			res.setData(u);
			res.setResult(true);
			return res;
		}
		logger.error("User Is Not Exists.Email Is " + username);
		return res;
	}

	public ResponseMessage checkEmail(String uemail) throws ServiceException {
		ResponseMessage res = new ResponseMessage();
		if (systemUserDao.checkEmail(uemail)) {
			res.setResult(true);
			logger.error("User's Email is Exists.The Email is" + uemail);
		} else {
			res.setResult(false);
		}
		return res;
	}
	
	
	public ResponseCollection<SystemUser> getAllUser() {
		ResponseCollection<SystemUser> res = new ResponseCollection<SystemUser>(
				false);
		try {
			List<SystemUser> lists = systemUserDao.getAllUser();
			res.setResult(true);
			res.setData(lists);
		} catch (ServiceException se) {
			logger.error("Get All User Error On Service", se);
			res.setResult(false);
			res.setData(null);
		}
		return res;
	}

	public ResponseCollection<SystemUser> getUsersByPlanId(Integer planId,
			Integer companyId) throws ServiceException {
		ResponseCollection<SystemUser> res = new ResponseCollection<SystemUser>(
				false);
		try {
			List<SystemUser> lists = systemUserDao.getUsersByPlanId(planId,
					companyId);
			res.setResult(true);
			res.setData(lists);
		} catch (ServiceException se) {
			logger.error("Get All User Error On Service", se);
			res.setResult(false);
			res.setData(null);
		}
		return res;
	}

	public ResponseCollection<SystemUser> getUsersByCompanyId(Integer companyId)
			throws ServiceException {
		ResponseCollection<SystemUser> res = new ResponseCollection<SystemUser>(
				false);
		try {
			List<SystemUser> lists = systemUserDao.getUsersByCompanyId(companyId) ;
			res.setResult(true);
			res.setData(lists);
		} catch (ServiceException se) {
			logger.error("Get All User Error On Service", se);
			res.setResult(false);
			res.setData(null);
		}
		return res;
	}
}
