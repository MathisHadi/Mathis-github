package strategies;

import cantstop.Jeu;


/**
 * Stratégie 160
 *                                    * Fonction CHOIX *
 * 
 * Le choix de la stratégie est de choisir les cases qui sont le plus proche de gagner : pour cela on choix le Choix en regardant :
 *                        - La possibilité de gagner en 1 coup                   
 *                        - La possibilité de gagner en 2 coup                     
 *                        - La possibilité de gagner en 3 coup si le nombre est entre 5 et 9    
 *                        - La possibilité de gagner en 4 coup si le nombre est entre 6 et 8 
 *     Puis 
 *                 ** Si le nombre de bronze supérieur à 0 **                    ** Sinon **
 *                 -Si deux nombre entre 6 et 8                            |    -Si deux choix possible on sélection le meilleur avancement
 *                 -Si deux nombre pareil entre 5 et 9                     |    -On sélectionne le meilleur avancement
 *                 -Si un nombre ente 5 et 9 et un nombre entre 2 et 12    |
 *                 -Si deux nombre nombre pareil                           |
 *                 -Choix plus proche de 7                                 |
 * 
 *                                   * Fonction STOP *
 * 
 *  La fonction stop est basé sur l'ajout d'un nombre, ce nombre dépendant du choix fait ( la colonne numéro 2 vaux plus que la colonne 7).
 *  Lorsque que le total dépasse un certain seuil, alors le tour s'arrête.
 * 
 *  Dans ce programme, le seuil est adaptatif, c'est à dire qu'il évolue du score en cours en fonction du nombre de colonne prise par
 *  moi ou l'adversaire
 *                          auteur : MATHIS HADINGER
 * @author HADINGER
 */
 

public class Strat160 implements Strategie {
    private int COMPTEUR = 0;
    private int BONZE_RESTANT = 0;
    private double MAXIMUM_COMPTEUR = 30.5;


    // Fonction de choix
    public int D(Jeu j){
        int total = 0 ;
        for ( int i = 0 ; i < j.avancementJoueurEnCours().length; i++){
            total = j.avancementJoueurEnCours()[i] + total ;
        }
        if ( total == 0 ){
            if ( j.getBonzesRestants() == 3){
                return 1;
            }
            if ( j.getBonzesRestants() == 2){
                return 2;
            }
            if ( j.getBonzesRestants() == 1){
                return 3;
            }
            if ( j.getBonzesRestants() == 0){
                return 0;
        }}
        return -1; 
        }

    public int U_0_0_Bis(Jeu j){
        for ( int i = 0;  i<  j.getNbChoix() ; i++){
            int x1 = j.getLesChoix()[i][0];
            int x2 = j.getLesChoix()[i][1];

            if ( x1 == x2 ){
                if ( j.getBonzes()[0][0] == x1 && j.getBonzes()[0][1] + 2 >= j.getMaximum()[x1-2]){
                    return i;
                }
                if ( j.getBonzes()[1][0] == x1 && j.getBonzes()[1][1] + 2 >= j.getMaximum()[x1-2]){
                    return i;
                }
                if ( j.getBonzes()[2][0] == x1 && j.getBonzes()[2][1] + 2 >= j.getMaximum()[x1-2]){
                    return i;
                }
            }
            if ( x2 == 0 ) {
                if ( j.getBonzes()[0][0] == x1 && j.getBonzes()[0][1] + 1 >= j.getMaximum()[x1-2]){
                    return i;
                }
                if ( j.getBonzes()[1][0] == x1 && j.getBonzes()[1][1] + 1 >= j.getMaximum()[x1-2]){
                    return i;
                }
                if ( j.getBonzes()[2][0] == x1 && j.getBonzes()[2][1] + 1 >= j.getMaximum()[x1-2]){
                    return i;
                }
            }
            if ( x2 != 0 ){
                if ( j.getBonzes()[0][0] == x1 && j.getBonzes()[0][1] + 1 >= j.getMaximum()[x1-2]){
                    return i;
                }
                if ( j.getBonzes()[1][0] == x1 && j.getBonzes()[0][1] + 1 >= j.getMaximum()[x1-2]){
                    return i;
                }
                if ( j.getBonzes()[2][0] == x1 && j.getBonzes()[0][1] + 1 >= j.getMaximum()[x1-2]){
                    return i;
                }
                if ( j.getBonzes()[0][0] == x2 && j.getBonzes()[0][1] + 1 >= j.getMaximum()[x2-2]){
                    return i;
                }
                if ( j.getBonzes()[1][0] == x2 && j.getBonzes()[0][1] + 1 >= j.getMaximum()[x2-2]){
                    return i;
                }
                if ( j.getBonzes()[2][0] == x2 && j.getBonzes()[0][1] + 1 >= j.getMaximum()[x2-2]){
                    return i;
                }
            }
        }
        return -1;
    }

