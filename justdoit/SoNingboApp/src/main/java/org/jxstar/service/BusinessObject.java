/*
 * BusinessObject.java 2009-6-23
 * 
 * Copyright 2008-2011 the original author or authors.
 * Licensed under the Apache License, Version 2.0
 */
package org.jxstar.service;

import java.io.Serializable;
import java.text.MessageFormat;


import org.jxstar.dao.BaseDao;
import org.jxstar.util.log.Log;

/**
 * 业务逻辑组件基类。
 *
 * @author TonyTan 2009-6-23
 */
abstract public class BusinessObject implements Serializable {
	private static final long serialVersionUID = 1L;
	//返回给服务层的提示信息
	private String _message = "";
	//返回给服务层的数据
	private String _returnData = "";
	
	//成功返回
	protected static String _returnSuccess = "true";
	//失败返回
	protected static String _returnFaild = "false";
	//系统日志对象
	protected Log _log = Log.getInstance();
	//数据库访问对象
	protected BaseDao _dao = BaseDao.getInstance();
	
	/**
	 * 设置返回给服务层的提示信息
	 * 
	 * @param sMsg - 提示信息
	 */
	protected void setMessage(String sMsg) {
		_message = sMsg;
	}
	
	/**
	 * 设置返回给服务层的提示信息，可以带参数。
	 * 
	 * @param sMsg - 提示信息
	 */
	protected void setMessage(String sMsg, Object ... param) {
		sMsg = MessageFormat.format(sMsg, param);
		_message = sMsg;
	}
	
	/**
	 * 获取返回给服务层的提示信息
	 */
	public String getMessage() {
		return _message;
	}
	
	/**
	 * 设置返回给服务层的数据
	 * 
	 * @param sData - 返回的数据，数据格式采用JSON格式
	 */
	protected void setReturnData(String sData) {
		_returnData = sData;
	}
	
	/**
	 * 获取返回给服务层的数据，数据格式采用JSON格式
	 */
	public String getReturnData() {
		return _returnData;
	}
}
