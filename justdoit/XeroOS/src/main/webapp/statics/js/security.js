	/**
	 * Loading...
	 */
	$(function(){
		$("#reset_psd_btn").click(function(){
			var valid = new gdp.Validation() ;
			var flagValue = valid.vEmail() ;// && valid.vAjax() ;
			
			var currency_email = $("input[name=currency_email]").val();
			var params = {"currency_email":currency_email} ;
			var isSuccess = false ;
			
			if(flagValue){
				$.ajax({
					type:'POST',
					url:'/forgot/password?random=' + (+new Date()),
					dataType:'json',
					data:params,
					success:function(res){
						if(res){
							$(".type_field p").removeClass("email_error")
							.addClass("email_accept")
							.html("Send Email Success.").show();
							isSuccess = true ;
						}
					},
					complete:function(){
						if(isSuccess){
							window.top.location.href = "/" ;
						}else{
							$(".email_error").html("Send Email Failure.").show();
						}
					}
				});
			}
			
		});
		
	}) ;
	
	
	
	
	/*** Validation The Email.*/
	var gdp = {} ;
	gdp.Validation = function(){
		return {
			mailValue:$.trim($("input[name=currency_email]").val()),
			
			vEmail:function(){
				$(".email_error").hide();
				var reg = /^[_a-z0-9]+(\.[_a-z0-9-]+)*@[a-z0-9-]+([a-z0-9-_]+)*(\.[a-z]{2,4}){1,2}$/i;
				if ("" == this.mailValue || this.mailValue.length < 0 || !reg.test(this.mailValue)) {
					$(".email_error").show();
					return false;
				}
				return true ;
			},vAjax:function(){
				$(".email_error").hide();
				$.ajax({
					type : 'POST',
					url : '/checkEmail?random=' + (+new Date()),
					dataType : 'json',
					data : {
						"currentEmail" : this.mailValue
					},
					success : function(res) {
						if (res.result) {
							return true ;
						}else{
							$(".email_error").html("Email No Exists.").show();
							return false ;
						}
					 }
				});
			}
		
		};
	}