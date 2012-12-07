package com.xero.core.Response;

import java.util.List;

public class ResponseCollection<E extends Object> {

	protected Boolean result;
	
	protected List<E> data;
	
	protected String message ;
	
	public ResponseCollection(Boolean result){
		this.result = result ;
	}

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	public List<E> getData() {
		return data;
	}

	public void setData(List<E> data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
}
