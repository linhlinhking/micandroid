package com.cisco.pmonitor.dao.dataobject;

public class UseRatioDo extends BaseDo {

	private static final long serialVersionUID = -6691207390891337114L;

	private int equipmentId;
	private int useTime;
	private int idleTime;
	private double useRatio;
	public int getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(int equipmentId) {
		this.equipmentId = equipmentId;
	}
	public int getUseTime() {
		return useTime;
	}
	public void setUseTime(int useTime) {
		this.useTime = useTime;
	}
	public int getIdleTime() {
		return idleTime;
	}
	public void setIdleTime(int idleTime) {
		this.idleTime = idleTime;
	}
	public double getUseRatio() {
		return useRatio;
	}
	public void setUseRatio(double useRatio) {
		this.useRatio = useRatio;
	}
}
