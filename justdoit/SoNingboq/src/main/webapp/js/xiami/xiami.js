//检查是否安装软件
var xiamiInstalled = false;
var getBrowser = function() {
	var s = navigator.userAgent.toLowerCase();
	var a = new Array("msie", "firefox", "safari", "opera", "netscape");
	for ( var i = 0; i < a.length; i++) {
		if (s.indexOf(a[i]) != -1) {
			return a[i];
		}
	}
	return "other";
};

try {
	if (!xiamiInstalled) {
		var obj = new ActiveXObject("XiaMiplugin.xiamistart");
		if (obj) {
			xiamiInstalled = true;
			delete obj
		}
	}
} catch (e) {
}

function runxiami() {
	if (xiamiInstalled || getBrowser() == 'firefox') {
		return true;
	} else {
		window.location = '/software/shark';
		return false;
	}
};

function runxiami_p(p) {
	if (xiamiInstalled || getBrowser() == 'firefox') {
		window.location = p;
	} else {
		window.location = '/software';
	}
};

// 选择name 的check box
function selectAll(name) {
	var arr = $(':input[name=' + name + ']');
	for ( var i = 0; i < arr.length; i++) {
		if (arr[i].disabled == false) {
			arr[i].checked = true;
		}
	}
};

// 反选
function inverse(name) {
	var arr = $(':input[name=' + name + ']');
	for ( var i = 0; i < arr.length; i++) {
		if (arr[i].disabled == false) {
			arr[i].checked = !arr[i].checked;
		}
	}
};

// 获取选择的值
function getSelectValues(name) {
	var sValue = "";
	var tmpels = $(':input[name=' + name + ']');
	for ( var i = 0; i < tmpels.length; i++) {
		if (tmpels[i].checked
				|| (tmpels[i].type == 'hidden' && tmpels[i].defaultChecked)) {
			if (sValue == "") {
				sValue = tmpels[i].value;
			} else {
				sValue = sValue + "," + tmpels[i].value;
			}
		}
	}
	return sValue;
};

// 获取值
function getValues(name) {
	var sValue = "";
	var tmpels = $(':input[name=' + name + ']');
	for ( var i = 0; i < tmpels.length; i++) {
		if (sValue == "") {
			sValue = tmpels[i].value;
		} else {
			sValue = sValue + "," + tmpels[i].value;
		}
	}
	return sValue;
};

function thisMovie(movieName) {
	if (navigator.appName.indexOf("Microsoft") != -1) {
		if (navigator.appVersion.match(/9./i) != "9.") {
			return window[movieName];
		} else {
			return document[movieName];
		}
	} else {
		return document[movieName];
	}
	// return navigator.appName.indexOf("Microsoft") != -1 ? window[movieName] :
	// document[movieName];
};

var playerDialog;
function openMusicPlayer(str) {
	playerDialog = window.open("/song/play?ids=" + str, "xiamiMusicPlayer",
			'width=754,height=656');
}

function openPlayer(str) {
	window.open("/song/play?ids=" + str, "xiami",
			'scrollbars,width=720,height=645');
};

// function player_focus(){
// if(!playerDialog) playerDialog =
// window.open('',"xiamiMusicPlayer",'width=754,height=656');
// playerDialog.focus();
// }

function addSongs(str) {
	thisMovie('trans').addSongs(str);
}

function playsongs(checkname, type_name, type_id, cat_id) {
	var ids = getSelectValues(checkname);
	if (ids == '') {
		alert("没有歌曲可以播放!");
		return;
	}
	if (!type_name)
		type_name = 'default';
	if (!type_id)
		type_id = 0;
	if (!cat_id)
		cat_id = 0;
	if (cat_id) {
		addSongs(escape("/song/playlist/id/" + ids + "/object_name/"
				+ type_name + "/object_id/" + type_id + "/cat_id/" + cat_id));
	} else {
		addSongs(escape("/song/playlist/id/" + ids + "/object_name/"
				+ type_name + "/object_id/" + type_id));
	}
};

function playall(checkname, type_name, type_id) {
	var ids = getValues(checkname);
	if (ids == '') {
		alert("没有歌曲可以播放!");
		return;
	}
	if (!type_name)
		type_name = 'default';
	if (!type_id)
		type_id = 0;
	addSongs(escape("/song/playlist/id/" + ids + "/object_name/" + type_name
			+ "/object_id/" + type_id));
};

function playsongsignore(checkname, type_name, type_id) {
	var ids = getSelectValues(checkname);
	if (ids == '') {
		alert("没有歌曲可以播放!");
		return;
	}
	if (!type_name)
		type_name = 'default';
	if (!type_id)
		type_id = 0;
	addSongs(escape("/song/playlist/id/" + ids + "/object_name/" + type_name
			+ "/object_id/" + type_id));
};

// type_name : collect , album
function play(song_id, type_name, type_id) {
	if (!type_name)
		type_name = 'default';
	if (!type_id)
		type_id = 0;
	addSongs(escape("/song/playlist/id/" + song_id + "/object_name/"
			+ type_name + "/object_id/" + type_id));
};

