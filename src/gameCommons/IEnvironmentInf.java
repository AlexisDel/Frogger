package gameCommons;

public interface IEnvironmentInf extends IEnvironment {

    /**
     * Fait monter ou descendre toutes les lignes ainsi que leurs voitures
     * @param downwards
     */
    void slideRoad(boolean downwards);

    /**
     * Ajoute une ligne tout en haut de la grille de jeu
     */
    void addTopLane();

    /**
     * Effectue une �tape d'actualisation de l'environnement
     * @param slideRoadDownwards
     */
    void update(boolean slideRoadDownwards);
}
