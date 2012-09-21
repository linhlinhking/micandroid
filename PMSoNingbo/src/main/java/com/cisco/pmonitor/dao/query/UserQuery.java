package com.cisco.pmonitor.dao.query;

public class UserQuery extends BaseQuery {

	private static final long serialVersionUID = -6610699597262540276L;

	
	private String username;
	private String email;
	private String fullName;
	private String gmtCreate;
	private String gmtModified;
	private Integer role;
	private String roleView;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getGmtCreate() {
		return gmtCreate;
	}
	public void setGmtCreate(String gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	public String getGmtModified() {
		return gmtModified;
	}
	public void setGmtModified(String gmtModified) {
		this.gmtModified = gmtModified;
	}
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
	public String getRoleView() {
		return roleView;
	}
	public void setRoleView(String roleView) {
		this.roleView = roleView;
	}


}
