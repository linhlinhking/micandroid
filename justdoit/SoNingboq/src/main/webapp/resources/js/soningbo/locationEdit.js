$(function() {
	var default_per_page = 10 ;
	var optInit = getPaginationOption();
	var locationNumber = $("input[name='locationEdit_total']").val();
	if (locationNumber == 'undefined' || locationNumber == null
			|| locationNumber < 0) {
		locationNumber = 1;
	}

	$("#Pagination").pagination(locationNumber * 1, optInit);

	function pageselectCallback(page_index, jq) {
		var pageNumber = page_index * 1 + 1;
		
		var pageSize = optInit.items_per_page * 1;
		if (page_index == 'undefined' || page_index == "") {
			pageNumber = 1;
		}
		//$("#content_location .location").remove();
		return false;
	}

	function getPaginationOption() {
		var opt = {
			num_edge_entries : 1, // 边缘页数
			num_display_entries : 10, // 主体页数
			callback : pageselectCallback,
			items_per_page : default_per_page,// 每页显示5项
			prev_text : '上一页',
			next_text : '下一页'
		};
		return opt;
	}

	function getImageUrl(photo_path) {
		var imgUrl;
		if (photo_path == 'undefined' || "0" == photo_path
				|| photo_path == null) {
			imgUrl = "/images/100x100.png";
		} else {
			imgUrl = "http://api.soningbo.com/upload/"
					+ photo_path.substring(0, 4) + "/"
					+ photo_path.substring(4, 8) + "/"
					+ photo_path.substring(8, 12) + "/"
					+ photo_path.substring(12) + "-100x100";
		}

		return imgUrl;
	}

});
