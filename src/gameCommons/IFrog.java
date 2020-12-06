package gameCommons;

import util.Case;
import util.Direction;

public interface IFrog {
	
	/**
	 * Donne la position actuelle de la grenouille
	 * @return case
	 */
	Case getPosition();

	/**
	 * Setter
	 * @param ord ordonné à attribuer
	 */
	void setOrd(int ord);

	/**
	 * D�place la grenouille dans la direction donn�e
	 * Si FrogInf : Gestion des variables de déplacement de la grilles (movedUp & movedDown)
	 * @param key direction
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
	 * Reset les variables de déplacement vertical (movedUp & movedDown)
	 */
	void resetBools();
}