function playalbum(album_id) {
	addSongs(escape("/song/playlist/id/" + album_id + "/type/1"));
};

function playartist(artist_id) {
	addSongs(escape("/song/playlist/id/" + artist_id + "/type/2"));
};

function playcollect(list_id) {
	addSongs(escape("/song/playlist/id/" + list_id + "/type/3"));
};

function playFriendRecommend(user_id) {
	addSongs(escape("/song/playlist/id/" + user_id + "/type/4"));
};

function playdefault() {
	addSongs(escape("/song/playlist/id/1/type/9"));
}

var ajaxText = '<div class="dialog_content"><p class="loading">虾小米正为您在处理数据, 请稍候...</p></div><a href="javascript:;" title="" onclick="closedialog();" class="Closeit">关闭</a>';

// ie6下高度的问题
var dialogbg = function() {
	if (getBrowser() == 'msie')
		$('.dialog_sharp').height($('.dialog_main').height());
};

var myjqmOnShow = function(hash) {
	hash.w.show();
	dialogbg();
};

var myjqmOnLoad = function(hash) {
	dialogbg();
};

function showDialog(url, target, ajaxText) {
	if (!target)
		target = "div.dialog_main";
	if (!ajaxText)
		ajaxText = '<div class="dialog_content"><p class="loading">虾小米正为您在处理数据, 请稍候...</p></div><a href="javascript:;" title="" onclick="closedialog();" class="Closeit">关闭</a>';
	if (!url)
		$('#dialog_clt .dialog_main').html(ajaxText);
	$('#dialog_clt').jqm({
		ajax : url,
		modal : true,
		toTop : true,
		target : target,
		ajaxText : ajaxText,
		onShow : myjqmOnShow,
		onLoad : myjqmOnLoad
	}).jqDrag('.jqDrag').jqmShow();
};

function collect(id) {
	var url = '/song/collect/id/' + encodeURIComponent(id);
	showDialog(url);
};

function zoneablum(id) {
	var url = '/zone/addablum/id/' + encodeURIComponent(id);
	showDialog(url);
}

function zoneCatergory(id, type) {
	var url = '/zone/editsort/id/' + encodeURIComponent(id) + '/type/'
			+ encodeURIComponent(type) + '/?' + Math.random();
	showDialog(url);
}

function tag(id, type) {
	var url = '/music/tag/type/' + encodeURIComponent(type) + '/id/'
			+ encodeURIComponent(id);
	showDialog(url);
};

function tagedit(id, type) {
	var url = '/music/tagedit/type/' + encodeURIComponent(type) + '/id/'
			+ encodeURIComponent(id);
	showDialog(url);
}

function closedialog() {
	$('#dialog_clt').jqmHide();
};

function insertsongs() {
	var url = '/search/popsongs?id=x';
	$('#dialog').jqm({
		ajax : url,
		modal : true,
		toTop : true,
		target : 'div.pop_message',
		ajaxText : ajaxText,
		onShow : myjqmOnShow,
		onLoad : myjqmOnLoad
	}).jqDrag('.jqDrag').jqmShow();
};

function search_songs(search_result, key) {
	var url = '/search/searchpopsongs';
	var pars = 'key=' + encodeURIComponent(key);
	var myAjax = new Ajax.Updater(search_result, // 更新的页面元素
	url, // 请求的URL
	{
		method : 'post',
		parameters : pars,
		evalScripts : true
	});
};

// 下载单曲
function download(id) {
	var url = '/download/song?id=' + encodeURIComponent(id);
	showDialog(url);
};

// chrome新版bug，onclick无法使用download命名的函数
function xm_download(id) {
	var url = '/download/song?id=' + encodeURIComponent(id);
	showDialog(url);
}

// 推广的下载单曲
function promotion_download(id, type, pid) {
	var url = '/download/song?id=' + encodeURIComponent(id) + '&ptype=' + type
			+ '&pid=' + pid;
	;
	showDialog(url);
};

// 下载专辑
function downloadalbum(id, type) {
	var url = '/download/song?id=' + encodeURIComponent(id) + '&type=album';
	showDialog(url);
};

// 参与主题精选集
function joinsub(id, type) {
	var url = '/collect/joinsub?id=' + encodeURIComponent(id) + "&type="
			+ encodeURIComponent(type);
	showDialog(url);
};

// 下载精选集
function downloadcollect(id, type) {
	var url = '/download/song?id=' + encodeURIComponent(id) + '&type=collect';
	showDialog(url);
};

function promotion_downloadsongs(ids, type, pid) {
	var url = '/download/song';
	var id = getSelectValues(ids);
	if (id == '') {
		alert("没有资源可以下载！");
		return;
	}
	var url = url + '?id=' + encodeURIComponent(id) + '&ptype=' + type
			+ '&pid=' + pid;
	showDialog(url);
};

