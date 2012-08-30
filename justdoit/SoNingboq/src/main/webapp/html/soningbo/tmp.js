	$(function(){
		

		
		$(".save_button").die().live("click",function(){
			$(window).scrollTop(0);
			$.ajax({
				type:"POST",
				dataType:"json",
				url:apiurl+"/resource/location/addLocation",
				data:oLocation.sendLocationData(),
				success:function(json){
					if(json == 'undefined' || json == null){
						showNotice("updatefailed");
						return ;
					}
					if("true" == json.result){
						//json.locationid;
						$.ajax({
							type:"POST",
							dataType:"json",
							url:apiurl + "/resource/locationExt/add",	
							data:oLocation.sendLocationExtData(json.locationId),
							success:function(json){
								showNotice("updatesuccess");
								
							},error:function(){
								showNotice("updatefailed");	
							}
						});
					}
				},
				error:function(){
					showNotice("updatefailed");
				}
			
			});
			
			
		
		});
	
	
	});	