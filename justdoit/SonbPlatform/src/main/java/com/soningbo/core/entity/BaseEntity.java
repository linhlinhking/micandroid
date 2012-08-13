package com.soningbo.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.compass.annotations.SearchableProperty;
import org.compass.annotations.Store;

@MappedSuperclass
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = -7625600897338869023L;

	private String md5Value;

	@SearchableProperty(store = Store.YES)
	@Column(updatable = false)
	private Date createDate;

	@SearchableProperty(store = Store.YES)
	private Date updateDate;

	public String getMd5Value() {
		return md5Value;
	}

	public void setMd5Value(String md5Value) {
		this.md5Value = md5Value;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
