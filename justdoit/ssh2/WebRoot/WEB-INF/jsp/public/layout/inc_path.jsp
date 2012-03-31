<%@ page contentType="text/html;charset=UTF-8"%>

<%  
	String path = request.getContextPath();  
	String basePath = request.getScheme()+"://"+request.getServerName()+
		(request.getServerPort() == 80 ? "" : (":"+request.getServerPort())) +path+"/";  
%>

<script type="text/javascript">
	var base_path = "<%=basePath%>";
</script>

<base href="<%=basePath%>">