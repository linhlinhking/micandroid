<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Email</title>
</head>

<body>
  <div><a style="display:inline-block;vertical-align:bottom;color:#727272;font-size:11px;text-decoration:none;" href="/"><img style="width:117px;height:56px;border:0 none;" src="http://dev.globaldesign.co.nz/images/gdp_logo.png" alt="GDP_logo"></a><span style="display:inline-block;vertical-align:bottom;color:#727272;font-size:11px;font-family:Arial, Helvetica, sans-serif;">Global Design &amp; Production</span></div>
  <h2 style="font-size:13px;font-family:Arial, Helvetica, sans-serif;color:#88b440;margin:15px 0 10px;">${projectName}</h2>
  
  <#list projectNotes as projectNote>
  <div style="font-size:12px;font-family:Arial, Helvetica, sans-serif;color:#666;font-weight:bold;margin:0 0 10px;text-indent:5px;">${projectNote.landmarkDate}</div>
  <div style="background-color:#fff;-moz-border-radius: 5px;-webkit-border-radius: 5px;-khtml-border-radius: 5px;border-radius: 5px;padding:5px;border:1px dashed #afafaf;width:750px;margin:0 0 10px;">
    <p style="font-size:12px;font-family:Arial, Helvetica, sans-serif;color:#666;font-weight:bold;margin:0 0 10px;">${projectNote.creator}</p>
    <p style="font-size:12px;font-family:Arial, Helvetica, sans-serif;color:#999;margin:0;">"${projectNote.content}"</p>
  </div>
  </#list>
 
</body>
</html>