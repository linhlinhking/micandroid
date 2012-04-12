<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<% String path = request.getContextPath(); %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="soningbo搜索宁波--是一个专门为广大用户服务，用于方便的搜索到用户自己想要找的或者去的任何地址，如车站、餐厅、旅游场景、娱乐场所、公司、旅馆等地方。">
<title>搜索宁波 - So Ningbo</title>
<link rel="stylesheet"  type="text/css" href="<%=path %>/css/global_test.css" />
<link rel="stylesheet" id="cssStyle" type="text/css" href="<%=path %>/css/genral_test.css" />
<script src="<%=path %>/scripts/jquery-1.7.min.js" type="text/javascript"></script>
<script src="<%=path %>/scripts/so_ningbo_showcateg.js" type="text/javascript"></script>s
<script type="text/javascript">

function userAgent(){
    var ua = navigator.userAgent;
    ua = ua.toLowerCase();
    var match = /(webkit)[ \/]([\w.]+)/.exec(ua) ||
    /(opera)(?:.*version)?[ \/]([\w.]+)/.exec(ua) ||
    /(msie) ([\w.]+)/.exec(ua) ||
    !/compatible/.test(ua) && /(mozilla)(?:.*? rv:([\w.]+))?/.exec(ua) ||
    [];
    //如果需要获取浏览器版本号：match[2]
    switch(match[1]){
     case "msie":      //ie

      document.getElementById("cssStyle").href="<%=path %>/css/ie_test.css";
      break;
     case "webkit":     //safari or chrome
	 case "opera":      //opera
	 case "mozilla":    //Firefox
     document.getElementById("cssStyle").href="<%=path %>/css/genral_test.css";
      break;
     default:    
	 document.getElementById("cssStyle").href="<%=path %>/css/ie_test.css";
      break;
    }
}
userAgent();
        </script>
</head>

<body><div class="container">
<header><div id="header">
<div class="logo"><span>English</span></div></div></header>
<div class="content">
<section>
<div class="section_st" id="section_st">
</div></section>
<section>
<div class="section_nd" id="section_nd">
</div></section></div>
<footer><div id="footer">
<div class="copyright"><img src="<%=path %>/images/nm_logo.png" alt="宁波商外文化传媒"/><br/>© 2012 宁波商外文化传媒</div>
<div class="message">客服电话：0574-87200625（个人） 0574-83860743（企业） （按当地市话标准计费）<br/><span>宁波ICP证000000号</span></div>
<div class="report"><a href="http://net.china.cn/" target="_blank"><img src="<%=path %>/images/ciirc.png" alt="不良信息举报"/><br/>不良信息举报中心</a></div></div></footer></div>
</body>
</html>
