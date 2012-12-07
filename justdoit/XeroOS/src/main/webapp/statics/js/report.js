$(function() {

	$(".pnu_area").toggle(function() {
		$(".pnu_area strong").css("background-position", "0 -8px");
	}, function() {
		$(".pnu_area strong").css("background-position", "0 0");
	});
	$(".pna_area").toggle(function() {
		$(".pna_area strong").css("background-position", "0 -8px");
	}, function() {
		$(".pna_area strong").css("background-position", "0 0");
	});
	$(".dov_area").toggle(function() {
		$(".dov_area strong").css("background-position", "0 -8px");
	}, function() {
		$(".dov_area strong").css("background-position", "0 0");
	});
	$(".sup_area").toggle(function() {
		$(".sup_area strong").css("background-position", "0 -8px");
	}, function() {
		$(".sup_area strong").css("background-position", "0 0");
	});
});