// 下载多首歌曲
function downloadsongs(ids) {
	var url = '/download/song';
	var id = getSelectValues(ids);
	if (id == '') {
		alert("没有资源可以下载！");
		return;
	}
	var url = url + '?id=' + encodeURIComponent(id);
	showDialog(url);
};
// 推送多首歌曲
function sendsongs(ids) {
	var url = '/music/sendall';
	var id = getSelectValues(ids);
	if (id == '') {
		alert("没有资源可以发送！");
		return;
	}
	var url = url + '?id=' + encodeURIComponent(id);
	showDialog(url);
};

// 推荐
// 32,歌曲，33，专辑，34，艺人，35，精选集，36，歌曲评论，37，专辑评论，38，艺人评论， 39，精选集评论， 310， 小组话题，
// 311，用户， 312，小组
function recommend(id, type, sid) {
	var url = '/recommend/post';
	var url = url + '?object_id=' + encodeURIComponent(id) + "&type="
			+ encodeURIComponent(type) + "&t=" + 1000 * Math.random();
	if (sid)
		var url = url + '&sid=' + encodeURIComponent(sid);
	showDialog(url);
};

// 再推
function retui(id) {
	var url = '/recommend/feed/id/' + encodeURIComponent(id);
	showDialog(url);
};

// 分享
function share(url) {
	var url = '/feed/share/?url=' + encodeURIComponent(url);
	showDialog(url);
};

// 参与精选集
function addcollect(id) {
	var url = '/collect/addcollect';
	var url = url + '?cid=' + encodeURIComponent(id) + "&" + Math.random();
	showDialog(url);
};

// 专辑想要
function require(aid, type) {
	var url = '/album/require';
	var url = url + '?id=' + encodeURIComponent(aid) + "&type="
			+ encodeURIComponent(type);
	showDialog(url);
};

// 介绍给好友
// 33，专辑，35，精选集
function intro(id, type) {
	var url = '/member/intro';
	var url = url + '?object_id=' + encodeURIComponent(id) + "&type="
			+ encodeURIComponent(type);
	showDialog(url);
};

function friends(id) {
	var url = '/member/myfriends/t/new/to_user_id/' + encodeURIComponent(id);
	showDialog(url);
};

function attention(id, type) {
	var url = '/member/attention/from/ajax/type/' + encodeURIComponent(type)
			+ '/uid/' + encodeURIComponent(id);
	showDialog(url);
};

function blacklist(uid, nick_name) {
	if (!confirm(nick_name
			+ '将不能...\n- 关注你 (已关注的会自动取消关注) \n- 给你发站内信\n- 给你留言，回复你的分享等\n确定要把'
			+ nick_name + '加入黑名单吗？'))
		return;
	window.location = '/member/attention/uid/' + uid + '/type/3';
}

// 专辑收藏到小组
function groupalbum(id) {
	var url = '/group/pooladd/id/' + encodeURIComponent(id);
	showDialog(url);
};

// 加入小组
function groupjoin(id) {
	var url = '/group/join/id/' + encodeURIComponent(id);
	showDialog(url);
};

// 精选集收藏到小组
function groupcollect(id) {
	var url = '/group/pooladd/type/1/id/' + encodeURIComponent(id);
	showDialog(url);
};

// 修改个人介绍
function editprofile(id) {
	var url = '/member/editprofile';
	var url = url + '?object_id=' + encodeURIComponent(id);
	showDialog(url);
};

function getshengxiao(yyyy) {
	// by Go_Rush(阿舜) from http://ashun.cnblogs.com/
	var arr = [ '猴', '鸡', '狗', '猪', '鼠', '牛', '虎', '兔', '龙', '蛇', '马', '羊' ];
	return /^\d{4}$/.test(yyyy) ? arr[yyyy % 12] : null;
};

// 取星座, 参数分别是 月份和日期
function getxingzuo(month, day) {
	// by Go_Rush(阿舜) from http://ashun.cnblogs.com/
	var d = new Date(1999, month - 1, day, 0, 0, 0);
	var arr = [];
	arr.push([ "魔羯座", new Date(1999, 0, 1, 0, 0, 0) ])
	arr.push([ "水瓶座", new Date(1999, 0, 20, 0, 0, 0) ])
	arr.push([ "双鱼座", new Date(1999, 1, 19, 0, 0, 0) ])
	arr.push([ "牡羊座", new Date(1999, 2, 21, 0, 0, 0) ])
	arr.push([ "金牛座", new Date(1999, 3, 21, 0, 0, 0) ])
	arr.push([ "双子座", new Date(1999, 4, 21, 0, 0, 0) ])
	arr.push([ "巨蟹座", new Date(1999, 5, 22, 0, 0, 0) ])
	arr.push([ "狮子座", new Date(1999, 6, 23, 0, 0, 0) ])
	arr.push([ "处女座", new Date(1999, 7, 23, 0, 0, 0) ])
	arr.push([ "天秤座", new Date(1999, 8, 23, 0, 0, 0) ])
	arr.push([ "天蝎座", new Date(1999, 9, 23, 0, 0, 0) ])
	arr.push([ "射手座", new Date(1999, 10, 22, 0, 0, 0) ])
	arr.push([ "魔羯座", new Date(1999, 11, 22, 0, 0, 0) ])
	for ( var i = arr.length - 1; i >= 0; i--) {
		if (d >= arr[i][1])
			return arr[i][0];
	}
};

