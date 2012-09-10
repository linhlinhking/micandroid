var com = function() {
	var topLogin = null;
	var topMyDuomi = null;
	
	function getUserLogin() {
		if(dm.isEmpty($.cookie('ulg'))){
			return null;
		}
		var userLogin = decodeURIComponent($.cookie("ulg"));
	    userLogin = eval("(" + userLogin + ")");
	    return userLogin;
	}
	
	function getUserName() {
		var userLogin = getUserLogin();
		if(dm.isEmpty(userLogin)) return null;
		return userLogin['UserName'];
	}
	
	function getUserId() {
		var userLogin = getUserLogin();
		if(dm.isEmpty(userLogin)) return null;
		return userLogin['UserId'];
	}
	
	function getToken() {
		var userLogin = getUserLogin();
		if(dm.isEmpty(userLogin)) return null;
		return userLogin['SessionId'];
	}
	
	function getNick() {
		var userLogin = getUserLogin();
		if(dm.isEmpty(userLogin)) return null;
		return userLogin['Nick'];
	}
	
	function getFace() {
		var userLogin = getUserLogin();
		if(dm.isEmpty(userLogin)) return null;
		return userLogin['Face'];
	}
	
	var obj = {
		init : function() {
			topLogin = $("#comTopLogin");
			topMyDuomi = $("#comTopMyDuomi");
			topMyNick = $("#comTopNick");
		},
		
		flushUserStatus : function() {
			if(!topLogin) {
				this.init();
			}
			if(getUserLogin()){
				topLogin.hide();
				topMyNick.text((getNick()?getNick():getUserName())+"的多米");
				topMyDuomi.show();
			}
			else {
				topLogin.show();
				topMyNick.text('我的多米');
				topMyDuomi.hide();
			}
		},
		
		getUserLogin : function(){
			return getUserLogin();
		},
		
		getUserName : function(){
			return getUserName();
		},
		
		getUserId : function(){
			return getUserId();
		},
		
		getNick : function() {
			return getNick();
		},
		
		getFace : function() {
			return getFace();
		},
		
		getToken : function(){
			return getToken();
		},
		 
		logout : function() {
			$.cookie('ulg', null, {expires: 0, path: "/", domain: ".duomi.com", secure: true});
			this.flushUserStatus();
			try{
				logoutHide();
			}
			catch(e){};
		}
	};
	
	return obj;
}();