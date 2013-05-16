$(function() {

	var  loginUserId = $("input[name='_current_login_userId']").val();
	
	$("#add_manage_btn").click(function() {
		$("input[name='_current_userId']").val("");
		$(".add_manage_title span").text("Add a new user");
		$(".add_button span").text("ADD USER");
		$(".del_button").css("display","none");
		$(".add_button").css("right","172px");

		$("input[name='name']").val("");
		$("input[name='uemail']").val("");
		$("input[name='password']").val("");

		$(".mask_area").fadeIn('fast', function() {
			$(".add_cus_bg").fadeIn('fast');
		});
	});

	$(".mask_area, .cancel_button, .del_button").click(function() {
		$(".add_cus_bg").fadeOut('fast', function() {
			$(".mask_area").fadeOut('fast');
		});
	});

	$(".plus_field").click(function() {
		$(".mask_area").fadeIn('fast', function() {
			$(".add_cus_bg").fadeIn('fast');
		});
	});
	$(".mask_area, .cancel_button").click(function() {
		$(".add_cus_bg").fadeOut('fast', function() {
			$(".mask_area").fadeOut('fast');
			$(".c_details_content li").removeClass("li_selected").addClass("li_defalut");
		});
	});

	$(".add_button").click(function() {
		var name = $("input[name='name']").val();
		var uemail = $("input[name='uemail']").val();
		var password = $("input[name='password']").val()
		if(name !='' && uemail != '' &&password != ''){
			$.ajax({
			url : '/user-add?random=' + (+new Date()),
				type : 'POST',
				dataType : 'json',
				data : {
					"name" : name,
					"uemail" : uemail,
					"password" : password,
					"userId" : $("input[name='_current_userId']").val(),
					"expiredDate" : $("input[name='expiredDate']").val(),
						"planId" : $("input[name='planId']").val(),
				"companyId" : $("input[name='companyId']").val()
				},
				success : function(res) {
					if (res.result) {
						window.top.location.href = "/user";
					} else {
						alert("Connection Timeout.");
					}
				}
			});
		}
		
	});

	$(".c_details_content li").die().live('click', function() {
		removeContainAttr();
		$(this).removeClass("li_defalut").addClass("li_selected");

		var _current_userId = this.id;
		
		if(loginUserId == _current_userId){
			$(".del_button").css("display","none");
			$(".add_button").css("right","172px");
		}else{
			$(".del_button").css("display","block");
			$(".add_button").css("right","329px");
			
		}
		
		$("input[name='_current_userId']").val(_current_userId);
		$(".add_manage_title span").text("Edit a user");
		$(".add_button span").text("EDIT USER");
		var umail = $(this).find('.ema_info').text();
		var uname = $(this).find('.nam_info').text();

		$("input[name='name']").val(uname);
		$("input[name='uemail']").val(umail);
		$("input[name='password']").val("");

		$(".mask_area").fadeIn('fast', function() {
			$(".add_cus_bg").fadeIn('fast');
		});
	});

	$(".del_button").click(function() {
		$.ajax({
			url : '/user-delete?random=' + (new Date()),
			type : 'POST',
			dataType : 'json',
			data : {
				"userId" : $("input[name='_current_userId']").val(),
			},
			success : function(res) {
				if(res.result){
					window.top.location.href = res.url ;
				}else{
					alert("Connection Timeout");
				}
			}
		});
	});

});

function removeContainAttr() {
	$(".c_details_content li").each(function() {
		if ($(this).hasClass("li_selected")) {
			$(this).removeClass("li_selected").addClass("li_defalut");
		}
	});
}

function editUser() {

}