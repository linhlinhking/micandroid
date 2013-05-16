$(function(){
	$("#register_companyName, #register_email, #password_ie").focusout(function(){
		var id = $(this).attr("id");
		
		if($(this).val() == ''){
			switch(id){
				case "register_companyName" : $(".check_com").show();break;
				case "register_email" : $(".check_email").show(); break;
				case "password_ie" : $(".check_pawo").show();break;
			}
		}else{
			switch(id){
				case "register_companyName" : $(".check_com").hide();break;
				case "register_email" : $(".check_email").hide(); break;
				case "password_ie" : ($(this).val().length<6||$(this).val().length>12)?$(".check_pawo").show():$(".check_pawo").hide();break;
			}
		}
		
		
	});
	
	$(".signup_button").click(function(){
		var register_companyName = $("#register_companyName").val();
		var register_email = $("#register_email").val();
		var password_ie = $("#password_ie").val();
		
		register_companyName == '' ? $(".check_com").show() : $(".check_com").hide();
		register_email == '' ? $(".check_email").show() : $(".check_email").hide();
        (password_ie == '') ? $(".check_pawo").show() : (password_ie.length>=6 && password_ie.length<=12)?$(".check_pawo").hide():$(".check_pawo").show();
		
		$("#icon_span").hasClass("not_accept") ? $(".check_term").show() : $(".check_term").hide();
		
	});
})