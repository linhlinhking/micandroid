<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Dashboard - Admin Template</title>
<%@ include file="/common/css.jsp"%>
<link rel="stylesheet" type="text/css" href="/css/soningbo/location.css" />
<!-- JS Imports -->
<script type="text/javascript" src="/js/jquery/jquery-1.5.2.min.js"></script>
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
		
		<!-- Loading Second Menu end -->

		<div id="wrapper">

			<!-- Loading right Menu start -->
			<%@ include file="/common/location/second-category-list.jsp"%>
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
<script type="text/javascript" src="/js/jquery/jquery-ui-1.8.23.custom.min.js"></script>
<script type="text/javascript" src="/js/pagination/jquery.paginate.js"></script>
<%@ include file="/common/mvc.jsp" %>
<script type="text/javascript" src="/js/soningbo/location-second-category.js"></script>
</html>
