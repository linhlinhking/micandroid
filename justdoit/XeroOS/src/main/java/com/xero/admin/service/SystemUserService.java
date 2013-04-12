package com.xero.admin.service;

import com.xero.admin.bean.SystemUser;
import com.xero.core.Response.ResponseCollection;
import com.xero.core.Response.ResponseEntity;
import com.xero.core.Response.ResponseMessage;
import com.xero.core.common.service.BaseService;
import com.xero.core.exception.ServiceException;

public interface SystemUserService extends BaseService<SystemUser, Integer> {

	public ResponseEntity<SystemUser> login(String username, String password)
			throws ServiceException;

	public ResponseMessage checkEmail(String uemail) throws ServiceException;

	public ResponseCollection<SystemUser> getAllUser() throws ServiceException;

	public ResponseCollection<SystemUser> getUsersByPlanId(Integer planId,
			Integer companyId) throws ServiceException;

	public ResponseCollection<SystemUser> getUsersByCompanyId(Integer companyId)
			throws ServiceException;
	

}
