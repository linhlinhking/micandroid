// 判断个浏览器 begin
var ua = navigator.userAgent.toLowerCase();
// 判断个浏览器 end
function counttxt(obj, i) {
	var totle;
	obj.focus(function() {
				totle = obj.val().length;
				$('#counttxt').text(i - totle);
			});
	obj.keyup(function() {
				totle = obj.val().length;
				if (totle <= i) {
					$('#counttxt').text(i - totle);
				} else {
					obj.val(obj.val().substr(0, i));
				}
			});
	$(document).mousemove(function() {
		totle = obj.val().length;
		if (totle <= i) {
			$('#counttxt').text(i - totle);
		} else {
			obj.val(obj.val().substr(0, i));
		}
	});
}

// 主搜
function preSearch(searchTxt) {
	if (searchTxt == null) {
		$("#searchtext").val($.trim($("#searchtext").val()) == $
				.trim($("#searchtext").attr("defaultValue")) ? "" : $
				.trim($("#searchtext").val()));
	} else {
		$("#searchtext").val($.trim(searchTxt));
	}
	// $("#searchForm").append($("#operId"));
	$("#searchForm").submit();
}

// 分页
function changePage(obj, page) {
	$(window).scrollTop(0);
	if (checknumber(page) == false || page <= 0) {
		page = 1;
	}
	$("#toPage").val(page);
	var fun = $(obj).parents("div [name='fun']").attr("method");
	var funStr = fun + "(" + page + ")";
	eval(funStr);
}

// 私信验证
function letterInitValidate() {
	$.formValidator.initConfig({
				formid : "sendMsgForm"
			});
	$("#nicheng").formValidator({
				onshow : messageObj['message_receiver'],
				onfocus : messageObj['message_receiver'],
				oncorrect : Lang['input_correct']
			}).functionValidator({
				fun : function(val, elem) {
					if (val == "") {
						return messageObj['message_nickname_null'];
					} else {
						var msg = "";
						var param = {
							"user.nickname" : $.trim(val)
						};
						$.ajax({
									type : "POST",
									dataType : "json",
									url : "../indexAjax/checkNickName.html",
									cache : false,
									data : param,
									async : false,
									success : function(ajaxResult) {
										if (ajaxResult.OK) {
											msg = messageObj['message_nickname_none'];
										}
									},
									clearForm : true
								});
						if (msg != "") {
							return msg;
						}
					}
					return true;
				}
			});

	$("#sendarea").formValidator({
				onshow : messageObj['message_body'],
				onfocus : messageObj['message_body'],
				oncorrect : Lang['input_correct']
			}).functionValidator({
				fun : function(val, elem) {
					if (val == "") {
						return messageObj['message_body_none'];
					} else {
						return true;
					}
				}
			});
}

function logout() {
	doAjax("../userAjax/logout.html", null, function(ajaxResult) {
				if (ajaxResult.OK) {
					window.location = "../user/welcome.html";
				} else {
					alert(ajaxResult.message);
				}
			});
}
function goBack() {
	window.location = "../user/welcome.html";
}

function backHome() {
	window.location = "../user/welcome.html";
}

// 计算输入字数
function changeMsgSize() {
	var msg = $.trim($("#sendMsg").val());
	if (msg.length > 300) {
		alertTip(false, Lang['contentTooLong']);
		$("#sendMsg").val(msg.substr(0, 300));
		$("#msgLeftCount").html(0);
	} else {
		$("#msgLeftCount").html(300 - msg.length);
	}
}

// 发私信
function sendPrivateMessage() {
	if (!$.formValidator.pageIsValid('1')) {
		return false;
	}
	var receiver = $("#nicheng").val();
	var msgBody = $("#sendarea").val();
	var params = {
		"message.toNickName" : receiver,
		"message.msgBody" : msgBody
	};
	var url = "../messageAjax/addMessage.html";
	doAjax(url, params, function(ajaxResult) {
				alertTip(ajaxResult.OK, ajaxResult.message);
				closeMessageBox();
			});
}

function checknumber(str) {
	var reg = /^[0-9]*$/;
	return (reg.test(str));
}
// 清空输入域
function trimInput(obj) {
	$(obj).val("");
}
// check系统中所有ID
function checkID(id) {
	if (id != "") {
		if (!isIntege(id) || id > 100000000000) {
			return false;
		}
	}
	return true;
}
// 是否是整数
function isIntege(str) {
	return new RegExp(regexEnum.intege).test(str);
}
// 获取字符串字节数
String.prototype.getBytesLength = function() {
	return this.replace(/[^\x00-\xff]/gi, "--").length;
}

