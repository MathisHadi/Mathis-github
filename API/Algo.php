<?php

 function Preference($l) {
    // Créer une nouvelle liste avec des clés de $l et des valeurs de 0  
    $keys = array_column($l, 0);
    $pref = array_fill_keys($keys, 0);

    // Durability : 0

   for ($i = 0; $i < count($l); $i++) {
        if ((120000 > $l[$i][1]) && (480000 < $l[$i][1])) {
            $pref[$l[$i][0]] += 20;
         }
        if ((240000 < $l[$i][1]) && (480000 > $l[$i][1])) {
            $pref[$l[$i][0]] += 10;
         }
   }

    // loudness : 8


   return $pref;
}


// établir la matrice distance
//possiblement optimisable car matrice symétrique
function MatriceDistance($l){
   $n = count($l);
   $matrice = array();
   for ($i=0;$i<$n;$i++){
      $matrice[$i] = array();
      for ($j=0;$j<$n;$j++){
         if ($i=$j){
            $matrice[$i][$j] = -1;
         }
         else {
         $matrice[$i][$j] = distance($l,$i, $j);
         }
      }
   }
   return($matrice);
}



$jayParsedAry = [
  [
        "Les sardines",
        233867,
        0.772,
        154.015,
        0.139,
        154.015,
        0.966,
        0.874,
        -4.164,
        1.54E-6,
        0.885,
        0.00889,
        "chanson paillarde",
        "francoton",
        "french pop"
     ],
  [
           "Balance ton quoi",
           189480,
           0.703,
           115.932,
           0.0823,
           115.932,
           0.381,
           0.442,
           -10.383,
           2.39E-5,
           0.276,
           0.594,
           "belgian pop",
           "variete francaise"
        ],
  [
              "Basique",
              163987,
              0.827,
              132.068,
              0.269,
              132.068,
              0.587,
              0.625,
              -5.996,
              6.23E-6,
              0.109,
              0.331,
              "french hip hop",
              "old school rap francais",
              "rap conscient"
           ],
  [
                 "Papaoutai",
                 232147,
                 0.734,
                 116.024,
                 0.0839,
                 116.024,
                 0.254,
                 0.809,
                 -7.264,
                 0,
                 0.0642,
                 0.0266,
                 "belgian pop",
                 "g-house"
              ],
  [
                    "Partenaire Particulier",
                    246440,
                    0.736,
                    156.046,
                    0.0541,
                    156.046,
                    0.971,
                    0.927,
                    -3.487,
                    0.000256,
                    0.217,
                    0.678,
                    "french synthpop"
                 ],
  [
                       "Logobitombo (Corde à sauter)",
                       208987,
                       0.858,
                       139.993,
                       0.242,
                       139.993,
                       0.657,
                       0.849,
                       -3.008,
                       0,
                       0.383,
                       0.00752,
                       "francoton",
                       "french pop"
                    ],
  [
                          "L'aventurier",
                          231960,
                          0.556,
                          167.101,
                          0.0444,
                          167.101,
                          0.633,
                          0.909,
                          -9.982,
                          0.00058,
                          0.0971,
                          0.254,
                          "chanson",
                          "francoton",
                          "french pop",
                          "french rock",
                          "french synthpop"
                       ],
  [
                             "Bella",
                             226480,
                             0.781,
                             103.003,
                             0.0615,
                             103.003,
                             0.965,
                             0.944,
                             -2.64,
                             0.0795,
                             0.117,
                             0.0873,
                             "francoton",
                             "french hip hop",
                             "french pop",
                             "rap conscient"
                          ],
  [
                                "J't'emmène au vent",
                                184173,
                                0.567,
                                137.014,
                                0.0365,
                                137.014,
                                0.596,
                                0.771,
                                -5.577,
                                0,
                                0.101,
                                0.252,
                                "chanson",
                                "french pop",
                                "french reggae",
                                "french rock"
                             ],
  [
                                   "En feu",
                                   205293,
                                   0.588,
                                   139.75,
                                   0.351,
                                   139.75,
                                   0.557,
                                   0.945,
                                   -2.72,
                                   0,
                                   0.25,
                                   0.0721,
                                   "francoton",
                                   "french hip hop",
                                   "french pop",
                                   "rap marseille"
                                ]
];

$pref = Preference($l);
print_r($pref);



