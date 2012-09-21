package com.cisco.pmonitor.dao.dataobject;

/**
 * 
 * @author shuaizha
 *
 */
public class RoomDo extends BaseDo {

	private static final long serialVersionUID = -4062647121584283099L;
	
	private String name;
	private String location;
	private String description;
	private String owner;
	private int departmentId;
	
	public String getName() {
		return name == null ? "" : name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location == null ? "" : location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description == null ? "" : description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getOwner() {
		return owner == null ? "" : owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	
}
