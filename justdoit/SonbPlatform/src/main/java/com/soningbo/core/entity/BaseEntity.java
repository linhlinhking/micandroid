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
	
	@Column(length = 64)
	private String create_person ;
	
	@Column(length = 64)
	private String update_person ;

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

	public String getCreate_person() {
		return create_person;
	}

	public void setCreate_person(String create_person) {
		this.create_person = create_person;
	}

	public String getUpdate_person() {
		return update_person;
	}

	public void setUpdate_person(String update_person) {
		this.update_person = update_person;
	}
	
	

}
