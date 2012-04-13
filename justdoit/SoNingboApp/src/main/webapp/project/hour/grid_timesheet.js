Jxstar.currentPage = function() {
	var config = {param:{},initpage:function(page, define){},eventcfg:{}};

	var cols = [
	{col:{header:'*项目名称', width:150, sortable:true, editable:true, hcss:'color:#0000ff;',
		editor:new Ext.form.TriggerField({
			maxLength:50,
			editable:false, allowBlank:false,
			triggerClass:'x-form-search-trigger', 
			onTriggerClick: function() {
				if (this.menu == null) {
					var selcfg = {pageType:'combogrid', nodeId:'query_project', layoutPage:'', sourceField:'ph_project.project_name;project_id', targetField:'ph_timesheet.project_name;project_id', whereSql:"", whereValue:'', whereType:'', isSame:'0', isShowData:'1', isMoreSelect:'0',isReadonly:'1',fieldName:'ph_timesheet.project_name'};
					this.menu = Jxstar.createComboMenu(this);
					Jxstar.createComboGrid(selcfg, this.menu, 'node_ph_timesheet_editgrid');
				}
				this.menu.show(this.el);
			}
		})}, field:{name:'ph_timesheet__project_name',type:'string'}},
	{col:{header:'*任务类型', width:100, sortable:true, editable:true, hcss:'color:#0000ff;',
		editor:new Ext.form.TriggerField({
			maxLength:20,
			editable:false, allowBlank:false,
			triggerClass:'x-form-search-trigger', 
			onTriggerClick: function() {
				if (this.menu == null) {
					var selcfg = {pageType:'combogrid', nodeId:'ph_tasktype', layoutPage:'', sourceField:'ph_tasktype.task_type;task_type_code', targetField:'ph_timesheet.task_type;task_type_code', whereSql:"", whereValue:'', whereType:'', isSame:'0', isShowData:'1', isMoreSelect:'0',isReadonly:'1',fieldName:'ph_timesheet.task_type'};
					this.menu = Jxstar.createComboMenu(this);
					Jxstar.createComboGrid(selcfg, this.menu, 'node_ph_timesheet_editgrid');
				}
				this.menu.show(this.el);
			}
		})}, field:{name:'ph_timesheet__task_type',type:'string'}},
	{col:{header:'*任务描述', width:300, sortable:true, editable:true, hcss:'color:#0000ff;',
		editor:new Ext.form.TextField({
			maxLength:200, allowBlank:false
		})}, field:{name:'ph_timesheet__task_desc',type:'string'}},
	{col:{header:'*工作小时', width:70, sortable:true, align:'right',
		editable:true, hcss:'color:#0000ff;',
		editor:new Ext.form.NumberField({
			maxLength:12, allowBlank:false,
			allowNegative: false
		}),renderer:JxUtil.formatNumber(2)}, field:{name:'ph_timesheet__work_hours',type:'float'}},
	{col:{header:'日报ID', width:100, sortable:true, hidden:true}, field:{name:'ph_timesheet__dreport_id',type:'string'}},
	{col:{header:'项目ID', width:100, sortable:true, hidden:true}, field:{name:'ph_timesheet__project_id',type:'string'}},
	{col:{header:'主键', width:100, sortable:true, hidden:true}, field:{name:'ph_timesheet__tsheet_id',type:'string'}},
	{col:{header:'任务类型代号', width:100, sortable:true, hidden:true}, field:{name:'ph_timesheet__task_type_code',type:'string'}}
	];
	
	config.param = {
		cols: cols,
		sorts: null,
		hasQuery: '0',
		isedit: '1',
		isshow: '0',
		funid: 'ph_timesheet'
	};
	
	cols[cols.length] = {col:
		{header:'<div class="hd_button_add" title="添加记录">&#160;</div>', id:'col_button_add', width:30, xtype:'actioncolumn', menuDisabled:true, align:'center', items:[{
				icon: 'resources/images/icons/fam/clear.gif',
				tooltip: jx.base.del,	
				handler: function(grid, rowIndex, colIndex) {
					grid.getSelectionModel().selectRow(rowIndex);
					grid.gridNode.event.editDelete();
				}
			}]
		}
	};
	
	config.initpage = function(gridNode){
		var grid = gridNode.page;
		grid.on('render', function(){
			Ext.fly(grid.getView().innerHd).on('mousedown', function(e, t){
			//添加表头添加事件
				if(e.button === 0 && t.className == 'hd_button_add'){
					e.stopEvent();
					grid.gridNode.event.editCreate();
				}
			}, grid);
        }, grid);
		
	};
		
	return new Jxstar.GridNode(config);
}