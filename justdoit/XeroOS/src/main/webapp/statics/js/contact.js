$(function() {
	var currentUserId = $("input[name='userId']").val() ;
	$(".add_button").click(function() {
		$.ajax({
			url : '/contact-add',
			type : 'POST',
			dataType : 'json',
			data : {
				"companyName" : $("input[name='companyName']").val(),
				"uemail" : $("input[name='uemail']").val(),
				"telephone" : $("input[name='telephone']").val(),
				"userId" : currentUserId,
				"groupId" : $("input[name='groupId']").val()
			},
			success : function(res) {
				if (res.result) {
					var tmp = res.data ;
					showAddContact(tmp.telephone,tmp.uemail,tmp.companyName);
					$(".add_cus_bg").fadeOut('fast', function() {
						$(".mask_area").fadeOut('fast');
					});
				} else {
					alert("Connection TimeOut.");
				}
			}
		});
	});
	
	$(".cus_field").click(function(){
		$(".selected_bg").css({
		'-webkit-transform':'rotate(-180deg)',
        '-moz-transform':'rotate(-180deg)',
        '-ms-transform':'rotate(-180deg)',
        '-o-transform':'rotate(-180deg)',
        'transform':'rotate(-180deg)'
		});
		$(".sup_field").removeClass("selected_field");
		$(".cus_field").addClass("selected_field");
		$("input[name='groupId']").val(1);
		getContactsByItem("customer","1","/contact-list",currentUserId);
	});
	
	$(".sup_field").click(function(){
		$(".selected_bg").css({
		'-webkit-transform':'rotate(0deg)',
        '-moz-transform':'rotate(0deg)',
        '-ms-transform':'rotate(0deg)',
        '-o-transform':'rotate(0deg)',
        'transform':'rotate(0deg)'
		});
		$(".cus_field").removeClass("selected_field");
		$(".sup_field").addClass("selected_field");
		$("input[name='groupId']").val(2);
		getContactsByItem("supplier","2","/contact-list",currentUserId);
	});
	
	$(".n_area").toggle(function(){
		$(".n_area strong").css("background-position","0 -8px");
	},function(){
		$(".n_area strong").css("background-position","0 0");
	});
	
	$(".plus_field").click(function(){
		$(".mask_area").fadeIn('fast', function () {
            $(".add_cus_bg").fadeIn('fast');
        });
	});
	
	$(".mask_area, .cancel_button").click(function(){
		$(".add_cus_bg").fadeOut('fast', function () {
            $(".mask_area").fadeOut('fast');
        });
	});
  
  
	getContactsByItem("all","","/contact-list",currentUserId);

});

function getContactsByItem(type,id,url,userId){
	$.ajax({
		url : url,
		type : 'GET',
		dataType : 'json',
		data : {
			"id" : id,
			"userId":userId
			'type' : type
		},
		success : showCurrentContacts
	});
}

function showCurrentContacts(res){
	if (res.result) {
		$(".c_details_content li").remove();
		var json = res.data;
		if (json.length > 0) {
			for ( var i = 0, j = json.length; i < j; i++){
				var temp = json[i] ;
				$(".c_details_content").append("<li class='li_default'><div class='info_area p_info'>"+temp.telephone+"</div><div class='info_area e_info'>"+temp.uemail+"</div><div class='info_area n_info'>"+temp.companyName+"</div></li>");

			}
				
		}
	
	} else {
		alert("Connection TimeOut.");
	}
}

function showAddContact(t,e,n){
	$(".c_details_content:first").append("<li class='li_default'><div class='info_area p_info'>"+t+"</div><div class='info_area e_info'>"+e+"</div><div class='info_area n_info'>"+n+"</div></li>");

}