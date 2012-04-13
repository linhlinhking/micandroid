/*
 * PageParserUtil.java 2010-10-15
 * 
 * Copyright 2008-2011 the original author or authors.
 * Licensed under the Apache License, Version 2.0
 */
package org.jxstar.fun.design.parser;

import java.util.Map;


import org.jxstar.dao.DaoParam;
import org.jxstar.service.BusinessObject;
import org.jxstar.service.define.DefineName;

/**
 * 页面解析工具类。
 *
 * @author TonyTan
 * @version 1.0, 2010-10-15
 */
public class PageParserUtil extends BusinessObject {
	private static final long serialVersionUID = 1L;

	/**
	 * 取功能的页面设计信息
	 * @param funcId
	 * @param pageType
	 * @return
	 */
	public String designPage(String funcId, String pageType){
		String sql = "select page_content from fun_design where fun_id = ? and page_type = ?";

		DaoParam param = _dao.createParam(sql);
		param.setDsName(DefineName.DESIGN_NAME);
		param.addStringValue(funcId).addStringValue(pageType);
		
		//如果有设计文件，则从设计文件中取
		String content = "";
		Map<String,String> map = _dao.queryMap(param);
		if (!map.isEmpty()) {
			content = map.get("page_content");
		}
		
		return content;
	}
}
