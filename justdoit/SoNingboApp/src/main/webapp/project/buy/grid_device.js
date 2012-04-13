Jxstar.currentPage = function() {
	var config = {param:{},initpage:function(page, define){},eventcfg:{}};

	var auditData = Jxstar.findComboData('audit');

	var cols = [
	{col:{header:'记录状态', width:68, sortable:true, align:'center',
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
		}}, field:{name:'buy_device__auditing',type:'string'}},
	{col:{header:'设备编号', width:100, sortable:true}, field:{name:'buy_device__device_code',type:'string'}},
	{col:{header:'设备名称', width:200, sortable:true}, field:{name:'buy_device__device_name',type:'string'}},
	{col:{header:'规格型号', width:140, sortable:true}, field:{name:'buy_device__device_size',type:'string'}},
	{col:{header:'制造商名称', width:100, sortable:true}, field:{name:'buy_device__factory',type:'string'}},
	{col:{header:'接收日期', width:100, sortable:true, renderer:function(value) {
			return value ? value.format('Y-m-d') : '';
		}}, field:{name:'buy_device__get_date',type:'date'}},
	{col:{header:'启用日期', width:100, sortable:true, renderer:function(value) {
			return value ? value.format('Y-m-d') : '';
		}}, field:{name:'buy_device__use_date',type:'date'}},
	{col:{header:'放置地点', width:188, sortable:true}, field:{name:'buy_device__device_addr',type:'string'}},
	{col:{header:'接收时的状态', width:100, sortable:true, hidden:true}, field:{name:'buy_device__get_state',type:'string'}},
	{col:{header:'校准（检定）/验证的日期', width:100, sortable:true, hidden:true, renderer:function(value) {
			return value ? value.format('Y-m-d') : '';
		}}, field:{name:'buy_device__check_date',type:'date'}},
	{col:{header:'下次校准（检定）/验证日期', width:100, sortable:true, hidden:true, renderer:function(value) {
			return value ? value.format('Y-m-d') : '';
		}}, field:{name:'buy_device__next_date',type:'date'}},
	{col:{header:'维护计划记录', width:100, sortable:true, hidden:true}, field:{name:'buy_device__maint_plan',type:'string'}},
	{col:{header:'填表人', width:100, sortable:true, hidden:true}, field:{name:'buy_device__write_user',type:'string'}},
	{col:{header:'填表日期', width:100, sortable:true, hidden:true, renderer:function(value) {
			return value ? value.format('Y-m-d') : '';
		}}, field:{name:'buy_device__write_date',type:'date'}},
	{col:{header:'维护历史记录', width:100, sortable:true, hidden:true}, field:{name:'buy_device__maint_his',type:'string'}},
	{col:{header:'归口部门', width:122, sortable:true}, field:{name:'buy_device__dept_name',type:'string'}},
	{col:{header:'填表人ID', width:100, sortable:true, hidden:true}, field:{name:'buy_device__write_userid',type:'string'}},
	{col:{header:'主键', width:100, sortable:true, hidden:true}, field:{name:'buy_device__device_id',type:'string'}},
	{col:{header:'归口部门ID', width:100, sortable:true, hidden:true}, field:{name:'buy_device__dept_id',type:'string'}}
	];
	
	config.param = {
		cols: cols,
		sorts: null,
		hasQuery: '1',
		isedit: '0',
		isshow: '1',
		funid: 'buy_device'
	};
	
	
		
	return new Jxstar.GridNode(config);
}