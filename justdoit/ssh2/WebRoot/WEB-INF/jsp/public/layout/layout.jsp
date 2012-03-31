<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="/tags/c" prefix="c"%>
<%@ taglib uri="/tags/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		<s:include value="inc_path.jsp"/>	
		<!-- EXT JS -->
		<link rel="stylesheet" type="text/css" href="resources/js/extjs/resources/css/ext-all.css" />
		<link rel="stylesheet" type="text/css" href="resources/js/extjs/examples/shared/icons/silk.css" />
	    <!-- GC -->
	 	<!-- LIBS -->
	 	<script type="text/javascript" src="resources/js/extjs/adapter/ext/ext-base.js"></script>
	 	<!-- ENDLIBS -->
	    <script type="text/javascript" src="resources/js/extjs/ext-all-debug.js"></script>
	    <!-- END EXT JS -->
		<!-- main js-->
		<script type="text/javascript" src="resources/js/IndexPage.js"></script>
		<!-- menu js-->
		<script type="text/javascript" src="resources/js/sysmgr/menumgr/MenuMgrGridPanel.js"></script>
		<script type="text/javascript" src="resources/js/sysmgr/menumgr/AddMenuWindow.js"></script>
		<script type="text/javascript" src="resources/js/sysmgr/menumgr/MenuFormPanel.js"></script>
		<script type="text/javascript" src="resources/js/sysmgr/menumgr/EditDictionaryDataWindow.js"></script>
		
        <title>得实设备管理系统</title> 
       
        <style type="text/css">
	    html, body {
	        font:normal 12px verdana;
	        margin:0;
	        padding:0;
	        border:0 none;
	        overflow:hidden;
	        height:100%;
	    }
	    p {
	        margin:5px;
	    }
	    .settings {
	        background-image:url(resources/js/extjs/examples/shared/icons/fam/folder_wrench.png);
	    }
	    .nav {
	        background-image:url(resources/js/extjs/examples/shared/icons/fam/folder_go.png);
	    }
	    </style>
     
       <script type="text/javascript">
			  Ext.onReady(function(){
		           <%-- 加载效果 --%>
					setTimeout(function() {
						Ext.get('loading').remove();
						Ext.get('loading-mask').fadeOut({remove:true});
					}, 1000); 
			       <%-- 开启提示 --%>
			        Ext.QuickTips.init() ;
					Ext.form.Field.prototype.msgTarget = "side" ;
					<%-- 实例化主页 --%>
				    var _index= new IndexPage();
				    <%-- 窗体大小改变延时事件 --%>
			        window.onresize=function(){
			        <%-- 过100毫秒在执行  --%>
			        setTimeout(_index.onActiveTabSize,100);
			        };
			  });
       </script>
    </head>
    
    <body>
    	<div id="loading">
             <div  class="loading-indicator">
                  <img src="resources/images/extanim32.gif" alt="" width="32" height="32" style="margin-right:8px;" align="absmiddle"/>
         正在加载,请稍候......
             </div>
         </div>
         <div id="loading-mask">
         </div>
    	<div id="content">
    		<!-- body header -->
			<div id="north" style="width:100%;">
				<s:include value="header.jsp"/>
			</div>
			
		    <div id="west" >
		        
		    </div>
		    <div id="south">
		       <s:include value="footer.jsp"/>
		    </div>
		</div>
    </body>
</html>