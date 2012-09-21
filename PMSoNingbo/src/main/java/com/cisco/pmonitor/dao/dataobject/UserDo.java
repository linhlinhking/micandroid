package com.cisco.pmonitor.dao.dataobject;


public class UserDo extends BaseDo {

	private static final long serialVersionUID = 699033179589672733L;
	
	private String username;
	private String password;
	private String email;
	private String fullName;
	private String gmtCreate;
	private String gmtModified;
	private Integer role;
	
	public String getUsername() {
		return username == null ? "" : username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password == null ? "" : password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email == null ? "" : email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFullName() {
		return fullName == null ? "" : fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getGmtCreate() {
		return gmtCreate == null ? "" : gmtCreate;
	}
	public void setGmtCreate(String gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	public String getGmtModified() {
		return gmtModified == null ? "" : gmtModified;
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

}
