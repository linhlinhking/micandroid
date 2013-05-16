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

	$(".report_content .li_defalut").die().live("click", function() {
		var tel = $(this).find(".tel_info").text();
		var email = $(this).find(".sup_email_txt").text();

		$(".email_box").attr("href", "mailto:" + email);
		$(".skype_box").attr("href", "callto:" + tel);

		$(".mask_area").fadeIn('fast', function() {
			$(".add_cus_bg").fadeIn('fast');
		});
	});

	$(".mask_area, .cancel_button").click(function() {
		$(".add_cus_bg").fadeOut('fast', function() {
			$(".mask_area").fadeOut('fast');
		});
	});

	/** debug Email.Just for develop model. */
	$("#email_debug").click(function() {
		//$.getJSON("/email-debug",responseDebugEmail);
	});

	loadingReports();

	var sort_data;
	var flage = false;
	var search_result = {
		"data" : []
	};
	$(".sup_area, .dov_area, .pna_area, .pnu_area").click(
			function() {
				var className = $(this).attr("class");
				showOrHide(className);
				var w = $("." + className + " strong").css(
						"background-position").length;
				var d = !flage ? sort_data : search_result;
				switch (className) {
				case "sup_area":
					orderBy("supplier", w, d);
					break;
				case "dov_area":
					orderBy("daysOverdue", w, d);
					break;
				case "pna_area":
					orderBy("projectName", w, d);
					break;
				case "pnu_area":
					orderBy("poNumber", w, d);
					break;
				}
			});

	$("#search").keyup(function() {
		var search_value = $("#search").val();
		_search(search_value);
		$(".c_details_content").empty();
		for ( var i = 0, j = search_result.data.length; i < j; i++) {
			var temp = search_result.data[i];
			var t = temp.telephone;
			var po = temp.poNumber;
			var pn = temp.projectName;
			var d = temp.daysOverdue;
			var s = temp.supplier;
			var email = temp.supplierEmail;
			fillData(t, po, pn, d, s, email);
		}
		flage = true;
	});

	function _search(search_value) {
		search_result.data.length = 0;
		for ( var i = 0, j = sort_data.data.length; i < j; i++) {
			var temp = sort_data.data[i].supplier;
			if (temp.substring(0, search_value.length).toLowerCase().indexOf(
					search_value.toLowerCase()) > -1) {
				search_result.data.push(sort_data.data[i]);
			}
		}
	}

	function showOrHide(show) {
		$(
				".sup_area strong, .dov_area strong, .pna_area strong, .pnu_area strong")
				.hide();
		$("." + show + " strong").show();
	}

	function orderBy(p, w, d) {
		d.data = (w == 8) ? d.data.sort(function(a, b) {
			return a[p] > b[p] ? 1 : -1;
		}) : d.data.sort(function(a, b) {
			return a[p] < b[p] ? 1 : -1;
		});
		var reports = d.data;
		$(".c_details_content").empty();
		for ( var i = 0, j = reports.length; i < j; i++) {
			var temp = reports[i];
			var t = temp.telephone;
			var po = temp.poNumber;
			var pn = temp.projectName;
			var d = temp.daysOverdue;
			var s = temp.supplier;
			var email = temp.supplierEmail;
			fillData(t, po, pn, d, s, email);
		}
	}

	function loadingReports() {
		var companyId = $("input[name='current_company_id']").val();
		var params = {
			"companyId" : companyId
		};
		getAjax("/report-list", params, reponseReports);
	}

	/**
	 * @description:Ajax Get request.
	 * @param url
	 * @param params
	 * @param callback
	 * 
	 */
	function getAjax(url, params, callback) {
		$.ajax({
			url : url,
			type : 'GET',
			dataType : 'json',
			data : params,
			success : callback
		});
	}

	function responseDebugEmail(res) {
		if (res.resut) {
			alert("Send Email OK.");
		} else {
			alert(res.message);
		}
	}

	/**
	 * @description:Deal with the callback from server.
	 * @param res
	 *            the response of server.
	 */
	function reponseReports(res) {
		if (res.result) {
			sort_data = res;
			var reports = res.data;
			if (reports.length > 0) {
				for ( var i = 0, j = reports.length; i < j; i++) {
					var temp = reports[i];
					var t = temp.telephone;
					var po = temp.poNumber;
					var pn = temp.projectName;
					var d = temp.daysOverdue;
					var s = temp.supplier;
					var email = temp.supplierEmail;

					fillData(t, po, pn, d, s, email);
				}
			} else {
				// No Project Reports.Everything is fine.
			}
		} else {
			// maybe ERROR.
		}
	}

	/**
	 * @description:Fill the data to front.
	 */
	function fillData(tel, poNumber, projectName, delayDays, supplierName,
			supplierEmail) {
		$(".c_details_content")
				.append(
						"<li class='li_defalut'><div class='info_area tel_info'>"
								+ tel
								+ "</div><div class='info_area pnu_info'>"
								+ poNumber
								+ "</div><div class='info_area pna_info'>"
								+ projectName
								+ "</div><div class='info_area dov_info'>"
								+ delayDays
								+ "</div><div class='info_area sup_info'>"
								+ supplierName
								+ "</div><div class='sup_email_txt' style='display:none'>"
								+ supplierEmail + "</div></li>");
	}

});