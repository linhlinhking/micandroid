Jxstar.currentPage = function() {
	var config = {param:{},initpage:function(page, define){},eventcfg:{}};
	
	var areatypeData = Jxstar.findComboData('areatype');
	var yesnoData = Jxstar.findComboData('yesno');
	var yesnoData = Jxstar.findComboData('yesno');
	var items = [{
		height: '97%',
		width: '97%',
		border: false,
		layout: 'form',
		style: 'padding:10px;',
		items: [{
			anchor:'100%',
			layout:'column',
			autoHeight:true,
			items:[{
				border:false,
				columnWidth:0.495,
				layout:'form',
				items:[
					{xtype:'numberfield', allowDecimals:false, fieldLabel:'序号', name:'rpt_area__area_index', anchor:'100%', maxLength:12},
					{xtype:'numberfield', allowDecimals:false, fieldLabel:'每页行数', name:'rpt_area__page_size', allowBlank:false, labelStyle:'color:#0000FF;', labelSeparator:'*', anchor:'100%', maxLength:12},
					{xtype:'textfield', fieldLabel:'关键表', name:'rpt_area__main_table', allowBlank:false, labelStyle:'color:#0000FF;', labelSeparator:'*', anchor:'100%', maxLength:50},
					{xtype:'textfield', fieldLabel:'关键字段', name:'rpt_area__pk_col', anchor:'100%', maxLength:50},
					{xtype:'textfield', fieldLabel:'数据源', name:'rpt_area__ds_name', defaultval:'default', anchor:'100%', maxLength:25},
					{xtype:'hidden', fieldLabel:'范围', name:'rpt_area__area_pos', anchor:'100%'}
				]
			},{
				border:false,
				columnWidth:0.495,
				layout:'form',
				items:[
					{xtype:'textfield', fieldLabel:'名称', name:'rpt_area__area_name', allowBlank:false, labelStyle:'color:#0000FF;', labelSeparator:'*', anchor:'100%', maxLength:50},
					{xtype:'combo', fieldLabel:'区域类型', name:'rpt_area__area_type', defaultval:'grid',
						anchor:'100%', editable:false,
						store: new Ext.data.SimpleStore({
							fields:['value','text'],
							data: areatypeData
						}),
						emptyText: jx.star.select,
						mode: 'local',
						triggerAction: 'all',
						valueField: 'value',
						displayField: 'text',
						value: areatypeData[0][0]},
					{xtype:'combo', fieldLabel:'主区域?', name:'rpt_area__is_main', defaultval:'1',
						anchor:'100%', editable:false,
						store: new Ext.data.SimpleStore({
							fields:['value','text'],
							data: yesnoData
						}),
						emptyText: jx.star.select,
						mode: 'local',
						triggerAction: 'all',
						valueField: 'value',
						displayField: 'text',
						value: yesnoData[0][0]},
					{xtype:'combo', fieldLabel:'统计区域?', name:'rpt_area__is_stat', defaultval:'0',
						anchor:'100%', editable:false,
						store: new Ext.data.SimpleStore({
							fields:['value','text'],
							data: yesnoData
						}),
						emptyText: jx.star.select,
						mode: 'local',
						triggerAction: 'all',
						valueField: 'value',
						displayField: 'text',
						value: yesnoData[0][0]},
					{xtype:'textfield', fieldLabel:'子区域外键', name:'rpt_area__sub_fkcol', anchor:'100%', maxLength:50},
					{xtype:'hidden', fieldLabel:'区域ID', name:'rpt_area__area_id', anchor:'100%'}
				]
			}
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
					{xtype:'textarea', fieldLabel:'区域SQL', name:'rpt_area__data_sql', width:'100%', height:96, maxLength:2000},
					{xtype:'textarea', fieldLabel:'区域Where', name:'rpt_area__data_where', width:'100%', height:48, maxLength:500},
					{xtype:'textfield', fieldLabel:'区域Group', name:'rpt_area__data_group', anchor:'100%', maxLength:200},
					{xtype:'textfield', fieldLabel:'区域Order', name:'rpt_area__data_order', anchor:'100%', maxLength:100},
					{xtype:'hidden', fieldLabel:'报表ID', name:'rpt_area__report_id', anchor:'100%'}
				]
			}
			]
		}]
	}];
	
	config.param = {
		items: items,
		funid: 'rpt_area'
	};

	
	
	return new Jxstar.FormNode(config);
}