var request = createXMLHttpRequest(); // 用户向服务器发起请求的XMLHttpRequest对象

function getFirstCategory(url,method) {
	request = createXMLHttpRequest();
	request.onreadystatechange = getFirstCategoryResponse;
	request.open("GET", "/proxy?url=" + url + "&method=" + method, true);
	request.send(null) ;
}

function getFirstCategoryResponse() {
	if (request.readyState == 4) {
		if (request.status == 200 || request.status == 0) {
			//document.write(request.responseText) ;
			var result = request.responseText;
			var json = eval("(" + result + ")");
			var len = json.firstCategory.length ;
			var parent1 = document.getElementById("category_list") ;
			var categoryString = "" ;
			for(var i=0;i<len;i++){
				//alert(json.firstCategory[i].name_cn) ;
				categoryString += "<li><a href='#'>"+ json.firstCategory[i].name_en +"</a>|</li>" ;
			}
			//alert(categoryString) ;
			parent1.innerHTML = categoryString ;
		} else {
			alert("Problem retrieving XML data");
		}
	}
}
/**
 * 向服务器发出添加好友请求
 * 
 * @param friendId
 *            要添加的好友id
 */
function getUser(url, method) {
	request = createXMLHttpRequest();
	request.onreadystatechange = getUserResponse;
	request.open("GET", "/proxy?url=" + url + "&method=" + method, true);
	request.send(null);
}
/**
 * 处理的服务器响应的回调函数 根据服务器的响应代码提示结果
 */
function getUserResponse() {
	// alert(request.readyState) ;
	// 已经完成请求
	if (request.readyState == 4) {
		alert(request.status);
		if (request.status == 200 || request.status == 0) {
			// document.write(request.responseText) ;
			var result = request.responseText;
			var json = eval("(" + result + ")");
			alert(json.username);
			// alert("success") ;
		} else {
			alert("Problem retrieving XML data");
		}
	}
}
/**
 * 创建XMLHttpRequest对象（考虑到不同浏览器的兼容性问题）
 * 
 * @return 如果创建成功，返回XMLHttpRequest对象，否则抛出异常
 */
function createXMLHttpRequest() {
	if (typeof XMLHttpRequest != "undefined") {
		return new XMLHttpRequest();
	} else {
		if (typeof ActiveXObject != "undefined") {
			return new ActiveXObject("Microsoft.XMLHTTP");
		} else {
			throw new Error("XMLHttpRequest not supported");
		}
	}
}
