package com.cisco.pmonitor.dao.query;

public class DepartmentQuery extends BaseQuery {

	private static final long serialVersionUID = 3545482999897318885L;

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
