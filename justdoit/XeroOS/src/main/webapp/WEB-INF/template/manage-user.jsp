<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>Manage user</title>
<link href="/css/GDP-common.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
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
.login_container .manage_content .right_section .manage_title .search_bg input {
	line-height:25px;
}
.login_container .contact_content .right_section .c_details .c_details_content li {
	margin-top:-4px;
}
</style>

<![endif]-->
<body>
<div class="mask_area"></div>
<div class="add_cus_bg">
  <div class="add_cus_content" style="height:350px;">
    <div class="add_manage_title dividing_line"><span>Add a new user</span></div>
    <div class="border_row dividing_line"><input type="text" placeholder="Name" name="name"></div>
    <div class="border_row dividing_line"><input type="email" placeholder="Email Address" name="uemail"></div>
    <div class="border_row dividing_line"><input type="password" placeholder="Password" name="password"></div>
    <input type="hidden" name="companyId" value="${currentCompany}"/>
    <input type="hidden" name="planId" value="${planId}"/>
    <input type="hidden" name="expiredDate" value="${xeroUser.expiredDateTime}"/>
    <input type="hidden" name="_current_userId"/>
    <div class="cancel_button"><span>CANCEL</span></div>
    <div class="del_button"><span>DELETE</span></div>
    <div class="add_button" style="right:329px;"><span>ADD USER</span></div>
  </div>
</div>
  <div class="login_container">
    <div class="header login_header">
      <a href="/"><img src="/images/gdp_logo.png" alt="GDP_logo"></a>
      <span>Global Design &amp; Production</span>
      <ul>
        <li><a href="/logout">Sign Out</a></li>
        <li><a style="color:#000; font-weight:bold;" href="/user">Manage Users</a></li>
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
    <div class="contact_content manage_content">
      <div class="left_section">
        <ul>
          <li class="ie_f"><a href="/contact">Contacts</a></li>
          <li class="ie_s"><a href="/project">Projects</a></li>
          <li class="ie_t"><a href="/report">Reports</a></li>
        </ul>
      </div>
      <div class="right_section">
        <div class="manage_title">
          <span>Manage Users</span>
          <div class="search_bg">
            <input type="text" placeholder="Search" id="search">
          </div>
          
         <c:if test="${planId == 3 || (planId == 2 && allowsRegisteredUsers < 5)}">
          	 <a class="add_manage_btn"></a>
          </c:if>
          
          <c:if test="${planId == 1 || (planId == 2 && allowsRegisteredUsers == 5)}">
          	 <a href="/payment" class="add_manage_btn"></a>
          </c:if>
          
        </div>
        <div class="c_details">
          <div class="c_details_head">
            <span class="dad_area">Date Added<strong id="dad_area"></strong></span>
            <span class="lse_area">Last Seen<strong id="lse_area"></strong></span>
            <span class="ema_area">Email<strong id="ema_area"></strong></span>
            <span class="nam_area">Name<strong id="nam_area"></strong></span>
          </div>
          <ul class="c_details_content">
          <!-- 
            <li class="li_defalut">
              <div class="info_area dad_info">9/11/2011</div>
              <div class="info_area lse_info">1/10/2012</div>
              <div class="info_area ema_info">testemail@gmail.com</div>
              <div class="info_area nam_info">User Name</div>
            </li>
            <li class="li_defalut">
              <div class="info_area dad_info">9/11/2011</div>
              <div class="info_area lse_info">1/10/2012</div>
              <div class="info_area ema_info">testemail@gmail.com</div>
              <div class="info_area nam_info">User Name</div>
            </li>
            <li class="li_defalut">
              <div class="info_area dad_info">9/11/2011</div>
              <div class="info_area lse_info">1/10/2012</div>
              <div class="info_area ema_info">testemail@gmail.com</div>
              <div class="info_area nam_info">User Name</div>
            </li>
             -->
             
          </ul>
        </div>
      </div>
    </div>
  </div>
  <input type="hidden" value="user" id="type_page"/>
</body>
<script type="text/javascript" src="/js/orderBy-com.js"></script>
<script type="text/javascript" src="/js/manager-user.js"></script>
</html>