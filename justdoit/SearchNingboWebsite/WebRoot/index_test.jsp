<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<c:set value="${pageContext.request.contextPath}" var="ctx" />
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="soningbo搜索宁波--是一个专门为广大用户服务，用于方便的搜索到用户自己想要找的或者去的任何地址，如车站、餐厅、旅游场景、娱乐场所、公司、旅馆等地方。">
<title>搜索宁波 - So Ningbo</title>
<script src="${ctx}/scripts/jquery-1.7.min.js" type="text/javascript"></script>
<script src="${ctx}/scripts/so_ningbo_test.js" type="text/javascript"></script>
<script src="${ctx}/scripts/autoComplete.js" type="text/javascript"></script>
<link rel="stylesheet"  type="text/css" href="${ctx}/css/global_test.css" />
<link rel="stylesheet" id="cssStyle" type="text/css" href="${ctx}/css/genral_test.css" />

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

      document.getElementById("cssStyle").href="${ctx}/css/ie_test.css";
      break;
     case "webkit":     //safari or chrome
	 case "opera":      //opera
	 case "mozilla":    //Firefox
     document.getElementById("cssStyle").href="${ctx}/css/genral_test.css";
      break;
     default:    
	 document.getElementById("cssStyle").href="${ctx}/css/ie_test.css";
      break;
    }
  }
   userAgent();
        </script>
</head>

<body><div class="container">
<header><div id="header">
<div class="logo"><span><a href="${ctx}/index_test_English.html">English</a></span></div></div></header>
<div class="content">
<section>
<div class="section_st">
<form>
<h1>搜索宁波测试版</h1>
<div class="submit_information_before" id="regInfo">
<h2>即将粉墨登场，抓住先机注册成会员</h2>
<div class="distance"><label>姓名</label>
<input type="text" name="name" id="name" required></div>
<div class="distance_nd"><label>邮箱</label>
<input type="email" name="email" id="email" required></div>
<div class="box_register" id="box_register"  onclick="regInfo('cn')"><span>提交信息</span></div>
</div>
<h1>商家确认注册信息</h1>
<h3>请输入公司名称确认是否已注册，如未注册<br/>
请联系我们&nbsp;&nbsp;&nbsp;咨询电话：&nbsp;0574-87200625</h3>
<div class="distance_rd"><label>公司名称</label>
<input type="text" id="address" required></div>
</form>
</div></section>
<section>
<div class="section_nd">
<div class="p_distance">
<h1>关于搜索宁波</h1>
<h2>一个全新的专注于宁波的本地搜索网络</h2>
<p>搜索宁波是宁波唯一一个拥有超过两万个本地商家，上万家公司<br/>工厂，允许会员上传地点、评级评论、互动交流的网络。</p>
<p>搜索宁波能让你找到在宁波你未发现的娱乐和便捷之处。</p>
<p>搜索宁波通过地图搜索和GPS定位找到您和朋友的位置，通过创<br/>建事件来邀请各路朋友参加您组织筹办的公共活动或私人聚会。</p>
<p>好玩、好用、好方便！</p>
<p>搜索宁波于网站、iphone和安卓程序同时运行。</p></div>
<div class="location">
<span>已注册的位置数</span>
<div id="loc_num">
<div class="total one"></div>
<div class="total two"></div>
<div class="total three"></div>
<div class="total four"></div>
<div class="total five"></div>
<div class="total six"></div>
</div>
</div>
</div></section></div>
<div class="link_page" id="link_page">
<span><a href="${ctx}/category/first?id=1">美容&amp;美发</a></span>
<span><a href="information.html">酒店&amp;住所</a></span>
<span><a href="information.html">咖啡馆&amp;餐厅</a></span>
<span><a href="information.html">运动&amp;休闲</a></span>
<span><a href="information.html">组织机构</a></span>
<span><a href="information.html">夜生活</a></span>
<span><a href="information.html">企业</a></span>
<span><a href="information.html">教育</a></span>
<span><a href="information.html">健康</a></span>
<span><a href="information.html">服务</a></span>
<span><a href="information.html">购物</a></span>
<span><a href="information.html">出行</a></span>
<span><a href="information.html">大厦</a></span>
<span><a href="information.html">银行</a></span>
<span><a href="information.html">媒体</a></span>
</div>
<footer><div id="footer">
<div class="copyright"><img src="${ctx}/images/nm_logo.png" alt="宁波商外文化传媒"/><br/>© 2012 宁波商外文化传媒</div>
<div class="message">客服电话：0574-87200625（个人） 0574-83860743（企业） （按当地市话标准计费）<br/><span>宁波ICP证000000号</span></div>
<div class="report"><a href="http://net.china.cn/" target="_blank"><img src="${ctx}/images/ciirc.png" alt="不良信息举报"/><br/>不良信息举报中心</a></div></div></footer></div>
</body>
</html>
