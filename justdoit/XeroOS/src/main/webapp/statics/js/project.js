$(function(){
	
	$(".plus_field").click(function(){
		$(".mask_area").fadeIn('fast', function () {
            //$(".add_cus_bg").fadeIn('fast');
        });
        var userId = $("#userId").val();
        $.getJSON("/contact-list",{groupId:'',userId:userId}, function(d){
			if(d.result == 'true' || d.result == true){
				json_t = d.data;
				
				(d.data != '') ? $(".with_cus").fadeIn('fast') : $(".without_cus").fadeIn('fast');
			}
		});
        
	});
	
	$("#start_day").datepicker({
		changeMonth : true,
		changeYear : true,
		dateFormat :'dd/mm/yy'
	});
	

	$("#end_day").datepicker({
		changeMonth : true,
		changeYear : true,
		dateFormat :'dd/mm/yy'
	});﻿
	$(".mask_area, .cancel_button").click(function(){
		$(".add_cus_bg").fadeOut('fast', function () {
            $(".mask_area").fadeOut('fast');
            
            $(".cus_content").hide();
			$(".sup_content").hide();
			$(".lan_content").hide();

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
		if(className == "lan_input"){
			$.getJSON("/language",function(d){
	        	if(d.result == 'true' || d.result == true){
	        		json_t = d.data;
	        	}
	        });
		}
	}).keyup(function(e){
		var value = $(this).val();
		if(className == "typing"){
			hideOrShow(".cus_content",".lan_content", ".sup_content");
			searchData(value, json_t, temp_json.data, 'companyName', '');
			dataShow(temp_json, ".cus_content", "#customerName", "companyName", "", false, false);
		}else if(className == "sup_input"){
			hideOrShow(".sup_content",".lan_content", ".cus_content");
			searchData(value, json_t, temp_json.data, 'companyName', '');
			dataShow(temp_json, ".sup_content", ".sup_input", "companyName", '', false, false);
		}else if(className == "lan_input"){
			hideOrShow(".lan_content",".cus_content", ".sup_content");
			searchData_lang(value, json_t, temp_json.data);
			dataShow(temp_json, ".lan_content", ".lan_input", "language", '', false, true);
		}

	});
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
				
				window.top.location.href = "/project";
			}
		});
		$(".add_cus_bg input").each(function(i){
			$(this).val('');
		});
		
		
	});
	
	$(".c_details_content li").die().live("click",function(){
		window.top.location.href="/project-detail" ;
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
	

	var session = window.sessionStorage;
	function dataShow(f, t, inp, type, type2, n, lang){// 显示供应商名 f:数据源 inp:输入框对象
												// t:显示的位置 type:类型 n:是否按下回车键
		$(t).empty();
		var customerId = $("#customerId").val();
		var supplierId = $("#supplierId").val();
		// supplier = 1 customer = 2
		for(var i = 0; i < f.data.length; i ++){
			if(className == "typing"){
				$(t).append('<li>'+ handleJSON(f.data[i], type) +'</li>');
				
			}else if(className == "sup_input"){
				$(t).append('<li>'+ handleJSON(f.data[i], type) +'</li>');
			}else if(className == "lan_input"){
				$(t).append('<li>'+ handleJSON(f.data[i], type) +'</li>');
			}
			
			
			if(className == "lan_input") {
				session.setItem(f.data[i].language, f.data[i].languageCode);
			}else {
				session.setItem(f.data[i][type], f.data[i].id);
			}
			
		};
		$(t + " li").live('click', function(){
			$(inp).val($(this).html());
			if(lang) {$("#language_type").val(session.getItem($(this).html()));}
			if(className == "typing"){
				$("#customerId").val(session.getItem($(this).html()));
			}else if(className == "sup_input"){
				$("#supplierId").val(session.getItem($(this).html()));
			}
			$(t).hide();
		})
	}
	
	function searchData_lang(v, d, t){// 键盘按下时搜索 v:搜索值 d:直接对比数据源 t:重新构建的临时数据源
										// type:搜索类型
		t.length = 0;
		
		for(var i = 0; i < d.length; i ++){
			if(d[i].language.toLowerCase().substring(0,v.length).indexOf(v.toLowerCase()) > -1){
				var temp_arr = {"language" : d[i].language,"languageCode" : d[i].languageCode};
				t.push(temp_arr);
			}
		}
	}
	
	function searchData(v,d,t,type,type2){// 键盘按下时搜索 v:搜索值 d:直接对比数据源 t:重新构建的临时数据源
		t.length = 0;
		for(var i = 0; i < d.length; i ++){
			var temp_arr = '{"'+type+'":"'+handleJSON(d[i], type)+'","'+type2+'":"' +  handleJSON(d[i], type2) + '","id":"'+handleJSON(d[i], "id")+'", "groupId" : "' + handleJSON(d[i], "groupId") +'"}';
			
			(handleJSON(d[i], type).toLowerCase().substring(0,v.length).indexOf(v.toLowerCase()) > -1)?t.push(eval("("+temp_arr+")")):null;
		}
	}
	
	function handleJSON(j, prototypeName){
		return j[prototypeName];
	}
	
	function hideOrShow(show, hide1, hide2){
		$(show).show();
		$(hide1 + ", " + hide2).hide();
	}
	
});











