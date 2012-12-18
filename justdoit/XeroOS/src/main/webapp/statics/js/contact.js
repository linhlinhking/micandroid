
/**Store Customers to Localstorage*/
var currentCustomers = [];
/**Store Suppliers to Localstorage*/
var currentSuppliers = [];
/**Store All Contacts to Localstorage*/
var currentAllContacts = [];
var xeroStorage = new XeroStorage();

$(function() {
	var currentUserId = $("input[name='userId']").val();
	var isXero = $("input[name='isLinkXero']").val() ;
	
	$(".add_button").click(function() {
		var Name = $("input[name='companyName']").val();
		var EmailAddress = $("input[name='uemail']").val();
		var DefaultNumber = $("input[name='telephone']").val() ;
		var groupId = $("input[name='groupId']").val();
		
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
		getContactsByType('All');
	});

	$(".cus_field").click(function() {
		$(".all_field").removeClass("selected_field");
		$(".sup_field").removeClass("selected_field");
		$(".cus_field").addClass("selected_field");
		$("input[name='groupId']").val(1);
		getContactsByType('Customer');
	});

	$(".sup_field").click(function() {
		$(".all_field").removeClass("selected_field");
		$(".cus_field").removeClass("selected_field");
		$(".sup_field").addClass("selected_field");
		$("input[name='groupId']").val(2);
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

	selLoadingWay(isXero,currentUserId);

});

function selLoadingWay(id,userId){
	if(id == 1){
		getContactsByXero("/contact-xero");
	}else{
		getContactsByItem("/contact-list",userId);
	}
}

/**
 * @param url
 * @Descripton Link to Xero.Get All Contact from Xero. 
 * @author Devon.ning
 */
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


/**
 * @param type (ALL SUPPLIER CUSTOMER)
 * @Description Click Each Button,Back the Contacts'Data.
 * @author Devon.ning 
 */
function getContactsByType(type) {
	var obj = xeroStorage.get("currentAllContacts");
	var myData = JSON.parse(obj);
	if (myData != "" && myData != null) {
		$(".c_details_content li").remove();
		switch (type) {
		case 'All':
			getDataByType(myData);
			break;
		case 'Supplier':
			var obj = xeroStorage.get("currentSuppliers");
			var tempObj = "[" + obj + "]";
			var tempJson = JSON.parse(tempObj);
			getDataByType(tempJson);
			break;
		case 'Customer':
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

/**
 * @param lists Json for Contact.
 * @Descriptioin show the data on page.
 * 
 * @author Devon.ning 
 * 
 */
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



/**
 * @param url Link to local data
 * @param groupId (1=customers 2=suppliers)
 * @param userId for current sign in user. 
 * @Description get Contacts from local db.
 * @author Devon.Ning
 */
function getContactsByItem(url,userId) {
	$.ajax({
		url : url,
		type : 'GET',
		dataType : 'json',
		data : {
			"groupId" : $("input[name='groupId']").val(),
			"userId" : userId
		},
		success : showCurrentContacts
	});
}

/**
 * @param res Response from Server.
 * @Description Set localStorage data.
 */
function showCurrentContacts(res) {
	if (res.result) {
		$(".c_details_content li").remove();
		var json = res.data;
		var jsonList = JSON.stringify(json);
		
		xeroStorage.set("currentAllContacts", jsonList);
		if (json.length > 0) {
			for ( var i = 0, j = json.length; i < j; i++) {
				var temp = json[i];
				
				var itemTxt = JSON.stringify(temp);
				
				var t = temp.telephone;
				var e = temp.uemail;
				var n = temp.companyName;
				
				if (temp.groupId == 1) {
					currentCustomers.push(itemTxt);
				}
				if (temp.groupId == 2) {
					currentSuppliers.push(itemTxt);
				}

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

/**
 * @Description clear added form data.
 * @author Devon.Ning
 */
function clearContact(){
	$("input[name='companyName']").val("");
	$("input[name='uemail']").val("");
	$("input[name='telephone']").val("");
}