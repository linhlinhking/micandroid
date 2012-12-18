/**
 * @version 1.0
 * @author Devon.Ning
 * @Description:register.
 */

var xero = {};

xero.Register = function() {
	return {
		focus : 0,

		init : function() {
			$("#register_email").blur(this, this.validateEmail);
			$("#password_ie").blur(this, this.validatePassword);
			$(".signup_button").click(this, this.validateForm);
		},
		validateProtocol : function(e) {
			if ($(".agree_row .icon_span").hasClass("not_accept")) {
				$(".check_term").show();
				return false;
			}
			return true;
		},
		validateXero : function(e) {
			if ($(".link_row .icon_span").hasClass("not_accept")) {
				return false;
			}
			return true;
		},
		validateEmail : function(e) {
			$(".check_email").hide();

			var uemail = $.trim($("#register_email").val());
			$("#register_email").val(uemail);

			var reg = /^[_a-z0-9]+(\.[_a-z0-9-]+)*@[a-z0-9-]+([a-z0-9-_]+)*(\.[a-z]{2,4}){1,2}$/i;
			if ("" == uemail || uemail.length < 0 || !reg.test(uemail)) {
				// $(".check_email").show();
				if (e.data.focus == 1)
					$("#register_email").focus();
				return false;
			}
			$.ajax({
				type : 'POST',
				url : '/checkEmail?random=' + (+new Date()),
				dataType : 'json',
				data : {
					"currentEmail" : uemail
				},
				success : function(res) {
					if (res.result) {
						$(".check_email").show();
						return false;
					}
				}

			});
			return true;
		},
		validatePassword : function(e) {
			$(".check_pawo").hide();
			var upass = $.trim($("#password_ie").val());
			$("#password_ie").val(upass);
			if ("" == upass || upass.length < 0 || upass.length < 6) {
				$(".check_pawo").show();
				if (e.data.focus != 'undefined' && e.data.focus == 1)
					$("#password_ie").focus();
				return false;
			}
			return true;
		},
		validateForm : function(e) {
			e.data.focus = true;
			rt = e.data.validateEmail(e) && e.data.validatePassword(e)
					&& e.data.validateProtocol(e);
			e.data.focus = false;
			
			xe = e.data.validateXero(e);

			if (rt) {
				var uemail = $.trim($("#register_email").val());
				var upass = $.trim($("#password_ie").val());
				var cmpName = $("#register_companyName").val();
				var linkXero = 0 ;
				if(xe){
					linkXero = 1 ;
				}

				$.ajax({
					type : 'POST',
					url : '/register?random=' + (+new Date()),
					dataType : 'json',
					data : {
						"companyName" : cmpName,
						"uemail" : uemail,
						"password" : upass,
						"linkXero": linkXero
					},
					success : function(res) {
						if (res.result && xe) {
							window.top.location.href = "/connect" ;
						}else{
							window.top.location.href = "/" + res.url;
						}
					}
				});

			}

			return rt ;
		}

	};

}