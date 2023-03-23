<?php

$fh = fopen( 'Token.txt', 'w' );
fclose($fh);
echo "\033[32m ! Token  Supprimé !\033[0m\n\n";

$fh = fopen( 'Playlist.txt', 'w' );
fclose($fh);
echo "\033[32m ! PlaylistID  Supprimé !\033[0m\n\n";

$fh = fopen( 'Profil.txt', 'w' );
fclose($fh);
echo "\033[32m ! ProfilID  Supprimé !\033[0m\n\n";
