<?php

$email = $_POST["email"];
$password = $_POST["password"];

require "init.php";

$query = "select * from user_info where email like '".$email."' and password like '".$password."';";
$result = mysqli_query($con,$query);

if(mysqli_num_rows($result)>0)
{   
	$response = array();
	$code = "login_true";
	$row = mysqli_fetch_array($result);
	$wel = $row[0];
	$message = "Login Success...Welcome ".$wel;
	array_push($response,array("code"=>$code,"message"=>$message));
	echo json_encode(array("server_response"=>$response));
}
else 
{
	$response = array();
	$code = "login_false";
	$message = "Login Failed ...Try Again";
	array_push($response,array("code"=>$code,"message"=>$message));
	echo json_encode(array("server_response"=>$response));
}
mysqli_close($con);
?>
