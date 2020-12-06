package environment;

import java.awt.*;


import gameCommons.Game;
import graphicalElements.Element;
import graphicalElements.FroggerGraphic;
import util.Case;

public class Car implements IMovableElement {
	protected Game game;
	protected Case leftPosition;
	private boolean leftToRight;
	protected int length;

	public Car(Game game, Case leftPosition, boolean leftToRight) {
		this.game = game;
		this.leftPosition = leftPosition;
		this.leftToRight = leftToRight;
		this.length = game.randomGen.nextInt(2) + 2;
	}

	@Override
	public boolean isGoodSurprise() {
		return false;
	}

	@Override
	public boolean isTrap(){
		return false;
	}

	@Override
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

	@Override
	public boolean isElementOnThisCase(Case c){
		if (c.ord != this.leftPosition.ord) {
			return false;
		} else {
			return c.absc >= this.leftPosition.absc && c.absc < this.leftPosition.absc + this.length;
		}
	}

	@Override
	public boolean isElementInLane(){
		return this.leftPosition.absc + this.length > 0 && this.leftPosition.absc < this.game.width;
	}

	@Override
	public void slide(boolean downwards) {
		if (downwards) {
			this.leftPosition = new Case(this.leftPosition.absc, this.leftPosition.ord - 1);
		} else {
			this.leftPosition = new Case(this.leftPosition.absc, this.leftPosition.ord + 1);
		}
	}

	/* Fourni : addToGraphics() permettant d'ajouter un element graphique correspondant a la voiture*/
	public void addToGraphics() {
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
