/*
 * ActionException.java 2010-11-16
 * 
 * Copyright 2008-2011 the original author or authors.
 * Licensed under the Apache License, Version 2.0
 */
package org.jxstar.control.action;

import java.text.MessageFormat;

/**
 * Action执行异常。
 *
 * @author TonyTan
 * @version 1.0, 2010-11-16
 */
public class ActionException extends Exception {
	private static final long serialVersionUID = 7137119381351052540L;

	/**
	 * @param message
	 */
	public ActionException(String message) {
		super(message);
	}
	
	public ActionException(String message, Object ... params) {
		super(MessageFormat.format(message, params));
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ActionException(String message, Throwable cause) {
		super(message, cause);
	}
}
