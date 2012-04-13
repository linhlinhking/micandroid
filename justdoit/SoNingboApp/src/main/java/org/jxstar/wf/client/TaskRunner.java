/*
 * TaskRunner.java 2011-1-28
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
import org.jxstar.wf.engine.TaskInstance;
import org.jxstar.wf.engine.TaskInstanceManager;

/**
 * 任务实例处理机，主要用途是完成已经执行的任务实例。
 *
 * @author TonyTan
 * @version 1.0, 2011-1-28
 */
public class TaskRunner extends SystemTask {

	public void execute() throws TaskException {
		String isComplete = SystemVar.getValue("wf.task.complete");
		if (!isComplete.equals("0")) {//"系统属性【{0}】设置任务实例执行后直接完成，处理机不需要执行！"
			_log.showDebug(JsMessage.getValue("taskrunner.nostart", "wf.task.complete"));
			return;
		}
		
		TaskInstanceManager manager = TaskInstanceManager.getInstance();

		try {
			List<ProcessContext> lsContext = manager.queryDoneTask();
			if (lsContext.isEmpty()) {//没有找到已执行的任务实例！
				_log.showDebug("--------"+JsMessage.getValue("taskrunner.notask"));
				return;
			}
			
			for (int i = 0, n = lsContext.size(); i < n; i++) {
				ProcessContext context = lsContext.get(i);
				
				TaskInstance task = context.getTaskInstance();
				task.complete(context);
			}
		} catch (WfException e) {
			e.printStackTrace();
			throw new TaskException(e.getMessage());
		}
	}

}
