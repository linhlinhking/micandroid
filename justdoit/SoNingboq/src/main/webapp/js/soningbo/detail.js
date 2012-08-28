/**
 * @version:1.0
 * @Description:detail.js By places-detail
 * @author:Devon.Ning
 * 
 */
var soningbo = soningbo || {};

soningbo.DetailLocation = function() {
	return {
		locationId : '',
		userId : '',
		language : 'cn',
		map : '',
		directionsDisplay : '',
		directionsService : '',
		oldDirections : '',
		currentDirections : '',

		init : function() {
			_this = this;
			_this.locationId = $("input[name='locationId']").val();
			_this.userId = $("input[name='userId']").val();
			_this.language = _this.getCurrentPageLanguage();
			_this.map = null;
			_this.directionsDisplay = null;
			_this.directionsService = new google.maps.DirectionsService();
			_this.oldDirections = [];
			_this.currentDirections = null;

			_this.flushComment();

			$('#location_rate').raty({
				path : liveurl + '/images',
				width : 125,
				size : 24,
				starOff : 'star_gray.png',
				starOn : 'star_blue.png',
				click : function(score, evt) {
					$("input[name='overAll']").val(score);
				}
			});

			_this.displayRate();

			$("#total_box_one div").each(function() {
				$(this).click(function() {
					$("input[name='rank_one_o']").val($.trim($(this).text()));
				});
			});

			$("#total_box_two div").each(function() {
				$(this).click(function() {
					$("input[name='rank_two_o']").val($.trim($(this).text()));
				});
			});

			$("#total_box_three div").each(
					function() {
						$(this).click(
								function() {
									$("input[name='rank_three_o']").val(
											$.trim($(this).text()));
								});
					});

			_this.addComment();
			$("#map_tab").click(function() {
				_this.initialize();
			});

			$('#add_favorite_location').click(function() {
				_this.addFavorite();
			});

		},
		addComment : function() {
			$("#Publish").click(function() {
				var commentContent = $("#commentContent").val();
				var rank1 = $("input[name='rank_one_o']").val();
				var rank2 = $("input[name='rank_two_o']").val();
				var rank3 = $("input[name='rank_three_o']").val();
				var overAll = $("input[name='overAll']").val();

				if ("" == commentContent || commentContent.length < 0) {
					return;
				}
				$.post(apiurl + "/resource/comment/addOrUpdate", {
					"key" : "soningbo",
					"userId" : _this.userId,
					"locationId" : _this.locationId,
					"commentContent" : commentContent,
					"overAll" : overAll,
					"rank1" : rank1,
					"rank2" : rank2,
					"rank3" : rank3,
					"commentId" : ""
				}, function(json) {

				}).complete(function() {
					_this.callback();
				});
			});
		},
		callback : function() {
			$("#commentContent").val("")
			_this.flushComment();
		},
		deleteComment : function() {

		},
		showComment : function(json) {
			var d = json.data;
			var l = json.locationData;
			var a = l.aspectsDatas;
			var buffer = $("#tableDiv ul");

			var imgPath = "";
			if (null != d || "" != d || d != 'undefined') {
				if (d.length > 0) {
					for ( var i = 0, j = d.length; i < j; i++) {
						var c = d[i].commentData;
						var u = c.userData;
						imgPath = _this.getImagePath(u.photo_path);
						var dateString = _this.formatDateToString(c.createTime);
						_this
								.templateHtml(buffer, c, u, imgPath,
										dateString, a);
					}

				} else {
					var c = d.commentData;
					var u = c.userData;
					imgPath = _this.getImagePath(u.photo_path);
					var dateString = _this.formatDateToString(c.createTime);
					_this.templateHtml(buffer, c, u, imgPath, dateString, a);
				}

			}

		},
		templateHtml : function(o, c, u, imgPath, dateString, a) {
			if (_this.language === "en") {
				o
						.append("<li class='reviews'><div class='reviews_left'><img src='"
								+ imgPath
								+ "' alt='用户头像'/></div><div class='reviews_right'><div class='review_info'><span class='name_span'>"
								+ u.username
								+ "</span><span class='time_span'>"
								+ dateString
								+ "</span></div><div class='assess_info'><ul class='star-rating_small'><div class='"
								+ _this.showOverAll(c.overAll)
								+ "'></div></ul><div class='assess_div first_div'><span class='bs'>"
								+ a[0].aspect_en
								+ ":"
								+ c.rank1
								+ "</span><span class='as'>("
								+ _this.showStyle($.trim(c.rank1))
								+ ")</span></div><div class='assess_div'><span class='bs'>"
								+ a[1].aspect_en
								+ ":"
								+ c.rank2
								+ "</span><span class='as'>("
								+ _this.showStyle(c.rank2)
								+ ")</span></div><div class='assess_div'><span class='bs'>"
								+ a[2].aspect_en
								+ ":"
								+ c.rank3
								+ "</span><span class='as'>("
								+ _this.showStyle(c.rank3)
								+ ")</span></div></div><div class='review_p'>"
								+ c.commentContent
								+ "</div></div><div class='reply'><span>reply</span></div></li>");
			} else {
				o
						.append("<li class='reviews'><div class='reviews_left'><img src='"
								+ imgPath
								+ "' alt='用户头像'/></div><div class='reviews_right'><div class='review_info'><span class='name_span'>"
								+ u.username
								+ "</span><span class='time_span'>"
								+ dateString
								+ "</span></div><div class='assess_info'><ul class='star-rating_small'><div class='"
								+ _this.showOverAll(c.overAll)
								+ "'></div></ul><div class='assess_div first_div'><span class='bs'>"
								+ a[0].aspect_cn
								+ ":"
								+ c.rank1
								+ "</span><span class='as'>("
								+ _this.showStyle(c.rank1)
								+ ")</span></div><div class='assess_div'><span class='bs'>"
								+ a[1].aspect_cn
								+ ":"
								+ c.rank2
								+ "</span><span class='as'>("
								+ _this.showStyle(c.rank2)
								+ ")</span></div><div class='assess_div'><span class='bs'>"
								+ a[2].aspect_cn
								+ ":"
								+ c.rank3
								+ "</span><span class='as'>("
								+ _this.showStyle(c.rank3)
								+ ")</span></div></div><div class='review_p'>"
								+ c.commentContent
								+ "</div></div><div class='reply'><span>reply</span></div></li>");
			}
		},
		flushComment : function() {
			$("#tableDiv ul li").remove();
			$
					.ajax({
						type : "get",
						url : apiurl + "/resource/comment/location/"
								+ _this.locationId,
						dataType : "json",
						success : _this.showComment,
						error : function() {
							alert("Error.");
						}
					});
		},
		displayRate : function() {
			$.ajax({
				type : "GET",
				url : apiurl + "/resource/location/show/" + _this.locationId,
				dataType : "json",
				success : function(json) {
					if (json != 'undefined' && json != "" && json != null) {
						var aspList = json.aspectsDatas;
						if (aspList.length > 0) {
							$("#assess_field_rate span").each(
									function(index) {
										if (_this.language === "en") {
											$(this).text(
													aspList[index].aspect_en
															+ ":");
										} else {
											$(this).text(
													aspList[index].aspect_cn
															+ ":");
										}

									});
						}
					} else {

					}
				},
				error : (function() {
					alert("Error!");
				})
			});
		},
		getImagePath : function(p) {
			var tmpPath = "";

			if ("0" == p || "undefined" == p || null == p) {
				tmpPath = "/images/username_photo.jpg";
			} else {
				tmpPath = apiurl + "/upload/" + p.substring(0, 4) + "/"
						+ p.substring(4, 8) + "/" + p.substring(8, 12) + "/"
						+ p.substring(12) + "-100x100";
			}
			return tmpPath;
		},
		formatDateToString : function(d) {
			var dd = new Date(d);
			var year = dd.getFullYear();
			var month = dd.getMonth() + 1;
			var date1 = dd.getDate();
			var hour = dd.getHours();
			var minutes = dd.getMinutes();
			var second = dd.getSeconds();
			var str = year + '-' + month + '-' + date1 + ' ' + hour + ':'
					+ minutes + ':' + second;
			return str.toString();
		},
		showStyle : function(v) {
			var arr = _this.getOverAllContent();
			switch (v) {
			case '0':
				return arr[0].toString();
				break;
			case '1':
				return arr[1].toString();
				break;
			case '2':
				return arr[2].toString();
				break;
			case '3':
				return arr[3].toString();
				break;
			case '4':
				return arr[4].toString();
				break;
			default:
				return arr[2].toString();
			}
		},
		showOverAll : function(v) {
			switch (v) {
			case '1':
				return "one_star_show";
				break;
			case '2':
				return "two_star_show";
				break;
			case '3':
				return "three_star_show";
				break;
			case '4':
				return "four_star_show";
				break;
			case '5':
				return "five_star_show";
				break;
			default:
				return "three_star_show";

			}
		},
		getCurrentPageLanguage : function() {
			var lng = $("input[name='lang']").val();
			if (lng != 'undefined' && lng != null) {
				return lng;
			}
		},
		getOverAllContent : function() {
			var arry = new Array(5);
			if (_this.language === "en") {
				arry[0] = "Poor";
				arry[1] = "General";
				arry[2] = "Good";
				arry[3] = "Very Good";
				arry[4] = "Excellent";
			} else {
				arry[0] = "差";
				arry[1] = "一般";
				arry[2] = "好";
				arry[3] = "很好";
				arry[4] = "非常好";
			}

			return arry;
		},
		addFavorite : function() {

			if (_this.locationId == 'undefined' || _this.userId == 'undefined'
					|| null == _this.locationId || null == _this.userId) {
				return;
			}
			$.ajax({
				type : "POST",
				url : apiurl + "/resource/favorite/add",
				data : "key=soningbo&locationId=" + locationId + "&userId="
						+ userId + "&deviceId=" + "",
				dataType : "json",
				beforeSend : function(XMLHttpRequest) {

				},
				success : function(json) {
					if (json.result == 'true') {
						showNotice("addsuccess");
					} else {
						showNotice("repeatadd");
					}
				},
				complete : function(XMLHttpRequest, textStatus) {
					$("#favorite_alert").show();
				}
			});
		},
		initialize : function() {
			var soNingboStyles = [ {
				featureType : "poi",
				stylers : [ {
					visibility : "off"
				} ]
			}, {
				featureType : "landscape",
				stylers : [ {
					saturation : -22
				} ]
			}, {
				featureType : "water",
				stylers : [ {
					saturation : -32
				}, {
					lightness : -6
				} ]
			}, {} ];
			// #latlong
			var myLatLng = new google.maps.LatLng(29.865372, 121.570606);
			var myOptions = {
				zoom : 12,
				minZoom : 12,
				maxZoom : 19,
				streetViewControl : false,
				mapTypeControl : false,
				panControl : false,
				scrollwheel : false,
				center : myLatLng,
				styles : soNingboStyles,
				mapTypeId : google.maps.MapTypeId.ROADMAP

			};
			_this.map = new google.maps.Map(document
					.getElementById('map_canvas_detail'), myOptions);
			_this.directionsDisplay = new google.maps.DirectionsRenderer({
				'map' : _this.map,
				'preserveViewport' : true,
				'draggable' : true
			});
			_this.directionsDisplay.setPanel(document
					.getElementById("directions_panel"));
			google.maps.event.addListener(directionsDisplay,
					'directions_changed', function() {
						$("#directions_panel").text("");
						if (currentDirections) {
							_this.oldDirections.push(_this.currentDirections);
							setUndoDisabled(false);
						}
						_this.currentDirections = _this.directionsDisplay
								.getDirections();
					});
			setUndoDisabled(true);
			doGeolocation();

			function calcRoute(startPostion, endPosition) {
				var request = {
					origin : startPostion,
					destination : endPosition,
					travelMode : google.maps.DirectionsTravelMode.DRIVING
				};
				_this.directionsService.route(request, function(response,
						status) {
					if (status == google.maps.DirectionsStatus.OK) {
						_this.directionsDisplay.setDirections(response);
					}
				});
			}

			function undo() {
				_this.currentDirections = null;
				_this.directionsDisplay
						.setDirections(_this.oldDirections.pop());
				if (!_this.oldDirections.length) {
					setUndoDisabled(true);
				}
			}

			function setUndoDisabled(value) {
				document.getElementById("undo").disabled = value;
			}

			function doGeolocation() {
				if (navigator.geolocation) {
					navigator.geolocation.getCurrentPosition(success, error, {
						maximumAge : 60000,
						timeout : 5000,
						enableHighAccuracy : true
					});
				} else {
					showNotice("notsupport");
				}

			}

			function success(position) {
				var coords = position.coords || position.coordinate || position;
				var tempLat = parseFloat(coords.latitude)
						- parseFloat(0.0024725);
				var tempLng = parseFloat(coords.longitude)
						+ parseFloat(0.0042515);
				var myPosition = new google.maps.LatLng(tempLat, tempLng);
				var locLat = $("input[name='latitude']").val();
				var locLng = $("input[name='longitude']").val();
				var locPostion = new google.maps.LatLng(locLat * 1 - 0.0024725,
						locLng * 1 + 0.0042515);

				calcRoute(myPosition, locPostion);
				var marker = new google.maps.Marker({
					position : myPosition,
					map : _this.map,
					icon : "/images/map_pin_off.png"
				});
			}

			function error(reason) {
				showNotice("signerror");
			}

		}

	};

}
