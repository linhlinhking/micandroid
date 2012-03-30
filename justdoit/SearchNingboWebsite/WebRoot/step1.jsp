<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>搜索宁波 - So Ningbo</title>


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
  <div style="display:block; height:74px; margin-top:25px">
  <div style="display:block; width:19px; height:74px; float:left;"><img src="images/arrow_cap.png" width="19" height="74" /></div> 
  <div style="display:block; width:676px; height:74px; background-image:url(images/arrow_bg.png); background-repeat:repeat-x; float:left">

   
  <div style="display:block; width:200px; height:74px; float:left;">
 <div style="font-weight:bold; font-size:16px; color:#666; margin-top:17px; margin-left:15px"> 第一个步骤</div>
<div style="font-size:12px; color:#666; margin-left:15px"> 导入联系人</div>
<div style="position:relative; top:-23px; left:200px; width:12px; height:10px; visibility:hidden"><img src="images/tick.png" width="12" height="10" /></div>
  </div>
  
   <div style="display:block; width:38px; height:74px; float:left;"><img src="images/arrow_head.png" width="38" height="74" /></div>
  
    <div style="display:block; width:200px; height:74px; float:left;">
    <div style="font-weight:bold; font-size:16px; color:#999; margin-top:17px; margin-left:34px">  第二个步骤</div>
    <div style="font-size:12px; color:#999; margin-left:34px"> 个人资料信息</div>
    <div style="position:relative; top:-23px; left:200px; width:12px; height:10px; visibility:hidden"><img src="images/tick.png" width="12" height="10" /></div>
    </div>
  
   <div style="display:block; width:38px; height:74px; float:left;"><img src="images/arrow_head.png" width="38" height="74" /></div>
  
    <div style="display:block; width:200px; height:74px; float:left;">
    <div style="font-weight:bold; font-size:16px; color:#999; margin-top:17px; margin-left:34px">  第三个步骤</div>
    <div style="font-size:12px; color:#999; margin-left:34px"> 资料图片</div>
    
    </div>
  
  
  </div>
   <div style="display:block; width:38px; height:74px; float:left;"><img src="images/arrow_head.png" width="38" height="74" /></div> 
   </div>
   <div style="width:645px; height:500px; display:block; margin-top:20px; background-color:#f7f7f7; 	border: 1px solid #d4d4d4;
	-moz-border-radius: 6px;
    -webkit-border-radius: 6px;
    -khtml-border-radius: 6px;
    -moz-box-shadow: 1px 3px 5px 0 #CCC;
-webkit-box-shadow: 1px 3px 5px 0 #CCC;
box-shadow: 1px 3px 5px 0 #CCC;
padding:25px">
<div style="font-size:18px;  color:#666">您的朋友和同事已经在这里？</div>
<div style="font-size:12px;  color:#999">使用您的电子邮件帐户，以最快的方式找到你的朋友</div>
<div style=" position:relative; top:430px; left:0px; display:block;float:right; width:50px" class="btn" id="step1_next_btn">下一步</div>



<div id="email1" style="width:100%; display:block; overflow:hidden; height:65px; margin-top:20px; background-color:#eeeeee;-moz-border-radius: 6px; -webkit-border-radius: 6px; -khtml-border-radius: 6px; border: 1px solid #d4d4d4; margin-bottom:15px;">
<div style="width:35px; height:35px; float:left; display:block; margin:15px"><img src="images/email_icon.png" width="35" height="35" /></div>
<div style="width:200px; height:35px; float:left; display:block; margin-top:25px; font-size:12px; color:#666">Windows Live Hotmail</div>




<div class="btn" id="email1_btn" style="float:right; margin:15px;">找朋友</div>





<div id="table1" style="width:100%; height:35px; float:right; display:block; position:relative; top:-5px;">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="40%" align="right"><div class="txt_14" style="margin-right:5px">输入您的电子邮件: </div></td>
    <td width="60%"><input type="text" name="email1" id="email4" class="text_fld" /></td>
  </tr>
  <tr>
    <td width="40%">&nbsp;</td>
    <td width="60%"><div class="btn" id="hotmail_btn" style="">找朋友</div></td>
  </tr>
</table>
</div>
</div>



<div id="email2" style="width:100%; display:block; overflow:hidden; height:65px; margin-top:20px; background-color:#eeeeee;-moz-border-radius: 6px; -webkit-border-radius: 6px; -khtml-border-radius: 6px; border: 1px solid #d4d4d4; margin-bottom:15px;">
<div style="width:35px; height:35px; float:left; display:block; margin:15px"><img src="images/email_icon.png" width="35" height="35" /></div>
<div style="width:200px; height:35px; float:left; display:block; margin-top:25px; font-size:12px; color:#666">Yahoo mail</div>




<div class="btn" id="email2_btn" style="float:right; margin:15px;">找朋友</div>





<div id="table2" style="width:100%; height:35px; float:right; display:block; position:relative; top:-5px;">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="40%" align="right"><div class="txt_14" style="margin-right:5px">输入您的电子邮件: </div></td>
    <td width="60%"><input type="text" name="email1" id="email4" class="text_fld" /></td>
  </tr>
  <tr>
    <td width="40%">&nbsp;</td>
    <td width="60%"><div class="btn" id="hotmail_btn" style="">找朋友</div></td>
  </tr>
</table>
</div>
</div>










</div>
  
  </div>
  <div class="footer">
 
  <div class="footer_display"></div>
  
  
  
</div>
<jsp:include page="/footer.jsp" />

</div>
</body>
</html>