/*
 * 魔羯座(12/22 - 1/19)、水瓶座(1/20 - 2/18)、双鱼座(2/19 - 3/20)、牡羊座(3/21 - 4/20)、金牛座(4/21 -
 * 5/20)、 双子座(5/21 - 6/21)、巨蟹座(6/22 - 7/22)、狮子座(7/23 - 8/22)、处女座(8/23 -
 * 9/22)、天秤座(9/23 - 10/22)、 天蝎座(10/23 - 11/21)、射手座(11/22 - 12/21)
 */

function resendmail() {
	var url = '/member/regresend/type/ajax';
	showDialog(url);
};

// 将选中歌曲制作成多曲播放widget
function makeMultiWidget(checkname) {
	var ids = getSelectValues(checkname);
	if (ids == '') {
		alert("请先选择歌曲!");
		return;
	}
	window.location = '/widget/imulti?sid=' + ids;
};

function makeMultiWidgetH(checkname) {
	var ids = getValues(checkname);
	if (ids == '') {
		alert("请先选择歌曲!");
		return;
	}
	window.location = '/widget/imulti?sid=' + ids;
};

// 将选中的歌曲添加到精选集
function collects(checkname) {
	var ids = getSelectValues(checkname);
	if (ids == '') {
		alert("请先选择歌曲!");
		return;
	}
	var url = '/song/collects/ids/' + encodeURIComponent(ids);
	showDialog(url);
};

function collectsH(checkname) {
	var ids = getValues(checkname);
	if (ids == '') {
		alert("请先选择歌曲!");
		return;
	}
	var url = '/song/collects/ids/' + encodeURIComponent(ids);
	showDialog(url);
};

// js模板解析 模板变量类似%uid% 以%号界定
// str 为需解析的模板的html
// data为json 数据
// 例如传入str="<a href=/u/%uid%>%username%</a>"
// data={uid:1,username:'xiami'}
// 则返回 str = "<a href=/u/1>xiami</a>"
function parseTpl(str, data) {
	var result;
	var patt = new RegExp("%([a-zA-z0-9]+)%");
	while ((result = patt.exec(str)) != null) {
		var v = data[result[1]] || '';
		str = str.replace(new RegExp(result[0], "g"), v);
	}
	return str;
}
String.prototype.parseTpl = function(data) {
	return parseTpl(this, data);
};

// 收藏的
function player_collect(obj) {
	// collect(obj.songId);
	// 打3分收藏成好评的歌曲
};

function player_collected(songId) {
	thisMovie("musicPlayer").player_collected(songId);
}

// 下载
function player_download(obj) {
	download(obj.songId);
};

// 多首下载
function player_downloadmulty(objAry) {
	if (!objAry.length) {
		alert('请选择需要下载的歌曲！');
		return;
	}
	var ids = '';
	for ( var i = 0; i < objAry.length; i++) {
		if (ids != '')
			ids += ',';
		ids = ids + objAry[i].songId;
	}
	if (ids == '') {
		alert("请选择需要下载的歌曲！");
		return;
	}
	var url = '/download/song?id=' + encodeURIComponent(ids);
	showDialog(url);
}

// 添加
function player_add(obj) {
	collect(obj.songId);
}

// 更多
function player_more(objAry) {
	var html = $('#tpl-gears_more').val().parseTpl(objAry);
	$('.gears_more').html(html).show();
	window.event.cancelBubble = true;
	return false;
}

// 分享
function player_share(obj) {
	share("http://www.xiami.com/song/" + obj.songId);
}

// 推荐
function player_recommend(obj) {
	recommend(obj.songId, 32);
};

// 不喜欢
function player_unlike(obj) {

};

// 评价
function player_review(obj, num) {
	num -= 1;
	var url = '/song/review/id/' + encodeURIComponent(obj.songId) + '/num/'
			+ encodeURIComponent(num);
	showDialog(url);
};

// 歌曲有错 歌词有错 词曲不同步
function player_reportlyric(obj, type) {
	var url = '/song/reportlyric/type/' + type + '/id/'
			+ encodeURIComponent(obj.songId);
	showDialog(url);
};

// 上传歌词
function player_uploadlyric(obj) {
	var url = '/song/addlyric/id/' + encodeURIComponent(obj.songId);
	showDialog(url);
};

// 歌曲改变
function player_changeSong(obj) {
	document.title = obj.songName + "——虾小米打碟中……";
};

// 播放完成
function player_overSong(obj) {
	// $.get("http://data.xiami.com/count/playrecord/sid/"+obj.songId+"?object_name="+obj.objectName+"&object_id="+obj.objectId);
	var target = "http://www.xiami.com/count/playrecord/sid/" + obj.songId
			+ "?object_name=" + obj.objectName + "&object_id=" + obj.objectId;
	$.ajax({
		type : "GET",
		url : target,
		dataType : "jsonp"
	});
};

