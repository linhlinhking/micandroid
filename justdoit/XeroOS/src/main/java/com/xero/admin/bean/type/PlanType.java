package com.xero.admin.bean.type;

public enum PlanType {

	FREE(1),FIVEUSERS(2),UNLIMITED(3);
	
	private  Integer id ;
	
	PlanType(Integer id){
		this.id = id ;
	}
	
	public void setId(Integer id){
		this.id = id ;
	}

	public Integer getId() {
		return id;
	}
	
}
