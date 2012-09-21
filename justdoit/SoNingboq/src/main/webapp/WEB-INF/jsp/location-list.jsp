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

	<div id="addLocation" style="display: none">
		<div id="dialog-form" title="添加位置信息">
			<p class="validateTips">添加位置信息</p>
			<form>
				<fieldset>
					<label for="locName">位置名称：</label> <input type="text"
						name="name_cn" id="locationName"
						class="text ui-widget-content ui-corner-all" /> <label
						for="locAddress">位置地址：</label> <input type="text"
						name="address_cn" id="locationAddress"
						class="text ui-widget-content ui-corner-all" /> <label
						for="locTelephone">电话号码：</label> <input type="text"
						name="telephone" id="locationTelephone"
						class="text ui-widget-content ui-corner-all" />
				</fieldset>
			</form>
		</div>

	</div>

	<div id="editLocation" style="display: none">
		<div id="dialog-form-edit" title="编辑位置信息">
			<form action="">
				
			</form>
		</div>
	</div>

</body>

<!-- /admin/user/getAll -->
<script type="text/javascript" src="/js/soningbo/location.js"></script>
</html>