    public int U_0_0(Jeu j){
        for ( int i=0; i<j.getNbChoix(); i++){
            if( j.getLesChoix()[i][0] == j.getLesChoix()[i][1] ){
                int x = j.getLesChoix()[i][0];
                if ( j.avancementJoueurEnCours()[x-2] + 2 >= j.getMaximum()[x-2]){
                    return i;    }    }
                int x1 = j.getLesChoix()[i][0];
                int x2 = j.getLesChoix()[i][1];
                if ( x2 != 0 ){
                    if ( j.avancementJoueurEnCours()[x1-2] + 1 >= j.getMaximum()[x1-2] || j.avancementJoueurEnCours()[x2-2] + 1 >= j.getMaximum()[x2-2] ){
                        return i;
                    } 
                }
                if ( x2 == 0 ){
                    if ( j.avancementJoueurEnCours()[x1-2] + 1 >= j.getMaximum()[x1-2]){
                        return i;
                    } 
                }
                  }    return -1;    }

    public int U_0_0_T(Jeu j){
    for ( int i=0; i<j.getNbChoix(); i++){
        if( j.getLesChoix()[i][0] == j.getLesChoix()[i][1] ){
            int x = j.getLesChoix()[i][0];
            if ( j.avancementAutreJoueur()[x-2] + 2 >= j.getMaximum()[x-2]){
                return i;    }    }
            int x1 = j.getLesChoix()[i][0];
            int x2 = j.getLesChoix()[i][1];
            if ( x2 != 0 ){
                if ( j.avancementAutreJoueur()[x1-2] + 1 >= j.getMaximum()[x1-2] || j.avancementAutreJoueur()[x2-2] + 1 >= j.getMaximum()[x2-2] ){
                    return i;
                } 
            }
            if ( x2 == 0 ){
                if ( j.avancementAutreJoueur()[x1-2] + 1 >= j.getMaximum()[x1-2]){
                    return i;
                } 
            }
                }    return -1;    }              

    public int U_0_1_Bis(Jeu j){
    for ( int i = 0;  i<  j.getNbChoix() ; i++){
        int x1 = j.getLesChoix()[i][0];
        int x2 = j.getLesChoix()[i][1];

        if ( x1 == x2 ){
            if ( j.getBonzes()[0][0] == x1 && j.getBonzes()[0][1] + 3 >= j.getMaximum()[x1-2]){
                return i;
            }
            if ( j.getBonzes()[1][0] == x1 && j.getBonzes()[1][1] + 3 >= j.getMaximum()[x1-2]){
                return i;
            }
            if ( j.getBonzes()[2][0] == x1 && j.getBonzes()[2][1] + 3 >= j.getMaximum()[x1-2]){
                return i;
            }
        }
        if ( x2 == 0 ) {
            if ( j.getBonzes()[0][0] == x1 && j.getBonzes()[0][1] + 2 >= j.getMaximum()[x1-2]){
                return i;
            }
            if ( j.getBonzes()[1][0] == x1 && j.getBonzes()[1][1] + 2 >= j.getMaximum()[x1-2]){
                return i;
            }
            if ( j.getBonzes()[2][0] == x1 && j.getBonzes()[2][1] + 2 >= j.getMaximum()[x1-2]){
                return i;
            }
        }
        if ( x2 != 0 ){
            if ( j.getBonzes()[0][0] == x1 && j.getBonzes()[0][1] + 2 >= j.getMaximum()[x1-2]){
                return i;
            }
            if ( j.getBonzes()[1][0] == x1 && j.getBonzes()[0][1] + 2 >= j.getMaximum()[x1-2]){
                return i;
            }
            if ( j.getBonzes()[2][0] == x1 && j.getBonzes()[0][1] + 2 >= j.getMaximum()[x1-2]){
                return i;
            }
            if ( j.getBonzes()[0][0] == x2 && j.getBonzes()[0][1] + 2 >= j.getMaximum()[x2-2]){
                return i;
            }
            if ( j.getBonzes()[1][0] == x2 && j.getBonzes()[0][1] + 2 >= j.getMaximum()[x2-2]){
                return i;
            }
            if ( j.getBonzes()[2][0] == x2 && j.getBonzes()[0][1] + 2 >= j.getMaximum()[x2-2]){
                return i;
            }
        }
    }
    return -1;
    }              
   
