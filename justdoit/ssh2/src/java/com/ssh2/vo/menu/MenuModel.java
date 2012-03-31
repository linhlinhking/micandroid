package com.ssh2.vo.menu;

import java.util.Set;

import org.apache.struts2.json.annotations.JSON;

import com.ssh2.vo.BaseModel;

public class MenuModel extends BaseModel {

	private static final long serialVersionUID = -4910835072793163473L;
	
	private String menuTitle;
	private String menuDescription;
	private String menuIconClass;
	private String menuURL;
	private MenuModel menuParent;
	private Integer menuOrder;
	private Set<MenuModel> menuChildren;
	
	private Integer editable = 1;
	
	@JSON(name="title")
	public String getMenuTitle() {
		return menuTitle;
	}
	public void setMenuTitle(String menuTitle) {
		this.menuTitle = menuTitle;
	}
	@JSON(name="description")
	public String getMenuDescription() {
		return menuDescription;
	}
	public void setMenuDescription(String menuDescription) {
		this.menuDescription = menuDescription;
	}
	@JSON(name="icon")
	public String getMenuIconClass() {
		return menuIconClass;
	}
	public void setMenuIconClass(String menuIconClass) {
		this.menuIconClass = menuIconClass;
	}
	@JSON(name="url")
	public String getMenuURL() {
		return menuURL;
	}
	public void setMenuURL(String menuURL) {
		this.menuURL = menuURL;
	}
	@JSON(serialize=false)
	public MenuModel getMenuParent() {
		return menuParent;
	}
	public void setMenuParent(MenuModel menuParent) {
		this.menuParent = menuParent;
	}
	@JSON(serialize=false)
	public Set<MenuModel> getMenuChildren() {
		return menuChildren;
	}
	public void setMenuChildren(Set<MenuModel> menuChildren) {
		this.menuChildren = menuChildren;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setEditable(Integer editable) {
		this.editable = editable;
	}
	@JSON(serialize=false) 
	public Integer getEditable() {
		return editable;
	}
	public void setMenuOrder(Integer menuOrder) {
		this.menuOrder = menuOrder;
	}
	public Integer getMenuOrder() {
		return menuOrder;
	}
	
}
