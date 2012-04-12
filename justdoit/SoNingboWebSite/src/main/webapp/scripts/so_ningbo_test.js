// PAGE LOAD

function regInfo(lang){
	var username = $("#name").val();
	var email = $("#email").val();
	if(!username){
		alert("username can't be null");
		return false;
	}
	if(!email){
		alert("email can't be null");
		return false;
	}
	$.post(
		"/userBeta/reg",
		{username:username,email:email},
		function(data){
			if(funTrim(data) == "success"){
				$("#regInfo").removeClass("submit_information_before").addClass("submit_information");
				if(lang == "cn"){
					var appendHtml="<div class='information_area'><span>感谢您提交的信息</span></div><img src='images/success.gif' alt='提交成功'><p>我们将在网站正式开通时邮件通知您</p>";
				}else{
					var appendHtml="<div class='information_area'><span>thanks for your information</span></div><img src='images/success.gif' alt='submit'><p>we will email you when our website<br/>is launched</p>";
				}
				$("#regInfo").html(appendHtml);
			}
		}
	);
	
}

$(function(){
	$.get("/location/getlocnum",
			function(data){
				data = funTrim(data);
				var numpic = "<div class='total'></div>";
				for(var i = 0; i < data.length; i++){
					switch(data[i]*1){
					case 1:
						numpic += "<div class='total one'></div>";
						break;
					case 2:
						numpic += "<div class='total two'></div>";
						break;
					case 3:
						numpic += "<div class='total three'></div>";
						break;
					case 4:
						numpic += "<div class='total four'></div>";
						break;
					case 5:
						numpic += "<div class='total five'></div>";
						break;
					case 6:
						numpic += "<div class='total six'></div>";
						break;
					case 7:
						numpic += "<div class='total seven'></div>";
						break;
					case 8:
						numpic += "<div class='total eight'></div>";
						break;
					case 9:
						numpic += "<div class='total nine'></div>";
						break;
					case 0:
						numpic += "<div class='total'></div>";
						break;
					default:
						alert("Oh, My God!");
						break;
					}
				}
				$("#loc_num").html(numpic);
			}
		);
	
	
	var requestUrl ="/category/getCategory1";
    $.getJSON(requestUrl,
    function(data) {
    	data = eval(data.firstCategory)
    	var appendHtml = "";
		$.each(data, function (i, o) {
			appendHtml +="<span><a href='information.jsp?id="+o.id+"'>"+o.name_cn+"</a></span>";
		});
    	$("#link_page").html(appendHtml);
   
    });
    
});



function funTrim(_str)
{ 
	return _str.replace(/(^\s*)|(\s*$)/g, ""); 
} 


