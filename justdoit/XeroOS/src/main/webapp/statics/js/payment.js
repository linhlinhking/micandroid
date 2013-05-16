	/**
	 *
	 *
	 */
	 $(function(){
	 	$("#payBtn").click(function(){
	 		requestPayment("/auth/payment2");
	 	});
	 	
	 });
	 
	 /**
	 */
	 
	 function requestPayment(url){
	 
	 	var amount = $("input[name=Amount]").val() ;
	 	var currencyInput = $("input[name=CurrencyInput]").val() ;
	 	var merchantReference = $("input[name=MerchantReference]").val();
	 	var txnId = $("input[name=TxnId]").val();
	 	var txnType = $("input[name=TxnType]").val();
	 	var cvc2Presence = $("input[name=Cvc2Presence]").val();
	 	var cvc2 = $("input[name=Cvc2]").val();
	 	var dateExpiry = $("input[name=DateExpiry]").val();
	 	var cardNumber = $("input[name=CardNumber]").val();//
	 	var cardHolderName = $("input[name=CardHolderName]").val();
	 	var enableBillingCard = $("input[name=EnableBillingCard]").val();
	 	var enableAvsData = $("input[name=EnableAvsData]").val();
	 	var avsAction = $("input[name=AvsAction]").val();
	 	var avsPostCode = $("input[name=AvsPostCode]").val();
	 	var avsStreetAddress = $("input[name=AvsStreetAddress]").val();
	 	var userId = $("input[name=currency_user_id]").val();
	 	var companyId= $("input[name=current_company_id]").val();
	 	var startDate = $("input[name=StartDate]").val();
	 	var issueNumber = $("input[name=IssueNumber]").val();
	 	
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
	 	
	 	postAjax(url,param,paymentResponse,completeCallback);
	 }
	 
	 /***/
	 function paymentResponse(res){
	 	if(res.result){
	 		var d = res.data ;
	 		var dd = d.transaction ;
	 		alert(d.success);
	 		alert(dd.amount) ;
	 	}else{
	 		alert("Error.");
	 	}
	 }
	 
	 
	 function completeCallback(jqXHR,textStatu){
		if(textStatu == 'success'){
			
		}
	}
	 
	 
