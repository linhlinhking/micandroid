package com.xero.website.bean;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xero.core.bean.BaseEntity;

@Entity
@Table(name="tb_group")
public class Group extends BaseEntity{

	private static final long serialVersionUID = 7894743168637969622L;
	
	private String groupName ;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	

}
