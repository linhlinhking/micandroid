/*
 * ProcessContext.java 2011-1-27
 * 
 * Copyright 2008-2011 the original author or authors.
 * Licensed under the Apache License, Version 2.0
 */
package org.jxstar.wf.engine;

/**
 * 过程执行依赖的上下文对象。
 *
 * @author TonyTan
 * @version 1.0, 2011-1-27
 */
public class ProcessContext {
	//过程执行状态标记
	private Token token; 
	//当前会话的任务实例
	private TaskInstance taskInstance;
	//当前会话的过程实例
	private ProcessInstance processInstance;
	
	public ProcessInstance getProcessInstance() {
		return processInstance;
	}
	public void setProcessInstance(ProcessInstance processInstance) {
		this.processInstance = processInstance;
	}
	public TaskInstance getTaskInstance() {
		return taskInstance;
	}
	public void setTaskInstance(TaskInstance taskInstance) {
		this.taskInstance = taskInstance;
	}
	public Token getToken() {
		return token;
	}
	public void setToken(Token token) {
		this.token = token;
	}

}
