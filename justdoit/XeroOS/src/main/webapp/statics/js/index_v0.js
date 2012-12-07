$(function() {
	$(".rem_icon").click(function() {
		if ($("#rem_check").is(':checked')) {
			$("input[name='remember_me']").val(1);
		} else {
			$("input[name='remember_me']").val(0);
		}
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
					window.top.location.href = "/register" ;
				}else{
					$('.shake_box').animate({
						left : '-=10'
					}, 100, function() {
						$(this).animate({
							left : '+=20'
						}, 100, function() {
							$(this).animate({
								left : '-=15'
							}, 100, function() {
								$(this).animate({
									left : '+=10'
								}, 100, function() {
									$(this).animate({
										left : '-=5'
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

	});

	$("#login_form").bind('submit', function(e) {
		e.preventDefault();
		var v = $("input[name='login_statue']").val();
		if (null != v && "" != v && v == '1') {
			$(".signin_button").click(function() {
				$('.shake_box').animate({
					left : '-=10'
				}, 100, function() {
					$(this).animate({
						left : '+=20'
					}, 100, function() {
						$(this).animate({
							left : '-=15'
						}, 100, function() {
							$(this).animate({
								left : '+=10'
							}, 100, function() {
								$(this).animate({
									left : '-=5'
								}, 100);
							});
						});
					});
				});
			});
		} else {
			$("#login_form")[0].submit();
		}
	});

})