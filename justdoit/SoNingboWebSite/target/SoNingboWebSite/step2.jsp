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
 <div style="font-weight:bold; font-size:16px; color:#999; margin-top:17px; margin-left:15px"> 第一个步骤</div>
<div style="font-size:12px; color:#999; margin-left:15px"> 导入联系人</div>
<div style="position:relative; top:-23px; left:200px; width:12px; height:10px;"><img src="images/tick.png" width="12" height="10" /></div>
  </div>
  
   <div style="display:block; width:38px; height:74px; float:left;"><img src="images/arrow_head.png" width="38" height="74" /></div>
  
    <div style="display:block; width:200px; height:74px; float:left;">
    <div style="font-weight:bold; font-size:16px; color:#666; margin-top:17px; margin-left:34px">  第二个步骤</div>
    <div style="font-size:12px; color:#666; margin-left:34px"> 个人资料信息</div>
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
<div style="font-size:18px;  color:#666">完成您的个人资料</div>
<div style="font-size:12px;  color:#999">这些信息将帮助你找到朋友, 请尽力填写完整</div>

<div style="font-size:12px;  color:#999; margin-top:15px;">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td style="text-align:right; font-size:14px; padding-right:15px; color:#666">家乡: </td>
    <td><input type="text" name="email2" id="email" class="text_fld" /></td>
  </tr>
  <tr>
    <td width="40%" style="text-align:right; font-size:14px; padding-right:15px; color:#666">住址: </td>
    <td width="60%"><input type="text" name="email1" id="email4" class="text_fld" /></td>
  </tr>
  <tr>
    <td width="40%" style="text-align:right; font-size:14px; padding-right:15px; color:#666">高中:</td>
    <td width="60%"><input type="text" name="email1" id="email4" class="text_fld" /></td>
  </tr>
  <tr>
    <td width="40%" style="text-align:right; font-size:14px; padding-right:15px; color:#666">大学: </td>
    <td width="60%"><input type="text" name="email1" id="email4" class="text_fld" /></td>
  </tr>
  <tr>
    <td width="40%" style="text-align:right; font-size:14px; padding-right:15px; color:#666">旧雇主: </td>
    <td width="60%"><input type="text" name="email1" id="email4" class="text_fld" /></td>
  </tr>
  <tr>
    <td width="40%" style="text-align:right; font-size:14px; padding-right:15px; color:#666">当前雇主: </td>
    <td width="60%"><input type="text" name="email1" id="email4" class="text_fld" /></td>
  </tr>
  <tr>
    <td style="text-align:right; font-size:14px; padding-right:15px; color:#666">工作技能: </td>
    <td><input type="text" name="email2" id="email2" class="text_fld" /></td>
  </tr>
  <tr>
    <td style="text-align:right; font-size:14px; padding-right:15px; color:#666">最喜欢的餐馆: </td>
    <td><input type="text" name="email3" id="email3" class="text_fld" /></td>
  </tr>
  <tr>
    <td style="text-align:right; font-size:14px; padding-right:15px; color:#666">婚姻状况: </td>
    <td><select name="reg_marital" id="reg_marital" class="drop_down">
        <option value="0" selected="selected">- 选择 -</option>
        <option value="1">已婚</option>
        <option value="4">恋爱中</option>
        <option value="3">我单身我快乐</option>
        <option value="2">单身寻找爱情</option>
      </select></td>
  </tr>
</table>
</div>

<div style="float:left; margin-top:40px" class="btn" id="step2_back_btn">上一步 </div>
<div style="float:right; margin-top:40px" class="btn" id="step2_next_btn">下一步 </div>

</div>
  
  </div>
  <div class="footer">
 
  <div class="footer_display"></div>
  
</div>
<jsp:include page="/footer.jsp" /></div>
</body>
</html>
