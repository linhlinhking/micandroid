<?php

// GET HTML //
	$raw_in = file_get_contents("html/location_cn.html");
	$cat2name = trim(urldecode(makeSpace($requestType[3])));
	$cat1name = trim(urldecode(makeSpace($requestType[2])));

// SQL STATEMENTS //
	$category1 = mysql_query("SELECT * FROM locations WHERE md5(id) = '".$requestType[5]."'");
	$result = mysql_fetch_assoc($category1);


// GET PAGE DATA //
	$sitename_cn = "搜索宁波";
	$siteURL = "soningbo.com";
	$location_id = $result['id']; 
	$category1_cn = getCategoryChineseName($cat1name,'cn');
	$category2_cn = getCategory2ChineseName($cat2name,'cn');
	$all_category1 = getCategory1ListingCn();
	$name_cn = $result['name_cn'];
	$address_cn = $result['address_cn'];
	$telephone = $result['telephone'];
	$topMenusItems = topMenusItems($lang,$request2,$staticPages);

// COMBINE PAGE DATA AND HTML //
	$find = array("#sitename_cn","#siteURL","#category1_cn","#category2_cn","#name_cn","#address_cn","#telephone","<li>#all_category1_cn</li>","#location_id","topMenusItems"); 
 	$replaceCatName = array($sitename_cn,$siteURL,$category1_cn,$category2_cn,$name_cn,$address_cn,$telephone,$all_category1,$location_id,$topMenusItems); 
	$result = str_replace($find, $replaceCatName, $raw_in); 


// WRITE OUT HTML
	echo $result;

?>
