<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'testie.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="../../js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
   		$(function(){
   			if(navigator.userAgent.indexOf("MSIE") > 0){}
   			$.get("<%=basePath%>resource/user/username/devon", function(json) {
   					//var json_tmp = (new Function('return' + json))() ;
   					//JSON.parse(json);
					alert(json.result);
			});
   			
   			   		
   		}) ;
  </head>
  
  <body>
    This is my JSP page. <br>
  </body>
</html>
