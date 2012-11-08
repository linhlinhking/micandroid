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
	
<link rel="stylesheet" href="/css/plugin/bootstrap.min.css">
<link rel="stylesheet" href="/css/plugin/style.css">
<link rel="stylesheet" href="/css/plugin/bootstrap-responsive.min.css">
<!--[if lt IE 7]><link rel="stylesheet" href="/css/plugin/bootstrap-ie6.min.css"><![endif]-->
<link rel="stylesheet" href="/css/plugin/bootstrap-image-gallery.min.css">
<link rel="stylesheet" href="/css/plugin/jquery.fileupload-ui.css">
<!--[if lt IE 9]><script src="/js/plugin/html5.js"></script><![endif]-->

<link rel="stylesheet" type="text/css" media="screen"
	href="/css/main/main.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="/css/soningbo/event.css" />

<!-- JS Imports -->
<script type="text/javascript" src="/js/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="/js/jquery/jquery-ui-1.8.12.custom.min.js"></script>
<script type="text/javascript" src="/js/datejs/date.js"></script>

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
			<%@ include file="/common/event/event-content.jsp"%>
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
<script src="/js/plugin/jquery.ui.widget.js"></script>
<script src="/js/plugin/tmpl.min.js"></script>
<script src="/js/plugin/load-image.min.js"></script>
<script src="/js/plugin/canvas-to-blob.min.js"></script>
<script src="/js/plugin/bootstrap.min.js"></script>
<script src="/js/plugin/bootstrap-image-gallery.min.js"></script>
<script src="/js/plugin/jquery.iframe-transport.js"></script>
<script src="/js/plugin/jquery.fileupload.js"></script>
<script src="/js/plugin/jquery.fileupload-fp.js"></script>
<script src="/js/plugin/jquery.fileupload-ui.js"></script>
<script src="/js/plugin/locale.js"></script>
<script src="/js/plugin/main.js"></script>
<script type="text/javascript" src="/js/soningbo/events.js"></script>
</html>
