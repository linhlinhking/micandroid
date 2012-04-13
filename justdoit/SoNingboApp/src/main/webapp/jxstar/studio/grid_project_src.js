Jxstar.currentPage = function() {
	var config = {param:{},initpage:function(page, define){},eventcfg:{}};

	var cols = [
	{col:{header:'*数据源代码', width:100, sortable:true, editable:true, hcss:'color:#0000ff;',
		editor:new Ext.form.TextField({
			maxLength:50, allowBlank:false
		})}, field:{name:'project_src__src_code',type:'string'}},
	{col:{header:'*数据库类型', width:100, sortable:true, editable:true, hcss:'color:#0000ff;',
		editor:new Ext.form.TextField({
			maxLength:20, allowBlank:false
		})}, field:{name:'project_src__dbmstype',type:'string'}},
	{col:{header:'数据源描述', width:91, sortable:true, editable:true, hcss:'color:#3039b4;',
		editor:new Ext.form.TextField({
			maxLength:100
		})}, field:{name:'project_src__src_desc',type:'string'}},
	{col:{header:'主机IP', width:100, sortable:true, hidden:true}, field:{name:'project_src__host',type:'string'}},
	{col:{header:'数据库名', width:100, sortable:true, hidden:true}, field:{name:'project_src__dbname',type:'string'}},
	{col:{header:'*驱动类全名', width:262, sortable:true, editable:true, hcss:'color:#0000ff;',
		editor:new Ext.form.TextField({
			maxLength:50, allowBlank:false
		})}, field:{name:'project_src__drive_path',type:'string'}},
	{col:{header:'*用户名', width:100, sortable:true, editable:true, hcss:'color:#0000ff;',
		editor:new Ext.form.TextField({
			maxLength:50, allowBlank:false
		})}, field:{name:'project_src__username',type:'string'}},
	{col:{header:'*连接描述', width:388, sortable:true, editable:true, hcss:'color:#0000ff;',
		editor:new Ext.form.TextField({
			maxLength:200, allowBlank:false
		})}, field:{name:'project_src__jdbcurl',type:'string'}},
	{col:{header:'*密码', width:100, sortable:true, hidden:true}, field:{name:'project_src__password',type:'string'}},
	{col:{header:'数据源ID', width:100, sortable:true, hidden:true}, field:{name:'project_src__src_id',type:'string'}},
	{col:{header:'项目ID', width:100, sortable:true, hidden:true}, field:{name:'project_src__project_id',type:'string'}}
	];
	
	config.param = {
		cols: cols,
		sorts: null,
		hasQuery: '0',
		isedit: '1',
		isshow: '0',
		funid: 'project_src'
	};
	
	config.eventcfg = {		
		testds: function(){
			var records = this.grid.getSelectionModel().getSelections();
			if (!JxUtil.selectone(records)) return;

			var keyid = records[0].get(this.define.pkcol);

			var params = 'keyid='+ keyid +'&funid='+ this.define.nodeid;
			
			params += '&pagetype=editgrid&eventcode=testds';

			Request.postRequest(params, null);
		}
	};
		
	return new Jxstar.GridNode(config);
}