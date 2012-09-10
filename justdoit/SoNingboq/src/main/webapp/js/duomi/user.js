/**
 * 用户表单处理基类
 */
var user_form = function() {
	var self = this;
	self.type = "";
	self.sub_tag = true;
	self.sub_check_tag = {};
	self.sub_args = {};
	self.sub_btn = $("#"+self.type+"SubTxt");
	self.url = "";
	self.sub_text = '提交';
	self.doing_text = '正在提交';
	
	self.bind = function() {};
	self.check = function() {
		return true;
	};
	self.args_build = function() {
		self.sub_args = {tmp:Math.random()};
	};
	
	self.init = function() {
		self.bind();
	};
	self.sub_act = function(){
		if(!self.check()) {
			return;
		}
		self.args_build();
		self.sub_btn.addClass("disable").text(self.doing_text);
		$.ajax({
    		type:"POST",
    		url: self.url,
            data: self.sub_args,
    		cache:false,
    		dataType:'json',
    		success:function(result) {
    			if(result['ecode'] == "0") {
    				self.success(result);
    			}
    			else {
    				self.fail(result);
    				self.sub_btn.removeClass("disable").html(self.sub_text);
    				self.sub_tag = true;
    			}
    		}
    	});
	};
	self.after_act = function(){};
	self.success = function(){
		self.after_act();
	};
	self.fail = function(){
		var data = arguments[0] || null;
		self.tip(data['message']);
		setTimeout(function(){self.hide_tip();},5000);
	};
	self.tip = function(msg) {
		var isErr = arguments[1]===false?false:true;
		var errTxt = $("#"+self.type+"ErrTipTxt");
		if(isErr) {
			errTxt.removeClass("right").addClass("error").text(msg).show();
			return;
		}
		errTxt.removeClass("error").addClass("right").text(msg).show();
	};
	self.hide_tip = function() {
		$("#"+self.type+"ErrTipTxt").text("&nbsp;").hide();
	};
	self.line_tip = function(type, msg) {
		var isErr = arguments[2]===false?false:true;
		//var tipBox = $("#r"+type+"TipBox");
		var errBox = $("#"+self.type+type+"ErrTipBox");
		//var errIcon = $("#r"+type+"ErrIcon");
		var errTxt = $("#"+self.type+type+"ErrTipTxt");
		//tipBox.hide();
		if(isErr) {
			errBox.show();
			//errIcon.removeClass("right_icon").addClass("error_icon").show();
			errTxt.removeClass("right").addClass("error").text(msg).show();
			return;
		}
		errBox.show();
		//errIcon.removeClass("error_icon").addClass("right_icon").show();
		errTxt.removeClass("error").addClass("right").text(msg).show();
	};
	self.hide_line_tip = function(type) {
		//var errBox = $("#"+self.type+type+"ErrTipBox");
		var errTxt = $("#"+self.type+type+"ErrTipTxt");
		//errBox.hide();
		errTxt.html("&nbsp;");
	};
	
	/**
	 * 邮箱验证
	 */
	self.fail_tmp_email = "";
	self.email_check = function() {
		self.sub_check_tag.email = false;
		var email = $("#"+self.type+"Email").val();
		var miniNum = 4;
		var maxNum = 60;
		if(dm.isEmpty(email) || email == "请输入您常用的邮箱地址"){
			self.line_tip("Email","邮箱不能为空");
			return false;
		}
		
		if(dm.string.nLength(email)<miniNum){
			self.line_tip("Email","请输入至少4位字符");
			self.fail_tmp_email = email;
			return false;
		}
		
		if(dm.string.nLength(email)>maxNum){
			self.line_tip("Email","请输入少于60位的字符");
			self.fail_tmp_email = email;
			return false;
		}
		
		if(!dm.validate.regxEmail(email)){
			self.line_tip("Email","您输入的邮箱格式不正确");
			self.fail_tmp_email = email;
			return false;
		}
		self.sub_check_tag.email = true;
		self.hide_line_tip('Email');
		return true;
	};

	/**
	 * 密码验证
	 */
	self.pass_check = function() {
		var show_success = arguments[0] === false ? false : true;
		var pass_type = arguments[1] || "Pass";
		var pass_title = arguments[2] || "密码";
		self.sub_check_tag.pass = false;
		var userPass = $("#"+self.type+pass_type).val();
		var miniNum = 4;
		var maxNum = 20;
		if(dm.isEmpty(userPass) || userPass == "请输入密码"){
			self.line_tip(pass_type,pass_title+"不能为空");
			return false;
		}
		if(dm.string.nLength(userPass)<miniNum){
			self.line_tip(pass_type,pass_title+"至少4位");
			return false;
		}
		if(dm.string.nLength(userPass)>maxNum){
			self.line_tip(pass_type,pass_title+"至多20位");
			return false;
		}
		if(!dm.validate.regxPassword(userPass)){
			self.line_tip(pass_type,"只能是字母数字");
			return false;
		}
		if(show_success)
			self.line_tip(pass_type,pass_title+"已输入",false);
		else
			self.hide_line_tip(pass_type);
		self.sub_check_tag.pass = true;
		return true;
	};
	
	/**
	 * 确认密码验证
	 * @returns {Boolean}
	 */
	self.cfm_pass_check = function() {
		self.sub_check_tag.cfm_pass = false;
		var pass_type = arguments[1] || "Pass";
		var _user_pass = $("#"+self.type+pass_type).val();
		var _cfm_pass = $("#"+self.type+"CfmPass").val();
		if(dm.isEmpty(_cfm_pass)){
			self.line_tip("CfmPass","请确认您的密码");
			return false;
		}
		else if(_cfm_pass != _user_pass){
			self.line_tip("CfmPass","两次输入不相同");
			return false;
		}
		self.hide_line_tip("CfmPass");
		self.sub_check_tag.cfm_pass = true;
		return true;
	};

	/**
	 * 昵称验证
	 */
	self.fail_tmp_nick = "";
	self.nick_check = function() {
		self.sub_check_tag.nick = false;
		var nickName = $("#"+self.type+"Nick").val();
		var miniNum = 2;
		var maxNum = 10;
		if(dm.isEmpty(nickName) || nickName == "请输入昵称"){
			self.line_tip("Nick","昵称不能为空");
			return false;
		}
		if(dm.string.nLength(nickName)<miniNum){
			self.line_tip("Nick","至少为2个中文");
			self.fail_tmp_nick = nickName;
			return false;
		}
		if(dm.string.nLength(nickName)>maxNum){
			self.line_tip("Nick","请少于10个中文");
			self.fail_tmp_nick = nickName;
			return false;
		}
		if(!dm.validate.regxNick(nickName)){
			self.line_tip("Nick","请用中英文、数字");
			self.fail_tmp_nick = nickName;
			return false;
		}
		self.sub_check_tag.nick = true;
		self.hide_line_tip('Nick');
		return true;
	};
	/**
	 * 邮箱重复验证
	 */
	self.tmp_email = "";
	self.email_exists = function() {
		self.sub_check_tag.email = false;
		var email = $("#"+self.type+"Email").val();
		if(self.tmp_email != email || self.fail_tmp_email != self.tmp_email){
			self.line_tip("Email","正在检测...",false);
			self.tmp_email = email;
			$.ajax({
				url: "/user-user-ename",
	            data: "user="+email+"&tmp="+Math.random(),
	            type: "GET",
				cache:false,
				dataType:"json",
				success:function(result){
					if(result['ecode'] == "0"){
						self.line_tip("Email","邮箱可以使用",false);
						self.sub_check_tag.email = true;
						return;
					}
					self.line_tip("Email",result['message']);
				}
			});
		}
	};
	/**
	 * 昵称重复验证
	 */
	self.tmp_nick = "";
	self.nick_exists = function() {
		self.sub_check_tag.nick = false;
		var nickName = $("#"+self.type+"Nick").val();
		if(self.tmp_nick!=nickName || self.fail_tmp_nick != self.tmp_nick){
			self.line_tip("Nick","正在检测...",false);
			self.tmp_nick = nickName;
			$.ajax({
				url: "/user-user-enick",
	            data: {nick:nickName,tmp:Math.random()},
	            type: "POST",
				cache:false,
				dataType:"json",
				success:function(result){
					if(result['ecode'] == "0"){
						self.line_tip("Nick","昵称可以使用",false);
						self.sub_check_tag.nick = true;
						return;
					}
					self.line_tip("Nick",result['message']);
				}
			});
		}
	};
	
	self.phone_check = function() {
		self.sub_check_tag.phone = false;
		var _phone = $("#"+self.type+"PhoneNum").val();
		if(dm.isEmpty(_phone) || _phone == "请输入手机号"){
			self.line_tip("PhoneNum","手机号不能为空");
			return false;
		}
		if(!dm.validate.regxPhone(_phone)){
			self.line_tip("PhoneNum","手机号格式错误");
			return false;
		}
		
		self.hide_line_tip("PhoneNum");
		self.sub_check_tag.phone = true;
		return true;
	};
	
	self.captcha_check = function() {
		self.sub_check_tag.captcha = false;
		var _captcha = $("#"+self.type+"Captcha").val();
		if(dm.isEmpty(_captcha)){
			self.line_tip("Captcha","验证码不能为空");
			return false;
		}
		self.hide_line_tip("Captcha");
		self.sub_check_tag.captcha = true;
		return true;
	};
};

