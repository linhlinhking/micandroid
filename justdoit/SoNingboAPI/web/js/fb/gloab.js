$(function(){
	defaultVNo($('#searchtext'));
	//$('.liaoNeiRong').jScrollPane();
	$('#TCshare').css({'opacity':0});
	countt($('#TCtarea'),140);
	s1FBi();
	$('.submenu .inner dl dd:last a').css({'border':'none'});
});
//显示FB积分的说明
function s1FBi(){
	var s1showInf=$('#s1-showInf');
	$('.s1-FBi').hover(function(){
		s1showInf.show();
	},function(){
		s1showInf.hide();
	});
}
//控制说明输入字数
function countt(obj, i) {
	var totle;
	obj.focus(function() {
				totle = obj.val().length;
				$('.TCbtn span').text(i - totle);
			});
	obj.keyup(function() {
				totle = obj.val().length;
				if (totle <= i) {
					$('#.TCbtn span').text(i - totle);
				} else {
					obj.val(obj.val().substr(0, i));
				}
			});
	}

function addMemberTOBlackList(userId, success_callback){
	var url = "../blacklistAjax/addBlackMember.html";
	var param ={
				"query.blackUid":userId,
				"query.type":1
				};
	doAjax(url, param, success_callback);
}

function deleteMemberFromBlackList(userId,targetTeamId,success_callback){
	var url = "../blacklistAjax/deleteBlackMember.html";
	var params = {
			"query.blackUid":userId,
			"targetTeamId":targetTeamId,
			"query.type":1
	}
	doAjax(url, params, success_callback);
}

//switchTou更换头像
function switchTou(){
	var touImg=$('.userInfo dt img'),curIndex;
	$('.switchTou a').each(function(index){
		$(this).mouseover(function(){
			touImg.attr('src',$(this).attr('u_path'));
			$(this).addClass('cur').siblings().removeClass('cur');
		});
	}).mouseout(function(){
		touImg.attr('src',$(this).attr('u_path'));
		$(this).addClass('cur').siblings().removeClass('cur');
	});
}

function setFacePath(obj){
	$(obj).addClass('cur').siblings().removeClass('cur');
	var url = "../userAjax/updateFacePath.html";
	var params = {"user.facePath":$(obj).attr('u_path')};
	doAjax(url, params, function(ajaxResult){
		if(!ajaxResult.OK){
			alertTip(ajaxResult.OK, ajaxResult.message);
		}
		if($(obj).attr('type')=="personal"){
			document.location = "http://freebao.com/"+$('#current_user_url').val();
		}else if($(obj).attr('type')=="userInfo"){
			document.location = "../userInfo/toEditProfile.html";
		}
		
	});
}

function deleteFacePath(obj){
	var url = "../userAjax/deleteUserFace.html";
	var params = {"userXFace.facepath":$(obj).next().find('img').attr('src')};
	doAjax(url, params, function(ajaxResult) {
		if(!ajaxResult.OK){
			alertTip(ajaxResult.OK, ajaxResult.message);
		}
		if($(obj).attr('type')=="personal"){
			document.location = "http://freebao.com/"+$('#current_user_url').val();
		}else if($(obj).attr('type')=="userInfo"){
			document.location = "../userInfo/toEditProfile.html";
		}
	});
}

