<?php
 	if(!isset($_COOKIE['userid']) || empty($_COOKIE['userid'])){
		header("location:".LIVESITEURL);
		exit;
	}
	
 // GET HTML //
	$raw_in = file_get_contents("html/welcome_cn.html");

// GET PAGE DATA //
 	$sitename_cn = "搜索宁波";
	$siteURL = "soningbo.com";
	$category1Listing = getCategory1ListingCn();
	$show_left_menu_cn = showLeftMenusCn($requestType);
	$topMenusItems = topMenusItems($lang,$request2,$staticPages); 
// COMBINE PAGE DATA AND HTML //
	$find = array("#username","#all_category1_cn","#show_left_menu_cn","#topMenusItems"); 
 	$replaceCatName = array($_COOKIE['username'],$category1Listing,$show_left_menu_cn,$topMenusItems); 
	$result = str_replace($find, $replaceCatName, $raw_in); 
	

// WRITE OUT HTML
	echo $result;	
	
?>
