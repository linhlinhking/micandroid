<?php
// GET HTML //
$raw_in = file_get_contents("html/category2_en.html");

// SQL STATEMENTS //
	$cat2name = trim(urldecode(makeSpace($requestType[3])));
	$cat1name = trim(urldecode(makeSpace($requestType[2])));
	
	$category1 = mysql_query("SELECT id,name_en,description_en,category1_id FROM category2 WHERE name_en = '".$cat2name."'");
	$result = mysql_fetch_assoc($category1);
	$category2_id = $result['id'];
	
	// Category 2 listing
	$locationString = "";
 	$locations = mysql_query("SELECT l.* FROM locations_category AS lc LEFT JOIN locations AS l  ON l.id = lc.location_id WHERE lc.category2_id = '".$category2_id."'");
	while($locationRS = mysql_fetch_assoc($locations)){
		$link = LIVESITEURL."/en/".removeSpace($cat1name)."/".removeSpace($cat2name)."/".removeSpace($locationRS['name_en'])."/".md5($locationRS['id']);	
		$locationString .= "<li><a href='".$link."'>".$locationRS['name_en']."</a></li>"; 
	}


// GET PAGE DATA //

	$sitename_en = "SoNingbo";
	$siteURL = "soningbo.com";
	$category1_en = $cat1name;
	$category2_en = $result['name_en'];
	$category2_description_en = $result['description_en'];
	$category1Listing = getCategory1ListingEn();
	$topMenusItems = topMenusItems($lang,$request2,$staticPages);

// COMBINE PAGE DATA AND HTML //
	$find = array("#sitename_en","#siteURL","#category1_en","#category2_en","#category2_description_en","<li>#all_locationname_en</li>","<li>#all_category1_en</li>","#topMenusItems"); 
 	$replaceCatName = array($sitename_en,$siteURL,$category1_en,$category2_en,$category2_description_en,$locationString,$category1Listing,$topMenusItems); 
	$result = str_replace($find, $replaceCatName, $raw_in); 


// WRITE OUT HTML
echo $result;
?>
