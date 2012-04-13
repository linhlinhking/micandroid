<%
	String contextpath = request.getContextPath();
	String curLangType = "zh";//java.util.Locale.getDefault().getLanguage();
	String supLangType = org.jxstar.util.config.SystemVar.getValue("sys.lang.type");
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title id='product_name'>恒拓开源日报系统 V0.1</title>
	<style type="text/css">
	<!--
	.login-label {
		 font-family:Arial, Helvetica, sans-serif;
		 font-size:12px;	
	 }

	.login-field {
		 font-family:Arial, Helvetica, sans-serif;
		 font-size:12px;
		 padding:1px;
		 width:100px;
		 border:1px solid #90a0ff;
	 }
	 
    #loading{
        position:absolute;
        left:45%; top:15%;
        z-index:20001;
        color:red;
		background-color:white;
        font:normal 12px arial,tahoma,helvetica;
        padding:5px;
        height:auto;
    }
	-->
	</style>
</head>
<body scroll="no">
	<div id="loading">
		<img src="resources/images/jxstar32.gif" width="32" height="32"
		style="margin-right:8px;float:left;vertical-align:bottom;"/>
		<span id="loading-msg">正在加载样式文件...</span>
	</div>
	<iframe id="frmhidden" style="display:none;"></iframe>
	<link rel="stylesheet" type="text/css" href="public/lib/ext/resources/css/ext-all.css" />
	<link rel="stylesheet" type="text/css" href="resources/css/main.css" />
	<link rel="stylesheet" type="text/css" href="public/lib/ext/ux/css/portal.css" />
	<link rel="stylesheet" type="text/css" href="public/lib/ext/ux/css/RowEditor.css" />
	
	<script type="text/javascript">
		document.getElementById('loading-msg').innerHTML = '正在加载系统文件...';
	</script>

	<script type="text/javascript" src="public/lib/ext/adapter/ext-base.js"></script>
	<script type="text/javascript" src="public/lib/ext/ext-all.js"></script>
	<script type="text/javascript" src="public/lib/ext/locale/ext-lang-<%=curLangType%>.js"></script>
	
	<script type="text/javascript" src="public/lib/ext/ux/Portal.js"></script>
	<script type="text/javascript" src="public/lib/ext/ux/PortalColumn.js"></script>
	<script type="text/javascript" src="public/lib/ext/ux/Portlet.js"></script>
	<script type="text/javascript" src="public/lib/ext/ux/RowEditor.js"></script>
	<script type="text/javascript" src="public/lib/ext/ux/Emptybox.js"></script>
	<script type="text/javascript" src="public/lib/ext/ux/FileUploadField.js"></script>
	
	<script type="text/javascript" src="public/locale/jxstar-lang-<%=curLangType%>.js"></script>
	<script type="text/javascript" src="public/core/JxLang.js"></script>
	<script type="text/javascript" src="public/core/SessionTimer.js"></script>
	<script type="text/javascript" src="public/core/GridNode.js"></script>
	<script type="text/javascript" src="public/core/FormNode.js"></script>
	<script type="text/javascript" src="public/core/JxUtil.js"></script>
	<script type="text/javascript" src="public/core/JxDefault.js"></script>
	<script type="text/javascript" src="public/core/JxLists.js"></script>
	<script type="text/javascript" src="public/core/JxGroup.js"></script>
	<script type="text/javascript" src="public/core/JxSum.js"></script>
	<script type="text/javascript" src="public/core/JxQuery.js"></script>
	<script type="text/javascript" src="public/core/JxExport.js"></script>
	<script type="text/javascript" src="public/core/JxPrint.js"></script>
	<script type="text/javascript" src="public/core/JxHint.js"></script>
	
	<script type="text/javascript" src="public/portlet/PortletFun.js"></script>
	<script type="text/javascript" src="public/portlet/PortletMsg.js"></script>
	<script type="text/javascript" src="public/portlet/PortletWarn.js"></script>
	<script type="text/javascript" src="public/portlet/PortletBoard.js"></script>
	<script type="text/javascript" src="public/portlet/PortletResult.js"></script>
	<script type="text/javascript" src="public/portlet/PortletResultG.js"></script>
	<script type="text/javascript" src="public/portlet/PortletWfAssign.js"></script>
	<script type="text/javascript" src="public/portlet/PortletWfAssignS.js"></script>
	<script type="text/javascript" src="public/core/JxMenu.js"></script>
	<script type="text/javascript" src="public/core/JxPortal.js"></script>

	<script type="text/javascript" src="public/core/Request.js"></script>
	<script type="text/javascript" src="public/core/XmlRequest.js"></script>

	<script type="text/javascript" src="public/core/GridEvent.js"></script>
	<script type="text/javascript" src="public/core/FormEvent.js"></script>

	<script type="text/javascript" src="public/core/JxExt.js"></script>
	<script type="text/javascript" src="public/core/Jxstar.js"></script>
	
	<script type="text/javascript" src="public/lib/graph/js/mxCanvas.js"></script>
	
	<script type="text/javascript">Ext.fly('loading').hide();</script>
	<!-- loaded all js file -->
	<div id="main_body"></div>
	<div id="login_body">
		<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
		  <tr>
			<td bgcolor="#cfdef6" style="text-align:right;font-size:12px;" id="td_language">&nbsp;</td><!--#2056aa-->
		  </tr>
		  <tr>
			<td height="608" background="resources/images/login/login_03.gif"><table width="862" border="0" align="center" cellpadding="0" cellspacing="0">
			  <tr>
				<td height="266" background="resources/images/login/login_04.gif">&nbsp;</td>
			  </tr>
			  <tr>
				<td height="95"><table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tr>
					<td width="424" height="95" background="resources/images/login/login_06.gif">&nbsp;</td>
					<td width="183" background="resources/images/login/login_07.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td width="21%" height="30"><div align="center"><span id="label_usercode" class="login-label">用户</span></div></td>
						<td width="79%" height="30"><input type="text" tabindex=1 name="user_code" id="user_code" class="login-field" value=""></td>
					  </tr>
					  <tr>
						<td height="30"><div align="center"><span id="label_userpass" class="login-label">密码</span></div></td>
						<td height="30"><input type="password" tabindex=2 name="user_pass" id="user_pass" class="login-field" value=""></td>
					  </tr>
					  <tr>
						<td height="30">&nbsp;</td>
						<td height="30"><img src="resources/images/login/dl.gif" width="81" height="22" border="0" usemap="#btn_map"></td>
					  </tr>
					</table></td>
					<td width="255" background="resources/images/login/login_08.gif">&nbsp;</td>
				  </tr>
				</table></td>
			  </tr>
			  <tr>
				<td height="247" valign="top" background="resources/images/login/login_09.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tr>
					<td width="22%" height="30">&nbsp;</td>
					<td width="56%">&nbsp;</td>
					<td width="22%">&nbsp;</td>
				  </tr>
				  <tr>
					<td>&nbsp;</td>
					<td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td colspan="2" align="center" id="label_company" class="login-label">www.jxstar.org copyright 2011</td>
					  </tr>
					</table></td>
					<td>&nbsp;</td>
				  </tr>
				</table></td>
			  </tr>
			</table></td>
		  </tr>
		  <tr>
			<td bgcolor="#628dd9">&nbsp;</td>
		  </tr>
		</table>
		<map name="btn_map"><area shape="rect" tabindex=3 coords="3,3,36,19" href="#" id="loginbtn"><area shape="rect" tabindex=4 coords="40,3,78,18" href="#" id="returnbtn"></map>
	</div>
