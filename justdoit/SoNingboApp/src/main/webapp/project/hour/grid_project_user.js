Jxstar.currentPage = function() {
	var config = {param:{},initpage:function(page, define){},eventcfg:{}};

	var cols = [
	{col:{header:'*成员姓名', width:110, sortable:true, editable:true, hcss:'color:#0000ff;',
		editor:new Ext.form.TriggerField({
			maxLength:20,
			editable:false, allowBlank:false,
			triggerClass:'x-form-search-trigger', 
			onTriggerClick: function() {
				var selcfg = {pageType:'combogrid', nodeId:'sys_user', layoutPage:'/public/layout/layout_tree.js', sourceField:'sys_user.user_name;user_id', targetField:'ph_projectuser.user_name;user_id', whereSql:"", whereValue:'', whereType:'', isSame:'0', isShowData:'1', isMoreSelect:'0',isReadonly:'1',fieldName:'ph_projectuser.user_name'};
				Jxstar.createSelectWin(selcfg, this, 'node_ph_projectuser_editgrid');
			}
		})}, field:{name:'ph_projectuser__user_name',type:'string'}},
	{col:{header:'项目角色', width:119, sortable:true, editable:true, hcss:'color:#3039b4;',
		editor:new Ext.form.TextField({
			maxLength:50
		})}, field:{name:'ph_projectuser__user_role',type:'string'}},
	{col:{header:'职责描述', width:214, sortable:true, editable:true, hcss:'color:#3039b4;',
		editor:new Ext.form.TextField({
			maxLength:200
		})}, field:{name:'ph_projectuser__task_desc',type:'string'}},
	{col:{header:'参与时间', width:127, sortable:true, editable:true, hcss:'color:#3039b4;',
		editor:new Ext.form.DateField({
			format: 'Y-m-d',
			minValue: '1900-01-01'
		}),
		renderer:function(value) {
			return value ? value.format('Y-m-d') : '';
		}}, field:{name:'ph_projectuser__start_date',type:'date'}},
	{col:{header:'退出时间', width:125, sortable:true, editable:true, hcss:'color:#3039b4;',
		editor:new Ext.form.DateField({
			format: 'Y-m-d',
			minValue: '1900-01-01'
		}),
		renderer:function(value) {
			return value ? value.format('Y-m-d') : '';
		}}, field:{name:'ph_projectuser__end_date',type:'date'}},
	{col:{header:'项目ID', width:100, sortable:true, hidden:true}, field:{name:'ph_projectuser__project_id',type:'string'}},
	{col:{header:'主键', width:100, sortable:true, hidden:true}, field:{name:'ph_projectuser__member_id',type:'string'}},
	{col:{header:'成员ID', width:100, sortable:true, hidden:true}, field:{name:'ph_projectuser__user_id',type:'string'}}
	];
	
	config.param = {
		cols: cols,
		sorts: null,
		hasQuery: '1',
		isedit: '1',
		isshow: '0',
		funid: 'ph_projectuser'
	};
	
	
		
	return new Jxstar.GridNode(config);
}