    public int U_0_1(Jeu j){
        for ( int i=0; i<j.getNbChoix(); i++){
            if( j.getLesChoix()[i][0] == j.getLesChoix()[i][1] ){
                int x = j.getLesChoix()[i][0];
                if ( j.avancementJoueurEnCours()[x-2] + 3 >= j.getMaximum()[x-2]){
                    return i;    }    }
                int x1 = j.getLesChoix()[i][0];
                int x2 = j.getLesChoix()[i][1];
                if ( x2 != 0 ){
                    if ( j.avancementJoueurEnCours()[x1-2] + 2 >= j.getMaximum()[x1-2] || j.avancementJoueurEnCours()[x2-2] + 1 >= j.getMaximum()[x2-2] ){
                        return i;
                    } 
                }
                if ( x2 == 0 ){
                    if ( j.avancementJoueurEnCours()[x1-2] + 2 >= j.getMaximum()[x1-2]){
                        return i;
                    } 
                }
                  }    return -1;    }

    public int U_0_1_T(Jeu j){
    for ( int i=0; i<j.getNbChoix(); i++){
        if( j.getLesChoix()[i][0] == j.getLesChoix()[i][1] ){
            int x = j.getLesChoix()[i][0];
            if ( j.avancementAutreJoueur()[x-2] + 3 >= j.getMaximum()[x-2]){
                return i;    }    }
            int x1 = j.getLesChoix()[i][0];
            int x2 = j.getLesChoix()[i][1];
            if ( x2 != 0 ){
                if ( j.avancementAutreJoueur()[x1-2] + 2 >= j.getMaximum()[x1-2] || j.avancementAutreJoueur()[x2-2] + 1 >= j.getMaximum()[x2-2] ){
                    return i;
                } 
            }
            if ( x2 == 0 ){
                if ( j.avancementAutreJoueur()[x1-2] + 2 >= j.getMaximum()[x1-2]){
                    return i;
                } 
            }
                }    return -1;    }

    public int U_0_2_Bis(Jeu j){
    for ( int i = 0;  i<  j.getNbChoix() ; i++){
        int x1 = j.getLesChoix()[i][0];
        int x2 = j.getLesChoix()[i][1];

        if ( x1 == x2 ){
            if ( j.getBonzes()[0][0] == x1 && j.getBonzes()[0][1] + 4 >= j.getMaximum()[x1-2] && x1 > 4  && x1 < 10){
                return i;
            }
            if ( j.getBonzes()[1][0] == x1 && j.getBonzes()[1][1] + 4 >= j.getMaximum()[x1-2] && x1 > 4  && x1 < 10){
                return i;
            }
            if ( j.getBonzes()[2][0] == x1 && j.getBonzes()[2][1] + 4 >= j.getMaximum()[x1-2] && x1 > 4  && x1 < 10){
                return i;
            }
        }
        if ( x2 == 0 ) {
            if ( j.getBonzes()[0][0] == x1 && j.getBonzes()[0][1] + 3 >= j.getMaximum()[x1-2] && x1 > 4  && x1 < 10){
                return i;
            }
            if ( j.getBonzes()[1][0] == x1 && j.getBonzes()[1][1] + 3 >= j.getMaximum()[x1-2] && x1 > 4  && x1 < 10){
                return i;
            }
            if ( j.getBonzes()[2][0] == x1 && j.getBonzes()[2][1] + 3 >= j.getMaximum()[x1-2] && x1 > 4  && x1 < 10){
                return i;
            }
        }
        if ( x2 != 0 ){
            if ( j.getBonzes()[0][0] == x1 && j.getBonzes()[0][1] + 3 >= j.getMaximum()[x1-2] && x1 > 4  && x1 < 10){
                return i;
            }
            if ( j.getBonzes()[1][0] == x1 && j.getBonzes()[0][1] + 3 >= j.getMaximum()[x1-2] && x1 > 4  && x1 < 10){
                return i;
            }
            if ( j.getBonzes()[2][0] == x1 && j.getBonzes()[0][1] + 3 >= j.getMaximum()[x1-2] && x1 > 4  && x1 < 10){
                return i;
            }
            if ( j.getBonzes()[0][0] == x2 && j.getBonzes()[0][1] + 3 >= j.getMaximum()[x2-2] && x2 > 4  && x2 < 10){
                return i;
            }
            if ( j.getBonzes()[1][0] == x2 && j.getBonzes()[0][1] + 3 >= j.getMaximum()[x2-2] && x2 > 4  && x2 < 10){
                return i;
            }
            if ( j.getBonzes()[2][0] == x2 && j.getBonzes()[0][1] + 3 >= j.getMaximum()[x2-2] && x2 > 4  && x2 < 10){
                return i;
            }
        }
    }
    return -1;
    }    