function xiamiclick(obj, jl, lj, hx) {
	var wordTip = '';
	var href = '';
	wordTip = getTip(jl);

	if (lj) {// 有链接的时候，需要在链接后面加关键词
		href = $(obj).attr("href");
		if (href.indexOf(wordTip) == '-1') {
			var join = '';
			if (hx)
				join = "?";
			else
				join = "&";
			var new_href = $(obj).attr("href") + join + wordTip;
			$(obj).attr("href", new_href);
		}
		return true;
	} else {
		href = "/index/ajaxmemberooter?" + wordTip;
		$.post(href, {}, function(txt) {
			$(obj).after(txt);
		});
		return true;
	}
};

function albumstore(id, type) {
	var url = '/album/addstore/id/' + encodeURIComponent(id) + '?type='
			+ encodeURIComponent(type);
	showDialog(url);
};

function getTip(num) {
	var Tip = '';
	switch (num) {
	// 首页
	case 101:
		Tip = 'trace_index_welcome';
		break;
	case 102:
		Tip = 'trace_index_recommand';
		break;
	case 103:
		Tip = 'trace_index_navigator';
		break;
	case 104:
		Tip = 'trace_index_blog';
		break;
	case 105:
		Tip = 'trace_index_software';
		break;
	case 106:
		Tip = 'trace_index_xiami_fm';
		break;
	case 107:
		Tip = 'trace_index_event';
		break;
	case 108:
		Tip = 'trace_index_friendsfeed';
		break;
	case 109:
		Tip = 'trace_index_album_new';
		break;
	case 110:
		Tip = 'trace_index_album_updown';
		break;
	case 111:
		Tip = 'trace_index_album_want';
		break;
	case 112:
		Tip = 'trace_index_collect_sub';
		break;
	case 113:
		Tip = 'trace_index_collect_recommand';
		break;
	case 114:
		Tip = 'trace_index_ranking';
		break;
	case 115:
		Tip = 'trace_index_comment';
		break;
	case 116:
		Tip = 'trace_index_bottom';
		break;
	// 音乐频道
	case 211:
		Tip = 'trace_music_navigator2';
		break;
	case 212:
		Tip = 'trace_music_filter';
		break;
	case 213:
		Tip = 'trace_music_recommand';
		break;
	case 214:
		Tip = 'trace_music_updown';
		break;
	case 222:
		Tip = 'trace_music_newalbum_list';
		break;
	case 221:
		Tip = 'trace_music_newalbum_category';
		break;
	case 231:
		Tip = 'trace_music_updown_list';
		break;
	case 241:
		Tip = 'trace_music_want_list';
		break;
	// AM精选集
	case 311:
		Tip = 'trace_collect_orinew_navigator2';
		break;
	case 312:
		Tip = 'trace_collect_orinew_list';
		break;
	case 313:
		Tip = 'trace_collect_orinew_right';
		break;
	case 321:
		Tip = 'trace_collect_helpnew';
		break;
	case 331:
		Tip = 'trace_collect_sub_head';
		break;
	case 332:
		Tip = 'trace_collect_sub_list';
		break;
	case 333:
		Tip = 'trace_collect_sub_subpast';
		break;
	// 小组
	case 411:
		Tip = 'trace_group_24hot';
		break;
	case 412:
		Tip = 'trace_group_update';
		break;
	case 413:
		Tip = 'trace_group_60min';
		break;
	case 414:
		Tip = 'trace_group_recommand';
		break;
	case 415:
		Tip = 'trace_group_manage';
		break;
	case 416:
		Tip = 'trace_group_myjoin';
		break;
	case 417:
		Tip = 'trace_group_friendjoin';
		break;
	}
	return Tip;
};

// 喜欢一位艺人
function artistLike(obj, url) {
	var id, load, uid;
	id = $(obj).attr("id");
	uid = $(obj).attr("rel");
	if (!uid) {
		if (confirm("呀！还未登录，现在要登录吗？"))
			window.location = "/member/login?done=" + url;
		return;
	}
	load = '<p><img width="16" height="16" alt="" src="http://img.xiami.com/res/img/default/loading.gif"/></p>';
	$(obj).html(load);
	$.post('/artist/like', {
		ajax : 1,
		likes : 1,
		id : id
	}, function(data) {
		if (data == 1) {
			var success = '<p>已记录；查看<a title="" href="/space/lib-artist/u/'
					+ uid + '">我喜欢的艺人</a></p>';
		}
		$(obj).replaceWith(success);
	});
};

// 专辑预览
/**
 * <div class="album_item100_thread" id="album_{$row.album_id}"> <a
 * class="preview" href="javascript:void(0)" onclick="album_preview(this)"
 * id="{$row.album_id}" title="">预览</a> </div> 在div、a里各加id，
 */
