<?php
//include('Ban.php');
include('Ban.php');
// Add a song to a playlist 
// Check if the song is ban 

/*
function Choose_Track($token,$fill,$playlist_id){
    $limit = 5;

    $tab = SearchTrack($token, str_replace(' ', '%20', $fill), $limit);
    
    for ($i = 0; $i < $limit; $i ++){
        echo " Résultat  : " . $i . "   " . $tab[$i][0] ." - ". $tab[$i][6]. "\n";
    }
    $a = readline('Enter a number: ');
    $uri = $tab[$a][8];
    if (Si_Deja_Jouer_Doublons($token, $uri,$playlist_id) == 1) {
        return $uri;
    }
    return -1;
}
*/
// Obetnir l'ID de la playlist
function get_playlist_txt(){
    $file = __DIR__ . '/playlist.txt';
$f = fopen($file, 'r');
$playlist_id_main = fgets($f);
return $playlist_id_main;
}


// Obtenir le toen
function get_token(){
    $file = __DIR__ . '/token.txt';
$f = fopen($file, 'r');
$token = fgets($f);
return $token;
}

// Ajouter à la playlist
function Add_Playlist($token,$uri,$playlist_id){
    //$uri = Choose_Track($token,$fill,$playlist_id);
    // Test doublon et joué récemment
    $result = Si_Deja_Jouer_Doublons($token, $uri, $playlist_id);
    if (  $result == 1) {
            $ch = curl_init();

            $uri2 = str_replace(':', '%3A', $uri);
            curl_setopt($ch, CURLOPT_URL, 'https://api.spotify.com/v1/playlists/' . $playlist_id . '/tracks?uris=' . $uri2 . '');
            curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
            curl_setopt($ch, CURLOPT_POST, 1);

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
            echo " Son ajouté ;) !" . "\n";
        }
        return  $result;
    }

//Add_Playlist($token,'spotify:track:3D29kjUyWxsT3jUUTtARVQ',$playlist_id_main);
//echo $token . "\n";