var user_info_form = function() {
	user_form.call(this);
	var self = this;
	self.type = 'user';
	self.data = null;
	self.init = function() {
		self.load_data();
		self.bind();
	};
	self.load_data = function() {
		$.ajax({
			url:'/user-user-getinfo',
			dataType:'json',
			cache:false,
			success:function(data) {
				if(data.ecode < 0) {
					self.tip(data.message);
				}
				else {
					self.data = data.user;
					self.format(data.user);
				}
			}
		});
	};
	self.format = function(user) {
		$("#infoNick").text(user.Nick || user.UserName);
		$("#infoFace").attr('src', user.Image || '/app/web/public/templates/images/default/face_b.jpg');
		$("#infoDistrict").text(user.District||'地球');
	};
};

/**
 * 用户登录表单处理类
 */
var login_form = function() {
	user_form.call(this);//继承user_form
	var self = this;
	self.type = 'login';
	self.url = '/user-user-login';
	self.sub_text = '登录';
	self.bind = function() {
		util.textBind({
			id : 'loginUser',
			nextId : 'loginPass'
		});
		util.textBind({
			id : 'loginPass',
			sub: self.sub_act
		});
		util.btnBind({
			id:'loginSubTxt',
			click: self.sub_act
		});
	};
	self.check = function() {
		if(!self.sub_tag) return;
		self.sub_tag = false;
		var user = $("#loginUser").val();
		var pass = $("#loginPass").val();
		if(user==""){
			self.tip("账号不能为空");
			self.sub_tag = true;
			return false;
		}
		if(pass==""){
			self.tip("密码不能为空");
			self.sub_tag = true;
			return false;
		}
		return true;
	};
	self.args_build = function() {
		var _user = $("#loginUser").val();
		var _pass = $("#loginPass").val();
		self.sub_args = {user:_user,pass:_pass.MD5(32),iskeep:$("#loginIsKeep").is(':checked')};
	};
};
//dm.object.inheritPrototype(login_form,user_form);

