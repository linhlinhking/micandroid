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
			layout:'column',
			autoHeight:true,
			items:[{
				border:false,
				columnWidth:0.33,
				layout:'form',
				items:[
					{xtype:'combo', fieldLabel:'记录状态', name:'buy_device__auditing', defaultval:'0',
						anchor:'100%', readOnly:true, editable:false,
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
					{xtype:'textfield', fieldLabel:'设备编号', name:'buy_device__device_code', anchor:'100%', maxLength:20},
					{xtype:'textfield', fieldLabel:'制造商名称', name:'buy_device__factory', anchor:'100%', maxLength:50},
					{xtype:'textfield', fieldLabel:'放置地点', name:'buy_device__device_addr', anchor:'100%', maxLength:50},
					{xtype:'textfield', fieldLabel:'填表人', name:'buy_device__write_user', defaultval:'fun_getUserName()', readOnly:true, anchor:'100%', maxLength:10},
					{xtype:'datefield', fieldLabel:'校准（检定）/验证的日期', name:'buy_device__check_date', format:'Y-m-d', anchor:'100%'},
					{xtype:'hidden', fieldLabel:'归口部门ID', name:'buy_device__dept_id', defaultval:'fun_getDeptId()', anchor:'100%'}
				]
			},{
				border:false,
				columnWidth:0.33,
				layout:'form',
				items:[
					{xtype:'emptybox'},
					{xtype:'textfield', fieldLabel:'设备名称', name:'buy_device__device_name', allowBlank:false, labelStyle:'color:#0000FF;', labelSeparator:'*', anchor:'100%', maxLength:50},
					{xtype:'datefield', fieldLabel:'接收日期', name:'buy_device__get_date', format:'Y-m-d', anchor:'100%'},
					{xtype:'textfield', fieldLabel:'接收时的状态', name:'buy_device__get_state', anchor:'100%', maxLength:50},
					{xtype:'datefield', fieldLabel:'填表日期', name:'buy_device__write_date', defaultval:'fun_getToday()', format:'Y-m-d', anchor:'100%'},
					{xtype:'datefield', fieldLabel:'下次校准（检定）/验证日期', name:'buy_device__next_date', format:'Y-m-d', anchor:'100%'},
					{xtype:'hidden', fieldLabel:'填表人ID', name:'buy_device__write_userid', defaultval:'fun_getUserId()', anchor:'100%'}
				]
			},{
				border:false,
				columnWidth:0.33,
				layout:'form',
				items:[
					{xtype:'emptybox'},
					{xtype:'textfield', fieldLabel:'规格型号', name:'buy_device__device_size', allowBlank:false, labelStyle:'color:#0000FF;', labelSeparator:'*', anchor:'100%', maxLength:50},
					{xtype:'datefield', fieldLabel:'启用日期', name:'buy_device__use_date', format:'Y-m-d', anchor:'100%'},
					{xtype:'textfield', fieldLabel:'归口部门', name:'buy_device__dept_name', defaultval:'fun_getDeptName()', readOnly:true, anchor:'100%', maxLength:50},
					{xtype:'hidden', fieldLabel:'主键', name:'buy_device__device_id', anchor:'100%'}
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
					{xtype:'textarea', fieldLabel:'维护计划记录', name:'buy_device__maint_plan', width:'100%', height:72, maxLength:200},
					{xtype:'textarea', fieldLabel:'维护历史记录', name:'buy_device__maint_his', width:'100%', height:72, maxLength:200}
				]
			}
			]
		}]
	}];
	
	config.param = {
		items: items,
		funid: 'buy_device'
	};

	
	
	return new Jxstar.FormNode(config);
}