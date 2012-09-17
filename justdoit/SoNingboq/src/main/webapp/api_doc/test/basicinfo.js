$(function(){




});

	function selectClosed(v){
		if(v == 'undefined' || v == null){
			return ;
		}
		var str = v.substring(0,v.indexOf("_")) ;
		if($("#"+v).is(':checked') == true){
			$("#"+str+"_open").hide();
			$("#"+str+"_line").hide();
			$("#"+str+"_close").hide();
			
		}else{
			$("#"+str+"_open").show();
			$("#"+str+"_line").show();
			$("#"+str+"_close").show();
		}
		
	}
	
	function applyAll(){
		var sValue = $("#mon_open option:selected").val();
		var eValue = $("#mon_close option:selected").val();
		$("select:not({'#mon_open','#mon_close'})").each(function(){
			var ids = this.id ;
			if(ids.indexOf("open") > 0){
				$("#"+ids + " option[value='"+sValue+"']").attr("selected",true);
			}else{
				$("#"+ids + " option[value='"+eValue+"']").attr("selected",true);
			}
			
		});
		
	}