    public int U_0_2(Jeu j){ 
    for ( int i=0; i<j.getNbChoix(); i++){
    if( j.getLesChoix()[i][0] == j.getLesChoix()[i][1] ){
        int x = j.getLesChoix()[i][0];
        if ( j.getLesChoix()[i][0] > 4 && j.getLesChoix()[i][0] < 10 ){
        if ( j.avancementJoueurEnCours()[x-2] + 4 >= j.getMaximum()[x-2]){
            return i;    }    }     }
    int x1 = j.getLesChoix()[i][0];
    int x2 = j.getLesChoix()[i][1];
    if ( j.avancementJoueurEnCours()[x1-2] + 3 >= j.getMaximum()[x1-2] ){
        if ( j.getLesChoix()[i][0] > 4 && j.getLesChoix()[i][0] < 10 ){
        return i;   }   }
    if ( x2 != 0 ){
    if ( j.avancementJoueurEnCours()[x2-2] + 3 >= j.getMaximum()[x2-2] ){
        if ( j.getLesChoix()[i][1] > 4 && j.getLesChoix()[i][1] < 10 ){
        return i;   }   }   }

            }    return -1;    }
    
    public int U_0_2_T(Jeu j){ 
        for ( int i=0; i<j.getNbChoix(); i++){
        if( j.getLesChoix()[i][0] == j.getLesChoix()[i][1] ){
            int x = j.getLesChoix()[i][0];
            if ( j.getLesChoix()[i][0] > 4 && j.getLesChoix()[i][0] < 10 ){
            if ( j.avancementAutreJoueur()[x-2] + 4 >= j.getMaximum()[x-2]){
                return i;    }    }     }
        int x1 = j.getLesChoix()[i][0];
        int x2 = j.getLesChoix()[i][1];
        if ( j.avancementAutreJoueur()[x1-2] + 3 >= j.getMaximum()[x1-2] ){
            if ( j.getLesChoix()[i][0] > 4 && j.getLesChoix()[i][0] < 10 ){
            return i;   }   }
        if ( x2 != 0 ){
        if ( j.avancementAutreJoueur()[x2-2] + 3 >= j.getMaximum()[x2-2] ){
            if ( j.getLesChoix()[i][1] > 4 && j.getLesChoix()[i][1] < 10 ){
            return i;   }   }   }
    
                }    return -1;    }

