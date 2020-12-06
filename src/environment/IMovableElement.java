package environment;

import util.Case;

public interface IMovableElement {

    /**
     * Si move est "true", fait avancer la voiture. Affiche la voiture dans tout les cas
     * @param move
     */
    void move(boolean move);

    /**
     * Retrun true si il y a un element sur la case c, false sinon.
     * @param c
     * @return boolean
     */
    boolean isElementOnThisCase(Case c);

    /**
     * Retrun true si l'élément est dans la grille du jeu, false sinon
     * @return boolean
     */
    boolean isElementInLane();

    /**
     * Fait descendre ou monter les voitures d'une ligne en fonction de "downwards"
     * @param downwards
     */
    void slide(boolean downwards);

    /**
     * @return true si c'est un bonne surprise (pas un piège), false sinon
     */
    boolean isGoodSurprise();

    /**
     * @return true si c'est un piège, false sinon
     */
    boolean isTrap();

    void addToGraphics();

}
