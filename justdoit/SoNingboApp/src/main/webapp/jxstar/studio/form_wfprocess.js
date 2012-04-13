Jxstar.currentPage = function() {
	var config = {param:{},initpage:function(page, define){},eventcfg:{}};
	
	var prostateData = Jxstar.findComboData('prostate');
	var protypeData = Jxstar.findComboData('protype');
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
					{xtype:'textfield', fieldLabel:'过程编号', name:'wf_process__process_code', allowBlank:false, labelStyle:'color:#0000FF;', labelSeparator:'*', anchor:'100%', maxLength:20},
					{xtype:'trigger', fieldLabel:'功能名称', name:'wf_process__fun_name',
						anchor:'100%', triggerClass:'x-form-search-trigger',
						maxLength:50, allowBlank:false, labelStyle:'color:#0000FF;', labelSeparator:'*', editable:false,
						onTriggerClick: function() {
							var selcfg = {pageType:'combogrid', nodeId:'sel_fun', layoutPage:'/public/layout/layout_tree.js', sourceField:'fun_base.fun_name;fun_id', targetField:'wf_process.fun_name;fun_id', whereSql:"reg_type in ('main','treemain')", whereValue:'', whereType:'', isSame:'0', isShowData:'1', isMoreSelect:'0',isReadonly:'1',fieldName:'wf_process.fun_name'};
							Jxstar.createSelectWin(selcfg, this, 'node_wf_process_form');
						}},
					{xtype:'combo', fieldLabel:'状态', name:'wf_process__process_state', defaultval:'1',
						anchor:'100%', readOnly:true, editable:false,
						store: new Ext.data.SimpleStore({
							fields:['value','text'],
							data: prostateData
						}),
						emptyText: jx.star.select,
						mode: 'local',
						triggerAction: 'all',
						valueField: 'value',
						displayField: 'text',
						value: prostateData[0][0]},
					{xtype:'hidden', fieldLabel:'变更人ID', name:'wf_process__chg_userid', defaultval:'fun_getUserId()', anchor:'100%'}
				]
			},{
				border:false,
				columnWidth:0.33,
				layout:'form',
				items:[
					{xtype:'textfield', fieldLabel:'过程名称', name:'wf_process__process_name', allowBlank:false, labelStyle:'color:#0000FF;', labelSeparator:'*', anchor:'100%', maxLength:50},
					{xtype:'textfield', fieldLabel:'功能ID', name:'wf_process__fun_id', readOnly:true, anchor:'100%', maxLength:25},
					{xtype:'textfield', fieldLabel:'变更人', name:'wf_process__chg_user', defaultval:'fun_getUserName()', readOnly:true, anchor:'100%', maxLength:20},
					{xtype:'hidden', fieldLabel:'过程ID', name:'wf_process__process_id', anchor:'100%'}
				]
			},{
				border:false,
				columnWidth:0.33,
				layout:'form',
				items:[
					{xtype:'combo', fieldLabel:'过程类型', name:'wf_process__process_type', defaultval:'1',
						anchor:'100%', editable:false,
						store: new Ext.data.SimpleStore({
							fields:['value','text'],
							data: protypeData
						}),
						emptyText: jx.star.select,
						mode: 'local',
						triggerAction: 'all',
						valueField: 'value',
						displayField: 'text',
						value: protypeData[0][0]},
					{xtype:'textfield', fieldLabel:'版本号', name:'wf_process__version_no', defaultval:'1', readOnly:true, anchor:'100%', maxLength:20},
					{xtype:'datefield', fieldLabel:'变更日期', name:'wf_process__chg_date', defaultval:'fun_getToday()', format:'Y-m-d', anchor:'100%', readOnly:true}
				]
			}
			]
		}]
	}];
	
	config.param = {
		items: items,
		funid: 'wf_process'
	};

	
	
	return new Jxstar.FormNode(config);
}