Jxstar.currentPage = function() {
	var config = {param:{},initpage:function(page, define){},eventcfg:{}};

	var cols = [
	{col:{header:'*控件代码', width:125, sortable:true, editable:true, hcss:'color:#0000ff;',
		editor:new Ext.form.TextField({
			maxLength:20, allowBlank:false
		})}, field:{name:'funall_control__control_code',type:'string'}},
	{col:{header:'*控件名称', width:136, sortable:true, editable:true, hcss:'color:#0000ff;',
		editor:new Ext.form.TextField({
			maxLength:100, allowBlank:false
		})}, field:{name:'funall_control__control_name',type:'string'}},
	{col:{header:'*控件值', width:100, sortable:true, editable:true, hcss:'color:#0000ff;',
		editor:new Ext.form.TextField({
			maxLength:20, allowBlank:false
		})}, field:{name:'funall_control__value_data',type:'string'}},
	{col:{header:'*显示值', width:119, sortable:true, editable:true, hcss:'color:#0000ff;',
		editor:new Ext.form.TextField({
			maxLength:50, allowBlank:false
		})}, field:{name:'funall_control__display_data',type:'string'}},
	{col:{header:'*序号', width:63, sortable:true, align:'right',
		editable:true, hcss:'color:#0000ff;',
		editor:new Ext.form.NumberField({
			maxLength:12, allowBlank:false,
			allowNegative: false
		}),renderer:JxUtil.formatInt()}, field:{name:'funall_control__control_index',type:'int'}},
	{col:{header:'控件ID', width:100, sortable:true, hidden:true}, field:{name:'funall_control__control_id',type:'string'}},
	{col:{header:'控件类型', width:100, sortable:true, hidden:true, defaultval:'combo'}, field:{name:'funall_control__control_type',type:'string'}}
	];
	
	config.param = {
		cols: cols,
		sorts: null,
		hasQuery: '1',
		isedit: '1',
		isshow: '1',
		funid: 'sys_control'
	};
	
	config.eventcfg = {
		createjs: function(){
			//取选择记录的主键值
			var params = 'funid='+ this.define.nodeid;
			
			//设置请求的参数
			params += '&pagetype=editgrid&eventcode=createjs&projectpath=' + Jxstar.session['project_path'];

			//生成文件后自动加载该文件
			var hdcall = function() {				ComboData = null;
				Request.loadJS('/public/locale/combo-lang-zh.js');
			};

			//发送请求
			Request.postRequest(params, hdcall);
		}
	};
		
	return new Jxstar.GridNode(config);
}