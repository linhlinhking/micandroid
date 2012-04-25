<link rel="stylesheet"  type="text/css" href="http://controlme.co.uk/css/global.css" />
<?php

	if(isset($_SERVER['REQUEST_URI']) && !empty($_SERVER['REQUEST_URI'])){
		$requestType = explode("/",$_SERVER['REQUEST_URI']);
	}
	

	switch (count($requestType)){
		case 1:
			require_once('php/holding_en.php');
			break;
		case 2:
			if(!empty($requestType[1])){
				require_once('php/category1_en.php');
				break;
			}else{
				require_once('php/holding_en.php');
				break;
			}	
			
		case 3:
			if(!empty($requestType[2])){
				require_once('php/category2_en.php');
				break;
			}else{
				require_once('php/holding_en.php');
				break;
			}		
			
		case 4:
			if(!empty($requestType[3])){
				require_once('php/location_en.php');
				break;
			}else{
				require_once('php/holding_en.php');
				break;
			}		
	
		default:
			require_once('php/holding_en.php');
			break;
		}

?>