$(function() {
		$("#grid").jqGrid({
			url : '/admin/event/getAll',
			datatype : 'json',
			mtype : 'POST',
			autowidth : false,
			height : 300,
			colNames : [ '编号', '活动标题', '活动类别'],//,'开始时间', '结束时间', '是否重复', '重复周期' ],
			colModel : [ {
				name : 'id',
				index : 'id',
				width : 55,
				editable : false
			}, {
				name : 'title_cn',
				index : 'title_cn',
				width : 90,
				editable : true
			}, {
				name : 'eventCategory.name_cn',
				index : 'eventCategory.name_cn',
				width : 90,
				editable : true
			}],
			
			rowNum : 10,
			rowList : [ 10, 20, 30 ],
			autowidth : true,
			rownumbers : true,
			pager : '#pager',
			sortname : 'id',
			viewrecords : true,
			sortorder : "asc",
			caption : "活动管理",
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

	});

	function addRow() {
		alert("add");
	}
	function editRow() {
		alert("edit");
	}
	function deleteRow() {
		alert("delete");
	}