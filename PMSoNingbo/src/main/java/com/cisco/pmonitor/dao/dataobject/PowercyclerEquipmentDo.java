package com.cisco.pmonitor.dao.dataobject;

public class PowercyclerEquipmentDo extends BaseDo {

	private static final long serialVersionUID = 6093153871233433586L;

	private int powercyclerId;
	private int equipmentId;
	private int outlet;
	private int status = -1;
	public int getPowercyclerId() {
		return powercyclerId;
	}
	public void setPowercyclerId(int powercyclerId) {
		this.powercyclerId = powercyclerId;
	}
	public int getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(int equipmentId) {
		this.equipmentId = equipmentId;
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
	}
}
