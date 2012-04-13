Jxstar.currentPage = function() {
	var config = {param:{},initpage:function(page, define){},eventcfg:{}};

	var cols = [
	{col:{header:'*任务类型', width:148, sortable:true, editable:true, hcss:'color:#0000ff;',
		editor:new Ext.form.TextField({
			maxLength:20, allowBlank:false
		})}, field:{name:'ph_tasktype__task_type',type:'string'}},
	{col:{header:'*类型代号', width:100, sortable:true, editable:true, hcss:'color:#0000ff;',
		editor:new Ext.form.TextField({
			maxLength:20, allowBlank:false
		})}, field:{name:'ph_tasktype__task_type_code',type:'string'}},
	{col:{header:'序号', width:58, sortable:true, align:'right',
		editable:true, hcss:'color:#3039b4;',
		editor:new Ext.form.NumberField({
			maxLength:12,
			allowNegative: false
		}),renderer:JxUtil.formatInt()}, field:{name:'ph_tasktype__type_seq',type:'float'}},
	{col:{header:'主键', width:100, sortable:true, hidden:true}, field:{name:'ph_tasktype__tasktype_id',type:'string'}}
	];
	
	config.param = {
		cols: cols,
		sorts: null,
		hasQuery: '0',
		isedit: '1',
		isshow: '1',
		funid: 'ph_tasktype'
	};
	
	
		
	return new Jxstar.GridNode(config);
}