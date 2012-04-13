/*
 * ProcessClientBO.java 2011-1-28
 * 
 * Copyright 2008-2011 the original author or authors.
 * Licensed under the Apache License, Version 2.0
 */
package org.jxstar.wf.client;

import java.util.Map;

import org.jxstar.control.action.RequestContext;
import org.jxstar.service.BusinessObject;
import org.jxstar.util.DateUtil;
import org.jxstar.util.MapUtil;
import org.jxstar.util.config.SystemVar;
import org.jxstar.util.resource.JsMessage;
import org.jxstar.util.resource.JsParam;
import org.jxstar.wf.WfException;
import org.jxstar.wf.define.TaskNode;
import org.jxstar.wf.define.WfDefineDao;
import org.jxstar.wf.engine.ProcessContext;
import org.jxstar.wf.engine.ProcessInstance;
import org.jxstar.wf.engine.ProcessInstanceManager;
import org.jxstar.wf.engine.TaskInstance;
import org.jxstar.wf.engine.TaskInstanceDao;
import org.jxstar.wf.engine.Token;
import org.jxstar.wf.engine.TokenDao;
import org.jxstar.wf.invoke.StatusCode;
import org.jxstar.wf.util.ProcessUtil;

/**
 * 过程实例客户端，是应用程序与工作组件交互对象。
 *
 * @author TonyTan
 * @version 1.0, 2011-1-28
 */
public class ProcessClientBO extends BusinessObject {
	private static final long serialVersionUID = -6406373057929422076L;
	
	/**
	 * 创建过程实例，在签字后事件中调用。
	 * @param request -- 服务请求对象
	 * @return
	 */
	public String createProcess(RequestContext request) {
		String funId = request.getFunID();
		if (!ProcessUtil.isNeedWf(funId)) {
			_log.showDebug("【{0}】功能定义中的“有效记录值”不是“审批通过”，不会执行流程启动检查！", funId);
			return _returnSuccess;
		}
		
		String[] dataIds = request.getRequestValues(JsParam.KEYID);
		if (dataIds == null || dataIds.length == 0 ) {
			//"没有找到审批记录ID！"
			setMessage(JsMessage.getValue("processclientbo.noid"));
			return _returnFaild;
		}
		
		//取当前用户信息
		Map<String,String> mpUser = request.getUserInfo();
		if (mpUser == null || mpUser.isEmpty()) {//"没有找到审批用户信息！"
			setMessage(JsMessage.getValue("processclientbo.nouser"));
			return _returnFaild;
		}
		
		return createProcess(funId, dataIds, mpUser);
	}

	/**
	 * 创建过程实例，在签字后事件中调用，处理内容有：
	 * 1、先检查当前功能是否定义有效审批流程，并取过程定义信息；
	 * 2、创建过程实例对象；
	 * 3、调用过程实例的创建方法；
	 * 
	 * @param funId -- 功能ID
	 * @param dataIds -- 记录主键值数组，支持多条记录签字
	 * @param mpUser -- 当前用户
	 * @return
	 */
	private String createProcess(String funId, String[] dataIds, Map<String,String> mpUser) {
		//先检查当前功能是否定义有效审批流程，并取过程定义信息
		WfDefineDao defineDao = WfDefineDao.getInstance();
		Map<String,String> mpDefine = defineDao.queryProcessByFunId(funId);
		if (mpDefine.isEmpty()) {//"功能【{0}】没有定义审批流程！"
			_log.showDebug(JsMessage.getValue("processclientbo.noprocess"), funId);
			return _returnSuccess;
		}
		
		for (int i = 0, n = dataIds.length; i < n; i++) {
			String dataId = dataIds[i];
			//取应用数据
			Map<String,String> mpData = ProcessUtil.queryFunData(funId, dataId);
			if (mpData.isEmpty()) {//"没有找到应用数据！"
				setMessage(JsMessage.getValue("processclientbo.nodata"));
				return _returnFaild;
			}
			
			//创建过程实例对象
			ProcessInstanceManager manager = ProcessInstanceManager.getInstance();
			ProcessInstance instance = manager.createProcess(dataId, mpUser, mpData, mpDefine);
			
			//调用过程实例的创建方法
			try {
				instance.create();
				
				//是否过程实例创建后直接启动，缺省是直接启动
				String isStartup = SystemVar.getValue("wf.instance.startup");
				if (!isStartup.equals("0")) {
					ProcessContext context = manager.createInitContext(instance);
					instance.startup(context);
				}
			} catch (WfException e) {
				_log.showError(e);
				setMessage(e.getMessage());
				return _returnFaild;
			}
		}
		
		return _returnSuccess;
	}
	
