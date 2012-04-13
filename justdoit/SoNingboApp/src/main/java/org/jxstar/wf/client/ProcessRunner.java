/*
 * ProcessRunner.java 2011-1-28
 * 
 * Copyright 2008-2011 the original author or authors.
 * Licensed under the Apache License, Version 2.0
 */
package org.jxstar.wf.client;

import java.util.List;

import org.jxstar.task.SystemTask;
import org.jxstar.task.TaskException;
import org.jxstar.util.config.SystemVar;
import org.jxstar.util.resource.JsMessage;
import org.jxstar.wf.WfException;
import org.jxstar.wf.engine.ProcessContext;
import org.jxstar.wf.engine.ProcessInstance;
import org.jxstar.wf.engine.ProcessInstanceManager;

/**
 * 过程实例处理机，主要用途是启动已经初始化的过程实例。
 *
 * @author TonyTan
 * @version 1.0, 2011-1-28
 */
public class ProcessRunner extends SystemTask {

	public void execute() throws TaskException {
		String isComplete = SystemVar.getValue("wf.instance.startup");
		if (!isComplete.equals("0")) {//"系统属性【{0}】设置过程实例创建后直接启动，处理机不需要执行！"
			_log.showDebug(JsMessage.getValue("processrunner.nostart", "wf.instance.startup"));
			return;
		}
		
		ProcessInstanceManager manager = ProcessInstanceManager.getInstance();

		try {
			List<ProcessContext> lsContext = manager.queryInitProcess();
			if (lsContext.isEmpty()) {//"没有找到初始化的过程实例！"
				_log.showDebug("--------"+ JsMessage.getValue("processrunner.noinstance"));
				return;
			}
			
			for (int i = 0, n = lsContext.size(); i < n; i++) {
				ProcessContext context = lsContext.get(i);
				
				ProcessInstance instance = context.getProcessInstance();
				instance.startup(context);
			}
		} catch (WfException e) {
			e.printStackTrace();
			throw new TaskException(e.getMessage());
		}
	}

}
