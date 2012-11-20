$(function() {
	// before submit ,please validate .
	// var v = $("#eventsCategory option:selected").val();
	// if (v == '0') {
	// alert("请选择一个类别！");
	// return false;
	// }
	$("#addEvents").tabs();
	getAutoLocation();
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

	$("#dialog-form-week").dialog(
			{
				autoOpen : false,
				height : 220,
				width : 460,
				modal : true,
				buttons : {
					"确定" : function() {
						var selectedType = $(
								"#sel_change_repeat_type option:selected")
								.val();
						var strValue = "";

						switch (selectedType) {
						case 'days':
							setEventDateValue(selectedType);
							$("#event_repeatValue").val(strValue);
							break;
						case 'weeks':
							setEventDateValue(selectedType);
							$(".sel_repeat_week div").each(function() {
								if ($(this).hasClass("selected")) {
									// strValue += $(this).text() + ",";
									strValue += this.id + ",";
								}
							});
							if (strValue == "" || strValue.length < 0) {
								alert("请选择重复的星期.");
								return;
							} else {
								strValue = strValue.substring(0,
										strValue.length - 1);
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

	/** 选择重复类型的事件处理程序 */
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

	/** 获取所有的UUID */

	/** 文件上传组件 */
	$("#events_upload").click(function() {
		setImageUUID("event");
		$("#dialog-events-upload div #fileupload").attr("action","/admin/file/upload/events");
		$("#dialog-events-upload").dialog("open");
	});

	/** 活动海报上传 */
	$("#events_upload_poster").click(function() {
		setImageUUID("poster");
		$("#dialog-events-upload div #fileupload").attr("action","/admin/file/upload/posters");
		$("#dialog-events-upload").dialog("open");
	});
	/** 活动LOGO上传 */
	$("#events_upload_icon").click(function() {
		setImageUUID("icon");
		$("#dialog-events-upload div #fileupload").attr("action","/admin/file/upload/icons");
		$("#dialog-events-upload").dialog("open");
	});
	

	
	$("#dialog-icon-upload").dialog({
		autoOpen : false,
		title : "选择活动ICON上传",
		height : 500,
		width : 820,
		modal : true,
		buttons : {
			"确定" : function() {

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

function getAutoLocation(){
	var session = window.sessionStorage;
	var $searchInput = $("#event_address_cn") ;
	$searchInput.attr('autocomplete', 'off');
	var $autocomplete = $('#event_location_autocomplete').hide().insertAfter('#event_address_button');
	var clear = function() {
		$autocomplete.empty().hide();
	};
	$searchInput.blur(function() {
		setTimeout(clear, 500);
	});
	var selectedItem = null;
	var timeoutid = null;
	var setSelectedItem = function(item) {
		selectedItem = item;
		if (selectedItem < 0) {
			selectedItem = $autocomplete.find('li').length - 1;
		} else if (selectedItem > $autocomplete.find('li').length - 1) {
			selectedItem = 0;
		}
		$autocomplete.find('li').removeClass('highlight').eq(selectedItem).addClass('highlight');
	};
	var ajax_request = function(val) {
		$.ajax({
			url : '/admin/location/search/' + $searchInput.val() ,  
			dataType : 'json', 
			type : 'get', 
			success : function(data) {
				if (data.data.length) {
					$.each(data.data, function(index, term) {
						session.setItem(term.name_cn,term.md5Value);
						$('<li></li>').text(term.name_cn).appendTo($autocomplete).addClass('clickable').hover(function() {
							$(this).siblings().removeClass('highlight');
							$(this).addClass('highlight');
							selectedItem = index;
						}, function() {
							$(this).removeClass('highlight');
							selectedItem = -1;
						}).click(function() {
							$("#event_location_md5value").val(session.getItem(term.name_cn));
							$searchInput.val(term.name_cn);
							$autocomplete.empty().hide();
						});
					});
					//var ypos = $searchInput.position().top;
					//var xpos = $searchInput.position().left;
					$autocomplete.css('width', $searchInput.css('width'));
					$autocomplete.css({
						'position' : 'absolute',
						'left' : "167px",
						'top' : "192px"
					});
					setSelectedItem(0);
					$autocomplete.show();
				}
			}
		});
	};
	$searchInput.keyup(function(event) {
		if (event.keyCode > 40 || event.keyCode == 8 || event.keyCode == 32) {
			$autocomplete.empty().hide();
			clearTimeout(timeoutid);
			timeoutid = setTimeout(ajax_request, 100);
		} else if (event.keyCode == 38) {
			if (selectedItem == -1) {
				setSelectedItem($autocomplete.find('li').length - 1);
			} else {
				setSelectedItem(selectedItem - 1);
			}
			event.preventDefault();
		} else if (event.keyCode == 40) {
			if (selectedItem == -1) {
				setSelectedItem(0);
			} else {
				setSelectedItem(selectedItem + 1);
			}
			event.preventDefault();
		}
	}).keypress(function(event) {
		if (event.keyCode == 13) {
			if ($autocomplete.find('li').length == 0 || selectedItem == -1) {
				return;
			}
			$("#event_location_md5value").val(session.getItem($autocomplete.find('li').eq(selectedItem).text()));
			$searchInput.val($autocomplete.find('li').eq(selectedItem).text());
			$autocomplete.empty().hide();
			event.preventDefault();
		}
	}).keydown(function(event) {
		if (event.keyCode == 27) {
			$autocomplete.empty().hide();
			event.preventDefault();
		}
	});

}

function setImageUUID(type){
	
	$("#files_data_body").html("");
	$("#dialog-events-upload").dialog({
		autoOpen : false,
		title : "选择图片上传",
		height : 500,
		width : 820,
		modal : true,
		buttons : {
			"确定" : function() {
				setHiddenUUIDByType(type);
				$(this).dialog("close");
			},
			"取消" : function() {
				$(this).dialog("close");
			}
		},
		close : function() {
		}
	});
}

function setHiddenUUIDByType(type){
	var tempUUIDValue = "";
	$(":input[name='uuid_value']").each(function() {
		tempUUIDValue += this.value + ",";
	});
	
	tempUUIDValue = tempUUIDValue.substring(0,
			tempUUIDValue.length - 1);
	if("icon" === type){
		setMessage("events_upload_icon_message",tempUUIDValue) ;
		$("input[name='eventIcon']").val(tempUUIDValue);
	}else if("poster" === type){
		setMessage("events_upload_poster_message",tempUUIDValue) ;
		$("input[name='posterImage']").val(tempUUIDValue);
	}else{
		setMessage("events_upload_message",tempUUIDValue) ;
		$("input[name='events_uuids']").val(tempUUIDValue);
	}

}

function setMessage(e,v){
	if("" == v){
		$("#" + e).text("");
	}else{
		$("#" + e).text("图片已成功上传.");
	}
}

/**
 * 功能：设置活动时间和频率
 * 
 * @param v
 *            活动重复类型值
 * 
 */
function setEventDateValue(v) {
	var sDate = $("#sel_start_time_" + v).text();
	var sEnd = $("#sel_end_date_time_" + v).val();
	var tmpFrequency = $("input[name='repeatFrequency_" + v + "']").val();

	if (!checkDate(sDate, sEnd)) {
		alert("结束日期必须大于开始日期。");
		return;
	}
	$("#event_start_date").val(sDate);
	$("#event_end_date").val(sEnd);
	$("#event_frequency").val(tmpFrequency);

}

/** 重复类型的切换 */
function showTabType(el, v) {
	$("#repeatTypeName").val(el);
	$("#sel_start_time_" + el).text(v);
	selDatePicker("sel_end_date_time_" + el);
	$("#show_by_" + el).show();
}

/** 加载UI日历 */
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

function ajaxFileUpload(url, loadingEl,formEl) {
	$("#" + loadingEl).ajaxStart(function() {
		$(this).show();
	}).ajaxComplete(function() {
		$(this).hide();
	});
	
	var opt = optionAjaxForm(url);
	$("#"+formEl).ajaxSubmit(opt); 
	return false;
}

function optionAjaxForm(url) {
	var options = {
		url : url,
		type : "POST",
		dataType : "json",
		success : function(json) {
			if (json != "" && json != null && json != 'undefined') {
				var imgUrl = json.url ;
				$("#showImage").html("<img src='"+imgUrl+"'/>");
			}
		}
	};
	
	return options ;
}
