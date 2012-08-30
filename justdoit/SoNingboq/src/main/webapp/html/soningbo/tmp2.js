,
		showCategory2 : function(id) {
			$.get(apiurl + "/resource/category/second/show/" + id, function(json) {
				_this.appendData(json);
			});
		},
		appendData : function(v) {
			if (v == null || v == "undefined") {
				return;
			}
			$("#div_cat_two ul li").remove();
			var d = v.data, len = d.length;
			if (len > 0) {
				for ( var i = 0; i < len; i++) {
					_this.fillingData(d[i]);
				}
			} else {
				_this.fillingData(d);
			}
		},
		fillingData : function(item) {
			if (_this.language === "cn") {
				$("#get_height_two").append(
						"<li><a href='javascript:void(0);' id='"+item.id+"'>"
								+ item.name_cn + "</a></li>");
			} else {
				$("#get_height_two").append(
						"<li><a href='javascript:void(0);' id='"+item.id+""'>"
								+ item.name_en + "</a></li>");
			}

		},