
var ua = navigator.userAgent.toLowerCase();
var isAndroid = ua.indexOf("android") > -1;
if(isAndroid) {
window.location = '/android.jsp';
}

if((navigator.userAgent.match(/iPhone/i)) || (navigator.userAgent.match(/iPod/i))) {
   if (document.cookie.indexOf("iphone_redirect=false") == -1) window.location = "/ios.jsp";
}

// PAGE LOAD
$(document).ready(function() {
	
	$('#email1_btn').click(function(){


		$('#email1').animate({
		'height':'150px'
  		}, 500, function() {
    	
  		});
		$('#email1_btn').fadeOut(250);
		$('#table1').show();
		
		
		$('#email2').animate({
		'height':'70px'
  		}, 500, function() {
    	
  		});
		$('#email2_btn').fadeIn(250);
		$('#table2').hide();
		
		
});


$('#email2_btn').click(function(){


		$('#email2').animate({
		'height':'150px'
  		}, 500, function() {
    	
  		});
		$('#email2_btn').fadeOut(250);
		$('#table2').show();
		
		
		$('#email1').animate({
		'height':'70px'
  		}, 500, function() {
    	
  		});
		$('#email1_btn').fadeIn(250);
		$('#table1').hide();
});



	
	var ikeep_me = 0;
	
	$('#keeptrying').click(function(){
	


if (ikeep_me==0){
	
		$('.keep_me_cover').animate({
		'margin-left':'33px'
  		}, 300, function() {
    	ikeep_me = 1;
  		});
	

}


if (ikeep_me==1){

		$('.keep_me_cover').animate({
		'margin-left':'0px'
  		}, 300, function() {
    	ikeep_me = 0;
  		});
	
}

});
	



	
$('.check_email').delay(2000).animate({
		'height':'50px'
  		}, 1200, function() {
    	//Completed Anim
  		});

	
	$('#table1, #table2, #webcam_image').hide();
	
//用户登陆
$('#sign_in_btn').click(function(){
	var email = $("#email").val();
	var password = $("#password").val();
	var key = "soningbo";
	var device_id = "z001";
	if(!email){
		alert("email can't be null");
		return ;
	}
	if(!password){
		alert("password can't be null");
		return ;
	}
	$.post(
		"/user/login",
		{username:email,password:password,key:key,device_id:device_id},
		function(data){
			if(data == 0){
				window.location = 'home.jsp';
			}else{
				alert("Email or password wrong!");
			}
		}
		
	
	);
});
	/*
	$('#sign_in_btn').click(function(){
	
		$.ajax({
           type: "get",
           url: "http://localhost:8080/user/show/34",
           crossDomain:true,
           dataType: "json",
           success: alert("success!")
		});
		
		var requestUrl ="http://localhost:8080/user/show/34";
        $.getJSON(requestUrl,
        function(data) {
           alert(data);
        });

	});
		
	*/
//用户注册
$('#reg_signup_btn').click(function(){
	var username = $("#username").val();
	var name_cn = $("#name_cn").val();
	var email = $("#remail").val();
	var password = $("#rpassword").val();
	var confirm_password = $("#confirm_password").val();
	var gender = document.getElementsByName("gender").value;
	var year = document.getElementById("year").value;
	var month = document.getElementById("month").value;
	var day = document.getElementById("day").value;
	var birthday;
	var key = "soningbo";
	
	if(!username){
		alert("nickname can't be null");
		return false;
	}
	if(!name_cn){
		alert("realname can't be null");
		return false;
	}
	if(!email){
		alert("email can't be null");
		return false;
	}
	if(!password){
		alert("password can't be null");
		return false;
	}
	if(!confirm_password){
		alert("confirm password can't be null");
		return false;
	}
	if(password != confirm_password){
		alert("password not equals confirm password");
		return false;
	}
	if(!year && !month && !day){
		alert("birthday can't be null");
		return false;
	}else{
		birthday = year + "-" + month + "-" + day;
		alert(birthday)
	}
	
	$.post(
		"/user/register",
		{username:username,name_cn:name_cn,email:email,password:password,gender:gender,key:key,birthday:birthday},
		function(data){
			if(data == 0){
				alert("Register Success!")
				window.location = 'step1.jsp';
			}else{
				alert("Register Error!");
			}
		}
	);

});

$('#step1_next_btn').click(function(){
	 window.location = 'step2.jsp';
	});	
	
$('#step2_back_btn').click(function(){
	 window.location = 'step1.jsp';
	});	
	
$('#step2_next_btn').click(function(){
	 window.location = 'step3.jsp';
	});	

$('#step3_back_btn').click(function(){
	 window.location = 'step2.jsp';
	});	
	
$('#step3_next_btn').click(function(){
	 window.location = 'home.jsp';
	});	

$('#forgot_password').click(function(){
	 window.location = 'forgot_password.jsp';
	});	

$('#index_btn').click(function(){
	 window.location = 'index.jsp';
	});	

$('#forgot_password_email_btn').click(function(){
	 window.location = 'forgot_password_email.jsp';
	});	





//$('#image_upload').fileinput("<div style='float:right; margin-top:40px; width:100px' class='btn' id='upload_image_btn'>‰∏ä‰º†ÁÖßÁâá</div>");


$('#webcam_image_btn').click(function(){
	 $('#webcam_image').show();
	 $('#upload_image').hide();
	});	

$('#upload_image_btn').click(function(){
	 $('#webcam_image').hide();
	 $('#upload_image').show();
	});	



});



// PAGE RE-LOAD
$(window).resize(function() {	

		
});



	
	
	
