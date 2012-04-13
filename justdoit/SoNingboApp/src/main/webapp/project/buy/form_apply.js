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
				columnWidth:0.495,
				layout:'form',
				items:[
					{xtype:'combo', fieldLabel:'记录状态', name:'buy_apply__auditing', defaultval:'0',
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
					{xtype:'textfield', fieldLabel:'提出人', name:'buy_apply__apply_user', defaultval:'fun_getUserName()', readOnly:true, anchor:'100%', maxLength:10},
					{xtype:'textfield', fieldLabel:'申请部门', name:'buy_apply__dept_name', defaultval:'fun_getDeptName()', readOnly:true, anchor:'100%', maxLength:50},
					{xtype:'hidden', fieldLabel:'主键', name:'buy_apply__apply_id', anchor:'100%'}
				]
			},{
				border:false,
				columnWidth:0.495,
				layout:'form',
				items:[
					{xtype:'datefield', fieldLabel:'申请日期', name:'buy_apply__apply_date', defaultval:'fun_getToday()', format:'Y-m-d', anchor:'100%'},
					{xtype:'textfield', fieldLabel:'部门接口人', name:'buy_apply__get_user', anchor:'100%', maxLength:10},
					{xtype:'textfield', fieldLabel:'申请单号', name:'buy_apply__apply_code', readOnly:true, anchor:'100%', maxLength:20},
					{xtype:'hidden', fieldLabel:'提出人ID', name:'buy_apply__apply_userid', defaultval:'fun_getUserId()', anchor:'100%'},
					{xtype:'hidden', fieldLabel:'申请部门ID', name:'buy_apply__dept_id', defaultval:'fun_getDeptId()', anchor:'100%'}
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
					{xtype:'textarea', fieldLabel:'采购产品的名称及规格型号', name:'buy_apply__device_name', allowBlank:false, labelStyle:'color:#0000FF;', labelSeparator:'*', width:'100%', height:48, maxLength:100},
					{xtype:'textarea', fieldLabel:'采购原因', name:'buy_apply__apply_cause', width:'100%', height:48, maxLength:200},
					{xtype:'textfield', fieldLabel:'采购产品数量', name:'buy_apply__device_num', anchor:'100%', maxLength:100},
					{xtype:'textfield', fieldLabel:'估算费用', name:'buy_apply__device_money', anchor:'100%', maxLength:50},
					{xtype:'textarea', fieldLabel:'其它说明', name:'buy_apply__apply_memo', width:'100%', height:48, maxLength:200}
				]
			}
			]
		}]
	}];
	
	config.param = {
		items: items,
		funid: 'buy_apply'
	};

	
	
	return new Jxstar.FormNode(config);
}