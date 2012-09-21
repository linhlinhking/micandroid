package com.cisco.pmonitor.dao.dataobject;

/**
 * 
 * @author shuaizha
 *
 */
public class DepartmentDo extends BaseDo {

	private static final long serialVersionUID = -5569201665252998220L;

	private String name;
	private String description;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
