/*
 * LoginEvent.java 2009-5-29
 * 
 * Copyright 2008-2011 the original author or authors.
 * Licensed under the Apache License, Version 2.0
 */
package org.jxstar.service.event;

import java.util.Map;


import org.jxstar.control.action.RequestContext;
import org.jxstar.dao.DaoParam;
import org.jxstar.security.Password;
import org.jxstar.service.BusinessObject;
import org.jxstar.service.util.SysUserUtil;
import org.jxstar.util.MapUtil;
import org.jxstar.util.resource.JsMessage;
import org.jxstar.util.resource.JsParam;

/**
 * 用户登录事件。
 * 
 * @author TonyTan
 * @version 1.0, 2009-5-28
 */
public class LoginEvent extends BusinessObject {
	private static final long serialVersionUID = 4418303228808369943L;

	/**
	 * 用户登陆系统的方法.
	 * 
	 * @return boolean
	 */
	public String login(RequestContext requestContext) {

		String sUserCode = requestContext.getRequestValue("user_code");
		String sUserPass = requestContext.getRequestValue("user_pass");
		_log.showDebug("login user code = " + sUserCode);
		
		//检查用户名与密码
		if (sUserCode.length() == 0 || sUserPass.length() == 0) {
			setMessage(JsMessage.getValue("loginbm.usercodenull"));
			return _returnFaild;
		}
		//从数据库中去用户信息
		Map<String,String> mpUser = SysUserUtil.getUserByCode(sUserCode);
		//_log.showDebug("user info = " + mpUser);
		if (mpUser == null || mpUser.isEmpty()) {
			setMessage(JsMessage.getValue("loginbm.nouserinfo", sUserCode));
			return _returnFaild;
		}
		
		//比较密码
		String sPass = (String) mpUser.get("user_pwd");
		//加密后的密码
		String userPass = Password.md5(sUserPass);
		if (! sPass.equals(userPass)) {
			setMessage(JsMessage.getValue("loginbm.userpwderror"));
			return _returnFaild;
		}
		
		//取当前用户的角色
		String sUserID = (String) mpUser.get("user_id");
		String sRoleID = SysUserUtil.getRoleID(sUserID);
		if (sRoleID == null || sRoleID.length() == 0) {
			setMessage(JsMessage.getValue("loginbm.nouserrole", sUserCode));
			return _returnFaild;
		}
		
		//保存用户信息
		mpUser.put("role_id", sRoleID);
		mpUser.remove("user_pwd");
		
		//取项目路径，系统登录时的项目路径为当前系统路径，在选择项目后可以修改项目路径
		String sysPath = requestContext.getRequestValue(JsParam.REALPATH);
		
		//保存用户信息到会话中
		mpUser.put("project_path", sysPath);
		_log.showDebug("-------session user info: " + mpUser.toString());
		requestContext.setUserInfo(mpUser);
		
		//添加实施部门信息, add by Tony.Tan at 2011-10-16
		getDoneDept(mpUser);
		
		//把用户信息保存为脚本，返回给前台
		setReturnData(getScript(mpUser, sysPath));
		
		return _returnSuccess;
	}
	
	/**
	 * 把用户信息保存为脚本
	 * @param mpUser
	 * @return
	 */
	private String getScript(Map<String,String> mpUser, String sysPath) {
		StringBuilder sbUser = new StringBuilder("{");
		sbUser.append("user_id:'" + mpUser.get("user_id") + "',");
		sbUser.append("user_code:'" + mpUser.get("user_code") + "',");
		sbUser.append("user_name:'" + mpUser.get("user_name") + "',");
		sbUser.append("dept_id:'" + mpUser.get("dept_id") + "',");
		sbUser.append("dept_code:'" + mpUser.get("dept_code") + "',");
		sbUser.append("dept_name:'" + mpUser.get("dept_name") + "',");
		
		//添加实施部门信息, add by Tony.Tan at 2011-10-16
		sbUser.append("done_deptid:'" + MapUtil.getValue(mpUser, "done_deptid") + "',");
		sbUser.append("done_deptcode:'" + MapUtil.getValue(mpUser, "done_deptcode") + "',");
		sbUser.append("done_deptname:'" + MapUtil.getValue(mpUser, "done_deptname") + "',");
		
		sbUser.append("project_path:'" + sysPath + "',");
		sbUser.append("role_id:'" + mpUser.get("role_id") + "'}");
		
		return sbUser.toString();
	}
	
	//设置实施单位, add by Tony.Tan at 2011-10-16
	private void getDoneDept(Map<String,String> mpUser) {
		String deptId = MapUtil.getValue(mpUser, "dept_id");
		if (deptId.length() < 4) return;
		
		String rootid = deptId.substring(0, 4);
		//如果是本部单位，则直接取当前部门
		if (rootid.equals("1001")) {
			mpUser.put("done_deptid", mpUser.get("dept_id"));
			mpUser.put("done_deptcode", mpUser.get("dept_code"));
			mpUser.put("done_deptname", mpUser.get("dept_name"));
		} else {
			String sql = "select dept_id, dept_code, dept_name from sys_dept where dept_id = ?";
			DaoParam param = _dao.createParam(sql);
			param.addStringValue(rootid);
			Map<String,String> mpData = _dao.queryMap(param);
			
			mpUser.put("done_deptid", MapUtil.getValue(mpData, "dept_id"));
			mpUser.put("done_deptcode", MapUtil.getValue(mpData, "dept_code"));
			mpUser.put("done_deptname", MapUtil.getValue(mpData, "dept_name"));
		}
	}
}
