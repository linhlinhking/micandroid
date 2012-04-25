<?php 
ini_set('default_charset', 'UTF-8');		
define('DB_CHARSET', 'utf8');
define('DB_COLLATE', '');

	function getUserLocation($user_id){
 		$sql = mysql_query("SELECT c2.category1_id,lc.category2_id,ul.location_id,l.name_en,l.name_cn,l.name_py FROM tbl_user_location_link AS ul LEFT JOIN locations AS l ON l.id = ul.location_id LEFT JOIN locations_category AS lc ON lc.location_id = ul.location_id LEFT JOIN category2 AS c2 ON c2.id = lc.category2_id WHERE md5(ul.user_id) = '".$user_id."'");
		if(mysql_num_rows($sql) > 0){
			$rows = mysql_fetch_assoc($sql);
			return $rows;
		}else{
			return false;
		}
	}

  
	function chkUsername($username){
		$sql = mysql_query("SELECT username FROM users WHERE username = '".$username."'");
		if(mysql_num_rows($sql) > 0){
			return true;
		}else{
			return false;
		}
	}

	function getCategoryChineseName($category1_name,$lang){
		$sql = mysql_query("SELECT name_en,name_cn FROM category1 WHERE name_py = '".$category1_name."'");
		if(mysql_num_rows($sql)){
			$rs = mysql_fetch_assoc($sql);
			if($lang == "cn"){
			 return $rs['name_cn'];
			}else{
			 return $rs['name_en'];
			}
		}
	}
	
	
	function getCategory2ChineseName($category2_name,$lang){
		$sql = mysql_query("SELECT name_en,name_cn FROM category2 WHERE name_py = '".$category2_name."'");
		if(mysql_num_rows($sql)){
			$rs = mysql_fetch_assoc($sql);
			if($lang == "cn"){
			 return $rs['name_cn'];
			}else{
			 return $rs['name_en'];
			}
		}
	}
	
	
	

  	function getLocationCount(){
		$query = mysql_query("SELECT count(id) AS locationCount FROM locations");
		$result = mysql_fetch_assoc($query);
		return $result['locationCount'];
	}
	
	function is_email_valid($email) {
	  $result = TRUE;
	  if(!eregi("^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,4})$", $email)) {
		$result = FALSE;
	  }
	  return $result;
}
	
	
	function messageBox($message1,$message2,$type){
		return $box = '<div class="submit_information">
		<div class="information_area"><span>'.$message1.'</span></div>
		<img src="'.LIVESITEURL.'/images/'.$type.'">
		<p>'.$message2.'</p></div>';
	}
	
	
	function getextnumber($number){
		if($number == 0){
			$textNumber = "zero";
		}
		if($number == 1){
			$textNumber = "one";
		}
		if($number == 2){
			$textNumber = "two";
		}
		if($number == 3){
			$textNumber = "three";
		}
		if($number == 4){
			$textNumber = "four";
		}
		if($number == 5){
			$textNumber = "five";
		}
		if($number == 6){
			$textNumber = "six";
		}
		if($number == 7){
			$textNumber = "seven";
		}
		if($number == 8){
			$textNumber = "eight";
		}
		if($number == 0){
			$textNumber = "eight";
		}
		return $textNumber;
	}
	
	
	

	function removeSpace($string){
		$string = (htmlspecialchars_decode($string) == $string) ? htmlspecialchars($string) : $string;		
		return str_replace(" ","-",$string);
	}
	
	function makeSpace($string){
		return str_replace("-"," ",$string);
	}
	
	
	function getLocationLink($location_id,$lang){
		$sql = mysql_query("SELECT c2.name_en AS cat2Name,c2.name_py AS cat2py,c1.name_en AS cat1Name,c1.name_py AS cat1py,l.name_en AS locationName,l.name_py AS locationPy,l.id AS location_id FROM locations_category AS lc LEFT JOIN category2b AS c2 ON c2.id = lc.category2_id LEFT JOIN category1b AS c1 ON c1.id = c2.category1_id LEFT JOIN locationsb AS l ON l.id = lc.location_id WHERE lc.location_id = '".$location_id."'");
		$rows = mysql_fetch_assoc($sql);
		if($lang == "en"){
			$category1Name = removeSpace($rows['cat1Name']);
			$category2Name = removeSpace($rows['cat2Name']);
			$locationName = removeSpace($rows['locationName']);
		}else{
			$category1Name = removeSpace($rows['cat1py']);
			$category2Name = removeSpace($rows['cat2py']);
			$locationName = removeSpace($rows['locationPy']);
		}
		return $link = LIVESITEURL."/".$lang."/".$category1Name."/".$category2Name."/".$locationName."/".md5($location_id);
	}


  
  
   function chkEmailExist($email){
		$sql = mysql_query("SELECT id FROM pre_registration WHERE email = '".$email."'");
		if(mysql_num_rows($sql) > '0'){
			$row = mysql_fetch_assoc($sql);
			return $row['id'];			
		}else{
			return false;
		}
	}
  
  
  
  
  function sendMail($toMail,$subject,$message){
 		$headers = "Content-type: text/html; charset=iso-8859-1\n";					// Mail headers
		$headers .= 'From: SearchNongbo<info@searchningbo.net>' . "\r\n";
		@mail($toMail, $subject, $message, $headers);
}

   

	function add_record($data_array,$table){
		$q = "insert into ".$table ." set ";
		$i = 0;
		foreach($data_array as $key => $val){
			if($i == 0)
				$q .= $key ." = '".$val."' ";
			else
				$q .= ", ".$key ." = '".$val."' ";
			$i++;
		}
   //echo "<br> q :: $q";exit;
		$r = mysql_query($q);
		return $lastInsertId = mysql_insert_id();
		//return $r;
	}

	function update_record($data_array,$table,$id){
		$q = "update ".$table ." set ";
		$i = 0;
		foreach($data_array as $key => $val){
			if($i == 0)
				$q .= $key ." = '".$val."' ";
			else
				$q .= ", ".$key ." = '".$val."' ";
			$i++;
		}
  		$q .= " where id  in ($id)";
 // echo $q;exit;
			$r = mysql_query($q);

		return $id;
	}
	
	
	
	
	function getCategory1ListingEn($active = NULL){
	//border: 1px solid #158ecb;	background-color:#20aedd;color:#fff;text-shadow:0 1px 0px #3275bc;
		$category1String = '';
		$category = mysql_query("SELECT * FROM category1 ORDER BY name_en ASC");
		while($category1RS = mysql_fetch_assoc($category)){
			$categoryName = (htmlspecialchars_decode($category1RS['name_en']) == $category1RS['name_en']) ? htmlspecialchars($category1RS['name_en']) : $category1RS['name_en'];
			$link = LIVESITEURL."/en/".removeSpace($categoryName);	
			if($active == ""){
				$category1String .= "<li><a href='".$link."'>".$category1RS['name_en']."</a></li> | ";
			}else{
				if($active == $category1RS['id']) { $class = "turn_on";}else{$class = "turn_off";}
				$cat1_id = "cat1_".$category1RS['id'];
				$cat1_ids = "'".$cat1_id."'";
				$lang = "'en'";
			$category1String .= '<li><a href="javascript:void(0);" onclick="changeCategory1('.$cat1_ids.','.$lang.')" id='.$cat1_id.' class='.$class.'>'.$category1RS['name_en'].'</a></li>';
			}
		}
		return $category1String;
	}
	
	function getCategory1ListingCn($active = NULL){
		$category1String = "";
		$category = mysql_query("SELECT * FROM category1");
		while($category1RS = mysql_fetch_assoc($category)){
			$categoryName = (htmlspecialchars_decode($category1RS['name_py']) == $category1RS['name_py']) ? htmlspecialchars($category1RS['name_py']) : $category1RS['name_py'];
			$link = LIVESITEURL."/cn/".removeSpace($categoryName);	
			if($active == ""){
				$category1String .= "<li><a href='".$link."'>".$category1RS['name_cn']."</a></li> | ";
			}else{
				if($active == $category1RS['id']) { $class = "turn_on";}else{$class = "turn_off";}
				$cat1_id = "cat1_".$category1RS['id'];
				$cat1_ids = "'".$cat1_id."'";
				$lang = "'cn'";
				$category1String .= '<li><a href="#" onclick="changeCategory1('.$cat1_ids.','.$lang.')" id='.$cat1_id.' class='.$class.'>'.$category1RS['name_cn'].'</a></li>';
//				$category1String .= "<li><a href='#' id='".$cat1_id."' class='".$class."'>".$category1RS['name_cn']."</a></li>";
			}
		}
		return $category1String;
	}




	 // show left menus in chinese
	function showLeftMenusCn($requestType){
			 $locations = getUserLocation($_COOKIE['userid']);
			 $location_name = removeSpace($locations['name_py']);
			 $locationLink = LIVESITEURL."/cn/".$requestType[2]."/".$location_name;
		return $menus = ' <ul>
                 <li class="main_menu_title"><span>管理员</span></li>
                 <li class="main_menu_content" id="manage"><span><a href="'.$locationLink.'">管理位置信息</a></span></li>
                 <li class="main_menu_content" id="add_location"><span>添加地点</span></li>
                 <li class="main_menu_content" id="add_user"><span>添加用户</span></li>
                 <li class="main_menu_content" id="link"><span>用户定位</span></li>
                 <li class="main_menu_title"><span>位置信息</span></li>
                 <li class="main_menu_content" id="category"><span>分类</span></li>
                 <li class="main_menu_content" id="information"><span>基本信息</span></li>
                 <li class="main_menu_content" id="picture"><span>图片</span></li>
                 <li class="main_menu_content" id="location"><span>地图</span></li>
                 <li class="main_menu_content" id="details"><span>详情</span></li>
                 <li class="main_menu_title"><span>用户</span></li>
                 <li class="main_menu_content" id="profile"><span>我的主页</span></li>
                 <li class="main_menu_content" id="photo"><span>我的相册</span></li>
                 <li class="main_menu_content" id="friends"><span>我的好友</span></li>
                 <li class="main_menu_content" id="favorite"><span>我的收藏</span></li>
              </ul>';
	}
	
	
	
	// show left menus in English
	function showLeftMenusEn($requestType){
			 $locations = getUserLocation($_COOKIE['userid']);
			 $location_name = removeSpace($locations['name_en']);
			 $locationLink = LIVESITEURL."/en/".$requestType[2]."/".$location_name;
		return $menus = ' <ul>
                 <li class="main_menu_title"><span>Administrator</span></li>
                 <li class="main_menu_content" id="manage"><span><a href="'.$locationLink.'">Manage Locations</a></span></li>
                 <li class="main_menu_content" id="add_location"><span>Add Location</span></li>
                 <li class="main_menu_content" id="add_user"><span>Add User</span></li>
                 <li class="main_menu_content" id="link"><span>User location</span></li>
                 <li class="main_menu_title"><span>Location information</span></li>
                 <li class="main_menu_content" id="category"><span>Classification</span></li>
                 <li class="main_menu_content" id="information"><span>Basic information</span></li>
                 <li class="main_menu_content" id="picture"><span>Picture</span></li>
                 <li class="main_menu_content" id="location"><span>Map</span></li>
                 <li class="main_menu_content" id="details"><span>Detail</span></li>
                 <li class="main_menu_title"><span>User</span></li>
                 <li class="main_menu_content" id="profile"><span>My Homepage</span></li>
                 <li class="main_menu_content" id="photo"><span>My Album</span></li>
                 <li class="main_menu_content" id="friends"><span>My Friends</span></li>
                 <li class="main_menu_content" id="favorite"><span>My Favorites</span></li>
              </ul>';
	}
	
	
	
	#############################  show cat 2 ####################################################
	
		function getCat2counting($cat1){
 			$sql = mysql_query("SELECT id FROM category2 WHERE category1_id = '".$cat1."'");
			while($rows = mysql_fetch_assoc($sql)){
				$rs[] = $rows['id'];
			}
			$ids = implode(",",$rs);
			return $ids;
		}
	
	function getCategory2ListingEn($active = NULL,$category1_id){
	//border: 1px solid #158ecb;	background-color:#20aedd;color:#fff;text-shadow:0 1px 0px #3275bc;
		$category2String = "";
		 $catids = getCat2counting($category1_id);
		$category = mysql_query("SELECT * FROM category2 WHERE category1_id = '".$category1_id."' ORDER BY name_en ASC");
		while($category2RS = mysql_fetch_assoc($category)){
			$categoryName = (htmlspecialchars_decode($category2RS['name_en']) == $category2RS['name_en']) ? htmlspecialchars($category2RS['name_en']) : $category2RS['name_en'];
			if($active <> ""){
				if($active == $category2RS['id']) { $class = "turn_on";}else{$class = "turn_off";}
				$cat2_id = "cat2_".$category2RS['id'];
				 $cat2_ids = "'".$cat2_id."'";
				 
				 $category2String .= '<li><a href="javascript:void(0);" onclick="changeCategory2('.$cat2_ids.')" id='.$cat2_id.' class='.$class.'>'.$category2RS['name_en'].'</a></li>';				
 			}
		}
		$category2String .= '<input type="hidden" name="cate2IDS" id="cate2IDS" value="'.$catids.'">';
		return $category2String;
	}

	
	 	function getCategory2ListingCn($active = NULL,$category1_id){
		$category2String = "";
		 $catids = getCat2counting($category1_id);
		$category = mysql_query("SELECT * FROM category2 WHERE category1_id = '".$category1_id."'");
		while($category2RS = mysql_fetch_assoc($category)){
			$categoryName = (htmlspecialchars_decode($category2RS['name_py']) == $category2RS['name_py']) ? htmlspecialchars($category2RS['name_py']) : $category2RS['name_py'];
			$link = LIVESITEURL."/cn/".removeSpace($categoryName);	
			if($active <> ""){
				if($active == $category2RS['id']) { $class = "turn_on";}else{$class = "turn_off";}
				$cat2_id = "cat2_".$category2RS['id'];
				 $cat2_ids = "'".$cat2_id."'";
				 $category2String .= '<li><a href="javascript:void(0);" onclick="changeCategory2('.$cat2_ids.')" id='.$cat2_id.' class='.$class.'>'.$category2RS['name_cn'].'</a></li>';				
			}
		}
		$category2String .= '<input type="hidden" name="cate2IDS" id="cate2IDS" value="'.$catids.'">';
		return $category2String;
	}

 
 
 
 
 // top menus 
 
 	function topMenusItems($lang,$request,$staticPages){
		if($lang == 'cn'){	
		
			if(in_array($request,$staticPages)){
				$langChangeURL = LIVESITEURL."/en/".$request;
			}else{
				$langChangeURL = LIVESITEURL."/en";
			}
		
			$items = '<ul>
						<li><a href="'.$langChangeURL.'" tabindex="2">English</a></li>
					  </ul>
					  <ul>
						<li><a href="site_map.html" tabindex="3">网站导航</a></li>&nbsp;&nbsp;&nbsp;&nbsp;
						<li><a href="'.LIVESITEURL.'/cn/features">特性</a></li>&nbsp;&nbsp;&nbsp;&nbsp;
						<li><a href="'.LIVESITEURL.'/cn/aboutus">关于我们</a></li>
					  </ul>';
		}else{
			if(in_array($request,$staticPages)){
				$langChangeURL = LIVESITEURL."/cn/".$request;
			}else{
				$langChangeURL = LIVESITEURL."/cn";
			}

			$items = '<ul>
						<li><a href="'.$langChangeURL.'" tabindex="2">中文</a></li>
					  </ul>
					  <ul>
						<li><a href="site_map.html" tabindex="3">Site Map</a></li>&nbsp;&nbsp;&nbsp;&nbsp;
						<li><a href="'.LIVESITEURL.'/en/features">Features</a></li>&nbsp;&nbsp;&nbsp;&nbsp;
						<li><a href="'.LIVESITEURL.'/en/aboutus">About Us</a></li>
					  </ul>';
		}
		return $items;
	}
 
 
 
 
	?>