/**
 * 用户注册表单处理
 */
var reg_form = function() {
	user_form.call(this);//继承user_form
	var self = this;
	self.type = 'reg';
	self.url = '/user-user-reg';
	self.sub_text = '立即注册';
	self.bind = function() {
		util.textBind({
			id : 'regEmail',
			nextId : 'regPass',
			focusout : function() {
				if(self.email_check()) {
					self.email_exists();
				}
			}
		});
		util.textBind({
			id : 'regPass',
			nextId: 'regCfmPass',
			focusout : function() {
				self.pass_check(false);
			}
		});
		util.textBind({
			id : 'regCfmPass',
			nextId: 'regNick',
			focusout : function() {
				self.cfm_pass_check();
			}
		});
		util.textBind({
			id : 'regNick',
			nextId: 'regCaptcha',
			focusout : function() {
				if(self.nick_check()) {
					self.nick_exists();
				}
			}
		});
		util.textBind({
			id : 'regCaptcha',
			sub: self.sub_act,
			focusout : function() {
				self.captcha_check();
			}
		});
		util.btnBind({
			id:'regSubTxt',
			click: self.sub_act
		});
	};
	self.check = function() {
		if(!self.sub_tag) return;
		self.sub_tag = false;
		if(!self.sub_check_tag.email || !self.sub_check_tag.pass || !self.sub_check_tag.cfm_pass || !self.sub_check_tag.nick || !self.sub_check_tag.captcha){
			if(!self.sub_check_tag.email){
				if(self.email_check()) {
					self.email_exists();
				}
			}
			if(!self.sub_check_tag.pass){
				self.pass_check(false);
			}
			if(!self.sub_check_tag.cfm_pass){
				self.cfm_pass_check();
			}
			if(!self.sub_check_tag.nick){
				if(self.nick_check()) {
					self.nick_exists();
				}
			}
			if(!self.sub_check_tag.captcha){
				self.captcha_check();
			}
			self.sub_tag = true;
			return false;
		}
		return true;
	};
	self.args_build = function() {
		var _user = $("#regEmail").val();
		var _pass = $("#regPass").val();
		var _cfm_pass = $("#regCfmPass").val();
		var _nick = $("#regNick").val();
		var _captcha = $("#regCaptcha").val();
		self.sub_args = {user:_user,pass:_pass,cfmpass:_cfm_pass,nick:_nick,captcha:_captcha};
	};
	self.fail = function(data) {
		var data = arguments[0] || null;
		$("#regCaptchaImg").attr('src','/plugin/captcha/imgcode.php?t='+Math.random());
		self.tip(data['message']);
	};
};

