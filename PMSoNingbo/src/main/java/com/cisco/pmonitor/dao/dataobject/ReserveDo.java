package com.cisco.pmonitor.dao.dataobject;

public class ReserveDo extends BaseDo {

	private static final long serialVersionUID = -4690782042112399926L;

	private String reserver;
	private int equipmentId;
	private String startTime;
	private String endTime;
	private int status;
	private String description;
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
	}
	public String getDescription() {
		return description == null ? "" : description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
