﻿Jxstar.currentPage = function() {
	var config = {param:{},initpage:function(page, define){},eventcfg:{}};
	
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
					{xtype:'trigger', fieldLabel:'分配用户', name:'wf_user__user_name',
						anchor:'100%', triggerClass:'x-form-search-trigger',
						maxLength:20, allowBlank:false, labelStyle:'color:#0000FF;', labelSeparator:'*', editable:false,
						onTriggerClick: function() {
							var selcfg = {pageType:'combogrid', nodeId:'sys_user', layoutPage:'/public/layout/layout_tree.js', sourceField:'sys_user.user_name;user_id', targetField:'wf_user.user_name;user_id', whereSql:"sys_user.is_novalid = '0'", whereValue:'', whereType:'', isSame:'1', isShowData:'1', isMoreSelect:'0',isReadonly:'1',fieldName:'wf_user.user_name'};
							Jxstar.createSelectWin(selcfg, this, 'node_wf_user_form');
						}},
					{xtype:'textfield', fieldLabel:'部门名称', name:'sys_dept__dept_name', readOnly:true, anchor:'100%', maxLength:100},
					{xtype:'trigger', fieldLabel:'升级用户', name:'wf_user__up_user',
						anchor:'100%', triggerClass:'x-form-search-trigger',
						maxLength:20, editable:false,
						onTriggerClick: function() {
							var selcfg = {pageType:'combogrid', nodeId:'sys_user', layoutPage:'/public/layout/layout_tree.js', sourceField:'sys_user.user_name;user_id', targetField:'wf_user.up_user;up_userid', whereSql:"sys_user.is_novalid = '0'", whereValue:'', whereType:'', isSame:'0', isShowData:'1', isMoreSelect:'0',isReadonly:'1',fieldName:'wf_user.up_user'};
							Jxstar.createSelectWin(selcfg, this, 'node_wf_user_form');
						}},
					{xtype:'hidden', fieldLabel:'主键', name:'wf_user__wfuser_id', anchor:'100%'},
					{xtype:'hidden', fieldLabel:'用户ID', name:'wf_user__user_id', anchor:'100%'},
					{xtype:'hidden', fieldLabel:'用户组', name:'wf_user__group_name', anchor:'100%'},
					{xtype:'hidden', fieldLabel:'节点ID', name:'wf_user__node_id', anchor:'100%'}
				]
			},{
				border:false,
				columnWidth:0.495,
				layout:'form',
				items:[
					{xtype:'textfield', fieldLabel:'用户编码', name:'sys_user__user_code', readOnly:true, anchor:'100%', maxLength:100},
					{xtype:'textfield', fieldLabel:'部门编码', name:'sys_dept__dept_code', readOnly:true, anchor:'100%', maxLength:100},
					{xtype:'hidden', fieldLabel:'是否定制类', name:'wf_user__use_class', defaultval:'0', anchor:'100%'},
					{xtype:'hidden', fieldLabel:'升级用户ID', name:'wf_user__up_userid', anchor:'100%'},
					{xtype:'hidden', fieldLabel:'任务属性ID', name:'wf_user__nodeattr_id', anchor:'100%'},
					{xtype:'hidden', fieldLabel:'过程ID', name:'wf_user__process_id', anchor:'100%'},
					{xtype:'hidden', fieldLabel:'部门ID', name:'sys_dept__dept_id', anchor:'100%'},
					{xtype:'hidden', fieldLabel:'用户组ID', name:'wf_user__group_id', anchor:'100%'}
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
					{xtype:'textarea', fieldLabel:'分配条件', name:'wf_user__condition', width:'100%', height:72, maxLength:200}
				]
			}
			]
		}]
	}];
	
	config.param = {
		items: items,
		funid: 'wf_user'
	};

	config.initpage = function(formNode){
		var event = formNode.event;
		
		event.on('beforecreate', function(event) {
			//取过程ID
			var fpage = Ext.getCmp('node_wf_nodeattr_form');
			var nodeId = fpage.getForm().get('wf_nodeattr__node_id');
			var processId = fpage.getForm().get('wf_nodeattr__process_id');
			
			//给分配用户设置过程ID
			formNode.page.getForm().set('wf_user__node_id', nodeId);
			formNode.page.getForm().set('wf_user__process_id', processId);
		});
	};
	
	return new Jxstar.FormNode(config);
}