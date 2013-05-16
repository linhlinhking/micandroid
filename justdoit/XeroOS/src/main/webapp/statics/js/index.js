
$(function() {
	
	
	$(".contact_us_li").live("click",function(event){
		event.preventDefault();
		$(".left_section h1").remove();
		$(".left_section .slider_area_bg").remove();
		$(".left_section").load("/contact-us");
	});
	
	$(".rem_icon").toggle(function() {
		$(this).addClass("not_rem");
		$("input[name='remember_me']").val(0);
	}, function() {
		$(this).removeClass("not_rem");
		$("input[name='remember_me']").val(1);
	});

	$(".signin_button").bind("click",function() {
		login();
	});
	
	$("#password_ie").keypress(function(e){
		if (e.which == 13){
			login();
		}
	});
	
	

	$(".cancel_button").click(function() {
		$("input[name='uemail']").val("");
		$("input[name='password']").val("");
	});
	
	$(".slider_point span").click(function(){
		$(".slider_point span").removeClass("selected_point");
		$(this).addClass("selected_point");
	});
	
	
	
	$("#for_fs").click(function(){
		$(".main_slider_box").css({
			'-moz-border-radius' : '0 2px 2px 0',
			'-webkit-border-radius' : '0 2px 2px 0',
			'-khtml-border-radius' : '0 2px 2px 0',
			'border-radius' : '0 2px 2px 0',
			'width' : '300px',
			'left' : '299px'
			});
		$(".second_status").css("opacity","0");
		$(".third_status").css("opacity","0");
		$(".first_status").css("opacity","1");
	});
	
	$("#for_ss").click(function(){
		$(".main_slider_box").css({
			'-moz-border-radius' : '2px 0 0 2px',
			'-webkit-border-radius' : '2px 0 0 2px',
			'-khtml-border-radius' : '2px 0 0 2px',
			'border-radius' : '2px 0 0 2px',
			'width' : '300px',
			'left' : '0px'
			});
		$(".first_status").css("opacity","0");
		$(".third_status").css("opacity","0");
		$(".second_status").css("opacity","1");
	});
	
   $("#for_ts").click(function(){
	   $(".main_slider_box").css({
			'-moz-border-radius' : '2px',
			'-webkit-border-radius' : '2px',
			'-khtml-border-radius' : '2px',
			'border-radius' : '2px',
			'width' : '599px',
			'left' : '0px'
			});
		$(".first_status").css("opacity","0");
		$(".second_status").css("opacity","0");
		$(".third_status").css("opacity","1");
   });

	var n = 2;
	setInterval(function(){
		
		point(n);
		loopImage(n);
		n ++;
		n = (n == 4) ? 1 : n;
	},8000);
	$("#for_ts, #for_ss, #for_fs").click(function(){
		var id = $(this).attr("id");
		switch(id){
			case "for_fs" : n = 2;break;
			case "for_ss" : n = 3;break;
			case "for_ts" : n = 1;break;
		}
	});
	function loopImage(n){
		$(".first_status, .second_status, .third_status").css("opacity","0");
		switch(n){
			case 3 : $(".main_slider_box").css("border-radius","2px 2px 2px 2px").css("width","599px").css("left","0px");$(".third_status").css("opacity","1");break;
			case 2 : $(".main_slider_box").css("border-radius","2px 0px 0px 2px").css("width","300px").css("left","0px");$(".second_status").css("opacity","1");break;
			case 1 : $(".main_slider_box").css("border-radius","0px 2px 2px 0px").css("width","300px").css("left","299px");$(".first_status").css("opacity","1");break;
		}
	}
	function point(n){
		$(".default_point").removeClass("selected_point");
		switch(n){
			case 3 : $("#for_ts").addClass("selected_point");break;
			case 2 : $("#for_ss").addClass("selected_point");break;
			case 1 : $("#for_fs").addClass("selected_point");break;
		}
	}
	
	
	function login(){
		var rem = $(".rem_ico").hasClass("not_rem") ? 0 : 1;
		$.ajax({
			type : 'POST',
			url : '/login',
			dataType : 'json',
			data : {
				'uemail' : $("input[name='uemail']").val(),
				'password' : $("input[name='password']").val(),
				'remember_me' : rem
			},
            error : function(e){
             console.log(e);	
            },
			success : function(res) {
				if (res) {
					window.top.location.href = "/contact";
				} else {
					$('.shake_box').animate({
						right : '-=10'
					}, 100, function() {
						$(this).animate({
							right : '+=20'
						}, 100, function() {
							$(this).animate({
								right : '-=15'
							}, 100, function() {
								$(this).animate({
									right : '+=10'
								}, 100, function() {
									$(this).animate({
										right : '-=5'
									}, 100);
								});
							});
						});
					});
				}
			}
		});
	
	}
	
	
	
	
	
	

})