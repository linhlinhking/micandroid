Jxstar.currentPage = function() {
	var config = {param:{},initpage:function(page, define){},eventcfg:{}};

	var auditData = Jxstar.findComboData('audit');

	var cols = [
	{col:{header:'记录状态', width:60, sortable:true, align:'center',
		editable:false,
		editor:new Ext.form.ComboBox({
			store: new Ext.data.SimpleStore({
				fields:['value','text'],
				data: auditData
			}),
			emptyText: jx.star.select,
			mode: 'local',
			triggerAction: 'all',
			valueField: 'value',
			displayField: 'text',
			editable:false,
			value: auditData[0][0]
		}),
		renderer:function(value){
			for (var i = 0; i < auditData.length; i++) {
				if (auditData[i][0] == value)
					return auditData[i][1];
			}
		}}, field:{name:'buy_provider__auditing',type:'string'}},
	{col:{header:'供应商编码', width:100, sortable:true}, field:{name:'buy_provider__provider_code',type:'string'}},
	{col:{header:'供应商名称', width:205, sortable:true}, field:{name:'buy_provider__provider_name',type:'string'}},
	{col:{header:'供应商地址', width:238, sortable:true}, field:{name:'buy_provider__address',type:'string'}},
	{col:{header:'登记人', width:78, sortable:true}, field:{name:'buy_provider__write_user',type:'string'}},
	{col:{header:'联系人', width:69, sortable:true}, field:{name:'buy_provider__limit_user',type:'string'}},
	{col:{header:'供应商电话', width:100, sortable:true}, field:{name:'buy_provider__phone',type:'string'}},
	{col:{header:'供应商服务内容', width:299, sortable:true}, field:{name:'buy_provider__provide_server',type:'string'}},
	{col:{header:'服务质量评价', width:100, sortable:true, hidden:true}, field:{name:'buy_provider__provide_quality',type:'string'}},
	{col:{header:'主键', width:100, sortable:true, hidden:true}, field:{name:'buy_provider__provider_id',type:'string'}},
	{col:{header:'登记日期', width:100, sortable:true, hidden:true, renderer:function(value) {
			return value ? value.format('Y-m-d') : '';
		}}, field:{name:'buy_provider__write_date',type:'date'}},
	{col:{header:'登记人ID', width:100, sortable:true, hidden:true}, field:{name:'buy_provider__write_userid',type:'string'}}
	];
	
	config.param = {
		cols: cols,
		sorts: null,
		hasQuery: '1',
		isedit: '0',
		isshow: '1',
		funid: 'buy_provider'
	};
	
	
		
	return new Jxstar.GridNode(config);
}