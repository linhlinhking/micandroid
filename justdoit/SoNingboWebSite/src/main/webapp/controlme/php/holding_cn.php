<?php
// GET HTML //
$raw_in = file_get_contents("html/holding_cn.html");

// GET PAGE DATA //

	$sitename_cn = "搜索宁波";
	$siteURL = "soningbo.com";
	$category1Listing = getCategory1ListingCn();

	$countString = "";
	$locationsArr = str_split(getLocationCount());
	foreach($locationsArr AS $k => $v){
		$numbertxt = getextnumber($v);				
		$countString .= '<div class="total '.$numbertxt.'"></div>';
	}

	$topMenusItems = topMenusItems($lang,$request2,$staticPages);
// COMBINE PAGE DATA AND HTML //
	$find = array("<li>#all_category1_cn</li>","#locationsCount","#topMenusItems"); 
 	$replaceCatName = array($category1Listing,$countString,$topMenusItems); 
	$result = str_replace($find, $replaceCatName, $raw_in); 


// WRITE OUT HTML
	echo $result;
?>
