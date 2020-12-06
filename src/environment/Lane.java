package environment;

import java.util.ArrayList;

import gameCommons.Game;
import util.Case;

public class Lane {
	private final Game game;
	private final int speed;
	private final boolean leftToRight;
	private final double density;
	private final double probaOfSurprise;
	private final double getProbaOfSurpriseisTrap;
	protected int ord;
	private int timer;

	public ArrayList<IMovableElement> movableElements = new ArrayList<>();

	public Lane(Game game, int ord) {
		this.game = game;
		this.ord = ord;
		this.speed = game.randomGen.nextInt(game.minSpeedInTimerLoops) + 1;
		this.leftToRight = game.randomGen.nextBoolean();
		this.density = game.defaultDensity;
		this.probaOfSurprise = 0.2;
		this.getProbaOfSurpriseisTrap = 0.3;
		this.timer = 0;

		for (int i = 0; i < game.randomGen.nextInt(game.width); i++){
			this.mayAddElement();
			this.moveElements(true);
		}
	}

	public void update() {
		timer++;
		if (timer <= speed){
			this.moveElements(false);
		} else {
			this.mayAddElement();
			this.moveElements(true);
			this.removeOldElements();
			timer = 0;

		}

	}

	/**
	 * Fait avancer toutes les voitures de la voie et les ajoute à l'interface graphique
	 */
	private void moveElements(boolean move) {
		for (IMovableElement e : movableElements) {
			e.move(move);
		}
	}

	/**
	 * Return true si la case c est safe (si il n'y a pas de voiture sur la case c)
	 * @param c une case
	 * @return boolean
	 */
	private boolean isOkToAddSomething(Case c) {
		for (IMovableElement e : movableElements) {
			if (e.isElementOnThisCase(c)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Supprime les voitures qui ne sont plus dans la grille du jeu
	 */
	private void removeOldElements() {
		ArrayList<IMovableElement> elementsToBeRemoved = new ArrayList<>();
		for (IMovableElement e : movableElements) {
			if (!e.isElementInLane()) {
				elementsToBeRemoved.add(e);
			}
		}
		for (IMovableElement e : elementsToBeRemoved) {
			movableElements.remove(e);
		}
	}

	/**
	 * Fait descendre ou monter une ligne et ses voitures d'une ligne en fonction de "downwards"
	 * @param downwards boolean true si slide vers le bas, false si vers le haut
	 */
	public void slideLane(boolean downwards) {
		if(downwards) {
			this.ord--;
		} else {
			this.ord++;
		}
		for (IMovableElement e : movableElements){
			e.slide(downwards);
		}
	}

	/**
	 * Ajoute une voiture au d�but de la voie avec probabilit� �gale � la
	 * densit�, si la premi�re case de la voie est vide
	 */
	private void mayAddElement() {
		if (isOkToAddSomething(getFirstCase()) && isOkToAddSomething(getBeforeFirstCase())) {
			if (game.randomGen.nextDouble() < density) {
				if(game.randomGen.nextDouble() < probaOfSurprise){
					if(game.randomGen.nextDouble() < getProbaOfSurpriseisTrap){
						movableElements.add(new Surprise(game, getBeforeFirstCase(), leftToRight, true));
					} else {
						movableElements.add(new Surprise(game, getBeforeFirstCase(), leftToRight, false));
					}
				} else {
					movableElements.add(new Car(game, getBeforeFirstCase(), leftToRight));
				}
			}
		}
	}

	private Case getFirstCase() {
		if (leftToRight) {
			return new Case(0, ord);
		} else
			return new Case(game.width - 1, ord);
	}

	private Case getBeforeFirstCase() {
		if (leftToRight) {
			return new Case(-1, ord);
		} else
			return new Case(game.width, ord);
	}

}