/**
 * 修改密码表单处理
 */
var setpwd_form = function() {
	user_form.call(this);//继承user_form
	var self = this;
	self.type = 'setpwd';
	self.url = '/user-user-setpwd';
	self.sub_text = '确认修改';
	self.bind = function() {
		util.textBind({
			id : 'setpwdOldPass',
			nextId : 'setpwdPass',
			focusout : function() {
				self.old_pass_check();
			}
		});
		util.textBind({
			id : 'setpwdPass',
			nextId : 'setpwdCfmPass',
			focusout : function() {
				self.new_pass_check();
			}
		});
		util.textBind({
			id : 'setpwdCfmPass',
			sub: self.sub_act,
			focusout : function() {
				self.cfm_pass_check();
			}
		});
		util.btnBind({
			id:'setpwdSubTxt',
			click: self.sub_act
		});
	};
	self.check = function() {
		if(!self.sub_tag) return;
		self.sub_tag = false;
		if(!self.sub_check_tag.old_pass || !self.sub_check_tag.pass || !self.sub_check_tag.cfm_pass){
			if(!self.sub_check_tag.old_pass){
				self.old_pass_check();
			}
			if(!self.sub_check_tag.pass){
				self.new_pass_check();
			}
			if(!self.sub_check_tag.cfm_pass){
				self.cfm_pass_check();
			}
			self.sub_tag = true;
			return false;
		}
		return true;
	};
	self.args_build = function() {
		var _old_pass = $("#setpwdOldPass").val();
		var _new_pass = $("#setpwdPass").val();
		var _cfm_pass = $("#setpwdCfmPass").val();
		self.sub_args = {oldpass:_old_pass,newpass:_new_pass,cfmpass:_cfm_pass};
	};
	self.old_pass_check = function() {
		self.sub_check_tag.old_pass = false;
		var _old_pass = $("#setpwdOldPass").val();
		if(dm.isEmpty(_old_pass)){
			self.line_tip("OldPass","请确输入旧密码");
			return false;
		}
		self.hide_line_tip("OldPass");
		self.sub_check_tag.old_pass = true;
		return true;
	};
	self.new_pass_check = function() {
		return self.pass_check(false,'Pass',"新密码");
	};
	self.cfm_pass_check = function() {
		self.sub_check_tag.cfm_pass = false;
		var _new_pass = $("#setpwdPass").val();
		var _cfm_pass = $("#setpwdCfmPass").val();
		if(dm.isEmpty(_cfm_pass)){
			self.line_tip("CfmPass","请确认您的密码");
			return false;
		}
		else if(_cfm_pass != _new_pass){
			self.line_tip("CfmPass","两次输入不相同");
			return false;
		}
		self.hide_line_tip("CfmPass");
		self.sub_check_tag.cfm_pass = true;
		return true;
	};
	self.success = function() {
		self.tip('修改密码成功');
		setTimeout(function(){self.hide_tip();},5000);
		self.clear();
	};
	self.clear = function() {
		$("#setpwdOldPass").val('');
		$("#setpwdPass").val('');
		$("#setpwdCfmPass").val('');
		self.sub_tag = true;
		self.sub_check_tag.old_pass = false;
		self.sub_check_tag.pass = false;
		self.sub_check_tag.cfm_pass = false;
	};
};

var face_form = function() {
	user_info_form.call(this);
	var self = this;
	self.type = 'face';
	self.format = function(user) {
		$("#faceBig").attr('src', user.Image || '/app/web/public/templates/images/default/face_b.jpg');
		$("#faceSmall").attr('src', user.Image || '/app/web/public/templates/images/default/face_s.jpg');
	};
};

/**
 * 提交头像修改（选择）
 */