var bgpathStr = "";
//换肤
function huanFu(){
	$('#uploadBG').css({'opacity':0});
	var bgs=$('.huanFu'),height=$(document).height(),greyBg=$('<div class="greyBg" />'),body=$('body');
	$('.hbgBtn,.setting p a.setBgBtn').click(function(){
		greyBg.appendTo('body').css({'height':height});
		bgs.css('top',$(window).scrollTop()+50).fadeIn(50);
		$('.upBgDl input[type="checkbox"]').attr('checked','true');
	});
	$(window).scroll(function(){
		height=$(document).height();
		greyBg.css({'height':height});
	});
	$('.huanFu ul li a').click(function(){
		var src=$('img',this).attr('src');
		bgpathStr = src;
		body.css({'background-image':'url('+src+')'});
	});
	$('.huanFu .bgs h3 a,.conBgs a.noconBg').click(function(){
		findAppBg($("#operId").val());
		greyBg.remove();
		bgs.hide();
	});
	$('.conBgs a.conBg').click(function(){
		updateUserBG();
		greyBg.remove();
		bgs.hide();
	});
}
//设置背景
function updateUserBG(){
	var url = "../userAjax/updateAppBg.html";
	var params = {"user.bgpath":bgpathStr};
	doAjax(url, params, function(ajaxResult){
		if(!ajaxResult.OK){
			alertTip(ajaxResult.OK, ajaxResult.message);
		}else{
			findAppBg($("#operId").val());
		}
	});
}
//自定义背景
function uploadBackground(){
	var options = {
		url : "../background/uploadBackground.html",
		type : "POST",
		dataType : "script",
		success : function(msg) {
			if(ua.indexOf("chrome") != -1){
				msg = $(msg).text();
			}
			if (msg.indexOf("#") > 0) {
				var data = msg.split("#");
				alertTip(true, data[0]);
				$('body').css({'background-image':'url('+data[1]+')'});
				bgpathStr = data[1];
				$(".huanFu .upBgDl img").attr("src",data[1]);
				$('.upBgDl input[type="checkbox"]').click(function(){
					if(this.checked){
						$('body').css({'background-image':'url('+data[1]+')'});
					}else{
						$('body').css({'background-image':'url('+findAppBg($("#operId").val())+')'});
					}
				});
			}else{
				alertTip(false, msg);
			}
		}
	};
	$("#bgForm").ajaxSubmit(options);
	return false;
}
//查询应用背景
function findAppBg(userId){
	if(userId != ""){
		var url = "../userAjax/findAppBg.html";
		var params = {"inviteId":userId};
		doAjax(url, params, function(ajaxResult){
			if(ajaxResult.OK){
				$('body').css({'background-image':'url('+ajaxResult.resultMap.bgpath+')'});
			}
		});
	}
}

//加载背景图片
function loadBgList(toPage){
	var url = "../background/sysBgList.html";
	var params = {"query.toPage":toPage};
	doAjaxHtml(url, params, function(response) {
		var obj=$(response);
		if(obj.find("#totalItem").attr("value")!=0){
			$(".huanFu .bgScrollDiv").html(obj);
			$(".huanFu .bgScrollDiv").attr('method', "loadBgList");
			$(".huanFu .bgScrollDiv").attr('name', "fun");
		}
		else{
			$(".bgs").find("h4").hide();
			}
		huanFu();
	});
}

//输入框聚焦默认defaultValue消失
function defaultVNo(obj){
	obj.focus(function() {
		if (this.value == '' || this.value == this.defaultValue) {
			this.value = '';
		}
	}).blur(function() {
		if (this.value == '' || this.value == this.defaultValue) {
			this.value = this.defaultValue;
		}
	});
}
function matchTopic(topicObj){
	topicObj.each(function(index){
		$(this).click(function(){
			var pattern = /#/g;
			var str = $(this).html().replace(pattern,"");
			preSearch(str);
		});
	});
}

function matchAtUser(atUserObj){
	atUserObj.each(function(index){
		$(this).mouseover(function(){
			var nicknameStr = $(this).attr('usercard_name');
			if(nicknameStr != ""){
				checkNickname(nicknameStr, function(ajaxResult) {
					if (ajaxResult.OK) {
						alertTip(false,userObject.user_nickname_notExist);
					}
				});
			}else{
				alertTip(false,userObject.user_nickname_notExist);
			}
		}).click(function(){
			var text = $(this).html();
			var nicknameStr = $(this).attr('usercard_name');
			checkNickname(nicknameStr, function(ajaxResult) {
				if (ajaxResult.OK) {
					preSearch(text);
				}else{
					document.location = $(this).attr('href');
				}
			});
		});
	});
}

