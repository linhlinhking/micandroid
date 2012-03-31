package com.ssh2.vo.user;

import com.ssh2.vo.BaseModel;

public class UserModel extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 711495810724776052L;
	private String name;
	private String password;
	private String mail;
	
	private RoleModel role;
	
	public UserModel(){}
	
	/**
	 * 
	 * @param name
	 * @param password
	 * @param mail
	 */
	public UserModel(String name, String password, String mail){
		this.name = name;
		this.password = password;
		this.mail = mail;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setRole(RoleModel role) {
		this.role = role;
	}

	public RoleModel getRole() {
		return role;
	}

	
}
