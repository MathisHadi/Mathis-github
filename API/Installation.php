
<?php
// Creation User ID
include('GetNewToken.php');

function Get_UserID($token)
{
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
    $jsonObj = json_decode($result);
    
    return $jsonObj->id;
}

function Test_profil($token){
        $file = 'Profil.txt';
        $f = fopen($file, 'r');
        $user_id = fgets($f);
        fclose($f);
        if ( strlen($user_id) != 28){
        echo "\033[31m Pas pas de profil ID !\033[0m\n\n";
        echo " Generation Nouveau Profil ...\n";
        $fh = fopen( 'Profil.txt', 'w' );
        fclose($fh);
        echo "Nouveau Profil ID !\n";
        $contents = file_get_contents($file);
        $contents = str_replace(1, '', $contents);
        file_put_contents($file, $contents);
        $user_id = Get_UserID($token);
        $file = 'Profil.txt';
        $f = fopen($file, 'r+');
        fputs($f, $user_id);
        fclose($f);
          Test_profil($token);
      }
      else {
echo "\033[32m |-----------| \n";
        echo " | ID Valide | \n";
        echo " |-----------|\n\033[0m";
      }
    return $user_id;

}
$user_id = Test_profil($token);


// Creation/Vérification d'une playlist : 


    function Create_Playliste($token, $user_id, $name){
        $ch = curl_init();
        
        curl_setopt($ch, CURLOPT_URL, 'https://api.spotify.com/v1/users/'.$user_id.'/playlists');
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
        curl_setopt($ch, CURLOPT_POST, 1);
        curl_setopt($ch, CURLOPT_POSTFIELDS, "{\"name\":\"".$name."\",\"description\":\"New playlist description\",\"public\":false}");
        
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
        echo $result;
        }
        
        function Get_Playlist_ID($token, $user_id,$name)
        {
            $ch = curl_init();
        
            curl_setopt($ch, CURLOPT_URL, 'https://api.spotify.com/v1/me/playlists?limit=10');
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
        
            $tab = array(array());
            $jsonObj = json_decode($result);
            $limit = $jsonObj->total;
           // echo $jsonObj->items[0]->name;
            $num = -1;
            for ($i = 0; $i < $limit; $i++) {
                if (strcmp($jsonObj->items[$i]->name,$name) == 0) {
                    echo "\n\033[32m |----------------------------------------- \n";
                    echo " |  Playlist trouvé : ".$name." \n";
                    echo " |-----------------------------------------\n\033[0m";
                    return $jsonObj->items[$i]->id;
                    }
                }
            echo "\033[31m Pas de playliste nommé ".$name." : création d'une nouvelle playlist : \033[0m\n\n";
           echo $result;
           // Create_Playliste($token,$user_id,$name);
           // Get_Playlist_ID($token,$user_id,$name);
        
            }
        
        
        $playlist_id_main =  Get_Playlist_ID($token,$user_id,"Music Project");
        $playlist_id_temp = Get_Playlist_ID($token,$user_id,"Music Project - Temp");



        $file = 'playlist.txt';
        $f = fopen($file, 'r+');
        fputs($f, $playlist_id_main);
        fclose($f);




              
