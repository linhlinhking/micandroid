Jxstar.currentPage = function() {
	var config = {param:{},initpage:function(page, define){},eventcfg:{}};

	var cols = [
	{col:{header:'项目代号', width:100, sortable:true}, field:{name:'project_list__project_code',type:'string'}},
	{col:{header:'项目名称', width:167, sortable:true}, field:{name:'project_list__project_name',type:'string'}},
	{col:{header:'文件路径', width:319, sortable:true}, field:{name:'project_list__project_path',type:'string'}},
	{col:{header:'项目ID', width:100, sortable:true, hidden:true}, field:{name:'project_list__project_id',type:'string'}}
	];
	
	config.param = {
		cols: cols,
		sorts: null,
		hasQuery: '1',
		isedit: '0',
		isshow: '1',
		funid: 'project_sel'
	};
	
	
		
	return new Jxstar.GridNode(config);
}