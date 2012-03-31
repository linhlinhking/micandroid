<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <title>人力资源管理系统-系统登陆</title>
  <jsp:include page="../../public/layout/inc_path.jsp"/>	
 <style type="text/css">
  #loading-mask{
        position:absolute;
        left:0;
        top:0;
        width:100%;
        height:100%;
        z-index:20000;
        background-color:white;
    }
    #loading{
        position:absolute;
        left:45%;
        top:40%;
        padding:2px;
        z-index:20001;
        height:auto;
    }
    #loading .loading-indicator{
        background:white;
        color:#444;
        font:bold 20px tahoma,arial,helvetica;
        padding:10px;
        margin:0;
        height:auto;
    }
    #loading-msg {
        font: normal 18px arial,tahoma,sans-serif;
    }
  </style>
  </head>
  <body>
<!-- 加载效果 -->
<div id='loading-mask'></div>
<div id="loading">
    <div class="loading-indicator">
      <img src="resources/images/extanim32.gif" width="32" height="32" style="margin-right:8px;float:left;vertical-align:top;"/> 
       <br/><span id="loading-msg">loading ...</span>
    </div>
</div>
  <!-- 加载类库 -->
	<!-- EXT JS -->
	<link rel="stylesheet" type="text/css" href="resources/js/extjs/resources/css/ext-all.css" />
	<!-- GC -->
	<!-- LIBS -->
	<script type="text/javascript" src="resources/js/extjs/adapter/ext/ext-base.js"></script>
	<!-- ENDLIBS -->
	<script type="text/javascript" src="resources/js/extjs/ext-all-debug.js"></script>
	<script type="text/javascript" src="resources/js/general/login.js"></script>
	<!-- END EXT JS -->
  <!-- 退去加载效果 -->
  <script type="text/javascript">
     Ext.get('loading').setOpacity(0.0,{duration:1.0,callback:function(){this.hide();}});
     Ext.get('loading-mask').setOpacity(0.0,{duration:1.0,callback:function(){this.hide();}});
  </script>
   <!-- 登陆界面 -->  
 <div id='wins'></div>
 </body>
</html>


