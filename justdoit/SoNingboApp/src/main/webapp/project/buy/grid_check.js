Jxstar.currentPage = function() {
	var config = {param:{},initpage:function(page, define){},eventcfg:{}};

	var auditData = Jxstar.findComboData('audit');
	var hasData = Jxstar.findComboData('has');
	var lineData = Jxstar.findComboData('line');

	var cols = [
	{col:{header:'记录状态', width:64, sortable:true, align:'center',
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
		}}, field:{name:'buy_check__auditing',type:'string'}},
	{col:{header:'清点日期', width:100, sortable:true, hidden:true, renderer:function(value) {
			return value ? value.format('Y-m-d') : '';
		}}, field:{name:'buy_check__check_date',type:'date'}},
	{col:{header:'验收单号', width:103, sortable:true}, field:{name:'buy_check__check_code',type:'string'}},
	{col:{header:'设备编号', width:126, sortable:true}, field:{name:'buy_check__device_code',type:'string'}},
	{col:{header:'采购品名称型号', width:211, sortable:true}, field:{name:'buy_check__device_name',type:'string'}},
	{col:{header:'供应商名称', width:156, sortable:true}, field:{name:'buy_check__provider_name',type:'string'}},
	{col:{header:'到货日期', width:100, sortable:true, renderer:function(value) {
			return value ? value.format('Y-m-d') : '';
		}}, field:{name:'buy_check__arrive_date',type:'date'}},
	{col:{header:'清点人', width:75, sortable:true}, field:{name:'buy_check__check_user',type:'string'}},
	{col:{header:'检查清点情况', width:291, sortable:true}, field:{name:'buy_check__check_desc',type:'string'}},
	{col:{header:'合格证书', width:100, sortable:true, hidden:true, align:'center',
		renderer:function(value){
			for (var i = 0; i < hasData.length; i++) {
				if (hasData[i][0] == value)
					return hasData[i][1];
			}
		}}, field:{name:'buy_check__has_right',type:'string'}},
	{col:{header:'装箱清单是否符合', width:100, sortable:true, hidden:true, align:'center',
		renderer:function(value){
			for (var i = 0; i < lineData.length; i++) {
				if (lineData[i][0] == value)
					return lineData[i][1];
			}
		}}, field:{name:'buy_check__has_zx',type:'string'}},
	{col:{header:'有无说明书', width:100, sortable:true, hidden:true, align:'center',
		renderer:function(value){
			for (var i = 0; i < hasData.length; i++) {
				if (hasData[i][0] == value)
					return hasData[i][1];
			}
		}}, field:{name:'buy_check__has_use',type:'string'}},
	{col:{header:'有无损伤', width:100, sortable:true, hidden:true}, field:{name:'buy_check__has_fai',type:'string'}},
	{col:{header:'备附件', width:100, sortable:true, hidden:true}, field:{name:'buy_check__has_attach',type:'string'}},
	{col:{header:'其它情况', width:100, sortable:true, hidden:true}, field:{name:'buy_check__has_other',type:'string'}},
	{col:{header:'清查结论', width:100, sortable:true, hidden:true}, field:{name:'buy_check__check_result',type:'string'}},
	{col:{header:'验收部门', width:100, sortable:true, hidden:true}, field:{name:'buy_check__dept_name',type:'string'}},
	{col:{header:'供应商ID', width:100, sortable:true, hidden:true}, field:{name:'buy_check__provider_id',type:'string'}},
	{col:{header:'主键', width:100, sortable:true, hidden:true}, field:{name:'buy_check__check_id',type:'string'}},
	{col:{header:'验收部门ID', width:100, sortable:true, hidden:true}, field:{name:'buy_check__dept_id',type:'string'}},
	{col:{header:'清点人ID', width:100, sortable:true, hidden:true}, field:{name:'buy_check__check_userid',type:'string'}}
	];
	
	config.param = {
		cols: cols,
		sorts: null,
		hasQuery: '1',
		isedit: '0',
		isshow: '1',
		funid: 'buy_check'
	};
	
	
		
	return new Jxstar.GridNode(config);
}