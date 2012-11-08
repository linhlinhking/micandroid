$(function() {
	$("#infowrap").tabs();

	$("#doSearchBtn").click(function() {
		var searchVal = $("input[name='searchValue']").val();
		if ("" == searchVal || null == searchVal || "undefined" == searchVal){
			alert("请输入要搜索的位置信息或者关键字.");
			return ;
		}
		
		
	});
});
