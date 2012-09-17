$(function() {
	'use strict';
	
	$('.fileinput-button').click(function() {
		$('.hide_file_button').click();
	});
	
	// Initialize the jQuery File Upload widget:
	$('#fileupload').fileupload({
		maxNumberOfFiles : 5,
		maxFileSize : 2000000,
		acceptFileTypes : /(\.|\/)(gif|jpe?g|png)$/i,
		previewAsCanvas : false,
		previewMaxWidth : undefined,
		previewMaxHeight : undefined
	});

	// Enable iframe cross-domain access via redirect option:
	$('#fileupload').fileupload('option', 'redirect',
			window.location.href.replace(/\/[^\/]*$/, '/cors/result.html?%s'));

	// Load existing files:
	$('#fileupload').each(function() {
		var that = this;
		$.getJSON(this.action, function(result) {
			if (result && result.length) {
				$(that).fileupload('option', 'done').call(that, null, {
					result : result
				});
			}
		});
	});

});