    public int U_0_3_Bis(Jeu j){
        for ( int i = 0;  i<  j.getNbChoix() ; i++){
            int x1 = j.getLesChoix()[i][0];
            int x2 = j.getLesChoix()[i][1];
    
            if ( x1 == x2 ){
                if ( j.getBonzes()[0][0] == x1 && j.getBonzes()[0][1] + 5 >= j.getMaximum()[x1-2] && x1 > 5  && x1 < 9){
                    return i;
                }
                if ( j.getBonzes()[1][0] == x1 && j.getBonzes()[1][1] + 5 >= j.getMaximum()[x1-2] && x1 > 5  && x1 < 9){
                    return i;
                }
                if ( j.getBonzes()[2][0] == x1 && j.getBonzes()[2][1] + 5 >= j.getMaximum()[x1-2] && x1 > 5  && x1 < 9){
                    return i;
                }
            }
            if ( x2 == 0 ) {
                if ( j.getBonzes()[0][0] == x1 && j.getBonzes()[0][1] + 4 >= j.getMaximum()[x1-2] && x1 > 5  && x1 < 9){
                    return i;
                }
                if ( j.getBonzes()[1][0] == x1 && j.getBonzes()[1][1] + 4 >= j.getMaximum()[x1-2] && x1 > 5  && x1 < 9){
                    return i;
                }
                if ( j.getBonzes()[2][0] == x1 && j.getBonzes()[2][1] + 4 >= j.getMaximum()[x1-2] && x1 > 5  && x1 < 9){
                    return i;
                }
            }
            if ( x2 != 0 ){
                if ( j.getBonzes()[0][0] == x1 && j.getBonzes()[0][1] + 4 >= j.getMaximum()[x1-2] && x1 > 5  && x1 < 9){
                    return i;
                }
                if ( j.getBonzes()[1][0] == x1 && j.getBonzes()[0][1] + 4 >= j.getMaximum()[x1-2] && x1 > 5  && x1 < 9){
                    return i;
                }
                if ( j.getBonzes()[2][0] == x1 && j.getBonzes()[0][1] + 4 >= j.getMaximum()[x1-2] && x1 > 5  && x1 < 9){
                    return i;
                }
                if ( j.getBonzes()[0][0] == x2 && j.getBonzes()[0][1] + 4 >= j.getMaximum()[x2-2] && x2 > 5  && x2 < 9){
                    return i;
                }
                if ( j.getBonzes()[1][0] == x2 && j.getBonzes()[0][1] + 4 >= j.getMaximum()[x2-2] && x2 > 5  && x2 < 9){
                    return i;
                }
                if ( j.getBonzes()[2][0] == x2 && j.getBonzes()[0][1] + 4 >= j.getMaximum()[x2-2] && x2 > 5  && x2 < 9){
                    return i;
                }
            }
        }
        return -1;
        } 

    public int U_0_3(Jeu j){
    for ( int i=0; i<j.getNbChoix(); i++){
    if( j.getLesChoix()[i][0] == j.getLesChoix()[i][1] ){
        int x = j.getLesChoix()[i][0];
        if ( j.getLesChoix()[i][0] > 5 && j.getLesChoix()[i][0] < 9 ){
        if ( j.avancementJoueurEnCours()[x-2] + 5 >= j.getMaximum()[x-2]){
            return i;    }    }     }
    int x1 = j.getLesChoix()[i][0];
    int x2 = j.getLesChoix()[i][1];

    if ( j.avancementJoueurEnCours()[x1-2] + 4 >= j.getMaximum()[x1-2] ){
        if ( j.getLesChoix()[i][0] > 5 && j.getLesChoix()[i][0] < 9 ){
        return i;   }   }
    if ( x2 != 0){
    if ( j.avancementJoueurEnCours()[x2-2] + 4 >= j.getMaximum()[x2-2] ){
        if ( j.getLesChoix()[i][1] > 5 && j.getLesChoix()[i][1] < 9 ){
        return i;   }   }   }

            }    return -1;    }

    public int U_0_3_T(Jeu j){
        for ( int i=0; i<j.getNbChoix(); i++){
        if( j.getLesChoix()[i][0] == j.getLesChoix()[i][1] ){
            int x = j.getLesChoix()[i][0];
            if ( j.getLesChoix()[i][0] > 5 && j.getLesChoix()[i][0] < 9 ){
            if ( j.avancementAutreJoueur()[x-2] + 5 >= j.getMaximum()[x-2]){
                return i;    }    }     }
        int x1 = j.getLesChoix()[i][0];
        int x2 = j.getLesChoix()[i][1];
    
        if ( j.avancementAutreJoueur()[x1-2] + 4 >= j.getMaximum()[x1-2] ){
            if ( j.getLesChoix()[i][0] > 5 && j.getLesChoix()[i][0] < 9 ){
            return i;   }   }
        if ( x2 != 0){
        if ( j.avancementAutreJoueur()[x2-2] + 4 >= j.getMaximum()[x2-2] ){
            if ( j.getLesChoix()[i][1] > 5 && j.getLesChoix()[i][1] < 9 ){
            return i;   }   }   }
    
                }    return -1;    } 


    public int U_1_0(Jeu j){
        for ( int i =0 ; i<j.getNbChoix() ; i++){


        int x1 = j.getLesChoix()[i][0];
        int x2 = j.getLesChoix()[i][1];
            if ( x1 == x2 &&  x1 > 5 && x1 < 9){
                return i;
            }
        }
        return -1;
    }

