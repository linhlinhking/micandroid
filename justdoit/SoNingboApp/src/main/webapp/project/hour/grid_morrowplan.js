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
					var selcfg = {pageType:'combogrid', nodeId:'query_project', layoutPage:'', sourceField:'', targetField:'', whereSql:"", whereValue:'', whereType:'', isSame:'1', isShowData:'1', isMoreSelect:'0',isReadonly:'1',fieldName:'ph_morrowplan.project_name'};
					this.menu = Jxstar.createComboMenu(this);
					Jxstar.createComboGrid(selcfg, this.menu, 'node_ph_morrowplan_editgrid');
				}
				this.menu.show(this.el);
			}
		})}, field:{name:'ph_morrowplan__project_name',type:'string'}},
	{col:{header:'任务类型', width:100, sortable:true, editable:true, hcss:'color:#3039b4;',
		editor:new Ext.form.TriggerField({
			maxLength:20,
			editable:false,
			triggerClass:'x-form-search-trigger', 
			onTriggerClick: function() {
				if (this.menu == null) {
					var selcfg = {pageType:'combogrid', nodeId:'ph_tasktype', layoutPage:'', sourceField:'ph_tasktype.task_type;task_type_code', targetField:'ph_morrowplan.task_type;task_type_code', whereSql:"", whereValue:'', whereType:'', isSame:'1', isShowData:'1', isMoreSelect:'0',isReadonly:'1',fieldName:'ph_morrowplan.task_type'};
					this.menu = Jxstar.createComboMenu(this);
					Jxstar.createComboGrid(selcfg, this.menu, 'node_ph_morrowplan_editgrid');
				}
				this.menu.show(this.el);
			}
		})}, field:{name:'ph_morrowplan__task_type',type:'string'}},
	{col:{header:'*任务描述', width:370, sortable:true, editable:true, hcss:'color:#0000ff;',
		editor:new Ext.form.TextField({
			maxLength:200, allowBlank:false
		})}, field:{name:'ph_morrowplan__task_desc',type:'string'}},
	{col:{header:'日报ID', width:100, sortable:true, hidden:true}, field:{name:'ph_morrowplan__dreport_id',type:'string'}},
	{col:{header:'项目ID', width:100, sortable:true, hidden:true}, field:{name:'ph_morrowplan__project_id',type:'string'}},
	{col:{header:'主键', width:100, sortable:true, hidden:true}, field:{name:'ph_morrowplan__mplan_id',type:'string'}},
	{col:{header:'任务类型代号', width:100, sortable:true, hidden:true}, field:{name:'ph_morrowplan__task_type_code',type:'string'}}
	];
	
	config.param = {
		cols: cols,
		sorts: null,
		hasQuery: '0',
		isedit: '1',
		isshow: '0',
		funid: 'ph_morrowplan'
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