	/**
	 * 执行过程实例，在用户收到分配消息后，对任务操作后执行，处理内容有：
	 * 1、先创建过程实例上下文对象，包括：过程实例、标记对象、任务实例；
	 * 2、然后执行过程实例；
	 * 
	 * 支持同时审批多条件记录，都采用相同的审批意见。
	 * 
	 * @param request -- 服务请求对象
	 * @return
	 */
	public String executeProcess(RequestContext request) {
		try {
			ProcessContext[] contexts = toProcessContext(request);
			
			for (int i = 0, n = contexts.length; i < n; i++) {
				ProcessInstance instance = contexts[i].getProcessInstance();
				instance.execute(contexts[i]);
				
				//是否任务实例执行后直接完成，缺省是直接完成
				String isComplete = SystemVar.getValue("wf.task.complete");
				if (!isComplete.equals("0")) {
					TaskInstance task = contexts[i].getTaskInstance();
					task.complete(contexts[i]);
				}
			}
		} catch (WfException e) {
			_log.showError(e);
			setMessage(e.getMessage());
			return _returnFaild;
		}
		
		return _returnSuccess;
	}
	
	/**
	 * 任务实例完成，在task_3事件中调用。
	 * @param request
	 * @return
	 */
	public String completeTask(RequestContext request) {//"开始执行【{0}】事件"
		_log.showDebug(JsMessage.getValue("processclientbo.doevent", "task_3"));
		String funId = request.getRequestValue("check_funid");
		String dataId = request.getRequestValue("data_id");
		String taskId = request.getRequestValue("task_id");
		String checkType = request.getRequestValue("check_type");
		//"审批意见："
		_log.showDebug(JsMessage.getValue("processclientbo.advice") + 
				checkType + ";" + funId + ";" + dataId + ";" + taskId);
		
		return _returnSuccess;
	}
	
	/**
	 * 启动过程实例，在process_1事件中调用。
	 * @param request -- 请求对象
	 * @return
	 */
	public String startupProcess(RequestContext request) {//"开始执行【{0}】事件"
		_log.showDebug(JsMessage.getValue("processclientbo.doevent", "process_1"));
		//修改记录状态为审批中
		String audit = "2";
		String funId = request.getRequestValue("check_funid");
		String dataId = request.getRequestValue("data_id");
		
		if (!ProcessUtil.updateFunAudit(funId, dataId, audit)) {
			//"更新【{0}】的【{1}】记录的状态为【{2}】失败！"
			setMessage(JsMessage.getValue("processclientbo.uperror"), 
					funId, dataId, audit);
			return _returnFaild;
		}
		
		return _returnSuccess;
	}
	
	/**
	 * 正常完成过程实例，在process_3事件中调用。
	 * @param request -- 请求对象
	 * @return
	 */
	public String completeProcess(RequestContext request) {//"开始执行【{0}】事件"
		_log.showDebug(JsMessage.getValue("processclientbo.doevent", "process_3"));
		//修改记录状态为审批通过
		String audit = "3";
		String funId = request.getRequestValue("check_funid");
		String dataId = request.getRequestValue("data_id");

		if (!ProcessUtil.updateFunAudit(funId, dataId, audit)) {
			//"更新【{0}】的【{1}】记录的状态为【{2}】失败！"
			setMessage(JsMessage.getValue("processclientbo.uperror"), 
					funId, dataId, audit);
			return _returnFaild;
		}
		
		//给编辑人发消息通知
		
		
		return _returnSuccess;
	}
	
