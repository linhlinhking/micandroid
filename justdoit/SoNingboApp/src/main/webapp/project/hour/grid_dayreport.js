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
		}}, field:{name:'ph_dayreport__auditing',type:'string'}},
	{col:{header:'报告日期', width:127, sortable:true, renderer:function(value) {
			return value ? value.format('Y-m-d') : '';
		}}, field:{name:'ph_dayreport__report_date',type:'date'}},
	{col:{header:'填报日报', width:119, sortable:true, hidden:true, renderer:function(value) {
			return value ? value.format('Y-m-d') : '';
		}}, field:{name:'ph_dayreport__write_date',type:'date'}},
	{col:{header:'主要问题', width:384, sortable:true}, field:{name:'ph_dayreport__work_question',type:'string'}},
	{col:{header:'填报人', width:90, sortable:true}, field:{name:'ph_dayreport__user_name',type:'string'}},
	{col:{header:'填报人ID', width:100, sortable:true, hidden:true}, field:{name:'ph_dayreport__user_id',type:'string'}},
	{col:{header:'日报ID', width:100, sortable:true, hidden:true}, field:{name:'ph_dayreport__dreport_id',type:'string'}},
	{col:{header:'所属部门', width:113, sortable:true}, field:{name:'ph_dayreport__dept_name',type:'string'}},
	{col:{header:'部门ID', width:100, sortable:true, hidden:true}, field:{name:'ph_dayreport__dept_id',type:'string'}}
	];
	
	config.param = {
		cols: cols,
		sorts: null,
		hasQuery: '1',
		isedit: '0',
		isshow: '1',
		funid: 'ph_dayreport'
	};
	
	config.eventcfg = {
		showlast: function(){
			Jxstar.createNode('ph_latestplan');
		},
		
		createday: function(){
			JxUtil.createReport();
		}
	};
		
	return new Jxstar.GridNode(config);
}