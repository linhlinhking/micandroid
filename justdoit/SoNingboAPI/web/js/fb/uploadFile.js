function reload() {
	window.location.reload();
}

function getUploadUrl() {
	//请替换成自己的上传url
	return "http://freebao.com/uploadAjax/uploadProfile.html";
}

function uploadSuccessHandler(ajaxResult){
	if($.parseJSON(ajaxResult).OK){
		alertTip($.parseJSON(ajaxResult).OK, $.parseJSON(ajaxResult).message);
		window.location.reload();
	}else{
		alertTip($.parseJSON(ajaxResult).OK, $.parseJSON(ajaxResult).message);
	}
}

function uploadFailHandler(ajaxResult) {
	if(!$.parseJSON(ajaxResult).OK){
		alertTip($.parseJSON(ajaxResult).OK, $.parseJSON(ajaxResult).message);
		$("#userFacePath").attr("src",$.parseJSON(ajaxResult).imgPath);
	}
	window.location.reload();
	//alert("上传失败，回调方法请写在这里");
}

//大头像：宽
function getBigAvatarWidth() {				
	return 150;
}

//大头像：高
function getBigAvatarHeight() {				
	return 150;
}

//中头像：宽
function getMiddleAvatarWidth() {				
	return 75;
}

//中头像：高
function getMiddleAvatarHeight() {				
	return 75;
}

//小头像：宽
function getLittleAvatarWidth() {				
	return 50;
}

//小头像：高
function getLittleAvatarHeight() {				
	return 50;
}

//裁剪框：宽
function getCutRectWidth() {				
	return 150;
}

//裁剪框：高
function getCutRectHeight() {				
	return 150;
}

//截取框是否可缩放
function getCutRectScalable() {				
	return "true";
}

//自定义参数
function getCustomParams(){
	return "type_user";
}

