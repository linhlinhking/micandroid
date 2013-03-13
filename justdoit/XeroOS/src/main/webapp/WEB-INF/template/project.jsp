<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>Project</title>

<link href="/css/GDP-common.css" rel="stylesheet" type="text/css">
<link href="/js/ui/south-street/jquery-ui-1.9.2.custom.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/js/ui/jquery-ui-1.9.2.custom.min.js"></script>
<script type="text/javascript" src="/js/placeholder.js"></script>
<script type="text/javascript" src="/js/project.js"></script>
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
.add_cus_bg .add_cus_content .border_row input {
	line-height:40px;
}
.login_container .project_content .right_section .project_title .search_bg input {
	line-height:25px;
}
.login_container .contact_content .right_section .c_details .c_details_content li {
	margin-top:-4px;
}
</style>

<![endif]-->
<body>
<div class="mask_area"></div>

<div class="add_cus_bg with_cus" style="margin: -250px 0 0 -305px;">
  <div class="add_cus_content">
    <div class="add_pro_title dividing_line"><span>Add a new project</span></div>
    <div class="border_row dividing_line"><input type="text" placeholder="Project Name" id="project_name"></div>
    <div class="border_row dividing_line"><input type="text" placeholder="PO Number" id="po_number"></div>
    <div class="border_row dividing_line cus_line">
      <input type="text"  placeholder="Customer Name" id="customerName">
      <ul class="cus_content"></ul>
    </div>
    <div class="border_row dividing_line"><input type="text" placeholder="Start Date" id="start_day"></div>
    <div class="border_row dividing_line"><input type="text" placeholder="End Date" id="end_day"></div>
    <div class="add_sup_line for_select">
      <span class="plus_btn"></span>
      <div class="input_div">
        <input class="sup_input" type="text" placeholder="Supplier" id="supplier">
        
        <ul class="sup_content"></ul>
      
      </div>
      <div class="input_div" style="margin:10px 0 0 38px;">
        <input class="lan_input"  type="text" placeholder="Language" id="language">
        <ul class="lan_content"></ul>
      </div>
      <span class="cross_btn"></span>
    </div>
    <div class="project_btn_line">
      <div class="cancel_button"><span>CANCEL</span></div>
      <div class="add_button"><span>ADD PROJECT</span></div>
    </div>
    <span class="check_name">Project Name should not be null</span>
    <span class="check_pon">PO Number should not be null</span>
    <span class="check_cname">Customer Name should not be null</span>
    <span class="check_sdate">Start Date should not be null</span>
    <span class="check_edate">End Date should not be null</span>
    <span class="check_sula">Supplier and Language should not be null</span>
  </div>
</div>

<div class="add_cus_bg without_cus" style="margin: -43px 0 0 -305px;">
  <div class="add_cus_content">
    <div class="add_pro_title dividing_line"><span>You need one customer and one supply at least</span><div class="close_btn"></div></div>
  </div>
</div>

  <div class="login_container">
 	<c:import url="/header-common" />
    <div class="contact_content project_content">
    	
     <div class="left_section">
		<ul>
			<li class="ie_f"><a href="/contact">Contacts</a></li>
			<li class="selected ie_s"><a href="/project">Projects</a></li>
			<li class="ie_t"><a href="/report">Reports</a></li>
		</ul>
	  </div>
      <div class="right_section">
        <div class="project_title">
          <span>Projects</span>
          <div class="search_bg">
            <input type="text" placeholder="Search" id="search">
          </div>
          <div class="c_type_bg">
            <div class="c_type">
              <div class="cus_field">Active</div>
              <div class="plus_field"></div>
              <div class="sup_field selected_field">Closed</div>
              <div class="selected_bg"></div>
            </div>
          </div>
        </div>
        <div class="c_details">
          <div class="c_details_head">
            <span class="ed_area">End Date<strong id="ed_area"></strong></span>
            <span class="sd_area">Start Date<strong id="sd_area"></strong></span>
            <span class="cn_area">Customer Name<strong id="cn_area"></strong></span>
            <span class="pn_area">PO Number<strong id="pn_area"></strong></span>
            <span class="na_area">Name<strong id="na_area"></strong></span>
          </div>
          <ul class="c_details_content">
      		
          </ul>
        </div>
        <div class="adv_box" style="display:none">
          <div class="adv_title">
            <span>Let's add some projects!</span>
          </div>
          <p>Contacts is a general term for Suppliers or Customers. To add a contact go ahead and click the +
button above.</p>
          <p>If you want to add a contact to the customers tab, first click Customers then click the + to add a new contact to the Customers tab, they will also apear in the all tab.</p>
          <p>If you want to add a contact to the Suppliers tab, first click Suppliers then click the + to add a new contact to the Suppliers tab, they will also apear in the all tab.</p>
          <p>You need to add atleast one contact to be able to add a project, as a project needs a Customer and
a supplier.</p>
        </div>
      </div>
    </div>
  </div>
  <input type="hidden" value="project" id="type_page"/>
  <input type="hidden" value="${xeroUser.id}" name="userId" id="userId"/>
  <input type="hidden"  name="supplierId" id="supplierId"/>
  <input type="hidden"  name="supplierEmail" id="supplierEmail"/>
  <input type="hidden"  name="customerId" id="customerId"/>
  <input type="hidden"  name="customerEmail" id="customerEmail"/>
  
  <input type="hidden" name="language_type" id="language_type"/>

  
</body>
<script type="text/javascript" src="/js/orderBy.js"></script>
</html>
