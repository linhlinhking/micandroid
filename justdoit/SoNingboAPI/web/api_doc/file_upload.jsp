<%@ page language="java" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>Upload file</title>
  </head>
  
  <body>
   <form action="${pageContext.request.contextPath}/resource/modulefile/upload" enctype="multipart/form-data" method="post">
	<input type="hidden" name="key" value="soningbo">
	<input type="hidden" name="userId" value="1">
	<input type="hidden" name="toolId" value="1">
	<input type="hidden" name="typeId" value="1">
	
	
	<input type="file" name="file">
	
	<input type="submit" name="upload">
	</form>
   
   
  </body>
</html>
