
$.getJSON("/category/showcategory",{id:GetQueryString("id")},
   function(data) {
   	$("#section_st").html("" + data.category_id);
   	var json = eval(data.secondCategory);
   	var appendHtml = "<ul style='float:left;'>";
   	if(json.length != undefined){
		$.each(json, function (i, o) {
			appendHtml +="<li><a href='information.html?id="+o.id+"'>"+o.name_cn+"</a></li>";
			if((i+1) % 15 == 0){
				appendHtml += "</ul><ul style='float:left;'>"
			}
		});
   	}else{
   		appendHtml += "<li><a href='information.html?id="+data.secondCategory.id+"'>"+data.secondCategory.name_cn+"</a></li>";
   	}
	appendHtml += "</ul>";
	alert(appendHtml)
   	$("#section_nd").html(appendHtml);
  
   });

function GetQueryString(name) 
{ 
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r!=null){
		return unescape(r[2]); 
	}
	return null; 
} 