<%@ page pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()
			+ "://"
			+ request.getServerName()
			+ (request.getServerPort() == 80 ? "" : (":" + request
					.getServerPort())) + path + "/";
%>

<html xmlns="http://www.w3.org/1999/xhtml">
	<HEAD>
		<TITLE>系统找不到页面</TITLE>
		<META content="MSHTML 6.00.2900.2523" name="GENERATOR">
	</HEAD>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<STYLE>
BODY {
	FONT-SIZE: 12px;
	FONT-FAMILY: Tahoma
}

TD {
	FONT-SIZE: 12px;
	FONT-FAMILY: Tahoma
}

A:link {
	COLOR: #636363;
	TEXT-DECORATION: none
}

A:visited {
	COLOR: #838383;
	TEXT-DECORATION: none
}

A:hover {
	COLOR: #a3a3a3;
	TEXT-DECORATION: underline
}

BODY {
	BACKGROUND-COLOR: #cccccc
}

LI {
	LINE-HEIGHT: 25px;
}
</STYLE>
	<base href="<%=basePath%>">
	<BODY>
		404
	</BODY>
</HTML>
