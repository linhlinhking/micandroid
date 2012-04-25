<?php
// GET HTML //
$raw_in = file_get_contents("html/aboutus_en.html");

// GET PAGE DATA //

	$sitename_cn = "搜索宁波";
	$siteURL = "soningbo.com";
	$category1Listing = getCategory1ListingCn();

 
	$topMenusItems = topMenusItems($lang,$requestType[2],$staticPages);
	
	$find           = array("#topMenusItems"); 
 	$replaceCatName = array($topMenusItems); 
	$result = str_replace($find, $replaceCatName, $raw_in); 



// WRITE OUT HTML
	echo $result;
?>
