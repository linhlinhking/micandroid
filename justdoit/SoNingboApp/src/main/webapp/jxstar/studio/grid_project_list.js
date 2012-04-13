Jxstar.currentPage = function() {
	var config = {param:{},initpage:function(page, define){},eventcfg:{}};

	var cols = [
	{col:{header:'*项目代码', width:100, sortable:true, editable:true, hcss:'color:#0000ff;',
		editor:new Ext.form.TextField({
			maxLength:50, allowBlank:false
		})}, field:{name:'project_list__project_code',type:'string'}},
	{col:{header:'*项目名称', width:148, sortable:true, editable:true, hcss:'color:#0000ff;',
		editor:new Ext.form.TextField({
			maxLength:100, allowBlank:false
		})}, field:{name:'project_list__project_name',type:'string'}},
	{col:{header:'*项目文件路径', width:251, sortable:true, editable:true, hcss:'color:#0000ff;',
		editor:new Ext.form.TextField({
			maxLength:200, allowBlank:false
		})}, field:{name:'project_list__project_path',type:'string'}},
	{col:{header:'项目应用路径', width:263, sortable:true, editable:true, hcss:'color:#3039b4;',
		editor:new Ext.form.TextField({
			maxLength:200
		})}, field:{name:'project_list__project_app',type:'string'}},
	{col:{header:'项目ID', width:100, sortable:true, hidden:true}, field:{name:'project_list__project_id',type:'string'}}
	];
	
	config.param = {
		cols: cols,
		sorts: null,
		hasQuery: '1',
		isedit: '0',
		isshow: '1',
		funid: 'project_list'
	};
	
	config.eventcfg = {
		showsub: function(){
			var records = this.grid.getSelectionModel().getSelections();
			if (!JxUtil.selectone(records)) return;

			//过滤条件
			var where_sql = 'project_src.project_id = ?';
			var where_type = 'string';
			var where_value = records[0].get('project_list__project_id');
			
			//加载数据
			var hdcall = function(grid) {
				//显示数据
				JxUtil.delay(500, function(){
					//设置外键值
					grid.fkValue = where_value;

					Jxstar.loadData(grid, {where_sql:where_sql, where_value:where_value, where_type:where_type});
				});
			};

			//显示数据
			Jxstar.showData({
				filename: 'jxstar.studio.GridProjectSrc',
				title: '项目数据源', 
				width: 700, 
				height: 600,
				callback: hdcall
			});
		},

		selproject: function(){
			var records = this.grid.getSelectionModel().getSelections();
			if (!JxUtil.selectone(records)) return;

			var keyid = records[0].get(this.define.pkcol);

			var params = 'keyid='+ keyid +'&funid='+ this.define.nodeid;
			
			params += '&pagetype=editgrid&eventcode=selproject';

			//保存当前项目信息
			var hdcall = function(data) {
				Jxstar.session['project_id'] = records[0].get('project_list__project_id');
				Jxstar.session['project_code'] = records[0].get('project_list__project_code');
				Jxstar.session['project_name'] = records[0].get('project_list__project_name');
				Jxstar.session['project_path'] = records[0].get('project_list__project_path');
			};

			Request.postRequest(params, hdcall);
		}
	};
		
	return new Jxstar.GridNode(config);
}