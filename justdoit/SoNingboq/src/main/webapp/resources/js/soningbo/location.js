$(function() {

	var optInit = getPaginationOption();
	var locationNumber = $("input[name='locations_total']").val();
	if (locationNumber == 'undefined' || locationNumber == null
			|| locationNumber < 0) {
		locationNumber = 1;
	}
	
	$("#Pagination").pagination(locationNumber*1, optInit);

	function pageselectCallback(page_index, jq) {
		var pageNumber = page_index * 1 + 1;
		var pageSize = optInit.items_per_page * 1;
		if (page_index == 'undefined' || page_index == "") {
			pageNumber = 1;
		}

		$("#content_location .location").remove();
		var appUrl = "/admin/location/getData/" + pageNumber + "/" + pageSize;
		_backbone(appUrl, $('#content_location'), $('#template_location')
				.html());
		return false;
	}

	function getPaginationOption() {
		var opt = {
			num_edge_entries : 1, // 边缘页数
			num_display_entries : 10, // 主体页数
			callback : pageselectCallback,
			items_per_page : 5,// 每页显示5项
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

	function _backbone(_url, location, template) {

		var Location = Backbone.Model.extend({});

		var LocationList = Backbone.Collection.extend({
			model : Location,
			url : '',
			initialize : function() {
				this.url = _url;
			}
		});

		var itemView = Backbone.View.extend({
			el : location,
			render : function() {
				var testItemTemplate = _
						.template(template, this.model.toJSON());
				return testItemTemplate;
			},
		});

		var listView = Backbone.View.extend({
			initialize : function() {
				this._List = new LocationList();
				this._List.bind('reset', this.addAll, this);
				this._List.fetch({
					silent : true,
					success : function(collection, response) {
						collection.reset(response);
					},
					error : function() {
					}
				});
			},
			addOne : function(json) {
				var view = new itemView({
					model : json
				});
				var str = "";
				str += view.render();
				location.append(str);
			},
			addAll : function() {
				this._List.each(this.addOne);
			}
		});
		var listView = new listView();
	}

});