var face_select_form = function() {
	user_info_form.call(this);
	var self = this;
	self.type = 'face';
	self.url = '/user-user-faces';
	self.sub_text = '保存头像';
	self.format = function(user) {
		$("#facesBig").attr('src', user.Image || '/app/web/public/templates/images/default/face_b.jpg');
	};
	self.bind = function() {
		var radio_click = function(obj) {
			if(dm.isEmpty(obj)){
				return;
			}
			$("#facesBig").attr('src',"http://img1.sns.duomi.com/default/big/head"+$(obj).val());
		};
		util.radioBind({
			name : 'faceRadio',
			click : function() {
				var obj = arguments[0] || null;
				radio_click(obj);
			}
		});
		util.btnBind({
			id:'facesSubTxt',
			click: self.sub_act
		});
	};
	self.args_build = function() {
		var _img = $("input[name='faceRadio']:checked").val();
		self.sub_args = {img:_img};
	};
};

/**
 * 提交头像修改（上传）
 */
var face_upload_form = function() {
	user_info_form.call(this);
	var self = this;
	self.type = 'faceu';
	self.url = '/user-user-faceu';
	self.sub_text = '保存头像';
	self.format = function(user) {
		$("#faceuThumbnail").attr('src', user.Image || '/app/web/public/templates/images/default/face_b.jpg');
		$("#faceuThumbnailpre").attr('src', user.Image || '/app/web/public/templates/images/default/face_b.jpg');
	};
	self.bind = function(user) {
		var preview = function(img, selection) {
			var scaleX = 150 / selection.width; 
			var scaleY = 150 / selection.height; 
			
			$('#faceuThumbnailpre').css({ 
				width: Math.round(scaleX * $("#faceuThumbnail").width()) + 'px', 
				height: Math.round(scaleY * $("#faceuThumbnail").height()) + 'px',
				marginLeft: '-' + Math.round(scaleX * selection.x1) + 'px', 
				marginTop: '-' + Math.round(scaleY * selection.y1) + 'px' 
			});
			$('#x1').val(selection.x1);
			$('#y1').val(selection.y1);
			$('#x2').val(selection.x2);
			$('#y2').val(selection.y2);
			$('#w').val(selection.width);
			$('#h').val(selection.height);
		};
		var showimg = function() {
			var img = $("#faceuImg2Upload").val();
			var point = img.lastIndexOf(".");  
			var type = img.substr(point);
			if(type==".jpg"||type==".gif"||type==".JPG"||type==".GIF"||type==".png"||type==".PNG"){
				//上传图片
				//设置thumbnail 和 thumbnailpre 图片属性为上传的图片
				//
				ajaxFileUpload(Math.random());
			}else{
				self.tip('图片格式不正确,仅支持jpg/gif/png图片');
			}
			$("#faceuImg2Upload").bind('change',showimg);
		};
		
		var ajaxFileUpload = function() {
			$("#faceuLoading").ajaxStart(function(){ $(this).show(); }).ajaxComplete(function(){	$(this).hide();	});
			$.ajaxFileUpload ({
					url:'/user-user-facet?t='+Math.random(),
					secureuri:false,
					fileElementId:'faceuImg2Upload',
					dataType: 'json',
					success: function (data, status) {
						if(data.ecode<0) {
							self.tip(data.message);
						}
						else {
							$("#faceuThumbnail").attr("src",data.thumb+"?"+Math.random());
							$("#faceuThumbnailpre").attr("src",data.thumb+"?"+Math.random());
							var w2 = 150;
							if(data.imgW<150){
								w2 = data.imgW;
							}
							$('#faceuThumbnail').imgAreaSelect({x1: 0, y1: 0, x2: w2, y2: w2,aspectRatio: '1:1', onSelectChange: preview ,handles: true});
						}
					},
					error: function (data, status, e) {
						self.tip('上传失败，请换张图片上传');
					}
				}
			);
			return false;
		};
		
		$('#faceuThumbnail').imgAreaSelect({x1: 0, y1: 0, x2: 150, y2: 150,aspectRatio: '1:1', onSelectChange: preview ,handles: true});

		$("#faceuImg2Upload").bind('change',showimg);
		
		util.btnBind({
			id:'faceuSubTxt',
			click: self.sub_act
		});
	};
	self.args_build = function() {
		self.sub_args = {x1:$("#x1").val()
				,y1:$("#y1").val()
				,w:$("#w").val()
				,h:$("#h").val()
				,tmp:+Math.random()
		};
	};
};

/**
 * 修改资料
 */
