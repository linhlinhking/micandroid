/*
 * SystemInitTest.java 2008-5-16
 * 
 * Copyright 2008-2011 the original author or authors.
 * Licensed under the Apache License, Version 2.0
 */
package org.jxstar.util;


import org.jxstar.util.log.Log;
import org.jxstar.util.system.SystemInitUtil;

/**
 * 
 * 
 * @author TonyTan
 * @version 1.0, 2008-5-16
 */
public class SystemInitTest {

	public static void initSystem(String realPath) {
		String configFile = "conf/server.xml";
		
		//初始化日志对象
		String logFile = "conf/log.properties";
		Log.getInstance().init(realPath, logFile);
		
		//初始化系统对象
		SystemInitUtil.initSystem(realPath, configFile, false);	
	}
}
