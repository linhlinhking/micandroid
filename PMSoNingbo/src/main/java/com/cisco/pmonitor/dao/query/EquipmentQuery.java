package com.cisco.pmonitor.dao.query;

import org.apache.commons.lang.StringUtils;

import com.cisco.pmonitor.dao.utils.Constants;

public class EquipmentQuery extends BaseQuery {

	private static final long serialVersionUID = -231403142204188809L;

	private String name;
	private String owner;
	private String location;
	private String description;
	private String room;
	private String group;
	private String department;
	private Double grossPower;
	private int status = -1;
	private String reserver;
	private Integer roomId;
	private Integer groupId;
	private Integer departmentId;
	private String strStatus = "Idle";
	private String reserverTo;
	private double usage;
	public String getName() {
		return name == null ? "" : name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOwner() {
		return owner  == null ? "" : owner;
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
	public String getRoom() {
		return room == null ? "" : room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getGroup() {
		return group == null ? "" : group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getDepartment() {
		return department == null ? "" : department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
		if(status == Constants.IDLE_EQUIPMENT_STATUS) {
			this.strStatus = "<span style='color:green'>Idle</span>";
		}
		if(status == Constants.RESERVE_EQUIPMENT_STATUS) {
			this.strStatus = "<span style='color:red'>Reserved</span>";
		}
	}
	public String getReserver() {
		return reserver == null ? "" : reserver;
	}
	public void setReserver(String reserver) {
		this.reserver = reserver;
	}
	public String getStrStatus() {
		if(StringUtils.isEmpty(strStatus)) {
			return "";
		}
		return strStatus;
	}
	public void setStrStatus(String strStatus) {
		this.strStatus = strStatus;
	}
	public String getReserverTo() {
		return reserverTo == null ? "" : reserverTo;
	}
	public void setReserverTo(String reserverTo) {
		this.reserverTo = reserverTo;
	}
	public double getUsage() {
		return usage;
	}
	public void setUsage(double usage) {
		this.usage = usage;
	}
	public Integer getRoomId() {
		return roomId;
	}
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public Integer getDepartmentId() {
		return departmentId;
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
