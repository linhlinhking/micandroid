﻿/*!
 * Copyright 2008-2011 the original author or authors.
 * Licensed under the Apache License, Version 2.0
 */
  
/**
 * 树形页面布局，支持tree, grid类型的页面组合。
 * 由于树型布局常用于数据导入界面与子功能界面布局，所以需要添加pageType参数，
 * 由于子功能也存在树形布局，所以需要添加parentNodeId参数，这两个参数都添加到pageParam中了。
 * 
 * @author TonyTan
 * @version 1.0, 2010-01-01
 */

Jxstar.currentPage = function(define, pageParam) {//pageType, parentNodeId
	if (define == null) {
		JxHint.alert('layout_tree define param define is null!');
		return;
	}

	var funid = define.nodeid;
	
	pageParam = pageParam || {};
	var pageType = pageParam.pageType || 'grid';

	//创建树形布局面板
	var funLayout = new Ext.Panel({
		border:false,
		layout:'border',
		items:[{
			autoScroll:true,
			region:'west',
			layout:'fit',
			width:160,
			minSize: 160,
	        maxSize: 300,
			split:true,
			border:false
		},{
			region:'center',
			layout:'fit',
			border:false
		}]
	});
	
	//创建数据页面
	Jxstar.createTree(funid, funLayout);
	Jxstar.createPage(funid, 'gridpage', funLayout.getComponent(1), pageParam);

	return funLayout;
};