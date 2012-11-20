$(function(){
	var imgMd5 = $("input[name='photo_path']").val() ;
	$("#location_header_image").attr("src",getImagePath(imgMd5,"http://api.soningbo.com"));
	
	
	$("#select_FirstCategory").change(function(){
		var ids = $(this).children(":selected").attr("id");
		
		$.getJSON('/admin/location/first/'+ids,function(data){
			if(data == null || data == 'undefined'){
				alert("No Data.") ;
				return;
			}else{
				$("#select_SecondCategory option").remove() ;
				for(var i=0,j=data.length;i<j;i++){
					var json = data[i] ;
					$("#select_SecondCategory").append("<option id='"+json.id+"' value='"+json.id+"'>"+json.name_cn+"</option>") ;
				}
			}
			
		});
	}) ;
	
	$("#infowrap").tabs();
	/**打开对话框*/
	$("#show_location_images").click(function(){
		$("#dialog_location_images").dialog("open");
	});
	
	$("#show_locationExt_infor").click(function(){
		$("#dialog_locationExt_infor").dialog("open");
	});
	$("#dialog_location_images").dialog({
		autoOpen : false,
		title:"选择图片上传",
		height : 300,
		width : 600,
		modal : true,
		buttons : {
			"确定" : function(){
				
				$(this).dialog("close");
			},
			"取消" : function() {
				$(this).dialog("close");
			}
		},
		close : function() {
		}
	});
	
	$("#dialog_locationExt_infor").dialog({
		autoOpen : false,
		title:"位置扩展信息",
		height : 300,
		width : 600,
		modal : true,
		buttons : {
			"确定" : function(){
				
				$(this).dialog("close");
			},
			"取消" : function() {
				$(this).dialog("close");
			}
		},
		close : function() {
		}
	});
	
});



function getImagePath(imgPath,apiurl) {
	var imgUrl = "";
	if (typeof (imgPath) == 'undefined' || null == imgPath
			|| '0' == imgPath || imgPath == '') {
		imgUrl = "/images/shop_photo_gray.gif";
	} else {
		imgUrl = apiurl + "/upload/" + imgPath.substring(0, 4) + "/"
				+ imgPath.substring(4, 8) + "/"
				+ imgPath.substring(8, 12) + "/"
				+ imgPath.substring(12) + "-100x100";
	}
	return imgUrl;
}