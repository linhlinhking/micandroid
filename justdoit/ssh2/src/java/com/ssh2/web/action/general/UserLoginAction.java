package com.ssh2.web.action.general;

import org.apache.log4j.Logger;

import com.ssh2.Constants;
import com.ssh2.vo.user.UserModel;
import com.ssh2.web.action.BaseAction;

public class UserLoginAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2151210628045628746L;
	private static final Logger LOGGER = Logger.getLogger(UserLoginAction.class);
	
	private String name;
	private String password;
	private String validateCode;
	
	public String loginIndex() throws Exception{
		return defaultResult();
	}
	
	public String authIndex() throws Exception{
		servletResponse.setCharacterEncoding("utf-8");
		UserModel userModel = null;
		
		boolean validateValiCode = session.get("validateCode").equals(validateCode) ? true : false;
		if (validateValiCode == false) {
			return ajaxPrint(servletResponse, "{success:true,msg:'" + getText("valiateCode.error") + "'}");
		}else{
			userModel = userService.getByNameAndPwd(name, password);
			if (userModel == null) {
				return ajaxPrint(servletResponse, "{success:true,msg:'" + getText("pwd.error") + "'}");
			}
		}
		session.put(Constants.DefaultUserInSession, userModel);
		return ajaxPrint(servletResponse, "{success:true,msg:'ok'}");
	}
	
	public String homeIndex() throws Exception{
		UserModel userModel = (UserModel)session.get(Constants.DefaultUserInSession);
		if(userModel == null){
			return viewResult("");
		}else {
			return defaultResult();
		}	
	}
	
	public static Logger getLogger() {
		return LOGGER;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	public String getValidateCode() {
		return validateCode;
	}

}
