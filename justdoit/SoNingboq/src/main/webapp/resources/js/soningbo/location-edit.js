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