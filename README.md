# Projet IPO S3 "Frogger"

![frogger](https://user-images.githubusercontent.com/22059248/121902862-015a1980-cd28-11eb-8355-b1e14de8857c.png)

#### Etudiants :

* Jacobo Ruiz Ocampo
* Alexis Delplace

#### Implémentations :

###### Base :

* Partie 1
* Partie 2 (mode de jeu fini)
* Partie 3 (mode de jeu infini)

###### Ajouts :

* Score : affichage à la fin de la partie & sauvegarde du meilleur score
* Timer : affichage à la fin de la partie & sauvegarde du meilleur temps
* Surprise :
   * 1/3 : Piège → Partie perdu
   * 2/3 : Bonus → Fait avancer la grenouille d'au moins 3 cases (jusqu'à la prochaine case sûre)
* Menu Principal : Choix du mode de jeu, de la difficulté, de la taille du terrain, et de la taille de la fenêtre (via pixelByCase)
* Mode de jeu : 2 joueurs
* Meilleur rendu graphique :
   * Chaque élément a un rendu graphique particulié et il est mis à l'echelle en fonction de la taille de la fenêtre
   * Création du background en fonction des paramètres rentrés dans le menu principal (taille du terrain et de la fenêtre)
