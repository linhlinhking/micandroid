$(function() {
	// 自动补全
	var maxcount = 0;// 表示他最大的值
	var thisCount =0;// 初始化他框的位置
	$("body").prepend("<div style='width:220px; display:none; background:#FFFFFF; position: absolute;' id='autoTxt'></div>");
	$("#address").keyup(function(even) {
		var v = even.which;
		if (v == 38 || v == 40 || v == 13)// 当点击上下键或者确定键时阻止他传送数据
			{
			return;
			}
		var txt = encodeURIComponent($("#address").val());//这里是取得他的输入框的值
		if (txt != "") {
			//拼装数据
			$.ajax({
				url : "/companyName/search",//从后台取得json数据
				type : "get",
				dataType : "json",
				data : {"name_cn" : txt},
				success : function(ls) {
					var offset = $("#address").offset();
					$("#autoTxt").show();
					$("#autoTxt").css("top", (offset.top + 30) + "px");
					$("#autoTxt").css("left", offset.left + "px");
					var Candidate = "";
					maxcount = 0;//再重新得值
					data = eval(ls.location);
					if(data.length>1){
						$.each(data, function (i, o) {
							 Candidate += "<li style='list-style:none;' id='" +maxcount+ "'>" + o.name + "</li>";
							 maxcount++;
					 		});
					}
					else{
						Candidate = "<li style='list-style:none;' id='" +maxcount+ "'>" + ls.location.name + "</li>";
						maxcount++;
					}
					$("#autoTxt").html(Candidate);
					$("#autoTxt li:eq(0)").css("background", "#CCC");
					//高亮对象
					$('body').highLight();
					$('body').highLight($("#address").val());
					event.preventDefault();
						//当单击某个ＬＩ时反映
						$("#autoTxt li").click(function(){
							$("#address").val($("#autoTxt li:eq("+this.id+")").text());
							$("#autoTxt").html("");
							$("#autoTxt").hide();
						});
						//移动对象
						$("#autoTxt li").hover(function(){
							$("#autoTxt li").css("background", "#FFFFFF");
							$("#autoTxt li:eq("+this.id+")").css("background", "#CCC");
							thisCount=this.id;},function(){
								$("#autoTxt li").css("background", "#FFFFFF");});
				},
				error : function() {
					$("#autoTxt").html("");
					$("#autoTxt").hide();
					maxcount = 0;
				}
			});
		} else {
			$("#autoTxt").hide();
			maxcount = 0;
			$("#sestart").click();
		}
	});
	//当单击ＢＯＤＹ时则隐藏搜索值
	$("body").click(function(){
		$("#autoTxt").html("");
		$("#autoTxt").hide();
		thisCount=0;
	});
	// 写移动事件//上键３８ 下键４０ 确定键 １３
	$("body").keyup(function(even) {
		var v = even.which;
			if (v == 38)// 按上键时
			{
				if(thisCount!=0){//等于零时则证明不能上了。所以获得焦点
					$("#address").blur();
					if(thisCount>0)
						--thisCount;
					else
						thisCount=0;
				$("#autoTxt li").css("background", "#FFFFFF");
				$("#autoTxt li:eq("+thisCount+")").css("background", "#CCC");
				}else{$("#address").focus();}
			} else if (v == 40) {// 按下键时
				if(thisCount<maxcount-1)
				{
					$("#address").blur();
					++thisCount;
					$("#autoTxt li").css("background", "#FFFFFF");
					$("#autoTxt li:eq("+thisCount+")").css("background", "#CCC");
				}
			} else if (v == 13) {// 按确认键时
				var tt=$("#"+thisCount).text();
				if(tt!="")
					{
						$("#address").val(tt);
						$("#autoTxt").html("");
						$("#autoTxt").hide();
					}else
					{
						if($("#address").val()!="")
						$("#sestart").click();
					}
			} else {
				if($("#autoTxt").html()!="")
					{
						$("#address").focus();
						thisCount=0;
					}
			}
	});
});