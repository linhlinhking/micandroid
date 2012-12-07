<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html class="no-js">
<head>
<meta charset="UTF-8">
<title>Online Contract Manufacturing Software. Free Trial | GDP Software</title>
<meta name="description" content="Global Design & Production online Contract Manufacturing software for business. Web based system for communicating effectively with suppliers and customers. Free Trial." />
<link href="/css/GDP-common.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/js/modernizr.min.js"></script>
<script type="text/javascript" src="/js/index.js"></script>
</head>
<!--[if lte IE 8]>

<style>
.container .index_content .right_section .right_area_bg .signin_box .signin_content .border_row input {
	line-height:35px;
}
</style>
<script type="text/javascript" src="/js/html5.js"></script>
<![endif]-->
<body>
<div class="container">
    <div class="header">
      <a href="/"><img src="/images/gdp_logo.png" alt="GDP_logo"></a>
      <span>Global Design &amp; Production</span>
    </div>
    <div class="index_content">
      <div class="left_section">
        <h1>Contract manufacturing made simple</h1>
        <div class="slider_area">
          <img src="/images/slide1.jpg" alt="s1">
          <div class="slider_info">
            <h1>Live project updates</h1>
            <p>Create upadate notes on a time line for each project.</p>
            <p>Request regualr updates from suppliers and issue reports to clients so everyone is informed.</p>
          </div>
        </div>
      </div>
      <div class="right_section">
        <div class="right_area_bg">
          <div class="register_button"><span><a href="/register">Try it for free today</a></span></div>
        </div>
        <form class="right_area_bg shake_box" method="POST" action="/login" id="login_form">
          <div class="signin_box">
            <div class="signin_title"><span>SIGN IN</span></div>
            <div class="signin_content">
              <div class="border_row"><input type="email" placeholder="Email" name="uemail"></div>
              <div class="border_row"><input type="password" placeholder="Password" name="password"></div>
              <div class="rem_area">
                <input type="checkbox" id="rem_check">
                <input type="hidden" name="remember_me" value="1"/>
                <label class="rem_icon" for="rem_check"></label>
                <span class="rem_text">Remember me</span>
              </div>
              <c:if test="${null != login_status}">
              	<input type="hidden" name="login_statue" value="1"/>
              </c:if>
              <input class="signin_button btn_for_sub" type="submit" value="SIGN IN">
              <div class="signin_button"><span style="line-height:33px;">SIGN IN</span></div>
              <div class="cancel_button"><span style="line-height:33px;">CANCEL</span></div>
            </div>
          </div>
        </form>
        <div class="right_area_bg last_box">
          <div class="xero_button"><span>Plug into Xero</span></div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>