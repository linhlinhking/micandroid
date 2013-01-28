package com.xero.website.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xero.core.bean.BaseEntity;

@Entity
@Table(name = "tb_project")
public class Project extends BaseEntity{

	private static final long serialVersionUID = -2407424834738966387L;
	
	public static final String PO_NUMBER = "poNumber" ;
	
	private String projectName ;
	
	private String poNumber ;
	
	private Date startDate ;
	
	private Date endDate ;
	
	private String customerId ;
	
	private String customerName ;
	
	private String status ;
	
	private Integer userId ;
	
	private Integer companyId ;
	
	private double rate ;
	
	
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	
}
