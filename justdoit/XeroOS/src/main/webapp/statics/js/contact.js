var currentCustomers = [];
var currentSuppliers = [];
var currentAllContacts = [];
var xeroStorage = new XeroStorage();

$(function() {
	var currentUserId = $("input[name='userId']").val();
	
	$(".add_button").click(function() {
		var Name = $("input[name='companyName']").val();
		var EmailAddress = $("input[name='uemail']").val();
		var DefaultNumber = $("input[name='telephone']").val() ;
		var groupId = $("input[name='groupId']").val();
		var isXero = $("input[name='isLinkXero']").val() ;
		
		$.ajax({
			url : '/contact-add?random=' + (new Date()),
			type : 'POST',
			dataType : 'json',
			data : {
				"Name" : Name,
				"EmailAddress" : EmailAddress,
				"DefaultNumber" : DefaultNumber,
				"userId" : currentUserId,
				"groupId" : groupId,
				"isXero" : isXero
			},
			success : function(res) {
				if (res.result) {
					clearContact() ;
					$(".add_cus_bg").fadeOut('fast', function() {
						$(".mask_area").fadeOut('fast');
					});
					window.top.location.href = "/contact";
				} else {
					alert("Connection TimeOut.");
				}
			}
		});
	});

	$(".all_field").click(function() {
		$(".sup_field").removeClass("selected_field");
		$(".cus_field").removeClass("selected_field");
		$(".all_field").addClass("selected_field");
		// getContactsByItem("all","","/contact-list",currentUserId);
		getContactsByType('All');
	});

	$(".cus_field").click(function() {
		$(".all_field").removeClass("selected_field");
		$(".sup_field").removeClass("selected_field");
		$(".cus_field").addClass("selected_field");
		$("input[name='groupId']").val(1);
		// getContactsByItem("customer", "1", "/contact-list", currentUserId);
		getContactsByType('Customer');
	});

	$(".sup_field").click(function() {
		$(".all_field").removeClass("selected_field");
		$(".cus_field").removeClass("selected_field");
		$(".sup_field").addClass("selected_field");
		$("input[name='groupId']").val(2);
		// getContactsByItem("supplier", "2", "/contact-list", currentUserId);
		getContactsByType('Supplier');
	});

	$(".n_area").toggle(function() {
		$(".n_area strong").css("background-position", "0 -8px");
		$(".c_details_content li .n_info").sorted();
	}, function() {
		$(".n_area strong").css("background-position", "0 0");
		$(".c_details_content li .n_info").sorted({
			reversed : true
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
		});
	});

	// getContactsByItem("all","","/contact-list",currentUserId);
	getContactsByXero("/contact-xero");

});

function getContactsByXero(url) {
	$.ajax({
		url : url,
		type : 'GET',
		dataType : 'json',
		success : function(res) {
			if (null == res) {
				window.top.location.href = "/oauth/xero";
			} else {
				var jsonTxt = JSON.stringify(res);
				// $("#jsonResult").val(jsonTxt);
				if (res.Status == 'OK') {
					var lists = res.Contacts;
					var jsonList = JSON.stringify(lists);

					xeroStorage.set("currentAllContacts", jsonList);
					for ( var i = 0, j = lists.length; i < j; i++) {
						var item = lists[i];
						var itemTxt = JSON.stringify(item);

						var n = item.Name;
						var e = item.EmailAddress;
						var t = item.Phones[1].PhoneNumber;
						var _e = (e == "") ? '--' : e;
						var _t = (t == "") ? '--' : t;

						if (item.IsCustomer) {
							currentCustomers.push(itemTxt);
						}
						if (item.IsSupplier) {
							currentSuppliers.push(itemTxt);
						}
						showAddContact(_t, _e, n);
					}
					xeroStorage.set("currentCustomers", currentCustomers);
					xeroStorage.set("currentSuppliers", currentSuppliers);
				} else {
					window.top.location.href = "/oauth/xero";
				}
			}
		}
	});
}

