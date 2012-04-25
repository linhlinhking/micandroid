<?php

// GET HTML //
	$raw_in = file_get_contents("html/location_en.html");
	$cat2name = trim(urldecode(makeSpace($requestType[3])));
	$cat1name = trim(urldecode(makeSpace($requestType[2])));
 
// SQL STATEMENTS //
	$category1 = mysql_query("SELECT * FROM locations WHERE md5(id) = '".$requestType[5]."'");
	$result = mysql_fetch_assoc($category1);


// GET PAGE DATA //
	$sitename_en = "SoNingbo";
	$siteURL = "soningbo.com";
	$location_id = $result['id']; 
	$category1_en = $cat1name;
	$category2_en = $cat2name;
	$all_category1 = getCategory1ListingEn();
	$name_en = $result['name_en'];
	$address_en = $result['address_en'];
	$telephone = $result['telephone'];
	$all_category1 = getCategory1ListingEn();
	$topMenusItems = topMenusItems($lang,$request2,$staticPages);


// COMBINE PAGE DATA AND HTML //
	$find = array("#sitename_en","#siteURL","#category1_en","#category2_en","#name_en","#address_en","#telephone","<li>#all_category1_en</li>","#location_id","#topMenusItems"); 
 	$replaceCatName = array($sitename_en,$siteURL,$category1_en,$category2_en,$name_en,$address_en,$telephone,$all_category1,$location_id,$topMenusItems); 
	$result = str_replace($find, $replaceCatName, $raw_in); 


// WRITE OUT HTML
	echo $result;

?>
