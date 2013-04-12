<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<title>Reset Password</title>
<link href="/css/GDP-common.css" rel="stylesheet" type="text/css">
<script src="/js/jquery-1.7.2.min.js" type="text/javascript"></script>
</head>
<body>
  <div class="container">
    <div class="header for_password">
      <a href="/"><img src="/images/gdp_logo.png" alt="GDP_logo"></a>
      <span>Global Design &amp; Production</span>
    </div>
    <div class="password_form">
      <h1>Reset Your Password</h1>
      <p>Please enter your email address and press the <strong class="green_strong">Reset Password</strong> button.</p>
      <p>We will send an email to the email address which has been written in the below.</p>
      <div class="type_field">
      	<p class="email_error">The email address is Invalid.</p>
        <label>Email Address:</label>
        <input type="email" name="currency_email">
      </div>
      <button id="reset_psd_btn">Reset Password</button>
    </div>
  </div>
</body>
<script type="text/javascript" src="/js/security.js"></script>
</html>