</body>
<script language="javascript">
Jxstar.path = '<%=contextpath%>';

Ext.BLANK_IMAGE_URL = Jxstar.path + '/public/lib/ext/resources/images/default/s.gif';
Ext.chart.Chart.CHART_URL = Jxstar.path + '/public/lib/ext/resources/charts.swf';

Ext.onReady(function() {
	//显示系统支持的语言版本
	JxLang.showLang('<%=curLangType%>', '<%=supLangType%>');

	var usercodeEl = Ext.get('user_code');
	var userpassEl = Ext.get('user_pass');
	usercodeEl.focus();
	
	//回车登录
	var enter = function(e){
		if (e.getKey() == e.ENTER) {f_login();}
	};
	userpassEl.on('keypress', enter);
	usercodeEl.on('keypress', enter);
	
	//登陆成功
	var f_success = function(data) {
		userpassEl.remove();
		usercodeEl.remove();
		Ext.fly('login_body').remove();
		
		Jxstar.session = data;
		Jxstar.session.maxInterval = <%=session.getMaxInactiveInterval()%>;
		Jxstar.session.sessionId = '<%=session.getId()%>';
		Request.loadJS('/public/core/JxBody.js');
	};
	
	//登陆方法
	var f_login = function() {
		var usercode = usercodeEl.dom.value;
		if (usercode == "") {
			alert(jx.index.nocode);
			return false;
		}
		
		var userpass = userpassEl.dom.value;
		userpassEl.dom.value = "";

		//设置请求的参数
		var params = 'funid=login&eventcode=login&pagetype=login';
		params += '&user_code='+usercode+'&user_pass='+userpass;

		//发送请求
		Ext.lib.Ajax.request(
			'POST', Jxstar.path + '/commonAction.do',
			{
				success: function(response) {
					var result = Ext.decode(response.responseText);
					if (result.success) {
						f_success(result.data);
					} else {
						JxHint.alert(result.message);
					}
				},
				failure: function(response) {
					JxUtil.errorResponse(response);
				}
			},
			params
		);
	};
	
	//登陆按钮
	Ext.fly('loginbtn').on('click', f_login);
	Ext.fly('returnbtn').on('click', function(){
		usercodeEl.dom.value = "";
		userpassEl.dom.value = "";
		usercodeEl.focus();
	});
	
	//添加frmhidden的响应事件，用于处理文件下载的错误消息
	Ext.fly('frmhidden').on('load', function(){
		var msg = '';
		var text = this.dom.contentWindow.document.body.innerHTML;
		if (text == null || text.length == 0) {
			msg = jx.index.downerror;
		} else {
			var result = Ext.decode(text);
			if (result != null) {
				var msg = result.message || '';
				if (result.success == true || result.success == 'true') {
					if (msg.length == 0) msg = jx.index.downok;
				} else {
					if (msg.length == 0) msg = jx.index.downno;
				}
			} else {
				msg = jx.index.downerror;
			}
		}
		alert(msg);
	});
});
</script>
</html>
