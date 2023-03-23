<?php


function Token()
{
    $date = date('d-m-y h:i:s');
    $ch = curl_init();

    curl_setopt($ch, CURLOPT_URL, 'https://accounts.spotify.com/api/token');
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
    curl_setopt($ch, CURLOPT_POST, 1);
    curl_setopt($ch, CURLOPT_POSTFIELDS, "grant_type=refresh_token&refresh_token=AQD8YFwmBeHALzNEW0jN6DcyJqVjYh2Ys_a1cN9Wtm2AdcAeLsf_hWpfmuWVF956-aooxETLQq6J--RdqWmowKDE0FOt-e2ruP4DZSYad5SkhHGn0xIm_vYIaZg0aobg7Pk");

    $headers = array();
    $headers[] = 'Content-Type: application/x-www-form-urlencoded';
    $headers[] = 'Authorization: Basic MWM2MTAyNTRmZWRjNGJhZWJjY2M1ODU4YjI2MmI2M2Q6Y2NiOTBkNTZmYzY3NGM5OTlkODNkYmU2NjBjNDcyZmM=';
    curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);

    $result = curl_exec($ch);
    #echo($result);
    $jsonObj = json_decode($result);
    //  echo ("Token : --> ");
    //echo $jsonObj->access_token ."\n";
    $TOKEN_ID = $jsonObj->access_token;

    $file = 'Token.txt';
    $f = fopen($file, 'r+');
    fputs($f, $TOKEN_ID);

    fclose($f);

    return [$TOKEN_ID,$jsonObj->expires_in,$date];
}

/*
function GetToken()
{
    /// A remettre plus tard 
    // echo $tab[0];
    $date_now = date('d-m-y h:i:s');

    $file = 'Token.txt';
    $f = fopen($file, 'r');
    $line = fgets($f);
    fclose($f);
    $line2 = substr($line, 0, 17);
   // echo $line. "\n";;
    $line_s =strtotime($line2);
    $date_now_s = strtotime($date_now);
   // echo $line_s . "\n";
   // echo $date_now_s . "\n";
    
    if ( $line_s + 2000 < $date_now_s  ){
        $tab = Token();
        
        echo " Token expiré ! Nouveau Token : \n \n";
        echo $tab[0]."\n";
      
        return $tab[0];
    }
    else {
        $token_ = substr($line, -(strlen($line)-17));
      
        echo "Token pas encore expiré, temps avant renouvellement : \n";
        echo "                                                     " . gmdate("i",$line_s + 2000 - $date_now_s). " minutes et " . gmdate("s",$line_s + 2000 - $date_now_s) . " secondes \n";
        echo "\n";
        echo "              Token actuel : \n\n";
        echo $token_."\n";
        
        return $token_;
        
    }
}
*/

function Request($token){
$ch = curl_init();

curl_setopt($ch, CURLOPT_URL, 'https://api.spotify.com/v1/me');
curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
curl_setopt($ch, CURLOPT_CUSTOMREQUEST, 'GET');


$headers = array();
$headers[] = 'Accept: application/json';
$headers[] = 'Content-Type: application/json';
$headers[] = 'Authorization: Bearer ' . $token;
curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);

$result = curl_exec($ch);
if (curl_errno($ch)) {
    echo 'Error:' . curl_error($ch);
}
curl_close($ch);
  //echo $result;
  return $result;
}

$file = 'Token.txt';
$f = fopen($file, 'r');
$token = fgets($f);
fclose($f);

$stop = 0;
function TestToken($token){
  echo "\033[34m\n\n TEST DU TOKEN....    \n\n\033[0m";
  
  if ( strlen($token) != 286 || substr(Request($token), 0, 11)== "{\n  \"error\""){
      echo "\033[31m ! Token non conforme !\033[0m\n\n";
      echo " Generation Nouveau Token ...\n";
      $fh = fopen( 'Token.txt', 'w' );
      fclose($fh);
  
      echo "Nouveau Token Généré !\n";
      $token = Token()[0];
      $file = 'Token.txt';
      $f = fopen($file, 'r+');
      fputs($f, $token);
      fclose($f);
      TestToken($token);
 }
 else if (substr(Request($token), 0, 13)=="{\n  \"country\""){
    echo "\033[32m |--------------| \n";
    echo " | Token Valide | \n";
    echo " |--------------|\n\033[0m";
    //echo "Token en cours : \n";
    //echo $token."\n";
    return $token;
 }
 else {
  echo "\033[31m Erreur du token inconnu \033[0m\n\n";

 }

}
$token = TestToken($token);


?>
