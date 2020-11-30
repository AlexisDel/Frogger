package gameCommons;

public interface IFrogInf extends IFrog {

    /**
     * Reset les variable representant le deplacement de la grille
     */
    void resetBools();

    /**
     * getter pour movedUp
     * @return movedUp
     */
    boolean isMovedUp();

    /**
     * getter pour movedDown
     * @return movedDown
     */
    boolean isMovedDown();
}
