package com.xero.website.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xero.core.bean.BaseEntity;

@Entity
@Table(name="tb_company")
public class Company extends BaseEntity{

	private static final long serialVersionUID = -521724015309535182L;
	
	private String companyName ;
	
	private Integer planId ;
	
	private Date expiredDate ;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public Date getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}
	
	
	
}
