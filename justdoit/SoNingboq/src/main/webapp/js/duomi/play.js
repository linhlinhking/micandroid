var play = function(){
	function play(id){
		if(isPluginInstall())
		{
			url = 'duomi://t=s&ac=ps&si='+id;
			document.location.href = url;
		}
		else
			showDown();

	}

	function down(id){
		if(isPluginInstall())
		{
			url = 'duomi://t=s&ac=d&si='+id;
			document.location.href = url;
		}
		else
			showDown();
	}

	function showDown(){
		lightbox.show("/pc/pc_ltbox.shtml",523,266);
	}


	function isPluginInstall() {
	    var flag = false;
	    // 如果是firefox浏览器 
	    if (navigator.plugins && navigator.plugins.length) {
	        for (x = 0; x < navigator.plugins.length; x++) {
				//alert(navigator.plugins[x].name);
	            if (navigator.plugins[x].name == '多米音乐')
	                flag = true;
				//console.log(navigator.plugins[x].name);
	        }
	    }
	    // 下面代码都是处理IE浏览器的情况 
	    else if (window.ActiveXObject) {
	        try {
	           dmobj = new ActiveXObject('DuomiObj.DuomiObject.1');
	            if (dmobj)
	                flag = true;
	        } catch (e) {
	            flag = false;
	        }
	    }
	    return flag;
	}
	
	return {
		play : function(id){
			return play(id);
		},
		down : function(id){
			return down(id);
		},
		showDown : function(id){
			return showDown(id);
		},
		isPluginInstall : function(id){
			return isPluginInstall(id);
		}
	};
}();