<?php
 	if(!isset($_COOKIE['userid']) || empty($_COOKIE['userid'])){
		header("location:".LIVESITEURL);
		exit;
	}
	
 // GET HTML //
	$raw_in = file_get_contents("html/categories_en.html");

// GET PAGE DATA //
 	$sitename_en = "SoNingbo";
	$siteURL = "soningbo.com";
	$category1Listing = getCategory1ListingEn();
	$show_left_menu_en = showLeftMenusEn($requestType);
	$catgory1_en = getCategory1ListingEn($locations_info['category1_id']);
	$category2_en = getCategory2ListingEn($locations_info['category2_id'],$locations_info['category1_id']);
	
	$selectedcat1_id = "cat1_".$locations_info['category1_id'];
	$selectedcat2_id = "cat2_".$locations_info['category2_id'];
	
 	$topMenusItems = topMenusItems($lang,$request2,$staticPages);

 
// COMBINE PAGE DATA AND HTML //
	$find = array("#sitename_en","#siteURL","#show_left_menu_en","#username","#all_category1_en","#catgory1_en","#category1_id","#category2_id","#category2_en","#selectedcat1_id","#selectedcat2_id","#location_id",,"#topMenusItems"); 
 	$replaceCatName = array($sitename_en,$siteURL,$show_left_menu_en,$_COOKIE['username'],$category1Listing,$catgory1_en,$locations_info['category1_id'],$locations_info['category2_id'],$category2_en,$selectedcat1_id,$selectedcat2_id,$locations_info['location_id'],$topMenusItems); 
	
	$result = str_replace($find, $replaceCatName, $raw_in); 
	

// WRITE OUT HTML
	echo $result;	
	
?>