    public int U_1_1(Jeu j){
        for ( int i =0 ; i<j.getNbChoix() ; i++){


        int x1 = j.getLesChoix()[i][0];
        int x2 = j.getLesChoix()[i][1];
            if ( x2 != 0 &&  x1 > 5 && x1 < 9 && ( x2==2 || x2==12 ) ){
                return i;
            }
            if ( x2 != 0 &&  x2 > 5 && x2 < 9 && ( x1==2 || x1==12 ) ){
                return i;
            }
        }
        return -1;
    }

    public int U_1_2(Jeu j){
        int max =0;
        int num = -1;
        
        for ( int i =0 ; i<j.getNbChoix() ; i++){
            int x1 = j.getLesChoix()[i][0];
            int x2 = j.getLesChoix()[i][1];
        
           
            if ( x1 == x2 && x1 > max ){
                max = x1;
                num = i;
            }
    
            
    }
    return num;
}

    public int U_1_3_Somme(Jeu j, int num_choix){
        int distance1 = j.getLesChoix()[num_choix][0] - 7;
        int distance2 = j.getLesChoix()[num_choix][1] - 7;

        if ( distance1 < 0){ distance1 = -distance1;}
        if ( distance2 < 0){ distance2 = -distance2;}

        int moyenne = (distance1 + distance2) / 2;
        return moyenne;
    }
    public int U_1_3_Somme2(Jeu j, int num_choix){
        int distance1 = j.getLesChoix()[num_choix][0] - 7;

        if ( distance1 < 0){ distance1 = -distance1;}

        int moyenne = distance1;
        return moyenne;
    }

    public int U_1_3(Jeu j){
        int min=100;
        int num = -1;
       
        for ( int i =0 ; i< j.getNbChoix() ; i++){
            int x2 = j.getLesChoix()[i][1];
            if ( U_1_3_Somme(j, i) < min && x2 != 0){
                min =  U_1_3_Somme(j, i);
                num = i;
            }
            if ( x2 == 0 && U_1_3_Somme2(j, i) < min ){
               min =  U_1_3_Somme2(j, i);
                num = i;
            }
        }

    return num;  }

    public float U_3_0_Avancement(Jeu j, int num_choix){
        int x1 = j.getLesChoix()[num_choix][0];
        int x2 = j.getLesChoix()[num_choix][1];

        if ( x1 == j.getBonzes()[0][0] && x1 == x2){
            return ( j.getBonzes()[0][1] / j.getMaximum()[x1-2]);
        }
        if ( x1 == j.getBonzes()[1][0] && x1 == x2){
            return ( j.getBonzes()[1][1] / j.getMaximum()[x1-2]);
        }
        if ( x1 == j.getBonzes()[2][0] && x1 == x2){
            return ( j.getBonzes()[2][1] / j.getMaximum()[x1-2]);
        }
        if ((x1 == j.getBonzes()[0][0] && x2 == j.getBonzes()[1][0] )||( x2 == j.getBonzes()[0][0] && x1 == j.getBonzes()[1][0]     )  ){
        int y1  = j.getBonzes()[0][1] / j.getMaximum()[j.getBonzes()[0][0]-2];
        int y2  = j.getBonzes()[1][1] / j.getMaximum()[j.getBonzes()[1][0]-2];
        return  (y1 + y2) /2;    }

        if (( x1 == j.getBonzes()[0][0] && x2 == j.getBonzes()[2][0] )||(   x2 == j.getBonzes()[0][0] && x1 == j.getBonzes()[2][0]     )  ){
            int y1  = j.getBonzes()[0][1] / j.getMaximum()[j.getBonzes()[0][0]-2];
            int y2  = j.getBonzes()[2][1] / j.getMaximum()[j.getBonzes()[2][0]-2];
            return  (y1 + y2) /2;    }

        if (( x1 == j.getBonzes()[1][0] && x2 == j.getBonzes()[2][0] )||(   x2 == j.getBonzes()[1][0] && x1 == j.getBonzes()[2][0]     )  ){
            int y1  = j.getBonzes()[1][1] / j.getMaximum()[j.getBonzes()[1][0]-2];
            int y2  = j.getBonzes()[2][1] / j.getMaximum()[j.getBonzes()[2][0]-2];
            return  (y1 + y2) /2;    }
        return -1;
    }

    public int U_3_0(Jeu j){
        float max =0;
        int num = -1;
        for ( int i =0 ; i< j.getNbChoix(); i++){
            if ( j.getLesChoix()[i][1] != 0 ){
                if ( U_3_0_Avancement(j, i) > max){
                    max = U_3_0_Avancement(j, i);
                    num = i;    }    }    }
        return num;    }

