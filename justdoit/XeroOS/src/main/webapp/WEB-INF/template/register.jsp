<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html class="no-js">
<head>
<meta charset="UTF-8">
<title>Register</title>
<link href="/css/GDP-common.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/js/modernizr.min.js"></script>
<script type="text/javascript" src="/js/jmpress.min.js"></script>
<script type="text/javascript" src="/js/jquery.jmslideshow.js"></script>
<script type="text/javascript" src="/js/registration.js"></script>
<script type="text/javascript" src="/js/placeholder.js"></script>
</head>
<!--[if lte IE 8]>

<style>
.mask_area {
filter:progid:DXImageTransform.Microsoft.Gradient(GradientType=0,StartColorStr='#bf000000',EndColorStr='#bf000000');
}
.container .register_content .left_section .right_area_bg .register_field .border_row input {
	line-height:40px;
}
.container .register_content .left_section .right_area_bg .register_field .border_row .typing {
    line-height:30px;
}
</style>

<![endif]-->

<!--[if lte IE 9]>

<style>
.container .register_content .left_section .right_area_bg .register_field .border_row .password_show {
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
<body>
<div class="mask_area"></div>
<div class="term_bg"><div class="term_content"></div></div>
  <div class="container">
    <div class="header">
      <a href="/"><img src="/images/gdp_logo.png" alt="GDP_logo"></a>
      <span>Global Design &amp; Production</span>
    </div>
    <div class="register_content">
      <div class="left_section">
        <h1>Try GDP for free</h1>
        <h2>Free trial for 30 days, unlimited projects and contacts!</h2>
        <div class="right_area_bg">
          <div class="register_field">
            <div class="border_row"><input type="text" placeholder="Company Name" name="companyName" id="register_companyName"></div>
            <div class="border_row"><input type="email" placeholder="Email Address" name="uemail" id="register_email"></div>
            <div class="border_row">
            <input id="password_ie" type="password" placeholder="Password" name="password" >
            <input class="password_show" type="text" placeholder="Password">
            </div>
            <div class="agree_row">
              <span class="icon_span not_accept"></span>
              <span class="text_span">I agree to the <strong>terms and conditions</strong></span>
            </div>
            <div class="link_row">
              <span class="icon_span not_accept"></span>
              <span class="text_span">I want to link to my Xero account</span>
            </div>
            <div class="signup_button"><span>SIGN UP</span></div>
            <span class="check_email" style="display:none">Email Taken</span>
            <span class="check_pawo" style="display:none">Password should be 6 ～ 12</span>
            <span class="check_term" style="display:none">You must agree to the terms.</span>
          </div>
        </div>
      </div>
      <div class="right_section">
        <h1>Customer Comments</h1>
        <div class="comment_box">
          <p>"GDP allows us to go from idea to desidn and right into production with amazing accuracy, we simply could not handle the volume we do now if we had to go back to spreadsheets and emails."</p>
          <h2>Charlotte Hill, Bristwith Ltd</h2>
        </div>
        <div class="comment_box">
          <p>"GDP took a huge amount of pressure of us, just handleing the information between suppliers and clients was eating up 80% of our day."</p>
          <h2>James Ghani, FABVip Ltd</h2>
        </div>
        <div class="comment_box">
          <p>"At last we're all on the same page, nobody gets a shocking or surprise email at the last minute, we have never fealt so connected to our foreign vendors."</p>
          <h2>Mohammed Imram, B&amp;M Bargains Ltd</h2>
        </div>
      </div>
    </div>
  </div>
</body>
</body>
<script>
var reg = new xero.Register();
reg.init();
$(function(){
	
	$(".agree_row .icon_span, .link_row .icon_span").click(function(){
		if($(this).hasClass("not_accept") == true){
			$(this).removeClass("not_accept");
		}else{
			$(this).addClass("not_accept");
		}
	});
	
	$(".border_row input").focus(function(){
		$(this).addClass("typing");
	});
	$(".border_row input").blur(function(){
		$(this).removeClass("typing");
	});
	
	$(".text_span strong").click(function(){
		$(".mask_area").fadeIn('fast', function () {
            $(".term_bg").fadeIn('fast');
        });
	});
	
	$(".mask_area").click(function(){
		$(".term_bg").fadeOut('fast', function () {
            $(".mask_area").fadeOut('fast');
        });
	});
});
</script>
</html>
