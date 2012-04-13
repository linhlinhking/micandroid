/*!
 * Copyright 2008-2011 the original author or authors.
 * Licensed under the Apache License, Version 2.0
 */
 
/**
 * 系统首页处理类，登录成功后动态加载。
 * 
 * @author TonyTan
 * @version 1.0, 2010-01-01
 */

(function(){
	Ext.QuickTips.init();  
	//Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
	//加载功能定义数据
	JxUtil.loadJxData();
	
	/*--------------------创建首页头部区域-----------------------*/
	var topHtml = 
		"<div class='top_bg'>" + 
			"<div class='top_north'>" + 
				"<div id='main_hello' class='top_nleft'>" + jx.base.welcome + ' ' + JxDefault.getUserName() +" ["+ JxDefault.getDeptName() +"]</div>" + 
				"<div id='main_menu' class='top_nright'></div>" +
			"</div>" + 
			"<div class='top_south' id='main_hint'></div>" + 
		"</div>";

    var topPanel = new Ext.Panel({
		region:'north',
        layout:'border',
		height:44,
		border:true,
	    items:
		[{
			width: 42,
			region:'west',
			border:false,
	        id:'top_left_img',
	        html: '<div class=\'top_bg\' style=\'padding-top:4px;\'><img onload=\'JxUtil.fixPNG(this);\' id=\'main_page_img\' src=\'./resources/images/top-app.gif\' width=\'40\' height=\'40\'/></div>'
	    },{
	        region:'center',
			border:false,
			html:topHtml
	    }]
	});

	//创建首页功能显示区域
	var funMainTab = new Ext.TabPanel({
		id:'sys_main_tab',
		region:'center',
		//resizeTabs:true,
		//enableTabScroll:true,
		closeAction:'close',
		tabPosition:'bottom',
		activeTab:0,
		items:[{
			id:'fun_main',
			title:jx.base.onepage, //首页
			autoScroll:true,
			layout:'fit',
			iconCls:'function'
		}]
	});

	//创建首页页面布局
    var viewport = new Ext.Viewport({
        layout:'border',
	    items:[topPanel, funMainTab]
	});

	//创建头部的菜单，main_menu是显示菜单的DIV标示
	JxMenu.createMainMenu('main_menu');

	//创建protel功能界面
	var funTab = Ext.getCmp('fun_main');
	JxPortal.createMainPortal(funTab);

	//设置首页按钮
	Ext.get('main_page_img').on('click', function(){
		funMainTab.activate(funTab);
		//重新加载首页
		funTab.removeAll(funTab.getComponent(0), true);
		JxPortal.createMainPortal(funTab);
	});

	//关闭右键事件
	//Ext.getBody().on('contextmenu', function(event){event.stopEvent();});

	funMainTab.doLayout();
	
	//每3秒打开一次layout_main布局功能页面
	/*var count = 200;
	Ext.TaskMgr.start({
		run: function() {
			Jxstar.createNode('run_malrecord');
			JxUtil.delay(2000, function(){
				Jxstar.closeNode('run_malrecord');
				count--;
				JxHint.hint('has num:' + count);
				if (count < 0) Ext.TaskMgr.stopAll();
			});
		},
		interval: 1000*3
	});*/
	
	//启动会话效验
	SessionTimer.SESSION_TIMEOUT = Jxstar.session.maxInterval;
	SessionTimer.resetTimer();
	SessionTimer.startTimer();
})();