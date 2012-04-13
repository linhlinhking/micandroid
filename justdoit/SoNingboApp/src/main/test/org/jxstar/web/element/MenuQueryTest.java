/*
 * MenuQueryTest.java 2010-3-7
 * 
 * Copyright 2008-2011 the original author or authors.
 * Licensed under the Apache License, Version 2.0
 */
package org.jxstar.web.element;


import org.jxstar.service.query.MenuQuery;
import org.jxstar.test.AbstractTest;

/**
 * 
 *
 * @author TonyTan
 * @version 1.0, 2010-3-7
 */
public class MenuQueryTest extends AbstractTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MenuQuery menu = new MenuQuery();
		
		menu.createMainMenu("jxstar2");
	}

}
