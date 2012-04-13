/*!
 * Copyright 2008-2011 the original author or authors.
 * Licensed under the Apache License, Version 2.0
 */
  
/**
 * 常用功能布局页面，支持grid, form, subgrid类型的页面组合。
 * 
 * @author TonyTan
 * @version 1.0, 2010-01-01
 */

Jxstar.currentPage = function(define, pageParam) {
	if (define == null) {
		JxHint.alert('layout_main define param define is null!');
		return;
	}

	var funid = define.nodeid;
	var pkcol = define.pkcol;
	var title = define.nodetitle;

	//创建标准GridForm布局
	var tabGridForm = new Ext.TabPanel({
		border:false,
		closeAction:'close',
		activeTab:0,
		items:[{
			pagetype:'grid',
			title: title+'-'+jx.layout.grid,	//列表
			autoScroll:true,
			layout:'fit',
			border:false,
			iconCls:'tab_grid'
		},{
			pagetype:'form',
			title: title+'-'+jx.layout.form,	//表单
			autoScroll:true,
			layout:'fit',
			border:false,
			iconCls:'tab_form'
		}]
	});
	
	//处理审批页面类型
	var isCheck = (pageParam && pageParam.pageType && pageParam.pageType == 'check');
	if (isCheck) pageParam.pageType = 'chkgrid';
	
	//添加grid页面
	Jxstar.createPage(funid, 'gridpage', tabGridForm.getComponent(0), pageParam);
	//添加form页面
	var fpageType = isCheck ? 'chkform' : 'form';
	Jxstar.createPage(funid, 'formpage', tabGridForm.getComponent(1), {pageType:fpageType});
	//添加子功能
	JxUtil.delay(1000, function(){
		var timesheetsub = Ext.getCmp('ph_dayreport__timesheet_subgrid');
		var subParam = {pageType:'notoolsubeditgrid', parentNodeId:funid};
		Jxstar.createPage('ph_timesheet', 'gridpage', timesheetsub, subParam);
		
		var morrowplansub = Ext.getCmp('ph_dayreport__morrowplan_subgrid');
		subParam = {pageType:'notoolsubeditgrid', parentNodeId:funid};
		Jxstar.createPage('ph_morrowplan', 'gridpage', morrowplansub, subParam);
		
		var latestplansub = Ext.getCmp('ph_dayreport__latestplan_subgrid');
		subParam = {pageType:'notoolsubeditgrid', parentNodeId:funid};
		Jxstar.createPage('query_lastplan', 'gridpage', latestplansub, subParam);
	});

	tabGridForm.on('beforetabchange', function(tabPanel, newTab, currentTab){
		//取列表
		var fgp = tabPanel.getComponent(0);
		if (fgp == null) return false;
		//tab打开时为空
		if (fgp.items == null) return true;
		var fgrid = fgp.getComponent(0);
		if (fgrid == null) return false;
		
		var pagetype = newTab.pagetype;
		var records = fgrid.getSelectionModel().getSelections();
		if (records.length == 0 && pagetype != 'grid') {
			//如果点击grid的新增按钮则可以打开form界面
			var form = newTab.getComponent(0);
			if (pagetype != 'form'  || (pagetype == 'form' && form.getForm().srcEvent != 'create')) {
				JxHint.alert(jx.layout.selmain);	//'请选择一条主记录！'
				return false;
			}
		}
		var curPage = currentTab.getComponent(0);
		if (curPage.isXType('form') && curPage.getForm().isDirty()) {
			if (confirm(jx.layout.modify)) {	//'记录已被修改，是否需要先保存？'
				self.save();
				return false;
			}
		}
		
		return true;
	});

	tabGridForm.on('tabchange', function(tabPanel, activeTab){
		//取当前激活的Tab页面类型
		var pagetype = activeTab.pagetype;
		//处理有些页面没有自动显示的问题
		activeTab.doLayout();
		//取主界面的功能列表
		var fgp = tabPanel.getComponent(0);
		if (fgp == null) return false;
		//tab打开时为空
		if (fgp.items == null) return false;
		var fgrid = fgp.getComponent(0);
		if (fgrid == null) return false;
		
		var curPage = activeTab.getComponent(0);
		
		//取选择记录的主键值
		var pkvalue = '';
		var records = fgrid.getSelectionModel().getSelections();
		if (records.length >= 1) {
			pkvalue = records[0].get(pkcol);
		} else {
			if ((pagetype != 'grid' && pagetype != 'form') || 
			    (pagetype == 'form' && curPage.getForm().srcEvent != 'create')) {
				JxHint.alert(jx.layout.selmain);	//'请选择一条主记录！'
				return false;
			}
		}
		
		//显示表单数据
		if (pagetype == 'form') {
			var form = curPage.getForm();
			if (form.srcEvent != 'create') {
				var record = records[0];
				form.myGrid = fgrid;
				form.myStore = fgrid.getStore();
				form.myRecord = record;
				form.loadRecord(record);
			}
			//显示FORM时，执行初始化事件
			curPage.formNode.event.initForm();
			//清除打开form的来源事件
			delete form.srcEvent;
		} else if (pagetype == 'subgrid') {
			Jxstar.loadSubData(curPage, pkvalue);
		}
	});

	return tabGridForm;
};
