package com.xero.payment.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.xero.core.bean.BaseEntity;

@Entity
@Table(name = "tb_transaction_log")
public class TransactionLogger extends BaseEntity{

	private static final long serialVersionUID = -2006313709429884800L;
	
	private Integer companyId ;
	
	private Integer userId ;
	
	@Lob
	@Column(name = "res_xml")
	private String txn ;

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getTxn() {
		return txn;
	}

	public void setTxn(String txn) {
		this.txn = txn;
	}
	
	

}
