<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html class="no-js">
<head>
<meta charset="UTF-8">
<title>Online Contract Manufacturing Software. Free Trial | GDP Software</title>
<meta name="description" content="Global Design & Production online Contract Manufacturing software for business. Web based system for communicating effectively with suppliers and customers. Free Trial." />
<link href="/css/GDP-common.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="/css/style.css" />
<script type="text/javascript" src="/js/modernizr.custom.js"></script>
<script>
	Modernizr.load([
		{
		   test : window.XMLHttpRequest,//Firefox,Chrome...
		   yep :  ["/js/jquery-1.7.2.min.js","/js/jmpress.min.js", "/js/jquery.jmslideshow.js","/js/index.js","/js/index-ie.js"],
		}
		
	]);
	
	var agent=navigator.userAgent.toLowerCase();
	var is_iphone = ((agent.indexOf('iphone')!=-1));
	if (is_iphone) { 
		Modernizr.load([
			{
			    both : [ "/js/jquery-1.7.2.min.js", "/js/index.js",  "/js/jquery.placeholder.js"],
			}
	
		]);
	}
</script>

<noscript>
	<style>
	.step {
		width: 100%;
		position: relative;
	}
	.step:not(.active) {
		opacity: 1;
		filter: alpha(opacity=99);
		-ms-filter: "progid:DXImageTransform.Microsoft.Alpha(opacity=99)";
	}
	.step:not(.active) a.jms-link{
		opacity: 1;
		margin-top: 40px;
	}
	</style>
</noscript>
<!--#if expr="(${HTTP_USER_AGENT} = /iPhone/)"-->

	<script src="/js/jquery-1.7.2.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="/js/index.js"></script>
	<script type="text/javascript" src="/js/jquery.placeholder.js"></script>

<!--#endif -->
<!--[if lte IE 8]>

<style>
.container .index_content .right_section .right_area_bg .signin_box .signin_content .border_row input {
    display:block;
	line-height:35px;
}

div,ul,li{margin:0;padding:0;}
ul{list-style-type:none;}
h2,p{text-align:center; Arial;}
#box{position:relative;width:492px;height:372px;background:#fff;border-radius:5px;border:8px solid #fff;margin:10px auto;}
#box .list{position:relative;width:490px;height:370px;overflow:hidden;border:0px solid #ccc;}
#box .list li{position:absolute;top:0;left:0;width:490px;height:370px;opacity:0;filter:alpha(opacity=0);}
#box .list li.current{opacity:1;filter:alpha(opacity=100);}
#box .count{position:absolute;right:0;bottom:5px;}
#box .count li{color:#fff;float:left;width:20px;height:20px;cursor:pointer;margin-right:5px;overflow:hidden;opacity:0.7;filter:alpha(opacity=70);border-radius:20px;}
#box .count li.current{color:#fff;opacity:1;filter:alpha(opacity=100);font-weight:700;}
#tmp{width:100px;height:100px;background:red;position:absolute;}
</style>

<script type="text/javascript" src="/js/index.js"></script>
<script type="text/javascript" src="/js/placeholder.js"></script>
<script type="text/javascript" src="/js/html5.js"></script>
<script type="text/javascript" src="/js/index-ie.js"></script>
<![endif]-->

<!--[if lte IE 9]>

