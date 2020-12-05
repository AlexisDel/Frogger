# Etape n°3 du projet Frogger

#### Statut : :heavy_check_mark:

#### Classes et méthodes ajoutées :
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
6. StartScreen    

#### Modifications / Ajouts :
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
6. StartScreen
   * Choix du mode de jeu via une interface graphique (fenetre indépendantes)
   
#### TODO : 
   
   