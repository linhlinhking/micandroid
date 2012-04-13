/*
 * JsCreator.java 2009-10-25
 * 
 * Copyright 2008-2011 the original author or authors.
 * Licensed under the Apache License, Version 2.0
 */
package org.jxstar.fun.design;

import java.util.Map;

import org.jxstar.service.BusinessObject;
import org.jxstar.service.define.FunDefineDao;
import org.jxstar.util.FileUtil;
import org.jxstar.util.resource.JsMessage;

/**
 * 页面文件生成类，可以生成GRID与FORM页面。
 *
 * @author TonyTan
 * @version 1.0, 2009-10-25
 */
public class JsCreatorBO extends BusinessObject {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 生成文件
	 * @param funcId -- 功能ID
	 * @param pageType -- 页面类型，只有form,grid
	 * @param realPath -- 系统路径
	 * @return
	 */
	public String createJs(String funcId, String pageType, String realPath) {
		if (funcId == null || funcId.length() == 0 ||
				pageType == null || pageType.length() == 0) {
			//"错误：生成文件的参数值为空！"
			_log.showWarn(JsMessage.getValue("formdisignbo.paramnull"));
			return _returnFaild;
		}
		//取功能定义信息
		Map<String,String> mpFun = FunDefineDao.queryFun(funcId);
		
		PageDesignBO design = new PageDesignBO();
		String ret = design.loadDesign(funcId, pageType, realPath);
		
		if (ret.equals(_returnFaild)) return _returnFaild;
		//生成文件内容
		String page = design.getReturnData();
		//文件名
		String fileName = "";
		if (pageType.indexOf("grid") >= 0) {
			fileName = mpFun.get("grid_page");
		} else {
			fileName = mpFun.get("form_page");
		}
		if (fileName.length() == 0) {
			setMessage(JsMessage.getValue("jscreator.notpage"), pageType);
			return _returnFaild;
		}
		
		//生成文件
		fileName = realPath + fileName;
		_log.showDebug("生成文件的名称=" + fileName);
		FileUtil.saveFileUtf8(fileName, page);
		
		return _returnSuccess;
	}
}
