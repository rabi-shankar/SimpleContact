<?php

/*
 * coding: utf-8 
 * Created on Mon May 6 3:38:52 2019
 * @author: rabi
 * @language: PHP 
 * @version codes: 3.0v
 * @Project Name: Simple Concat
 *
*/

$msg = " ";
$numbers = " ";
$OTP = null;

$fastsms = ""; 

if ($_SERVER["REQUEST_METHOD"] == "GET") {
  
  $numbers = urlencode(get($_GET["numbers"]));
  $msg = urlencode(get($_GET["msg"]));
  //new 
  preg_match_all('!\d+!', $msg, $matches);
  $OTP = $matches[0][1];   // msg=hi+your+OTP+is%3A+123456 so your OTP is 2nd element of array 
  
}else{
    //echo("Android Application Request Method Error!");
}

//Function to remove extra space and special character from request
function get($data) {
    
  $data = trim($data);
  $data = stripslashes($data);
  $data = htmlspecialchars($data);
  return $data;
}


if($OTP!=null){
    
    $field = array(
        "sender_id" => "FSTSMS",
        "language" => "english",
        "route" => "qt",
        "numbers" => $numbers,
        "message" => "9978",
        "variables" => "{#BB#}",
        "variables_values" => $OTP
    );

    $curl = curl_init();
    curl_setopt_array($curl, array(
        CURLOPT_URL => "https://www.fast2sms.com/dev/bulk",
        CURLOPT_RETURNTRANSFER => true,
        CURLOPT_ENCODING => "",
        CURLOPT_MAXREDIRS => 10,
        CURLOPT_TIMEOUT => 30,
        CURLOPT_SSL_VERIFYHOST => 0,
        CURLOPT_SSL_VERIFYPEER => 0,
        CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
        CURLOPT_CUSTOMREQUEST => "POST",
        CURLOPT_POSTFIELDS => json_encode($field),
        CURLOPT_HTTPHEADER => array(
            "authorization:Fast2SMS_authorization_String",
            "cache-control: no-cache",
            "accept: */*",
            "content-type: application/json"
        ),
    ));
    
    $response = curl_exec($curl);
    $err = curl_error($curl);
    
    curl_close($curl);
    if ($err) {
        //echo "cURL Error #:" . $err;
    } else {
        //echo $response;
    }
}else{
    echo("error 2");
}
echo("Write by Rabi Shankar");

?>