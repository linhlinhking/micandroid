Jxstar.currentPage = function() {
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
				columnWidth:0.33,
				layout:'form',
				items:[
					{xtype:'textfield', fieldLabel:'项目编号', name:'ph_project__project_code', anchor:'100%', maxLength:20},
					{xtype:'trigger', fieldLabel:'归属部门', name:'ph_project__dept_name', defaultval:'fun_getDeptName()',
						anchor:'100%', triggerClass:'x-form-search-trigger',
						maxLength:50, editable:false,
						onTriggerClick: function() {
							var selcfg = {pageType:'combogrid', nodeId:'sys_dept', layoutPage:'/public/layout/layout_tree.js', sourceField:'sys_dept.dept_name;dept_id', targetField:'ph_project.dept_name;dept_id', whereSql:"", whereValue:'', whereType:'', isSame:'0', isShowData:'1', isMoreSelect:'0',isReadonly:'1',fieldName:'ph_project.dept_name'};
							Jxstar.createSelectWin(selcfg, this, 'node_ph_project_form');
						}},
					{xtype:'hidden', fieldLabel:'项目经理ID', name:'ph_project__pm_userid', anchor:'100%'}
				]
			},{
				border:false,
				columnWidth:0.33,
				layout:'form',
				items:[
					{xtype:'textfield', fieldLabel:'项目名称', name:'ph_project__project_name', allowBlank:false, labelStyle:'color:#0000FF;', labelSeparator:'*', anchor:'100%', maxLength:50},
					{xtype:'trigger', fieldLabel:'项目经理', name:'ph_project__pm_user',
						anchor:'100%', triggerClass:'x-form-search-trigger',
						maxLength:20, editable:false,
						onTriggerClick: function() {
							var selcfg = {pageType:'combogrid', nodeId:'sys_user', layoutPage:'/public/layout/layout_tree.js', sourceField:'sys_user.user_name;user_id', targetField:'ph_project.pm_user;pm_userid', whereSql:"", whereValue:'', whereType:'', isSame:'0', isShowData:'1', isMoreSelect:'0',isReadonly:'1',fieldName:'ph_project.pm_user'};
							Jxstar.createSelectWin(selcfg, this, 'node_ph_project_form');
						}},
					{xtype:'hidden', fieldLabel:'部门ID', name:'ph_project__dept_id', defaultval:'fun_getDeptId()', anchor:'100%'}
				]
			},{
				border:false,
				columnWidth:0.33,
				layout:'form',
				items:[
					{xtype:'datefield', fieldLabel:'立项日期', name:'ph_project__create_date', format:'Y-m-d', anchor:'100%'},
					{xtype:'hidden', fieldLabel:'项目ID', name:'ph_project__project_id', anchor:'100%'}
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
					{xtype:'textarea', fieldLabel:'技术方案简述', name:'ph_project__tech_desc', width:'100%', height:48, maxLength:200}
				]
			}
			]
		}]
	}];
	
	config.param = {
		items: items,
		funid: 'ph_project'
	};

	
	
	return new Jxstar.FormNode(config);
}