	/**
	 * 终止过程实例，在process_7事件中调用。
	 * @param request -- 请求对象
	 * @return
	 */
	public String terminateProcess(RequestContext request) {//"开始执行【{0}】事件"
		_log.showDebug(JsMessage.getValue("processclientbo.doevent", "process_7"));
		//根据审批意见，修改记录状态
		String funId = request.getRequestValue("check_funid");
		String dataId = request.getRequestValue("data_id");
		String checkType = request.getRequestValue("check_type");
		
		if (!updateAudit(funId, dataId, checkType)) {
			//"更新【{0}】的【{1}】记录的状态失败！"
			setMessage(JsMessage.getValue("processclientbo.staterror"), 
					funId, dataId);
			return _returnFaild;
		}
		
		//根据审批意见，给历史审批人发送通知消息
		
		
		return _returnSuccess;
	}
	
	/**
	 * 修改业务记录状态值，在过程实例结束时调用。
	 * @param funId
	 * @param dataId
	 * @param checkType
	 * @return
	 */
	private boolean updateAudit(String funId, String dataId, String checkType) {
		//已注销，发生异常了
		String audit = "7";
		if (checkType.equals(TaskNode.RETURNEDIT)) {
		//退回编辑人
			audit = "6";
		} else if (checkType.equals(TaskNode.DISAGREE)) {
		//已否决
			audit = "4";
		} else if (checkType.equals(TaskNode.RETURN)) {
		//退回
			audit = "5";
		} 
		
		return ProcessUtil.updateFunAudit(funId, dataId, audit);
	}
	
	/**
	 * 服务请求对象转换为过程实例上下文对象，创建：过程实例、标记对象、任务实例。
	 * @param request -- 服务请求对象
	 * @return
	 */
	private ProcessContext[] toProcessContext(RequestContext request) throws WfException {
		//恢复任务实例对象
		TaskInstance[] tasks = restoreTask(request);
		
		ProcessInstanceManager manager = ProcessInstanceManager.getInstance();
		
		//创建多个上下文对象
		ProcessContext[] retContexts = new ProcessContext[tasks.length];
		for (int i = 0, n = tasks.length; i < n; i++) {
			//检验任务状态是否为初始化
			String runState = tasks[i].getRunState();
			if (!runState.equals(StatusCode.TASK_CREATED)) {
				//"分配任务【{0}】已执行完成，不需要处理！"
				throw new WfException(JsMessage.getValue("processclientbo.donot"), 
						tasks[i].getTaskId());
			}
			
			//恢复过程实例对象
			String instanceId = tasks[i].getInstanceId();
			ProcessInstance instance = manager.restoreInstance(instanceId);
			
			//恢复过程标记对象
			TokenDao tokenDao = TokenDao.getInstance();
			String nodeId = tasks[i].getNodeId();
			Token token = tokenDao.restoreToken(instanceId, nodeId);
			
			//创建过程实例上下文对象
			ProcessContext context = new ProcessContext();
			context.setToken(token);
			context.setTaskInstance(tasks[i]);
			context.setProcessInstance(instance);
			
			retContexts[i] = context;
		}
		
		return retContexts;
	}
	
