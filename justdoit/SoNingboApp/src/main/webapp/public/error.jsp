<%
	String errorCode = (String)request.getAttribute("error_code");
	String errorInfo = (String)request.getAttribute("error_info");
%>
<html>
<head>
<style>
<!--
H1 {font-family:Tahoma,Arial,sans-serif;color:#15428b;background-color:#99bbe8;font-size:22px;}
HR {color : #15428b;}
BODY {font-family:Tahoma,Arial,sans-serif;color:#15428b;background-color:white;} 
-->
</style>
<body>
<h1>错误代码：<%=errorCode%></h1>
<HR size="1" noshade="noshade"><p>
<b>错误信息：</b> <%=errorInfo%>
</body>
</html>