// 只能是数字、26个英文字母或者下划线
function isValidatText(str) {
	return new RegExp(regexEnum.text).test(str);
}

// 是否是中文
function isChinese(str) {
	return new RegExp(regexEnum.chinese).test(str);
}

// 是否是邮箱
function isEmail(str) {
	return new RegExp(regexEnum.email).test(str);
}

// 是否是邮编
function isZip(str) {
	return new RegExp(regexEnum.zipcode).test(str);
}

// 是否是手机
function isMobile(str) {
	return new RegExp(regexEnum.mobile).test(str);
}

// 是否是手机
function isPhone(str) {
	return new RegExp(regexEnum.tel).test(str);
}

// 是否是密码
function isPassword(str) {
	return new RegExp(regexEnum.password).test(str);
}

// 是否是空
function isNotempty(str) {
	return new RegExp(regexEnum.notempty).test(str);
}

function isNotNull(str) {
	if (trim(str).length == 0)
		return false;
	return true;
}
/**
 * 检验一个金额的字段是否合法：满足整数或者是一位或两位小数且非负
 * 
 * @param amount
 * @return
 */
function validateAmount(amount) {
	var amountPattern = /^\d{1,18}(\.\d{1,2})?$/;//
	return amountPattern.test(amount);
}

// 只含有汉字、数字、字母、下划线不能以下划线开头和结尾
function chineseorletter(str) {
	return new RegExp(regexEnum.chineseorletter).test(str);
}
function checkCharacter(str, min, max) {
	var c = str.length;
	if (c > max || c < min) {
		return false;
	}
	return true;
}

function checkLength(str, min, max) {
	var c = getLength(str);
	if (c > max || c < min) {
		return false;
	}
	return true;
}
function getLength(strTemp) {
	var i, sum;
	sum = 0;
	for (i = 0; i < strTemp.length; i++) {
		if ((strTemp.charCodeAt(i) >= 0) && (strTemp.charCodeAt(i) <= 255))
			sum = sum + 1;
		else
			sum = sum + 2;
	}
	return sum;
}