function getContactsByType(type) {
	// var jsonRes = $("#jsonResult").val();
	// if (null == jsonRes || "" == jsonRes || jsonRes == 'undefined') {
	// window.top.location.href = "/oauth/xero";
	// return;
	// }
	var obj = xeroStorage.get("currentAllContacts");
	var myData = JSON.parse(obj);
	if (myData != "" && myData != null) {
		$(".c_details_content li").remove();
		switch (type) {
		case 'All':
			// getDataByAll(lists);
			getDataByType(myData);
			break;
		case 'Supplier':
			// getDataBySupplier(lists);
			var obj = xeroStorage.get("currentSuppliers");
			var tempObj = "[" + obj + "]";
			var tempJson = JSON.parse(tempObj);
			getDataByType(tempJson);
			break;
		case 'Customer':
			// getDataByCustomer(lists);
			var obj = xeroStorage.get("currentCustomers");
			var tempObj = "[" + obj + "]";
			var tempJson = JSON.parse(tempObj);
			getDataByType(tempJson);
			break;
		}

	} else {
		alert("Connection Timeout.");
	}
}

function getDataByType(lists) {
	if (null == lists || "" == lists) {
		return;
	}
	for ( var i = 0, j = lists.length; i < j; i++) {
		var item = lists[i];
		var n = item.Name;
		var e = item.EmailAddress;
		var t = item.Phones[1].PhoneNumber;
		var _e = (e == "") ? '--' : e;
		var _t = (t == "") ? '--' : t;

		showAddContact(_t, _e, n);

	}
}

function getDataBySupplier(lists) {
	var obj = xeroStorage.get("currentSuppliers");
	var tempObj = "[" + obj + "]";
	var tempJson = JSON.parse(tempObj);
	console.log(tempJson);

	for ( var i = 0, j = lists.length; i < j; i++) {
		var item = lists[i];
		var n = item.Name;
		var e = item.EmailAddress;
		var t = item.Phones[1].PhoneNumber;
		var _e = (e == "") ? '--' : e;
		var _t = (t == "") ? '--' : t;
		if (item.IsSupplier) {
			showAddContact(_t, _e, n);
		}
	}
}

function getDataByCustomer(lists) {

	for ( var i = 0, j = lists.length; i < j; i++) {
		var item = lists[i];
		var n = item.Name;
		var e = item.EmailAddress;
		var t = item.Phones[1].PhoneNumber;

		var _e = (e == "") ? '--' : e;
		var _t = (t == "") ? '--' : t;
		if (item.IsCustomer) {
			showAddContact(_t, _e, n);
		}
	}
}

function getDataByAll(lists) {
	for ( var i = 0, j = lists.length; i < j; i++) {
		var item = lists[i];
		var n = item.Name;
		var e = item.EmailAddress;
		var t = item.Phones[1].PhoneNumber;
		var _e = (e == "") ? '--' : e;
		var _t = (t == "") ? '--' : t;
		showAddContact(_t, _e, n);
	}
}

function getContactsByItem(type, id, url, userId) {
	$.ajax({
		url : url,
		type : 'GET',
		dataType : 'json',
		data : {
			"id" : id,
			"userId" : userId,
			'type' : type
		},
		success : showCurrentContacts
	});
}

function showCurrentContacts(res) {
	if (res.result) {
		$(".c_details_content li").remove();
		var json = res.data;
		if (json.length > 0) {
			for ( var i = 0, j = json.length; i < j; i++) {
				var temp = json[i];
				var t = temp.telephone;
				var e = temp.uemail;
				var n = temp.companyName;

				showAddContact(t, e, n);
			}
		}

	} else {
		alert("Connection TimeOut.");
	}
}

function showAddContact(t, e, n) {
	$(".c_details_content:first").append(
			"<li class='li_default'><div class='info_area p_info'>" + t
					+ "</div><div class='info_area e_info'>" + e
					+ "</div><div class='info_area n_info'>" + n
					+ "</div></li>");

}

function clearContact(){
	$("input[name='companyName']").val("");
	$("input[name='uemail']").val("");
	$("input[name='telephone']").val("");
}