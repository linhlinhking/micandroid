<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Dashboard - Admin Template</title>
<link rel="stylesheet" type="text/css" href="/css/template/theme.css" />
<link rel="stylesheet" type="text/css" href="/css/template/style.css" />

<!-- CSS Imports-->
<link rel="stylesheet" type="text/css" media="screen"
	href="/css/jquery/redmond/jquery-ui-1.8.23.custom.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="/css/jqgrid/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="/css/main/main.css" />

<!-- JS Imports -->
<script type="text/javascript" src="/js/jquery/jquery-1.5.2.min.js"></script>
<script type="text/javascript"
	src="/js/jquery/jquery-ui-1.8.12.custom.min.js"></script>
<script type="text/javascript" src="/js/datejs/date.js"></script>
<script type="text/javascript" src="/js/jqgrid/grid.locale-en.js"></script>
<script type="text/javascript" src="/js/jqgrid/jquery.jqGrid.min.js"></script>
<script>
	var StyleFile = "theme" + document.cookie.charAt(6) + ".css";
	document
			.writeln('<link rel="stylesheet" type="/text/css" href="/css/template/' + StyleFile + '">');
</script>
<!--[if IE]>
<link rel="stylesheet" type="text/css" href="/css/template/ie-sucks.css" />
<![endif]-->
</head>

<body>
	<div id="container">
		<!-- Loading header start-->
		<%@ include file="/common/header.jsp"%>
		<!-- loading header end -->
		<!-- Loading Second Menu start -->
		<%@ include file="/common/head-menu.jsp"%>
		<!-- Loading Second Menu end -->

		<div id="wrapper">

			<!-- Loading right Menu start -->
			<%@ include file="/common/content.jsp"%>
			<!-- Loading right Menu end -->

			<!-- Loading right Menu start -->
			<%@ include file="/common/right.jsp"%>
			<!-- Loading right Menu end -->
		</div>


		<!-- Loading footer start -->
		<%@ include file="/common/footer.jsp"%>
		<!-- Loading footer end -->


	</div>
</body>
<!-- /admin/user/getAll -->
<script type="text/javascript">
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
</script>
</html>
