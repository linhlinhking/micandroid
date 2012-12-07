$(function() {
	$(".rem_icon").toggle(function() {
		$(this).addClass("not_rem");
		$("input[name='remember_me']").val(0);
	}, function() {
		$(this).removeClass("not_rem");
		$("input[name='remember_me']").val(1);
	});

	$(".signin_button").click(function() {
		$.ajax({
			type : 'POST',
			url : '/login',
			dataType : 'json',
			data : {
				'uemail' : $("input[name='uemail']").val(),
				'password' : $("input[name='password']").val(),
				'remember_me' : $("input[name='remember_me']").val()
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
	});

	$(".cancel_button").click(function() {
		$("input[name='uemail']").val("");
		$("input[name='password']").val("");
	});

})