    public int U_3_1_Avancement ( Jeu j, int num_choix){
        int x1 = j.getLesChoix()[num_choix][0];
        if ( x1 == j.getBonzes()[0][0]){
            return j.getBonzes()[0][1] / j.getMaximum()[j.getBonzes()[0][0]-2];
        }
        if ( x1 == j.getBonzes()[1][0]){
            return j.getBonzes()[1][1] / j.getMaximum()[j.getBonzes()[1][0]-2];
        }
        if ( x1 == j.getBonzes()[2][0]){
            return j.getBonzes()[2][1] / j.getMaximum()[j.getBonzes()[2][0]-2];
        }
        return -1;
        
    }
    
    public int U_3_1(Jeu j){
        float max =0;
        int num = -1;
        for ( int i =0 ; i< j.getNbChoix(); i++){
                if ( U_3_1_Avancement(j, i) > max){
                    max = U_3_1_Avancement(j, i);
                    num = i;    }    }    
        return num;    }


    public int U_Final(Jeu j){ 
        if ( U_0_0_Bis(j) != -1 ){   return U_0_0_Bis(j);   }
        if ( U_0_0(j) != -1 ){   return U_0_0(j);   }

        if ( U_0_1_Bis(j) != -1 ){   return U_0_1_Bis(j);   }
        if ( U_0_1(j) != -1 ){   return U_0_1(j);   }
        if ( U_0_3_T(j) != -1 ){   return U_0_3_T(j);   }

        if ( U_0_2_Bis(j) != -1 ){   return U_0_2_Bis(j);   }
        if ( U_0_2(j) != -1 ){   return U_0_2(j);   }
    
        if ( U_0_3_Bis(j) != -1 ){   return U_0_3_Bis(j);   }
        if ( U_0_3(j) != -1 ){   return U_0_3(j);   }

        
        if ( j.getBonzesRestants() > 0 ){
            if ( U_1_0(j) != -1 ) {    return U_1_0(j);     }
            if ( U_1_1(j) != -1 ) {    return U_1_1(j);     }
            if ( U_1_2(j) != -1 ) {    return U_1_2(j);     }
          if (  U_1_3(j) != -1 ) {    return U_1_3(j);     }
        }
        else { 
            if ( U_3_0(j) != -1 ){ return U_3_0(j); }
            if ( U_3_1(j) != -1 ){ return U_3_1(j); }
        } 
        return 0;
        }

    /**
     * @param j le jeu
     * @return toujours le 1er choix
     */
    public int choix(Jeu j){
        if ( j.getBonzesRestants() == 3 ){
            COMPTEUR = 0;
            BONZE_RESTANT = 0;
            MAXIMUM_COMPTEUR = 38.5;
        }
        S_0_maj_D(j, U_Final(j));
                return U_Final(j);
    }

    public boolean stop(Jeu j){
           S_0_maj_Feature(j);
           maj_MAXIMUM_COMPTEUR(j);   
          return S(j);
       }
     
       public String getName(){
           return "MATHIS HADINGER";
       }
   
   
   
       /// Fonction Stop ///
       public int S1_0_total(Jeu j){
           int total =0;
           for ( int i=0 ;i<11;i++){
               if ( j.getBloque()[i] == true){
                   total = total + 1;
               }
           }
           total = total + j.scoreJoueurEnCours();
           return total;    }
   
       public int S1_0_encours(Jeu j){
           int total = 0;
       
           for ( int i=0; i<11; i++){
               if ( j.getBloque()[i] == true ){
                   total = total +1;
           }
           }
           return total;
       }
   
