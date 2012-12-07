package com.xero.website.bean;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xero.core.bean.BaseEntity;

@Entity
@Table(name="tb_company")
public class Company extends BaseEntity{

	private static final long serialVersionUID = -521724015309535182L;
	
	private String companyName ;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	
}