var edit_form = function() {
	user_info_form.call(this);//继承user_form
	var self = this;
	self.type = 'edit';
	self.url = '/user-user-edit';
	self.sub_text = '保存资料';
	self.format = function(user) {
		var _userName = $("#editUserName");
		var _nick = $("#editNick");
		var _boy = $("#editBoy");
		var _girl = $("#editGirl");
		var _sign = $("#editSign");
		_userName.text(user.UserName);
		_nick.val(user.Nick || '');
		if(!dm.isEmpty(user.Nick)) {
			self.sub_check_tag.nick = true;
		}
		_sign.val(user.Signature || '');
		self.sub_check_tag.sgin = true;
		if(user.Sex==1){
			_boy.attr('checked',true);
		}
		else{
			_girl.attr('checked',true);
		}
		var dt = new Date();
		if(user.Birthday){
			var ary = user.Birthday.split("-");
			dt = new Date(ary[0], ary[1]-1, ary[2]);
		}
		var selYear = window.document.getElementById("editSelyear");
		var selMonth = window.document.getElementById("editSelmonth");
		var selDay = window.document.getElementById("editSelday");
		new DateSelector(selYear, selMonth,selDay, dt);
		setTimeout(function(){//ie6 特别添加操作
			selYear.selectedIndex = new Date().getFullYear()-dt.getFullYear();
			selMonth.selectedIndex = dt.getMonth();
			selDay.selectedIndex = dt.getDate()-1;
		},100);
		var areaAry = user.District?user.District.split(","):new Array();
		new PCAS("editProvince","editCity",areaAry[0]||"",areaAry[1]||"");
	};
	self.bind = function() {
		util.textBind({
			id : 'editNick',
			nextId : 'editSign',
			focusin : function() {
				self.sub_check_tag.nick = false;
			},
			focusout : function() {
				if(self.nick_check()) {
					self.nick_exists();
				}
			}
		});
		util.textareaBind({
			id : 'editSign',
			nextId : 'sub',
			focusin : function() {
				self.sub_check_tag.sign = false;
			},
			focusout : function() {
				self.sign_check();
			}
		});
		util.btnBind({
			id:'editSubTxt',
			click: self.sub_act
		});
	};
	self.check = function() {
		if(!self.sub_tag) return;
		self.sub_tag = false;
		if(!self.sub_check_tag.nick || self.sub_check_tag.sign === false){
			if(!self.sub_check_tag.nick){
				if(self.nick_check()) {
					self.nick_exists();
				}
			}
			if(!self.sub_check_tag.sign){
				self.sign_check();
			}
			if(self.sub_check_tag.nick && self.sub_check_tag.sign) {
				return true;
			}
			self.sub_tag = true;
			return false;
		}
		return true;
	};
	self.args_build = function() {
		var _nick = $("#editNick").val();
		var _sex = $("input[name='editGender']:checked").val();
		var _year = $("#editSelyear").val();
		var _month = $("#editSelmonth").val();
		var _day = $("#editSelday").val();
		var _province = $("#editProvince").val();
		var _city = $("#editCity").val();
		var _sign = $("#editSign").val();
		self.sub_args = {nick:_nick,sex:_sex,year:_year,month:_month,day:_day,province:_province,city:_city,sign:_sign};
	};
	self.success = function(){
		self.tip('修改资料成功',false);
		setTimeout(function(){self.hide_tip();},5000);
		self.clear();
		self.after_act();
	};
	self.clear = function() {
		self.sub_tag = true;
		self.hide_line_tip('Nick');
		self.hide_line_tip('Sgin');
		self.sub_check_tag.nick = true;
		self.sub_check_tag.sgin = true;
	};
	self.sign_check = function() {
		self.sub_check_tag.sign = true;
		var maxNum = 100;
		var _sign = $("#editSign").val();
		if(!dm.isEmpty(_sign)&&dm.string.nLength(_sign)>maxNum){
			self.sub_check_tag.sign = false;
			self.tip("自我介绍不能超过100个中文或字母");
			return false;
		}
		return true;
	};
};

