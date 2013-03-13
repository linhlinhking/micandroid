<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<title>Online Contract Manufacturing Software. Free Trial | GDP Software</title>
<meta name="description" content="Global Design & Production online Contract Manufacturing software for business. Web based system for communicating effectively with suppliers and customers. Free Trial." />
<link href="/css/GDP-common.css" rel="stylesheet" type="text/css">
<script src="/js/jquery-1.7.2.min.js" type="text/javascript"></script>
<script type="text/javascript" src="/js/modernizr.min.js"></script>
<script type="text/javascript" src="/js/index.js"></script>
<script type="text/javascript" src="/js/placeholder.js"></script>
<!--[if lte IE 8]>

<style>
.container .index_content .right_section .right_area_bg .signin_box .signin_content .border_row input {
    display:block;
	line-height:35px;
}
</style>
<script type="text/javascript" src="/js/html5.js"></script>
<![endif]-->

<!--[if lte IE 9]>

<style>
.container .index_content .right_section .right_area_bg .signin_box .signin_content .border_row .password_show {
	display:block;
}
</style>
<script>
$(function(){
$("#password_ie").hide();
$('.password_show').focus(function(){
		$('#password_ie').show().focus();
		$('.password_show').hide();
		});
		$('#password_ie').blur(function(){
		if($('#password_ie').val() == ""){
		$('.password_show').show();
		$('#password_ie').hide();
		}
		});
});
</script>
<![endif]-->
</head>
<body>
  <div class="container">
    <div class="header">
      <a href="/"><img src="/images/gdp_logo.png" alt="GDP_logo"></a>
      <span>Global Design &amp; Production</span>
    </div>
    <div class="index_content">
      <div class="left_section">
        <h1>Contract manufacturing made simple</h1>
        
        
        
        <div class="slider_area_bg">
          <div class="home_slider">
            <div class="main_slider_box">
              <span class="first_status"></span>
              <span class="second_status"></span>
              <span class="third_status"></span>
            </div>
            <div class="slider_point">
              <span class="default_point selected_point" id="for_fs"></span>
              <span class="default_point" id="for_ss"></span>
              <span class="default_point" id="for_ts"></span>
            </div>
          </div>
        </div>
        
        
        
        
        
      </div>
      <div class="right_section">
        <div class="right_area_bg">
          <div class="register_button"><span><a href="/register">Try it for free today</a></span></div>
        </div>
        <div class="right_area_bg shake_box">
          <div class="signin_box">
            <div class="signin_title"><span>SIGN IN</span></div>
            <div class="signin_content">
              <div class="border_row"><input type="email" placeholder="Email" name="uemail"></div>
              <div class="border_row"><input id="password_ie" type="password" placeholder="Password" name="password"><input class="password_show" type="text" placeholder="Password"></div>
              <div class="rem_area">
                <span class="rem_icon"></span>
                <span class="rem_text">Remember me</span>
              </div>
              <div class="signin_button"><span>SIGN IN</span></div>
              <div class="cancel_button"><span>CANCEL</span></div>
            </div>
          </div>
        </div>
        <div class="right_area_bg last_box">
          <div class="xero_button"><span>Plug into Xero</span></div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
