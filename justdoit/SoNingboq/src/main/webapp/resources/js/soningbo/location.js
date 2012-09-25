$(function() {
	$("#grid")
			.jqGrid(
					{
						url : '/admin/location/getAll',
						datatype : 'json',
						mtype : 'POST',
						autowidth : false,
						height : 'auto',
						multiselect : true,
						colNames : [ '位置名称', '位置地址', '位置电话', '经度', '维度', '操作' ],
						colModel : [ {
							name : 'name_cn',
							index : 'name_cn',
							width : 40,
							editable : true
						}, {
							name : 'address_cn',
							index : 'address_cn',
							width : 40,
							editable : true
						}, {
							name : 'telephone',
							index : 'telephone',
							width : 40,
							editable : true
						}, {
							name : 'latitude',
							index : 'latitude',
							width : 40
						}, {
							name : 'longitude',
							index : 'longitude',
							width : 40
						}, {
							name : 'CheckData',
							index : 'id',
							width : 60,
							editable : false,
							align : 'center',
							sortable : false
						} ],
						page : 1,
						rowNum : 20,
						rowList : [ 10, 20, 30 ],
						autowidth : true,
						rownumbers : true,
						pager : '#pager',
						sortname : 'id',
						viewrecords : true,
						sortorder : "asc",
						caption : "位置管理",
						emptyrecords : "Empty records",
						loadonce : false,
						jsonReader : {
							root : "rows",
							page : "page",
							total : "total",
							records : "records",
							repeatitems : false,
							cell : "cell",
							id : "id"
						},
						gridComplete : function() {
							var ids = jQuery("#grid").jqGrid('getDataIDs');
							for ( var i = 0; i < ids.length; i++) {
								var id = ids[i];
								appover = "<a href='#' style='color:#f60' onclick='checkData("
										+ id + ")'>审核</a>";
								jQuery("#grid").jqGrid('setRowData', ids[i], {
									CheckData : appover
								});
							}
						},ondblClickRow:function(ids){
							editRow();
						}
					});

	$("#grid").jqGrid('navGrid', '#pager', {
		edit : false,
		add : false,
		del : false,
		search : true
	}, {}, {}, {}, {
		sopt : [ 'eq', 'ne', 'lt', 'gt', 'cn', 'bw', 'ew' ],
		closeOnEscape : true,
		multipleSearch : true,
		closeAfterSearch : true
	});

	$("#grid").navButtonAdd('#pager', {
		caption : "添加",
		buttonicon : "ui-icon-plus",
		onClickButton : addRow,
		position : "top",
		title : "",
		cursor : "pointer"
	});

	$("#grid").navButtonAdd('#pager', {
		caption : "编辑",
		buttonicon : "ui-icon-pencil",
		onClickButton : editRow,
		position : "top",
		title : "",
		cursor : "pointer"
	});

	$("#grid").navButtonAdd('#pager', {
		caption : "删除",
		buttonicon : "ui-icon-trash",
		onClickButton : deleteRow,
		position : "top",
		title : "",
		cursor : "pointer"
	});
	
	$("#add-location-dialog-form").dialog({
				autoOpen : false,
				height : 480,
				width : 960,
				modal : true,
				buttons : {
					"保存" : function() {
						
					},
					"取消" : function() {
						$(this).dialog("close");
					}
				},
				close : function() {
				}
	});
	
	$("#edit-location-dialog-form").dialog({
		autoOpen : false,
		height : 480,
		width : 960,
		modal : true,
		buttons : {
			"保存" : function() {
				
			},
			"取消" : function() {
				$(this).dialog("close");
			}
		},
		close : function() {
		}
});

	
	function addRow() {
		$("#add-location-dialog-form").dialog("open");
	}

	function editRow() {
		$("#edit-location-dialog-form").dialog("open");
	}
	function deleteRow() {
		alert("delete");
	}

	function checkData(ids) {
		alert(ids);
	}

});


