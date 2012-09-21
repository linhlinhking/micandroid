package com.cisco.pmonitor.dao.dataobject;

public class UserGroupDo extends BaseDo {

	private static final long serialVersionUID = 5343726527501295192L;
	
	private String username;
	private Integer groupId;
	private Integer role;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}

}
