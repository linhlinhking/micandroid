	/**
	 * @description Common AJAX POST METHOD
	 * @param url 
	 * @param param 
	 * @param callback 
	 * @author Devon.Ning 
	 */
	function postAjax(url,param,callback,complete){
		$.ajax({
			type:'POST',
			url:url,
			dataType:'json',
			data:param,
			success:callback,
			complete:complete
		});
	}
	

	
	