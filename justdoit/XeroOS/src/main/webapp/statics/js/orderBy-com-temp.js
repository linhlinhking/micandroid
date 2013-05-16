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
	$(".dad_area").toggle(function() {
		$(".dad_area strong").css("background-position", "0 -8px");
	}, function() {
		$(".dad_area strong").css("background-position", "0 0");
	});
	$(".lse_area").toggle(function() {
		$(".lse_area strong").css("background-position", "0 -8px");
	}, function() {
		$(".lse_area strong").css("background-position", "0 0");
	});
	$(".ema_area").toggle(function() {
		$(".ema_area strong").css("background-position", "0 -8px");
	}, function() {
		$(".ema_area strong").css("background-position", "0 0");
	});
	$(".nam_area").toggle(function() {
		$(".nam_area strong").css("background-position", "0 -8px");
	}, function() {
		$(".nam_area strong").css("background-position", "0 0");
	});

	$(".add_manage_btn").click(function() {
		$(".mask_area").fadeIn('fast', function() {
			$(".add_cus_bg").fadeIn('fast');
		});
	});

	$(".mask_area, .cancel_button").click(function() {
		$(".add_cus_bg").fadeOut('fast', function() {
			$(".mask_area").fadeOut('fast');
		});
	});

	$(".c_details_content li").click(function() {
		removeContainAttr();
		$(this).removeClass("li_defalut").addClass("li_selected");
	});

	var user_json;
	var search_date;
	$.ajax({
		url : '/user-list',
		dataType : 'json',
		success : function(e) {
			if (e.result) {
				user_json = e;
				search_date = e;
				showDate();
			}
			getTimeFormat(new Date(e.data[0].createDateTime));
		}
	});
	// 控制排序按钮只显示一个
	var showOrHide = function(show, hide1, hide2, hide3) {
		$(show).show();
		$(hide1).hide();
		$(hide2).hide();
		$(hide3).hide();
	}
	$(".sup_area").click(function() {
		showOrHide("#sup_area", "#dov_area", "#pna_area", "#pnu_area");
	});
	$(".dov_area").click(function() {
		showOrHide("#dov_area", "#sup_area", "#pna_area", "#pnu_area");
	});
	$(".pna_area").click(function() {
		showOrHide("#pna_area", "#dov_area", "#sup_area", "#pnu_area");
	});
	$(".pnu_area").click(function() {
		showOrHide("#pnu_area", "#dov_area", "#pna_area", "#sup_area");
	});
	$(".dad_area").click(function() {
		showOrHide("#dad_area", "#lse_area", "#ema_area", "#nam_area");
	});
	$(".lse_area").click(function() {
		showOrHide("#lse_area", "#dad_area", "#ema_area", "#nam_area");
	});
	$(".ema_area").click(function() {
		showOrHide("#ema_area", "#lse_area", "#dad_area", "#nam_area");
	});
	$(".nam_area").click(function() {
		showOrHide("#nam_area", "#lse_area", "#ema_area", "#dad_area");
	});
	// 显示json数据
	var type_page = $("#type_page").val();
	function showDate() {
		if (type_page == "reports") {
			for ( var i = 0; i < json.reports.length; i++) {
				$(".c_details_content").append(
						'<li class="li_defalut"><div class="info_area tel_info">'
								+ json.reports[i].telephone
								+ '</div><div class="info_area pnu_info">'
								+ json.reports[i].po_number
								+ '</div><div class="info_area pna_info">'
								+ json.reports[i].project_name
								+ '</div><div class="info_area dov_info">'
								+ json.reports[i].days_overdue
								+ '</div><div class="info_area sup_info">'
								+ json.reports[i].supplier + '</div></li>');
			}
		} else if (type_page == "user") {
			for ( var i = 0; i < user_json.data.length; i++) {

				var createDateTime = getTimeFormat(user_json.data[i].createDateTime);

				var updateDateTime = "--";
				if ("" != user_json.data[i].updateDateTime
						&& null != user_json.data[i].updateDateTime) {
					updateDateTime = getTimeFormat(user_json.data[i].updateDateTime);
				}

				$(".c_details_content").append(
						' <li class="li_defalut"><div class="info_area dad_info">'
								+ createDateTime
								+ '</div><div class="info_area lse_info">'
								+ updateDateTime
								+ '</div><div class="info_area ema_info">'
								+ user_json.data[i].uemail
								+ '</div><div class="info_area nam_info">'
								+ user_json.data[i].username + '</div></li>');
			}
		}
	}
	// 输出的时间格式
	function getTimeFormat(t) {
		var tm = new Date(t);
		var y = tm.getFullYear();
		var m = tm.getMonth() + 1;
		var d = tm.getDate();
		return m + "/" + d + "/" + y;
	}
	// 排序
	var f = false;// 是否对搜索结果排序
	var search_json = {
		'data' : []
	};
	var orderBy = function(v, w) {
		$(".c_details_content").empty();
		if (type_page == "reports" && !f) {
			if (w == 8) {
				json = (json.reports == 'undefined' || json.reports == undefined) ? json
						.sort(function(a, b) {
							return a[v] > b[v] ? 1 : -1;
						})
						: json.reports.sort(function(a, b) {
							return a[v] > b[v] ? 1 : -1;
						});
			} else {
				json = (json.reports == 'undefined' || json.reports == undefined) ? json
						.sort(function(a, b) {
							return a[v] < b[v] ? 1 : -1;
						})
						: json.reports.sort(function(a, b) {
							return a[v] > b[v] ? 1 : -1;
						});
			}
			for ( var i = 0; i < json.length; i++) {
				$(".c_details_content").append(
						'<li class="li_defalut"><div class="info_area tel_info">'
								+ json[i].telephone
								+ '</div><div class="info_area pnu_info">'
								+ json[i].po_number
								+ '</div><div class="info_area pna_info">'
								+ json[i].project_name
								+ '</div><div class="info_area dov_info">'
								+ json[i].days_overdue
								+ '</div><div class="info_area sup_info">'
								+ json[i].supplier + '</div></li>');
			}
		} else if (type_page == "user" && !f) {// 无搜索时排序
			if (w == 8) {
				user_json = (user_json.data == 'undefined' || user_json.data == undefined) ? user_json
						.sort(function(a, b) {
							return a[v] > b[v] ? 1 : -1;
						})
						: user_json.data.sort(function(a, b) {
							return a[v] > b[v] ? 1 : -1;
						});
			} else {
				user_json = (user_json.data == 'undefined' || user_json.data == undefined) ? user_json
						.sort(function(a, b) {
							return a[v] < b[v] ? 1 : -1;
						})
						: user_json.data.sort(function(a, b) {
							return a[v] > b[v] ? 1 : -1;
						});
			}
			// console.log(json);
			for ( var i = 0; i < user_json.length; i++) {
				var createDateTime = getTimeFormat(user_json[i].createDateTime);

				var updateDateTime = "--";
				if ("" != user_json[i].updateDateTime
						&& null != user_json[i].updateDateTime) {
					updateDateTime = getTimeFormat(user_json[i].updateDateTime);
				}

				$(".c_details_content").append(
						' <li class="li_defalut"><div class="info_area dad_info">'
								+ createDateTime
								+ '</div><div class="info_area lse_info">'
								+ updateDateTime
								+ '</div><div class="info_area ema_info">'
								+ user_json[i].uemail
								+ '</div><div class="info_area nam_info">'
								+ user_json[i].username + '</div></li>');
			}
		} else if (type_page == "user" && f) {// 搜索时排序
			if (w == 8) {
				search_json = (search_json.data == 'undefined' || search_json.data == undefined) ? search_json
						.sort(function(a, b) {
							return a[v] < b[v] ? 1 : -1;
						})
						: search_json.data.sort(function(a, b) {
							return a[v] > b[v] ? 1 : -1;
						});
			} else {
				search_json = (search_json.data == 'undefined' || search_json.data == undefined) ? search_json
						.sort(function(a, b) {
							return a[v] > b[v] ? 1 : -1;
						})
						: search_json.data.sort(function(a, b) {
							return a[v] > b[v] ? 1 : -1;
						});
			}
			for ( var i = 0; i < search_json.length; i++) {
				var createDateTime = getTimeFormat(search_json[i].createDateTime);
				var updateDateTime = getTimeFormat(search_json[i].updateDateTime);
				$(".c_details_content").append(
						' <li class="li_defalut"><div class="info_area dad_info">'
								+ createDateTime
								+ '</div><div class="info_area lse_info">'
								+ updateDateTime
								+ '</div><div class="info_area ema_info">'
								+ search_json[i].uemail
								+ '</div><div class="info_area nam_info">'
								+ search_json[i].username + '</div></li>');
			}
		}

	}
	$("#sup_area, .sup_area").click(function() {
		var w = $("#sup_area").css("background-position").length;
		orderBy("supplier", w);
	});
	$("#dov_area, .dov_area").click(function() {
		var w = $("#dov_area").css("background-position").length;
		orderBy("days_overdue", w);
	});
	$("#pna_area, .pna_area").click(function() {
		var w = $("#pna_area").css("background-position").length;
		orderBy("project_name", w);
	});
	$("#pnu_area, .pnu_area").click(function() {
		var w = $("#pnu_area").css("background-position").length;
		orderBy("po_number", w);
	});
	// userPage
	$("#nam_area, .nam_area").click(function() {
		var w = $("#nam_area").css("background-position").length;
		orderBy("username", w);
	});
	$("#ema_area, .ema_area").click(function() {
		var w = $("#ema_area").css("background-position").length;
		orderBy("uemail", w);
	});
	$("#lse_area, .lse_area").click(function() {
		var w = $("#lse_area").css("background-position").length;
		orderBy("updateDateTime", w);
	});
	$("#dad_area, .dad_area").click(function() {
		var w = $("#dad_area").css("background-position").length;
		orderBy("createDateTime", w);
	});
	// 搜索
	$("#search")
			.keyup(
					function(e) {
						var keyNum = e.which || e.keyCode;
						var search = $("#search").val();
						var search_res = '';
						search_json = {
							'data' : []
						};
						for ( var i = 0; i < search_date.data.length; i++) {
							var name = search_date.data[i].username
									.toLowerCase();
							if (name.indexOf(search.toLowerCase()) >= 0) {
								var temp = {
									'createDateTime' : search_date.data[i].createDateTime,
									'updateDateTime' : search_date.data[i].updateDateTime,
									'uemail' : search_date.data[i].uemail,
									'username' : search_date.data[i].username
								};

								search_json.data.push(temp);

								var createDateTime = getTimeFormat(search_date.data[i].createDateTime);

								var updateDateTime = "--";
								if (null != search_date.data[i].updateDateTime
										&& "" != search_date.data[i].updateDateTime) {
									updateDateTime = getTimeFormat(search_date.data[i].updateDateTime);
								}

								search_res += ' <li class="li_defalut"><div class="info_area dad_info">'
										+ createDateTime
										+ '</div><div class="info_area lse_info">'
										+ updateDateTime
										+ '</div><div class="info_area ema_info">'
										+ search_date.data[i].uemail
										+ '</div><div class="info_area nam_info">'
										+ search_date.data[i].username
										+ '</div></li>';
							}
						}
						f = true;
						$(".c_details_content").empty();
						$(".c_details_content").append(search_res);

						if (search == '' || search == null) {
							search_res = '';
							$(".c_details_content").empty();
							for ( var i = 0; i < search_date.data.length; i++) {
								var createDateTime = getTimeFormat(search_date.data[i].createDateTime);
								
								var updateDateTime = "--";
								if (null != search_date.data[i].updateDateTime
										&& "" != search_date.data[i].updateDateTime) {
									updateDateTime = getTimeFormat(search_date.data[i].updateDateTime);
								}
								
								search_res += ' <li class="li_defalut"><div class="info_area dad_info">'
										+ createDateTime
										+ '</div><div class="info_area lse_info">'
										+ updateDateTime
										+ '</div><div class="info_area ema_info">'
										+ search_date.data[i].uemail
										+ '</div><div class="info_area nam_info">'
										+ search_date.data[i].username
										+ '</div></li>';
							}
							f = false;
							$(".c_details_content").empty();
							$(".c_details_content").append(search_res);
						}

					});

});

function removeContainAttr() {
	$(".c_details_content li").each(function() {
		if ($(this).hasClass("li_selected")) {
			$(this).removeClass("li_selected").addClass("li_defalut");
		}
	});
}