var safe_form = function() {
	user_info_form.call(this);//继承user_form
	var self = this;
	self.type = 'safe';
	self.url = '/user-user-actphone';
	self.sub_text = '立刻发送';
	self.format = function(user) {
		var _userBox = $("#safeUserBox");
		if(!dm.validate.regxEmail(user.UserName)) {
			_userBox.hide();
		}
		else {
			_userBox.show();
			$("#safeUser").text(user.UserName);
			var _userStatus = $("#safeUserStatus");
			var _userBtn = $("#safeUserBtn");
			if(parseInt(user.User_status)==1) {
				_userStatus.text('已验证');
				_userBtn.hide();
			}
			else {
				_userStatus.text('未验证');
				_userBtn.show();
				_userBtn.text('立即验证');
			}
		}

		var _phone = $("#safePhone");
		var _phoneStatus = $("#safePhoneStatus");
		var _phoneBtn = $("#safePhoneBtn");
		if(dm.isEmpty(user.Phone)) {
			_phone.text('无');
			_phoneStatus.text('未验证');
			_phoneBtn.show();
			_phoneBtn.text('立即验证');
		}
		else if(parseInt(user.Phone_activated) == 1) {
			_phone.text(user.Phone);
			_phoneStatus.text('已验证');
			_phoneBtn.show();
			_phoneBtn.text('更换手机');
		}
		else if(parseInt(user.Phone_activated) == 2) {
			_phone.text(user.Phone);
			_phoneStatus.text('已被他人绑定');
			_phoneBtn.show();
			_phoneBtn.text('更换手机');
		}
		else {
			_phone.text(user.Phone);
			_phoneStatus.text('未验证');
			_phoneBtn.show();
			_phoneBtn.text('立即验证');
		}
	};
	self.bind = function() {
		util.btnBind({
			id:'safeUserBtn',
			click : function() {
				self.user_active();
			}
		});
		util.btnBind({
			id:'safePhoneBtn',
			click: function() {
				$("#safeStatus").hide();
				$("#safeSend").hide();
				$("#safePhoneInput").show();
				$("#safePhoneSubTxt").removeClass('disable');
				$("#safePhoneSubTxt").text('立即发送');
			}
		});
		util.textBind({
			id : 'safePhoneNum',
			nextId : 'safeCaptcha',
			focusout : function() {
				self.phone_check();
			}
		});
		util.textBind({
			id : 'safeCaptcha',
			nextId : 'sub',
			focusout : function() {
				self.captcha_check();
			}
		});
		util.btnBind({
			id:'safePhoneSubTxt',
			click : self.sub_act
		});
	};
	self.user_active = function() {
		$.ajax({
			url:'/user-user-actuser',
			dataType:'json',
			cache:false,
			success : function(data) {
				if(data.ecode<0) {
					self.tip(data.message);
					return;
				}
				$("#safeStatus").hide();
				$("#safeSend").show();
				$("#safePhoneInput").hide();
				$("#safeSendTo").text('系统已经给 '+data.email+' 发送一封验证邮件，请登录此邮箱点击验证邮件中的链接进行验证。');
			}
		});
	};
	self.check = function() {
		if(!self.sub_tag) return;
		self.sub_tag = false;
		if(!self.sub_check_tag.phone || self.sub_check_tag.captcha === false){
			if(!self.sub_check_tag.phone){
				self.phone_check();
			}
			if(!self.sub_check_tag.captcha){
				self.captcha_check();
			}
			self.sub_tag = true;
			return false;
		}
		return true;
	};
	self.args_build = function() {
		var _phone = $("#safePhoneNum").val();
		var _captcha = $("#safeCaptcha").val();
		self.sub_args = {phone:_phone,captcha:_captcha};
	};
	self.success = function(data){
		if(data.ecode<0) {
			self.tip(data.message);
			setTimeout(function(){self.hide_tip();},5000);
			return;
		}
		$("#safeStatus").hide();
		$("#safeSend").show();
		$("#safePhoneInput").hide();
		$("#safeSendTo").text('系统已经给您的手机发送一封验证短信，请点击验证短信中的链接进行验证。');
	};
	self.fail = function() {
		var data = arguments[0] || null;
		$("#safeCaptchaImg").attr('src','/plugin/captcha/imgcode_s.php?t='+Math.random());
		self.tip(data['message']);
	};
};

var findpwd_form = function() {
	var self = this;
	self.init = function() {
		email_form = new email_findpwd_form();
		phone_form = new phone_findpwd_form();
		email_form.init();
		phone_form.init();
	};
};