function album_preview(obj) {
	var id, load, $preview, indexOf;
	id = $(obj).attr("id");
	$preview = $("#album_preview_" + id);
	$(obj).hide();
	indexOf = $(obj).attr("class").indexOf('current');
	$(".album_preview").hide();
	$("p .current").removeClass("current").html("预览");
	if ($preview.html()) {
		if (indexOf == '-1') {
			$preview.show();
			$(obj).addClass("current").html("关闭预览").show();
		} else {
			$preview.hide();
			$(obj).removeClass("current").html("预览").show();
		}
	} else {
		load = '<div class="album_preview" id="album_preview_'
				+ id
				+ '"><img width="16" height="16" alt="" src="http://img.xiami.com/res/img/default/loading.gif"/></div>';
		$("#album_" + id).after(load);
		$.post('/album/preview', {
			id : id
		}, function(data) {
			$("#album_preview_" + id).html(data);
			$(obj).addClass("current").html("关闭预览").show();
		});
	}
};

// 广告显示
function display_ads(type, id) {
	$.post('/search/sponsor', {
		type : type
	}, function(data) {
		$("#" + id).html(data);
	});
};

// 推荐专题展示
function show_hot_events(type) {
	$.post('/ajax/showevents', {
		type : type
	}, function(data) {
		$('#hot_events_show').html(data);
	});
};

var $loading = $('<img class="load_feed loading" src="http://st.xiami.com/res/img/default/loading2.gif" width="16" height="11" />');
var $loading2 = $('<img class="loading" src="http://st.xiami.com/res/img/default/loading2.gif" width="16" height="11" />');

/*
 * function showFeedComment(feedId){ var $feedWrap = $('#feed_item_'+feedId);
 * var $comment=$feedWrap.find('.comments'); if($comment.size()<1 &&
 * $feedWrap.find('.loading').size()<1){ $feedWrap.append($loading);
 * $.getJSON('/commentlist/feed',{id:feedId},function(data){
 * if(data.status=='failed'){ if(data.msg=='请先登录'){ var t = window.location;
 * window.location='/member/login?done='+t; return false; }
 * alert(data.msg);return false; } $feedWrap.find('.loading').remove();
 * $feedWrap.find('.feed_body').append(data.msg).find('.comments').hide().fadeIn();
 * var $textarea = $feedWrap.find('.post textarea'); var $count =
 * $textarea.parent().next().find('.type_counts em');
 * $textarea.focus().keyup(function(){ var num = $textarea.val().length;
 * if(num>140) alert('输入内容请限制在140字以内'); $count.html(num.toString()); }); });
 * }else{ if($comment.css('display')=='block') $comment.fadeOut(); else
 * $comment.fadeIn(); } };
 * 
 * function addFeedComment(feedId){ var $feedWrap = $('#feed_item_'+feedId); var
 * $form = $feedWrap.find('.post'); var $ul = $form.prev().find('ul'); var
 * $textarea = $form.find('textarea'); var $count =
 * $textarea.parent().next().find('.type_counts em'); var val = $textarea.val();
 * if(val.length<3) {alert('请输入不少于3个字的内容');return false;} if(val.length>140)
 * {alert('输入内容请限制在140字以内');return false;} $feedWrap.find('.post
 * .bt_sub2').hide().after($loading2);
 * $.getJSON('/commentlist/add',{type:8,oid:feedId,content:val,mode:'ajax'},function(data){
 * $feedWrap.find('.loading').remove(); $feedWrap.find('.post
 * .bt_sub2').fadeIn(); if(data.status=='failed') {alert(data.errmsg);return;}
 * $ul.append(data.output); $textarea.val(''); var num = $textarea.val().length;
 * $count.html(num.toString()); }); };
 * 
 * function editFeedComment(commentId){ var $commentItem =
 * $('#feed_comment_item_'+commentId); var $editBox = '<li class="feed_comment_item"><p><textarea
 * rows="2" cols="60"></textarea></p><p class="editbox_act"><input
 * type="button" value="确定" class="bt_sub2"/> <input type="button"
 * onclick="$(this).parent().parent().prev().fadeIn();$(this).parent().parent().remove();"
 * value="取消" class="bt_cancle2"/></p></li>';
 * $commentItem.hide().after($editBox);
 * $commentItem.next().find('textarea').val($commentItem.find('input').val()).focus();
 * $commentItem.next().find('.bt_sub2').click(function(){ var $btnSubmit =
 * $(this); var $btnCancel = $btnSubmit.next(); var $input =
 * $btnSubmit.parent().prev().find('textarea'); $btnSubmit.hide();
 * $btnCancel.after($loading2).hide();
 * $.getJSON('/commentlist/feededit',{content:$input.val(),id:commentId},function(data){
 * if(data.status=='failed')
 * {alert(data.errmsg);$commentItem.fadeIn().next().remove();return;}
 * $commentItem.before(data.output).next().remove(); }); }); };
 * 
 * function delFeedComment(commentId){ var $commentItem =
 * $('#feed_comment_item_'+commentId);
 * $commentItem.find('.feed_comment_item').append($loading2);
 * $.post('/commentlist/del',{id:commentId,mode:'ajax'},function(data){
 * $commentItem.fadeOut('fast',function(){$(this).remove();}); }); };
 * 
 * function reFeedComment(commentId){ var $commentItem =
 * $('#feed_comment_item_'+commentId);
 * if($commentItem.next().find('textarea').size()>0) return false; var $editBox = '<li class="feed_comment_reply"><p><textarea
 * tabindex="4" rows="2" cols="60"></textarea></p><p class="editbox_act"><a
 * tabindex="6" href="javascript:;"
 * onclick="$(this).parent().parent().prev().fadeIn();$(this).parent().parent().remove();">取消</a>
 * <input tabindex="5" type="button" value="确定" class="bt_sub2"/> </p></li>';
 * $commentItem.after($editBox); $commentItem.next().find('textarea').val('回复'+
 * $commentItem.find('.nickname').text() + ':').focus();
 * $commentItem.next().find('.bt_sub2').click(function(){ var $btnSubmit =
 * $(this); var $btnCancel = $btnSubmit.next(); var $input =
 * $btnSubmit.parent().prev().find('textarea'); $btnSubmit.hide();
 * $btnCancel.after($loading2).hide();
 * $.getJSON('/commentlist/feedre',{content:$input.val(),id:commentId,type:8},function(data){
 * var $edbox = $commentItem.next(); if(data.status=='failed')
 * {alert(data.errmsg);$commentItem.fadeIn().next().remove();return;}
 * $commentItem.after(data.output); $edbox.remove(); }); }); };
 */

