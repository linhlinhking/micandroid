﻿Jxstar.currentPage = function() {
	var config = {param:{},initpage:function(page, define){},eventcfg:{}};
	
		var items = [{
		height: '97%',
		width: '97%',
		border: false,
		layout: 'form',
		style: 'padding:10px;',
		items: [{
			anchor:'100%',
			layout:'column',
			autoHeight:true,
			items:[{
				border:false,
				columnWidth:0.99,
				layout:'form',
				items:[
					{xtype:'textarea', fieldLabel:'判断条件', name:'wf_condition__condition', allowBlank:false, labelStyle:'color:#0000FF;', labelSeparator:'*', width:'100%', height:72, maxLength:200},
					{xtype:'hidden', fieldLabel:'是否定制类', name:'wf_condition__use_class', defaultval:'0', anchor:'100%'},
					{xtype:'hidden', fieldLabel:'条件ID', name:'wf_condition__condition_id', anchor:'100%'},
					{xtype:'hidden', fieldLabel:'过程ID', name:'wf_condition__process_id', anchor:'100%'},
					{xtype:'hidden', fieldLabel:'流转ID', name:'wf_condition__line_id', anchor:'100%'}
				]
			}
			]
		}]
	}];
	
	config.param = {
		items: items,
		funid: 'wf_condition'
	};

	
	
	return new Jxstar.FormNode(config);
}