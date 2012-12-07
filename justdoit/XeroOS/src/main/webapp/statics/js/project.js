$(function() {
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
});