/*
 * WfVersionTest.java 2011-1-26
 * 
 * Copyright 2008-2011 the original author or authors.
 * Licensed under the Apache License, Version 2.0
 */
package org.jxstar.wf.bo;

import java.util.Map;


import org.jxstar.test.AbstractTest;
import org.jxstar.util.factory.FactoryUtil;
import org.jxstar.wf.studio.WfVersionBO;

/**
 * 过程定义信息创建新版本。
 *
 * @author TonyTan
 * @version 1.0, 2011-1-26
 */
public class WfVersionTest extends AbstractTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WfVersionBO version = new WfVersionBO();
		
		String processId = "jxstar2";
		Map<String,String> mpUser = FactoryUtil.newMap();
		mpUser.put("user_id", "administrator");
		mpUser.put("user_name", "东宏");
		version.newVersion(processId, mpUser);
	}

}
