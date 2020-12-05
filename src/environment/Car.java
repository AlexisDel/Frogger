package environment;

import java.awt.*;


import gameCommons.Game;
import graphicalElements.Element;
import graphicalElements.FroggerGraphic;
import util.Case;

public class Car {
	private Game game;
	protected Case leftPosition;
	private boolean leftToRight;
	private int length;
	private final Color colorLtR = Color.BLACK;
	private final Color colorRtL = Color.BLUE;

	public Car(Game game, Case leftPosition, boolean leftToRight) {
		this.game = game;
		this.leftPosition = leftPosition;
		this.leftToRight = leftToRight;
		this.length = game.randomGen.nextInt(2) + 2;
	}

	/**
	 * Si move est "true", fait avancer la voiture. Affiche la voiture dans tout les cas
	 * @param move
	 */
	public void move(boolean move){
		if (move) {
			if (this.leftToRight) {
				this.leftPosition = new Case(this.leftPosition.absc + 1, this.leftPosition.ord);
			} else {
				this.leftPosition = new Case(this.leftPosition.absc - 1, this.leftPosition.ord);
			}
		}
		this.addToGraphics();
	}

	/**
	 * Retrun true si il y a une voiture sur la case c, false sinon.
	 * @param c
	 * @return boolean
	 */
	public boolean isCarOnThisCase(Case c){
		if (c.ord != this.leftPosition.ord) {
			return false;
		} else {
			return c.absc >= this.leftPosition.absc && c.absc < this.leftPosition.absc + this.length;
		}
	}

	/**
	 * Retrun true si la voiture est dans la grille du jeu, false sinon
	 * @return boolean
	 */
	public boolean isCarInLane(){
		return this.leftPosition.absc + this.length > 0 && this.leftPosition.absc < this.game.width;
	}

	/**
	 * Fait descendre ou monter les voitures d'une ligne en fonction de "downwards" 
	 * @param downwards
	 */
	public void slide(boolean downwards) {
		if (downwards) {
			this.leftPosition = new Case(this.leftPosition.absc, this.leftPosition.ord - 1);
		} else {
			this.leftPosition = new Case(this.leftPosition.absc, this.leftPosition.ord + 1);
		}
	}

	/* Fourni : addToGraphics() permettant d'ajouter un element graphique correspondant a la voiture*/
	private void addToGraphics() {
		Image carImage = null;

		if(length == 2){
			if (leftToRight){
				carImage = FroggerGraphic.carLtoRImage;
			} else {
				carImage = FroggerGraphic.carRtoLImage;
			}
		}
		else if (length == 3){
			if (leftToRight){
				carImage = FroggerGraphic.truckLtoRImage;
			} else {
				carImage = FroggerGraphic.truckRtoLImage;
			}
		}

		game.getGraphic().add(new Element(leftPosition.absc, leftPosition.ord, carImage));
	}
}
