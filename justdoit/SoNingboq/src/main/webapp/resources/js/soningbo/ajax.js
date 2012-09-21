/*** Ajax Code ***/
var xmlhttp

var liveurl = "http://www.soningbo.com";
var apiurl = "http://api.soningbo.com";

var selectId ;

function showNotice(type){
		$(".top_alert").hide();
		
		switch(type){
		case "error":
			showMessage("send_error");
			break;
		case "success":
			showMessage("send_accept");
			break;
		case "empty":	
			showMessage("send_null");
			break;
		case "nodata":
			showMessage("no_data");
			break;
		case "dataerror":
			showMessage("data_error");
			break;
		case "operatesuccess":
			showMessage("operate_success");
			break;
		case "operatefailed":
			showMessage("operate_failed");
			break;
		case "operateerror":
			showMessage("operate_error");
			break;
		case "updatesuccess":
			showMessage("update_success");
			break;
		case "updatefailed":
			showMessage("update_failed");
			break;
		case "addsuccess":
			showMessage("add_success");
			break;
		case "removesuccess":
			showMessage("remove_success");
			break;
		case "repeatadd":
			showMessage("repeat_add");
			break;
		case "notsupport":
			showMessage("not_support");
			break;
		case "signerror":
			showMessage("sign_error");
			break;
		case "nofriend":
			showMessage("no_friend");
			break;
		default:
			;
		}
	}
	
function showMessage(element){
		$("."+element).fadeIn(function(){
                        setTimeout("$('."+element+"').animate({top:'140px',opacity:'0'},{duration:500,easing:'easeOutCirc',complete:function(){$('."+element+"').hide().css({'top':'15px','opacity':'1'});}});",2000)
			});
	}
	

function getCurrentLanguage(){
		//id:current_selected_language
		var lng = $("input[name='lang']").val();
		if("" != lng && lng != 'undefined' && lng != null){
			return lng ;	
		}	
		return "cn" ;
	}

