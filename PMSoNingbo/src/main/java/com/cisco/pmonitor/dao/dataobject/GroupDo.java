package com.cisco.pmonitor.dao.dataobject;

/**
 * 
 * @author shuaizha
 *
 */
public class GroupDo extends BaseDo {

	private static final long serialVersionUID = 6597081369281009448L;
	
	private String name;
	private String description;
	private String owner;
	private int roomId;
	
	public String getName() {
		return name == null ? "" : name;
	}
	public void setName(String name) {
		this.name = name;
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
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

}