function checkcharacter(str) {
	var reg = /^[`~!@#$%^&*()=\'%<>&,.?;:|\"～！◎＃￥％……※×（）——＋§]*$/;
	return (reg.test(str));
}

function trim(string) {
	if (string != null && string != undefined && string.length > 0) {
		return string.replace(/(^\s*)|(\s*$)/g, "");
	}
	return "";
}

function CheckValidChar(sm_name, max_sm_name_len) {
	var i;
	sm_name = trimString(sm_name);
	if (sm_name == "")
		return 1;
	if (sm_name.length > max_sm_name_len)
		return 2;

	if (sm_name.indexOf("'") != -1)
		return 3;

	for (i = 0; i < sm_name.length; i++) {
		if (navigator.appName == "Netscape") {
			if (sm_name.charCodeAt(i) < 0)
				return 0;
		} else if (sm_name.charAt(i) > String.fromCharCode(0x7f))
			return 0;
	}

	return 0;
}

function gotoURL(obj) {
	window.open($(obj).attr("title"), "bank");
}

function checkHtml(targetStr) {
	var valid = "<>&'\\\"#";
	var flag = false;
	for (i = 0; i <= (targetStr.length - 1); i++) {
		if (valid.indexOf(targetStr.charAt(i)) >= 0) {
			flag = true;
			break;
		}
	}
	return flag;
}

function checkForFSH(targetStr) {
	var valid = "~!@#$%^&*:\"[]{}+-<>?|\'_,，;./～！◎＃￥％……※×——＋§";
	var flag = false;
	for (i = 0; i <= (targetStr.length - 1); i++) {
		if (valid.indexOf(targetStr.charAt(i)) >= 0) {
			flag = true;
			break;
		}
	}
	return flag;
}

/**
 * construction a Map
 */
function formPost(url, paraJson) {
	$("body").append("<form id='__f' target='_self'></form>");
	$('#__f').attr('action', url);
	$('#__f').attr('method', 'post');
	$.each(paraJson, function(k, v) {
				$('#__f').append("<input type='hidden' name='" + k
						+ "' value='" + v + "' />");
			});
	// alert($('#__f').attr('action'));
	window.setTimeout((function() {
				$('#__f').submit();
			}), 0)
}

// 字符串操作
function convertStr(strs, str, oper) {
	var arrs;
	if (strs == "" || strs == null) {
		arrs = new Array();
	} else {
		arrs = strs.split(",");
	}
	if (oper == "+") {
		arrs.push(str);
		return arrs.toString();
	} else if (oper == "-") {
		for (var i = 0; i < arrs.length; i++) {
			if (arrs[i] == str) {
				arrs.splice(i, 1);
				return arrs.toString();
			}
		}
	}
	return arrs.toString();
}

// 点击头像个人主页
function skipYourHome(userId) {
	var url = "../user/otherHome.html";
	var param = {
		"userId" : userId
	};
	formPost(url, param);
}
// 点击粉丝、关注、微薄跳转个人主页
function skipYourHome2(userId, methodName) {
	var url = "../user/otherHome.html";
	if (isNotempty(methodName)) {
		var param = {
			"userId" : userId,
			"methodName" : methodName
		};
		formPost(url, param);
	}
}

function feedback() {
	$("#weibotext").trigger("focus");
	$("#weibotext").val("#freebao#");
	$(document).scrollTop(0);
}

function skipHome(methodName) {
	if (location.href == "http://freebao.com/user/welcome.html") {
		methodName();
		return;
	}
	var url = "../user/welcome.html";
	var param = {
		"methodName" : methodName.name
	};
	formPost(url, param);
}
// loading
$(function() {
			$('#loading').ajaxStart(function() {
						$(this).show();
					}).ajaxStop(function() {
						$(this).hide();
					});
		})

function addOperId(params) {
	if (params == null) {
		params = {};
	}
	params.operId = $("#operId").val();
	return params;
}

function doAjax(url, params, funSuccess) {
	params = addOperId(params);
	$.ajax({
				type : "POST",
				dataType : "json",
				url : url,
				cache : false,
				data : params,
				beforeSend : disAjaxLoading,
				success : funSuccess,
				complete : hideAjaxLoading,
				clearForm : true
			});
}
function doAsyncAjax(url, params, funSuccess) {
	params = addOperId(params);
	$.ajax({
				type : "POST",
				dataType : "json",
				url : url,
				cache : false,
				async : false,
				data : params,
				beforeSend : disAjaxLoading,
				success : funSuccess,
				complete : hideAjaxLoading,
				clearForm : true
			});
}
function doAsyncAjaxHtml(url, params, funSuccess) {
	params = addOperId(params);
	$.ajax({
				type : "POST",
				dataType : "html",
				url : url,
				cache : false,
				async : false,
				data : params,
				beforeSend : disAjaxLoading,
				success : funSuccess,
				complete : hideAjaxLoading,
				clearForm : true
			});
}
function doAjaxHtml(url, params, funSuccess) {
	params = addOperId(params);
	$.ajax({
				type : "POST",
				dataType : "html",
				url : url,
				cache : false,
				data : params,
				beforeSend : disAjaxLoading,
				success : funSuccess,
				complete : hideAjaxLoading,
				clearForm : true
			});
}

function disAjaxLoading() {
	$('#ajax_loading').show();
}
function hideAjaxLoading() {
	$('#ajax_loading').hide();
	$('.pageing input.go').css({'opacity':'0'});
}
function hideAjaxLoadingError() {
	//alert('系统异常...');
	$('#ajax_loading').hide();
}
function getAttrs(objs,attrName){
	var attrs = new Array();
	objs.each(function(index,obj){
		attrs.push($(obj).attr(attrName));
	});
	return attrs;
}

function contains(arr,obj){
	for(var i=0;i<arr.length;i++){
		if(arr[i]==obj){
			return true;
		}
	}
	return false;
}

function isUndefined(obj){
	if(obj == null || obj == ""||obj=="undefined"||obj==undefined||obj == 0 ||obj == "0"){
		return true;
	}
	return false;
}
function IsURL(str_url){ 
    var strRegex = "^((http|https|ftp|rtsp|mms)?://)?([\\w-]+\\.?)+[\\w-]+(/[\\w- ./?%&=#@~:()!_+-]*)?$";  
    var re=new RegExp(strRegex);  
    if (re.test(str_url)){ 
        return (true);  
    }else{  
        return (false);  
    } 
}
