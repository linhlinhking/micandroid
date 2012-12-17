var json = {	
				"reports": [
		                {
		                    "supplier": "Supplier Name",
		                    "days_overdue": "3",
		                    "project_name": "Bags for Big Tom",
		                    "po_number": "PO8349857",
		                    "telephone": "+86 1234 567890"
		                },
		                {
		                    "supplier": "Aupplier Name",
		                    "days_overdue": "30",
		                    "project_name": "Wags for Big Com",
		                    "po_number": "WO8349857",
		                    "telephone": "+86 1234 567890"
		                },
		                {
		                    "supplier": "Eupplier Name",
		                    "days_overdue": "78",
		                    "project_name": "Tom",
		                    "po_number": "TO8349857",
		                    "telephone": "+86 1234 567890"
		                }
		            ],
	            "data": [
	                {
	                    "username": "Will",
	                    "uemail": "will@gmail.com.cn",
	                    "updateDateTime": 1354677821000,
	                    "createDateTime": 1354677821000
	                },
	                {
	                    "username": "Bill",
	                    "uemail": "bill@gmail.com.cn",
	                    "updateDateTime": 1354677805000,
	                    "createDateTime": 1354677805000
	                },
	                {
	                    "username": "will",
	                    "uemail": "bill@gmail.com.cn",
	                    "updateDateTime": 1357269805000,
	                    "createDateTime": 1357269805000
	                },
	                {
	                    "username": "went",
	                    "uemail": "bill@gmail.com.cn",
	                    "updateDateTime": 1355366830000,
	                    "createDateTime": 1355366889000
	                },
	                {
	                    "username": "Sky",
	                    "uemail": "bill@gmail.com.cn",
	                    "updateDateTime": 1354677614000,
	                    "createDateTime": 1354677614000
	                },
	                {
	                    "username": "Dill",
	                    "uemail": "dill@gmail.com.cn",
	                    "updateDateTime": 1354677557000,
	                    "createDateTime": 1354677557000
	                }
	            ],
	            "project" : [
	                {
	                	"p_name" : "Green",
	                	"p_number" : "3489572897",
	                	"customer_name" : "Will",
	                	"start_date" : "07/11/2011",
	                	"end_date" : "12/12/2011"
	                },
	                {
	                	"p_name" : "Blue",
	                	"p_number" : "1289572897",
	                	"customer_name" : "Will",
	                	"start_date" : "11/11/2011",
	                	"end_date" : "01/25/2012"
	                },
	                {
	                	"p_name" : "Goeen",
	                	"p_number" : "7889572897",
	                	"customer_name" : "Bill",
	                	"start_date" : "03/26/2012",
	                	"end_date" : "09/20/2012"
	                },
	                {
	                	"p_name" : "Sky",
	                	"p_number" : "5289572897",
	                	"customer_name" : "Blue",
	                	"start_date" : "09/16/2010",
	                	"end_date" : "14/12/2011"
	                },
	                {
	                	"p_name" : "Withe",
	                	"p_number" : "6289572897",
	                	"customer_name" : "Will",
	                	"start_date" : "04/29/2012",
	                	"end_date" : "11/30/2012"
	                }
	            
	            
	            ]
	        };

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
		
		
		
		$(".plus_btn").click(function(){
			$(".add_sup_line").removeClass("for_plus");
			$(".add_sup_line").addClass("for_select");
		});
		$(".cross_btn").click(function(){
			$(".add_sup_line").removeClass("for_select");
			$(".add_sup_line").addClass("for_plus");
		});
		
		$(".sup_input, .lan_input").focus(function(){
			$(this).addClass("typing");
		});
		$(".sup_input, .lan_input").blur(function(){
			$(this).removeClass("typing");
		});
		
		$(".pnu_area").toggle(function(){
			$(".pnu_area strong").css("background-position","0 -8px");
		},function(){
			$(".pnu_area strong").css("background-position","0 0");
		});
		$(".pna_area").toggle(function(){
			$(".pna_area strong").css("background-position","0 -8px");
		},function(){
			$(".pna_area strong").css("background-position","0 0");
		});
		$(".dov_area").toggle(function(){
			$(".dov_area strong").css("background-position","0 -8px");
		},function(){
			$(".dov_area strong").css("background-position","0 0");
		});
		$(".sup_area").toggle(function(){
			$(".sup_area strong").css("background-position","0 -8px");
		},function(){
			$(".sup_area strong").css("background-position","0 0");
		});
		$(".dad_area").toggle(function(){
			$(".dad_area strong").css("background-position","0 -8px");
		},function(){
			$(".dad_area strong").css("background-position","0 0");
		});
		$(".lse_area").toggle(function(){
			$(".lse_area strong").css("background-position","0 -8px");
		},function(){
			$(".lse_area strong").css("background-position","0 0");
		});
		$(".ema_area").toggle(function(){
			$(".ema_area strong").css("background-position","0 -8px");
		},function(){
			$(".ema_area strong").css("background-position","0 0");
		});
		$(".nam_area").toggle(function(){
			$(".nam_area strong").css("background-position","0 -8px");
		},function(){
			$(".nam_area strong").css("background-position","0 0");
		});
		
	


		//$(".c_details_content li").die().live('click',function{
		//	
		//});


		var user_json;
		var search_date;
		$.ajax({
		  url: '/user-list',
		  type:'GET',
		  dataType: 'json',
		  ﻿data:{
			  "planId":$("input[name='planId']").val(),
			  "companyId":$("input[name='companyId']").val()
		  },
		  success: function(e){
			  if(e.result){
				  user_json = e;
				  search_date = e;
				  showDate();
			  }
			  getTimeFormat(new Date(e.data[0].createDateTime));
		  }		 
		});
		//控制排序按钮只显示一个
		var showOrHide = function(show, hide1, hide2, hide3){
			$(show).show();
			$(hide1).hide();
			$(hide2).hide();
			$(hide3).hide();
		}
		//report
		$(".sup_area, #sup_area").click(function(){
			showOrHide("#sup_area", "#dov_area", "#pna_area", "#pnu_area");
			var w = $("#sup_area").css("background-position").length;
			orderBy("supplier", w);
		});
		$(".dov_area, #dov_area").click(function(){
			showOrHide("#dov_area", "#sup_area", "#pna_area", "#pnu_area");
			var w = $("#dov_area").css("background-position").length;
			orderBy("days_overdue", w);
		});
		$(".pna_area, #pna_area").click(function(){
			showOrHide("#pna_area", "#dov_area", "#sup_area", "#pnu_area");
			var w = $("#pna_area").css("background-position").length;
			orderBy("project_name", w);
		});
		$(".pnu_area, #pnu_area").click(function(){
			showOrHide("#pnu_area", "#dov_area", "#pna_area", "#sup_area");
			var w = $("#pnu_area").css("background-position").length;
			orderBy("po_number", w);
		});
		//manage_user
		$(".dad_area, #dad_area").click(function(){
			showOrHide("#dad_area", "#lse_area", "#ema_area", "#nam_area");
			var w = $("#dad_area").css("background-position").length;
			orderBy("createDateTime", w);
		});
		$(".lse_area, #lse_area").click(function(){
			showOrHide("#lse_area", "#dad_area", "#ema_area", "#nam_area");
			var w = $("#lse_area").css("background-position").length;
			orderBy("updateDateTime", w);
		});
		$(".ema_area, #ema_area").click(function(){
			showOrHide("#ema_area", "#lse_area", "#dad_area", "#nam_area");
			var w = $("#ema_area").css("background-position").length;
			orderBy("uemail", w);
		});
		$(".nam_area, #nam_area").click(function(){
			showOrHide("#nam_area", "#lse_area", "#ema_area", "#dad_area");
			var w = $("#nam_area").css("background-position").length;
			orderBy("username", w);
		});
		//project
		$(".ed_area").click(function(){
			showOrHide("#ed_area", "#sd_area", "#pn_area", "#na_area");
			var w = $("#ed_area").css("background-position").length;
			orderBy("end_date", w);
		});
		$(".sd_area").click(function(){
			showOrHide("#sd_area", "#ed_area", "#pn_area", "#na_area");
			var w = $("#sd_area").css("background-position").length;
			orderBy("start_date", w);
		});
		$(".pn_area").click(function(){
			showOrHide("#pn_area", "#sd_area", "#ed_area", "#na_area");
			var w = $("#pn_area").css("background-position").length;
			orderBy("p_number", w);
		});
		$(".na_area").click(function(){
			showOrHide("#na_area", "#sd_area", "#pn_area", "#ed_area");
			var w = $("#na_area").css("background-position").length;
			orderBy("p_name", w);
		});
		
		//显示json数据
		var type_page = $("#type_page").val();
		function showDate(){
			if(type_page == "reports"){
				for(var i = 0; i < json.reports.length; i ++){
					$(".c_details_content").append('<li class="li_defalut"><div class="info_area tel_info">'+json.reports[i].telephone+'</div><div class="info_area pnu_info">'+json.reports[i].po_number+'</div><div class="info_area pna_info">'+json.reports[i].project_name+'</div><div class="info_area dov_info">'+json.reports[i].days_overdue+'</div><div class="info_area sup_info">'+json.reports[i].supplier+'</div></li>');
				}
			}else if(type_page == "user"){
				for(var i = 0; i < user_json.data.length; i ++){
					var createDateTime = getTimeFormat(user_json.data[i].createDateTime);
					var updateDateTime = "--";
					if ("" != user_json.data[i].updateDateTime && null != user_json.data[i].updateDateTime) {
						updateDateTime = getTimeFormat(user_json.data[i].updateDateTime);
					}

					$(".c_details_content").append(' <li class="li_defalut" id="'+user_json.data[i].id+'"><div class="info_area dad_info">'+ createDateTime+ '</div><div class="info_area lse_info">'+ updateDateTime+ '</div><div class="info_area ema_info">'+ user_json.data[i].uemail+ '</div><div class="info_area nam_info">'+ user_json.data[i].username + '</div></li>');
				}
			}else if(type_page == 'project'){
				for(var i = 0; i < json.project.length; i ++){
					$(".c_details_content").append('<li class="li_defalut" id="'+json.project[i].id+'"><div class="info_area ed_info">'+json.project[i].end_date+'</div><div class="info_area sd_info">'+json.project[i].start_date+'</div><div class="info_area cn_info">'+json.project[i].customer_name+'</div><div class="info_area pn_info">'+json.project[i].p_number+'</div><div class="info_area na_info">'+json.project[i].p_name+'</div></li>');
				}
			}
		}
		//输出的时间格式
		function getTimeFormat(t){
			var tm = new Date(t);
			var y = tm.getFullYear();
			var m = tm.getMonth() + 1;
			var d = tm.getDate();
			return y + "-" + m + "-" + d;
		}
		//排序
		var f = false;//是否对搜索结果排序
		var search_json = {'data' : []};
		function orderBy(v,w){
			$(".c_details_content").empty();
			if(type_page == "reports" && !f){//reports无搜索时排序
				if(w == 8){
					json = (json.reports == 'undefined' || json.reports == undefined)? json.sort(function(a,b){return a[v]>b[v]? 1 : -1;}) : json.reports.sort(function(a,b){return a[v]>b[v]? 1 : -1;});
				}else{
					json = (json.reports == 'undefined' || json.reports == undefined)? json.sort(function(a,b){return a[v]<b[v]? 1 : -1;}) : json.reports.sort(function(a,b){return a[v]<b[v]? 1 : -1;});
				}
				for(var i = 0; i < json.length; i ++){
					$(".c_details_content").append('<li class="li_defalut" id="'+json[i].id+'"><div class="info_area tel_info">'+json[i].telephone+'</div><div class="info_area pnu_info">'+json[i].po_number+'</div><div class="info_area pna_info">'+json[i].project_name+'</div><div class="info_area dov_info">'+json[i].days_overdue+'</div><div class="info_area sup_info">'+json[i].supplier+'</div></li>');
				}
			}else if(type_page == "user" && !f){//manage_user无搜索时排序
				if(w == 8){
					user_json = (user_json.data == 'undefined' || user_json.data == undefined)? user_json.sort(function(a,b){return a[v]>b[v]? 1 : -1;}) : user_json.data.sort(function(a,b){return a[v]>b[v]? 1 : -1;});
				}else{
					user_json = (user_json.data == 'undefined' || user_json.data == undefined)? user_json.sort(function(a,b){return a[v]<b[v]? 1 : -1;}) : user_json.data.sort(function(a,b){return a[v]<b[v]? 1 : -1;});
				}
				for(var i = 0; i < user_json.length; i++){
					var createDateTime = getTimeFormat(user_json[i].createDateTime);
					var updateDateTime = "--";
					if ("" != user_json[i].updateDateTime && null != user_json[i].updateDateTime) {
						updateDateTime = getTimeFormat(user_json[i].updateDateTime);
					}

					$(".c_details_content").append('<li class="li_defalut" id="'+user_json[i].id+'"><div class="info_area dad_info">'+ createDateTime+ '</div><div class="info_area lse_info">'+ updateDateTime+ '</div><div class="info_area ema_info">'+ user_json[i].uemail+ '</div><div class="info_area nam_info">'+ user_json[i].username + '</div></li>');
				}
			}else if(type_page == "project" && !f){//project无搜索时排序
				if(w == 8){
					json = (json.project == 'undefined' || json.project == undefined)? json.sort(function(a,b){return a[v]>b[v]? 1 : -1;}) : json.project.sort(function(a,b){return a[v]>b[v]? 1 : -1;});
				}else{
					json = (json.project == 'undefined' || json.project == undefined)? json.sort(function(a,b){return a[v]<b[v]? 1 : -1;}) : json.project.sort(function(a,b){return a[v]<b[v]? 1 : -1;});
				}
				for(var i = 0; i < json.length; i ++){
					$(".c_details_content").append('<li class="li_defalut" id="'+json[i].id+'"><div class="info_area ed_info">'+json[i].end_date+'</div><div class="info_area sd_info">'+json[i].start_date+'</div><div class="info_area cn_info">'+json[i].customer_name+'</div><div class="info_area pn_info">'+json[i].p_number+'</div><div class="info_area na_info">'+json[i].p_name+'</div></li>');
				}
			}
			else if(type_page == "user" && f){//manage_user搜索时排序
				if(w == 8){
					search_json = (search_json.data == 'undefined' || search_json.data == undefined)? search_json.sort(function(a,b){return a[v]<b[v]? 1 : -1;}) : search_json.data.sort(function(a,b){return a[v]>b[v]? 1 : -1;});
				}else{
					search_json = (search_json.data == 'undefined' || search_json.data == undefined)? search_json.sort(function(a,b){return a[v]>b[v]? 1 : -1;}) : search_json.data.sort(function(a,b){return a[v]>b[v]? 1 : -1;});
				}
				for(var i = 0; i < search_json.length; i++){
					var createDateTime = getTimeFormat(search_json[i].createDateTime);
					var updateDateTime = '--';
					if(null != search_json[i].updateDateTime && "" != search_json[i].updateDateTime){
						updateDateTime = getTimeFormat(search_json[i].updateDateTime);
					}
					$(".c_details_content").append(' <li class="li_defalut" id="'+search_json[i].id+'"><div class="info_area dad_info">'+ createDateTime+ '</div><div class="info_area lse_info">'+ updateDateTime+ '</div><div class="info_area ema_info">'+ search_json[i].uemail+ '</div><div class="info_area nam_info">'+ search_json[i].username + '</div></li>');
				}
			}
			
		};
		
		//搜索
		$("#search").keyup(function(e){
			var keyNum = e.which || e.keyCode;
			var search = $("#search").val();
			var search_res = '';
			search_json = {'data' : []};
			for(var i = 0; i < search_date.data.length; i ++){
				var name = search_date.data[i].username.toLowerCase();
				if(name.indexOf(search.toLowerCase()) >= 0){
					var temp = {
								'createDateTime' : search_date.data[i].createDateTime,
					            'updateDateTime'  : search_date.data[i].updateDateTime,
					            'uemail'      : search_date.data[i].uemail,
					            'username'		 : search_date.data[i].username
					};
					
					search_json.data.push(temp);
					
					var createDateTime = getTimeFormat(search_date.data[i].createDateTime);
					var updateDateTime = "--";
					if (null != search_date.data[i].updateDateTime && "" != search_date.data[i].updateDateTime) {
						updateDateTime = getTimeFormat(search_date.data[i].updateDateTime);
					}
					search_res += ' <li class="li_defalut" id="'+search_date.data[i].id+'"><div class="info_area dad_info">'+createDateTime+'</div><div class="info_area lse_info">'+updateDateTime+'</div><div class="info_area ema_info">'+search_date.data[i].uemail+'</div><div class="info_area nam_info">'+search_date.data[i].username+'</div></li>';
				}
			}
			f = true;
			$(".c_details_content").empty();
			$(".c_details_content").append(search_res);
			
			if(keyNum == 13){
				search_res = '';
				for(var i = 0; i < search_json.data.length; i ++){
					if(search.toLowerCase() == search_json.data[i].username.toLowerCase()){
						var createDateTime = getTimeFormat(search_json.data[i].createDateTime);
						var updateDateTime = "--";
						if (null != search_json.data[i].updateDateTime && "" != search_json.data[i].updateDateTime) {
							updateDateTime = getTimeFormat(search_json.data[i].updateDateTime);
						}
						search_res += ' <li class="li_defalut" id="'+search_json.data[i].id+'"><div class="info_area dad_info">'+createDateTime+'</div><div class="info_area lse_info">'+updateDateTime+'</div><div class="info_area ema_info">'+search_json.data[i].uemail+'</div><div class="info_area nam_info">'+search_json.data[i].username+'</div></li>';
					}
				};
				$(".c_details_content").empty();
				$(".c_details_content").append(search_res);
			}
			
			if(search == '' || search == null){
				search_res = '';
				$(".c_details_content").empty();
				for(var i = 0; i < search_date.data.length; i ++){
					var createDateTime = getTimeFormat(search_date.data[i].createDateTime);
					var updateDateTime = "--";
					if (null != search_date.data[i].updateDateTime && "" != search_date.data[i].updateDateTime) {
						updateDateTime = getTimeFormat(search_date.data[i].updateDateTime);
					}
					search_res += ' <li class="li_defalut" id="'+search_date .data[i].id+'"><div class="info_area dad_info">'+createDateTime+'</div><div class="info_area lse_info">'+updateDateTime+'</div><div class="info_area ema_info">'+search_date.data[i].uemail+'</div><div class="info_area nam_info">'+search_date.data[i].username+'</div></li>';
				}
				f = false;
				$(".c_details_content").empty();
				$(".c_details_content").append(search_res);
			}
			
		});
		
	});
	






