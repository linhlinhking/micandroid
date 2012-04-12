<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>搜索宁波 - So Ningbo</title>
<script src="scripts/jquery-1.7.min.js" type="text/javascript"></script>
<script type="text/javascript" src="http://192.168.1.105:8080/js/soningbo.api.v1.js"></script>
<link href="global.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
$(document).ready(function(){
	$('#sign_in_btn').click(function(){
		alert(1) ;
		//var log_data = function(data) { console.log(data); };
		//$.searchningbo_api.api({method:'resource/user/showAll',callback:log_data}) ;
	}
	
	
}

</script>
</head>

<body>
<div class="container">
  <div class="header" style="height:90px">
    
    <div class="logo"> 
    <a href="http://www.soningbo.com"> 
    <img src="images/home_logo.png" alt="搜索宁波 - So Ningbo" name="logo" width="180" height="46" id="logo" style="display:block;" /> 
    </a> 
    </div>
    
    <div class="sign_in">
    <table width="300" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>电子邮件 </td>
    <td>密码</td>
    <td>保持登录</td>
    <td width="78">&nbsp;</td>
  </tr>
  <tr>
    <td><input type="text" name="email" id="email" tabindex="1" /></td>
    <td><input type="password" name="password" id="password" tabindex="1" /></td>
    <td>
    
    <div class="keep_me">
    <div class="keep_me_cover" id="keeptrying"><img src="images/keep_me_cover.png" width="36" height="30" /></div>
    </div>
    
    </td>
    <td width="78">
    <div >
   
    
    <div class="btn" id="sign_in_btn" style="-moz-box-shadow: 0px 0px 0px 0 #CCC;
	-webkit-box-shadow: 0px 0px 0px 0 #CCC;
	box-shadow: 0px 0px 0px 0 #CCC;" >登录 </div>
    </div>
    
    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><div id="forgot_password" style="cursor:hand; cursor:pointer">忘记密码?</div></td>
    <td>&nbsp;</td>
    <td width="78">&nbsp;</td>
  </tr>
</table>
    </div> 
    
    
    
  </div>
  
  <div class="content">
  
  <div class="home_video">
  <div style="padding-top:50px; font-size:24px; font-weight:bold; color:#333">您的城市宁波</div>
  <div style="color:#333333"> 让搜索成为您生活的一部分！</div>
  <div style="padding-top:15px"><img src="images/video.jpg" width="430" height="262" /></div>
  </div>
  
  <div class="home_login">
    <div style="width:90%; margin: 0 auto; padding-top:50px; font-size:24px; color:#333333">
    注册<br />
    <div style="font-size:16px"> 与您的城市自由连接</div>
        <div class="greybar" style="margin-top:10px"></div>
    <div class="whitebar"></div>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" style="padding-top:15px">
  <tr>
    <td style="text-align:right; font-size:14px; padding-right:15px">昵称:</td>
    <td><input type="text" name="username" id="username" tabindex="1" class="text_fld" /></td>
  </tr>
  <tr>
    <td style="text-align:right; font-size:14px; padding-right:15px">真实姓名:</td>
    <td><input type="text" name="name_cn" id="name_cn" tabindex="1" class="text_fld" /></td>
  </tr>
  <tr>
    <td style="text-align:right; font-size:14px; padding-right:15px">电子邮件:</td>
    <td><input type="text" name="remail" id="remail" tabindex="1" class="text_fld" /></td>
  </tr>
  <tr>
    <td style="text-align:right; font-size:14px; padding-right:15px">密码:</td>
    <td><input type="password" name="rpassword" id="rpassword" tabindex="1" class="text_fld" /></td>
  </tr>
  <tr>
    <td style="text-align:right; font-size:14px; padding-right:15px">确认密码:</td>
    <td><input type="password" name="confirm_password" id="confirm_password" tabindex="1" class="text_fld" /></td>
  </tr>
  <tr>
    <td style="text-align:right; font-size:14px; padding-right:15px">性别:</td>
    <td style="font-size:14px;">
    <input type="radio" name="gender" value="1" checked="checked" />男&nbsp;&nbsp;&nbsp;&nbsp;
    <input type="radio" name="gender" value="0" />女
    </td>
  </tr>
  <tr>
    <td style="text-align:right; font-size:14px; padding-right:15px">出生日期:</td>
    <td>
    <input name="birthday" id="birthday" class="Wdate text_fld" type="text" onfocus="WdatePicker({skin:'blueFresh',maxDate:'%y-%M-%d',lang:'auto'})" value="1990-01-01"/>
    </td>
  </tr>
  <tr>
    <td style="text-align:right; font-size:14px; padding-right:15px">&nbsp;</td>
    <td>
    
    <div id="reg_signup_btn"><div style="margin-left:90px;">注册</div></div>
    
    </td>
  </tr>
</table>

 <div class="greybar" style="margin-top:10px"></div>
    <div class="whitebar"></div>
    
   <div style="text-align:center; font-size:12px; padding-top:10px"> 为您的企业创建页面，点击这里</div>
    </div>
    
  
  </div>
  
 <jsp:include page="/footer.jsp" />
 </div>
</div>
</body>
</html>
