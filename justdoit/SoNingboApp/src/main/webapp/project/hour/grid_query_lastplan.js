Jxstar.currentPage = function() {
	var config = {param:{},initpage:function(page, define){},eventcfg:{}};

	var cols = [
	{col:{header:'项目名称', width:150, sortable:true}, field:{name:'ph_latestplan__project_name',type:'string'}},
	{col:{header:'任务类型', width:100, sortable:true}, field:{name:'ph_latestplan__task_type',type:'string'}},
	{col:{header:'任务描述', width:300, sortable:true}, field:{name:'ph_latestplan__task_desc',type:'string'}},
	{col:{header:'计划开始日期', width:100, sortable:true, renderer:function(value) {
			return value ? value.format('Y-m-d') : '';
		}}, field:{name:'ph_latestplan__start_date',type:'date'}},
	{col:{header:'计划结束日期', width:100, sortable:true, renderer:function(value) {
			return value ? value.format('Y-m-d') : '';
		}}, field:{name:'ph_latestplan__end_date',type:'date'}},
	{col:{header:'计划人', width:71, sortable:true}, field:{name:'ph_latestplan__user_name',type:'string'}},
	{col:{header:'计划人ID', width:100, sortable:true, hidden:true}, field:{name:'ph_latestplan__user_id',type:'string'}},
	{col:{header:'主键', width:100, sortable:true, hidden:true}, field:{name:'ph_latestplan__plan_id',type:'string'}},
	{col:{header:'项目ID', width:100, sortable:true, hidden:true}, field:{name:'ph_latestplan__project_id',type:'string'}},
	{col:{header:'任务类型代号', width:100, sortable:true, hidden:true}, field:{name:'ph_latestplan__task_type_code',type:'string'}},
	{col:{header:'所属部门', width:100, sortable:true}, field:{name:'ph_latestplan__dept_name',type:'string'}},
	{col:{header:'部门ID', width:100, sortable:true, hidden:true}, field:{name:'ph_latestplan__dept_id',type:'string'}}
	];
	
	config.param = {
		cols: cols,
		sorts: null,
		hasQuery: '0',
		isedit: '0',
		isshow: '1',
		funid: 'query_lastplan'
	};
	
	
		
	return new Jxstar.GridNode(config);
}