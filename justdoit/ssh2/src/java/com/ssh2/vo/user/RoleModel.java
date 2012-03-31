package com.ssh2.vo.user;

import java.util.HashSet;
import java.util.Set;

import com.ssh2.vo.BaseModel;
import com.ssh2.vo.menu.MenuModel;

/**
 * 
 * @author JeccyZhao
 *
 */
public class RoleModel extends BaseModel {

	private static final long serialVersionUID = -720261263445253179L;
	
	private String roleName;
	private String roleDescription;
	private Integer roleEditable = ERoleEditable.Editable.getValue();
	private Set<MenuModel> menus = new HashSet<MenuModel>();
	
	public enum ERoleEditable{
		NonEditable(0),
		Editable(1);
		private int value;
		ERoleEditable(int value){
			this.value = value;
		}
		public int getValue(){
			return value;
		}
	}
	
	public enum ERoleHighLevel{
		N(0),
		Y(1);
		private int value;
		ERoleHighLevel (int value) {
			this.value = value;
		}
		public int getValue() {
			return value;
		}
	}
	
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleEditable(Integer roleEditable) {
		this.roleEditable = roleEditable;
	}

	public Integer getRoleEditable() {
		return roleEditable;
	}

	public void setMenus(Set<MenuModel> menus) {
		this.menus = menus;
	}

	public Set<MenuModel> getMenus() {
		return menus;
	}
	
}
