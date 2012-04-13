Jxstar.currentPage = function() {
	var config = {param:{},initpage:function(page, define){},eventcfg:{}};
	
	var auditData = Jxstar.findComboData('audit');
	var items = [{
		height: '97%',
		width: '97%',
		border: false,
		layout: 'form',
		style: 'padding:10px;',
		items: [{
			anchor:'100%',
			xtype:'compositefield',
			fieldLabel:'【报告日期】',
			msgTarget:'side',
			readOnly:true, 
			defaults: {flex:1},
			items: [
				{xtype:'datefield', name:'ph_dayreport__report_date', defaultval:'fun_getToday()', readOnly:true, format:'Y-m-d', allowBlank:false, labelStyle:'color:#0000FF;', labelSeparator:'*', width:150},
				{xtype:'hidden', name:'ph_dayreport__dreport_id', anchor:'100%'},
				{xtype:'textfield', name:'ph_dayreport__user_name', defaultval:'fun_getUserName()', readOnly:true, width:150, maxLength:20},
				{xtype:'textfield', name:'ph_dayreport__dept_name', defaultval:'fun_getDeptName()', readOnly:true, width:150, maxLength:50},
				{xtype:'hidden', name:'ph_dayreport__dept_id', defaultval:'fun_getDeptId()', anchor:'100%'},
				{xtype:'hidden', name:'ph_dayreport__write_date', defaultval:'fun_getToday()', anchor:'100%'},
				{xtype:'combo', name:'ph_dayreport__auditing', defaultval:'0',
						width:150, readOnly:true, editable:false,
						store: new Ext.data.SimpleStore({
							fields:['value','text'],
							data: auditData
						}),
						emptyText: jx.star.select,
						mode: 'local',
						triggerAction: 'all',
						valueField: 'value',
						displayField: 'text',
						value: auditData[0][0]},
				{xtype:'hidden', fieldLabel:'填报人ID', name:'ph_dayreport__user_id', defaultval:'fun_getUserId()', anchor:'100%'}
			]
		},{
			anchor:'100%',
			layout:'column',
			autoHeight:true,
			items:[{
				border:false,
				columnWidth:0.99,
				layout:'form',
				items:[
					{xtype:'textarea', fieldLabel:'【主要问题】', name:'ph_dayreport__work_question', width:'100%', height:48, maxLength:500},
					{xtype:'panel', layout:'fit', style:'border-width:1px;', fieldLabel:'【主要完成任务】', id:'ph_dayreport__timesheet_subgrid', width:'100%', height:96, maxLength:500},
					{xtype:'panel', layout:'fit', style:'border-width:1px;', fieldLabel:'【明日计划】', id:'ph_dayreport__morrowplan_subgrid', width:'100%', height:96, maxLength:500},
					{xtype:'panel', layout:'fit', style:'border-width:1px;', fieldLabel:'【近期计划】', id:'ph_dayreport__latestplan_subgrid', width:'100%', height:96, maxLength:500}
				]
			}
			]
		}]
	}];
	
	config.param = {
		items: items,
		labelAlign: 'top',
		funid: 'ph_dayreport'
	};

	config.initpage = function(formNode){
		var fevent = formNode.event;
		
		//显示近期计划功能
		fevent.showlast = function() {
			Jxstar.createNode('ph_latestplan');
		};
		
		//注册保存前事件，检查工时不能超过24小时，不能小于6小时
		fevent.on('beforesave', function(fe, data, eventcode){
			var timegrid = Ext.getCmp('node_ph_timesheet_notoolsubeditgrid');
			var hours = 0;
			timegrid.getStore().each(function(r){
				var h = r.get('ph_timesheet__work_hours');
				hours += h;
			});
			if (hours < 6 || hours > 24) {
				JxHint.alert('日工时不能小于6小时，且不能大于24小时！');
				return false;
			}
			return true;
		});
		
		//注册保存后事件，保存子表记录
		fevent.on('aftersave', function(fe, data, eventcode){
			var timegrid = Ext.getCmp('node_ph_timesheet_notoolsubeditgrid');
			var morrgrid = Ext.getCmp('node_ph_morrowplan_notoolsubeditgrid');
			/*
			//由于每次都主键值，暂不需要更新子表的外键值
			//新增后的主键值，如果不为空表示是新增，需要替换明细表中的主键值
			var newid = data['keyid'];
			if (newid != null && newid.length > 0) {
				if (timegrid.fkValue != null || timegrid.fkValue.length == 0) {
					timegrid.fkValue = newid;
					timegrid.getStore().each(function(r){
						r.set(timegrid.fkName, newid);
					});
				}
				
				if (morrgrid.fkValue != null || morrgrid.fkValue.length == 0) {
					morrgrid.fkValue = newid;
					morrgrid.getStore().each(function(r){
						r.set(morrgrid.fkName, newid);
					});
				}
			}*/
			
			//执行子表保存事件
			timegrid.gridNode.event.editSave();
			morrgrid.gridNode.event.editSave();
			
			return true;
		});
		
		//显示子表数据
		var showSubData = function(pkvalue, form) {
			var timegrid = Ext.getCmp('node_ph_timesheet_notoolsubeditgrid');
			if (timegrid == null) return;
			Jxstar.loadSubData(timegrid, pkvalue);
			
			var morrgrid = Ext.getCmp('node_ph_morrowplan_notoolsubeditgrid');
			if (morrgrid == null) return;
			Jxstar.loadSubData(morrgrid, pkvalue);
			
			var latestgrid = Ext.getCmp('node_query_lastplan_notoolsubeditgrid');
			if (latestgrid == null) return;
			
			var userId = form.get('ph_dayreport__user_id');
			var reportDate = form.get('ph_dayreport__report_date').dateFormat('Y-m-d');
			var reportDate1 = Date.parseDate(reportDate, 'Y-m-d').add(Date.DAY, 1).dateFormat('Y-m-d');
			var where_type = 'date;date;string';
			var where_value = reportDate1 + ';' + reportDate + ';' + userId;
			var where_sql = 'ph_latestplan.start_date < ? and ph_latestplan.end_date >= ? and ph_latestplan.user_id = ?';
			Jxstar.loadData(latestgrid, {where_sql:where_sql, where_value:where_value, where_type:where_type, is_query:1});
			
			//已复核记录不能编辑
			var auditval = form.get('ph_dayreport__auditing');
			timegrid.on('beforeedit', function(event) {
				if (auditval != '0') return false;
			});
			morrgrid.on('beforeedit', function(event) {
				if (auditval != '0') return false;
			});
			
			//已复核的记录隐藏按钮列
			var hidden = (auditval != '0');
			
			var tcolm = timegrid.getColumnModel();
			var tindex = tcolm.getIndexById('col_button_add');
			tcolm.setHidden(tindex, hidden);
			
			var mcolm = morrgrid.getColumnModel();
			var mindex = mcolm.getIndexById('col_button_add');
			mcolm.setHidden(mindex, hidden);
		};
	
		fevent.initOther = function() {
			if (formNode.pageType == 'form') {
				var form = formNode.page.getForm();
				
				var pkvalue = form.get('ph_dayreport__dreport_id');
				if (pkvalue == null || pkvalue.length == 0) return;
				/*if (pkvalue.length == 0) {
					pkvalue = Jxstar.session.sessionId;
				}*/
				showSubData(pkvalue, form);
			}
		};
		//用于设置子表的外键值与加载子表数据
		JxUtil.delay(1000, function(){
			fevent.initOther();
		});
	};
	
	return new Jxstar.FormNode(config);
}