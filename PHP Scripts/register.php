<?php

$email = $_POST["email"];
$password = $_POST["password"];
$phone_no= $_POST["phone_no"];
require "init.php";

$query= "select * from user_info where email like '".$email."';";
$result = mysqli_query($con,$query);

if(mysqli_num_rows($result)>0){
	$response=array();
	$code = "reg_false";
	$message = "User Already exist...";
	array_push($response,array("code"=>$code,"message"=>$message));
	echo json_encode(array("server_response"=>$response));
}

else{
	$query = "insert into user_info values('".$email."','".$password."','".$phone_no."');";
	$result = mysqli_query($con,$query);
	$response=array();
	$code = "reg_true";
	$message = "Registration Success ";
	array_push($response,array("code"=>$code,"message"=>$message));
	echo json_encode(array("server_response"=>$response));
	//}
	mysqli_close($con);
}

?>	

