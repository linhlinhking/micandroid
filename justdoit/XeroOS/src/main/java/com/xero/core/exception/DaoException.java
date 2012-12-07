package com.xero.core.exception;

public class DaoException extends XeroException {

	private static final long serialVersionUID = -8707237016775608982L;

	public DaoException(String message) {
		super(message);
	}

	public DaoException(String message, Exception ex) {
		super(message, ex);
	}

	public DaoException(Throwable cause) {
		super(null,cause);
	}
	
	public DaoException(String message,Throwable cause) {
		super(message,cause);
	}
}
