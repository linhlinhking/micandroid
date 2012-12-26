	/**
	 * @Description: 
	 * @author Devon.Ning
	 */

	$(function() {
		
		var _planId = $("input[name='current_planId']").val() ;
		
		showPlayStatus(_planId);
		
		$("#freeUser span").click(function(){
			cancelBuy();
		});
		
		$("#fiveUsers span").click(function(){
			var id = this.id ;
			var _this = this ;
			if($(_this).hasClass('not_buy')){
				doBuy(id,_this);
			}else{
				cancelBuy();
			}
		});
		
		$("#unlimitedUser span").click(function(){
			var id = this.id ;
			var _this = this ;
			
			if($(_this).hasClass('not_buy')){
				doBuy(id,_this);
			}else{
				cancelBuy();
			}
		});
		
		
		$(".mask_area, .cancel_button").click(function(){
			displayWindow();
		});
	
	});
	
	

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
			case '1':
				break;
			case '2':
				//setBuyStatus(_this);
				updateCurrentUser(id,_this);
				break;
			case '3':
				//setBuyStatus(_this);
				updateCurrentUser(id,_this);
				break;
			}
			displayWindow();
		});
	}
	
	function setBuyStatus(_this){
		
		$(".each_box").each(function(){
			$(this).find('.con_buy').removeClass("con_buy").addClass('not_buy');
		});
		$(_this).removeClass('not_buy').addClass('con_buy');
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
				}
			}
		});
		
	}