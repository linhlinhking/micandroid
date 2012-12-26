<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>Project Detail</title>
<link href="../css/GDP-common.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/js/placeholder.js"></script>
<script type="text/javascript" src="/js/mootools.js"></script>
<script type="text/javascript" src="/js/slider.js"></script>
<script>
jQuery(function(){
	jQuery(".show_icon").toggle(function() {
	    jQuery(this).addClass("not_show");
	},function(){
		jQuery(this).removeClass("not_show");
	});
	
	jQuery(".add_note_field .note_point").click(function(){
		jQuery(this).removeClass("white_point").addClass("red_point");
		jQuery(".add_note_bg").hide();
		jQuery(".note_bg").show();
		jQuery(".today_line span").removeClass("red_point").addClass("white_point");
	});
	
	jQuery(".today_line span").click(function(){
		jQuery(this).removeClass("white_point").addClass("red_point");
		jQuery(".note_bg").hide();
		jQuery(".add_note_bg").show();
		jQuery(".add_note_field .note_point").removeClass("red_point").addClass("white_point");
	});
	
});
</script>
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
   	<c:import url="/header-common" />
    <div class="contact_content project_content">
   	<%@ include file="/common/leftMenu.jsp"%>
   
   
      <div class="right_section">
        <div class="project_title">
          <span>Project Name / PO234234</span>
        </div>
        <div class="project_schedule" id="project_schedule_f">
          <span class="percent_span" >0%</span>
          <span class="percent_span" >10%</span>
          <span class="percent_span" >20%</span>
          <span class="percent_span" >30%</span>
          <span class="percent_span" >40%</span>
          <span class="percent_span" >50%</span>
          <span class="percent_span" >60%</span>
          <span class="percent_span" >70%</span>
          <span class="percent_span" >80%</span>
          <span class="percent_span" >90%</span>
          <span class="percent_span" >100%</span>
          <span class="start_date">04/12/2012</span>
          <span class="end_date">24/12/2012</span>
          <div class="slider_btn" id="slider_btn_t">
            <div class="t_part"></div>
            <div class="b_part"><span id="action_num">0%</span></div>
          </div>
          <div class="today_line" id="today_line">
            <span class="red_point"></span>
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
          <span class="note_point white_point" style="left:5%;"></span>
          <span class="note_point white_point" style="left:15%;"></span>
        </div>
      </div>
    </div>
  </div>
</body>
<script>
	window.addEvent('domready', function(){
		var slider = new Slider('project_schedule_f', 'slider_btn_t', {
			onComplete: function(val){
				jQuery("#action_num").html(Math.round(val) + "%");
			},
		});
	});
</script>
</html>














