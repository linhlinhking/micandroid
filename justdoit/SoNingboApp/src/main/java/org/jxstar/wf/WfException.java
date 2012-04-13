/*
 * WfException.java 2011-1-26
 * 
 * Copyright 2008-2011 the original author or authors.
 * Licensed under the Apache License, Version 2.0
 */
package org.jxstar.wf;

import java.text.MessageFormat;


/**
 * 工作流系统的异常处理类。
 *
 * @author TonyTan
 * @version 1.0, 2011-1-26
 */
public class WfException extends Exception {
	private static final long serialVersionUID = -7403854986811443994L;

	/**
	 * @param message
	 */
	public WfException(String message) {
		super(message);
	}
	
	public WfException(String message, Object ... params) {
		super(MessageFormat.format(message, params));
	}

	/**
	 * @param message
	 * @param cause
	 */
	public WfException(String message, Throwable cause) {
		super(message, cause);
	}
}
