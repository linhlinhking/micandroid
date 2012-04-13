/*
 * TaskException.java 2009-6-30
 * 
 * Copyright 2008-2011 the original author or authors.
 * Licensed under the Apache License, Version 2.0
 */
package org.jxstar.task;

import java.text.MessageFormat;

/**
 * 任务执行抛出的异常
 *
 * @author TonyTan
 * @version 1.0, 2009-6-30
 */
public class TaskException extends Exception {
	private static final long serialVersionUID = 2451599531477765055L;
	
	public TaskException(String message) {
		super(message);
	}
	
	public TaskException(String message, Object ... params) {
		super(MessageFormat.format(message, params));
	}

	/**
	 * @param message
	 * @param cause
	 */
	public TaskException(String message, Throwable cause) {
		super(message, cause);
	}
}
