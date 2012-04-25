<?php

// Create database variables for local server
$host = "controlme.co.uk";
$username = "contMeUsr";
$password = "BS#&DH@(EJD@@*sd2d";
$database = "searchningbo";


/*
$host = "localhost";
$username = "root";
$password = "";
$database = "searchningbo";*/

// Create database connection 
$link = mysql_connect($host,$username,$password);
if (!$link) {
    die('Not connected : ' . mysql_error());
} 
$db_selected = mysql_select_db($database, $link);
if (!$db_selected) {
    die ('Can\'t use searchningbo: ' . mysql_error());
}
mysql_query("SET NAMES 'utf8'", $link);

session_start();

$timezone = "Asia/Shanghai";
if(function_exists('date_default_timezone_set')) date_default_timezone_set($timezone);

define("LIVESITEURL", "http://www.controlme.co.uk");
require_once('func.php');

?>