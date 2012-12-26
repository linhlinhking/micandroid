<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>Contact</title>
<link href="/css/GDP-common.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/js/json2.js"></script>
<script type="text/javascript" src="/js/placeholder.js"></script>
<script type="text/javascript" src="/js/localStorage.js"></script>
<script type="text/javascript" src="/js/contact.js"></script>



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
.login_container .contact_content .right_section .contact_title .search_bg input {
	line-height:25px;
}
.login_container .contact_content .right_section .c_details .c_details_content li {
	margin-top:-4px;
}
.add_cus_bg .add_cus_content .border_row input {
	line-height:40px;
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
</style>
<![endif]-->
<body>
<div class="mask_area"></div>
<div class="add_cus_bg">
  <div class="add_cus_content" style="height:350px;">
    <div class="add_cus_title dividing_line"><span>Add a new customer</span></div>
    <div class="border_row dividing_line"><input type="text" placeholder="Company Name" name="companyName"></div>
    <div class="border_row dividing_line"><input type="email" placeholder="Email Address" name="uemail"></div>
    <div class="border_row dividing_line"><input type="text" placeholder="Telephone" name="telephone"></div>
    <input type="hidden" name="groupId"/>
    <input type="hidden" name="userId" value="${xeroUser.id}"/>
    <input type="hidden" name="isLinkXero" value="${isLinkXero}"/>
    <input type="hidden" name="_current_contactId"/>
    
    <div class="cancel_button"><span>CANCEL</span></div>
    <div class="del_button"><span>DELETE</span></div>
    <div class="add_button" style="right:329px;"><span>ADD CONTACT</span></div>
  </div>
</div>
  <div class="login_container">
    <div class="header login_header">
      <a href="/"><img src="/images/gdp_logo.png" alt="GDP_logo"></a>
      <span>Global Design &amp; Production</span>
      <ul>
        <li><a href="/logout">Sign Out</a></li>
        <li><a href="/user">Manage Users</a></li>
        <li>
        	<c:if test="${planId == 1}">
        		<a href="/payment">${leftDays} Days Remaining</a>
        	</c:if>
        	<c:if test="${planId != 1}">
        		<a href="/payment">My Account</a>
        	</c:if>
        </li>
      </ul>
    </div>
    <div class="contact_content">
      <div class="left_section">
        <ul>
          <li class="selected ie_f"><a href="/contact">Contacts</a></li>
          <li class="ie_s"><a href="/project">Projects</a></li>
          <li class="ie_t"><a href="/report">Reports</a></li>
        </ul>
      </div>
      
      <div class="right_section">
        <div class="contact_title">
          <span>Contacts</span>
          <div class="search_bg">
            <input id="search" type="text" placeholder="Search">
          </div>
          <div class="c_type_bg">
            <div class="c_type">
              <div class="plus_field">+</div>
              <div class="sup_field">Suppliers</div>
              <div class="cus_field">Customers</div>
              <div class="all_field selected_field">All</div>
            </div>
          </div>
        </div>
        <div class="c_details">
          <div class="c_details_head">
            <span class="p_area">Phone</span>
            <span class="e_area">Email</span>
            <span class="n_area">Name<strong></strong></span>
          </div>
          <input type="hidden" name="jsonResult" id="jsonResult"/>
          <ul class="c_details_content">
          
       
          </ul>
        </div>
      </div>
    
    
    </div>
  </div>
</body>
</html>
