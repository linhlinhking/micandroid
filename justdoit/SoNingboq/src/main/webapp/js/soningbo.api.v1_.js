(function($) {
	$.fn.searchningbo_api = function() {};

	$.searchningbo_api = {
		api : function(options) {
			return new this.make_api_call(options, this);
		},

		make_api_call : function(options, obj,method) {
			this.settings = {
				'callback' : false,
				'params' : {},
				'method' : method,
				'format' : 'json',
				'dataType' : 'jsonp',
				'api_url' : 'http://192.168.0.105:8080/',
				'version' : 1,
			};

			var self = obj;
			var instance = this;

			this.settings = $.extend(this.settings, options);

			if (jQuery.inArray(this.settings.format, [ 'json', 'xml', 'rss',
					'atom' ]) == -1) {
				return false;
			} // check format

			var method_url = self.get_method(this.settings);
			
			$.ajax({
				url : this.settings.api_url + method_url,
				type : 'GET',
				dataType : this.settings.dataType,
				async : true,
				data : this.settings.params,
				context : self,
				success : function(xhr) {
					if (typeof instance.settings.callback === 'function') {
						instance.settings.callback(xhr);
					}
				}
			});

		},

		get_method : function(settings) {
			var url = settings.method ;
			//var without_format = [ 'users/profile_image' ];

			//if (jQuery.inArray(settings.method, without_format) != -1) {
			//	url = settings.method;
			//} else {
			//url = settings.method + '.' + settings.format;
			//}

			return url;
		},
	};

})(jQuery);
