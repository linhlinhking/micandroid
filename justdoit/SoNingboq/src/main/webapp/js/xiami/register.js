function checkmail() {	
	var input = $("#email_reg");
	if (input.val() != null && input.val().length > 4 && input.val().match(/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/)){
	$.ajax({
		type: "POST",
		url: "/ajax/querymail",
		data: "mail="+ encodeURIComponent(input.val()),
		success: function(msg){
			if(msg != '1'){
				$("#email_info").attr("class","hint warning");
				$("#email_info").html('该Email地址已被使用，如果你是所有者，可 <a href="/member/login">直接登陆</a> 或 <a href="/member/getpassword">找回密码</a>');				
				return false;
			}else{
				$("#email_info").attr("class","hint ok");
				$("#email_info").html("正确");
			}
		}
	});
}else{
	$("#email_info").attr("class","hint warning");
	$("#email_info").html("请输入有效的Email地址");
	return false;
}
return true;
}

function checkname(){
	var input = $("#nickname_reg");	
	if(input.val().length <1 || input.val().length >12){		
		$("#name_info").attr("class","hint warning");
		$("#name_info").html("昵称使用1-12个字符");
		return false;
	}else{
		$("#name_info").attr("class","hint ok");
		$("#name_info").html("正确");
	}
	return true;
}

function checkagree(){
	var input = $("#agreement");		
	if(!input.attr("checked")){		
		$("#agreement_info").attr("class","hint warning");
		$("#agreement_info").html("请阅读和同意协议");
		return false;	
	}else{
		$("#agreement_info").attr("class","hint");
		$("#agreement_info").html("");
	}
	return true;
}

function checkreg(){	
	var e = $('#emailisset').val();
	if(e==1){	//绑定只检查密码		
		if(checkPasswords()) {
			return true;
		}else{
			return false;
		}
	}
	if(checkmail() && checkname() && checkPasswords() && checkagree() && ($("#email_info").attr("class") == "hint ok")){
		$("#reg_submit").attr("disabled","disabled");
		$("#submiting").html("<img src=\"http://img.xiami.com/res/img/default/loading2.gif\" width=\"16\" height=\"16\" alt=\"数据加载中...\" />");
		return true;
	}else{
		return false;
	}		
}

function checkPasswords(){
	var input1 = $('#password_reg');
	if (input1.val().length < 6 || input1.val().length > 16){
		$("#password_info").attr("class","hint warning");
		$("#password_info").html("密码请使用6-16个字符");
		return false;
	}else{
		$("#password_info").attr("class","hint");
		$("#password_info").html("");		
	}
//	var input2 = $('#verifypass');
//	if (input2.val().length > 0 ) {
//		if (input1.val() != input2.val()) {
//			$("#password_info").attr("class","hint warning");
//			$("#password_info").html("两次密码输入不相同");
//			return false;
//		}else{
//			$("#password_info").attr("class","hint ok");
//			$("#password_info").html("正确");
//		}
//		
//	}
	return true;
}

function checkValidate(){
	var v = $("#validate").val();
	if(v.match(/\S/)==null){
		
	}
}

function checklocation(){
   var v = $("select[name='province']").val();
   if(v){
       $('#province_info').hide();   
   }else{
	   $("#province_info").show();
       return false;
   }   
   return true;
}

function checkgender(){
    var v = $('#gender').find('input[name=gender]:checked').val();
    if(v!='M' && v!='F'){
        $('#gender_info').show();
        return false;
    }else{
    	$('#gender_info').hide();
    }
    return true;    
}



/*
中文判断函数，允许生僻字用英文“*”代替
返回true表示是符合条件，返回false表示不符合
*/
function isChinese(str){
	var badChar ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	badChar += "abcdefghijklmnopqrstuvwxyz";
	badChar += "0123456789";
	badChar += " "+"　";//半角与全角空格
	badChar += "`~!@#$%^&()-_=+]\\|:;\"\\'<,>?/";//不包含*或.的英文符号
	if(""==str){
		return false;
	}
	for(var i=0;i<badChar.length;i++)//字符串str中的字符
	{
		var c = str.charAt(badChar[i]);
		if(badChar.indexOf(c) > -1){
			return false;
		}
	}
	return true;
}


