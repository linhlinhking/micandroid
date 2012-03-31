<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="/tags/c" prefix="c"%>
<%@ taglib uri="/tags/struts-tags" prefix="s"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>人力资源管理系统-系统登陆</title>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />	
        <link href="resources/css/general/login.css" rel="stylesheet" type="text/css" />
    </head>

	<body>
  <center>
      <form action="login" method="post">
      <div class="loginDiv" style="">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="17%" height="106">&nbsp;</td>
            <td width="16%">&nbsp;</td>
            <td width="47%">&nbsp;</td>
            <td width="1%">&nbsp;</td>
            <td width="19%">&nbsp;</td>
          </tr>
          <tr>
            <td height="32">&nbsp;</td>
            <td><span class="myFont">用户名：</span></td>
            <td><input name="userName" type="text" class="loginTextField" id="textfield"></td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td><span class="myFont">密　码：</span></td>
            <td><input name="userPass" type="password" class="loginTextField" id="textfield2"/></td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>
              <span class="myFont">验证码:</span></td>
            <td>
            	<input name="validCode" type="text" class="loginCheckCodeTextField" id="textfield3">
            	<img id="validateCodeImage" width="60" height="20" alt="验证码" src="validateCodeServlet"/>
           		<input name="button2" type="button" class="loginBtn" id="button2" class="loginBtn" value="看不清" onclick="refreshValidateCode();">
            </td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>
              <input name="button" type="submit" style="widows: 100px;" class="loginBtn" id="button" value="提交" />
              <input name="button3" type="reset"  class="loginBtn" id="button3" value="重置">
            </td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </table>
      </div>
      </form>
  </center>  

</body>
</html>

        <script type="text/javascript">

           function refreshValidateCode(){
              var _img=document.getElementById("validateCodeImage");
              _img.src="";
              setTimeout(function(){     
              _img.src="validateCodeServlet";              
              },10);
           }           
           function login(){
             alert('${sessionScope.validateCode}');
           }
        </script>