var email_findpwd_form = function() {
	user_form.call(this);//继承user_form
	var self = this;
	self.type = 'findpwde';
	self.url = '/user-user-findpwd';
	self.sub_text = '找回密码';
	self.bind = function() {
		util.textBind({
			id : 'findpwdeEmail',
			nextId : 'findpwdeCaptcha',
			focusout : function() {
				self.email_check();
			}
		});
		util.textBind({
			id : 'findpwdeCaptcha',
			sub: self.sub_act,
			focusout : function() {
				self.captcha_check();
			}
		});
		util.btnBind({
			id:'findpwdeLeft',
			click: function() {
				$("#findpwdeLeft").hide();
				$("#findpwdeLeftBox").slideDown("slow");
				$("#findpwdeCaptchaImg").attr('src','/plugin/captcha/imgcode_s.php?t='+Math.random());
				$("#findpwdpRight").show();
				$("#findpwdpRightBox").hide();
			}
		});
		util.btnBind({
			id:'findpwdeSubTxt',
			click: self.sub_act
		});
	};
	self.check = function() {
		if(!self.sub_tag) return;
		self.sub_tag = false;
		if(!self.sub_check_tag.email || self.sub_check_tag.captcha === false){
			if(!self.sub_check_tag.email){
				self.email_check();
			}
			if(!self.sub_check_tag.captcha){
				self.captcha_check();
			}
			self.sub_tag = true;
			return false;
		}
		return true;
	};
	self.args_build = function() {
		var _email = $("#findpwdeEmail").val();
		var _captcha = $("#findpwdeCaptcha").val();
		self.sub_args = {by:_email,captcha:_captcha,type:'m'};
	};
	self.success = function(data) {
		$("#findBox").hide();
		$("#findSuccessBox").html("已经成功发送重置密码邮件").show();
	};
};

var phone_findpwd_form = function() {
	user_form.call(this);//继承user_form
	var self = this;
	self.type = 'findpwdp';
	self.url = '/user-user-findpwd';
	self.sub_text = '找回密码';
	self.bind = function() {
		util.textBind({
			id : 'findpwdpPhoneNum',
			nextId : 'findpwdpCaptcha',
			focusout : function() {
				self.phone_check();
			}
		});
		util.textBind({
			id : 'findpwdpCaptcha',
			sub: self.sub_act,
			focusout : function() {
				self.captcha_check();
			}
		});
		util.btnBind({
			id:'findpwdpRight',
			click: function() {
				$("#findpwdpRight").hide();
				$("#findpwdpRightBox").slideDown("slow");
				$("#findpwdpCaptchaImg").attr('src','/plugin/captcha/imgcode_s.php?t='+Math.random());
				$("#findpwdeLeft").show();
				$("#findpwdeLeftBox").hide();
			}
		});
		util.btnBind({
			id:'findpwdpSubTxt',
			click: self.sub_act
		});
	};
	self.check = function() {
		if(!self.sub_tag) return;
		self.sub_tag = false;
		if(!self.sub_check_tag.phone || self.sub_check_tag.captcha === false){
			if(!self.sub_check_tag.phone){
				self.email_check();
			}
			if(!self.sub_check_tag.captcha){
				self.captcha_check();
			}
			self.sub_tag = true;
			return false;
		}
		return true;
	};
	self.args_build = function() {
		var _by = $("#findpwdpPhoneNum").val();
		var _captcha = $("#findpwdpCaptcha").val();
		self.sub_args = {by:_by,captcha:_captcha,type:'s'};
	};
	self.success = function(data) {
		$("#findBox").hide();
		$("#findSuccessBox").text("已经成功发送重置密码短信").show();
	};
};

/**
 * 用户操作域
 */
var user = {
	init: function(type) {
		var afterFun = arguments[1] || function() {};
		var loginType = arguments[2] || 'need';//need需要登录、neednot需要非登录、none无所谓登录
		//var sub = function(){};
		//var init = function(){};
		var login = new login_form(),
		reg = new reg_form(),
		info = new user_info_form(),
		setpwd = new setpwd_form(),
		edit = new edit_form(),
		face = new face_form(),
		faces = new face_select_form(),
		faceu = new face_upload_form(),
		safe = new safe_form(),
		findpwd = new findpwd_form();
		var type = eval("("+type+")");
		if(typeof type != 'undefined'){
			if(loginType != 'none') {
				user.is_login(loginType);
			}
			type.after_act = afterFun;
			type.init();
		}
		/*LOG(type);
		LOG(type.bind);*/
	},
	
	is_login : function(loginType){
		if(loginType=='need') {
			if(dm.isEmpty($.cookie('ulg'))){//用户登录跳至登录界面
	    			top.location.href = '/user/login.shtml';
	    	}
		}
		else if(loginType == 'neednot') {
			if(!(dm.isEmpty($.cookie('ulg')))){
	    			top.location.href = '/';
	    	}
		}
	},
	
	logout : function() {
		$.cookie('ulg', null, {expires: 0, path: "/", domain: ".duomi.com", secure: true});
		top.window.location.href = "/";
	}
};



