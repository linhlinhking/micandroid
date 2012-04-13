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
					{xtype:'combo', fieldLabel:'记录状态', name:'buy_provider__auditing', defaultval:'0',
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
					{xtype:'textfield', fieldLabel:'供应商名称', name:'buy_provider__provider_name', allowBlank:false, labelStyle:'color:#0000FF;', labelSeparator:'*', anchor:'100%', maxLength:50},
					{xtype:'textfield', fieldLabel:'供应商地址', name:'buy_provider__address', anchor:'100%', maxLength:100},
					{xtype:'textfield', fieldLabel:'登记人', name:'buy_provider__write_user', defaultval:'fun_getUserName()', readOnly:true, anchor:'100%', maxLength:10},
					{xtype:'hidden', fieldLabel:'主键', name:'buy_provider__provider_id', anchor:'100%'}
				]
			},{
				border:false,
				columnWidth:0.495,
				layout:'form',
				items:[
					{xtype:'textfield', fieldLabel:'供应商编码', name:'buy_provider__provider_code', readOnly:true, anchor:'100%', maxLength:20},
					{xtype:'textfield', fieldLabel:'供应商电话', name:'buy_provider__phone', anchor:'100%', maxLength:50},
					{xtype:'textfield', fieldLabel:'联系人', name:'buy_provider__limit_user', anchor:'100%', maxLength:50},
					{xtype:'datefield', fieldLabel:'登记日期', name:'buy_provider__write_date', defaultval:'fun_getToday()', format:'Y-m-d', anchor:'100%'},
					{xtype:'hidden', fieldLabel:'登记人ID', name:'buy_provider__write_userid', defaultval:'fun_getUserId()', anchor:'100%'}
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
					{xtype:'textarea', fieldLabel:'供应商服务内容', name:'buy_provider__provide_server', width:'100%', height:48, maxLength:200},
					{xtype:'textarea', fieldLabel:'服务质量评价', name:'buy_provider__provide_quality', width:'100%', height:48, maxLength:200}
				]
			}
			]
		}]
	}];
	
	config.param = {
		items: items,
		funid: 'buy_provider'
	};

	
	
	return new Jxstar.FormNode(config);
}