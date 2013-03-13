<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>Project Detail Supply</title>
<link href="../css/GDP-common.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/js/placeholder.js"></script>
<script type="text/javascript" src="/js/mootools.js"></script>
<script type="text/javascript" src="/js/slider.js"></script>
<script type="text/javascript" src="/js/project-detail.js"></script>
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
</style>

<![endif]-->
<body>
  <div class="login_container">
    <div class="header login_header">
      <a href="/"><img src="/images/gdp_logo.png" alt="GDP_logo"></a>
      <span>Global Design &amp; Production</span>
    </div>
    <div class="contact_content project_content">
      <div class="right_section" style="margin:0;">
        <div class="project_title">
          <span>${project.projectName } / ${project.poNumber }</span>
          <input type="hidden" name="current_project_id" value="${project.id}"/>
          <input type="hidden" name="current_supplier_id" value="${supplierId}"/>
          <input type="hidden" name="current_creator" value="${supplierName}">
          <input type="hidden" name="current_emailId" value="${emailId}">
          
        </div>
        <div class="project_schedule" id="project_schedule_f">
          <span class="percent_span">0%</span>
          <span class="percent_span">10%</span>
          <span class="percent_span">20%</span>
          <span class="percent_span">30%</span>
          <span class="percent_span">40%</span>
          <span class="percent_span">50%</span>
          <span class="percent_span">60%</span>
          <span class="percent_span">70%</span>
          <span class="percent_span">80%</span>
          <span class="percent_span">90%</span>
          <span class="percent_span">100%</span>
          <span class="start_date">${sDate}</span>
          <span class="end_date">${eDate }</span>
          <div class="slider_btn" id="slider_btn_t">
            <div class="t_part"></div>
            <div class="b_part"><span id="action_num">0%</span></div>
          </div>
        </div>
        <div class="add_note_field">
          <div class="add_note_bg">
            <div class="add_note_content">
              <div class="add_note_top"><span>ADD NOTE</span></div>
              <div class="add_note_bottom">
                <textarea placeholder="Note"></textarea>
                <div class="cancel_button"><span>CANCEL</span></div>
                <div class="note_button"><span>NOTE</span></div>
                <div class="show_cus"><span class="show_icon"></span><span class="show_text">Show to Customer</span></div>
              </div>
            </div>
          </div>
          <div class="note_bg">
            <div class="note_content">
              <div class="note_top"><span class="left_span">User Name</span><span class="right_span">09/12/2012</span></div>
              <div class="note_bottom">
                <p>Note Paragraph</p>
                <div class="show_cus"><span class="show_icon"></span><span class="show_text">Show to Customer</span></div>
              </div>
            </div>
          </div>
        </div>
        <div class="note_point_field" style="width:870px;">
          <div class="today_line">
            <span class="red_point"></span>
          </div>
          <!--<span class="note_point white_point" style="left:5%;"></span>
          <span class="note_point white_point" style="left:15%;"></span>-->
        </div>
      </div>
    </div>
  </div>
  <input type="hidden" value="project_detail_supply" id="project_detail_supply"/>
</body>

</html>
