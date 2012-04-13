/*
 * TableCfgTest.java 2010-12-22
 * 
 * Copyright 2008-2011 the original author or authors.
 * Licensed under the Apache License, Version 2.0
 */
package org.jxstar.dm;


import org.jxstar.dm.studio.TableCfgBO;
import org.jxstar.test.AbstractTest;

/**
 * 表配置信息处理测试类。
 *
 * @author TonyTan
 * @version 1.0, 2010-12-22
 */
public class TableCfgTest extends AbstractTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TableCfgBO tablecfg = new TableCfgBO();
		String[] tableIds = {"jxstar3"};
		tablecfg.commitDDL(tableIds);
	}

}