$(document).ready(function () {
	//Get User's Profile
	var currentUserId = $("input[name='userId']").val();
	

	
	//change user's image
	$.getJSON(apiurl + "/resource/user/show/" + currentUserId  , function(json){
		   var imgPath = json.photo_path ;
		   if(typeof(imgPath) == 'undefined' || null == imgPath || '0' == imgPath){
			   imgPath = "/images/username_photo.jpg" ;
			}else{
			   imgPath = apiurl + "/upload/" + imgPath.substring(0,4) + "/" + imgPath.substring(4,8) + "/" + imgPath.substring(8,12) + "/" + imgPath.substring(12)+"-100x100" ;
			}
			$("#photopath").attr("src",imgPath);
			$("#photopath_2").attr("src",imgPath);
			$("#show_following_number").html(json.following + "<span><span>");
			$("#show_followed_number").html(json.followed + "<span><span>");
			
		 });
	
	//save feedback
	
	$("#send").click(function(){
			var feedEmail = $("#feedEmail").val();
			var content=$("#feedback_content").val();
			
			if("" == content || content == 'undefined' || content.length < 0){
				showNotice("empty");
				return ;
			}
			$.ajax({
				type:"post",
				url:apiurl+"/resource/feedback/add",
				data:"key=soningbo&md5Value="+currentUserId+"&feedEmail="+feedEmail+"&content="+content,
				dataType:"json",
				success:function(json){
					if("true"==json.result){
						showNotice("success");
						
					}
					else{
						showNotice("error");
					}
				},
				complete:function(){
					$('#send_feedback1').fadeOut('fast', function () {
           				 $("#send_feedback").fadeOut('fast');
       				});
					$("#feedback_content").val("");
				}
			});
		});

//----------GET WINDOW SIZE ----------//
    windowWidth = $(window).width();
    windowHeight = $(window).height();

//------CLICK AVATAR-----------//
    $('.personal_photo').mouseover(function () {
        $('.photo_right').animate({
            left: '55'
        }, {
            queue: false,
            duration: 500,
            easing: 'easeOutBounce'
        });
    });

//------MOUSE OUT AVATAR-----------//
    $('.personal_photo, .photo_right').mouseout(function () {
        timer = setTimeout("hide_avatar()", 1);
    });

//------ KEEP OPEN  AVATAR-----------//
    $('.personal_photo, .photo_right').mouseover(function () {
        clearTimeout(timer);
    });

//------ FEEDBACK TO FADE IN-----------//
    $('#showFeedbackTab').click(function () {
        $('#send_feedback').fadeIn('fast', function () {
            $("#send_feedback1").fadeIn('fast');
        });
    });

//------ FEEDBACK TO FADE OUT-----------//
    $('#send_feedback, #closefeedback').click(function () {
        $('#send_feedback1').fadeOut('fast', function () {
            $("#send_feedback").fadeOut('fast');
        });
    });

//-------- FEEDBACK HEIGHT -------------//
    $("#send_feedback").css("height", windowHeight);

//-------- FEEDBACK FORM CENTER HEIGHT -------------//
    $(".feedback_form").css("top", windowHeight / 2 - 150);


//------ NOTIFICATIONS SLIDE DOWN -----------//
    $('#showNotificationTab').click(function () {
        $('.notification_content_title, .notification_main').show()
        $('.notification_main').animate({
            height: '120px'
        }, {
            queue: false,
            duration: 500,
            easing: 'easeOutBounce'
        });	
    });
	
//------ KEEP OPEN  NOTIFICATIONS-----------//
    $('.notification_content_title, .notification_main').mouseover(function () {
        clearTimeout(timer);
    });
	
//------NOTIFICATIONS SLIDE UP-----------//
    $('.notification_content_title, .notification_main').mouseout(function () {
       timer = setTimeout("hide_notifications()", 1);
    });

//------NOTIFICATIONS CLICK SLIDE UP-----------//
    $('.notification_content_title').click(function () {
      hide_notifications();

    });
	
//------ USERNAME SLIDE DOWN -----------//
    $("#userTabInfo").hide();
    $('#showUsernameTab').click(function () {
        $('.username_content_title, .username_main').show()
        $('.username_main').animate({
            height: '166px'
        }, {
            queue: false,
            duration: 500,
            easing: 'easeOutBounce'
        });	
    });
	
//------ KEEP OPEN  USERNAME-----------//
    $('.username_content_title, .username_main').mouseover(function () {
        clearTimeout(timer);
    });
	
//------USERNAME SLIDE UP-----------//
    $('.username_content_title').mouseout(function () {
       timer = setTimeout("hide_username()", 1);
    });

//------USERNAME CLICK SLIDE UP-----------//
    $('.username_content_title').click(function () {
      hide_username();

    });


//------ CATEGORY 1 SELECT LIST-----------//	
    $('.column_select span').click(function () {
        $("#select_list_st").fadeIn('fast');
    });
	
//------ CATEGORY 2 SELECT LIST-----------//	
    $('#cat2 span').click(function () {
        $("#select_list_nd").fadeIn('fast');
    });

//------ MAP TOGGLE-----------//
$('#map_canvas').css("height", 0.45*windowHeight);
    $('#hide_map').toggle(function () {
        $(".place_map, #map_canvas").animate({
            height: '0'
        }, {
            duration: 400,
            easing: 'easeInQuad'
        });
        $(".middle_column").css("top", "-1px");
        $(".middle_column").css("border-top-right-radius", "5px");
        $(".middle_column").css("-moz-border-top-right-radius", "5px");
        $(".middle_column").css("-webkit-border-top-right-radius", "5px");
        $("#hide_map").html("Show Map");
		$(".hide_map_cn").html("显示地图");
		$(".arrow_img").hide();
		$("#go_up").hide();
		$(".cat2_arrow_img").hide();
		$("#go_up_nd").hide();
		$(".friends_lists").css("height", "410px");
    }, function () {
        $(".place_map, #map_canvas").animate({
            height: '425'
        }, {
            duration: 400,
            easing: 'easeOutQuad'
        });
        $("#hide_map").html("Hide Map");
		$(".hide_map_cn").html("隐藏地图");
        $(".middle_column").css("top", "0px");
        $(".middle_column").css("border-radius", "0px");
        $(".middle_column").css("-moz-border-radius", "0px");
        $(".middle_column").css("-webkit-border-radius", "0px");
		$(".arrow_img_down").hide();
		$("#go_down").hide();
		$(".cat2_arrow_img_down").hide();
		$("#go_down_nd").hide();
		$(".friends_lists").css("height", "275px");
    });
//------ CATEGORY 1 CLICK-----------//
var liNum = 0;
var overHight = 0;

$('#cat1').click(function(){
if($(".place_map").height()!=0){
	if($("#go_up").is(":hidden") == true){
 	  hidingst();
	}else{
	  hidingnd();
	}
}else{
  
   if($("#go_down").is(":hidden") == true){
	showingst();
   }else{
        showingnd();
  }

}


});

$('#cat2').click(function(){
if($(".place_map").height()!=0){
  if($("#go_up_nd").is(":hidden") == true){
      upst();
  }else{
      upnd();
 }

}else{
  if($("#go_down_nd").is(":hidden") == true){
 	 downst();
  }else{
  	 downnd();
  }
}


});
function hidingst() {
	$("#go_up_nd").hide();
	$("#go_up").show();
	$("#go_up").animate({
		top:'-240',
		height:'240'
		},{
			 queue: false,
			 duration:250,
			 easing:'easeOutCirc'});
	}
//hide the first category up
function hidingnd() {
	 $("#go_up").animate({
	 	 height:'0',
		 top:'0'
		 },{
			 queue: false,
			 duration:250,
			 easing:'easeOutCirc',
			 complete: function () {
				 $("#go_up").hide();
				}
			 });
	}
//show the first category  down
function showingst() {
	$("#go_up").hide();
	$("#go_down_nd").hide();
	$("#go_down").show();
	$("#go_down").animate({
		height:'240'
		},{
			queue: false,
			duration:250,
			easing:'easeOutCirc'});
	}
//hide the first category down
function showingnd() {
 	$("#go_up").hide();
	$("#go_down").animate({
	 	 height:'0'
		 },{
			 queue: false,
			 duration:250,
			 easing:'easeOutCirc',
			 complete: function (){
				$("#go_down").hide();
				 }
			 });
	}
//------ CATEGORY 2 CLICK-----------//
function upst() {
	$("#go_up").hide();
	$("#go_up_nd").show();
	$("#go_up_nd").animate({
		top:'-' + overHeight,
		height:overHeight
		},{
			 queue: false,
			 duration:250,
			 easing:'easeOutCirc'});
	}
//the second category hide up
function upnd() {
	 $("#go_up_nd").animate({
	 	 height:'0',
		 top:'0'
		 },{
			 queue: false,
			 duration:250,
			 easing:'easeOutCirc',
			 complete: function () {
				 $("#go_up_nd").hide();
				 }
			});
	}
//the second category show down
function downst() {
	$("#go_up_nd").hide();
	$("#go_down").hide();
	$("#go_down_nd").show();
	$("#go_down_nd").animate({
		height:overHeight
		},{
			queue: false,
			duration:250,
			easing:'easeOutCirc'});  
	}
//the second category hide down
function downnd() {
	$("#go_up_nd").hide();
	$("#go_down_nd").animate({
	 	 height:'0'
		 },{
			 queue: false,
			 duration:250,
			 easing:'easeOutCirc',
			 complete: function (){
			   $("#go_down_nd").hide();
				}
			 });
}


// ------ CATEGORY 1 CLICK -------------------//
function htmldecode(str) {
 str = str.replace(/&amp;/gi, '&');
 str = str.replace(/&nbsp;/gi, ' ');
 str = str.replace(/&quot;/gi, '"');
 str = str.replace(/&#39;/g, "'");
 str = str.replace(/&lt;/gi, '<');
 str = str.replace(/&gt;/gi, '>');
 str = str.replace(/<br[^>]*>(?:(rn)|r|n)?/gi, 'n');
 return str;
}

 $('#go_up ul li').click(function () {
  $('#c1').text(htmldecode($(this).html()));
	show_AllContent();
	var reqUrl = apiurl + '/resource/category/second/show/' + this.id; 
	$('#go_up_nd ul li').remove();
	$.getJSON(reqUrl,function(json){
		showCategoryJSONData("go_up_nd ul",json,"go_up_ul_cat2 li");
	   });
	});
	

	
function bindEvendByCat2(v){
	$('#' + v).unbind('click.cat2up').bind('click.cat2up',function(){
					selectId = this.id;
					$('#c2').text($(this).html());
					$('#searchResponse li').remove();
	});
}
	
function showCategoryItem(v,item){
	var tmp = getCurrentLanguage();
	if(tmp === "en"){
		$('#' + v).append("<li id="+ item.id+">"+ item.name_en +"</li>");
	}else{
		$('#' + v).append("<li id="+ item.id+">"+ item.name_cn +"</li>");
	}
}

function show_AllContent(){
	var tmp = getCurrentLanguage();
	if(tmp === "en"){
		$('#c2').text(htmldecode('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;All&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'));
	}else{
		$('#c2').text(htmldecode('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;所有&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'));	
	}
}

function showCategoryJSONData(v,json,b){
	if(json == null || json =='undefined'){
		return ;	
	}
	var n = 0;
	var d = json.data ;
	n = d.length ;
	
	if(n > 0){
		liNum = Math.ceil(n/4);
		overHeight = (liNum + 1) * 30 ;	
		$.each(d,function(id,item){
				showCategoryItem(v,item);
		});	
	}else{
		n = 1 ;
		liNum = Math.ceil(n/4);
		overHeight = (liNum + 1) * 30 ;	
		showCategoryItem(v,d);
	}
	bindEvendByCat2(b);
}



$('#go_down ul li').click(function () {
  $('#c1').text(htmldecode($(this).html()));
	show_AllContent();
	var reqUrl = apiurl + '/resource/category/second/show/' + this.id; 
	$('#go_down_nd ul li').remove();
	$.getJSON(reqUrl,function(json){
		showCategoryJSONData("go_down_nd ul",json,"go_down_ul_cat2 li");
	   }) ;
}) ;


// ------ TOP_BAR CLICK -------------------//
 $('#top_bar_blank div').click(function () {
	  $(this).prevAll().removeClass('tab_focus');
	  $(this).nextAll().removeClass('tab_focus');
     
      if(this.id == 'info_tab'){
      	$('#info_tab').addClass('tab_focus');
      	$('.tab_shadow').css('left','-4px');
		$('.photo_content').css('display','block');
		$('.map_content').css('display','none');
		$('.review_content').css('display','none');
      }
      if(this.id == 'map_tab'){
      	$('#map_tab').addClass('tab_focus');
      	$('.tab_shadow').css('left','71px');
		$('.photo_content').css('display','none');
		$('.map_content').css('display','block');
		$('.review_content').css('display','none');
		initialize();
      }
      if(this.id == 'review_tab'){
      	$('#review_tab').addClass('tab_focus');
      	$('.tab_shadow').css('left','146px');
		$('.photo_content').css('display','none');
		$('.map_content').css('display','none');
		$('.review_content').css('display','block');
      }
    });
//------ ADD_MORE_FADE TO FADE IN-----------//
    $('.big_photo, .small_photo_list .small_photo_shadow').click(function () {
        $('#send_feedback').fadeIn('fast', function () {
            $(".big_detail_photo").fadeIn('fast',function(){
				$(".detail_photo").css("margin-top",255 - $(".detail_photo").height()/2);
				});
        });
    });

//------ ADD_MORE_FADE TO FADE OUT-----------//
    $('#send_feedback').click(function () {
        $('.big_detail_photo').fadeOut('fast', function () {
            $("#send_feedback").fadeOut('fast');
        });
    });

//-------- ADD_MORE_FADE SIZE -------------//
    $(".add_more_fade").css("height", windowHeight);
	
	
//-------- DETAIL_PHOTO_BOTTOM HOVER -------------//
$(".detail_photo_bottom").hide();
$(".big_detail_photo").hover(
  function () {
    photo_up();
  },
  function () {
    photo_down();
  }
);

function photo_up() {
	$(".detail_photo_bottom").show();
	$(".detail_photo_bottom").animate({
		bottom:'0',
		height:'60'
		},{
			 queue: false,
			 duration:300,
			 easing:'easeOutCirc'});
	}
function photo_down() {
	$(".detail_photo_bottom").show();
	$(".detail_photo_bottom").animate({
		height:'0',
		bottom:'0'
		},{
			 queue: true,
			 duration:300,
			 easing:'easeOutCirc',
			 
			 complete: function() {
      $(".detail_photo_bottom").hide();
    }
			 
			
		});
	}
//-------- BIG_DETAIL_PHOTO POSITION -------------//
var leftPosition = 0;
var topPosition = 0;

function resize_window(){
	
	leftPosition=(windowWidth-$(".big_detail_photo").width())/2
    topPosition=(windowHeight-$(".big_detail_photo").height())/2
    $(".big_detail_photo").css("left", leftPosition);
    $(".big_detail_photo").css("top", topPosition);
}

resize_window();

$(window).resize(function (){
	windowWidth = $(window).width();
    windowHeight = $(window).height();
	$(".add_more_fade").css("height", windowHeight);
	resize_window();
});
//-------- DETAIL_PHOTO CHANGE -------------//

$(window).load(function () {
            $("div.big_detail_photo .detail_photo").each(function () {
                DrawImage(this, 420, 500);
            });
			
			$('input[id=part1]:radio').attr('checked','checked');
			
			$(".alert_div").animate({
		       height:'38'
		             },{
			   queue: false,
			   duration:800,
			   easing:'easeOutCirc'
			 });
			
			
        });
        function DrawImage(ImgD, FitWidth, FitHeight) {
            var image = new Image();
            image.src = ImgD.src;
			if (image.width / image.height >= FitWidth / FitHeight) {
                    if (image.width > FitWidth) {
                        ImgD.width = FitWidth;
                        ImgD.height = (image.height * FitWidth) / image.width;
                    } else {
                        ImgD.width = image.width;
                        ImgD.height = image.height;
                    }
                } else {
                    if (image.height > FitHeight) {
                        ImgD.height = FitHeight;
                        ImgD.width = (image.width * FitHeight) / image.height;
                    } else {
                        ImgD.width = image.width;
                        ImgD.height = image.height;
                    }
                }
			
        }

//-------- STAR_RATING HOVER -------------//
$(".location_rating").hide();
$("#location_over_all").hover(
  function () {
    location_rating_fadeIn();
  },
  function () {
    location_rating_fadeOut();
  }
);
function location_rating_fadeIn() {
	$(".location_rating").fadeIn('fast');
	}
function location_rating_fadeOut() {
	$(".location_rating").fadeOut('fast');
	}
//-------- LEFT_REVIEW_PHOTO SLIDE -------------//
$(".for_others_detail").hide();
$(".left_total").hover(
  function () {
    left_review_photo_up();
  },
  function () {
    left_review_photo_down();
  }
);
function left_review_photo_up() {
	$(".for_others_detail").show();
	$(".for_others_detail").animate({
		top:'0',
		height:'150'
		},{
			 queue: false,
			 duration:100,
			 easing:'easeOutCirc'});
	$(".left_review_photo").animate({
		height:'0'
		},{
			 queue: false,
			 duration:100,
			 easing:'easeOutCirc'});
	}
function left_review_photo_down() {
	$(".left_review_photo").animate({
		height:'150'
		},{
			 queue: false,
			 duration:100,
			 easing:'easeInQuart'});
	$(".for_others_detail").animate({
		height:'0',
		top:'150'
		},{
			 queue: false,
			 duration:100,
			 easing:'easeInQuart',
			 complete: function() {
      $(".for_others_detail").hide();
    }
	});
	}
//-------- RIGHT_REVIEW_PHOTO SLIDE -------------//
$(".for_others_detail_right").hide();
$(".right_total").hover(
  function () {
    right_review_photo_up();
  },
  function () {
    right_review_photo_down();
  }
);
function right_review_photo_up() {
	$(".for_others_detail_right").show();
	$(".for_others_detail_right").animate({
		top:'0',
		height:'150'
		},{
			 queue: false,
			 duration:100,
			 easing:'easeOutCirc'});
	$(".right_review_photo").animate({
		height:'0'
		},{
			 queue: false,
			 duration:100,
			 easing:'easeOutCirc'});
	}
function right_review_photo_down() {
	$(".right_review_photo").animate({
		height:'150'
		},{
			 queue: false,
			 duration:100,
			 easing:'easeInQuart'});
	$(".for_others_detail_right").animate({
		height:'0',
		top:'150'
		},{
			 queue: false,
			 duration:100,
			 easing:'easeInQuart',
			 complete: function() {
      $(".for_others_detail_right").hide();
    }
	});
	}
//-------- SELF_REVIEW BUTTON CLICK-------------//  
    $('.review_submit').click(function () {
        $('#send_feedback').fadeIn('fast', function () {
            $(".review_field").fadeIn('fast');
        });
    });
    $('#send_feedback, .cancel_button, .publish_button').click(function () {
        $('.review_field').fadeOut('fast', function () {
            $("#send_feedback").fadeOut('fast');
        });
    });
	$(".review_field").css("left", windowWidth / 2 - 300);
    $(".review_field").css("top", windowHeight / 2 - 162);
//-------- FORGET REMEMBER CHANGE-------------//
$('.forget').click(function(){
	$(".alert_box").hide();
	if($.browser.msie){
		$('.login_section').hide();
		$('.forgoten_section').show();
	}else {
		$(".type_section").css({
		'-webkit-transform'	: 'rotate3d(0,1,0,180deg)',
		'-moz-transform'	: 'rotate3d(0,1,0,180deg)',
		'-o-transform'		: 'rotate3d(0,1,0,180deg)',
		'-ms-transform'		: 'rotate3d(0,1,0,180deg)',
		'transform'			: 'rotate3d(0,1,0,180deg)'
		});
	}
});
$('.forget_back').click(function(){
	if($.browser.msie){
		$('.forgoten_section').hide();
		$('.login_section').show();
	}else{
		$(".type_section").css({
		'-webkit-transform'	: 'rotate3d(0,1,0,0deg)',
		'-moz-transform'	: 'rotate3d(0,1,0,0deg)',
		'-o-transform'		: 'rotate3d(0,1,0,0deg)',
		'-ms-transform'		: 'rotate3d(0,1,0,0deg)',
		'transform'			: 'rotate3d(0,1,0,0deg)'
		});
	}
});
//-------- ACCOUNT FIELD SLIDER -------------//
$('.other_account').click(function(){
	$('.account_field').show().animate({
		height:'25px'
		},{
			 queue: false,
			 duration:500,
			 easing:'easeOutCirc'
	});
});
$('.account_field').click(function(){
	$('.account_field').hide(function(){
		$('.account_field').css("height", "0px");
	});
});
//-------- FAVORITE CLICK ANIMATE -------------//

//----------AUDIO FUN ----------//
var audio_throw = $("#audio_throw")[0];

$('.animate_star').hide();
$('.add_to_favorite').click(function(){
	audio_throw.play();
	$('.animate_star').show();
	$('.animate_star').animate({
		width:'50px',
		height:'50px',
		top:'-120px',
		left:'3px',
		opacity:'0'
		},{
		duration:600,
		queue:false,
		easing:'swing',
		complete:function(){
			$('.animate_star').hide().css({
				'width':'26px',
				'heifht':'26px',
				'top':'5px',
				'left':'15px',
				'opacity':'1'
			});
			}
		});
	});
// ------ MIDDLE_COLUMN BAR CLICK AND GET LEFT VALUE -------------------//
var c1WidthLoad = 0 ;
c1WidthLoad = $('#c1').width() ;
c1WidthLoad += 43;
$(".press_tab_shadow").css('width',c1WidthLoad + 'px');
 $('.middle_column .column_select').click(function () {
	  var temp = 0 ;
      var c1WidthValue = 0 ;
      var c2WidthValue = 0 ;
	  var c3WidthValue = 0 ;
	  var c4WidthValue = 0 ;
      var leftValue = 0 ;
	  
	  $(this).prevAll().removeClass('press_tab');
	  $(this).nextAll().removeClass('press_tab');
	  
	  c1WidthValue = $('#c1').width() ;
	  c2WidthValue = $('#c2').width() ;
	  c3WidthValue = $('#c3').width() ;
	  c4WidthValue = $('#c4').width() ;
	  
	  c1WidthValue += 43;
	  
      if(this.id == 'cat1'){
		 
		  $('.press_tab_shadow').css('width',c1WidthValue + 'px').css('left','12px');
      	  $('#cat1').addClass('press_tab');
      }
      if(this.id == 'cat2'){
		  c2WidthValue  += 43 ;
		  temp = c1WidthValue + 20 ;
		  $('.press_tab_shadow').css('width',c2WidthValue + 'px').css('left',temp + 'px');
      	  $('#cat2').addClass('press_tab');
      }
	  if(this.id == 'cat3'){
		  c3WidthValue  += 43 ;
		  temp = c1WidthValue + c2WidthValue + 71 ;
		  $('.press_tab_shadow').css('width',c3WidthValue + 'px').css('left',temp + 'px');
      	  $('#cat3').addClass('press_tab');
      }
	  if(this.id == 'cat4'){
		  c4WidthValue  += 43 ;
		  temp = c1WidthValue + c2WidthValue + c3WidthValue + 122 ;
		  $('.press_tab_shadow').css('width',c4WidthValue + 'px').css('left',temp + 'px');
      	  $('#cat4').addClass('press_tab');
      }
	  
	  leftValue = c1WidthValue + 8 ;
	  $('#go_up_nd, #go_down_nd').css('left','-' + leftValue + 'px');
	  
    });
// ------ KEY PRESS -------------------//
$("#password").focus(function(){
	$(this).unbind('keydown.login').bind('keydown.login',function(event){	
		if(event.keyCode == '13'){
			if((".button span").html == "Login"){
				chkLogin('en');
			}else {
				chkLogin('cn');
				}
		}	
	});
	
}); 
// ------ PHOTO TURN -------------------//
$('.photo_for_edit').hide();
$('.avatar_button').click(function(){
	$('.photo_for_view').animate({
		top:'-15px',
		height:'0'
		},{
		duration:450,
		queue:false,
		easing:'easeOutCirc',
		complete:function(){
			edit_show();
		}
		});
	
});
$('.refresh_button').click(function(){
	$('.photo_for_edit').animate({
		top:'-15px',
		height:'0'
		},{
		duration:450,
		queue:false,
		easing:'easeOutCirc',
		complete:function(){
			view_show();
		}
		});
	
});
function edit_show() {
	$('.photo_for_view').hide();
	$('.photo_for_edit').show();
	$('.photo_for_edit').animate({
		height:'278'
		},{
		duration:900,
		queue:false,
		easing:'easeOutBounce'
		});
}
function view_show() {
	$('.photo_for_edit').hide();
	$('.photo_for_view').show();
	$('.photo_for_view').animate({
		height:'278'
		},{
		duration:900,
		queue:false,
		easing:'easeOutBounce'
		});
}
// ------ PLACE_SLIDER FOR IE -------------------//
var app=navigator.appName;
var verStr=navigator.appVersion;
if(app == "Microsoft Internet Explorer"){
	$(".sec_st").css("height","215px");
    $(".sec_nd").css("height","0px");
    $(".sec_rd").css("height","0px");
	detail_slider();
};
function detail_slider() {
	$(".middle_label").click(function() {
		$('.sec_nd').animate({
		height:'230'
		},{
		duration:500,
		queue:false,
		easing:'easeOutCirc'
		});
		$('.sec_rd').animate({
		height:'0'
		},{
		duration:500,
		queue:false,
		easing:'easeOutCirc'
		});
		$('.sec_st').animate({
		height:'0'
		},{
		duration:500,
		queue:false,
		easing:'easeOutCirc'
		});
	});
  $(".bottom_label").click(function() {
		$('.sec_rd').animate({
		height:'188'
		},{
		duration:500,
		queue:false,
		easing:'easeOutCirc'
		});
		$('.sec_st').animate({
		height:'0'
		},{
		duration:500,
		queue:false,
		easing:'easeOutCirc'
		});
		$('.sec_nd').animate({
		height:'0'
		},{
		duration:500,
		queue:false,
		easing:'easeOutCirc'
		});
	});
  $(".top_label").click(function() {
		$('.sec_st').animate({
		height:'215'
		},{
		duration:500,
		queue:false,
		easing:'easeOutCirc'
		});
		$('.sec_nd').animate({
		height:'0'
		},{
		duration:500,
		queue:false,
		easing:'easeOutCirc'
		});
		$('.sec_rd').animate({
		height:'0'
		},{
		duration:500,
		queue:false,
		easing:'easeOutCirc'
		});
	});
}
// ------ MY-PLACE SLIDER -------------------//
$('#cat1').click(function(){
	$('.place_parts').scrollTop(0);
	$('#li1').prevAll().insertBefore($('.li_change'));
	$('#add_location_form').prevAll().insertBefore($('#li5'));
});
$('#cat2').click(function(){
	$('.place_parts').scrollTop(0);
	$('#li2').prevAll().insertBefore($('.li_change'));
	$('#add_location_form').prevAll().insertBefore($('#li5'));
});
$('#cat3').click(function(){
	$('.place_parts').scrollTop(0);
	$('#li3').prevAll().insertBefore($('.li_change'));
	$('#add_location_form').prevAll().insertBefore($('#li5'));
});
$('#cat4').click(function(){
	$('.place_parts').scrollTop(0);
	$('#li4').prevAll().insertBefore($('#li5'));
});
// ------ HELP_IMG SHOW -------------------//
$(".site_img img").mouseover(
  function () {
	$(".site_img").fadeOut();
    $(".hover_site_img").fadeIn();
});
$(".hover_site_img").hover(
  function () {
	$(".site_img").fadeOut();
    $(".hover_site_img").fadeIn();
},
  function () {
    $(".hover_site_img").fadeOut();
	$(".site_img").fadeIn();
});
// ------ SELECT CATEGORY -------------------//
$('.site_content_nd').hide();
$('#rank_st li a').click(function(){
	$(this).parent().siblings("li").children().removeClass('turn_on').addClass('turn_off');
	$(this).removeClass('turn_off').addClass('turn_on');
	$('.site_title .span_first').text($(this).text());
	$('.site_content').fadeOut(function(){
		   $('.site_content_nd').fadeIn().show();
	   });
	  
	 $('#rank_nd li').remove();
	 var reqUrl = apiurl + '/resource/category/second/show/' + this.id;
	 $.getJSON(reqUrl,function(json){
	 			var items = json.data ;
	 			if(items.length > 0){
	 				$.each(items,function(ids,item){
		   			showMyPlaceCategory(item);
	   			});
	 			}else{
	 				showMyPlaceCategory(items);
	 			}
		   
		 });
});

function showMyPlaceCategory(item){
	var tmp = getCurrentLanguage();
	if("en" == tmp){
		$('#rank_nd').append("<li><a href='javascript:void(0);' id='"+item.id+"' class='turn_off'>"+ item.name_en +"</a></li>");
	}else{
		$('#rank_nd').append("<li><a href='javascript:void(0);' id='"+item.id+"' class='turn_off'>"+ item.name_cn +"</a></li>");
	}	
}

$('#rank_nd li a').live('click',function(){
	$(this).parent().siblings("li").children().removeClass('turn_on').addClass('turn_off');
	$(this).removeClass('turn_off').addClass('turn_on');
	$('.site_title .span_second').html(" " + "/" + " " + $(this).text());
	$("input[name='category2_id']").val(this.id);

});
$('.span_first').click(function(){
	$('.site_content_nd').fadeOut(
	    function(){
			$('.site_content').fadeIn();
		}
	);
	$('.site_title .span_second').text("");
});
// ------ UPLOAD SLIDER -------------------//
$('.upload_box').hide();
$('#photo_title').mouseover(
function(){
	box_slider_left();
	}
);

$('.upload_box').hover(
function(){
	box_slider_left();
	},
function(){
	box_slider_right();
	}
);

$('.upload_box').click(
function(){
	box_slider_right();
	}
);

function box_slider_left(){
	$('.upload_box').show();
	$('.upload_box').animate({
		width:'122px'
		},{
		duration:500,
		queue:false,
		easing:'easeOutCirc'
		});
}
function box_slider_right(){
	$('.upload_box').animate({
		width:'0px'
		},{
		duration:500,
		queue:false,
		easing:'easeOutCirc',
		complete:function(){
			$('.upload_box').hide();
			}
		});
	}
// ------ UPLOAD_FILE CLICK CHANGE -------------------//
$('.alert_error').hide();
$('.upload_button').click(function(){
	$('.file_input').click();
	});
$('.save_button').click(function(){
  if($('#rank_nd li a').hasClass('turn_on') == true){
		 alert(OK);
}else{
	$('.alert_error').show();
	$(".alert_error").animate({
		 height:'38'
		 },{
	       queue: false,
		   duration:800,
		   easing:'easeOutCirc'});
	}
});
// ------ ALERT DIV -------------------//
$('.alert_div .close_img').click(function(){
	$(".alert_div").animate({
		       height:'0'
		             },{
			   queue: false,
			   duration:800,
			   easing:'easeOutCirc',
			   complete:function(){
				   $('.alert_div').hide();
				   }
	    });
	});
$('.alert_error .close_img').click(function(){
	$(".alert_error").animate({
		       height:'0'
		             },{
			   queue: false,
			   duration:800,
			   easing:'easeOutCirc',
			   complete:function(){
				   $('.alert_error').hide();
				   }
	    });
	});
$('.alert_warn').hide();
$('.save_button').click(function(){
	var location_name_value = $("input[name='location_name']").val();
    var address_value = $("input[name='address']").val();
	if((location_name_value == "") || (address_value == "")){
	     $(".alert_warn").show().animate({
		       height:'38px'
		             },{
			   queue: false,
			   duration:800,
			   easing:'easeOutCirc'
	    });
		$("#main_info").show().animate({
		       top:'53px'
		             },{
			   queue: false,
			   duration:800,
			   easing:'easeOutCirc'
	    });
    }
});
$('.alert_warn .close_img').click(function(){
	$(".alert_warn").animate({
		       height:'0'
		             },{
			   queue: false,
			   duration:800,
			   easing:'easeOutCirc',
			   complete:function(){
				   $('.alert_warn').hide();
				   }
	    });
	$("#main_info").show().animate({
		       top:'15px'
		             },{
			   queue: false,
			   duration:800,
			   easing:'easeOutCirc'
	    });
	});
// ------ PAGE SLIDER -------------------//
$('#main_info').hide();
$('.site_content_hours').hide();
$('#hours').click(function(){
	$('#hours').animate({
		width:'0px'
		},{
		duration:500,
		easing:'easeOutCirc',
		complete:function(){
			$('#hours').hide();
			}
		});
	$('.site_content_main').fadeOut(function(){
		$('.site_content_hours').fadeIn(500,function(){
		$('#main_info').show().animate({
		width:'100px'
		},{
		duration:500,
		easing:'easeOutCirc'
		});
	});
	});
	$('#main_info_title span').text('Open Hours');
	$('.site_title_cn span').text('营业时间');
	});
$('#main_info').click(function(){
	$('#main_info').animate({
		width:'0px'
		},{
		duration:500,
		easing:'easeOutCirc',
		complete:function(){
			$('#main_info').hide();
			}
		});
	$('.site_content_hours').fadeOut(function(){
		$('.site_content_main').fadeIn(500,function(){
		$('#hours').show().animate({
		width:'100px'
		},{
		duration:500,
		easing:'easeOutCirc'
		});
	});
	});
	$('#main_info_title span').text('Main Information');
	$('.site_title_cn span').text('详细信息');
	});
// ------ ADD TAGS -------------------//
$('.tag_field').hide();
$('.add_button').click(function(){
	var tag_value = $("input[name='tag_input']").val();
	var tag_length = $.trim(tag_value).length;
	
	$("input[name='tag_input']").val("").focus();
	
	if(tag_value != "" && tag_length != 0){
	$('.tag_field').show();
	$('.tag_field ul').append("<li><a href='javascript:void(0);' class='a_tag'>" + tag_value + "</a></li>");	
	}
});
// ------ REMOVE TAGS -------------------//
$('.tag_field ul li a').live('dblclick', function(){
	$(this).parent().remove();
	if($('.tag_field ul li').length == 0){
		$('.tag_field').hide();
	}
});
// ------ DELETE AND REMARK ALERT -------------------//
$('.friends_alert').hide();
$('.last_span').click(function(){
	delete_alert_show();
	remark_alert_close();
	chat_alert_close();
	follow_alert_close();
});
$('.cancel_button, .publish_button, .confirm_button').click(function(){
	delete_alert_close();
});
$('.remark_span').click(function(){
	remark_alert_show();
	chat_alert_close();
	delete_alert_close();
});
$('.cancel_button, .publish_button, .confirm_button').click(function(){
	$("input[name='remark']").val("");
	remark_alert_close();
});
$('.chat_span').click(function(){
	chat_alert_show();
	remark_alert_close();
	delete_alert_close();
	follow_alert_close();
});
$('.confirm_button').click(function(){
	chat_alert_close();
});
$('.follow_span').click(function(){
	follow_alert_show();
	chat_alert_close();
	delete_alert_close();
});
$('.confirm_button').click(function(){
	follow_alert_close();
});
$('.friends_lists').scroll(function(){
	$('.friends_alert').css('top', $('.friends_lists').scrollTop() - 10);
});
function chat_alert_show(){
	$(".chat_alert").show().animate({
		       height:'120',
			   top:$('.friends_lists').scrollTop() - 10
		             },{
			   queue: false,
			   duration:500,
			   easing:'easeOutCirc'
	    });
}
function chat_alert_close(){
	$(".chat_alert").animate({
		       height:'0',
			   top:$('.friends_lists').scrollTop() - 10
		             },{
			   queue: false,
			   duration:500,
			   easing:'easeOutCirc',
			   complete:function(){
				  $('.chat_alert').hide(); 
			   }
	    });
}
function remark_alert_show(){
	$(".remark_alert").show().animate({
		       height:'120',
			   top:$('.friends_lists').scrollTop() - 10
		             },{
			   queue: false,
			   duration:500,
			   easing:'easeOutCirc'
	    });
}
function remark_alert_close(){
	$(".remark_alert").animate({
		       height:'0',
			   top:$('.friends_lists').scrollTop() - 10
		             },{
			   queue: false,
			   duration:500,
			   easing:'easeOutCirc',
			   complete:function(){
				  $('.remark_alert').hide(); 
			   }
	    });
}
function follow_alert_show(){
	$(".follow_alert").show().animate({
		       height:'120',
			   top:$('.friends_lists').scrollTop() - 10
		             },{
			   queue: false,
			   duration:500,
			   easing:'easeOutCirc'
	    });
}
function follow_alert_close(){
	$(".follow_alert").animate({
		       height:'0',
			   top:$('.friends_lists').scrollTop() - 10
		             },{
			   queue: false,
			   duration:500,
			   easing:'easeOutCirc',
			   complete:function(){
				  $('.follow_alert').hide(); 
			   }
	    });
}
function delete_alert_show(){
	$(".delete_alert").show().animate({
		       height:'120',
			   top:$('.friends_lists').scrollTop() - 10
		             },{
			   queue: false,
			   duration:500,
			   easing:'easeOutCirc'
	    });
}
function delete_alert_close(){
	$(".delete_alert").animate({
		       height:'0',
			   top:$('.friends_lists').scrollTop() - 10
		             },{
			   queue: false,
			   duration:500,
			   easing:'easeOutCirc',
			   complete:function(){
				  $('.delete_alert').hide(); 
			   }
	    });
}
// ------ FOLLOWING AND REMARK FOLLOWER -------------------//
$('#follower_ul').hide();
$('#cat1').click(function(){
	$('#follower_ul').css('margin-left', '-730px');
	$('#follower_ul').fadeOut();
	$('#following_ul').fadeIn();
});
$('#cat2').click(function(){
	$('#following_ul').fadeOut(function(){
		$('#follower_ul').css('margin-left', '0px');
	});
	$('#follower_ul').fadeIn();
});
// ------ ASPECT PRESS-ON -------------------//
$('.num_box').click(function(){
	$(this).addClass("press_on");
	$(this).prevAll().removeClass('press_on');
	$(this).nextAll().removeClass('press_on');
});
$('.s0').hover(
function(){
	$('.detail_info_span').html('poor');
},function(){
	$('.detail_info_span').html('');
}
);
$('.s1').hover(
function(){
	$('.detail_info_span').html('general');
},function(){
	$('.detail_info_span').html('');
}
);
$('.s2').hover(
function(){
	$('.detail_info_span').html('good');
},function(){
	$('.detail_info_span').html('');
}
);
$('.s3').hover(
function(){
	$('.detail_info_span').html('very good');
},function(){
	$('.detail_info_span').html('');
}
);
$('.s4').hover(
function(){
	$('.detail_info_span').html('Excellent');
},function(){
	$('.detail_info_span').html('');
}
);
// ------ MAP_SIGN -------------------//
    $('.map_sign a').click(function () {
        $('#send_feedback').fadeIn('fast', function () {
            $(".map_sign_field").fadeIn('fast');
        });
    });
    $('#send_feedback').click(function () {
        $('.map_sign_field').fadeOut('fast', function () {
            $("#send_feedback").fadeOut('fast');
        });
    });
		$(".map_sign_field").css("left", windowWidth / 2 - 325);
    $(".map_sign_field").css("top", windowHeight / 2 - 175);
// ------ REPLY SHOW -------------------//
$(".reviews").each(function(){
	$(this).hover(function(){
		$(".reply").show();	
	},function(){
		$(".reply").hide();
	});
	
});
// ------ CREATE_EVENT -------------------//
    $('.create').click(function () {
        $('#send_feedback').fadeIn('fast', function () {
            $(".create_event_field").fadeIn('fast');
        });
    });
    $('#send_feedback, .cancel_button').click(function () {
        $('.create_event_field').fadeOut('fast', function () {
            $("#send_feedback").fadeOut('fast');
        });
    });
	$(".create_event_field").css("left", windowWidth / 2 - 290);
    $(".create_event_field").css("top", windowHeight / 2 - 255);
// ------ EVENT TIME -------------------//
$("#box_input").click(function(){
	if($("#box_input").is(":checked") == true){
	          $('.line_span, .clock').hide();
       }else{
	          $('.line_span, .clock').show();
}
});
$("#close_time").click(function(){
	$(".end_div").hide();
	$(".add_span").show();
});
$(".add_span").click(function(){
	$(".add_span").hide();
	$(".end_div").show();
});
// ------ INDEX -------------------//
$(".alert_box img").click(function(){
	$(".alert_box").hide();
});
$("input").focus(function(){
	$(".alert_box").hide();
});
$("#user_email").focus(function(){
	$("#user_email").removeClass('empty_email');
});
$(".middle_input").focus(function(){
	$(".middle_input").removeClass('empty_data');
});
// ------ REGISTER BUTTON SHOCK (ERROR STATUS) -------------------//
$(".register_button").click(function(){
	$('.register_button').animate({
	left: '-=10'},100,"easeOutCirc",function(){
	$(this).animate({
	left: '+=20'},100,"easeOutCirc",function(){
	$(this).animate({
	left: '-=15'},100,"easeOutCirc",function(){
	$(this).animate({
	left: '+=10'},100,"easeOutCirc",function(){
	$(this).animate({	
	left: '-=5'},100,"easeOutCirc");
	});
	});
	});	
	});	
});
// ------ EVENT DETAIL EDIT -------------------//
$('#edit_detail').keyup(function(){
	if($(this).text().length != 0){
		$('#show_detail').hide();
	}else{
		$('#show_detail').show();
	}
});
$('#edit_invite').keyup(function(){
	if($(this).text().length != 0){
		$('#show_invite').hide();
	}else{
		$('#show_invite').show();
	}
});
// ------ BLACK AREA SELECT -------------------//
$(".column_select_area ul li").click(function(){
	$(this).siblings().removeClass("li_selected");
	$(this).addClass("li_selected");
});
// ------ FRIEND LIST OPEN -------------------//
$("#menu_friends .info_a").toggle(function(){
	$("#menu_friends .info_a").css("color","#fff");
	$(".group_content").show().animate({
		height:'107px',
	    opacity:'1'
		     },{
			   queue: false,
			   duration:500,
			   easing:'easeOutCirc'
	});},
   function(){
	$("#menu_friends .info_a").css("color","#999");
	$(".group_content").animate({
		height:'0px',
	     opacity:'0'
		     },{
			   queue: false,
			   duration:500,
			   easing:'easeOutCirc',
			   complete:function(){
				   $(".group_content").hide();
				}
	});  
});
// ------ FRIENDS CLICK -------------------//
$(".fr_content").click(function(){
	$(this).siblings().removeClass("fr_on");
	$(this).addClass("fr_on");
});
// ------ PLACE_CAT_SHOW -------------------//
$("#cat1_field").click(function(){
	$("#cat2_field").hide();
	$("#div_cat_two").hide();
	$("#cat2_field span").text("Please Select");
	$(".for_cn span").text("请选择");
	$("#div_cat_one").show().animate({
		height: $("#get_height_one").height()
		     },{
			   queue: false,
			   duration:500,
			   easing:'easeOutCirc'
	});
});
$("#get_height_one li a").click(function(){
	$("#div_cat_one").animate({
		height: '0px'
		     },{
			   queue: false,
			   duration:500,
			   easing:'easeOutCirc',
			   complete:function(){
				   $("#div_cat_one").hide();
			   }
	});
	$("#cat1_field span").text($(this).text());
	$("#cat2_field").show();
});
$("#cat2_field").click(function(){
	$("#div_cat_two").show().animate({
		height: $("#get_height_two").height()
		     },{
			   queue: false,
			   duration:500,
			   easing:'easeOutCirc'
	});
});
// ------ FRIEND_LISTS_HEIGHT -------------------//
$('.view_auto').css("height", 0.75*windowHeight);



















	
	
});

// ------ PAGE RE-SIZE -------------------//
$(window).resize(function () {
    windowWidth = $(window).width();
    windowHeight = $(window).height();
    $("#send_feedback").css("height", windowHeight);
    $(".feedback_form").css("top", windowHeight / 2 - 150);
	$(".review_field").css("left", windowWidth / 2 - 300);
    $(".review_field").css("top", windowHeight / 2 - 162);
	$(".map_sign_field").css("left", windowWidth / 2 - 325);
    $(".map_sign_field").css("top", windowHeight / 2 - 175);
	$(".create_event_field").css("left", windowWidth / 2 - 290);
    $(".create_event_field").css("top", windowHeight / 2 - 255);
});

//------ HIDE AVATAR-----------//
function hide_avatar() {
    $('.photo_right').animate({
        left: '15'
    }, {
        queue: false,
        duration: 400,
        easing: 'easeOutQuart'
    });
};

//------ HIDE NOTIFICATIONS-----------//
function hide_notifications() {
       $('.notification_main').animate({
            height: '0px'
        }, {
            queue: false,
            duration: 200,
            easing: 'easeOutQuart',
			complete: setTimeout("notificationhide()", 10)
			
			
        });	
};

function notificationhide(){
	  $('.notification_content_title, .notification_main').hide()
	};


//------ HIDE USERNAME-----------//
function hide_username() {
	   $('.username_main').animate({
            height: '0px'
        }, {
            queue: false,
            duration: 200,
            easing: 'easeOutQuart',
			complete: setTimeout("usernamehide()", 10)
			
			
        });	
};

function usernamehide(){
	  $('.username_content_title, .username_main').hide()
	};
	
	
	
	

	// function to show the user tab 
	function showUserTab(){
		 if (document.getElementById("userTabInfo").style.display == 'block'){
			document.getElementById("userTabInfo").style.display = 'none';
		}else{
			document.getElementById("userTabInfo").style.display = 'block';
		}
		document.getElementById("notificationTabInfo").style.display = 'none';
	   // I ADDED THE LINE BELOW TO GIVE FOCUS //   
		document.getElementById('userTabInfo').focus();
	}

	function closeusertab(){
		document.getElementById('userTabInfo').style.display='none';
	}
	
	
	function showNotificationTab(){
 		if(document.getElementById("notificationTabInfo").style.display=='block'){
			document.getElementById("notificationTabInfo").style.display='none';	
		}else{
			document.getElementById("notificationTabInfo").style.display='block';	
		}
		document.getElementById("userTabInfo").style.display='none';
 
    document.getElementById('notificationTabInfo').focus();
	}
	
	function closenotification(){
		document.getElementById('notificationTabInfo').style.display='none';
	}
	
	
	
	// search result 
	
	function searchLocWelcomeScr(){
		var category1_id = document.getElementById('category1').value ;
		var category2_id = document.getElementById('category2').value ;
		var searchword = document.getElementById('searchword').value ;
		
		xmlhttp=GetXmlHttpObject();
		if(xmlhttp==null){
			  alert ("Your browser does not support AJAX!");
			  return;
		 }
		var url=liveurl+"/ajax/welcomepage.php";
		url=url+"?searchResult=searchResult&category1_id="+category1_id+"&category2_id="+category2_id+"&searchword="+searchword;
		//url=url+"&sid="+Math.random();
		xmlhttp.onreadystatechange=selectResultResponse;
		xmlhttp.open("GET",url,true);
		xmlhttp.send(null);
	}
	
	function selectResultResponse(){
		if(xmlhttp.readyState==1){	  
			  document.getElementById("searchResponse").innerHTML='Processing...';
		}
		if (xmlhttp.readyState==4){			
			document.getElementById("searchResponse").innerHTML=xmlhttp.responseText;
		}
	}

	
	
	
	
	
// category 2 select box populate
	
	
	function selectCat1(val){
 		document.getElementById('category1').value = val;
		document.getElementById("select_list_st").style.display = 'none';			

 		var category1_id = document.getElementById('category1').value;	
		
		xmlhttp=GetXmlHttpObject();
		if(xmlhttp==null){
			  alert ("Your browser does not support AJAX!");
			  return;
		 }
		var url=liveurl+"/ajax/welcomepage.php";
		url=url+"?showcat2=showcat2&category1_id="+category1_id;
		//url=url+"&sid="+Math.random();
		xmlhttp.onreadystatechange=selectCategorytwoResponse;
		xmlhttp.open("GET",url,true);
		xmlhttp.send(null);
	}
	
	function selectCategorytwoResponse(){
		if(xmlhttp.readyState==1){	  
			  //document.getElementById("btnLogin").innerHTML='Processing...';
		}
		if (xmlhttp.readyState==4){			

			//document.getElementById("").style.display='block';	
			var myUrlString = xmlhttp.responseText.split("###");
 			
			document.getElementById("c1").innerHTML=myUrlString[1];
			document.getElementById("cat2ul").innerHTML=myUrlString[0];
			document.getElementById("searchResponse").innerHTML=myUrlString[2];
			document.getElementById("c2").innerHTML="All";
		}
	}

	
	
	 	function selectCat2(val){
			document.getElementById('category2').value = val;
			document.getElementById("select_list_nd").style.display = 'none';	
			document.getElementById("select_list_st").style.display = 'none';		
			
			xmlhttp=GetXmlHttpObject();
			if(xmlhttp==null){
			  alert ("Your browser does not support AJAX!");
			  return;
			}
			var url=liveurl+"/ajax/welcomepage.php";
			url=url+"?slcat2=slcat2&category2_id="+val;
			//url=url+"&sid="+Math.random();
			xmlhttp.onreadystatechange=slCResponse;
			xmlhttp.open("GET",url,true);
			xmlhttp.send(null);
			
		}

	
	function slCResponse(){
		if(xmlhttp.readyState==1){	  
			  //document.getElementById("btnLogin").innerHTML='Processing...';
		}
		if (xmlhttp.readyState==4){		
			var myUrlString = xmlhttp.responseText.split("###");
			document.getElementById("searchResponse").innerHTML=myUrlString[0];
  			document.getElementById("c2").innerHTML=myUrlString[1];
 		}
	}
	








// update cateogry 2 for select location 

	function updateCategory(lang){
 		var category2_id = document.getElementById('category2_id').value;	
		var location_id = document.getElementById('location_id').value;	
		
		xmlhttp=GetXmlHttpObject();
		if(xmlhttp==null){
			  alert ("Your browser does not support AJAX!");
			  return;
		 }
		var url=liveurl+"/ajax/catLocUpdate.php";
		url=url+"?updatecat2=updatecat2&category2_id="+category2_id+"&location_id="+location_id;
		//url=url+"&sid="+Math.random();
		xmlhttp.onreadystatechange=updateCategoryResponse;
		xmlhttp.open("GET",url,true);
		xmlhttp.send(null);
	}
	
	function updateCategoryResponse(){
		if(xmlhttp.readyState==1){	  
			  //document.getElementById("btnLogin").innerHTML='Processing...';
		}
		if (xmlhttp.readyState==4){
			
			document.getElementById("no_result").style.display='block';	
			document.getElementById("no_result").innerHTML=xmlhttp.responseText;
		}
	}



///////////////////////////////////////////////////////////////////////////////////////////////
 function changeCategory2(cat2){
	document.getElementById("loading_box").style.display='block';
	setTimeout("closeloading()", 1000);
 	var allcats = document.getElementById('cate2IDS').value;
	 categoryArr = allcats.split(",");
	 lengthCat = categoryArr.length;
  	 for (i=0;i<lengthCat;i++){
			catid = "cat2_"+categoryArr[i];
 			document.getElementById(catid).setAttribute("class", "turn_off"); 
		}
	document.getElementById(cat2).setAttribute("class", "turn_on"); 
	categoryArr = cat2.split("_");
	category2_id = categoryArr[1];
	document.getElementById('category2_id').value = category2_id;
	document.getElementById('selectedcat2_id').value = cat2;
	//document.getElementById("loading_box").style.display='none';
 }
 
function startloading(){
	document.getElementById("loading_box").style.display='block';
	setTimeout("closeloading()", 800);
	sizeFeddback();
}
 
function closeloading(){
	document.getElementById("loading_box").style.display='none';
}




// function to show the category 1
 
 	function changeCategory1(cat1,lang){
		oldCat = document.getElementById('selectedcat1_id').value ;
		document.getElementById(oldCat).setAttribute("class", "turn_off"); 
		
		document.getElementById('selectedcat1_id').value = cat1;
		document.getElementById(cat1).setAttribute("class", "turn_on");		
		//document.getElementById('selectedcat2_id').value = '';
		document.getElementById('category2_id').value = '';
		
		categoryArr = cat1.split("_");
		category1_id = categoryArr[1];
		document.getElementById('category1_id').value = category1_id;
		xmlhttp=GetXmlHttpObject();
		if(xmlhttp==null){
			  alert ("Your browser does not support AJAX!");
			  return;
		 }
		var url=liveurl+"/ajax/catLocUpdate.php";
		url=url+"?showcat2=showcat2&category1_id="+category1_id+"&lang="+lang;
		//url=url+"&sid="+Math.random();
		xmlhttp.onreadystatechange=shCat2;
		xmlhttp.open("GET",url,true);
		xmlhttp.send(null);

  	}	

 function shCat2(){
	if(xmlhttp.readyState==1){	  
	  document.getElementById("loading_box").style.display='block';	
	}
	if (xmlhttp.readyState==4){
		document.getElementById("loading_box").style.display='none';
 		document.getElementById("cat2listing").innerHTML=xmlhttp.responseText;
		
		selectedcat2 = document.getElementById('selectedcat2_id').value;
		document.getElementById(selectedcat2).setAttribute("class", "turn_on");	
		
		categoryArr = selectedcat2.split("_");
		category2_id = categoryArr[1];
		document.getElementById('category2_id').value = category2_id;
		
 	}
}
 
 
 // function to check login 
 	function chkLogin(lang){
		var email = document.getElementById('email').value;	
		var password = document.getElementById('password').value;	
		var remember = "" ;
		if($("#remember").is(':checked') == true){
				remember = "yes" ;
		}else{
				remember = "no" ;	
		}
		
		
		//var location_id = document.getElementById('location_id').value;	
 		xmlhttp=GetXmlHttpObject();
		if(xmlhttp==null){
			  alert ("Your browser does not support AJAX!");
			  return;
		 }
		var url=liveurl+"/ajax/loginchk.php";
		url=url+"?email="+email+"&password="+password+"&loginChk=loginChk&remember="+ remember +"&lang="+lang;
		//url=url+"&sid="+Math.random();
		xmlhttp.onreadystatechange=loginChkResult;
		xmlhttp.open("GET",url,true);
		xmlhttp.send(null);
	}
 
	 function loginChkResult(){
			if(xmlhttp.readyState==1){	  
			  //document.getElementById("loading_box").style.display='block';
			}
			if (xmlhttp.readyState==4){
 				//document.getElementById("loading_box").style.display='none';	
				if(xmlhttp.responseText == 'BOTH'){
					//document.getElementById('user_alert').setAttribute("class", "alert");	
					//document.getElementById('pass_alert').setAttribute("class", "password_alert");	
					//alert("Please enter email and passsword for login");
					$(".null_alert").show();
					return false;

				}else if(xmlhttp.responseText == 'USERNAME'){
					//alert("Please enter email");
					$(".null_alert").show();
					return false;

					//document.getElementById('user_alert').setAttribute("class", "alert");
					//document.getElementById('pass_alert').setAttribute("class", "");	
				}else if(xmlhttp.responseText == 'PASSWORD'){
					//document.getElementById('user_alert').setAttribute("class", "");
					//document.getElementById('pass_alert').setAttribute("class", "password_alert");	
					$(".null_alert").show();
					return false;
				}else if(xmlhttp.responseText == 'No'){
					$(".wrong_alert").show();
					return false;
					//document.getElementById('user_alert').setAttribute("class", "alert");	
					//document.getElementById('pass_alert').setAttribute("class", "password_alert");	
				}else{
					var myUrlString = xmlhttp.responseText.split("#");
					//alert(liveurl+"/"+myUrlString[0]+"/"+myUrlString[1]);
					window.top.location.href = liveurl+"/"+myUrlString[0]+"/"+myUrlString[1];	
				}
			  }
		}
 
	// End login function 
 
	function timeMsg(){
		var t=setTimeout("closeSearchDiv()",500);
	}
 	function closeSearchDiv(){		
		document.getElementById("predict_dropdown").style.display='none';	 
	}
	
  
/* Funcion for the serach result*/
	function showSearchResult(lang){
 		var locationSearch = document.getElementById('locationSearch').value;
 		if(locationSearch == ''){
			document.getElementById("predict_dropdown").style.display='none';
			return false;	
		}
		xmlhttp=GetXmlHttpObject();
		if(xmlhttp==null){
			  alert ("Your browser does not support AJAX!");
			  return;
		 }
		var url=liveurl+"/ajax/searchLocations.php";
		url=url+"?locationSearch="+locationSearch+"&search=searchResult&lang="+lang;
		//url=url+"&sid="+Math.random();
		xmlhttp.onreadystatechange=getSearchResult;
		xmlhttp.open("GET",url,true);
		xmlhttp.send(null);
	}

	function getSearchResult(){
		if(xmlhttp.readyState==1){	  
		  document.getElementById("predict_dropdown").style.display='block';
		}
		if (xmlhttp.readyState==4){
  			if(xmlhttp.responseText == 'No'){
				document.getElementById("predict_dropdown").style.display='none';
				document.getElementById("no_result").style.display='block';
				
			}else{
				document.getElementById("no_result").style.display='none';
				document.getElementById("predict_dropdown").innerHTML=xmlhttp.responseText;
			}
		  }
	}
/* End */

	  function saveMapValues(lang){
	  	lat = document.getElementById('rwLat').value;
		lon = document.getElementById('rwLon').value;
		location_id = document.getElementById('location_id').value;
		
		xmlhttp=GetXmlHttpObject();
		if(xmlhttp==null){
			  alert ("Your browser does not support AJAX!");
			  return;
		 }
		var url=liveurl+"/ajax/map.php";
		url=url+"?lat="+lat+"&mapSave=mapSave&lon="+lon+"&location_id="+location_id+"&lang="+lang;
 		xmlhttp.onreadystatechange=mapResultSave;
		xmlhttp.open("GET",url,true);
		xmlhttp.send(null);
	  }

	function mapResultSave(){
		if(xmlhttp.readyState==1){	  
			startloading();
		}
		if(xmlhttp.readyState==4){
			document.getElementById("loading_box").style.display='block';
			var myUrlString = xmlhttp.responseText.split("##");
			//window.top.location.href = liveurl+"/"+myUrlString[0]+"/"+myUrlString[1];	
			alert(xmlhttp.responseText);
			//document.getElementById("loading_box").innerHTML=xmlhttp.responseText;
		}
	}




// save information from the profile page Like username , name , age , gender

	function saveuserinfo(){
 		var name = document.getElementById('name').value;	
		var username = document.getElementById('username').value;	
		var gender = document.getElementById('gender').value;	
		var age = document.getElementById('age').value;	
		
		xmlhttp=GetXmlHttpObject();
		if(xmlhttp==null){
			  alert ("Your browser does not support AJAX!");
			  return;
		 }
		var url=liveurl+"/ajax/profile.php";
		url=url+"?saveuser=saveuser&name="+name+"&username="+username+"&gender="+gender+"&age="+age;
		//url=url+"&sid="+Math.random();
		xmlhttp.onreadystatechange=saveuserinfoResponse;
		xmlhttp.open("GET",url,true);
		xmlhttp.send(null);
	}
	
	function saveuserinfoResponse(){
		if(xmlhttp.readyState==1){	  
			  //document.getElementById("btnLogin").innerHTML='Processing...';
		}
		if (xmlhttp.readyState==4){
			//alert(xmlhttp.responseText);
			document.getElementById("userinfodiv").innerHTML=xmlhttp.responseText;
 			document.getElementById("profile_top_view").style.display = 'block';
			document.getElementById("profile_basic_edit").style.display = 'none';
 		}
	}





// save information from the profile page Like username , name , age , gender

	function saveUserPass(){
 		var password = document.getElementById('password').value;	
		var confirmPassword = document.getElementById('confirmPassword').value;
		var email = document.getElementById('email').value;	
		var phone = document.getElementById('phone').value;	
		var website = document.getElementById('website').value;	
		
		if(password != ""){
			if(confirmPassword == ""){
				alert("Please enter the confirm password");	
				document.getElementById('confirmPassword').focus();
				return false;
			}else if(password != confirmPassword){
				alert("Both password should be same.");	
				document.getElementById('confirmPassword').focus();
				return false;
			}
		}
		
		xmlhttp=GetXmlHttpObject();
		if(xmlhttp==null){
			  alert ("Your browser does not support AJAX!");
			  return;
		 }
		var url=liveurl+"/ajax/profile.php";
		url=url+"?saveuserpass=saveuserpass&password="+password+"&email="+email+"&phone="+phone+"&website="+website;
		//url=url+"&sid="+Math.random();
		xmlhttp.onreadystatechange=saveuserpassResponse;
		xmlhttp.open("GET",url,true);
		xmlhttp.send(null);
	}
	
	function saveuserpassResponse(){
		if(xmlhttp.readyState==1){	  
			  //document.getElementById("btnLogin").innerHTML='Processing...';
		}
		if (xmlhttp.readyState==4){
			document.getElementById("userpassdiv").innerHTML=xmlhttp.responseText;
			document.getElementById("profile_bottom_view").style.display = 'block';
			document.getElementById("profile_bottom_edit").style.display = 'none';
 		}
	}



// show location images

function showLocationsPhotos(){
		var location_id = document.getElementById('locationId').value;	
		xmlhttp=GetXmlHttpObject();
		if(xmlhttp==null){
			  alert ("Your browser does not support AJAX!");
			  return;
		 }
		var url=liveurl+"/ajax/catLocUpdate.php";
		url=url+"?showLocPhotos=showLocPhotos&location_id="+location_id;
		//url=url+"&sid="+Math.random();
		xmlhttp.onreadystatechange=showLocPhotos;
		xmlhttp.open("GET",url,true);
		xmlhttp.send(null);
	}
	
	function showLocPhotos(){
		if(xmlhttp.readyState==1){	  
			document.getElementById("loading_box").innerHTML = 'loading...';		
			document.getElementById("loading_box").style.display='block';
		}
		if (xmlhttp.readyState==4){
				document.getElementById('location_photos').innerHTML = xmlhttp.responseText;	
				document.getElementById("loading_box").innerHTML = 'New photo has been added.';		
				setTimeout("closeloading()", 600);
 		} 
	}

	
	
	

// save review 


	function saveUserReview(){
 		var title = document.getElementById('title').value;	
		var comment = document.getElementById('comment').value;
		var user_id = document.getElementById('user_id').value;	
		var location_id = document.getElementById('location_id').value;	

		if(title == ""){
			return false;
		}
		
		xmlhttp=GetXmlHttpObject();
		if(xmlhttp==null){
			  alert ("Your browser does not support AJAX!");
			  return;
		 }
		var url=liveurl+"/ajax/review.php";
		url=url+"?saveuserReview=saveuserReview&title="+title+"&comment="+comment+"&user_id="+user_id+"&location_id="+location_id;
		//url=url+"&sid="+Math.random();
		xmlhttp.onreadystatechange=userReview;
		xmlhttp.open("GET",url,true);
		xmlhttp.send(null);
	}
	
	function userReview(){
		if(xmlhttp.readyState==1){	  
			document.getElementById("loading_box").innerHTML = 'saving...';		
			document.getElementById("loading_box").style.display='block';
		}
		if (xmlhttp.readyState==4){
			if(xmlhttp.responseText == 'added'){
				document.getElementById("loading_box").innerHTML = 'Saved';		
				document.getElementById('title').value = '';	
				document.getElementById('comment').value = '';			
				setTimeout("closeloading()", 600);
			}
 			//document.getElementById("userpassdiv").innerHTML=xmlhttp.responseText;
 		} 
	}




































//
	function GetXmlHttpObject()
	{
	if (window.XMLHttpRequest)
	  {
	  // code for IE7+, Firefox, Chrome, Opera, Safari
	  return new XMLHttpRequest();
	  }
	if (window.ActiveXObject)
	  {
	  // code for IE6, IE5
	  return new ActiveXObject("Microsoft.XMLHTTP");
	  }
	return null;
	}
 	/** End **/
	
	
 	
	
	
	<!--   Ajax uploader jquery code  -->
	
	/**
 * Ajax upload
 * Project page - http://valums.com/ajax-upload/
 * Copyright (c) 2008 Andris Valums, http://valums.com
 * Licensed under the MIT license (http://valums.com/mit-license/)
 * Version 3.5 (23.06.2009)
 */

/**
 * Changes from the previous version:
 * 1. Added better JSON handling that allows to use 'application/javascript' as a response
 * 2. Added demo for usage with jQuery UI dialog
 * 3. Fixed IE "mixed content" issue when used with secure connections
 * 
 * For the full changelog please visit: 
 * http://valums.com/ajax-upload-changelog/
 */

(function(){
	
var d = document, w = window;

/**
 * Get element by id
 */	
function get(element){
	if (typeof element == "string")
		element = d.getElementById(element);
	return element;
}

/**
 * Attaches event to a dom element
 */
function addEvent(el, type, fn){
	if (w.addEventListener){
		el.addEventListener(type, fn, false);
	} else if (w.attachEvent){
		var f = function(){
		  fn.call(el, w.event);
		};			
		el.attachEvent('on' + type, f)
	}
}


/**
 * Creates and returns element from html chunk
 */
var toElement = function(){
	var div = d.createElement('div');
	return function(html){
		div.innerHTML = html;
		var el = div.childNodes[0];
		div.removeChild(el);
		return el;
	}
}();

function hasClass(ele,cls){
	return ele.className.match(new RegExp('(\\s|^)'+cls+'(\\s|$)'));
}
function addClass(ele,cls) {
	if (!hasClass(ele,cls)) ele.className += " "+cls;
}
function removeClass(ele,cls) {
	var reg = new RegExp('(\\s|^)'+cls+'(\\s|$)');
	ele.className=ele.className.replace(reg,' ');
}

// getOffset function copied from jQuery lib (http://jquery.com/)
if (document.documentElement["getBoundingClientRect"]){
	// Get Offset using getBoundingClientRect
	// http://ejohn.org/blog/getboundingclientrect-is-awesome/
	var getOffset = function(el){
		var box = el.getBoundingClientRect(),
		doc = el.ownerDocument,
		body = doc.body,
		docElem = doc.documentElement,
		
		// for ie 
		clientTop = docElem.clientTop || body.clientTop || 0,
		clientLeft = docElem.clientLeft || body.clientLeft || 0,
		
		// In Internet Explorer 7 getBoundingClientRect property is treated as physical,
		// while others are logical. Make all logical, like in IE8.
		
		
		zoom = 1;
		if (body.getBoundingClientRect) {
			var bound = body.getBoundingClientRect();
			zoom = (bound.right - bound.left)/body.clientWidth;
		}
		if (zoom > 1){
			clientTop = 0;
			clientLeft = 0;
		}
		var top = box.top/zoom + (window.pageYOffset || docElem && docElem.scrollTop/zoom || body.scrollTop/zoom) - clientTop,
		left = box.left/zoom + (window.pageXOffset|| docElem && docElem.scrollLeft/zoom || body.scrollLeft/zoom) - clientLeft;
				
		return {
			top: top,
			left: left
		};
	}
	
} else {
	// Get offset adding all offsets 
	var getOffset = function(el){
		if (w.jQuery){
			return jQuery(el).offset();
		}		
			
		var top = 0, left = 0;
		do {
			top += el.offsetTop || 0;
			left += el.offsetLeft || 0;
		}
		while (el = el.offsetParent);
		
		return {
			left: left,
			top: top
		};
	}
}

function getBox(el){
	var left, right, top, bottom;	
	var offset = getOffset(el);
	left = offset.left;
	top = offset.top;
						
	right = left + el.offsetWidth;
	bottom = top + el.offsetHeight;		
		
	return {
		left: left,
		right: right,
		top: top,
		bottom: bottom
	};
}

/**
 * Crossbrowser mouse coordinates
 */
function getMouseCoords(e){		
	// pageX/Y is not supported in IE
	// http://www.quirksmode.org/dom/w3c_cssom.html			
	if (!e.pageX && e.clientX){
		// In Internet Explorer 7 some properties (mouse coordinates) are treated as physical,
		// while others are logical (offset).
		var zoom = 1;	
		var body = document.body;
		
		if (body.getBoundingClientRect) {
			var bound = body.getBoundingClientRect();
			zoom = (bound.right - bound.left)/body.clientWidth;
		}

		return {
			x: e.clientX / zoom + d.body.scrollLeft + d.documentElement.scrollLeft,
			y: e.clientY / zoom + d.body.scrollTop + d.documentElement.scrollTop
		};
	}
	
	return {
		x: e.pageX,
		y: e.pageY
	};		

}
/**
 * Function generates unique id
 */		
var getUID = function(){
	var id = 0;
	return function(){
		return 'ValumsAjaxUpload' + id++;
	}
}();

function fileFromPath(file){
	return file.replace(/.*(\/|\\)/, "");			
}

function getExt(file){
	return (/[.]/.exec(file)) ? /[^.]+$/.exec(file.toLowerCase()) : '';
}			

// Please use AjaxUpload , Ajax_upload will be removed in the next version
Ajax_upload = AjaxUpload = function(button, options){
	if (button.jquery){
		// jquery object was passed
		button = button[0];
	} else if (typeof button == "string" && /^#.*/.test(button)){					
		button = button.slice(1);				
	}
	button = get(button);	
	
	this._input = null;
	this._button = button;
	this._disabled = false;
	this._submitting = false;
	// Variable changes to true if the button was clicked
	// 3 seconds ago (requred to fix Safari on Mac error)
	this._justClicked = false;
	this._parentDialog = d.body;
	
	if (window.jQuery && jQuery.ui && jQuery.ui.dialog){
		var parentDialog = jQuery(this._button).parents('.ui-dialog');
		if (parentDialog.length){
			this._parentDialog = parentDialog[0];
		}
	}			
					
	this._settings = {
		// Location of the server-side upload script
		action: 'upload.php',			
		// File upload name
		name: 'userfile',
		// Additional data to send
		data: {},
		// Submit file as soon as it's selected
		autoSubmit: true,
		// The type of data that you're expecting back from the server.
		// Html and xml are detected automatically.
		// Only useful when you are using json data as a response.
		// Set to "json" in that case. 
		responseType: false,
		// When user selects a file, useful with autoSubmit disabled			
		onChange: function(file, extension){},					
		// Callback to fire before file is uploaded
		// You can return false to cancel upload
		onSubmit: function(file, extension){},
		// Fired when file upload is completed
		// WARNING! DO NOT USE "FALSE" STRING AS A RESPONSE!
		onComplete: function(file, response) {}
	};

	// Merge the users options with our defaults
	for (var i in options) {
		this._settings[i] = options[i];
	}
	
	this._createInput();
	this._rerouteClicks();
}
			
// assigning methods to our class
AjaxUpload.prototype = {
	setData : function(data){
		this._settings.data = data;
	},
	disable : function(){
		this._disabled = true;
	},
	enable : function(){
		this._disabled = false;
	},
	// removes ajaxupload
	destroy : function(){
		if(this._input){
			if(this._input.parentNode){
				this._input.parentNode.removeChild(this._input);
			}
			this._input = null;
		}
	},				
	/**
	 * Creates invisible file input above the button 
	 */
	_createInput : function(){
		var self = this;
		var input = d.createElement("input");
		input.setAttribute('type', 'file');
		input.setAttribute('name', this._settings.name);
		var styles = {
			'position' : 'absolute'
			,'margin': '-5px 0 0 -175px'
			,'padding': 0
			,'width': '220px'
			,'height': '30px'
			,'fontSize': '14px'								
			,'opacity': 0
			,'cursor': 'pointer'
			,'display' : 'none'
			,'zIndex' :  2147483583 //Max zIndex supported by Opera 9.0-9.2x 
			// Strange, I expected 2147483647					
		};
		for (var i in styles){
			input.style[i] = styles[i];
		}
		
		// Make sure that element opacity exists
		// (IE uses filter instead)
		if ( ! (input.style.opacity === "0")){
			input.style.filter = "alpha(opacity=0)";
		}
							
		this._parentDialog.appendChild(input);

		addEvent(input, 'change', function(){
			// get filename from input
			var file = fileFromPath(this.value);	
			if(self._settings.onChange.call(self, file, getExt(file)) == false ){
				return;				
			}														
			// Submit form when value is changed
			if (self._settings.autoSubmit){
				self.submit();						
			}						
		});
		
		// Fixing problem with Safari
		// The problem is that if you leave input before the file select dialog opens
		// it does not upload the file.
		// As dialog opens slowly (it is a sheet dialog which takes some time to open)
		// there is some time while you can leave the button.
		// So we should not change display to none immediately
		addEvent(input, 'click', function(){
			self.justClicked = true;
			setTimeout(function(){
				// we will wait 3 seconds for dialog to open
				self.justClicked = false;
			}, 3000);			
		});		
		
		this._input = input;
	},
	_rerouteClicks : function (){
		var self = this;
	
		// IE displays 'access denied' error when using this method
		// other browsers just ignore click()
		// addEvent(this._button, 'click', function(e){
		//   self._input.click();
		// });
				
		var box, dialogOffset = {top:0, left:0}, over = false;							
		addEvent(self._button, 'mouseover', function(e){
			if (!self._input || over) return;
			over = true;
			box = getBox(self._button);
					
			if (self._parentDialog != d.body){
				dialogOffset = getOffset(self._parentDialog);
			}	
		});
		
	
		// we can't use mouseout on the button,
		// because invisible input is over it
		addEvent(document, 'mousemove', function(e){
			var input = self._input;			
			if (!input || !over) return;
			
			if (self._disabled){
				removeClass(self._button, 'hover');
				input.style.display = 'none';
				return;
			}	
										
			var c = getMouseCoords(e);

			if ((c.x >= box.left) && (c.x <= box.right) && 
			(c.y >= box.top) && (c.y <= box.bottom)){			
				input.style.top = c.y - dialogOffset.top + 'px';
				input.style.left = c.x - dialogOffset.left + 'px';
				input.style.display = 'block';
				addClass(self._button, 'hover');				
			} else {		
				// mouse left the button
				over = false;
				if (!self.justClicked){
					input.style.display = 'none';
				}
				removeClass(self._button, 'hover');
			}			
		});			
			
	},
	/**
	 * Creates iframe with unique name
	 */
	_createIframe : function(){
		// unique name
		// We cannot use getTime, because it sometimes return
		// same value in safari :(
		var id = getUID();
		
		// Remove ie6 "This page contains both secure and nonsecure items" prompt 
		// http://tinyurl.com/77w9wh
		var iframe = toElement('<iframe src="javascript:false;" name="' + id + '" />');
		iframe.id = id;
		iframe.style.display = 'none';
		d.body.appendChild(iframe);			
		return iframe;						
	},
	/**
	 * Upload file without refreshing the page
	 */
	submit : function(){
		var self = this, settings = this._settings;	
					
		if (this._input.value === ''){
			// there is no file
			return;
		}
										
		// get filename from input
		var file = fileFromPath(this._input.value);			

		// execute user event
		if (! (settings.onSubmit.call(this, file, getExt(file)) == false)) {
			// Create new iframe for this submission
			var iframe = this._createIframe();
			
			// Do not submit if user function returns false										
			var form = this._createForm(iframe);
			form.appendChild(this._input);
			
			form.submit();
			
			d.body.removeChild(form);				
			form = null;
			this._input = null;
			
			// create new input
			this._createInput();
			
			var toDeleteFlag = false;
			
			addEvent(iframe, 'load', function(e){
					
				if (// For Safari
					iframe.src == "javascript:'%3Chtml%3E%3C/html%3E';" ||
					// For FF, IE
					iframe.src == "javascript:'<html></html>';"){						
					
					// First time around, do not delete.
					if( toDeleteFlag ){
						// Fix busy state in FF3
						setTimeout( function() {
							d.body.removeChild(iframe);
						}, 0);
					}
					return;
				}				
				
				var doc = iframe.contentDocument ? iframe.contentDocument : frames[iframe.id].document;

				// fixing Opera 9.26
				if (doc.readyState && doc.readyState != 'complete'){
					// Opera fires load event multiple times
					// Even when the DOM is not ready yet
					// this fix should not affect other browsers
					return;
				}
				
				// fixing Opera 9.64
				if (doc.body && doc.body.innerHTML == "false"){
					// In Opera 9.64 event was fired second time
					// when body.innerHTML changed from false 
					// to server response approx. after 1 sec
					return;				
				}
				
				var response;
									
				if (doc.XMLDocument){
					// response is a xml document IE property
					response = doc.XMLDocument;
				} else if (doc.body){
					// response is html document or plain text
					response = doc.body.innerHTML;
					if (settings.responseType && settings.responseType.toLowerCase() == 'json'){
						// If the document was sent as 'application/javascript' or
						// 'text/javascript', then the browser wraps the text in a <pre>
						// tag and performs html encoding on the contents.  In this case,
						// we need to pull the original text content from the text node's
						// nodeValue property to retrieve the unmangled content.
						// Note that IE6 only understands text/html
						if (doc.body.firstChild && doc.body.firstChild.nodeName.toUpperCase() == 'PRE'){
							response = doc.body.firstChild.firstChild.nodeValue;
						}
						if (response) {
							response = window["eval"]("(" + response + ")");
						} else {
							response = {};
						}
					}
				} else {
					// response is a xml document
					var response = doc;
				}
																			
				settings.onComplete.call(self, file, response);
						
				// Reload blank page, so that reloading main page
				// does not re-submit the post. Also, remember to
				// delete the frame
				toDeleteFlag = true;
				
				// Fix IE mixed content issue
				iframe.src = "javascript:'<html></html>';";		 								
			});
	
		} else {
			// clear input to allow user to select same file
			// Doesn't work in IE6
			// this._input.value = '';
			d.body.removeChild(this._input);				
			this._input = null;
			
			// create new input
			this._createInput();						
		}
	},		
	/**
	 * Creates form, that will be submitted to iframe
	 */
	_createForm : function(iframe){
		var settings = this._settings;
		
		// method, enctype must be specified here
		// because changing this attr on the fly is not allowed in IE 6/7		
		var form = toElement('<form method="post" enctype="multipart/form-data"></form>');
		form.style.display = 'none';
		form.action = settings.action;
		form.target = iframe.name;
		d.body.appendChild(form);
		
		// Create hidden input element for each data key
		for (var prop in settings.data){
			var el = d.createElement("input");
			el.type = 'hidden';
			el.name = prop;
			el.value = settings.data[prop];
			form.appendChild(el);
		}			
		return form;
	}	
};
})();
			<!--   Ajax uploader jquery code  -->
		
		
		
		
		<!-- Jquery easing V1.3-->
	
	
// t: current time, b: begInnIng value, c: change In value, d: duration
jQuery.easing['jswing'] = jQuery.easing['swing'];

jQuery.extend( jQuery.easing,
{
	def: 'easeOutQuad',
	swing: function (x, t, b, c, d) {
		//alert(jQuery.easing.default);
		return jQuery.easing[jQuery.easing.def](x, t, b, c, d);
	},
	easeInQuad: function (x, t, b, c, d) {
		return c*(t/=d)*t + b;
	},
	easeOutQuad: function (x, t, b, c, d) {
		return -c *(t/=d)*(t-2) + b;
	},
	easeInOutQuad: function (x, t, b, c, d) {
		if ((t/=d/2) < 1) return c/2*t*t + b;
		return -c/2 * ((--t)*(t-2) - 1) + b;
	},
	easeInCubic: function (x, t, b, c, d) {
		return c*(t/=d)*t*t + b;
	},
	easeOutCubic: function (x, t, b, c, d) {
		return c*((t=t/d-1)*t*t + 1) + b;
	},
	easeInOutCubic: function (x, t, b, c, d) {
		if ((t/=d/2) < 1) return c/2*t*t*t + b;
		return c/2*((t-=2)*t*t + 2) + b;
	},
	easeInQuart: function (x, t, b, c, d) {
		return c*(t/=d)*t*t*t + b;
	},
	easeOutQuart: function (x, t, b, c, d) {
		return -c * ((t=t/d-1)*t*t*t - 1) + b;
	},
	easeInOutQuart: function (x, t, b, c, d) {
		if ((t/=d/2) < 1) return c/2*t*t*t*t + b;
		return -c/2 * ((t-=2)*t*t*t - 2) + b;
	},
	easeInQuint: function (x, t, b, c, d) {
		return c*(t/=d)*t*t*t*t + b;
	},
	easeOutQuint: function (x, t, b, c, d) {
		return c*((t=t/d-1)*t*t*t*t + 1) + b;
	},
	easeInOutQuint: function (x, t, b, c, d) {
		if ((t/=d/2) < 1) return c/2*t*t*t*t*t + b;
		return c/2*((t-=2)*t*t*t*t + 2) + b;
	},
	easeInSine: function (x, t, b, c, d) {
		return -c * Math.cos(t/d * (Math.PI/2)) + c + b;
	},
	easeOutSine: function (x, t, b, c, d) {
		return c * Math.sin(t/d * (Math.PI/2)) + b;
	},
	easeInOutSine: function (x, t, b, c, d) {
		return -c/2 * (Math.cos(Math.PI*t/d) - 1) + b;
	},
	easeInExpo: function (x, t, b, c, d) {
		return (t==0) ? b : c * Math.pow(2, 10 * (t/d - 1)) + b;
	},
	easeOutExpo: function (x, t, b, c, d) {
		return (t==d) ? b+c : c * (-Math.pow(2, -10 * t/d) + 1) + b;
	},
	easeInOutExpo: function (x, t, b, c, d) {
		if (t==0) return b;
		if (t==d) return b+c;
		if ((t/=d/2) < 1) return c/2 * Math.pow(2, 10 * (t - 1)) + b;
		return c/2 * (-Math.pow(2, -10 * --t) + 2) + b;
	},
	easeInCirc: function (x, t, b, c, d) {
		return -c * (Math.sqrt(1 - (t/=d)*t) - 1) + b;
	},
	easeOutCirc: function (x, t, b, c, d) {
		return c * Math.sqrt(1 - (t=t/d-1)*t) + b;
	},
	easeInOutCirc: function (x, t, b, c, d) {
		if ((t/=d/2) < 1) return -c/2 * (Math.sqrt(1 - t*t) - 1) + b;
		return c/2 * (Math.sqrt(1 - (t-=2)*t) + 1) + b;
	},
	easeInElastic: function (x, t, b, c, d) {
		var s=1.70158;var p=0;var a=c;
		if (t==0) return b;  if ((t/=d)==1) return b+c;  if (!p) p=d*.3;
		if (a < Math.abs(c)) { a=c; var s=p/4; }
		else var s = p/(2*Math.PI) * Math.asin (c/a);
		return -(a*Math.pow(2,10*(t-=1)) * Math.sin( (t*d-s)*(2*Math.PI)/p )) + b;
	},
	easeOutElastic: function (x, t, b, c, d) {
		var s=1.70158;var p=0;var a=c;
		if (t==0) return b;  if ((t/=d)==1) return b+c;  if (!p) p=d*.3;
		if (a < Math.abs(c)) { a=c; var s=p/4; }
		else var s = p/(2*Math.PI) * Math.asin (c/a);
		return a*Math.pow(2,-10*t) * Math.sin( (t*d-s)*(2*Math.PI)/p ) + c + b;
	},
	easeInOutElastic: function (x, t, b, c, d) {
		var s=1.70158;var p=0;var a=c;
		if (t==0) return b;  if ((t/=d/2)==2) return b+c;  if (!p) p=d*(.3*1.5);
		if (a < Math.abs(c)) { a=c; var s=p/4; }
		else var s = p/(2*Math.PI) * Math.asin (c/a);
		if (t < 1) return -.5*(a*Math.pow(2,10*(t-=1)) * Math.sin( (t*d-s)*(2*Math.PI)/p )) + b;
		return a*Math.pow(2,-10*(t-=1)) * Math.sin( (t*d-s)*(2*Math.PI)/p )*.5 + c + b;
	},
	easeInBack: function (x, t, b, c, d, s) {
		if (s == undefined) s = 1.70158;
		return c*(t/=d)*t*((s+1)*t - s) + b;
	},
	easeOutBack: function (x, t, b, c, d, s) {
		if (s == undefined) s = 1.70158;
		return c*((t=t/d-1)*t*((s+1)*t + s) + 1) + b;
	},
	easeInOutBack: function (x, t, b, c, d, s) {
		if (s == undefined) s = 1.70158; 
		if ((t/=d/2) < 1) return c/2*(t*t*(((s*=(1.525))+1)*t - s)) + b;
		return c/2*((t-=2)*t*(((s*=(1.525))+1)*t + s) + 2) + b;
	},
	easeInBounce: function (x, t, b, c, d) {
		return c - jQuery.easing.easeOutBounce (x, d-t, 0, c, d) + b;
	},
	easeOutBounce: function (x, t, b, c, d) {
		if ((t/=d) < (1/2.75)) {
			return c*(7.5625*t*t) + b;
		} else if (t < (2/2.75)) {
			return c*(7.5625*(t-=(1.5/2.75))*t + .75) + b;
		} else if (t < (2.5/2.75)) {
			return c*(7.5625*(t-=(2.25/2.75))*t + .9375) + b;
		} else {
			return c*(7.5625*(t-=(2.625/2.75))*t + .984375) + b;
		}
	},
	easeInOutBounce: function (x, t, b, c, d) {
		if (t < d/2) return jQuery.easing.easeInBounce (x, t*2, 0, c, d) * .5 + b;
		return jQuery.easing.easeOutBounce (x, t*2-d, 0, c, d) * .5 + c*.5 + b;
	}
});
	
	
	

<!-- Jquery easing V1.3-->


