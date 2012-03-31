<%@ page contentType="text/html; charset=UTF-8" language="java"
	import="java.sql.*" errorPage=""%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>

		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>搜索宁波 - So Ningbo</title>


		<script src="scripts/jquery-1.7.min.js" type="text/javascript"></script>
		<script src="scripts/so_ningbo.js" type="text/javascript"></script>
		<script type="text/javascript"
			src="http://api.map.baidu.com/api?v=1.2"></script>
		<link href="global.css" rel="stylesheet" type="text/css" />
	</head>

	<body
		style="background-image: url(images/bg.png); background-repeat: repeat;">


		<jsp:include page="/check_email.jsp" />

		<div class="whitebar"></div>

		<div class="container">


			<div class="header" style="height: 90px">

				<div style="padding-top: 22px; padding-bottom: 22px; float: left">
					<a href="http://www.soningbo.com/home.jsp"><img
							src="images/logo.png" alt="搜索宁波 - So Ningbo" name="logo"
							width="180" height="46" id="logo" style="display: block;" />
					</a>
				</div>

				<div
					style="float: left; width: 360px; padding-left: 60px; padding-top: 22px; display: block; position: relative">
					<input type="text" class="search_box" value="搜索" />

				</div>
				<div
					style="float: left; width: 60px; padding-top: 27px; display: block; position: relative; left: -77px">
					<input type="image" name="search_btn" id="search_btn"
						src="images/search_btn.png" />
				</div>



			</div>


			<div class="content">

				<div class="navigation">
					<div class="nav_corner"></div>
					<div class="photo">
						<a href="profile.html"><img src="images/no_photo.png"
								width="90" height="90" />
						</a>
					</div>

					<div class="nav_short">
						<div class="nav_short_txt">
							地点
						</div>
					</div>

					<div class="nav_off">
						<div class="icon">
							<img src="images/search_icon.png" width="21" height="22" />
						</div>
						<div class="nav_txt">
							搜索
						</div>
					</div>


					<div class="nav_off">
						<div class="icon">
							<img src="images/find_icon.png" width="22" height="21" />
						</div>
						<div class="nav_txt">
							查找
						</div>
					</div>


					<div class="nav_off">
						<div class="icon">
							<img src="images/favorites_icon.png" width="22" height="22" />
						</div>
						<div class="nav_txt">
							收藏夹
						</div>
					</div>

					<div class="nav_off">
						<div class="icon">
							<img src="images/favorites_icon.png" width="22" height="22" />
						</div>
						<div class="nav_txt">
							<a href="manage/location/addLocation.html">添加地区</a>
						</div>
					</div>

					<div class="nav_short">
						<div class="nav_short_txt">
							社交
						</div>
					</div>

					<div class="nav_off">
						<div class="icon">
							<img src="images/friends_icon.png" width="22" height="22" />
						</div>
						<div class="nav_txt">
							好友
						</div>
					</div>


					<div class="nav_off">
						<div class="icon">
							<img src="images/events_icon.png" width="22" height="22" />
						</div>
						<div class="nav_txt">
							事件
						</div>
					</div>


					<div class="nav_off">
						<div class="icon">
							<img src="images/where_icon.png" width="22" height="22" />
						</div>
						<div class="nav_txt">
							你在哪里
						</div>
					</div>

					<div class="nav_short_bot"></div>

				</div>

				<div class="display">

					<div id="map_container"></div>
					<div id="map_bar"></div>
					<div id="list_container"></div>

				</div>



			</div>
			<jsp:include page="/footer.jsp" />
		</div>

	</body>
</html>


<script type="text/javascript">
var map = new BMap.Map("map_container");            // 创建Map实例
var point = new BMap.Point(121.5426935,29.8091635);    // 创建点坐标
map.centerAndZoom(point,13);                     // 初始化地图,设置中心点坐标和地图级别。
var opts = {anchor: BMAP_ANCHOR_TOP_RIGHT, offset: new BMap.Size(10, 10)};
map.addControl(new BMap.NavigationControl(opts));

</script>