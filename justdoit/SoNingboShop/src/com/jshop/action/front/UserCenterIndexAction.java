package com.jshop.action.front;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.jshop.entity.UserT;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@ParentPackage("jshop")
@Namespace("")
@InterceptorRefs({
    @InterceptorRef("defaultStack")  
})
@Controller("userCenterIndexAction")
public class UserCenterIndexAction extends ActionSupport {

	private String hidurl;
	private boolean slogin = false;

	public boolean isSlogin() {
		return slogin;
	}

	public void setSlogin(boolean slogin) {
		this.slogin = slogin;
	}

	public String getHidurl() {
		return hidurl;
	}

	public void setHidurl(String hidurl) {
		this.hidurl = hidurl;
	}

	/**
	 * 清理错误
	 */
	@Override
	public void validate() {
		this.clearErrorsAndMessages();

	}

	/**
	 * 初始化用户中心
	 * 
	 * @return
	 */
	@Action(value = "InitUserCenterIndex", results = { 
			@Result(name = "success",type="dispatcher",location = "/usercenter/usercenterindex.jsp"),
			@Result(name = "input",location = "/html/default/shop/login.html")
	})
	public String InitUserCenterIndex() {
		UserT user = (UserT) ActionContext.getContext().getSession().get("user");
		if (user != null) {
			return SUCCESS;
		}
		return INPUT;
	}

}
