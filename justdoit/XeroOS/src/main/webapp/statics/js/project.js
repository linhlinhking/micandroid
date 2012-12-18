$(function() {
	$(".plus_btn").click(function() {
		$(".add_sup_line").removeClass("for_plus");
		$(".add_sup_line").addClass("for_select");
	});
	$(".cross_btn").click(function() {
		$(".add_sup_line").removeClass("for_select");
		$(".add_sup_line").addClass("for_plus");
	});

	$(".sup_input, .lan_input").focus(function() {
		$(this).addClass("typing");
	});
	$(".sup_input, .lan_input").blur(function() {
		$(this).removeClass("typing");
	});

	$(".cus_field").click(function() {
		$(".selected_bg").css({
			'-webkit-transform' : 'rotate(-180deg)',
			'-moz-transform' : 'rotate(-180deg)',
			'-ms-transform' : 'rotate(-180deg)',
			'-o-transform' : 'rotate(-180deg)',
			'transform' : 'rotate(-180deg)'
		});
		$(".sup_field").removeClass("selected_field");
		$(".cus_field").addClass("selected_field");
	});

	$(".sup_field").click(function() {
		$(".selected_bg").css({
			'-webkit-transform' : 'rotate(0deg)',
			'-moz-transform' : 'rotate(0deg)',
			'-ms-transform' : 'rotate(0deg)',
			'-o-transform' : 'rotate(0deg)',
			'transform' : 'rotate(0deg)'
		});
		$(".cus_field").removeClass("selected_field");
		$(".sup_field").addClass("selected_field");
	});

	$(".ed_area").toggle(function() {
		$(".ed_area strong").css("background-position", "0 -8px");
	}, function() {
		$(".ed_area strong").css("background-position", "0 0");
	});
	$(".sd_area").toggle(function() {
		$(".sd_area strong").css("background-position", "0 -8px");
	}, function() {
		$(".sd_area strong").css("background-position", "0 0");
	});
	$(".pn_area").toggle(function() {
		$(".pn_area strong").css("background-position", "0 -8px");
	}, function() {
		$(".pn_area strong").css("background-position", "0 0");
	});
	$(".na_area").toggle(function() {
		$(".na_area strong").css("background-position", "0 -8px");
	}, function() {
		$(".na_area strong").css("background-position", "0 0");
	});

	$(".plus_field").click(function() {
		$(".mask_area").fadeIn('fast', function() {
			$(".add_cus_bg").fadeIn('fast');
		});
	});

	$(".mask_area, .cancel_button").click(function() {
		$(".add_cus_bg").fadeOut('fast', function() {
			$(".mask_area").fadeOut('fast');
		});
	});
	
	﻿$("#project_startDate").datepicker({
		changeMonth : true,
		changeYear : true
	});
	

	$("#project_endDate").datepicker({
		changeMonth : true,
		changeYear : true
	});﻿	
	
	$(".c_details_content li").die().live("click",function(){
		window.top.location.href="/project-detail" ;
	});

	$(".add_button").click(function() {
			$.ajax({
				url : '/project-add',
				type : 'POST',
				dataType : 'json',
				data : {
					"proName":$("#project_name").val(),
					"customerId":$("#project_customer").val(),
					"startDate":$("#project_startDate").val(),
					"startDate":$("#project_endDate").val(),
				},
				success : function(res) {
					if (res.result) {
						var tmp = res.data;
						// showAddProject(tmp.endDate,
						// tmp.startDate,tmp.customerId,tmp.id,tmp.projectName);
						$(".add_cus_bg").fadeOut('fast', function() {
							$(".mask_area").fadeOut('fast');
						});
					} else {
						alert("Connection TimeOut.");
					}
				}
			});
		});
});
