/*
 * AbstractTest.java 2009-10-5
 * 
 * Copyright 2008-2011 the original author or authors.
 * Licensed under the Apache License, Version 2.0
 */
package org.jxstar.test;

import org.jxstar.util.log.Log;
import org.jxstar.util.system.SystemInitUtil;

/**
 * 测试类的基类。
 *
 * @author TonyTan
 * @version 1.0, 2009-10-5
 */
public class AbstractTest {
	protected static String path = "D:/tomcat6/webapps/jxstar/WEB-INF/classes/";
	
	static {
	    System.out.println("..........classpath=" + path);
        String configFile = "conf/server.xml";
        
        //初始化日志对象
        String logFile = "conf/log.properties";
        Log.getInstance().init(path, logFile);
        
        //初始化系统对象
        SystemInitUtil.initSystem(path, configFile, false);   
        
		//SystemVar.REALPATH = path;
	}
}
