

$(function() {
				 if (!window.ActiveXObject){//IE
					 $("#box").hide();
					 	var jmpressOpts	= {
								//animation		: { transitionDuration : '0.8s' }
								viewPort        : {
						            height  : 400,
						            width   : 130,
						            maxScale: 1
						        },
						        fullscreen      : false,
						        hash            : { use : false },
						        mouse           : { clickSelects : false },
						        keyboard        : { use : true },
						        animation       : { transitionDuration : '2s' },
							};
							$( '#jms-slideshow' ).jmslideshow( $.extend( true, { jmpressOpts : jmpressOpts }, {
								autoplay	: true,
								bgColorSpeed: '2s',
								arrows		: false,
								dots        : false,
								interval    : 10500
							}));
				 }else{
						
						$("#jms-slideshow").hide();
					 	window.onload = function (){
							var oBox = document.getElementById("box");
							var aUl = document.getElementsByTagName("ul");
							var aImg = aUl[0].getElementsByTagName("li");
							var timer = timer2 = play = null;
							var i = index = index2 = 0;
							var num = 0;
							function autoPlay ()
							{
								play = setInterval(function () {
									index++;
									index2 ++;
									index >= aImg.length && (index = 0);
									show(index);
								},10000);	
							}
							autoPlay();
							function show (a){
								index = a;
								var alpha = 0;
								$(".jms" + index2).fadeOut('slow');
								num ++ ;
								if(num == 4){
									num = 0;
									for(var k = 1; k <= 4; k++){
										$(".jms" + k).attr("display","block");
									}
								}
								for (i = 0; i < aImg.length; i++)
								{
									aImg[i].style.opacity = 0;
									aImg[i].style.filter = "alpha(opacity=0)";	
								}
								
								timer = setInterval(function () {
									alpha += 2;
									alpha > 100 && (alpha =100);
									aImg[index].style.opacity = alpha / 100;
									aImg[index].style.filter = "alpha(opacity = " + alpha + ")";
									alpha == 100 && clearInterval(timer)
								},20);
							}
						};
						
						
				 }
				
			});