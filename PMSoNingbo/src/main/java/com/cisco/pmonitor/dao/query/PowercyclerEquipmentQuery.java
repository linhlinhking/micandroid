package com.cisco.pmonitor.dao.query;

import com.cisco.pmonitor.dao.utils.Constants;

public class PowercyclerEquipmentQuery extends BaseQuery {

	private static final long serialVersionUID = 6640059598021050802L;

	private int powercyclerId;
	private Integer equipmentId;
	private int outlet;
	private int status = -1;
	private String strStatus;
	private String powercycler;
	private String equipment;
	private String owner;
	private String operation;
	public int getPowercyclerId() {
		return powercyclerId;
	}
	public void setPowercyclerId(int powercyclerId) {
		this.powercyclerId = powercyclerId;
	}
	public int getOutlet() {
		return outlet;
	}
	public void setOutlet(int outlet) {
		this.outlet = outlet;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
		if(status == Constants.POWER_ON) {
//			this.strStatus = "<span style='color:green'>On</span>";
			this.strStatus = "<img src='images/green-circle.png' style='width:16px;height:16px;'/>";
		}
		if(status == Constants.POWER_OFF) {
//			this.strStatus = "<span style='color:red'>Off</span>";
			this.strStatus = "<img src='images/red-circle.png' style='width:16px;height:16px;'/>";
		}
	}
	public String getStrStatus() {
		return strStatus;
	}
	public void setStrStatus(String strStatus) {
		this.strStatus = strStatus;
	}
	public String getPowercycler() {
		return powercycler  == null ? "" : powercycler;
	}
	public void setPowercycler(String powercycler) {
		this.powercycler = powercycler;
	}
	public String getEquipment() {
		return equipment  == null ? "" : equipment;
	}
	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}
	public String getOwner() {
		return owner == null ? "" : owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public Integer getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}
	public String getOperation() {
		if(status == Constants.POWER_ON) {
			operation = "<a href='#' onclick='powerOff();' alt='Power Off'><img src='images/cancel.png' style='width:16px;height:16px;'/></a>";
		}
		if(status == Constants.POWER_OFF) {
			operation = "<a href='#' onclick='powerOn();' alt='Power On'><img src='images/ok.png' style='width:16px;height:16px;'/></a>";
		}
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
}
