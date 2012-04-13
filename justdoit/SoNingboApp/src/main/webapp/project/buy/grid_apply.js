Jxstar.currentPage = function() {
	var config = {param:{},initpage:function(page, define){},eventcfg:{}};

	var auditData = Jxstar.findComboData('audit');

	var cols = [
	{col:{header:'记录状态', width:72, sortable:true, align:'center',
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
		}}, field:{name:'buy_apply__auditing',type:'string'}},
	{col:{header:'申请单号', width:100, sortable:true}, field:{name:'buy_apply__apply_code',type:'string'}},
	{col:{header:'采购产品信息', width:295, sortable:true}, field:{name:'buy_apply__device_name',type:'string'}},
	{col:{header:'采购产品数量', width:144, sortable:true}, field:{name:'buy_apply__device_num',type:'string'}},
	{col:{header:'估算费用', width:100, sortable:true}, field:{name:'buy_apply__device_money',type:'string'}},
	{col:{header:'提出人', width:68, sortable:true}, field:{name:'buy_apply__apply_user',type:'string'}},
	{col:{header:'申请日期', width:100, sortable:true, renderer:function(value) {
			return value ? value.format('Y-m-d') : '';
		}}, field:{name:'buy_apply__apply_date',type:'date'}},
	{col:{header:'部门接口人', width:77, sortable:true}, field:{name:'buy_apply__get_user',type:'string'}},
	{col:{header:'采购原因', width:100, sortable:true, hidden:true}, field:{name:'buy_apply__apply_cause',type:'string'}},
	{col:{header:'其它说明', width:100, sortable:true, hidden:true}, field:{name:'buy_apply__apply_memo',type:'string'}},
	{col:{header:'申请部门', width:100, sortable:true}, field:{name:'buy_apply__dept_name',type:'string'}},
	{col:{header:'提出人ID', width:100, sortable:true, hidden:true}, field:{name:'buy_apply__apply_userid',type:'string'}},
	{col:{header:'申请部门ID', width:100, sortable:true, hidden:true}, field:{name:'buy_apply__dept_id',type:'string'}},
	{col:{header:'主键', width:100, sortable:true, hidden:true}, field:{name:'buy_apply__apply_id',type:'string'}}
	];
	
	config.param = {
		cols: cols,
		sorts: null,
		hasQuery: '1',
		isedit: '0',
		isshow: '1',
		funid: 'buy_apply'
	};
	
	
		
	return new Jxstar.GridNode(config);
}