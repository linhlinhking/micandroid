<?php
// GET HTML //
	$raw_in = file_get_contents("html/category1_en.html");

// SQL STATEMENTS //
	$category1 = mysql_query("SELECT id,name_en,description_en FROM category1 WHERE name_en = '".makeSpace($requestType[2])."'");
	$result = mysql_fetch_assoc($category1);
	$category1_id = $result['id'];
 
	// Category 2 listing
	$cat2String = "";
	$subCat = mysql_query("SELECT name_en FROM category2 WHERE category1_id = '".$category1_id."'");
	while($category2RS = mysql_fetch_assoc($subCat)){
		$link = LIVESITEURL."/en/".removeSpace($result['name_en'])."/".removeSpace($category2RS['name_en']);	
		$cat2String .= "<li><a href='".$link."'>".$category2RS['name_en']."</a></li>"; 
	}


// GET PAGE DATA //
	$sitename_en = "SoNingbo";
	$siteURL = "soningbo.com";
	$category1_en = $result['name_en'];
	$category1_description = $result['description_en'];
	$category1Listing = getCategory1ListingEn();
	$topMenusItems = topMenusItems($lang,$request2,$staticPages);

	
// COMBINE PAGE DATA AND HTML //
	$find = array("#sitename_en","#siteURL","#category1_en","#category1_description_en","<li>#all_category2_en</li>","<li>#all_category1_en</li>","#topMenusItems"); 
 	$replaceCatName = array($sitename_en,$siteURL,$category1_en,$category1_description,$cat2String,$category1Listing,$topMenusItems); 
	$result = str_replace($find, $replaceCatName, $raw_in); 


// WRITE OUT HTML
echo $result;
?>