       public boolean S(Jeu j){
           if ( S1_0_total(j) >= 3 ){  return true;}
           if ( S1_0_encours(j) > 0 && j.getBonzesRestants()==0 ){ return true; }
           if ( S1_0_encours(j) > 0 && j.getBonzesRestants()>0 ){ return false; }
           if ( j.getBonzesRestants() > 0 ){ return false;     }
           if ( j.getBonzesRestants() == 0 ){ 
           
           if ( COMPTEUR < MAXIMUM_COMPTEUR ){
               return false;
           }
           else {
               return true;
           }
           }
   
       
           return true;
       }

       
      public void S_0_maj_D(Jeu j, int num_choix){
        int [] si_premier = {0,0,12,10,8,6,4,2,4,6,8,10,12};
       int [] si_avancement = {0,0,6,5,4,3,2,1,2,3,4,5,6};

        int x1 = j.getLesChoix()[num_choix][0];
        int x2 = j.getLesChoix()[num_choix][1];
        // COMPTEUR PRINCIPAL
        if ( x2 == 0){
                if ( j.getBonzes()[0][0]==x1 || j.getBonzes()[1][0] == x1 || j.getBonzes()[2][0] == x1 ){
                    COMPTEUR = COMPTEUR + si_avancement[x1];
                }
                else {
                    COMPTEUR = COMPTEUR + si_premier[x1];
                }
        }
        else {
            if ( x1 == x2 ){
                if ( j.getBonzes()[0][0]==x1 || j.getBonzes()[1][0] == x1 || j.getBonzes()[2][0] == x1 ){
                    COMPTEUR = COMPTEUR + si_avancement[x1] + si_avancement[x2];
                    }
                else {
                    COMPTEUR = COMPTEUR + si_premier[x1] + si_premier[x2];
                    }
                }
            else{
                if ( j.getBonzes()[0][0]==x1 || j.getBonzes()[1][0] == x1 || j.getBonzes()[2][0] == x1){
                    COMPTEUR = COMPTEUR + si_avancement[x1];
                }
                else {
                COMPTEUR = COMPTEUR + si_premier[x1];
                }
                if ( j.getBonzes()[0][0]==x2 || j.getBonzes()[1][0] == x2 || j.getBonzes()[2][0] == x2){
                    COMPTEUR = COMPTEUR + si_avancement[x2];
                }
                else {
                COMPTEUR = COMPTEUR + si_premier[x2];
                }
                }
            }
        }
            
        // Autre feature : 
        public void S_0_maj_Feature(Jeu j){
            if ( j.getBonzesRestants() == 0 && BONZE_RESTANT == 0){
                if ( j.getBonzes()[0][0] % 2 == 0 && j.getBonzes()[1][0] %2 == 0 && j.getBonzes()[2][0] % 2 == 0){ //Paire 
                    COMPTEUR = COMPTEUR -2;
                }
                if ( j.getBonzes()[0][0] % 2 != 0 && j.getBonzes()[1][0] %2 != 0 && j.getBonzes()[2][0] % 2 != 0){ //ImPaire 
                    COMPTEUR = COMPTEUR +3;
                }
                if ( j.getBonzes()[0][0] < 8 && j.getBonzes()[1][0] < 8 && j.getBonzes()[2][0] < 8 ){
                    COMPTEUR = COMPTEUR + 4;
                }
                if ( j.getBonzes()[0][0] > 6 && j.getBonzes()[1][0] > 6 && j.getBonzes()[2][0] > 6 ){
                    COMPTEUR = COMPTEUR + 4;
                }
                BONZE_RESTANT = 1;
            }
        }

      // Fonction maj maximum compteur
      public void maj_MAXIMUM_COMPTEUR(Jeu j){
        if ( S1_0_total(j) == 2 && j.scoreAutreJoueur() == 0 ){
            MAXIMUM_COMPTEUR = 30;
        }
        if ( S1_0_total(j) == 1 && j.scoreAutreJoueur() == 0 ){
            MAXIMUM_COMPTEUR = 35;
        }
        if ( S1_0_total(j) == 0 && j.scoreAutreJoueur() == 0 ){
            MAXIMUM_COMPTEUR = 38;
        }
        if ( S1_0_total(j) == 2 && j.scoreAutreJoueur() == 1 ){
            MAXIMUM_COMPTEUR = 35;
        }
        if ( S1_0_total(j) == 1 && j.scoreAutreJoueur() == 1 ){
            MAXIMUM_COMPTEUR = 35;
        }
        if ( S1_0_total(j) == 0 && j.scoreAutreJoueur() == 1 ){
            MAXIMUM_COMPTEUR = 38;
        }
        if ( S1_0_total(j) == 2 && j.scoreAutreJoueur() == 2 ){
            MAXIMUM_COMPTEUR = 60;
        }
        if ( S1_0_total(j) == 1 && j.scoreAutreJoueur() == 2 ){
            MAXIMUM_COMPTEUR = 55;
        }
        if ( S1_0_total(j) == 0 && j.scoreAutreJoueur() == 2 ){
            MAXIMUM_COMPTEUR = 50;
        }           
    }
}