	/**
	 * 根据服务请求对象恢复任务实例对象。
	 * @param request -- 服务请求对象
	 * @return
	 */
	private TaskInstance[] restoreTask(RequestContext request) throws WfException {
		//取当前用户信息
		Map<String,String> mpUser = request.getUserInfo();
		if (mpUser == null || mpUser.isEmpty()) {//"没有找到审批用户信息！"
			throw new WfException(JsMessage.getValue("processclientbo.nouser"));
		}
		String userId = mpUser.get("user_id");
		
		//取审批执行信息
		Map<String,String> mpCheck = request.getUserInfo();
		mpCheck.put("check_type", request.getRequestValue("check_type"));
		mpCheck.put("check_desc", request.getRequestValue("check_desc"));
		mpCheck.put("next_nodeid", request.getRequestValue("next_nodeid"));
		mpCheck.put("next_userid", request.getRequestValue("next_userid"));
		mpCheck.put("next_user", request.getRequestValue("next_user"));
		mpCheck.put("deal_desc", request.getRequestValue("deal_desc"));
		
		TaskInstanceDao taskDao = TaskInstanceDao.getInstance();
		
		//取任务ID，如果指定了任务ID，则说明是审批一条记录
		String taskId = request.getRequestValue("taskid");
		if (taskId.length() > 0) {
			Map<String,String> mpTask = taskDao.queryTask(taskId);
			if (mpTask.isEmpty()) {//"没有找到【{0}】任务实例数据！"
				throw new WfException(JsMessage.getValue("processclientbo.notask"), taskId);
			}
			
			TaskInstance task = restoreTask(mpTask, mpCheck, mpUser);
			return new TaskInstance[]{task};
		}
		
		//取审批功能ID与数据ID
		String funId = request.getRequestValue("check_funid");
		String[] dataIds = request.getRequestValues(JsParam.KEYID);
		if (dataIds == null || dataIds.length == 0 ) {//"没有找到审批记录ID！"
			throw new WfException(JsMessage.getValue("processclientbo.noid"));
		}
		
		//创建多个任务实例对象
		TaskInstance[] retTasks = new TaskInstance[dataIds.length];
		for (int i = 0, n = dataIds.length; i < n; i++) {
			//根据功能ID与数据ID查询任务实例数据
			Map<String,String> mpTask = taskDao.queryTaskByAssign(funId, dataIds[i], userId);
			if (mpTask.isEmpty()) {//"分配任务已执行完成，不需要处理！"
				throw new WfException(JsMessage.getValue("processclientbo.taskend"));
			}//没有找到【{0}】功能与【{1}】数据的任务实例数据, funId, dataIds[i]
			
			retTasks[i] = restoreTask(mpTask, mpCheck, mpUser);
		}

		return retTasks;
	}
	
	/**
	 * 根据任务信息创建实例对象。
	 * @param mpTask -- 初始化的任务信息
	 * @param request -- 取任务完成信息
	 * @return
	 * @throws WfException
	 */
	private TaskInstance restoreTask(Map<String,String> mpTask, 
			Map<String,String> mpCheck,
			Map<String,String> mpUser) throws WfException {
		TaskInstance task = new TaskInstance();
		//恢复任务实例的基础信息
		task.setProcessId(MapUtil.getValue(mpTask, "process_id"));
		task.setNodeId(MapUtil.getValue(mpTask, "node_id"));
		task.setNodeTitle(MapUtil.getValue(mpTask, "node_title"));
		task.setFunId(MapUtil.getValue(mpTask, "fun_id"));
		task.setDataId(MapUtil.getValue(mpTask, "data_id"));
		task.setInstanceId(MapUtil.getValue(mpTask, "instance_id"));
		task.setTaskId(MapUtil.getValue(mpTask, "task_id"));
		task.setRunState(MapUtil.getValue(mpTask, "run_state"));
		task.setStartDate(MapUtil.getValue(mpTask, "start_date"));
		task.setLimitDate(MapUtil.getValue(mpTask, "limit_date"));
		task.setEndDate(MapUtil.getValue(mpTask, "end_date"));
		task.setTaskDesc(MapUtil.getValue(mpTask, "task_desc"));
		task.setHasEmail(MapUtil.getValue(mpTask, "has_email"));
		task.setIsTimeout(MapUtil.getValue(mpTask, "is_timeout"));
		//设置任务执行信息
		task.setCheckUserId(MapUtil.getValue(mpUser, "user_id"));
		task.setCheckUserName(MapUtil.getValue(mpUser, "user_name"));
		task.setCheckDate(DateUtil.getTodaySec());
		task.setCheckType(MapUtil.getValue(mpCheck, "check_type"));
		task.setCheckDesc(MapUtil.getValue(mpCheck, "check_desc"));
		task.setNextNodeId(MapUtil.getValue(mpCheck, "next_nodeid"));
		task.setNextUserId(MapUtil.getValue(mpCheck, "next_userid"));
		task.setNextUser(MapUtil.getValue(mpCheck, "next_user"));
		task.setDealDesc(MapUtil.getValue(mpCheck, "deal_desc"));
		
		return task;
	}
}
