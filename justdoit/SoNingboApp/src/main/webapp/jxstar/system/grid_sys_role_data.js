Jxstar.currentPage = function() {
	var config = {param:{},initpage:function(page, define){},eventcfg:{}};

	var cols = [
	{col:{header:'分类名称', width:147, sortable:true}, field:{name:'sys_datatype__dtype_name',type:'string'}},
	{col:{header:'权限字段', width:139, sortable:true}, field:{name:'sys_datatype__dtype_field',type:'string'}},
	{col:{header:'设置ID', width:100, sortable:true, hidden:true}, field:{name:'sys_role_data__role_data_id',type:'string'}},
	{col:{header:'角色功能ID', width:100, sortable:true, hidden:true}, field:{name:'sys_role_data__role_fun_id',type:'string'}},
	{col:{header:'数据权限ID', width:100, sortable:true, hidden:true}, field:{name:'sys_role_data__dtype_id',type:'string'}}
	];
	
	config.param = {
		cols: cols,
		sorts: null,
		hasQuery: '0',
		isedit: '0',
		isshow: '0',
		funid: 'sys_role_data'
	};
	
	config.eventcfg = {		
		dataImportParam: function() {
			var roleId = this.grid.fkValue;			var options = {				whereSql: 'dtype_id not in (select dtype_id from sys_role_data where role_fun_id = ?)',				whereValue: roleId,				whereType: 'string'			};			return options;		}		
	};
		
	return new Jxstar.GridNode(config);
}