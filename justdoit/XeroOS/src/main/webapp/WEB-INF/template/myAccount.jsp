<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>My Account</title>
<link href="/css/GDP-common.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/js/placeholder.js"></script>
<script type="text/javascript" src="/js/account.js"></script>

</head>
<!--[if lte IE 8]>

<style>
.mask_area {
filter:progid:DXImageTransform.Microsoft.Gradient(GradientType=0,StartColorStr='#bf000000',EndColorStr='#bf000000');
}
.login_header ul {
	margin-top:-56px;
}
.login_header ul li a {
	width:auto;
	height:auto;
}
.login_container .contact_content .left_section ul .ie_f {
	background:url(../images/contacts_icon.jpg) no-repeat 0 7px;
}
.login_container .contact_content .left_section ul .ie_s {
    background:url(../images/projects_icon.jpg) no-repeat 0 7px;
}
.login_container .contact_content .left_section ul .ie_t {
    background:url(../images/reports_icon.jpg) no-repeat 0 5px;
}
.login_container .account_content .right_section .pay_area_bg .pay_box .pay_content .each_box .ie_p1 {
	margin-top:24px;
}
.login_container .account_content .right_section .pay_area_bg .pay_box .pay_content .each_box .ie_p2 {
	margin-top:12px;
}
</style>

<![endif]-->
<body>
<div class="mask_area"></div>
<div class="add_cus_bg pay_show" style="margin: -300px 0 0 -305px;">
  <div class="add_cus_content">
    <div class="add_payment_title dividing_line"><span>Payment</span></div>
    <div class="border_row dividing_line"><input type="text" placeholder="Name On Card"></div>
    <div class="border_row dividing_line"><input type="text" placeholder="Card Number"></div>
    <div class="border_row dividing_line"><input type="text" placeholder="CV Number"></div>
    <div class="border_row dividing_line"><input type="text" placeholder="Start Date"></div>
    <div class="border_row dividing_line"><input type="text" placeholder="Expiry Date"></div>
    <div class="border_row dividing_line"><input type="text" placeholder="Issue Number"></div>
    <div class="border_row dividing_line"><input type="text" placeholder="Address 1"></div>
    <div class="border_row dividing_line"><input type="text" placeholder="Address 2"></div>
    <div class="border_row dividing_line"><input type="text" placeholder="Postal Code"></div>
    <div class="border_row dividing_line"><input type="text" placeholder="Amount (NZD)"></div>
    <div class="project_btn_line">
      <div class="cancel_button"><span>CANCEL</span></div>
      <div class="add_button"><span>PAY</span></div>
    </div>
  </div>
</div>
<div class="add_cus_bg cancel_show">
  <div class="add_cus_content" style="height:350px;">
    <div class="add_payment_title dividing_line"><span>Cancel Account</span></div>
    <div class="can_acc_info"><textarea placeholder="Feedback"></textarea></div>
    <p>Your Account Is Active Untill 12/21/2012</p>
    <div class="del_button"><span>REMOVE&nbsp;&nbsp;ACCOUNT</span></div>
  </div>
</div>
  <div class="login_container">
 	<c:import url="/header-common" />
    <div class="contact_content account_content">
    <%@ include file="/common/leftMenu.jsp"%>
    
    
      <div class="right_section">
        <div class="account_title">
          <span>My Account</span>
        </div>
        <div class="pay_area_bg">
          <div class="pay_box">
            <div class="pay_title">
            	<span>FREE 30 DAYS</span>
            	<span>CORPORATE</span>
            	<span>ENTERPRISE</span>
            </div>
            <!-- 
            	not_buy : buy
            	con_buy : cancel
             -->
            <div class="pay_content">
              <div class="each_box border_box" id="freeUser">
                <p class="ie_p1">1 User</p>
                <p class="ie_p2">Free for 30 Days</p>
                <span class="buy_status not_buy" id="1"></span>
              </div>
              <div class="each_box border_box" id="fiveUsers">
                <p class="ie_p1">0 - 5 User's</p>
                <p class="ie_p2">39.99 NZD Monthly</p>
                <span class="buy_status not_buy" id="2"></span>
              </div>
              <div class="each_box" id="unlimitedUser">
                <p class="ie_p1">Unlimited</p>
                <p class="ie_p2">49.99 NZD Monthly</p>
                <span class="buy_status not_buy" id="3"></span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
