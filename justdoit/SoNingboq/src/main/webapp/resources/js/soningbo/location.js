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
			var testItemTemplate = _.template(template, this.model.toJSON());
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
