/*
 * GridPageParserTest.java 2009-10-5
 * 
 * Copyright 2008-2011 the original author or authors.
 * Licensed under the Apache License, Version 2.0
 */
package org.jxstar.design;


import org.jxstar.fun.design.PageDesignBO;
import org.jxstar.fun.design.templet.ElementTemplet;
import org.jxstar.fun.design.templet.PageTemplet;
import org.jxstar.test.AbstractTest;

/**
 * grid页面生成类
 *
 * @author TonyTan
 * @version 1.0, 2009-10-5
 */
public class GridPageParserTest extends AbstractTest {
	private static String _path = "d:/tomcat6/webapps/myoa/";
	
	public static void main(String[] args) {
		PageTemplet pageTpl = PageTemplet.getInstance();
		pageTpl.read(path+"/WEB-INF/tpl/grid-page-tpl.txt", "grid");
		//System.out.println(pageTpl.content());
		
		ElementTemplet elTpl = ElementTemplet.getInstance();
		elTpl.read(path+"/WEB-INF/tpl/grid-element-tpl.xml", "grid");
		//System.out.println(elTpl.element("columnModel"));
		
		
		
		//GridPageParser parse = new GridPageParser();
		//parse.parseGrid("sys_fun_col");
		PageDesignBO parse = new PageDesignBO();
		parse.loadDesign("car_app", "grid", _path);
		System.out.println(parse.getReturnData());
	}
}
