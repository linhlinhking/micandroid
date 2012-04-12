<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>搜索宁波 - So Ningbo</title>


<script src="scripts/jquery-1.7.min.js" type="text/javascript"></script>
<script src="scripts/jquery.fileinput.min.js" type="text/javascript"></script>
<script src="scripts/so_ningbo.js" type="text/javascript"></script>
<script src="scripts/swfobject_modified.js" type="text/javascript"></script>
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
    <div style="font-weight:bold; font-size:16px; color:#999; margin-top:17px; margin-left:34px">  第二个步骤</div>
    <div style="font-size:12px; color:#999; margin-left:34px"> 个人资料信息</div>
    <div style="position:relative; top:-23px; left:200px; width:12px; height:10px;"><img src="images/tick.png" width="12" height="10" /></div>
    </div>
  
   <div style="display:block; width:38px; height:74px; float:left;"><img src="images/arrow_head.png" width="38" height="74" /></div>
  
    <div style="display:block; width:200px; height:74px; float:left;">
    <div style="font-weight:bold; font-size:16px; color:#666; margin-top:17px; margin-left:34px">  第三个步骤</div>
    <div style="font-size:12px; color:#666; margin-left:34px"> 资料图片</div>
    
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
<div style="font-size:18px;  color:#666">上传您的图片，完成您的个人资料</div>
<div style="font-size:12px;  color:#999">上传本地照片或使用摄像头拍摄并保存上传你的头像</div>

<div style="float:right; width:220px"><input type="file" name="file" id="image_upload" /><div style="margin-top:40px; width:100px" class="btn" id="webcam_image_btn">使用摄像头</div><div style="margin-top:40px; width:100px" class="btn" id="webcam_image_btn">保存照片</div></div>

<div style="width:320px; height:320px; margin-top:15px">
<div id="upload_image" style="float:left; width:319px; height:320px; background-image:url(images/no_photo_large.png)"><img src="images/photo_frame.png" width="319" height="320" /></div>
<div id="webcam_image" style="float:left; width:320px; height:320px;">
  <object id="FlashID" classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="319" height="320">
    <param name="movie" value="webcam.swf" />
    <param name="quality" value="high" />
    <param name="wmode" value="opaque" />
    <param name="swfversion" value="11.0.0.0" />
    <!-- This param tag prompts users with Flash Player 6.0 r65 and higher to download the latest version of Flash Player. Delete it if you don’t want users to see the prompt. -->
    <param name="expressinstall" value="scripts/expressInstall.swf" />
    <!-- Next object tag is for non-IE browsers. So hide it from IE using IECC. -->
    <!--[if !IE]>-->
    <object type="application/x-shockwave-flash" data="webcam.swf" width="319" height="320">
      <!--<![endif]-->
      <param name="quality" value="high" />
      <param name="wmode" value="opaque" />
      <param name="swfversion" value="11.0.0.0" />
      <param name="expressinstall" value="scripts/expressInstall.swf" />
      <!-- The browser displays the following alternative content for users with Flash Player 6.0 and older. -->
      <div>
        <h4>Content on this page requires a newer version of Adobe Flash Player.</h4>
        <p><a href="http://www.adobe.com/go/getflashplayer"><img src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif" alt="Get Adobe Flash player" width="112" height="33" /></a></p>
      </div>
      <!--[if !IE]>-->
    </object>
    <!--<![endif]-->
  </object>
</div>

</div>




<div style="float:left; margin-top:100px" class="btn" id="step3_back_btn">上一步 </div>
<div style="float:right; margin-top:100px" class="btn" id="step3_next_btn">完成</div>

</div>
  
  </div>
  <div class="footer">
 
  <div class="footer_display"></div>
  
  
  
</div>
<jsp:include page="/footer.jsp" /></div>
<script type="text/javascript">
swfobject.registerObject("FlashID");
</script>
</body>
</html>