function checkNickname(nicknameStr,success_back){
		var params = {
			"user.nickname" : $.trim(nicknameStr)
		};
		var url = "../indexAjax/checkNickName.html";
		doAsyncAjax(url, params, success_back);
}
function GtxtFocus(obj){
	obj.focus(function(){
		$(this).addClass('txtFocus');
	});
	obj.blur(function(){
		$(this).removeClass('txtFocus');
	});
	
}
$(document).ready(function(){
	$('body').append('<div class="list popInfo"></div>');
});
//鼠标滑过显示个人简介
function popInfo(obj){
	var popInfo=$('.popInfo'),time;
	obj.hover(function(e){
		time=setTimeout(function(){
			var curObj = e.target;
			var url = "../user/userBrief.html";
			var params = {"inviteId" : $(curObj).parent().attr("uid")};
			doAjaxHtml(url, params, function(response) {
				var resultObj = $(response);
				popInfo.html(resultObj);
				if($(curObj).parent('a').attr('class') !="" && $(curObj).parent('a').attr('class') != "recommend_user")
				{
					popInfo.find(".jiaquanzi").html($("#pymkc").html());
					$(".jiaquanzi input.haveC").click(function() {
						var teamObj = $(this);
						var userId = teamObj.parents("dd").attr("userid");
						var teamId = teamObj.attr("teamid");
						followFriend(userId, teamId, function(ajaxResult) {
							alertTip(ajaxResult.OK, ajaxResult.message);
						});
					});
					follow();
				}else{
					popInfo.find(".jiaquanzi").hide();
					popInfo.find(".follow").hide();
				}
			});
			popInfo.find('dl').show();
			popInfo.css({'left':e.pageX-5,'top':e.pageY-5}).show();
		},200);
	},function(){
		clearTimeout(time);
		popInfo.hide();
	});
	popInfo.hover(function(){
		popInfo.show();
	},function(){
		popInfo.hide();
	});
}
//btn移动上去和划出去的样式变化
function btnHover(){
	$('.btn').mouseover(function(){
		$(this).addClass('btnHover');
	}).mouseout(function(){
		$(this).removeClass('btnHover');
	});
}
function btnGreyHover(){
	$('.btnGrey').mouseover(function(){
		$(this).addClass('btnGreyHover');
	}).mouseout(function(){
		$(this).removeClass('btnGreyHover');
	});
}
var myTeamsObj;
$(function() {
			initMyTeams();
			// 控制透明
			$('.opacity').css({'opacity' : '0'});
			navtip();
			newsTip();
		});
// 注册时等待邮件loading效果
function loadEmail() {
	$('<div id="loading"><img  src="../images/load.gif" />londing…… </div><div class="grey"></div>')
			.appendTo($('body'));
	$('#loading').css({
				'position' : 'absolute',
				'left' : '50%',
				'margin-left' : '-160px',
				'top' : $(window).scrollTop() + $(window).height() / 2,
				'z-index' : '99991',
				'color' : '#FFf',
				'fontSize' : '16px',
				'lineHeight' : '16px'
			});
	$('#loading img').css({
				'float' : 'left',
				'marginRight' : '8px'
			});
	$('.grey').show().css({
				'height' : $(document).height(),
				'width' : $(document).width(),
				'background' : '#666',
				'position' : 'absolute',
				'top' : '0',
				'left' : '0',
				'z-index' : '9999',
				'opacity' : '0.2'
			});
}
function shutloadEmail() {
	$('#loading').remove();
	$('.grey').remove();
}
// alertTip
function alertTip(param, txt) {
	var left, $this, $create;
	$create = '<div class="alertTip"><span>' + txt + '</span><p>◆</p></div>';
	$('body').append($create);
	left = $('.alertTip').width() / 2, $this = $('.alertTip');
	if (param == false) {
		$this.addClass('erroralert');
	}
	$this.css({
				'marginLeft' : -left,
				'top' : $(window).scrollTop() + 43
			}).fadeIn(300).fadeOut(1000, function() {
				$this.remove();
			});
	$(window).scroll(function() {
				$this.css({
							'marginLeft' : -left,
							'top' : $(window).scrollTop() + 43
						});
			});
}
// 轮循通知
function notify() {
	$.ajax({
		type : "POST",
		dataType : "json",
		url : "../notificationAjax/notify.html",
		cache : false,
		data : null,
		success : function(ajaxResult) {
			if (ajaxResult.OK) {
				var userId = ajaxResult.resultMap.userId;
				
				var wCount = ajaxResult.resultMap.contentIds.length;
				var mCount = ajaxResult.resultMap.message.length;
				var fCount = ajaxResult.resultMap.fan.length;
				var cCount = ajaxResult.resultMap.comment.length;
				var atMeContentCount = ajaxResult.resultMap.atMeContent.length;
				
				var count = wCount + mCount + fCount + cCount+atMeContentCount;
				var liStr = ""
				if (count > 0) {
					$("#notiCount").html(count);
					$(".newsCont").addClass("havNews");
					if (mCount > 0) {
						liStr += "<li><a href='http://freebao.com/message/list.html'>"
								+ mCount
								+ notification['notify_new_msg']
								+ "</a></li>"
					}
					if (fCount > 0) {
						liStr += '<li><a href="#" onclick=skipYourHome2('
								+ userId + ',"myFansList")>' + fCount
								+ notification.notify_new_fans + '</a></li>'
					}
					if (wCount > 0) {
						liStr += "<li><a href='http://freebao.com'>" + wCount
								+ notification['notify_new_weibo']
								+ "</a></li>"
					}
					if (cCount > 0) {
						liStr += "<li><a href='http://freebao.com/comment/toMyCommentPage.html'>"
								+ cCount
								+ notification['notify_new_comments']
								+ "</a></li>"
					}
					if (atMeContentCount > 0) {
						liStr += "<li><a href='http://freebao.com/atinfo/toAtMePage.html'>"
								+ atMeContentCount
								+ notification['notify_new_atContent']
								+ "</a></li>"
					}
				} else {
					$("#notiCount").html(0);
					$(".newsCont").removeClass("havNews");
					liStr += "<li>" + notification['notify_no'] + "</li>"
				}
				$(".newsUl").html(liStr);
				setTimeout(notify, 5000);// 一秒钟一次
			}
		},
		clearForm : true
	});
}

