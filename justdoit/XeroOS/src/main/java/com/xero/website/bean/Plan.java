package com.xero.website.bean;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xero.core.bean.BaseEntity;

@Entity
@Table(name="tb_plan")
public class Plan extends BaseEntity{

	private static final long serialVersionUID = 6006416133871822301L;
	
	private String planName ;

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}
	
}
