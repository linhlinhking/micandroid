$(function(){
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
	});
	
	$(".ed_area").toggle(function(){
		$(".ed_area strong").css("background-position","0 -8px");
	},function(){
		$(".ed_area strong").css("background-position","0 0");
	});
	$(".sd_area").toggle(function(){
		$(".sd_area strong").css("background-position","0 -8px");
	},function(){
		$(".sd_area strong").css("background-position","0 0");
	});
	$(".pn_area").toggle(function(){
		$(".pn_area strong").css("background-position","0 -8px");
	},function(){
		$(".pn_area strong").css("background-position","0 0");
	});
	$(".na_area").toggle(function(){
		$(".na_area strong").css("background-position","0 -8px");
	},function(){
		$(".na_area strong").css("background-position","0 0");
	});
	
	$(".plus_field").click(function(){
		$(".mask_area").fadeIn('fast', function () {
            $(".add_cus_bg").fadeIn('fast');
        });
	});
	// supplier = 1 customer = 2
	$(".mask_area, .cancel_button").click(function(){
		$(".add_cus_bg").fadeOut('fast', function () {
            $(".mask_area").fadeOut('fast');
        });
        $(".add_cus_bg input").each(function(i){
			$(this).val('');
		});
	});
	$(".cus_line input").focus(function(){
		$(this).addClass("typing");
	});
	$(".cus_line input").blur(function(){
		$(this).removeClass("typing");
	});
	$(".plus_btn").click(function(){
		$(".add_sup_line").removeClass("for_plus");
		$(".add_sup_line").addClass("for_select");
	});
	
	$(".cus_content").hide();
	$(".sup_content").hide();
	$(".lan_content").hide();
	
	var temp_json = {"data" : []};
	var json_t;
	var className;
	$(".sup_input, .lan_input, #customerName").focus(function(){
		className = $(this).attr("class")
		var userId = $("#userId").val();
		var customerId = $("#customerId").val();
		var supplierId = $("#supplierId").val();
		if(className == "typing"){
			$.getJSON("http://dev.globaldesign.co.nz/contact-list",{groupId:customerId,userId:userId}, function(d){
				if(d.result == 'true' || d.result == true){
					json_t = d.data;
				}
			});
		}else if(className == "sup_input"){
			$.getJSON("http://dev.globaldesign.co.nz/contact-list",{groupId:supplierId,userId:userId}, function(d){
				if(d.result == 'true' || d.result == true){
					json_t = d.data;
				}
			});
		}else if(className == "lan_input"){
			$.getJSON("http://dev.globaldesign.co.nz/language",function(d){
	        	if(d.result == 'true' || d.result == true){
	        		json_t = d.data;
	        	}
	        });
		}
	}).keyup(function(e){
		var value = $(this).val();
		if(className == "typing"){
			hideOrShow(".cus_content",".lan_content", ".sup_content");
			searchData(value, json_t, temp_json.data, 'companyName');
			(e.which || e.keyCode)==13 ? dataShow(temp_json, ".cus_content", "#customerName", "companyName", true, false) : dataShow(temp_json, ".cus_content", "#customerName", "companyName", false, false);
		}else if(className == "sup_input"){
			hideOrShow(".sup_content",".lan_content", ".cus_content");
			searchData(value, json_t, temp_json.data, 'uemail');
			(e.which || e.keyCode) == 13? dataShow(temp_json, ".sup_content", ".sup_input", "uemail",true, false):dataShow(temp_json, ".sup_content", ".sup_input", "uemail",false, false);
		}else if(className == "lan_input"){
			hideOrShow(".lan_content",".cus_content", ".sup_content");
			searchData_lang(value, json_t, temp_json.data);
			(e.which || e.keyCode)==13 ? dataShow(temp_json, ".lan_content", ".lan_input", "language", true, true) : dataShow(temp_json, ".lan_content", ".lan_input", "language", false, true);
		}
	});
	
	$("#start_day").datepicker({
		changeMonth : true,
		changeYear : true
	});
	

	$("#end_day").datepicker({
		changeMonth : true,
		changeYear : true
	});﻿	
	
	
	$(".add_button").click(function(){
		var project_name = $("#project_name").val();
		var po_number = $("#po_number").val();
		var customerName = $("#customerName").val();
		var start_day = $("#start_day").val();
		var end_day = $("#end_day").val();
		var sup_input = $(".sup_input").val();
		var lan_input = $(".lan_input").val();
		var customerId = $("#customerId").val();
		var supplierId = $("#supplierId").val();
		var user_id = $("input[name='userId']").val();
		$.ajax({
			url : "/project-add",
			data : "proName="+project_name+"&poNumber="+po_number+"&customerId="+customerId+"&customerName="
					+customerName+"&startDate="+start_day
					+"&endDate="+end_day+"&supplierId="+supplierId
					+"&supplierName="+sup_input+"&supplierLang="+lan_input+"&userId="+user_id,
			type : "POST",
			dataType : "json",
			success : function(d){
				$(".add_cus_bg").fadeOut('fast', function () {
		            $(".mask_area").fadeOut('fast');
		        });
			}
		});
		
		$(".add_cus_bg input").each(function(i){
			$(this).val('');
		});
		
		$(".c_details_content li").die().live("click",function(){
				window.top.location.href="/project-detail" ;
		});
	
	});
	
	$(".add_cus_bg:not(#customerName, .sup_input, .lan_input)").click(function(){
		$(".lan_content, .cus_content, .sup_content").hide();
		
	});
	$(".cross_btn").click(function(){
		$(".add_sup_line").removeClass("for_select");
		$(".add_sup_line").addClass("for_plus");
		$(".sup_input, .lan_input").val('');
	});
	
	$(".sup_input, .lan_input").focus(function(){
		$(this).addClass("typing");
	});
	$(".sup_input, .lan_input").blur(function(){
		$(this).removeClass("typing");
	});
	
});

	var session = window.sessionStorage;
	function dataShow(f, t, inp, type, n, lang){// 显示供应商名 f:数据源 inp:输入框对象
												// t:显示的位置 type:类型 n:是否按下回车键
		$(t).empty();
		for(var i = 0; i < f.data.length; i ++){
			$(t).append('<li>'+ handleJSON(f.data[i], type) +'</li>');
			if(lang) {session.setItem(f.data[i].language, f.data[i].languageCode);}
			if(n){
				$(inp).val(handleJSON(f.data[0], type));
				$(".lan_content, .cus_content, .sup_content").hide();
				if(lang){
					$("#language_type").val(session.getItem(f.data[0].language));
				}
			}
		};
		$(t + " li").live('click', function(){
			$(inp).val($(this).html());
			if(lang) {$("#language_type").val(session.getItem($(this).html()));}
			$(t).hide();
		})
	}
	
	function searchData_lang(v, d, t){// 键盘按下时搜索 v:搜索值 d:直接对比数据源 t:重新构建的临时数据源
										// type:搜索类型
		t.length = 0;
		for(var i = 0; i < d.length; i ++){
			if(d[i].language.toLowerCase().indexOf(v.toLowerCase()) > -1){
				var temp_arr = {"language" : d[i].language,"languageCode" : d[i].languageCode};
				t.push(temp_arr);
			}
		}
	}
	
	function searchData(v,d,t,type){// 键盘按下时搜索 v:搜索值 d:直接对比数据源 t:重新构建的临时数据源
		t.length = 0;
		for(var i = 0; i < d.length; i ++){
			(handleJSON(d[i], type).toLowerCase().indexOf(v.toLowerCase()) > -1)?t.push(eval("("+"{'" + type + "' :'" + handleJSON(d[i], type) + "'}"+")")):null;
		}
	}
	
	function handleJSON(json, prototypeName){
		return json[prototypeName];
	}
	
	function hideOrShow(show, hide1, hide2){
		$(show).show();
		$(hide1 + ", " + hide2).hide();
	}
	





















