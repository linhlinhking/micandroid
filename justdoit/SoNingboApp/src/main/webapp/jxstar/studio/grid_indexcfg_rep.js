﻿Jxstar.currentPage = function() {
	var config = {param:{},initpage:function(page, define){},eventcfg:{}};

	var tablestateData = Jxstar.findComboData('tablestate');
	var yesnoData = Jxstar.findComboData('yesno');

	var cols = [
	{col:{header:'状态', width:62, sortable:true, defaultval:'1', align:'center',
		editable:true, hcss:'color:#3039b4;',
		editor:new Ext.form.ComboBox({
			store: new Ext.data.SimpleStore({
				fields:['value','text'],
				data: tablestateData
			}),
			emptyText: jx.star.select,
			mode: 'local',
			triggerAction: 'all',
			valueField: 'value',
			displayField: 'text',
			editable:false,
			value: tablestateData[0][0]
		}),
		renderer:function(value){
			for (var i = 0; i < tablestateData.length; i++) {
				if (tablestateData[i][0] == value)
					return tablestateData[i][1];
			}
		}}, field:{name:'dm_indexcfg__state',type:'string'}},
	{col:{header:'*索引名称', width:129, sortable:true, editable:true, hcss:'color:#0000ff;',
		editor:new Ext.form.TextField({
			maxLength:50, allowBlank:false
		})}, field:{name:'dm_indexcfg__index_name',type:'string'}},
	{col:{header:'*相关字段', width:221, sortable:true, editable:true, hcss:'color:#0000ff;',
		editor:new Ext.form.TextField({
			maxLength:100, allowBlank:false
		})}, field:{name:'dm_indexcfg__index_field',type:'string'}},
	{col:{header:'唯一?', width:58, sortable:true, defaultval:'0', align:'center',
		editable:true, hcss:'color:#3039b4;',
		editor:new Ext.form.ComboBox({
			store: new Ext.data.SimpleStore({
				fields:['value','text'],
				data: yesnoData
			}),
			emptyText: jx.star.select,
			mode: 'local',
			triggerAction: 'all',
			valueField: 'value',
			displayField: 'text',
			editable:false,
			value: yesnoData[0][0]
		}),
		renderer:function(value){
			for (var i = 0; i < yesnoData.length; i++) {
				if (yesnoData[i][0] == value)
					return yesnoData[i][1];
			}
		}}, field:{name:'dm_indexcfg__isunique',type:'string'}},
	{col:{header:'索引说明', width:211, sortable:true, editable:true, hcss:'color:#3039b4;',
		editor:new Ext.form.TextField({
			maxLength:200
		})}, field:{name:'dm_indexcfg__index_memo',type:'string'}},
	{col:{header:'索引ID', width:100, sortable:true, hidden:true}, field:{name:'dm_indexcfg__index_id',type:'string'}},
	{col:{header:'表ID', width:100, sortable:true, hidden:true}, field:{name:'dm_indexcfg__table_id',type:'string'}}
	];
	
	config.param = {
		cols: cols,
		sorts: null,
		hasQuery: '0',
		isedit: '1',
		isshow: '0',
		funid: 'dm_indexcfg_rep'
	};
	
	config.initpage = function(gridNode){
		var grid = gridNode.page;
		//删除状态的记录不能修改
		grid.on('beforeedit', function(event) {
			var r = event.grid.store.getAt(event.row);
			var a = 'dm_indexcfg__state';
			return !(r.get(a) != 'undefined' && r.get(a) == '3');
		});
		
		//表格编辑后事件，索引名必须大写
		grid.on('afteredit', function(event) {
			if (event.field == 'dm_indexcfg__index_name') {
				var r = event.record;
				var indexName = r.get('dm_indexcfg__index_name');
				if (indexName.length > 0) {
					r.set('dm_indexcfg__index_name', indexName.toUpperCase());
				}
			}
		});
	};
		
	return new Jxstar.GridNode(config);
}