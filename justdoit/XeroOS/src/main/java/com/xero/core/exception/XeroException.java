package com.xero.core.exception;

public class XeroException extends RuntimeException {

	private static final long serialVersionUID = -8716293499487592906L;

	public XeroException(String message, Exception ex) {
		super(message, ex);
	}

	public XeroException(String message) {
		super(message, null);
	}

	public XeroException(String message,Throwable cause) {
		super(message,cause);
	}
}
