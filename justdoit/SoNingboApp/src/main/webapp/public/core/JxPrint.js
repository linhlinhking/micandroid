﻿/*!
 * Copyright 2008-2011 the original author or authors.
 * Licensed under the Apache License, Version 2.0
 */
 
/**
 * 报表打印选择窗口。
 * 
 * @author TonyTan
 * @version 1.0, 2010-01-01
 */
 
JxPrint = {};
(function(){

	Ext.apply(JxPrint, {
	/**
	* 是否有报表定义信息
	**/
	hasReport: false,
	
	/**
	* public 显示报表选项对话框
	*
	* pageNode -- 当前功能的表格定义对象
	**/
	showWindow: function(pageNode) {
		var self = this;
		var funid = pageNode.nodeId;
		
		var hdCall = function(data) {
			var lsReport = [];
			
			if (data == null || data.length == 0) {
				self.hasReport = false;
				lsReport[0] = {baseCls:'x-plain', border:false, html:'<font color="red">'+ jx.print.noreport +'</font>'};	//没有报表定义信息！
			} else {
				self.hasReport = true;
				for (var i = 0, n = data.length; i < n; i++) {
					var item = {boxLabel: data[i].report_name,
								xtype: 'radio',
								name: 'reportId',
								inputValue: data[i].report_id,
								checked: false};
								
					if (i == 0) item.checked = true;
								
					lsReport[i] = item;
				}
			}
			
			self.createWindow(funid, lsReport, pageNode);
		};
		
		//从后台取报表定义信息
		var params = 'funid=rpt_list&pagetype=grid&eventcode=listdata&selfunid='+ funid;
		Request.dataRequest(params, hdCall);
	},
		
	/**
	* private 创建报表选项对话框
	*
	* funid -- 当前功能ID
	* lsReport -- 当前功能的报表定义信息
	**/
	createWindow: function(funid, lsReport, pageNode) {
		var printForm = new Ext.form.FormPanel({
			style: 'padding:20 20 0 0px;',
			border: false,
			frame: false,
			baseCls: 'x-plain',
			items: [{
				xtype:'fieldset',
				title:	jx.print.seltemp,	//'选择报表模板'
				autoHeight:true,
				hideLabels: true,
				style: 'margin-left:10px;',
				items: lsReport
			},{
				xtype:'fieldset',
				title: jx.print.outtype, 		//'选择输出类型'
				autoHeight:true,
				defaultType: 'radio',
				hideLabels: true,
				style: 'margin-left:10px;',
				items: [{
					boxLabel: jx.print.outxls,	//'输出Excel'
					name: 'printType',
					inputValue: 'xls',
					checked: true
				},{
					boxLabel: jx.print.outhtm,	//'输出HTML',
					name: 'printType',
					inputValue: 'html'
				}]
			},{
				xtype:'fieldset',
				title: jx.print.outrang, 		//'选择输出范围'
				autoHeight:true,
				defaultType: 'radio',
				hideLabels: true,
				style: 'margin-left:10px;',
				items: [{
					boxLabel: jx.print.outrec, 	//'输出选择的记录'
					name: 'printScope',
					inputValue: 'select',
					checked: true
				},{
					boxLabel: jx.print.outall, 	//'输出当前所有记录'
					name: 'printScope',
					inputValue: 'query'
				}]
			}]
		});
		
		var self = this;
		//创建对话框
		var win = new Ext.Window({
			title:	jx.print.ptitle, 	//'输出报表选项'
			layout:'fit',
			width:280,
			height:380,
			resizable: false,
			modal: true,
			closeAction:'close',
			items:[printForm],

			buttons: [{
				text:jx.base.ok,		//'确定'
				disabled:(!self.hasReport),
				handler:function(){
					JxPrint.printReport(funid, printForm.getForm(), pageNode);
					win.close();
				}
			},{
				text:jx.base.cancel,	//'取消'
				handler:function(){win.close();}
			}]
		});
		win.show();
	},
	
	/**
	* private 向后台发出打印报表的请求
	*
	* funId -- 当前功能ID
	* form -- 打印报表选项信息
	**/
	printReport: function(funId, form, pageNode) {
		//取选择的报表ID
		var reportId = form.findField('reportId').getGroupValue();
		
		//取选择的输出类型
		var printType = form.findField('printType').getGroupValue();
		
		//取选择的数据范围
		var printScope = form.findField('printScope').getGroupValue();
		
		var whereSql = '';
		var whereValue = '';
		var whereType = '';
		if (printScope == 'select') {
			whereSql = this.selectWhere(pageNode);
			if (whereSql.length == 0) {
				JxHint.alert(jx.print.selectno);	//'没有选择一条记录，不能执行此操作！'
				return false;
			}
		} else if (printScope == 'query') {
			var wheres = this.queryWhere(pageNode);
			whereSql = wheres[0];
			whereValue = wheres[1];
			whereType = wheres[2];
		} else {
			return false;
		}
		
		//请求参数
		var e = encodeURIComponent; //编码
		var params = 'funid='+ funId +'&reportId='+ reportId +'&printType='+  printType+'&whereSql='+
					 e(whereSql) +'&whereValue='+ e(whereValue) +'&whereType='+ whereType;
		params += '&user_id=' + Jxstar.session['user_id'];
		//发送后台请求
		var href = Jxstar.path + "/reportAction.do?" + params;
		
		if ('xls' == printType) {
			document.getElementById('frmhidden').src = href;
		} else {
			var winName = "w_report_" + parseInt(Math.random() * 10000);
			this.newPrintWindow(href, winName, 800, 600);
		}
	},
	
	//打开打印窗口
	newPrintWindow: function(url, windowName, width, height) {
		var newwin = window.open(url, windowName, 'resizable=yes,scrollbars=yes,status=no,toolbar=no,menubar=yes,location=no');
		newwin.focus();
		newwin.moveTo((screen.width - width)/2, (screen.height - height)/2);
		newwin.resizeTo(width, height);
	},
	
	/**
	* private 取当前查询结果的where子句
	**/
	queryWhere: function(pageNode) {
		var wheres = [3];
		//取当前where子句
		var dsOption = pageNode.page.getStore().lastOptions.params || {}; 
		wheres[0] = dsOption.where_sql || '';
		wheres[1] = dsOption.where_value || '';
		wheres[2] = dsOption.where_type || '';
		
		return wheres;
	},

	/**
	* private 取当前选择记录的where子句
	**/
	selectWhere: function(pageNode) {	
		var page = pageNode.page;
	
		var where = '';
		var pkcol = pageNode.define.pkcol;
		if(pageNode.pageType.indexOf("form") > -1) {
			var keyid = page.getForm().findField(pkcol).getValue();
			if (keyid != null && keyid.length > 0) {
				where = "'" + keyid + "'";
			}
		} else {
			var records = page.getSelectionModel().getSelections();
			if (records.length > 0) {
				for (var i = 0, n = records.length; i < n; i++) {
					where += "'" + records[i].get(pkcol) + "',";
				}
				where = where.substring(0, where.length - 1);
			}
		}
		
		if (where.length > 0) {
			where = pkcol.replace('__', '.') + ' in (' + where + ')';
		}
		return where;
	}

	});//Ext.apply

})();
