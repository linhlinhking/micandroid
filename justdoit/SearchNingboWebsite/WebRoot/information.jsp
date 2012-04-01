<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<c:set value="${pageContext.request.contextPath}" var="ctx" />
<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="description" content="">
		<title>搜索宁波 - So Ningbo</title>

		<link rel="stylesheet" type="text/css"
			href="${ctx}/css/global_test.css" />
		<link rel="stylesheet" id="cssStyle" type="text/css"
			href="${ctx}/css/genral_test.css" />

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

	<body>
		<div class="container">
			<header>
			<div id="header">
				<div class="logo">
					<span>English</span>
				</div>
			</div>
			</header>
			<div class="content">
				<section>
				<div class="section_st">
				</div>
				</section>
				<section>
				<div class="section_nd">


					<div class="red_arrow">
						<div class="red_arrow_cap"></div>
						<div class="red_arrow_bg">
							中等专科学校
						</div>
						<div class="red_arrow_head"></div>
					</div>

					<div class="red_arrow">
						<div class="red_arrow_cap"></div>
						<div class="red_arrow_bg">
							出入境检验检疫局
						</div>
						<div class="red_arrow_head"></div>
					</div>

					<div class="red_arrow">
						<div class="red_arrow_cap"></div>
						<div class="red_arrow_bg">
							浙江泰隆商业银行
						</div>
						<div class="red_arrow_head"></div>
					</div>

				</div>
				</section>
			</div>
			<footer>
			<div id="footer">
				<div class="copyright">
					<img src="${ctx}/images/nm_logo.png" alt="宁波商外文化传媒" />
					<br />
					© 2012 宁波商外文化传媒
				</div>
				<div class="message">
					客服电话：0574-87200625（个人） 0574-83860743（企业） （按当地市话标准计费）
					<br />
					<span>宁波ICP证000000号</span>
				</div>
				<div class="report">
					<a href="http://net.china.cn/" target="_blank"><img
							src="${ctx}/images/ciirc.png" alt="不良信息举报" /> <br />不良信息举报中心</a>
				</div>
			</div>
			</footer>
		</div>
	</body>
</html>
