	/**
	 * @Description: 
	 * @author Devon.Ning
	 */

	$(function() {
		
		var _planId = $("input[name='current_planId']").val() ;
		
		/** Show Currency Pay Plan. */
		showPlayStatus(_planId);
		
		/** Hide the Card Type. */
		$(".type_lists").hide();
		
		/** Limited User. */
		$("#freeUser span").click(function(){
			cancelBuy();
		});
		
		/** Hide All Validation Tips.*/
		hideTips();
		
		/** Five User. */
		$("#fiveUsers span").click(function(){
			var id = this.id ;
			var _this = this ;
			if($(_this).hasClass('not_buy')){
				$("input[name=Amount]").val("39.99") ;
				doBuy(id,_this);
			}else{
				cancelBuy();
			}
		});
		
		/** Unlimited User. */
		$("#unlimitedUser span").click(function(){
			var id = this.id ;
			var _this = this ;
			if($(_this).hasClass('not_buy')){
			$("input[name=Amount]").val("49.99") ;
				doBuy(id,_this);
			}else{
				cancelBuy();
			}
		});
		
		
		$(".mask_area, .cancel_button").click(function(){
			displayWindow();
			//clearInputValue();
		});
		
		var cardJson = ["Visa", "Master Card"];
	    var temp_json = new Array();
		$("#card_type").focus(function(){
	    	 $(".type_lists").show().empty();
	    	 for(var i = 0, j = cardJson.length; i < j ; i ++){
	    	 	showDate(cardJson[i]);
	    	 }
	    }).focusout(function(){
	    	setTimeout(function(){
	    		$(".type_lists").hide();
	    	},100);
	    }).keyup(function(){
	    	var v = $("#card_type").val();
	    	$(".type_lists").empty()
	    	searchData(v, cardJson, temp_json);
	    	for(var i = 0, j = temp_json.length; i < j ; i ++){
	    		showDate(temp_json[i]);
	    	}
	    });
	    
	    $(".type_lists li").live("click", function(){
	    	$("#card_type").val($(this).html());
	    });
	
		/** Pop The selected item . */
		$(".for_card_type input").focus(function(){
		    $(this).addClass("typing");
	    });
	    
	    $(".for_card_type input").blur(function(){
		    $(this).removeClass("typing");
	    });﻿
	    
	    
	    $("#amount").blur(function(){
	   	 	$(".l1").hide();
	    	var value = $(this).val();
	    	if("" == value || value.length < 1){
	    		$(".l1").show();
	    		$(this).focus();
	    	}
	    });
	    
	    $("#address1").blur(function(){
	   	 	$(".l2").hide();
	    	var value = $(this).val();
	    	if("" == value || value.length < 1){
	    		$(".l2").show();
	    		$(this).focus();
	    	}
	    });
	    
	   $("#card_number").blur(function(){
	   	 	$(".r2").hide();
	    	var value = $(this).val();
	    	if("" == value || value.length != 16 ){
	    		$(".r2").show();
	    		$(this).focus();
	    	}
	    });
	    
	    
	   $("#cv_number").blur(function(){
	   	 	$(".r4").hide();
	    	var value = $(this).val();
	    	if("" == value || value.length < 1){
	    		$(".r4").show();
	    		$(this).focus();
	    	}
	    });
	    
	   $("#name_on_card").blur(function(){
	   	 	$(".r3").hide();
	    	var value = $(this).val();
	    	if("" == value || value.length < 1){
	    		$(".r3").show();
	    		$(this).focus();
	    	}
	    });
	    
	    
	   $("#postal_code").blur(function(){
	   	 	$(".l3").hide();
	    	var value = $(this).val();
	    	if("" == value || value.length < 1){
	    		$(".l3").show();
	    		$(this).focus();
	    	}
	    });
	    
	   $("#issue_number").blur(function(){
	   	 	$(".r5").hide();
	    	var value = $(this).val();
	    	if("" == value || value.length < 1){
	    		$(".r5").show();
	    		$(this).focus();
	    	}
	    });
	    
	    
	    
	
	});
	
	/** Private Methods Start...*/
	
	function handlerBuy(_self,id){
		if($(_self).hasClass("not_buy")){
			doBuy(id_self);
		}else{
			cancelBuy();
		}
	}
	
	function checkIput(b, c){
		if(!b){
			$("." + c).show();
		}else{
			$("." + c).hide();
		}
	}
	
	function searchData(v,d,t){// 键盘按下时搜索 v:搜索值 d:直接对比数据源
		t.length = 0;
		for(var i = 0; i < d.length; i ++){
			if(d[i].toLowerCase().substring(0,v.length).indexOf(v.toLowerCase()) > -1){
				t[t.length] = d[i];
			}
		}
	}
	
	function showDate(value){
		$(".type_lists").append("<li>" + value + "</li>");
	}
	
	function getServerTime(){
        var http;
        if(window.XMLHttpRequest){
        	http = new XMLHttpRequest();
        }else{
        	http = new ActiveXObject("Microsoft.XMLHTTP");
        }
		http.open("HEAD", ".", false);   
		http.send(null);   
		return new Date(http.getResponseHeader("Date"));
    }
	

	function displayWindow(){
		$(".pay_show, .cancel_show").fadeOut('fast', function () {
            $(".mask_area").fadeOut('fast');
        });
		
	}
	
	/**
	 * @Description remove current free way.
	 * 
	 */
	function cancelBuy(){
		$(".mask_area").fadeIn('fast', function () {
	        $(".cancel_show").fadeIn('fast');
	    });
	}
	
	function showPlayStatus(_id){
		if(_id == "" || _id == 'undefined'){
			_id = 1 ;
		}
		switch(_id){
		case '1':
			$("#freeUser span").removeClass("not_buy").addClass('con_buy');
			break;
		case '2':
			$("#fiveUsers span").removeClass("not_buy").addClass('con_buy');
			break;
		case '3':
			$("#unlimitedUser span").removeClass("not_buy").addClass('con_buy');
			break;
		}
	}
	
	
	/**
	 * @Description Pay
	 * 
	 */
	function doBuy(id,_this){
		$(".mask_area").fadeIn('fast', function () {
		      $(".pay_show").fadeIn('fast');
		 });
		 
		  $(".add_button").click(function(){
				 switch(id){
					 case '1': break;
					 case '2': 
					 	//setBuyStatus(_this);
					 	addPayment("/auth/payment2",id,_this);
						//updateCurrentUser(id,_this);
						break;
					 case '3': 
					 	//setBuyStatus(_this);
					 	addPayment("/auth/payment2",id,_this);
						//updateCurrentUser(id,_this);
						break;
				 }
		 });
	}
	
	function execReg(reg, str){
		var result = reg.exec(str);
		return  (result == null || result == '') ? false : true;
	}
	
	function setBuyStatus(_this){
		$(".each_box").each(function(){
			$(this).find('.con_buy').removeClass("con_buy").addClass('not_buy');
		});
		$(_this).removeClass('not_buy').addClass('con_buy');
	}
	
	function clearInputValue(){
		$(".add_cus_content input").each(function(){
			$(this).val('');
		});
		
		$(".r1, .l1, .r2, .l2, .r3, .l3, .r4, .l4, .r5, .l5, .l6").hide();
	}
	
	function updateCurrentUser(_id,_this){
		$.ajax({
			url:'/user-plan?random=' + (new Date()),
			type:'POST',
			dataType:'json',
			data:{
				"planId":_id,
				"companyId":$("input[name='current_company_id']").val()
			},success:function(res){
				if(res.result){
					$("input[name='current_planId']").val(_id);
					setBuyStatus(_this);
					clearInputValue();
				}
			}
		});
	}
	
	/** 发送支付请求.*/
	function addPayment(url,_id,_this){
	
		var eYear = $("#ed_y option:selected").val();
		var eMonth = $("#ed_m option:selected").val() ;
		$("input[name=DateExpiry]").val(eMonth+eYear);
		var sYear = $("#sd_y option:selected").val();
		var sMonth = $("#sd_m option:selected").val();
		$("input[name=DateStart]").val(sMonth+sYear);
	
		var amount = $("input[name=Amount]").val() ;
	 	var currencyInput = $("input[name=CurrencyInput]").val() ;
	 	var merchantReference = $("input[name=MerchantReference]").val();
	 	var txnId = $("input[name=TxnId]").val();
	 	var txnType = $("input[name=TxnType]").val();
	 	var cvc2Presence = $("input[name=Cvc2Presence]").val();
	 	var cvc2 = $("input[name=Cvc2]").val();
	 	//var dateExpiry = $("input[name=DateExpiry]").val();
	 	var dateExpiry = eMonth + eYear ;
	 	var cardNumber = $("input[name=CardNumber]").val();//
	 	var cardHolderName = $("input[name=CardHolderName]").val();
	 	var enableBillingCard = $("input[name=EnableAddBillCard]").val();
	 	var enableAvsData = $("input[name=EnableAvsData]").val();
	 	var avsAction = $("input[name=AvsAction]").val();
	 	var avsPostCode = $("input[name=AvsPostCode]").val();
	 	var avsStreetAddress = $("input[name=AvsStreetAddress]").val();
	 	//var startDate = $("input[name=DateStart]").val();
	 	var startDate = sMonth + sYear ;
	 	var issueNumber = $("input[name=IssueNumber]").val();
	 	var userId = $("input[name=currency_user_id]").val();
	 	var companyId= $("input[name=current_company_id]").val();
	 	
	 	var param = {"amount":amount,
	 	"currencyInput":currencyInput,
	 	"merchantReference":merchantReference,
	 	"cardHolderName":cardHolderName,
	 	"dateExpiry":dateExpiry,
	 	"cardNumber":cardNumber,
	 	"cvc2":cvc2,
	 	"cvc2Presence":cvc2Presence,
	 	"txnId":txnId,
	 	"txnType":txnType,
	 	"enableBillingCard":enableBillingCard,
	 	"enableAvsData":enableAvsData,
	 	"avsAction":avsAction,
	 	"avsPostCode":avsPostCode,
	 	"avsStreetAddress":avsStreetAddress,
	 	"startDate":startDate,
	 	"issueNumber":issueNumber,
	 	"currencyUserId":userId,
	 	"companyId":companyId} ;
	 	
	 	
	 	//postAjax(url,param,paymentResponse,completeCallback);
	 	var gdpChecked = new gdp.Validation() ;
	 	var flag = gdpChecked.vAmount() && gdpChecked.vAddress()
	 		&& gdpChecked.vCardNumber() && gdpChecked.vCvNumber()
	 		&& gdpChecked.vCardHoldName() && gdpChecked.vPostCode()
	 		&& gdpChecked.vIssureNumber() ;
	 		
	 	var isError = true ;
	 	
	 	if(flag){
		 	$.ajax({
				type:'POST',
				url:url,
				dataType:'json',
				data:param,
				beforeSend:function(){
					$(".add_button").addClass("loading_btn");
					
				},
				success:function(res){
					if(res.result){
				 		var d = res.data ;
				 		var dd = d.transaction ;
				 		var flag = d.success ;
				 		var txtResponse = d.responseText ;
				 		if("1" == flag){
				 			updateCurrentUser(_id,_this);
				 			isError = false ;
				 		}else{
				 			$(".pay_err").html(txtResponse);
				 			$(".pay_err").fadeIn(400,function(){
				 				$(this).fadeOut(3000);
				 			}) ;
				 		}
				 		
				 	}else{
				 		$(".pay_err").html("Payment Failure.");
				 			$(".pay_err").fadeIn(400,function(){
				 				$(this).fadeOut(3000);
				 			}) ;
				 	}
				},
				complete:function(){
					$(".add_button").removeClass("loading_btn");
					if(isError){
						//don't do anything,
					}else{
						displayWindow();
					}
					 
				}
			});
	 	}else{
	 		event.preventDefault();
	 		//alert(flag);
	 	}
	 
	 	
	}
	
	function paymentResponse(res){
	 	
	 }
	 
	 
	function completeCallback(jqXHR,textStatu){
		if(textStatu == 'success'){
			
		}
	}
	
	function hideTips(){
		$(".r1, .l1, .r2, .l2, .r3, .l3, .r4, .l4, .r5, .l5").hide();
	}
	
	function trimValue(str){
		str = str.replace(/^\s+/,'') ;
		for(var i = str.length -1;i >=0;i--){
			if(/\S/.test(str.charAt(i))){
				str = str.substring(0,i+1) ;
				break;
			}
		}
		
		return str ;
	}
	
	/** Validate. */
	var gdp = {} ;
	gdp.Validation = function(){
		return {
			focus:0,
			
			init:function(){
				$("#amount").blur(this,this.vAmount) ;
				//$("#")
			},
			
			vAmount:function(){
				var amountValue = $("input[name=Amount]").val();
				if("" == amountValue || amountValue.length < 1){
					$(".l1").show();
					$("#amount").focus();
					return false ;
				}
				$(".l1").hide();
				return true ;
			},vCardNumber:function(){
				var cardNumberValue = $("input[name=CardNumber]").val();
				if("" == cardNumberValue || cardNumberValue.length != 16 ){
					$(".r2").show();
					$("#card_number").focus();
					return false ;
				}
				$(".r2").hide();
				return true ;
			},vCvNumber:function(){
				var cvNumber = $("input[name=Cvc2]").val();
				if("" == cvNumber || cvNumber.length < 1 ){
					$(".r4").show();
					$("#cv_number").focus();
					return false ;
				}
				$(".r4").hide();
				return true ;
			},vAddress:function(){
				var address = $("input[name=AvsStreetAddress]").val();
				if("" == address || address.length < 1){
					$(".l2").show();
					$("#address1").focus() ;
					return false ;
				}
				$(".l2").hide();
				return true ;
			},vCardHoldName:function(){
				var cardHoldNameValue = $("input[name=CardHolderName]").val() ;
				if("" == cardHoldNameValue || cardHoldNameValue.length < 1 ){
					$(".r3").show();
					$("#name_on_card").focus() ;
					return false ;
				}
				$(".r3").hide();
				return true ;
			},vPostCode:function(){
				var postCodeValue = $("input[name=AvsPostCode]").val() ;
				if("" == postCodeValue || postCodeValue.length < 1){
					$(".l3").show();
					$("#postal_code").focus();
					return false ;
				}
				$(".l3").hide();
				return true ;
			},vIssureNumber:function(){
				var issureNumberValue = $("input[name=IssueNumber]").val();
				if("" == issureNumberValue || issureNumberValue.length < 1 ){
					$(".r5").show();
					$("#issue_number").focus() ;
					return false ;
				}
				$(".r5").hide();
				return true ;
			},vCardType:function(){
				return true ;
			}
			
		} ;
	}
	
	








