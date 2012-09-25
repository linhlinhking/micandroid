$(function() {

	$("#add-staffPicks-dialog-form").dialog({
		autoOpen : false,
		height : 480,
		width : 960,
		modal : true,
		buttons : {
			"保存" : function() {

			},
			"取消" : function() {
				$(this).dialog("close");
			}
		},
		close : function() {
		}
	});

	$("#user-add-staffPicks").click(function() {
		$("#add-staffPicks-dialog-form").dialog("open");
	});
	
	//before submit ,please validate .
	var v = $("#eventsCategory option:selected").val();
	if (v == '0') {
		//alert("请选择一个类别！");
		return false;
	}
});
