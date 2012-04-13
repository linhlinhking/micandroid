Jxstar.currentPage = function() {
	var config = {param:{},initpage:function(page, define){},eventcfg:{}};
	
	var auditData = Jxstar.findComboData('audit');
	var hasData = Jxstar.findComboData('has');
	var lineData = Jxstar.findComboData('line');
	var hasData = Jxstar.findComboData('has');
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
					{xtype:'combo', fieldLabel:'记录状态', name:'buy_check__auditing', defaultval:'0',
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
					{xtype:'textfield', fieldLabel:'采购品名称型号', name:'buy_check__device_name', allowBlank:false, labelStyle:'color:#0000FF;', labelSeparator:'*', anchor:'100%', maxLength:100},
					{xtype:'trigger', fieldLabel:'供应商名称', name:'buy_check__provider_name',
						anchor:'100%', triggerClass:'x-form-search-trigger',
						maxLength:50, allowBlank:false, labelStyle:'color:#0000FF;', labelSeparator:'*', editable:false,
						onTriggerClick: function() {
							if (this.menu == null) {
								var selcfg = {pageType:'combogrid', nodeId:'buy_provider', layoutPage:'', sourceField:'buy_provider.provider_name;provider_id', targetField:'buy_check.provider_name;provider_id', whereSql:"", whereValue:'', whereType:'', isSame:'0', isShowData:'1', isMoreSelect:'0',isReadonly:'1',fieldName:'buy_check.provider_name'};
								this.menu = Jxstar.createComboMenu(this);
								Jxstar.createComboGrid(selcfg, this.menu, 'node_buy_check_form');
							}
							this.menu.show(this.el);
						}},
					{xtype:'combo', fieldLabel:'合格证书', name:'buy_check__has_right', defaultval:'1',
						anchor:'100%', editable:false,
						store: new Ext.data.SimpleStore({
							fields:['value','text'],
							data: hasData
						}),
						emptyText: jx.star.select,
						mode: 'local',
						triggerAction: 'all',
						valueField: 'value',
						displayField: 'text',
						value: hasData[0][0]},
					{xtype:'textfield', fieldLabel:'有无损伤', name:'buy_check__has_fai', defaultval:'无', anchor:'100%', maxLength:50},
					{xtype:'textfield', fieldLabel:'验收部门', name:'buy_check__dept_name', defaultval:'fun_getDeptName()', readOnly:true, anchor:'100%', maxLength:50},
					{xtype:'hidden', fieldLabel:'主键', name:'buy_check__check_id', anchor:'100%'}
				]
			},{
				border:false,
				columnWidth:0.33,
				layout:'form',
				items:[
					{xtype:'emptybox'},
					{xtype:'textfield', fieldLabel:'设备编号', name:'buy_check__device_code', anchor:'100%', maxLength:20},
					{xtype:'datefield', fieldLabel:'到货日期', name:'buy_check__arrive_date', defaultval:'fun_getToday()', format:'Y-m-d', anchor:'100%'},
					{xtype:'combo', fieldLabel:'装箱清单是否符合', name:'buy_check__has_zx', defaultval:'1',
						anchor:'100%', editable:false,
						store: new Ext.data.SimpleStore({
							fields:['value','text'],
							data: lineData
						}),
						emptyText: jx.star.select,
						mode: 'local',
						triggerAction: 'all',
						valueField: 'value',
						displayField: 'text',
						value: lineData[0][0]},
					{xtype:'textfield', fieldLabel:'备附件', name:'buy_check__has_attach', defaultval:'无', anchor:'100%', maxLength:50},
					{xtype:'datefield', fieldLabel:'清点日期', name:'buy_check__check_date', defaultval:'fun_getToday()', format:'Y-m-d', anchor:'100%'},
					{xtype:'hidden', fieldLabel:'验收部门ID', name:'buy_check__dept_id', defaultval:'fun_getDeptId()', anchor:'100%'}
				]
			},{
				border:false,
				columnWidth:0.33,
				layout:'form',
				items:[
					{xtype:'emptybox'},
					{xtype:'textfield', fieldLabel:'验收单号', name:'buy_check__check_code', readOnly:true, anchor:'100%', maxLength:20},
					{xtype:'textfield', fieldLabel:'清点人', name:'buy_check__check_user', defaultval:'fun_getUserName()', readOnly:true, anchor:'100%', maxLength:10},
					{xtype:'combo', fieldLabel:'有无说明书', name:'buy_check__has_use', defaultval:'1',
						anchor:'100%', editable:false,
						store: new Ext.data.SimpleStore({
							fields:['value','text'],
							data: hasData
						}),
						emptyText: jx.star.select,
						mode: 'local',
						triggerAction: 'all',
						valueField: 'value',
						displayField: 'text',
						value: hasData[0][0]},
					{xtype:'textfield', fieldLabel:'其它情况', name:'buy_check__has_other', defaultval:'无', anchor:'100%', maxLength:50},
					{xtype:'hidden', fieldLabel:'供应商ID', name:'buy_check__provider_id', anchor:'100%'},
					{xtype:'hidden', fieldLabel:'清点人ID', name:'buy_check__check_userid', defaultval:'fun_getUserId()', anchor:'100%'}
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
					{xtype:'textarea', fieldLabel:'检查清点情况', name:'buy_check__check_desc', width:'100%', height:48, maxLength:200}
				]
			},{
				border:false,
				columnWidth:0.99,
				layout:'form',
				items:[
					{xtype:'textarea', fieldLabel:'清查结论', name:'buy_check__check_result', width:'100%', height:48, maxLength:200}
				]
			}
			]
		}]
	}];
	
	config.param = {
		items: items,
		funid: 'buy_check'
	};

	
	
	return new Jxstar.FormNode(config);
}