<style>
.container .index_content .right_section .right_area_bg .signin_box .signin_content .border_row .password_show {
	display:block;
}
</style>
<script>
$(function(){
$("#password_ie").hide();
$('.password_show').focus(function(){
		$('#password_ie').show().focus();
		$('.password_show').hide();
		});
		$('#password_ie').blur(function(){
		if($('#password_ie').val() == ""){
		$('.password_show').show();
		$('#password_ie').hide();
		}
		});
});
</script>
<![endif]-->
</head>
<body>
<div class="container">
    <div class="header">
      <a href="/"><img src="/images/gdp_logo.png" alt="GDP_logo"></a>
      <span>Global Design &amp; Production</span>
    </div>
    <div class="index_content">
    
  <div class="left_section">
      <h1>Contract manufacturing made simple</h1>
      
      <!-- IE -->
	    <div id="box">
		    <ul class="list">
		        <li class="current">
		        	<img src="/images/slide1.jpg" />
		        	<div class="slider_info" id="jms">
							<h2 >Live project updates</h2>
							<p>Create update notes on a time line for each project.</p>
					        <p >Request regualr updates from suppliers and issue reports to clients so </p>
					        <p>everyone is informed.</p>
					</div>
		        </li>
		        <li>
		        	<img src="/images/slide2.jpg" />
		        	<div class="slider_info" id="jms">
							<h2 >Real time reporting</h2>
					        <p >At a glance clearly identify those suppliers who have, or more importantly,  </p>
					        <p>have not reported in and given weekly update notes.</p>
					</div>
		        </li>
		        <li>
		        	<img src="/images/slide3.jpg" />
		        	<div class="slider_info" id="jms">
							<h2 >Native Language</h2>
					        <p >Suppliers can now add comments in their native language, allowing suppliers </p>
					        <p> update notes to be more natural and informative.</p>
					</div>
		        </li>
		        <li>
		        	<img src="/images/slide4.jpg"  />
		        	<div class="slider_info" id="jms">
							<h2 >Leverage Xero Accounting</h2>
					        <p >Contacts are kept synchronised via Xero's beautiful accounting system, if </p>
					        <p>you havenever seen zero in action check it out.</p>
					</div>
		        </li>
		    </ul>
		</div>
	    <!-- IE END -->
      
      
      
        <section id="jms-slideshow" class="jms-slideshow">
        	 <div class="step" >
				<img style="border:0 none;" src="/images/slide1.jpg" />
				<div class="jms-content">
					<h3 >Live project updates</h3>
					<p >Create upadate notes on a time line for each project.</p>
			        <p >Request regualr updates from suppliers and issue reports to clients so </p>
			        <p>everyone is informed.</p>
				</div>
			</div>
			 <div class="step"  data-y="90" data-rotate-x="80">
				<img style="border:0 none;" src="/images/slide2.jpg" />
				<div class="jms-content">
					<h3 >Real time reporting</h3>
	
			        <p >At a glance clearly identify those suppliers who have, or more importantly,  </p>
			        <p>have not reported in and given weekly update notes.</p>
				</div>
			</div>  
			<div class="step"  data-x="-100" data-z="150" data-rotate="1700">
				<img style="border:0 none;" src="/images/slide3.jpg" />
				<div class="jms-content">
					<h3 >Native Language</h3>
					
			        <p >Suppliers can now add comments in their native language, allowing suppliers </p>
			        <p> update notes to be more natural and informative.</p>
				</div>
			</div> 
			<div class="step"  data-x="3000" >
				<img src="/images/slide4.jpg" />
				<div class="jms-content">
					<h3 >Leverage Xero Accounting</h3>
					
			        <p >Contacts are kept synchronised via Xero's beautiful accounting system, if </p>
			        <p>you havenever seen zero in action check it out.</p>
				</div>
			</div>
        </section>
      </div>
      
      
      <div class="right_section">
        <div class="right_area_bg">
          <div class="register_button"><span><a href="/register">Try it for free today</a></span></div>
        </div>
        <div class="right_area_bg shake_box">
          <div class="signin_box">
            <div class="signin_title"><span>SIGN IN</span></div>
            <div class="signin_content">
              <div class="border_row"><input type="email" placeholder="Email" name="uemail"></div>
              <div class="border_row">
              	<input id="password_ie" type="password" placeholder="Password" name="password">
             	<input class="password_show" type="text" placeholder="Password">
              </div>
              <div class="rem_area">
                <input type="hidden" name="remember_me" value="1"/>
              	<span class="rem_icon"></span>
                <span class="rem_text">Remember me</span>
              </div>
              <div class="signin_button"><span>SIGN IN</span></div>
              <div class="cancel_button"><span>CANCEL</span></div>
            </div>
          </div>
        </div>
        <div class="right_area_bg last_box">
          <div class="xero_button"><span>Plug into Xero</span></div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>