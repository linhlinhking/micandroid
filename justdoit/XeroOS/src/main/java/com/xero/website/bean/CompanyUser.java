package com.xero.website.bean;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xero.core.bean.BaseEntity;

@Entity
@Table(name="tb_company_user")
public class CompanyUser extends BaseEntity{

	private static final long serialVersionUID = 7334785154022596111L;
	
	private Integer userId ;
	
	private Integer companyId ;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	
	

}
