<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>Report</title>
<link href="/css/GDP-common.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/js/placeholder.js"></script>
<script type="text/javascript" src="/js/report.js"></script>

</head>
<!--[if lte IE 8]>

<style>
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
.login_container .report_content .right_section .report_title .search_bg input {
	line-height:25px;
}
.login_container .contact_content .right_section .c_details .c_details_content li {
	margin-top:-4px;
}
</style>

<![endif]-->
<body>
  <div class="login_container">
    <div class="header login_header">
      <a href="/"><img src="/images/gdp_logo.png" alt="GDP_logo"></a>
      <span>Global Design &amp; Production</span>
      <ul>
        <li><a href="/logout">Sign Out</a></li>
        <li><a href="/user">Manage Users</a></li>
        <li>
        	<c:if test="${xeroUser.planId == 1}">
        		<a href="/payment">${leftDays} Days Remaining</a>
        	</c:if>
        	<c:if test="${xeroUser.planId != 1}">
        		<a href="/payment">My Account</a>
        	</c:if>
        </li>
      </ul>
    </div>
    <div class="contact_content report_content">
      <div class="left_section">
        <ul>
          <li class="ie_f"><a href="/contact">Contacts</a></li>
          <li class="ie_s"><a href="/project">Projects</a></li>
          <li class="ie_t selected"><a href="/report">Reports</a></li>
        </ul>
      </div>
      
      <div class="right_section">
        <div class="report_title">
          <span>Reports</span>
          <div class="search_bg">
            <input type="text" placeholder="All suppliers who have not given an update">
          </div>
        </div>
        <div class="c_details">
          <div class="c_details_head">
            <span class="tel_area">Telephone</span>
            <span class="pnu_area">PO Number<strong></strong></span>
            <span class="pna_area">Project Name<strong></strong></span>
            <span class="dov_area">Days Overdue<strong></strong></span>
            <span class="sup_area">Supplier<strong></strong></span>
          </div>
          <ul class="c_details_content">
            <li class="li_defalut">
              <div class="info_area tel_info">+86 1234 567890</div>
              <div class="info_area pnu_info">PO8349857</div>
              <div class="info_area pna_info">Bags for Big Tom</div>
              <div class="info_area dov_info">3</div>
              <div class="info_area sup_info">Supplier Name</div>
            </li>
            <li class="li_defalut">
              <div class="info_area tel_info">+86 1234 567890</div>
              <div class="info_area pnu_info">PO8349857</div>
              <div class="info_area pna_info">Bags for Big Tom</div>
              <div class="info_area dov_info">3</div>
              <div class="info_area sup_info">Supplier Name</div>
            </li>
            <li class="li_defalut">
              <div class="info_area tel_info">+86 1234 567890</div>
              <div class="info_area pnu_info">PO8349857</div>
              <div class="info_area pna_info">Bags for Big Tom</div>
              <div class="info_area dov_info">3</div>
              <div class="info_area sup_info">Supplier Name</div>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
