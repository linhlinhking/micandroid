var location_id = $("input[name='locationid']").val();
alert(location_id);
$.ajax({
	url : apiurl + "/resource/locationExt/add",
	type : "post",
	beforeSend : function() {
	},
	dataType : "json",
	data : _this.sendLocationExtData(location_id),
	success : function(json) {
		if (json.result == "true") {
			window.location.href = "my-place-dp/" + location_id;
		} else {

		}
	}
});