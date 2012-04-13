/*
 * PortalQueryTest.java 2010-12-30
 * 
 * Copyright 2008-2011 the original author or authors.
 * Licensed under the Apache License, Version 2.0
 */
package org.jxstar.web.element;


import org.jxstar.service.query.PortalQuery;
import org.jxstar.test.AbstractTest;

/**
 * 首页配置信息测试类
 *
 * @author TonyTan
 * @version 1.0, 2010-12-30
 */
public class PortalQueryTest extends AbstractTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PortalQuery portal = new PortalQuery();
		portal.getPortalJson("administrator");
	}

}
