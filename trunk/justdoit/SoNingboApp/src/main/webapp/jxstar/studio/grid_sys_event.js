﻿Jxstar.currentPage = function() {
	var config = {param:{},initpage:function(page, define){},eventcfg:{}};

	var right_typeData = Jxstar.findComboData('right_type');
	var btnshowData = Jxstar.findComboData('btnshow');

	var cols = [
	{col:{header:'*代码', width:100, sortable:true, editable:true, hcss:'color:#0000ff;',
		editor:new Ext.form.TextField({
			maxLength:20, allowBlank:false
		})}, field:{name:'fun_event__event_code',type:'string'}},
	{col:{header:'*名称', width:100, sortable:true, editable:true, hcss:'color:#0000ff;',
		editor:new Ext.form.TextField({
			maxLength:20, allowBlank:false
		})}, field:{name:'fun_event__event_name',type:'string'}},
	{col:{header:'前台方法', width:140, sortable:true, editable:true, hcss:'color:#3039b4;',
		editor:new Ext.form.TextField({
			maxLength:50
		})}, field:{name:'fun_event__client_method',type:'string'}},
	{col:{header:'页面类型', width:126, sortable:true, editable:true, hcss:'color:#3039b4;',
		editor:new Ext.form.TextField({
			maxLength:100
		})}, field:{name:'fun_event__page_type',type:'string'}},
	{col:{header:'权限类型', width:60, sortable:true, defaultval:'edit', align:'center',
		editable:true, hcss:'color:#3039b4;',
		editor:new Ext.form.ComboBox({
			store: new Ext.data.SimpleStore({
				fields:['value','text'],
				data: right_typeData
			}),
			emptyText: jx.star.select,
			mode: 'local',
			triggerAction: 'all',
			valueField: 'value',
			displayField: 'text',
			editable:false,
			value: right_typeData[0][0]
		}),
		renderer:function(value){
			for (var i = 0; i < right_typeData.length; i++) {
				if (right_typeData[i][0] == value)
					return right_typeData[i][1];
			}
		}}, field:{name:'fun_event__right_type',type:'string'}},
	{col:{header:'显示类型', width:54, sortable:true, defaultval:'tool', align:'center',
		editable:true, hcss:'color:#3039b4;',
		editor:new Ext.form.ComboBox({
			store: new Ext.data.SimpleStore({
				fields:['value','text'],
				data: btnshowData
			}),
			emptyText: jx.star.select,
			mode: 'local',
			triggerAction: 'all',
			valueField: 'value',
			displayField: 'text',
			editable:false,
			value: btnshowData[0][0]
		}),
		renderer:function(value){
			for (var i = 0; i < btnshowData.length; i++) {
				if (btnshowData[i][0] == value)
					return btnshowData[i][1];
			}
		}}, field:{name:'fun_event__show_type',type:'string'}},
	{col:{header:'序号', width:63, sortable:true, align:'right',
		editable:true, hcss:'color:#3039b4;',
		editor:new Ext.form.NumberField({
			maxLength:12,
			allowNegative: false
		}),renderer:JxUtil.formatInt()}, field:{name:'fun_event__event_index',type:'int'}},
	{col:{header:'隐藏？', width:60, sortable:true, defaultval:'0', editable:true, hcss:'color:#3039b4;',
		editor:new Ext.form.Checkbox(),
		renderer:function(value) {
			return value=='1' ? jx.base.yes : jx.base.no;
		}}, field:{name:'fun_event__is_hide',type:'string'}},
	{col:{header:'快捷键', width:61, sortable:true, editable:true, hcss:'color:#3039b4;',
		editor:new Ext.form.TextField({
			maxLength:1
		})}, field:{name:'fun_event__access_key',type:'string'}},
	{col:{header:'功能ID', width:100, sortable:true, defaultval:'sysevent', editable:true, hcss:'color:#3039b4;',
		editor:new Ext.form.TextField({
			maxLength:25
		})}, field:{name:'fun_event__fun_id',type:'string'}},
	{col:{header:'域？', width:100, sortable:true, hidden:true, defaultval:'0'}, field:{name:'fun_event__is_domain',type:'string'}},
	{col:{header:'事件ID', width:100, sortable:true, hidden:true}, field:{name:'fun_event__event_id',type:'string'}}
	];
	
	config.param = {
		cols: cols,
		sorts: null,
		hasQuery: '1',
		isedit: '1',
		isshow: '1',
		funid: 'sys_event'
	};
	
	config.eventcfg = {
		f_invoke: function(){
			var records = this.grid.getSelectionModel().getSelections();
			if (!JxUtil.selectone(records)) return;

			//过滤条件
			var where_sql = 'fun_event_invoke.event_id = ?';
			var where_type = 'string';
			var where_value = records[0].get('fun_event__event_id');
			
			//加载数据
			var hdcall = function(layout) {
				//显示数据
				JxUtil.delay(500, function(){
					//调用类表
					var grid = layout.getComponent(0).getComponent(0);
					//设置外键值
					grid.fkValue = where_value;

					Jxstar.loadData(grid, {where_sql:where_sql, where_value:where_value, where_type:where_type});
				});
			};

			//显示数据
			Jxstar.showData({
				filename: '/jxstar/studio/pub/layout_invoke.js',
				title: jx.fun.invoke,	//'调用类注册' 
				callback: hdcall
			});
		}
	};
		
	return new Jxstar.GridNode(config);
}