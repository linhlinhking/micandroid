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
<link href="/js/ui/south-street/jquery-ui-1.9.2.custom.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/js/ui/jquery-ui-1.9.2.custom.min.js"></script>
<script type="text/javascript" src="/js/placeholder.js"></script>

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
<div class="pay_mask"></div>
<div class="add_cus_bg pay_show" style="margin: -212px 0 0 -405px;">
  <div class="add_cus_content">
    <div class="add_payment_title dividing_line" style="position:relative;"><span>Payment</span><img src="/images/paymentexpress.png" alt="express"></div>
    <div class="border_row dividing_line">
    <label class="change_l">Amount (NZD):</label>
    <input type="text" id="amount" class="change_i" name="Amount"></div>
    <input type="hidden" name="CurrencyInput" value="NZD">
    <input type="hidden" name="TxnType" value="Purchase">
    <input type="hidden" name="EnableAvsData" value="1">
    <input type="hidden" name="AvsAction" value="1">
    <input type="hidden" name="EnableAddBillCard" value="1">
    <input type="hidden" name="TxnId" value="${txnId}">
    <input type="hidden" name="MerchantReference" value="${txnId}">
    
    
    <div class="border_row dividing_line" style="height:35px;padding-top:5px;">
      <div class="for_card_type">
      	<label class="change_l">Card Type:</label>
      	<select style="display: inline-block; margin: 11px 5px 0; vertical-align: middle;" id="currency_cardType">
      	  <option>Visa</option>
      	  <option>Master Card</option>
      	</select>
      	<!-- 
        <input type="text" id="card_type" name="CardType">
        <ul class="type_lists" style="left:84px;">
          <li>Visa</li>
          <li>Master Card</li>
        </ul>
         -->
         
      </div>
    </div>
    <div class="border_row dividing_line">
    	<label class="change_l">Address:</label>
    	<input type="text" id="address1" class="change_i" name="AvsStreetAddress">
    </div>
    <div class="border_row dividing_line">
    	<label class="change_l">Card Number:</label>
    	<input type="text" id="card_number" class="change_i" name="CardNumber">
    </div>
  
    <div class="border_row dividing_line">
    <label class="change_l">Postal Code:</label>
    <input type="text" id="postal_code" class="change_i" name="AvsPostCode"></div>
    
    
    <div class="border_row dividing_line">
    <label class="change_l">Name On Card:</label>
    <input type="text" id="name_on_card"  class="change_i" name="CardHolderName"></div>
    <div class="border_row dividing_line">
    <label class="change_l">Start Date:</label>
    <input type="hidden" id="start_date" name="DateStart">
    <select id="sd_m">
    	<option value="01">01-January</option>
		<option value="02">02-February</option>
		<option value="03">03-March</option>
		<option value="04">04-April</option>
		<option value="05">05-May</option>
		<option value="06">06-June</option>
		<option value="07">07-July</option>
		<option value="08">08-August</option>
		<option value="09">09-September</option>
		<option value="10">10-October</option>
		<option value="11">11-November</option>
		<option value="12">12-December</option>
    </select>
    <select id="sd_y">
    	<option value="00">2000</option>
		<option value="01">2001</option>
		<option value="02">2002</option>
		<option value="03">2003</option>
		<option value="04">2004</option>
		<option value="05">2005</option>
		<option value="06">2006</option>
		<option value="07">2007</option>
		<option value="08">2008</option>
		<option value="09">2009</option>
		<option value="10">2010</option>
		<option value="11">2011</option>
		<option value="12">2012</option>
    	<option value="13">2013</option>
    </select>
    </div>
    
     <div class="border_row dividing_line" style="position:relative;">
      <label class="change_l">CV Number:</label>
      <input type="text" id="cv_number" class="change_i" name="Cvc2">
      <input type="hidden" name="Cvc2Presence" value="1">
      <img class="icon_for_cv" src="/images/cvv.png" alt="cv">
    </div>
    
    <div class="border_row dividing_line">
    <label class="change_l">Expiry Date:</label>
    <input type="hidden" id="expiry_date" name="DateExpiry">
  
    <select id="ed_m">
    	<option value="01">01-January</option>
		<option value="02">02-February</option>
		<option value="03">03-March</option>
		<option value="04">04-April</option>
		<option value="05">05-May</option>
		<option value="06">06-June</option>
		<option value="07">07-July</option>
		<option value="08">08-August</option>
		<option value="09">09-September</option>
		<option value="10">10-October</option>
		<option value="11">11-November</option>
		<option value="12">12-December</option>
    </select>
    <select id="ed_y">
    	<option value="13">2013</option>
		<option value="14">2014</option>
		<option value="15">2015</option>
		<option value="16">2016</option>
		<option value="17">2017</option>
		<option value="18">2018</option>
		<option value="19">2019</option>
		<option value="20">2020</option>
		<option value="21">2021</option>
		<option value="22">2022</option>
		<option value="23">2023</option>
		<option value="24">2024</option>
		<option value="25">2025</option>
		<option value="26">2026</option>
		<option value="27">2027</option>
		<option value="28">2028</option>
    </select>
    </div>
    <div class="border_row dividing_line">
    <label class="change_l">Issue Number:</label>
    <input type="text" id="issue_number" class="change_i" name="IssueNumber"></div>
    <div class="project_btn_line">
      <div class="cancel_button"><span>CANCEL</span></div>
      <div class="add_button"><span>PAY</span></div>
      <img src="/images/card_icons.png" alt="card_icons">
      <div class="pay_err"></div>
    </div>
    <!--   -->
    <span class="l1">Invalid Amount</span>
    <span class="r1">Invalid Card Type</span>
    <span class="l2">Invalid Address</span>
    <span class="r2">Invalid Card Number</span>
    <span class="l3">Invalid Postal Code</span>
    <span class="r3">Invalid Name On Card</span>
    <span class="l4">Invalid Start Date</span>
    <span class="r4">Invalid CV Number</span>
    <span class="l5">Invalid Expiry Date</span>
    <span class="r5">Invalid Issue Number</span>
    
    
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
            	<span class="first_span">FREE 30 DAYS</span>
            	<span class="second_span">CORPORATE</span>
            	<span class="third_span">ENTERPRISE</span>
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
<script type="text/javascript" src="/js/common/base.js"></script>
<script type="text/javascript" src="/js/account.js"></script>
</html>
