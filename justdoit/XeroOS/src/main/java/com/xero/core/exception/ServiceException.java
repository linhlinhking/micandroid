package com.xero.core.exception;

public class ServiceException extends XeroException {

	private static final long serialVersionUID = -5703380816942055603L;

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String message, Exception ex) {
		super(message, ex);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(Throwable cause) {
		super(null, cause);
	}

}
