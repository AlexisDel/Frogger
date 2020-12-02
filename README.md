# Etape n°3 du projet Frogger

#### Statut : :heavy_check_mark:

#### Ajouts :
1. Car
   * slide
2. Lane
   * slideLane
3. FrogInf
   * resetBools
   * isMovedUp
   * isMovedDown   
4. EnvironmentInf
   * slideRoad
   * addTopLane
   * saveAndReadScore
5. Game
   * getElapsedTimeHoursMinutesFromStart
    

#### Modifications :
1. Main
   * Choix du mode de jeu via.
   * Fix : Fin de la partie => n'update plus l'environement.
2. Game
   * Gestion de la fin de la partie en fonction du mode de jeu.
   * Ajout d'un Timer
3. FroggerGraphic
   * Ajout de l'affichage du score, du meilleur score, et du meilleur score de tout les temps à la fin de la partie.
   * Affichage du Timer à la fin de la partie.
4. FrogInf
   * Gestion des déplacements de la grille.
5. EnvironmentInf
   * Gestion du score.
   
#### TODO : 
   * Sauvegarder le meilleur temps en cas de win dans un fichier (le même que celui du score)
   