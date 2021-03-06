/*
 * NodeType.java 2011-1-27
 * 
 * Copyright 2008-2011 the original author or authors.
 * Licensed under the Apache License, Version 2.0
 */
package org.jxstar.wf.define;

/**
 * 节点类型代号。
 *
 * @author TonyTan
 * @version 1.0, 2011-1-27
 */
public interface NodeType {
	public static final String NODE_START = "start";			//开始节点
	public static final String NODE_END = "end";				//结束节点
	public static final String NODE_TASK = "task";				//任务节点
	public static final String NODE_SELECT = "select";			//判断节点
	public static final String NODE_SUBPROCESS = "subprocess";	//子过程节点
	public static final String NODE_FORK = "fork";				//并发节点
	public static final String NODE_JOIN = "join";				//聚合节点
}
