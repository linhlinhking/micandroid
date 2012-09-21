package com.cisco.pmonitor.dao.query;

import com.cisco.pmonitor.dao.utils.Constants;

public class ReserveQuery extends BaseQuery {

	private static final long serialVersionUID = 1L;

	private String reserver;
	private int equipmentId;
	private String startTime;
	private String endTime;
	private int status;
	private String description;
	private String equipmentName;
	private String strStatus;
	public String getReserver() {
		return reserver == null ? "" : reserver;
	}
	public void setReserver(String reserver) {
		this.reserver = reserver;
	}
	public int getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(int equipmentId) {
		this.equipmentId = equipmentId;
	}
	public String getStartTime() {
		return startTime == null ? "" : startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime == null ? "" : endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
		if(status == Constants.PENDING_STATUS) {
			this.strStatus = "<span style='color:green'>Pending</span>";
		}
		if(status == Constants.RESERVED_STATUS) {
			this.strStatus = "<span style='color:red'>Reserved</span>";
		}
		if(status == Constants.OVERDUE_STATUS) {
			this.strStatus = "<span style='color:black'>Overdue</span>";
		}
	}
	public String getDescription() {
		return description == null ? "" : description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEquipmentName() {
		return equipmentName == null ? "" : equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	public String getStrStatus() {
		return strStatus == null ? "" : strStatus;
	}
	public void setStrStatus(String strStatus) {
		this.strStatus = strStatus;
	}
}
