<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title></title>
	<link rel="stylesheet" type="text/css" href="/css/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="/css/easyui/themes/icon.css">
	<script type="text/javascript" src="/js/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript">
		if(top != self) {
			if(top.location != self.location) {
				top.location = self.location;
			}
		}
	</script>
	<script type="text/javascript">
	function submit(){
		var username = document.getElementById('username').value;
		var password = document.getElementById('password').value;
		if(null == username || username == ''){
			$.messager.alert('Warning','Please input your username!');
			return;
		}
		if(null == password || password == ''){
			$.messager.alert('Warning','Please input your password!');
			return;
		}
		document.getElementById('loginForm').submit();
	}
	function reset() {
		document.getElementById('loginForm').reset();
	}
	function register(){
		var $win;
	    $win = $('#registerwin').window({
	            title:'注册',
	            width: 400,
	            height: 225,
	            shadow: true,
	            modal:true,
	            iconCls:'icon-add',
			    closed:true,
			    cache:false,
			    minimizable:false,
			    maximizable:false,
			    collapsible:false,
			    href:'registerwin.do'
	         });
	   
	    $win.window('open');
	}
</script>
</head>
<body>
    <div id="win" class="easyui-window" title="系统登陆" minimizable="false" maximizable="false" closable="false" style="width:370px;height:250px;">  
       <form id="loginForm" method="post" action="login" style="padding:10px 30px 10px 50px;">
       	   <p><label style="color:red"> <%
       	   	String msg = (String)request.getAttribute("msg");
       	   	if(null != msg) {
       	   		out.print(msg);
       	   	}
       	   %></label></p> 
           <p><span style="font-weight:bold">登陆邮箱:</span> <input type="text" id="username" name="username" style="width:220px;"></p>  
           <p><span style="font-weight:bold">登陆密码:</span> <input type="password" id="password" name="password" style="width:220px;" onkeypress="if(event.keyCode==13){submit();return false;}"></p>  
           <div style="padding:5px;text-align:left;">  
               <a href="#" class="easyui-linkbutton" plain="true" icon="icon-ok" onclick="javascript:submit();"><span style="font-weight:bold">登陆</span></a>  
               <a href="#" class="easyui-linkbutton" plain="true" icon="icon-reload" onclick="javascript:reset();"><span style="font-weight:bold">重置</span></a>
           </div>  
       </form>  
    </div> 
</body>
</html>