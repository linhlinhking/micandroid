package com.xero.core.Response;

public class ResponseEntity<E extends Object> {

	protected Boolean result;

	protected E data;
	
	protected String json ;
	
	public ResponseEntity(Boolean result){
		this.result = result ;
	}

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	
	
	
}