function report(id, type) {
	var url = '/ajax/report/id/' + encodeURIComponent(id) + '/type/'
			+ encodeURIComponent(type);
	showDialog(url);
};

$.fn.extend({
	getParent : function(level) {
		var $this = $(this);
		for ( var i = 0; i < level; i++)
			$this = $this.parent();
		return $this;
	}
});

var __share_prefix = '#share_li_';
var __comment_div = '.share_comment';
var __comment_prefix = '#comment_li_';
var commentTpl = '<li id="comment_li_%_id%"><span>%msg%</span> - <a href="/u/%user_id%" class="nickname">%nick_name%</a> <span class="minor">( <a href="javascript:;" onclick="editShareComment(\'%shareId%\',\'%_id%\')">编辑</a> | <a href="javascript:;" onclick="delShareComment(this,\'%shareId%\',\'%_id%\')">删除</a> )</span></li>';
var $editBox = '<li class="editbox"><p><textarea rows="2" cols="60"></textarea></p><p class="editbox_act"><input type="button" value="确定" class="bt_sub2"/> <input type="button" onclick="$(this).parent().parent().prev().fadeIn();$(this).parent().parent().remove();" value="取消" class="bt_cancle2"/></p></li>';
var showShareComment = function(shareId) {
	var $commentDiv = $(__share_prefix + shareId).find(__comment_div);
	$commentDiv.toggleClass('hidden');
	var $textarea = $commentDiv.find('.post textarea');
	var $count = $textarea.next().find('.type_counts em');
	$textarea.unbind().focus().keyup(
			function(e) {
				var num = $textarea.val().length;
				if (num > 140)
					$count.html('<span style="color:red">' + num.toString()
							+ '</span>');
				else
					$count.html(num.toString());
			}).trigger('keyup');
};

var addShareComment = function(shareId) {
	var $commentDiv = $(__share_prefix + shareId).find(__comment_div);
	var $submit = $commentDiv.find('.bt_sub2');
	var $textarea = $commentDiv.find('.post textarea');
	var val = $textarea.val();
	if (val.length < 3) {
		alert('天哪！你难道不会写点什么吗？这么短？！！');
		return;
	}
	if (val.length > 140) {
		alert('OMG,你怎么有这么多废话，不是拷贝粘帖的吧? 最多只需要140个字哦:-)');
		return;
	}
	$submit.hide().after($loading2);
	$.getJSON('/share/comment-add', {
		'msg' : val,
		'shareId' : shareId
	}, function(data) {
		try {
			if (data.status == 'failed') {
				$submit.show().next().remove();
				alert(data.msg);
				return;
			}
			if (data.status == 'ok') {
				$textarea.val('').trigger('keyup');
				data.comment.shareId = shareId;
				$commentDiv.find('ul li:last').before(
						commentTpl.parseTpl(data.comment));
			}
		} catch (e) {
			alert('提交过程中出现错误！请重试');
		}
		$submit.show().next().remove();
	});
};

var editShareComment = function(shareId, commentId) {
	var $commentDiv = $(__share_prefix + shareId).find(__comment_div);
	var $commentItem = $commentDiv.find(__comment_prefix + commentId);
	$commentItem.hide().after($editBox);
	$commentItem.next().find('textarea').focus().val(
			$($commentItem.find('span')[0]).text());
	$commentItem.next().find('.bt_sub2').click(
			function() {
				var $btnSubmit = $(this);
				var $btnCancel = $btnSubmit.next();
				var $input = $btnSubmit.parent().prev().find('textarea');
				var val = $input.val();
				if (val.length < 3) {
					alert('天哪！你难道不会写点什么吗？这么短？！！');
					return;
				}
				if (val.length > 140) {
					alert('OMG,你怎么有这么多废话，不是拷贝粘帖的吧? 最多只需要140个字哦:-)');
					return;
				}
				$btnSubmit.hide();
				$btnCancel.after($loading2).hide();
				$.getJSON('/share/comment-edit', {
					msg : $input.val(),
					commentId : commentId,
					shareId : shareId
				}, function(data) {
					if (data.status == 'failed') {
						alert(data.msg);
						$commentItem.fadeIn().next().remove();
						return;
					}
					data.comment.shareId = shareId;
					$commentItem.before(commentTpl.parseTpl(data.comment))
							.next().remove();
					$commentItem.remove();
				});
			});
};

