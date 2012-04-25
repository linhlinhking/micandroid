<?php

// GET HTML //
$raw_in = file_get_contents("html/category1_cn.html");

// SQL STATEMENTS //
	$name_py = trim(urldecode(makeSpace($requestType[2])));
	$category1 = mysql_query("SELECT id,name_cn,name_py,description_cn FROM category1 WHERE name_py = '".$name_py."'");
	$result = mysql_fetch_assoc($category1);
	$category1_id = $result['id'];
	$categoryNameUrl = $result['name_cn'];

	// Category 2 kisting
	$cat2String = "";
	$subCat = mysql_query("SELECT name_cn,name_py FROM category2 WHERE category1_id = '".$category1_id."'");
	while($category2RS = mysql_fetch_assoc($subCat)){
			$link = LIVESITEURL."/cn/".removeSpace($result['name_py'])."/".removeSpace($category2RS['name_py']);	
			$cat2String .= "<li><a href='".$link."'>".$category2RS['name_cn']."</a></li>"; 
	}


// GET PAGE DATA //

$sitename_cn = "搜索宁波";
$siteURL = "soningbo.com";

$category1_cn = $result['name_cn'];
$category1_description = $result['description_cn'];
$category1Listing = getCategory1ListingCn();

$topMenusItems = topMenusItems($lang,$request2,$staticPages);
// COMBINE PAGE DATA AND HTML //

	$find           = array("#siteURL","#sitename_cn","#category1_cn","#category1_description_cn","<li>#all_category2_cn</li>","<li>#all_category1_cn</li>","#topMenusItems"); 
 	$replaceCatName = array($siteURL,$sitename_cn,$category1_cn,$category1_description,$cat2String,$category1Listing,$topMenusItems); 
	$result = str_replace($find, $replaceCatName, $raw_in); 




// WRITE OUT HTML
echo $result;
?>
