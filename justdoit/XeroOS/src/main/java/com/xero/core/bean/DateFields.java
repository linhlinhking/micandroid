package com.xero.core.bean;

import java.io.Serializable;
import java.util.List;


public class DateFields implements Serializable{

	private static final long serialVersionUID = 8214875827779023406L;
	
	private List<QueryField> queryFields;

	public List<QueryField> getQueryFields() {
		return queryFields;
	}

	public void setQueryFields(List<QueryField> queryFields) {
		this.queryFields = queryFields;
	}
}
