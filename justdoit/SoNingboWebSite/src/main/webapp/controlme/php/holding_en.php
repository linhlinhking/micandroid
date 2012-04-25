<?php
// GET HTML //
$raw_in = file_get_contents("html/holding_en.html");

// SQL STATEMENTS //


// GET PAGE DATA //

	$sitename_en = "SoNingbo";
	$siteURL = "soningbo.com";
	$category1Listing = getCategory1ListingEn();

	$countString = "";
	$locationsArr = str_split(getLocationCount());
	foreach($locationsArr AS $k => $v){
		$numbertxt = getextnumber($v);				
		$countString .= '<div class="total '.$numbertxt.'"></div>';
	}

	$topMenusItems = topMenusItems($lang,$request2,$staticPages);

// COMBINE PAGE DATA AND HTML //
	$find = array("<li>#all_category1_en</li>","#locationsCount","#topMenusItems"); 
 	$replaceCatName = array($category1Listing,$countString,$topMenusItems); 
	$result = str_replace($find, $replaceCatName, $raw_in); 


// WRITE OUT HTML
echo $result;
?>
