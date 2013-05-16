$(function(){
	$(".cus_field").click(function(){$(".selected_bg").css({"-webkit-transform":"rotate(-180deg)","-moz-transform":"rotate(-180deg)","-ms-transform":"rotate(-180deg)","-o-transform":"rotate(-180deg)",transform:"rotate(-180deg)"});$(".sup_field").removeClass("selected_field");$(".cus_field").addClass("selected_field")});$(".sup_field").click(function(){$(".selected_bg").css({"-webkit-transform":"rotate(0deg)","-moz-transform":"rotate(0deg)","-ms-transform":"rotate(0deg)","-o-transform":"rotate(0deg)",transform:"rotate(0deg)"});$(".cus_field").removeClass("selected_field");$(".sup_field").addClass("selected_field")});$(".ed_area").toggle(function(){$(".ed_area strong").css("background-position","0 -8px")},function(){$(".ed_area strong").css("background-position","0 0")});$(".sd_area").toggle(function(){$(".sd_area strong").css("background-position","0 -8px")},function(){$(".sd_area strong").css("background-position","0 0")});$(".cn_area").toggle(function(){$(".cn_area strong").css("background-position","0 -8px")},function(){$(".cn_area strong").css("background-position","0 0")});$(".pn_area").toggle(function(){$(".pn_area strong").css("background-position","0 -8px")},function(){$(".pn_area strong").css("background-position","0 0")});$(".na_area").toggle(function(){$(".na_area strong").css("background-position","0 -8px")},function(){$(".na_area strong").css("background-position","0 0")});$(".plus_btn").click(function(){$(".add_sup_line").removeClass("for_plus");$(".add_sup_line").addClass("for_select")});$(".cross_btn").click(function(){$(".add_sup_line").removeClass("for_select");$(".add_sup_line").addClass("for_plus")});$(".sup_input, .lan_input").focus(function(){$(this).addClass("typing")});$(".sup_input, .lan_input").blur(function(){$(this).removeClass("typing")});$(".pnu_area").toggle(function(){$(".pnu_area strong").css("background-position","0 -8px")},function(){$(".pnu_area strong").css("background-position","0 0")});$(".pna_area").toggle(function(){$(".pna_area strong").css("background-position","0 -8px")},function(){$(".pna_area strong").css("background-position","0 0")});$(".dov_area").toggle(function(){$(".dov_area strong").css("background-position","0 -8px")},function(){$(".dov_area strong").css("background-position","0 0")});$(".sup_area").toggle(function(){$(".sup_area strong").css("background-position","0 -8px")},function(){$(".sup_area strong").css("background-position","0 0")});$(".dad_area").toggle(function(){$(".dad_area strong").css("background-position","0 -8px")},function(){$(".dad_area strong").css("background-position","0 0")});$(".lse_area").toggle(function(){$(".lse_area strong").css("background-position","0 -8px")},function(){$(".lse_area strong").css("background-position","0 0")});$(".ema_area").toggle(function(){$(".ema_area strong").css("background-position","0 -8px")},function(){$(".ema_area strong").css("background-position","0 0")});$(".nam_area").toggle(function(){$(".nam_area strong").css("background-position","0 -8px")},function(){$(".nam_area strong").css("background-position","0 0")});
	
	var sort_data;
	var flage = false;
	var search_result = {"data" : []};
	
	$(".ed_area, .sd_area, .cn_area, .pn_area, .na_area").click(function(){
		var className = $(this).attr("class");
		showOrHide(className);
		var w = $("." + className + " strong").css("background-position").length;
		var d = !flage ? sort_data : search_result;
		switch(className){
			case "ed_area" : orderBy("endDate", w, d);break;
			case "sd_area" : orderBy("startDate", w, d);break;
			case "cn_area" : orderBy("customerName", w, d);break;
			case "pn_area" : orderBy("poNumber", w, d);break;
			case "na_area" : orderBy("projectName", w, d);break;
		}
	});
	
	$("#search").keyup(function(){
		var search_value = $("#search").val();
		_search(search_value);
		$(".c_details_content").empty();
		for(var i = 0, j = search_result.data.length; i < j; i ++){
			var temp = search_result.data[i];
			var id = temp.id;
			var e = temp.endDate;
			var s = temp.startDate;
			var c = temp.customerName;
			var pn = temp.poNumber;
			var po = temp.projectName;
			showData(id,e,s,c,pn,po);
		}
		flage = true;
	});
	
	function _search(search_value){
		search_result.data.length = 0;
		for(var i = 0, j = sort_data.data.length; i < j; i ++){
			var temp = sort_data.data[i];
			var projectName = temp.projectName;
			if(projectName.substring(0,search_value.length).toLowerCase().indexOf(search_value.toLowerCase()) > -1){
				pushValue(search_result.data,sort_data.data[i]);
			}
			var poNumber = temp.poNumber;
			if(poNumber.substring(0,search_value.length).toLowerCase().indexOf(search_value.toLowerCase()) > -1){
				pushValue(search_result.data,sort_data.data[i]);
			}
			var customerName = temp.customerName;
			if(customerName.substring(0,search_value.length).toLowerCase().indexOf(search_value.toLowerCase()) > -1){
				pushValue(search_result.data,sort_data.data[i]);
			}
		}
	}
	
	function pushValue(d,n){
		if(d.length == 0){
			if(d.id != n.id){
				d.push(n);
			}
		}
		for(var i = 0, j = d.length; i < j; i ++){
			if(d[i]["id"] == n["id"]){
				return false;
			}
		}
		d.push(n);
	}
	
	function orderBy(p, w, d){
		d.data = (w == 8) ? d.data.sort(function(a,b){ return a[p] > b[p] ? 1 : -1; }) : d.data.sort(function(a,b){ return a[p] < b[p] ? 1 : -1; });
		var reports = d.data;
		$(".c_details_content").empty();
		for(var i = 0, j = d.data.length; i < j; i ++){
			var temp = d.data[i];
			var id = temp.id;
			var e = temp.endDate;
			var s = temp.startDate;
			var c = temp.customerName;
			var pn = temp.poNumber;
			var po = temp.projectName;
			showData(id,e,s,c,pn,po);
		}
	}
	
	function showData(poNumber,endDate,startDate,customerName,poNumber,projectName){
		var endDate = (endDate != '' || endDate != null) ? getTimeFormat(endDate) : "--";
		var startDate = (startDate != '' || startDate != null) ? getTimeFormat(startDate) : "--";
		$(".c_details_content").append('<li class="li_defalut" id="' + poNumber + 
		'"><div class="info_area ed_info">'+endDate+
		'</div><div class="info_area sd_info">'+startDate+
		'</div><div class="info_area cn_info">'+customerName+
		'</div><div class="info_area pn_info">'+poNumber+
		'</div><div class="info_area na_info">'+projectName+'</div></li>');
	}
	
	function showOrHide(show){
		$(".ed_area strong, .sd_area strong, .cn_area strong, .pn_area strong, .na_area strong").hide();
		$("." + show + " strong").show();
	}
	
	function getTimeFormat(t){//输出的时间格式
			var tm = new Date(Number(t));
			var y = tm.getFullYear();
			var m = tm.getMonth() + 1;
			var d = tm.getDate();
			m = (m.length==1)? "0"+m:m;
			d = (d.length == 1)? "0"+d:d;
			return d + "/" + m + "/" + y;
		}
	
	var initData = function(){
		$.getJSON("/project-list",{"companyId":$("input[name='current_company_id']").val()},function(d){
			if(d.result){
			  if(d.data.length > 0){
				sort_data = d;
				for(var i = 0, j = d.data.length; i < j; i ++){
					var temp = d.data[i];
					var id = temp.id;
					var e = temp.endDate;
					var s = temp.startDate;
					var c = temp.customerName;
					var pn = temp.poNumber;
					var po = temp.projectName;
					showData(id,e,s,c,pn,po);
				}

			  }else{

			    $(".adv_box").css("display","block");

			  }
				
			}
			
		});
	}();
		
})
