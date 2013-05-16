
	$(function(){
		alert(getServerDate());
	});

	function getServerDate(){
		var http = new XMLHttpRequest;   
		http.open("HEAD", ".", false);   
		http.send(null);   
		//alert(new Date(http.getResponseHeader("Date")).toLocaleString());
		return new Date(http.getResponseHeader("Date")).toLocaleString());
	}