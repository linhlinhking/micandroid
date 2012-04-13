﻿Jxstar.currentPage = function() {
	var config = {param:{},initpage:function(page, define){},eventcfg:{}};

	var datastyleData = Jxstar.findComboData('datastyle');
	var yesnoData = Jxstar.findComboData('yesno');

	var cols = [
	{col:{header:'序号', width:44, sortable:true, editable:true, hcss:'color:#3039b4;',
		editor:new Ext.form.TextField({
			maxLength:22
		})}, field:{name:'rpt_detail__col_index',type:'int'}},
	{col:{header:'*字段名', width:106, sortable:true, editable:true, hcss:'color:#0000ff;',
		editor:new Ext.form.TextField({
			maxLength:50, allowBlank:false
		})}, field:{name:'rpt_detail__col_code',type:'string'}},
	{col:{header:'*字段标签', width:96, sortable:true, editable:true, hcss:'color:#0000ff;',
		editor:new Ext.form.TextField({
			maxLength:50, allowBlank:false
		})}, field:{name:'rpt_detail__display',type:'string'}},
	{col:{header:'显示位置', width:78, sortable:true, editable:true, hcss:'color:#3039b4;',
		editor:new Ext.form.TextField({
			maxLength:20
		})}, field:{name:'rpt_detail__col_pos',type:'string'}},
	{col:{header:'显示样式', width:81, sortable:true, defaultval:'text', align:'center',
		editable:true, hcss:'color:#3039b4;',
		editor:new Ext.form.ComboBox({
			store: new Ext.data.SimpleStore({
				fields:['value','text'],
				data: datastyleData
			}),
			emptyText: jx.star.select,
			mode: 'local',
			triggerAction: 'all',
			valueField: 'value',
			displayField: 'text',
			editable:false,
			value: datastyleData[0][0]
		}),
		renderer:function(value){
			for (var i = 0; i < datastyleData.length; i++) {
				if (datastyleData[i][0] == value)
					return datastyleData[i][1];
			}
		}}, field:{name:'rpt_detail__format',type:'string'}},
	{col:{header:'统计?', width:49, sortable:true, defaultval:'0', align:'center',
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
		}}, field:{name:'rpt_detail__is_stat',type:'string'}},
	{col:{header:'显示?', width:49, sortable:true, defaultval:'1', align:'center',
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
		}}, field:{name:'rpt_detail__is_show',type:'string'}},
	{col:{header:'输出零?', width:63, sortable:true, defaultval:'1', align:'center',
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
		}}, field:{name:'rpt_detail__is_outzero',type:'string'}},
	{col:{header:'选项控件', width:100, sortable:true, editable:true, hcss:'color:#3039b4;',
		editor:new Ext.form.TextField({
			maxLength:20
		})}, field:{name:'rpt_detail__combo_code',type:'string'}},
	{col:{header:'明细ID', width:100, sortable:true, hidden:true}, field:{name:'rpt_detail__det_id',type:'string'}},
	{col:{header:'区域ID', width:100, sortable:true, hidden:true}, field:{name:'rpt_detail__area_id',type:'string'}}
	];
	
	config.param = {
		cols: cols,
		sorts: null,
		hasQuery: '0',
		isedit: '1',
		isshow: '0',
		funid: 'rpt_detail'
	};
	
	config.initpage = function(gridNode){
		var grid = gridNode.page;
		grid.on('rowclick', function(g, rowindex, e) {
			var seldiv = Ext.get('sel_rpttddiv');
			if (seldiv != null) {
				var record = g.getStore().getAt(rowindex);
				
				seldiv.oldRecord = seldiv.curRecord;
				seldiv.curRecord = record;
				seldiv.curTable = 'rpt_detail';
			}
		});
	};
		
	return new Jxstar.GridNode(config);
}