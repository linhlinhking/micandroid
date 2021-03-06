/*
 * FormPageParser.java 2009-10-31
 * 
 * Copyright 2008-2011 the original author or authors.
 * Licensed under the Apache License, Version 2.0
 */
package org.jxstar.fun.design.parser;


/**
 * FORM页面解析类。
 *
 * @author TonyTan
 * @version 1.0, 2009-10-31
 */
public class FormPageParser extends PageParser {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 解析控件的值
	 * @param name
	 * @return
	 */
	protected String elementValue(String name) {
		String ret = "";
		
		if (_designPage == null || _designPage.length() == 0) {
			_log.showWarn("designPage is null!");
			return "";
		}
		
		if (name.equals("items")) {
			FormDesignParser fieldParser = new FormDesignParser();
			
			String tableName = _funDefine.get("table_name");
			ret = fieldParser.parse(_funid, tableName, _designPage);
		} else if (name.equals("incPage")) {
			ExtPageParser extParser = new ExtPageParser();

			ret = extParser.parse(_funid, "form", _realPath);
			//替换文件中的\转义符
			ret = ret.replace("\\", "\\\\");
			
			if (ret.length() > 0) {//去掉换行符，不然前台取不到文件
				ret = ret.trim();
			}
		} else {
			ret = _elementTpl.get(name);
		}
		
		return ret;
	}
	
	/**
	 * 解析参数的值
	 * @param name
	 * @return
	 */
	protected String paramValue(String name) {
		return _funDefine.get(name);
	}
}
