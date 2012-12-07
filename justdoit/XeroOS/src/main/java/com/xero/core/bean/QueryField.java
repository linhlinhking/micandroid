package com.xero.core.bean;

import java.util.Date;

public class QueryField {
	
	private String field;
	
	private Date beginDate;
	
	private Date endDate;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	

}
