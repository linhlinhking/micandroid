<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>搜索宁波 - 找回密码</title>


<script src="scripts/jquery-1.7.min.js" type="text/javascript"></script>
<script src="scripts/so_ningbo.js" type="text/javascript"></script>
<link href="global.css" rel="stylesheet" type="text/css" />
</head>

<body>


<div class="container">
 
 
  <div class="header" style="height:90px">
  
  <div style="padding-top:22px; padding-bottom:22px; float:left"> <a href="http://www.soningbo.com"><img src="images/home_logo.png" alt="搜索宁波 - So Ningbo" name="logo" width="180" height="46" id="logo" style="display:block;" /></a>
  </div>
  </div>
 
  
  <div class="content">
    <div style="width:645px; height:500px; display:block; margin-top:20px; background-color:#f7f7f7; 	border: 1px solid #d4d4d4;
	-moz-border-radius: 6px;
    -webkit-border-radius: 6px;
    -khtml-border-radius: 6px;
    -moz-box-shadow: 1px 3px 5px 0 #CCC;
-webkit-box-shadow: 1px 3px 5px 0 #CCC;
box-shadow: 1px 3px 5px 0 #CCC;
padding:25px">
  <div style="font-size:18px;  color:#666">忘记密码？</div>
<div style="font-size:12px;  color:#999">请在下面输入您的电子邮件，我们将会寄给您的密码</div>

<div style="font-size:12px;  color:#999; margin-top:15px;">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td style="text-align:right; font-size:14px; padding-right:15px; color:#666">家乡: </td>
    <td><input type="text" name="email2" id="email" class="text_fld" /></td>
  </tr>
  <tr>
    <td width="40%" style="text-align:right; font-size:14px; padding-right:15px; color:#666">电子邮件:</td>
    <td width="60%"><input type="text" name="email1" id="email4" class="text_fld" /></td>
  </tr>
</table>
</div>

<div style="float:left; margin-top:330px" class="btn" id="index_btn">返回 </div>
<div style="float:right; margin-top:330px" class="btn" id="forgot_password_email_btn">发送密码</div>

</div>
  
  </div>
  <div class="footer">
 
  <div class="footer_display"></div>
  
  
  
</div>
<jsp:include page="/footer.jsp" /></div>
</body>
</html>
