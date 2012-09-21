package com.cisco.pmonitor.dao.dataobject;

public class EquipmentDo extends BaseDo {

	private static final long serialVersionUID = 5985702075059998278L;

	
	private String name;
	private String owner;
	private String location;
	private String description;
	private int status;
	private Integer roomId;
	private Integer groupId;
	private Integer departmentId;
	private Double grossPower;
	public String getName() {
		return name == null ? "" : name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOwner() {
		return owner == null ? "" : owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Integer getRoomId() {
		return roomId == null ? 0 : roomId;
	}
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
	public Integer getGroupId() {
		return groupId == null ? 0 : groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public Integer getDepartmentId() {
		return departmentId == null ? 0 : departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public Double getGrossPower() {
		return grossPower == null ? 0 : grossPower;
	}
	public void setGrossPower(Double grossPower) {
		this.grossPower = grossPower;
	}
}
