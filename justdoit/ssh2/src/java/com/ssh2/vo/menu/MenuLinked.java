package com.ssh2.vo.menu;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

public class MenuLinked implements Comparable<Object>, Serializable {
	
	private static final long serialVersionUID = 4319237481463367217L;
	
	private String menuId;
	private String menuTitle;
	private String menuIconClass;
	private String menuURL;
	private Integer menuOrder;
	private Set<MenuLinked> menuChildren = new LinkedHashSet<MenuLinked>();
	
	public MenuLinked(){
		
	}
	
	public MenuLinked(MenuModel menuModel){
		this(menuModel.getId(), menuModel.getMenuTitle(), 
				menuModel.getMenuIconClass(), menuModel.getMenuURL(), menuModel.getMenuOrder());
	}
	
	public MenuLinked(String id, String menuTitle, String menuIcon, String menuURL, Integer menuOrder){
		this.menuId = id;
		this.menuTitle = menuTitle;
		this.menuIconClass = menuIcon;
		this.menuURL = menuURL;
		this.menuOrder = menuOrder;
	}
	
	public void setMenuTitle(String menuTitle) {
		this.menuTitle = menuTitle;
	}
	public String getMenuTitle() {
		return menuTitle;
	}
	public void setMenuIconClass(String menuIcon) {
		this.menuIconClass = menuIcon;
	}
	public String getMenuIconClass() {
		return menuIconClass;
	}
	public void setMenuURL(String menuURL) {
		this.menuURL = menuURL;
	}
	public String getMenuURL() {
		return menuURL;
	}
	public void setMenuChildren(Set<MenuLinked> menuChildren) {
		this.menuChildren = menuChildren;
	}
	public Set<MenuLinked> getMenuChildren() {
		return menuChildren;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMenuId() {
		return menuId;
	}
	
	@Override
	public int hashCode(){
		return Integer.parseInt(menuId);
	}
	
	@Override
	public String toString() {
		return "id:" + menuId + "-" + menuTitle + "@" + hashCode();
	}
	
	@Override
	public int compareTo(Object o) {
		if (o instanceof MenuLinked){
			MenuLinked ml = (MenuLinked)o;
			int oid = Integer.parseInt(ml.getMenuId());
			int mid = Integer.parseInt(this.getMenuId());
			if(oid == mid){
				return 0;
			}else if(oid < mid){
				 return 1;
			}else {
				return -1;
			}
		}
		return 0;
	}

	public void setMenuOrder(Integer menuOrder) {
		this.menuOrder = menuOrder;
	}

	public Integer getMenuOrder() {
		return menuOrder;
	}
}
