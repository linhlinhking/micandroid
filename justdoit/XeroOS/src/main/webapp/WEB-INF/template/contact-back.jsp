<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>Contact Us</title>
<link href="/css/GDP-common.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>

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

  <div class="login_container">
   	<c:import url="/header-common" />
    <div class="contact_content">
     <div class="left_section">
		<ul>
			<li class="selected ie_f"><a href="/contact">Contacts</a></li>
			<li class="ie_s"><a href="/project">Projects</a></li>
			<li class="ie_t"><a href="/report">Reports</a></li>
		</ul>
	</div>
     
      <div class="right_section">
 		<h1 class="for_con_us">Contact Us</h1>
        <div class="contact_us">
          <p>GDP Limited</p>
          <p>Office B</p>
          <p>22 Matakana Road</p>
          <p>Matakana</p>
          <p>Auckland</p>
          <p>0985</p>
          <p>Contact phone number: 022 350 7981</p>
          <p>email: support@globaldesign.co.nz</p>
        </div>
      </div>
    
    
    </div>
  </div>
</body>
</html>

