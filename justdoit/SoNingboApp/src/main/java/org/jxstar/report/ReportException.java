/*
 * ReportException.java 2010-11-11
 * 
 * Copyright 2008-2011 the original author or authors.
 * Licensed under the Apache License, Version 2.0
 */
package org.jxstar.report;

import java.text.MessageFormat;

/**
 * 报表异常对象。
 *
 * @author TonyTan
 * @version 1.0, 2010-11-11
 */
public class ReportException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 */
	public ReportException(String message) {
		super(message);
	}
	
	public ReportException(String message, Object ... params) {
		super(MessageFormat.format(message, params));
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ReportException(String message, Throwable cause) {
		super(message, cause);
	}
}
