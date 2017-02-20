<?php
$db_name="shareloco";
$mysql_user="root";
$mysql_pass="";
$server_name="localhost";

$con = mysqli_connect($server_name,$mysql_user,$mysql_pass,$db_name);
if(!$con){
	//echo"Error in connection ...".mysqli_connect_error();
}
else{
	//echo"<h3>Database Connection is Successfull </h3>";
}

?>