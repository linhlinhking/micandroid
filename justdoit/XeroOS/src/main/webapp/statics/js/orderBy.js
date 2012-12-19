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
		
		
		var temp_json;
		var type_page = $("#type_page").val();
		var temp_search_json;//搜索后的临时数据
		var flag = false;//是否为搜索后的排序
		if(type_page == "project"){
			$.getJSON("/project-list",{"userId":$("input[name='userId']").val()}, function(d){
				if(d.result == 'true' || d.result == true){
					temp_json = d;
					dataShow(temp_json, "endDate", "startDate", "customerName", "id", "projectName");
				}
			});
		}else if(type_page == "user"){
			// $.getJSON("http://dev.globaldesign.co.nz/user-list", function(d){
				// console.log(d);
			// });
					$.ajax({
					  url: 'http://dev.globaldesign.co.nz/user-list',
					  dataType: 'json',
					  success: function(e){
						  console.log(e);
					  },
					  error : function(r){
					  	  console.log(12 + r);
					  }		 
					});
		}
		
		$(".ed_area").click(function(){
			showOrHide("#ed_area", "#sd_area", "#pn_area", "#na_area");
			var w = $("#ed_area").css("background-position").length;
			f?orderBy(temp_search_json,"endDate", w):orderBy(temp_json,"endDate", w);
		});
		$(".sd_area").click(function(){
			showOrHide("#sd_area", "#ed_area", "#pn_area", "#na_area");
			var w = $("#sd_area").css("background-position").length;
			f?orderBy(temp_search_json,"startDate", w):orderBy(temp_json,"startDate", w);
		});
		$(".pn_area").click(function(){
			showOrHide("#pn_area", "#sd_area", "#ed_area", "#na_area");
			var w = $("#pn_area").css("background-position").length;
			f?orderBy(temp_search_json,"id", w):orderBy(temp_json,"id", w);
		});
		$(".na_area").click(function(){
			showOrHide("#na_area", "#sd_area", "#pn_area", "#ed_area");
			var w = $("#na_area").css("background-position").length;
			f?orderBy(temp_search_json,"projectName", w):orderBy(temp_json,"projectName", w);
		});
		
		$("#search").keyup(function(e){
			var searchValue = $("#search").val();
			search(temp_json,searchValue, "projectName", "endDate", "startDate", "customerName", "id", "projectName");
			dataShow(temp_search_json, "endDate", "startDate", "customerName", "id", "projectName");
			f = true
		});
		
	
		function dataShow(json, type1, type2, type3, type4, type5){//显示数据
			$(".c_details_content").empty();
			for(var i = 0; i < json.data.length; i ++){
				var createDateTime = getTimeFormat(json.data[i][type2]);
				var updateDateTime ;
				("" != json.data[i][type1] && null != json.data[i][type1])?updateDateTime = getTimeFormat(json.data[i][type1]):"--";
				$(".c_details_content").append('<li class="li_defalut"><div class="info_area ed_info">'+updateDateTime+'</div><div class="info_area sd_info">'+createDateTime+'</div><div class="info_area cn_info">'+json.data[i][type3]+'</div><div class="info_area pn_info">'+json.data[i][type4]+'</div><div class="info_area na_info">'+json.data[i][type5]+'</div></li>');
			}
		}
		function orderBy(data, v, w){//排序
			if(w == 8){
				data.data = data.data.sort(function(a,b){return a[v]>b[v]? 1 : -1;});
			}else{
				data.data = data.data.sort(function(a,b){return a[v]<b[v]? 1 : -1;});
			}
			dataShow(data, "endDate", "startDate", "customerName", "id", "projectName");
		}
		var showOrHide = function(show, hide1, hide2, hide3){//控制排序按钮只显示一个
			$(show).show();
			$(hide1).hide();
			$(hide2).hide();
			$(hide3).hide();
		}
		function getTimeFormat(t){//输出的时间格式
			var tm = new Date(Number(t));
			var y = tm.getFullYear();
			var m = tm.getMonth() + 1;
			var d = tm.getDate();
			return m + "/" + d + "/" + y;
		}
		function search(data, value, type, type1, type2, type3, type4, type5){
			temp_search_json = {"data" : []};
			for(var i = 0; i < data.data.length; i ++){
				if(data.data[i][type].toLowerCase().indexOf(value.toLowerCase()) > -1){
					var temp = '{"'+type1+'":"'+data.data[i][type1]+'","'
					+type2+'":"'+data.data[i][type2]+'","'
					+type3+'":"'+data.data[i][type3]+'","'
					+type4+'":"'+data.data[i][type4]+'","'
					+type5+'":"'+data.data[i][type5]+'"}';
					console.log(temp);					
					temp_search_json.data.push(eval('(' + temp + ')'));
				}
			}
		}




})


