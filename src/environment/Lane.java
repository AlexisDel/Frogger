package environment;

import java.util.ArrayList;

import gameCommons.Game;
import util.Case;

public class Lane {
	private Game game;
	private int ord;
	private int speed;
	private boolean leftToRight;
	private double density;
	private int timer;

	public ArrayList<Car> cars = new ArrayList<>();

	public Lane(Game game, int ord) {
		this.game = game;
		this.ord = ord;
		this.speed = game.randomGen.nextInt(game.minSpeedInTimerLoops) + 1;
		this.leftToRight = game.randomGen.nextBoolean();
		this.density = game.defaultDensity;
		this.timer = 0;

		for (int i = 0; i < game.randomGen.nextInt(game.width); i++){
			this.mayAddCar();
			this.moveCars(true);
		}
	}

	public void update() {
		timer++;
		if (timer <= speed){
			this.moveCars(false);
		} else {
			this.mayAddCar();
			this.moveCars(true);
			this.removeOldCars();
			timer = 0;

		}

	}

	/**
	 * Fait avancer toutes les voitures de la voie et les ajoute à l'interface graphique
	 */
	private void moveCars(boolean move) {
		for (Car car : cars) {
			car.move(move);
		}
	}

	/**
	 * Return true si la case c est safe (si il n'y a pas de voiture sur la case c)
	 * @param c
	 * @return boolean
	 */
	private boolean isSafe(Case c) {
		for (Car car : cars) {
			if (car.isCarOnThisCase(c)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Supprime les voitures qui ne sont plus dans la grille du jeu
	 */
	private void removeOldCars() {
		ArrayList<Car> carsToBeRemoved = new ArrayList<>();
		for (Car car : cars) {
			if (!car.isCarInLane()) {
				carsToBeRemoved.add(car);
			}
		}
		for (Car car : carsToBeRemoved) {
			cars.remove(car);
		}
	}

	/*
	 * Fourni : mayAddCar(), getFirstCase() et getBeforeFirstCase() 
	 */

	/**
	 * Ajoute une voiture au d�but de la voie avec probabilit� �gale � la
	 * densit�, si la premi�re case de la voie est vide
	 */
	private void mayAddCar() {
		if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) {
			if (game.randomGen.nextDouble() < density) {
				cars.add(new Car(game, getBeforeFirstCase(), leftToRight));
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