var delShare = function(_this, shareId) {
	if (!confirm('确定要删除分享吗？')) {
		return;
	}
	var $shareDiv = $(__share_prefix + shareId);
	$(_this).hide().after($loading2);
	$.getJSON('/share/del', {
		shareId : shareId
	}, function(data) {
		if (data.status == 'failed') {
			$(_this).show().next().remove();
			alert(data.msg);
			return;
		}
		if (data.status == 'ok') {
			$shareDiv.fadeOut('fast', function() {
				$(this).remove();
			});
		} else {
			$(_this).show().next().remove();
			alert('删除失败，请重试！');
			return;
		}
	});
}

var delShareComment = function(_this, shareId, commentId) {
	if (!confirm('确定要删除评论吗？')) {
		return;
	}
	var $commentDiv = $(__share_prefix + shareId).find(__comment_div);
	var $commentItem = $commentDiv.find(__comment_prefix + commentId);
	$(_this).hide().after($loading2);
	$.getJSON('/share/comment-del', {
		commentId : commentId,
		shareId : shareId
	}, function(data) {
		if (data.status == 'failed') {
			$(_this).show().next().remove();
			alert(data.msg);
			return;
		}
		if (data.status == 'ok') {
			$commentItem.fadeOut('fast', function() {
				$(this).remove();
			});
		} else {
			$(_this).show().next().remove();
			alert('删除失败，请重试！');
			return;
		}
	});
}

var reShareComment = function(shareId, commentId) {
	var $commentDiv = $(__share_prefix + shareId).find(__comment_div);
	var $commentItem = $commentDiv.find(__comment_prefix + commentId);
	if ($commentItem.next().hasClass('editbox'))
		return;
	$commentItem.after($editBox);
	var $edbox = $commentItem.next();
	$edbox.find('textarea').focus().val(
			'回复' + $commentItem.find('.nickname').text() + ': ');
	$edbox.find('.bt_sub2').click(
			function() {
				var $btnSubmit = $(this);
				var $btnCancel = $btnSubmit.next();
				var $input = $btnSubmit.parent().prev().find('textarea');
				var val = $input.val();
				if (val.length < 3) {
					alert('天哪！你难道不会写点什么吗？这么短？！！');
					return;
				}
				if (val.length > 140) {
					alert('OMG,你怎么有这么多废话，不是拷贝粘帖的吧? 最多只需要140个字哦:-)');
					return;
				}
				$btnSubmit.hide();
				$btnCancel.after($loading2).hide();
				$.getJSON('/share/comment-add', {
					'msg' : $input.val(),
					'shareId' : shareId,
					'commentId' : commentId
				}, function(data) {
					try {
						if (data.status == 'failed') {
							alert(data.msg);
							$commentItem.fadeIn().next().remove();
							return;
						}
						if (data.status == 'ok') {
							$edbox.remove();
							data.comment.shareId = shareId;
							$commentDiv.find('ul li:last').before(
									commentTpl.parseTpl(data.comment));
						}
					} catch (e) {
						alert('提交过程中出现错误！请重试');
					}
				});
			});
};

var parseUbbFlash = function(obj) {
	var self = $(obj).parent();
	var swf = self.data("swf");
	var WH = self.attr('rel');
	var height, width;
	if (WH) {
		var index = WH.indexOf('_');
		height = WH.substring(0, index);
		width = WH.substring(index + 1);
	} else {
		width = 600;
		height = 450;
	}
	if (swf.indexOf("tudou.com") != -1) {
		swf = swf.replace("v.swf", "&autoPlay=true/v.swf")
	}

	if (swf.indexOf("yinyuetai.com") != -1) {
		swf = swf.replace("v_0.swf", "a_0.swf");
	}
	var tmp = '<object width="{width}" height="{height}" classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0"><param name="allowScriptAccess" value="never"><flashvars value="playMovie=true&auto=1&adss=0&isAutoPlay=true" /><param name="autoplay" value="true"><param name="allowFullScreen" value="false"><param name="movie" value="{swf}"><param value="opaque" name="wmode"><embed width="{width}" height="{height}" src="{swf}" quality="high" wmode="Opaque" allowscriptaccess="never" autoplay="true" flashvars="playMovie=true&auto=1&adss=0&isAutoPlay=true" type="application/x-shockwave-flash"></object>';

	var html = tmp.replace(/\{swf\}/g, swf);
	html = html.replace(/\{width\}/g, width);
	html = html.replace(/\{height\}/g, height);
	self.html(html);
	$(this).unbind("click");
	return false;
};

var reqStat = function(name) {
	$.get("http://www.xiami.com/statclick/req/" + name);
};