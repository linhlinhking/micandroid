/*
 * DmException.java 2010-12-17
 * 
 * Copyright 2008-2011 the original author or authors.
 * Licensed under the Apache License, Version 2.0
 */
package org.jxstar.dm;

import java.text.MessageFormat;

/**
 * 数据库配置异常。
 *
 * @author TonyTan
 * @version 1.0, 2010-12-17
 */
public class DmException extends Exception {
	private static final long serialVersionUID = 3106828241744138978L;

	/**
	 * @param message
	 */
	public DmException(String message) {
		super(message);
	}
	
	public DmException(String message, Object ... params) {
		super(MessageFormat.format(message, params));
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DmException(String message, Throwable cause) {
		super(message, cause);
	}
}
