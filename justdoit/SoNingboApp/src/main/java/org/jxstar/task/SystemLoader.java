/*
 * SystemLoader.java 2009-10-31
 * 
 * Copyright 2008-2011 the original author or authors.
 * Licensed under the Apache License, Version 2.0
 */
package org.jxstar.task;

import java.util.Map;

import org.jxstar.util.factory.FactoryUtil;
import org.jxstar.util.log.Log;

/**
 * 系统任务加载基类，加载器为单例对象。
 *
 * @author TonyTan
 * @version 1.0, 2009-10-31
 */
public abstract class SystemLoader {
	protected Log _log = Log.getInstance();
	protected Map<String,String> _initParam = FactoryUtil.newMap();
	
	/**
	 * 初始化参数，调用加载的任务内容。
	 */
	public void execute(Map<String,String> param) {
		_initParam = param;
		
		load();
	}

	/**
	 * 在实现类定义加载的内容。
	 */
	protected abstract void load();
}
