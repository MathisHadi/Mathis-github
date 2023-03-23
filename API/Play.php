<?php
include ('Device.php');

function Change_Volume($token, $volume,$device_id){
    if ($device_id != -1) {
        $ch = curl_init();

        curl_setopt($ch, CURLOPT_URL, 'https://api.spotify.com/v1/me/player/volume?volume_percent='. $volume .'&device_id='.$device_id .'');
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
        curl_setopt($ch, CURLOPT_CUSTOMREQUEST, 'PUT');


        $headers = array();
        $headers[] = 'Accept: application/json';
        $headers[] = 'Content-Type: application/json';
        $headers[] = 'Authorization: Bearer ' . $token;
        curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);

        $result = curl_exec($ch);
        if (curl_errno($ch)) {
            echo 'Error:' . curl_error($ch);
        }
        echo $result;
        curl_close($ch);
    }
}

//Change_Volume($token, 10, $device_id);


function Pause($token,$device_id){
    $ch = curl_init();

curl_setopt($ch, CURLOPT_URL, 'https://api.spotify.com/v1/me/player/pause?device_id='.$device_id.'');
curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
curl_setopt($ch, CURLOPT_CUSTOMREQUEST, 'PUT');


$headers = array();
$headers[] = 'Accept: application/json';
$headers[] = 'Content-Type: application/json';
$headers[] = 'Authorization: Bearer '.$token;
curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);

$result = curl_exec($ch);
if (curl_errno($ch)) {
    echo 'Error:' . curl_error($ch);
}
curl_close($ch);
}

function Play_current($token,$device_id){
    $ch = curl_init();

curl_setopt($ch, CURLOPT_URL, 'https://api.spotify.com/v1/me/player/play?device_id='.$device_id.'');
curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
curl_setopt($ch, CURLOPT_CUSTOMREQUEST, 'PUT');


$headers = array();
$headers[] = 'Accept: application/json';
$headers[] = 'Content-Type: application/json';
$headers[] = 'Authorization: Bearer '.$token;
curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);

$result = curl_exec($ch);
if (curl_errno($ch)) {
    echo 'Error:' . curl_error($ch);
}
curl_close($ch);
}

function Get_Play_Statut($token){
    $ch = curl_init();

    curl_setopt($ch, CURLOPT_URL, 'https://api.spotify.com/v1/me/player');
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
    curl_setopt($ch, CURLOPT_CUSTOMREQUEST, 'GET');


    $headers = array();
    $headers[] = 'Accept: application/json';
    $headers[] = 'Content-Type: application/json';
    $headers[] = 'Authorization: Bearer '.$token;
    curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);

    $result = curl_exec($ch);
    if (curl_errno($ch)) {
        echo 'Error:' . curl_error($ch);
    }
    curl_close($ch);
    $jsonObj = json_decode($result);
   
    return $jsonObj->is_playing;
      
}
Get_Play_Statut($token);

function Play_Pause($token,$device_id){
    if ( Get_Play_Statut($token)==true){
        Pause($token,$device_id);
    }
    else {
        Play_current($token, $device_id);
    }
}

//Play_Pause($token, $device_id);

echo $token . "\n";