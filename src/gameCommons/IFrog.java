package gameCommons;

import util.Case;
import util.Direction;

public interface IFrog {
	
	/**
	 * Donne la position actuelle de la grenouille
	 * @return
	 */
	Case getPosition();
	
	/**
	 * Donne la direction de la grenouille, c'est � dire de son dernier mouvement 
	 * @return
	 */
	Direction getDirection();
	
	/**
	 * D�place la grenouille dans la direction donn�e
	 * Si FrogInf : Gestion des variables de dépélacement de la grilles (movedUp & movedDown)
	 * @param key
	 */
	void move(Direction key);

	/**
	 * getter
	 * @return movedUp
	 */
	boolean isMovedUp();

	/**
	 * getter
	 * @return movedDown
	 */
	boolean isMovedDown();

	/**
	 * Reset les varabiables de déplacement vertical (movedUp & movedDown)
	 */
	void resetBools();
}
