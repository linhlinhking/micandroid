<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>使用Freemarker渲染2</title>
</head>
<body>
你好， ${userName!} ！栏目id为：${categroyId}

遍历二级栏目：<br/>
<#list secondCategorys as being>
	${being.id} <br/>
	${being.name_cn}<br/>
	${being.name_en}<br/>
</#list>
</body>
</html>