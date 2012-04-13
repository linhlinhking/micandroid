/*
 * LangTextTest.java 2011-3-31
 * 
 * Copyright 2008-2011 the original author or authors.
 * Licensed under the Apache License, Version 2.0
 */

package org.jxstar.fun.studio;

import org.jxstar.test.AbstractTest;

/**
 * 文字描述文件生成类测试。
 *
 * @author TonyTan
 * @version 1.0, 2011-3-31
 */
public class LangTextTest extends AbstractTest {

	public static void main(String[] args) {
		String path = "d:/Tomcat6/webapps/jxstar";
		//ComboDataBO text = new ComboDataBO();
		//LangTextBO text = new LangTextBO();
		//LangEventBO text = new LangEventBO();
		//LangFunBO text = new LangFunBO();
		LangFieldBO text = new LangFieldBO();
		
		text.createJson(path);
		
		System.out.println("----" + text.getMessage());
	}

}
