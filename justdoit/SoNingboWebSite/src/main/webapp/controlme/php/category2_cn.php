<?php
// GET HTML //
$raw_in = file_get_contents("html/category2_cn.html");
$cat2name = trim(urldecode(makeSpace($requestType[3])));
$cat1name = trim(urldecode(makeSpace($requestType[2])));

// SQL STATEMENTS //
 	$category1 = mysql_query("SELECT id,name_cn,description_cn,category1_id,name_py FROM category2 WHERE name_py = '$cat2name'");
	$result = mysql_fetch_assoc($category1);
	$categoryName = $result['name_cn'];
	$description = $result['description_cn'];
	$category2_id = $result['id'];
	
// locations listing
	$locationString = "";
 	$locations = mysql_query("SELECT l.* FROM locations_category AS lc LEFT JOIN locations AS l  ON l.id = lc.location_id WHERE lc.category2_id = '".$category2_id."'");
	while($locationRS = mysql_fetch_assoc($locations)){
		$link = LIVESITEURL."/cn/".removeSpace($cat1name)."/".removeSpace($cat2name)."/".removeSpace($locationRS['name_py']."/".md5($locationRS['id']));	
		$locationString .= "<li><a href='".$link."'>".$locationRS['name_cn']."</a></li>"; 
	}


// GET PAGE DATA //
	$sitename_cn = "搜索宁波";
	$siteURL = "soningbo.com";
	$linkCat1 = removeSpace($cat1name);
	$category1_cn = getCategoryChineseName($cat1name,'cn');
	$category2_cn = $result['name_cn'];
	$category2_description_cn = $result['description_cn'];
	$category1Listing = getCategory1ListingCn();
	$topMenusItems = topMenusItems($lang,$request2,$staticPages);

	

// COMBINE PAGE DATA AND HTML //
	$find = array("#sitename_cn","#siteURL","#category1_cn","#category2_cn","#category2_description_cn","<li>#all_locationname_cn</li>","<li>#all_category1_cn</li>","#linkCat1","#topMenusItems"); 
 	$replaceCatName = array($sitename_cn,$siteURL,$category1_cn,$category2_cn,$category2_description_cn,$locationString,$category1Listing,$linkCat1,$topMenusItems); 
	$result = str_replace($find, $replaceCatName, $raw_in); 


// WRITE OUT HTML
echo $result;
?>
