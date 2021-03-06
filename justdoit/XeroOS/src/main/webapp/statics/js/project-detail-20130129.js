    window.addEvent('domready', function(){
		var $ = jQuery;
		var initRateValue = $("input[name='rate_value']").val();
		var slider = new Slider('project_schedule_f', 'slider_btn_t', {
			onComplete: function(val){
				jQuery("#action_num").html(Math.round(val) + "%");
				var projectId = $("input[name='current_project_id']").val();
				var rateValue = Math.round(val);
				$.ajax({
					url : "/slide-add",
					data : "projectId=" + projectId + "&rateValue=" + rateValue,
					type : "post"
				});
			},
		});
		slider.set(Number(initRateValue));
	});
	
	jQuery(function(){
		var $ = jQuery;
		
		var start_time = $(".start_date").html();
		var end_time = $(".end_date").html();
		
		$(".show_icon").toggle(function() {
		    $(this).addClass("not_show");
		},function(){
			$(this).removeClass("not_show");
		});
		
		$(".show_icon").live("click",function(){
			var noteId = $(this).attr("id").substring(4);
			if($(this).hasClass("not_show") ){
				$(this).removeClass("not_show") ;
				updateCustomer(noteId,1);
			}else{
				 $(this).addClass("not_show");
				 updateCustomer(noteId,0);
			}
			
		});
		
		var nowDate = getServerTime();
        
        if(new Date(initTimeFormat(nowDate)) >= new Date(timeFormat(start_time)) && new Date(initTimeFormat(nowDate)) <= new Date(timeFormat(end_time))){
            $(".today_line").css("left",(ratio(start_time, end_time, nowDate) ) + "%");
        }else if(new Date(initTimeFormat(nowDate)) < new Date(timeFormat(start_time))){
            $(".today_line").css("left",(ratio(start_time, end_time, new Date(timeFormat(start_time))) ) + "%");
        }else if(new Date(initTimeFormat(nowDate)) > new Date(timeFormat(end_time))){
             $(".today_line").css("left",(ratio(start_time, end_time, new Date(timeFormat(end_time))) ) + "%");
        }
	
		var max_name = 0;
		var add_note_bg_flag = null;
		$(".note_point, .white_point").live("click", function(){
			$(".note_point, .white_point, .red_point").removeClass("red_point").addClass("white_point");
			$(this).removeClass("white_point").addClass("red_point");
			$(".add_note_bg, .note_bg").hide();
			var id;
			$(this).attr("id") ? id = $(this).attr("id").substring(1) : $(".add_note_bg").show();
			$("#" + id).show();
			$(".note_lists li").hide();
			var name = $(this).attr("name");
			
			$("li[name='" + name + "']").show();
			if(add_note_bg_flag == "block"){
				$(".note_lists").css("top","105px");
				var id = $("li[name='" + name + "']").attr("id");
				$("#" + id).removeClass("middle_line");
				$(".note_bg").hide();
			}else{
				if(name != max_name){
					$(".note_lists").css("top","105px");
					var id = $("li[name='" + name + "']").attr("id");
					$("#" + id).removeClass("middle_line");
				}else{
					$(".note_lists").css("top","260px");
				}
			
			}
		});
		
        $(".cancel_button").click(function(){
            $("textarea").val('');
        });
        
		$(".note_button").click(function(){
			if(new Date(initTimeFormat(nowDate)) >= new Date(timeFormat(start_time)) && new Date(initTimeFormat(nowDate)) <= new Date(timeFormat(end_time))){
               //return true; 
            }else{
               return false;
            }
            $(".add_note_bg").hide();
			$(".note_bg").remove();
            addNote();
			onload(true,false);
			
		});
		var project_detail_supply = $("#project_detail_supply").val();
		if(project_detail_supply != "project_detail_supply"){
			onload(false,true);
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
	   
		
		// 1 √  0 X
		function addNote(){
			var projectId = $("input[name='current_project_id']").val();
			var userId =($("input[name='current_user_id']").val()==undefined || $("input[name='current_user_id']").val()=='undefined') ? '':$("input[name='current_user_id']").val();
			var content = $("textarea").val();
			var landmarkDate = timeFormatEng(nowDate);
			var showCustomer = $(".show_cus").children().hasClass("not_show") ? 0 : 1;
			var creator = $("input[name='current_creator']").val();
			$.ajax({
				url : "/note-add",
				data : "projectId=" + projectId + "&userId=" + userId + "&content=" + content + "&landmarkDate=" + landmarkDate + "&showCustomer=" + showCustomer + "&creator=" + creator,
				type : "post",
				success : function(e){
					if(e.result){
						$("textarea").val('');
						$(".note_point, .note_bg").empty();
						location.reload();
					}
				}
				
			});
		}
		
		function timeFormatEng(t){
			t = new Date(t);
			var y = t.getFullYear();
			var m = t.getMonth() + 1;
			var d = t.getDate();
			return d + '/' + m + '/' + y;			
		}
		
		function initNoteContentFlag(e){
			var creator = $("input[name='current_creator']").val();
			for(var i = 0, j = e.data.length; i < j ; i++){
				var data = e.data[i];
				if(initTimeFormat(data.landmarkDate) == initTimeFormat(nowDate.getTime())){
					if(creator == data.creator){
						$(".add_note_bg").hide();
					}else{
						$(".add_note_bg").show();
						
					}
				}else{
					$(".add_note_bg").show();
				}
			}
		}
		
		function onload(f,s){
			$.getJSON("/note-list",{projectId : $("input[name='current_project_id']").val()}, function(e){
				if(e.result){
					var time = nowDate;
					var k = 0;
					var tem = 0;
					var tem_i = 0;
					for(var i = 0, j = e.data.length; i < j; i ++){
						var data = e.data[i];
						if(data.id > tem){
							tem = data.id;
							tem_i = i;
							max_name = data.landmarkDate;
						}
					}
					initNoteContent(tem, e.data[tem_i].creator, timeFormatEng(e.data[tem_i].landmarkDate), e.data[tem_i].content, e.data[tem_i].showCustomer);
					for(var i = 0; i < e.data.length; i ++){
						var data = e.data[i];
						initNote(start_time, end_time, data.landmarkDate, data.id, data.landmarkDate);
						data.creator = (data.creator == '' || data.creator == null) ? '' : data.creator;
						noteContentList(data.creator, timeFormatEng(data.landmarkDate), data.content, data.landmarkDate , i);
						$(".note_bg").each(function(e){
							var t = Number($(this).attr("id"));
							if(t >= k){
								k = t;
							}
						});
						if(s){
							var mark = initTimeFormat(data.landmarkDate);
							var t = initTimeFormat(time.getTime());
							if(mark == t){
								$("#" + k).show();
								$(".note_point, .white_point, .red_point").removeClass("red_point");
								$("#p" + k).addClass("red_point");
							}
						}
						if(f){
							$("#" + k).show();
							$(".note_point, .white_point, .red_point").removeClass("red_point");
							$("#p" + k).addClass("red_point");
						}else{
							$(".add_note_bg").show();
						}
					}
					$(".middle_line").hide();
					for(var i = 0, j = e.data.length; i < j; i ++){
						var data = e.data[i];
						if(initTimeFormat(data.landmarkDate) == initTimeFormat(nowDate.getTime())){
							$("li[name='" + data.landmarkDate + "']").show();
						}
					}
					
					initNoteContentFlag(e);
					add_note_bg_flag = $(".add_note_bg").css("display");
					
				}
			});
		}
		function ratio(start_time, end_time, today_time){// mark's ratio
			start_time = timeFormat(start_time);
			end_time = timeFormat(end_time);
			var s = new Date(start_time).getTime();
			var e = new Date(end_time).getTime();
			
			
			var y = today_time.getFullYear();
			var m = Number(today_time.getMonth() + 1) < 10 ? "0" + (today_time.getMonth() + 1):today_time.getMonth() + 1;
			var d = today_time.getDate() < 10 ? "0"+today_time.getDate():today_time.getDate();
			var t = new Date(y + "-" + m + "-" + d).getTime();
			
			return  (t-s)/(e-s)*100;
		}
		
		function noteContentList(n, t, c, ln, i){//n:name t:time c:content ln:this list name i:id
			$(".note_lists").append('<li class="middle_line" id="li' + i + '" name="' + ln + '">'
              						+ '<span class="note_name">' + n + '</span>'
              						+ '<span class="note_date">' + t + '</span>'
              						+ '<p>' + c + '</p></li>');
		}
		
		function initNoteContent(i, u, t, c, f){//i : id, u : user name, t : time, c : note content  
			var html = '<div class="note_bg" id="' + i + '">'
            			+ '<div class="note_content">'
              			+ '<div class="note_top"><span class="left_span">' + u + '</span><span class="right_span">' + t + '</span></div>'
              			+ '<div class="note_bottom">'
                		+ '<p>' + c + '</p>';
                		
                		if(f){
                			html += '<div class="show_cus"><span class="show_icon" id="note'+i+'"></span><span class="show_text">Show to Customer</span></div>';
                		}else{
                			html += '<div class="show_cus"><span class="show_icon not_show" id="note'+i+'"></span><span class="show_text">Show to Customer</span></div>';
                		}
                		
                		html += '</div></div> </div>';	
              			
			$(".add_note_field").append(html);
		}
		function initNote(s, e, t, i, n){//s:start time, e:end time t:now time
			$(".note_point_field").append('<span id="p' + i + '" name="' + n + '" class="note_point white_point" style="left:'+(ratio(s, e, noteTimeFormat(t)) )+'%;"></span>');// 2.8%   96%
		}
		function noteTimeFormat(t){
			var t = new Date(t);
			var y = t.getFullYear();
			var m = Number(t.getMonth() + 1) <10 ? "0" + (t.getMonth() + 1) : t.getMonth() + 1;
			var d = Number(t.getDate()) < 10 ? "0" + t.getDate() : t.getDate();
			return  new Date(y + "-" + m + "-" + d);
		}
		function timeFormat(d){
			var str = String(d).split("/");
			var temp = str[2] + "-" + str[1] + "-" + str[0];
			var date = new Date(temp);
			var d = Number(date.getDate()) < 10 ? "0" +  date.getDate() :  date.getDate();
			var m = Number(date.getMonth()) < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
			var y = date.getFullYear();
			return y + "-" + m + "-" + d;
		}
		function initTimeFormat(t){
			var t = new Date(t);
			var y = t.getFullYear();
			var d = Number(t.getDate()) < 10 ? "0" +  t.getDate() :  t.getDate();
			var m = Number(t.getMonth()) < 10 ? "0" + (t.getMonth() + 1) : t.getMonth() + 1;
			return y + "-" + m + "-" + d;
		}
		function updateCustomer(noteId,showCustomer){
			$.ajax({
				url : "/update-customer",
				data : "noteId=" + noteId + "&showCustomer=" + showCustomer,
				type : "post"			
			});
		}
		
		
	});
	
	