function findHotTopic(hotTopicSize, suc) {
	var url = "../topicAjax/findHotTopic.html";
	var params = {
		"hotTopicSize" : hotTopicSize
	};
	doAjax(url, params, suc);
}

/*function loopTpoic() {
	findHotTopic(5, function(ajaxResult) {
				var topics = ajaxResult.resultMap.topics;
				var leng = topics.length;
				function changeTxt() {
					leng--;
					if (leng < 0) {
						leng = topics.length - 1;
					}
					$("#searchtext").val(topics[leng].topicName);
				}
				if (leng > 0) {
					setInterval(changeTxt, 15000);
				}
			});
}*/

function initMyTeams(){
	findUserTeams(function(ajaxResult){
		myTeamsObj = ajaxResult.resultMap.userTeams;
	});
}

function findUserTeams(suc) {
	doAsyncAjax("../teamAjax/findUserTeams.html", null, suc);
}
function toAtMePage() {
	document.location = "../atinfo/toAtMePage.html";
}

function toMyCommentPage() {
	document.location = "../comment/toMyCommentPage.html";
}

function toInvitePage() {
	document.location = "../invite/toInvitePage.html";
}

function toMyHomePage() {
	document.location = "../user/welcome.html";
}

function toMyFavoritePage() {
	document.location = "../favorite/toMyFavoritesPage.html";
}

// 有新消息的提醒
function newsTip() {
	$('.newsCont').click(function() {
				$('.newsTip').addClass('newsTipcur');
				$('.newsTip .shut').show();
				$(".newsCont").removeClass("havNews");
			});
	$('.newsTip .shut').click(function() {
				$('.newsTip').removeClass('newsTipcur');
				$(this).hide();
			});
}
// nav主菜单tip小提示
function navtip() {
	$('.nav a').hover(function() {
				$('span', this).show();
			}, function() {
				$('span', this).hide();
			});
	// 弹出个人设置选项
	$('.set2').hover(function() {
			var $this=$(this);
			$this.addClass('set2cur');
				$p = $(this).next('p');
				$p.show().hover(function() {
							$p.show();
							$this.addClass('set2cur');
						}, function() {
							$p.hide();
							$this.removeClass('set2cur');
						});
			}, function() {
				$p.hide();
				$(this).removeClass('set2cur');
			});
}

