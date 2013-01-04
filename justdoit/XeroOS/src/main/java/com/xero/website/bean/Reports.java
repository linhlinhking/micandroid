package com.xero.website.bean;

import java.io.Serializable;

public class Reports implements Serializable {

	private static final long serialVersionUID = 3159885838055088968L;

	private String supplier;

	private Integer daysOverdue;

	private String projectName;

	private String poNumber;

	private String telephone;

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public Integer getDaysOverdue() {
		return daysOverdue;
	}

	public void setDaysOverdue(Integer daysOverdue) {
		this.daysOverdue = daysOverdue;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	
	

}
