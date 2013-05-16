
$(function() {
	var sort_data;
	var flage = false;
	var search_result = {"data" : []};
	
	$(".n_area").click(function(){
		var w = $(".n_area strong").css("background-position").length;
		var d = !flage ? sort_data : search_result;
		orderBy("companyName", w, d);
	});
	
	$("#search").keyup(function(){
		var search_value = $("#search").val();
		_search(search_value);
		$(".c_details_content").empty();
		for(var i = 0, j = search_result.data.length; i < j; i ++){
			var temp = search_result.data[i] ;
			var id = temp.id ;
			var t = temp.telephone;
			var e = temp.uemail;
			var n = temp.companyName;
			showData(id, t, e, n) ;
		}
		flage = true;
	});
	
	function _search(search_value){
		search_result.data.length = 0;
		for(var i = 0, j = sort_data.data.length; i < j; i ++){
			var temp = sort_data.data[i];
			var companyName = temp.companyName;
			if(companyName.substring(0,search_value.length).toLowerCase().indexOf(search_value.toLowerCase()) > -1){
				//search_result.data.push(sort_data.data[i]);
				pushValue(search_result.data,sort_data.data[i]);
			}
			var uemail = temp.uemail;
			if(uemail.substring(0,search_value.length).toLowerCase().indexOf(search_value.toLowerCase()) > -1){
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
		d.data = (w != 8) ? d.data.sort(function(a,b){ return a[p] > b[p] ? 1 : -1; }) : d.data.sort(function(a,b){ return a[p] < b[p] ? 1 : -1; });
		var reports = d.data;
		$(".c_details_content").empty();
		for(var i=0,j=reports.length;i<j;i++){
			var temp = reports[i] ;
			var id = temp.id ;
			var t = temp.telephone;
			var e = temp.uemail;
			var n = temp.companyName;
			showData(id, t, e, n) ;
		}
	}
	
	function showData(cId, t, e, n){
		$(".c_details_content").append("<li class='li_defalut' id='"+cId+"'><div class='info_area p_info'>" + t
					+ "</div><div class='info_area e_info'>" + e
					+ "</div><div class='info_area n_info'>" + n
					+ "</div></li>");
	}
	
	var initData = function(){
		$.ajax({
			url : "/contact-list",
			type : 'GET',
			dataType : 'json',
			data : {"groupId" : $("input[name='groupId']").val(),"userId" : $("input[name='userId']").val()},
			success : function(e){
				if(e.result){
					sort_data = e;
				}
			}
		});
	}();

});

