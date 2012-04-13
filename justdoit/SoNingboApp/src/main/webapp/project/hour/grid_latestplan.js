Jxstar.currentPage = function() {
	var config = {param:{},initpage:function(page, define){},eventcfg:{}};

	var cols = [
	{col:{header:'项目名称', width:150, sortable:true, editable:true, hcss:'color:#3039b4;',
		editor:new Ext.form.TriggerField({
			maxLength:50,
			editable:false,
			triggerClass:'x-form-search-trigger', 
			onTriggerClick: function() {
				if (this.menu == null) {
					var selcfg = {pageType:'combogrid', nodeId:'query_project', layoutPage:'', sourceField:'ph_project.project_name;project_id', targetField:'ph_latestplan.project_name;project_id', whereSql:"", whereValue:'', whereType:'', isSame:'0', isShowData:'1', isMoreSelect:'0',isReadonly:'1',fieldName:'ph_latestplan.project_name'};
					this.menu = Jxstar.createComboMenu(this);
					Jxstar.createComboGrid(selcfg, this.menu, 'node_ph_latestplan_editgrid');
				}
				this.menu.show(this.el);
			}
		})}, field:{name:'ph_latestplan__project_name',type:'string'}},
	{col:{header:'任务类型', width:100, sortable:true, editable:true, hcss:'color:#3039b4;',
		editor:new Ext.form.TriggerField({
			maxLength:20,
			editable:false,
			triggerClass:'x-form-search-trigger', 
			onTriggerClick: function() {
				if (this.menu == null) {
					var selcfg = {pageType:'combogrid', nodeId:'ph_tasktype', layoutPage:'', sourceField:'ph_tasktype.task_type;task_type_code', targetField:'ph_latestplan.task_type;task_type_code', whereSql:"", whereValue:'', whereType:'', isSame:'0', isShowData:'1', isMoreSelect:'0',isReadonly:'1',fieldName:'ph_latestplan.task_type'};
					this.menu = Jxstar.createComboMenu(this);
					Jxstar.createComboGrid(selcfg, this.menu, 'node_ph_latestplan_editgrid');
				}
				this.menu.show(this.el);
			}
		})}, field:{name:'ph_latestplan__task_type',type:'string'}},
	{col:{header:'*任务描述', width:300, sortable:true, editable:true, hcss:'color:#0000ff;',
		editor:new Ext.form.TextField({
			maxLength:200, allowBlank:false
		})}, field:{name:'ph_latestplan__task_desc',type:'string'}},
	{col:{header:'*计划开始日期', width:100, sortable:true, defaultval:'fun_getToday()', editable:true, hcss:'color:#0000ff;',
		editor:new Ext.form.DateField({
			format: 'Y-m-d', allowBlank:false,
			minValue: '1900-01-01'
		}),
		renderer:function(value) {
			return value ? value.format('Y-m-d') : '';
		}}, field:{name:'ph_latestplan__start_date',type:'date'}},
	{col:{header:'*计划结束日期', width:100, sortable:true, editable:true, hcss:'color:#0000ff;',
		editor:new Ext.form.DateField({
			format: 'Y-m-d', allowBlank:false,
			minValue: '1900-01-01'
		}),
		renderer:function(value) {
			return value ? value.format('Y-m-d') : '';
		}}, field:{name:'ph_latestplan__end_date',type:'date'}},
	{col:{header:'计划人', width:71, sortable:true, defaultval:'fun_getUserName()', editable:false,
		editor:new Ext.form.TextField({
			maxLength:20
		})}, field:{name:'ph_latestplan__user_name',type:'string'}},
	{col:{header:'计划人ID', width:100, sortable:true, hidden:true, defaultval:'fun_getUserId()'}, field:{name:'ph_latestplan__user_id',type:'string'}},
	{col:{header:'主键', width:100, sortable:true, hidden:true}, field:{name:'ph_latestplan__plan_id',type:'string'}},
	{col:{header:'项目ID', width:100, sortable:true, hidden:true}, field:{name:'ph_latestplan__project_id',type:'string'}},
	{col:{header:'任务类型代号', width:100, sortable:true, hidden:true}, field:{name:'ph_latestplan__task_type_code',type:'string'}},
	{col:{header:'所属部门', width:100, sortable:true, defaultval:'fun_getDeptName()', editable:false,
		editor:new Ext.form.TextField({
			maxLength:50
		})}, field:{name:'ph_latestplan__dept_name',type:'string'}},
	{col:{header:'部门ID', width:100, sortable:true, hidden:true, defaultval:'fun_getDeptId()'}, field:{name:'ph_latestplan__dept_id',type:'string'}}
	];
	
	config.param = {
		cols: cols,
		sorts: null,
		hasQuery: '1',
		isedit: '1',
		isshow: '1',
		funid: 'ph_latestplan'
	};
	
	
		
	return new Jxstar.GridNode(config);
}