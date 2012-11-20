$(function() {
	$(".location_count").tabs();
	
	var currentFirstId = $("input[name='current_first_id']").val();
	var currentPage = $("input[name='location_current_page']").val();
	var locationTotal = $("input[name='locations_total']").val();
	var totalPage = $("input[name='location_total_page']").val();
	var firstId = $("input[name='current_first_id']").val();
	var secondId = $("input[name='current_second_id']").val();
	var currentDisplay = getDisplayPage(totalPage);
	var currentUrl = "/admin/location/first-category/"+firstId+"/second-category/"+secondId ;
	
	/**初始化图片*/
	$(".img_photo_path").each(function(){
		var path = getImageUrl(this.id) ;
		$(this).attr("src",path) ;
	});
	
	$("#location_pagable").paginate({
		count 		: totalPage,
		start 		: currentPage,
		display     : currentDisplay,
		url         : currentUrl,
		border					: false,
		text_color  			: '#79B5E3',
		background_color    	: 'none',	
		text_hover_color  		: '#2573AF',
		background_hover_color	: 'none', 
		images		: false,
		mouse		: 'press'
	});
	
	$("#delete_span a").die().live("click",function(){
		var md5Value = this.id ;
		$( "#dialog-delete-location" ).dialog({
	        resizable: false,
	        height:140,
	        modal: true,
	        buttons: {
	            "确定删除": function() {
	            	$.ajax({
	            		type:"POST",
	            		url:"/admin/location/delete",
	            		data:"md5Value=" + md5Value ,
	            		success:function(json){
	            			if(json.success){
	            				alert("删除成功.");
	            				setTemplateData("",optInit.current_page*1,optInit.items_per_page*1);
	            			}else{
	            				alert("删除失败.");
	            			}
	            		}
	            	}) ;
	                $( this ).dialog( "close" );
	            },
	            "取消": function() {
	                $( this ).dialog( "close" );
	            }
	        }
	    }).html("<span>确定要删除该位置信息？</span>");
	});
	

});

function getDisplayPage(totalPage){
	var defaultDisplay = 20 ;
	if(totalPage < defaultDisplay){
		defaultDisplay = totalPage ;
	}
	return defaultDisplay ;
}



/**设置动态图片路径*/
function getImageUrl(photo_path) {
	var imgUrl;
	if (photo_path == 'undefined' || "0" == photo_path || photo_path == null) {
		imgUrl = "/images/100x100.png";
	} else {
		imgUrl = "http://api.soningbo.com/upload/" + photo_path.substring(0, 4)
				+ "/" + photo_path.substring(4, 8) + "/"
				+ photo_path.substring(8, 12) + "/" + photo_path.substring(12)
				+ "-100x100";
	}

	return imgUrl;
}


