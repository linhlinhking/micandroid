/**
 * ui
 */
$(function(){
	style_path = "resources/css/colors";
	$("#date-picker").datepicker();
	$("#box-tabs, #box-left-tabs").tabs();
});
/*===========================================Gorgeous split-line==============================================*/
/**
 * Function
 */
//一件更新商城
function buildAllHtml(){
	$.post("buildAllHtml.action",function(data){
		if(data.status=="success"){
			alert("更新商城成功");
			return;
		}
	});
}
/*===========================================Gorgeous split-line==============================================*/

/**
 * 检测用户是否登录并实行跳转控制
 */
$(function(){
	$.post("CheckLogin.action",function(data){
		if(data.slogin){
		    top.location.href="http://"+window.location.host+"/jshop/admin/jump.jsp";
		}else{
			if(data.length>0){
				top.location.href="http://"+window.location.host+"/jshop/admin/jump.jsp";
			}else{
				return;
			}
		}
	})
});

/*===========================================Gorgeous split-line==============================================*/


