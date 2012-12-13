package com.xero.core.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;

@MappedSuperclass
public class BaseEntity  implements Serializable{

	private static final long serialVersionUID = 8811816392980129330L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id; 
	
	protected boolean deleted = false ;
	
	protected Date createDateTime ;
	
	protected Date updateDateTime ;
	
	@JsonIgnore
	@Transient
	protected DateFields dateFields;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public Date getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(Date updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public DateFields getDateFields() {
		return dateFields;
	}

	public void setDateFields(DateFields dateFields) {
		this.dateFields = dateFields;
	}
	
	
	
}
