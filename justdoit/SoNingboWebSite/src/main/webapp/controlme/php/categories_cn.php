<?php
 	if(!isset($_COOKIE['userid']) || empty($_COOKIE['userid'])){
		header("location:".LIVESITEURL);
		exit;
	}
	
 // GET HTML //
	$raw_in = file_get_contents("html/categories_cn.html");

// GET PAGE DATA //
 	$sitename_cn = "搜索宁波";
	$siteURL = "soningbo.com";
	$category1Listing = getCategory1ListingCn();
	$show_left_menu_cn = showLeftMenusCn($requestType);
	$catgory1_cn = getCategory1ListingCn($locations_info['category1_id']);
	$catgory2_cn = getCategory2ListingCn($locations_info['category2_id'],$locations_info['category1_id']);

	$selectedcat1_id = "cat1_".$locations_info['category1_id'];
	$selectedcat2_id = "cat2_".$locations_info['category2_id'];
	
	$topMenusItems = topMenusItems($lang,$request2,$staticPages);
 
// COMBINE PAGE DATA AND HTML //
	$find = array("#sitename_cn","#siteURL","#show_left_menu_cn","#username","#all_category1_cn","#catgory1_cn","#category1_id","#category2_id","#catgory2_cn","#selectedcat1_id","#selectedcat2_id","#location_id","#topMenusItems");
 	$replaceCatName = array($sitename_cn,$siteURL,$show_left_menu_cn,$_COOKIE['username'],$category1Listing,$catgory1_cn,$locations_info['category1_id'],$locations_info['category2_id'],$catgory2_cn,$selectedcat1_id,$selectedcat2_id,$locations_info['location_id'],$topMenusItems); 
	$result = str_replace($find, $replaceCatName, $raw_in); 
	

// WRITE OUT HTML
	echo $result;	
	
?>
