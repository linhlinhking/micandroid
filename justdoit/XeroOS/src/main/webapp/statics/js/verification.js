$(function(){
		
		$(".check_name, .check_email, .check_pawo").hide();
		$("input[name='name'], input[name='uemail'], input[name='password']").focusout(function(){
			var v = $(this).val();
			switch($(this).attr("name")){
				case "name" : checkName(v);break;
				case "uemail" : checkEmail(v);break;
				case "password" : checkPass(v);break;
			}
		});
		$(".cancel_button, .del_button, .add_button").click(function(){
			$(".check_name, .check_email, .check_pawo").hide();
		});
		
		function checkName(v){
			(v == '') ? $(".check_name").show() : $(".check_name").hide();
		}
		function checkEmail(v){
			var reg = /^[_a-z0-9]+(\.[_a-z0-9-]+)*@[a-z0-9-]+([a-z0-9-_]+)*(\.[a-z]{2,4}){1,2}$/i;
			reg.test(v) ? email_f = $(".check_email").hide() : $(".check_email").show();
		}
		function checkPass(v){
			(v.length < 6 || v.length > 12) ? $(".check_pawo").show() : $(".check_pawo").hide();
		}
	
})
