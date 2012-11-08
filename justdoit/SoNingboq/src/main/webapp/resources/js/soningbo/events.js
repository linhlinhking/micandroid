$(function() {
	// before submit ,please validate .
	// var v = $("#eventsCategory option:selected").val();
	// if (v == '0') {
	// alert("请选择一个类别！");
	// return false;
	// }
	$("#addEvents").tabs();
	$("input:radio[name='repeatTypeItem']").click(function() {
		var start_txt = $("#startDateTime").val();
		var v = $(this).val();

		if (start_txt == '' || start_txt == 'undefined') {
			alert("请选择开始时间。");
			return;
		}
		if (v == '1') {
			$("#sel_start_time_days").text(start_txt);
			$("#repeatTypeName").val("days");
			selDatePicker("sel_end_date_time_days");
			$("#dialog-form-week").dialog("open");
		}

	});

	$("input:radio[name='selectPrice']").click(function() {
		var v = $(this).val();
		if (v == '0') {
			$("#price").val(0);
		}
	});

	$("#dialog-form-week").dialog({
		autoOpen : false,
		height : 220,
		width : 460,
		modal : true,
		buttons : {
			"确定" : function() {
				var selectedType = $("#sel_change_repeat_type option:selected").val();
				var strValue = "" ;
				
				switch (selectedType) {
				case 'days':
					setEventDateValue(selectedType);
					$("#event_repeatValue").val(strValue);
					break;
				case 'weeks':
					setEventDateValue(selectedType);
					$(".sel_repeat_week div").each(function(){
						if($(this).hasClass("selected")){
							//strValue += $(this).text() + ","; 
							strValue += this.id + "," ;
						}
					});
					if(strValue == "" || strValue.length < 0){
						alert("请选择重复的星期.");
						return ;
					}else{
						strValue = strValue.substring(0, strValue.length - 1);
					}
					$("#event_repeatValue").val(strValue);
					
					break;
				case 'months':
					setEventDateValue(selectedType);
					break;
				case 'customers':
					setEventDateValue(selectedType);
					
					$("#event_repeatValue").val(strValue);
					break;
				}

				$(this).dialog("close");
			},
			"取消" : function() {
				$(this).dialog("close");
			}
		},
		close : function() {
		}
	});

	selDatePicker("startDateTime");

	/**选择重复类型的事件处理程序*/
	$("#sel_change_repeat_type").change(function() {
		$("#show_by_days").hide();
		$("#show_by_weeks").hide();
		$("#show_by_months").hide();
		$("#show_by_customers").hide();

		var txt_startTime = $("input[name='startDateTime']").val();
		var v = $(this).val();
		switch (v) {
		case 'days':
			showTabType(v, txt_startTime);
			break;
		case 'weeks':
			showTabType(v, txt_startTime);
			break;
		case 'months':
			showTabType(v, txt_startTime);
			break;
		case 'customs':
			showTabType(v, txt_startTime);
			break;
		}
	});

	// var val=$('input:radio[name="repeatType"]:checked').val();

	/** 选择week */
	$(".sel_repeat_week div").click(function() {
		if ($(this).hasClass("selected")) {
			$(this).removeClass("selected");
		} else {
			$(this).addClass("selected");
		}
	});
	
	/**获取所有的UUID*/
	
	
	/**文件上传组件*/
	$("#events_upload").click(function(){
		$("#dialog-events-upload").dialog("open");
	});
	
	$("#dialog-events-upload").dialog({
		autoOpen : false,
		title:"选择图片上传",
		height : 500,
		width : 820,
		modal : true,
		buttons : {
			"确定" : function(){
				var tempUUIDValue = "" ;
				$(":input[name='uuid_value']").each(function(){
					tempUUIDValue += this.value + ",";
				});
				tempUUIDValue = tempUUIDValue.substring(0, tempUUIDValue.length - 1);
				$("input[name='events_uuids']").val(tempUUIDValue);
				
				if("" == tempUUIDValue){
					$("#events_upload_message").text("");
				}else{
					$("#events_upload_message").text("图片已成功上传.");//addClass("events_upload");
				}
				$(this).dialog("close");
			},
			"取消" : function() {
				$(this).dialog("close");
			}
		},
		close : function() {
		}
	});

});

/**
 *功能：设置活动时间和频率
 *
 *@param v 活动重复类型值
 * 
 */
function setEventDateValue(v){
	var sDate = $("#sel_start_time_"+v).text();
	var sEnd = $("#sel_end_date_time_"+v).val();
	var tmpFrequency = $("input[name='repeatFrequency_"+v+"']").val();
	

	if (!checkDate(sDate, sEnd)) {
		alert("结束日期必须大于开始日期。");
		return;
	}
	$("#event_start_date").val(sDate);
	$("#event_end_date").val(sEnd);
	$("#event_frequency").val(tmpFrequency);
	
}


/**重复类型的切换*/
function showTabType(el, v) {
	$("#repeatTypeName").val(el);
	$("#sel_start_time_" + el).text(v);
	selDatePicker("sel_end_date_time_" + el);
	$("#show_by_" + el).show();
}


/**加载UI日历*/
function selDatePicker(el) {
	$("#" + el).datepicker({
		showButtonPanel : true,
		dateFormat : 'yy-mm-dd'
	});
}

/** 判断两日期的区间规格 */
function checkDate(start, end) {
	var sDate = new Date(start);
	var sEnd = new Date(end);
	if (sDate > sEnd) {
		return false;
	} else {
		return true;
	}

}
