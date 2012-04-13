/*
 * SystemTask.java 2011-1-13
 * 
 * Copyright 2008-2011 the original author or authors.
 * Licensed under the Apache License, Version 2.0
 */
package org.jxstar.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


import org.jxstar.dao.BaseDao;
import org.jxstar.dao.DaoParam;
import org.jxstar.dao.transaction.TransactionException;
import org.jxstar.dao.transaction.TransactionManager;
import org.jxstar.util.DateUtil;
import org.jxstar.util.MapUtil;
import org.jxstar.util.SystemUtil;
import org.jxstar.util.factory.FactoryUtil;
import org.jxstar.util.factory.SystemFactory;
import org.jxstar.util.key.KeyCreator;
import org.jxstar.util.log.Log;

/**
 * 后台任务基类，支持事务处理。
 *
 * @author TonyTan
 * @version 1.0, 2011-1-13
 */
public abstract class SystemTask extends Thread {
	protected Log _log = Log.getInstance();
	//任务执行参数
	protected Map<String,String> _taskParam = null;
	
	//数据库访问对象
	protected BaseDao _dao = BaseDao.getInstance();
	//检查间隔时间，单位秒
	private int _checkTime = 60;
	//检查开始时间
	private Date _startTime = null;
	//任务设置信息
	private Map<String,String> _taskConfig = null;
	//是否禁用
	private boolean _disabled = false;
	
	/**
	 * 根据后台任务设置信息初始化任务
	 * @param checkTime --检查间隔时间，单位秒
	 * @param mpTask --任务设置信息
	 */
	public void init(int checkTime, Map<String,String> mpTask) {
		_checkTime = checkTime;
		_taskConfig = mpTask;
		_startTime = new Date();
		
		//取该后台任务的参数
		String taskId = mpTask.get("task_id");
		_taskParam = getTaskParam(taskId);
		
		String taskName = mpTask.get("task_name");
		_log.showDebug("init task: {0} at {1}...", taskName, DateUtil.getTodaySec());
	}
	
	/**
	 * 设置是否禁用当前线程
	 * @param disabled -- 是否禁用
	 */
	public void setDisabled(boolean disabled) {
		_disabled = disabled;
	}
	
	/**
	 * 系统任务线程执行方法
	 */
	public void run() {
		String taskName = _taskConfig.get("task_name");
		TransactionManager tranMng = (TransactionManager) SystemFactory.
										createSystemObject("TransactionManager");
		
		while(true) {
			//等待检查间隔时间
			try {
				sleep(_checkTime*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}
			
			//如果中断了就退出
			if (_disabled) {
				_log.showDebug("disable task: {0} is disabled...", taskName);
				return;
			}
			
			boolean isvalid = isValid();
			String today = DateUtil.getTodaySec();
			_log.showDebug("check task: {0} at {1}, is executed: {2}...", taskName, today, isvalid);
			
			//检查执行计划是否有效
			if (isvalid) {
				String errorMsg = "";
				_startTime = new Date();
				
				//开始一个事务
				tranMng.startTran();
				try {
					execute();
					
					tranMng.commitTran();
				} catch (Exception e) {
					try {
						tranMng.rollbackTran();
					} catch (TransactionException e2) {
						e2.printStackTrace();
					}
					
					//记载运行时错误到任务日志中
					_log.showError(e);
					errorMsg = e.getMessage();
				}
				
				//记录后台任务执行日志
				if (!writeTaskLog(_startTime, errorMsg)) {
					_log.showDebug("write task: {0} log failed!!", taskName);
				}
			}
		}
	}
	
	/**
	 * 实现类实现执行内容，执行过程的异常将被系统捕获。
	 * @throws TaskException
	 */
	public abstract void execute() throws TaskException;
	
	/**
	 * 检查执行计划是否到期
	 * @return
	 */
	private boolean isValid() {
		String splan = _taskConfig.get("task_plan");
		DatePlan plan = new DatePlan();
		
		boolean bret = false;
		try {
			bret = plan.isValid(_startTime, splan);
		} catch (TaskException e) {
			e.printStackTrace();
		}
		
		return bret;
	}
	
	/**
	 * 记录后台任务执行日志
	 * @param startTime -- 开始时间
	 * @param errorMsg -- 错误信息
	 */
	private boolean writeTaskLog(Date startTime, String errorMsg) {
		String taskId = _taskConfig.get("task_id");
		//当前时间
		String curTime = DateUtil.getTodaySec();
		
		//修改上次运行日期
		String usql = "update task_base set run_date = ? where task_id = ?";
		DaoParam uparam = _dao.createParam(usql);
		uparam.addDateValue(curTime);
		uparam.addStringValue(taskId);
		if (!_dao.update(uparam)) {
			_log.showDebug("update task run date failed!!");
			return false;
		}
		
		//是否记录日志
		String hasLog = _taskConfig.get("has_log");
		if (!hasLog.equals("1")) {
			return true;
		}
		
		//操作保留日志条数，则需要删除日志
		String logNum = _taskConfig.get("log_num");
		int hasNum = getLogNum(taskId);
		if (logNum.length() > 0) {
			int logSize = Integer.parseInt(logNum);
			if (hasNum > logSize) {
				delTaskLog(taskId);
			}
		}
		
		//新增运行日志
		String sql = "insert into task_log(log_id, task_id, start_date, end_date, server_name, server_ip, run_error) "+
					 "values(?, ?, ?, ?, ?, ?, ?)";
		
		DaoParam param = _dao.createParam(sql);
		
		String logId = KeyCreator.getInstance().createKey("task_log");
		String sTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTime);
		String serverName = SystemUtil.getHostName();
		String serverIp = SystemUtil.getIPAddress();
		
		param.addStringValue(logId);
		param.addStringValue(taskId);
		param.addDateValue(sTime);
		param.addDateValue(curTime);
		param.addStringValue(serverName);
		param.addStringValue(serverIp);
		param.addStringValue(errorMsg);
		
		return _dao.update(param);
	}
	
	/**
	 * 删除任务执行日志
	 * @param taskId -- 后台任务ID
	 */
	private boolean delTaskLog(String taskId) {
		String sql = "delete from task_log where task_id = ?";
		DaoParam param = _dao.createParam(sql);
		param.addStringValue(taskId);
		return _dao.update(param);
	}
	
	/**
	 * 取任务日志记录数
	 * @param taskId -- 后台任务ID
	 * @return
	 */
	private int getLogNum(String taskId) {
		String sql = "select count(*) as cnt from task_log where task_id = ?";
		DaoParam param = _dao.createParam(sql);
		param.addStringValue(taskId);
		
		return MapUtil.hasRecodNum(_dao.queryMap(param));
	}
	
	/**
	 * 取后台任务参数
	 * @param taskId -- 任务ID
	 * @return
	 */
	private Map<String,String> getTaskParam(String taskId) {
		Map<String,String> mpRet = FactoryUtil.newMap();
		
		String sql = "select param_name, param_value from task_param where task_id = ?";
		DaoParam param = _dao.createParam(sql);
		param.addStringValue(taskId);
		
		List<Map<String,String>> lsParam = _dao.query(param);
		if (lsParam == null || lsParam.isEmpty()) {
			return mpRet;
		}
		
		for (int i = 0, n = lsParam.size(); i < n; i++) {
			Map<String,String> mpParam = lsParam.get(i);
			
			String paramName = mpParam.get("param_name");
			String paramValue = mpParam.get("param_value");
			
			mpRet.put(paramName, paramValue);
		}
		
		return mpRet;
	}
}
