var locStorage = window.localStorage ;
/** Current Customers come from Xero.*/
var CurrentCustomers = locStorage.getItem("currentCustomers");
var CustomersObj = "[" + CurrentCustomers + "]";
var JSONCustomers = JSON.parse(CustomersObj);

/** Current Suppliers come from Xero.*/
var CurrentSuppliers = locStorage.getItem("currentSuppliers");
var SuppliersObj = "[" + CurrentSuppliers + "]" ;
var JSONSuppliers = JSON.parse(SuppliersObj);

$(function(){

	var isLinkXero = $("input[name=isLinkXero]").val();
	
	
	$(".check_name, .check_pon, .check_cname, .check_sdate, .check_edate, .check_sula").hide();
	$(".plus_field").click(function(){
		$(".mask_area").fadeIn('fast', function () {
            // $(".add_cus_bg").fadeIn('fast');
        });
        if(isLinkXero === '1'){//Link to Xero
        	$(".with_cus").fadeIn('fast') ;
        	
        }else{//Link to local.
        	var userId = $("#userId").val();
	        $.getJSON("/contact-list",{"groupId":'',"userId":userId}, function(d){
				if(d.result == 'true' || d.result == true){
					json_t = d.data;
				    json_t !="" ? $(".with_cus").fadeIn('fast'):$(".without_cus").fadeIn('fast');
				    
					
				}
			});
        }
      
		
		
        $.getJSON("/language",function(d){
        	if(d.result == 'true' || d.result == true){
        		json_l = d.data;
        	}
        });
	});
	
	$("#start_day").datepicker({
		changeMonth : true,
		changeYear : true,
		dateFormat :'dd/mm/yy',
		altField : '#end_day',
		minDate : getServerTime(),
		onSelect : function(date){
			var str = date.split("/");
			$("#end_day").datepicker('option','minDate',new Date(str[2],str[1]-1,str[0]));			
		},
	
	});
	$("#end_day").datepicker({
		changeMonth : true,
		changeYear : true,
		dateFormat :'dd/mm/yy'
				
	});﻿
	
	
	
	
	$(".mask_area, .cancel_button").click(function(){
		$(".add_cus_bg").fadeOut('fast', function () {
            $(".mask_area").fadeOut('fast');
            
           $(".cus_content, .sup_content, .lan_content").hide();

			$(".add_cus_bg input").each(function(i){
				$(this).val('');
			});
			$(".check_name, .check_pon, .check_cname, .check_sdate, .check_edate, .check_sula").hide();

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
	
	$(".cus_content, .sup_content, .lan_content").hide();

	var temp_json = {"data" : []};
	var json_t;
	var json_l;
	var className;
	$(".sup_input, .lan_input, #customerName").focus(function(){
		className = $(this).attr("class")
		var value = $(this).val();
		if(className == "typing"){
			if(isLinkXero === '1'){
				json_t = JSONCustomers ;
			}
			hideOrShow(".cus_content",".lan_content", ".sup_content");
			searchData(value, json_t, temp_json.data, 'companyName', 'uemail',isLinkXero);
			dataShow(temp_json, ".cus_content", "#customerName", "companyName", "uemail", false, false);
		}else if(className == "sup_input"){
			if(isLinkXero === '1'){
				json_t = JSONSuppliers ;
			}
			hideOrShow(".sup_content",".lan_content", ".cus_content");
			searchData(value, json_t, temp_json.data, 'companyName', 'uemail',isLinkXero);
			dataShow(temp_json, ".sup_content", ".sup_input", "companyName", 'uemail', false, false);
		}else if(className == "lan_input"){
			hideOrShow(".lan_content",".cus_content", ".sup_content");
			searchData_lang(value, json_l, temp_json.data);
			dataShow(temp_json, ".lan_content", ".lan_input", "language", '', false, true);
		}
	}).keyup(function(e){
		var value = $(this).val();
		if(className == "typing"){
			if(isLinkXero === '1'){
				json_t = JSONCustomers ;
			}
			hideOrShow(".cus_content",".lan_content", ".sup_content");
			searchData(value, json_t, temp_json.data, 'companyName', 'uemail',isLinkXero);
			dataShow(temp_json, ".cus_content", "#customerName", "companyName", "uemail", false, false);
		}else if(className == "sup_input"){
			if(isLinkXero === '1'){
				json_t = JSONSuppliers ;
			}
			hideOrShow(".sup_content",".lan_content", ".cus_content");
			searchData(value, json_t, temp_json.data, 'companyName', 'uemail',isLinkXero);
			dataShow(temp_json, ".sup_content", ".sup_input", "companyName", 'uemail', false, false);
		}else if(className == "lan_input"){
			hideOrShow(".lan_content",".cus_content", ".sup_content");
			searchData_lang(value, json_l, temp_json.data);
			dataShow(temp_json, ".lan_content", ".lan_input", "language", '', false, true);
		}

	});
	
	$("#project_name, #po_number,  #start_day, #end_day, ").focusout(function(){
		var obj = $(this).attr("id");
		if($(this).val()==''){
			switch(obj){
				case "project_name" : $(".check_name").show();break;
				case "po_number" : $(".check_pon").show();break;
				
			}
		}else{
			switch(obj){
				case "project_name" : $(".check_name").hide();break;
				case "po_number" : $(".check_pon").hide();break;
				
			}
		}		
	});
	
	$(".add_button").click(function(){
		var project_name = $("#project_name").val();
		var po_number = $("#po_number").val();
		var customerName = $("#customerName").val();
		var start_day = $("#start_day").val();
		var end_day = $("#end_day").val();
		var sup_input = $(".sup_input").val();
		var lan_input = $("#language_type").val();
		var customerId = $("#customerId").val();
		var supplierId = $("#supplierId").val();
		var user_id = $("input[name='userId']").val();
		var current_company_id = $("input[name='current_company_id']").val();
		var supplierEmail = $("#supplierEmail").val();
		var customerEmail = $("#customerEmail").val();
		var linkToXero = $("input[name=isLinkXero]").val();
		
		if(project_name == ''){
			$(".check_name").show();
		}else{
			$(".check_name").hide();
		}
		if(po_number == ''){
			$(".check_pon").show();
		}else{
			$(".check_pon").hide();
		}
		if(customerName == ''){
			$(".check_cname").show();
		}else{
			$(".check_cname").hide();
		}
		if(sup_input == '' || lan_input == ''){
			$(".check_sula").show();
		}else{
			$(".check_sula").hide();
		}
		
		if(start_day == ''){
			$(".check_sdate").show();
		}else{
			$(".check_sdate").hide();
		}
		if(end_day == ''){
			$(".check_edate").show();
		}else{
			$(".check_edate").hide();
		}
		
		
		
		if(project_name != '' && po_number !='' && customerName != '' && start_day != '' && end_day != '' && sup_input != '' && lan_input != ''){
			var params = {"proName":project_name,
			"poNumber":po_number,
			"customerId":customerId,
			"customerName":customerName,
			"startDate":start_day,
			"endDate":end_day,
			"supplierId":supplierId,
			"supplierName":sup_input,
			"supplierLang":lan_input,
			"userId":user_id,
			"companyId":current_company_id,
			"supplierEmail":supplierEmail,
			"customerEmail":customerEmail,
			"linkToXero":linkToXero } ;
			
			$.ajax({
				url : "/project-add",
				data : params,
				type : "POST",
				dataType : "json",
				success : function(d){
					$(".add_cus_bg").fadeOut('fast', function () {
		            	$(".mask_area").fadeOut('fast');
		            	$(".add_cus_bg input").each(function(i){
							$(this).val('');
						});
		       	 });
				
					window.top.location.href = "/project";
				}
			});
			
		}
		
		
	});
	
	$(".c_details_content li").die().live("click",function(){
		var _poNumber = this.id ;
		window.top.location.href = "/project-detail/" + _poNumber;
	});
	
	// $(".add_cus_bg:not(#customerName, .sup_input, .lan_input)").click(function(){
		// $(".lan_content, .cus_content, .sup_content").hide();
// 		
	// });
	// $(".add_cus_bg").not(".border_row,#customerName, .sup_input, .lan_input").click(function(){
		// $(".lan_content, .cus_content, .sup_content").hide();
	// });
	$("#customerName, .sup_input, .lan_input").focusout(function(){
		setTimeout(function(){
			$(".lan_content, .cus_content, .sup_content").hide();
		},200);
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
	
	function dataShow(f, t, inp, type, type2, n, lang){// 显示供应商名 f:数据源
														// inp:输入框对象
												// t:显示的位置 type:类型 n:是否按下回车键
		$(t).empty();
		var customerId = $("#customerId").val();
		var supplierId = $("#supplierId").val();
		var tempXeroID = $("input[name=isLinkXero]").val();
		// supplier = 1 customer = 2
		for(var i = 0; i < f.data.length; i ++){
			if(className == "typing"){
				$(t).append('<li cusMail='+f.data[i].uemail+'>'+ handleJSON(f.data[i], type) +'</li>');
				
			}else if(className == "sup_input"){
				$(t).append('<li supMail='+f.data[i].uemail+'>'+ handleJSON(f.data[i], type) +'</li>');
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
				if(tempXeroID === '1'){
					$("#customerId").val('0');
				}else{
					$("#customerId").val(session.getItem($(this).html()));
				}
				$("#customerEmail").val($(this).attr("cusMail"));
			}else if(className == "sup_input"){
				if(tempXeroID === '1'){
					$("#supplierId").val('0');
				}else{
					$("#supplierId").val(session.getItem($(this).html()));
				}
				$("#supplierEmail").val($(this).attr("supMail"));
			}
			$(t).hide();
		})
	}
	function getServerTime(){
        var http;
        if(window.XMLHttpRequest){
        	http = new XMLHttpRequest();
        }else{
        	http = new ActiveXObject("Microsoft.XMLHTTP");
        }
		http.open("HEAD", ".", false);   
		http.send(null);   
		return new Date(http.getResponseHeader("Date"));
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
	
	/** 
	 *	example:searchData(value, json_t, temp_json.data, 'companyName', 'uemail');
	 *
	 */
	function searchData(v,d,t,type,type2,isXero){// 键盘按下时搜索 v:搜索值 d:直接对比数据源
											// t:重新构建的临时数据源
		t.length = 0;
		if(isXero === '1'){
			
			if(d.length > 0 ){
				for(var i=0,j=d.length;i<j;i++){
					var item = d[i] ;
					//var v_arry = "{'companyName':"+item.Name+",'uemail':"+item.EmailAddress+"}" ;
					var v_arry = '{"companyName":"'+item.Name+'","uemail":"'+item.EmailAddress+'"}' ;
					var json_v_arry = JSON.parse(v_arry);
					t.push(json_v_arry) ;
				}
				
			}
		}else{
			for(var i = 0; i < d.length; i ++){
				var temp_arr = '{"'+type+'":"'+handleJSON(d[i], type)+'","'+type2+'":"' +  handleJSON(d[i], type2) + '","id":"'+handleJSON(d[i], "id")+'", "groupId" : "' + handleJSON(d[i], "groupId") +'"}';
				
				(handleJSON(d[i], type).toLowerCase().substring(0,v.length).indexOf(v.toLowerCase()) > -1)?t.push(eval("("+temp_arr+")")):null;
			}
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










