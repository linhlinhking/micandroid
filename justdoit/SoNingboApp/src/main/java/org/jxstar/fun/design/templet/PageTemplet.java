/*
 * PageTemplet.java 2009-9-27
 * 
 * Copyright 2008-2011 the original author or authors.
 * Licensed under the Apache License, Version 2.0
 */
package org.jxstar.fun.design.templet;

import java.util.Map;

import org.jxstar.util.FileUtil;
import org.jxstar.util.factory.FactoryUtil;


/**
 * 页面模板文件，在系统启动时加载。
 *
 * @author TonyTan
 * @version 1.0, 2009-9-27
 */
public class PageTemplet {
	private static PageTemplet instance = new PageTemplet();
	private static Map<String,String> _mpContent = FactoryUtil.newMap();
	private PageTemplet(){}
	
	public static PageTemplet getInstance() {
		return instance;
	}
	
	public void read(String fileName, String pageType) {
		_mpContent.put(pageType, FileUtil.read(fileName));
	}

	/**
	 * 返回模板文件内容
	 * @return
	 */
	public String getPage(String pageType) {
		return _mpContent.get(pageType);
	}
}
