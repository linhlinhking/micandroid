Jxstar.currentPage = function() {
	var config = {param:{},initpage:function(page, define){},eventcfg:{}};

	var cols = [
	{col:{header:'*控件代号', width:107, sortable:true, editable:true, hcss:'color:#0000ff;',
		editor:new Ext.form.TextField({
			maxLength:20, allowBlank:false
		})}, field:{name:'funall_control__control_code',type:'string'}},
	{col:{header:'*控件名称', width:111, sortable:true, editable:true, hcss:'color:#0000ff;',
		editor:new Ext.form.TextField({
			maxLength:100, allowBlank:false
		})}, field:{name:'funall_control__control_name',type:'string'}},
	{col:{header:'*功能标识', width:100, sortable:true, editable:true, hcss:'color:#0000ff;',
		editor:new Ext.form.TextField({
			maxLength:25, allowBlank:false
		})}, field:{name:'funall_control__fun_id',type:'string'}},
	{col:{header:'选择布局', width:229, sortable:true, editable:true, hcss:'color:#3039b4;',
		editor:new Ext.form.TriggerField({
			maxLength:100,
			editable:true,
			triggerClass:'x-form-search-trigger', 
			onTriggerClick: function() {
				if (this.menu == null) {
					var selcfg = {pageType:'combogrid', nodeId:'fun_layout', layoutPage:'', sourceField:'funall_layout.layout_path', targetField:'funall_control.layout_page', whereSql:"", whereValue:'', whereType:'', isSame:'0', isShowData:'1', isMoreSelect:'0',isReadonly:'0',fieldName:'funall_control.layout_page'};
					this.menu = Jxstar.createComboMenu(this);
					Jxstar.createComboGrid(selcfg, this.menu, 'node_sys_ctlsel_editgrid');
				}
				this.menu.show(this.el);
			}
		})}, field:{name:'funall_control__layout_page',type:'string'}},
	{col:{header:'控件类型', width:100, sortable:true, hidden:true, defaultval:'combowin'}, field:{name:'funall_control__control_type',type:'string'}},
	{col:{header:'control_id', width:100, sortable:true, hidden:true}, field:{name:'funall_control__control_id',type:'string'}}
	];
	
	config.param = {
		cols: cols,
		sorts: null,
		hasQuery: '1',
		isedit: '1',
		isshow: '1',
		funid: 'sys_ctlsel'
	};
	
	
		
	return new Jxstar.GridNode(config);
}