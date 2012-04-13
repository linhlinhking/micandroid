/*
 * CodeCreatorTest.java 2010-3-27
 * 
 * Copyright 2008-2011 the original author or authors.
 * Licensed under the Apache License, Version 2.0
 */
package org.jxstar.util;


import org.jxstar.test.AbstractTest;
import org.jxstar.util.key.CodeCreator;

/**
 * 编号测试类
 *
 * @author TonyTan
 * @version 1.0, 2010-3-27
 */
public class CodeCreatorTest extends AbstractTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String code = CodeCreator.getInstance().createCode("car_app");
		System.out.println("=================" + code);
	}

}
