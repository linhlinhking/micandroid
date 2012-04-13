Jxstar.currentPage = function() {
	var config = {param:{},initpage:function(page, define){},eventcfg:{}};

	var cols = [
	{col:{header:'项目编号', width:103, sortable:true}, field:{name:'ph_project__project_code',type:'string'}},
	{col:{header:'项目名称', width:237, sortable:true}, field:{name:'ph_project__project_name',type:'string'}},
	{col:{header:'归属部门', width:100, sortable:true}, field:{name:'ph_project__dept_name',type:'string'}},
	{col:{header:'立项日期', width:100, sortable:true, renderer:function(value) {
			return value ? value.format('Y-m-d') : '';
		}}, field:{name:'ph_project__create_date',type:'date'}},
	{col:{header:'项目经理', width:78, sortable:true}, field:{name:'ph_project__pm_user',type:'string'}},
	{col:{header:'技术方案简述', width:285, sortable:true}, field:{name:'ph_project__tech_desc',type:'string'}},
	{col:{header:'部门ID', width:100, sortable:true, hidden:true}, field:{name:'ph_project__dept_id',type:'string'}},
	{col:{header:'项目ID', width:100, sortable:true, hidden:true}, field:{name:'ph_project__project_id',type:'string'}},
	{col:{header:'项目经理ID', width:100, sortable:true, hidden:true}, field:{name:'ph_project__pm_userid',type:'string'}}
	];
	
	config.param = {
		cols: cols,
		sorts: null,
		hasQuery: '1',
		isedit: '0',
		isshow: '1',
		funid: 'ph_project'
	};
	
	
		
	return new Jxstar.GridNode(config);
}