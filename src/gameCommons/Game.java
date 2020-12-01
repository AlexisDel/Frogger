package gameCommons;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import graphicalElements.Element;
import graphicalElements.IFroggerGraphics;

public class Game {

	public final Random randomGen = new Random();

	// Caracteristique de la partie
	public final int width;
	public final int height;
	public final int minSpeedInTimerLoops;
	public final double defaultDensity;

	//Gestion du score
	public int maxScore;
	public int score;
	public int highestScoreEver;

	// Lien aux objets utilis�s
	private IEnvironment environment;
	private IFrog frog;
	private IFroggerGraphics graphic;

	/**
	 * 
	 * @param graphic
	 *            l'interface graphique
	 * @param width
	 *            largeur en cases
	 * @param height
	 *            hauteur en cases
	 * @param minSpeedInTimerLoop
	 *            Vitesse minimale, en nombre de tour de timer avant d�placement
	 * @param defaultDensity
	 *            densite de voiture utilisee par defaut pour les routes
	 */
	public Game(IFroggerGraphics graphic, int width, int height, int minSpeedInTimerLoop, double defaultDensity) {
		super();
		this.graphic = graphic;
		this.width = width;
		this.height = height;
		this.minSpeedInTimerLoops = minSpeedInTimerLoop;
		this.defaultDensity = defaultDensity;

		this.maxScore = 0;
		this.score = 0;
		this.highestScoreEver = 0;
	}

	/**
	 * Lie l'objet frogInf à la partie
	 * @param frog
	 */
	public void setFrog(IFrog frog) {
		this.frog = frog;
	}

	/**
	 * Lie l'objet environment à la partie
	 * @param environment
	 */
	public void setEnvironment(IEnvironment environment) {
		this.environment = environment;
	}

	/**
	 * 
	 * @return l'interface graphique
	 */
	public IFroggerGraphics getGraphic() {
		return graphic;
	}

	/**
	 * Teste si la partie est perdue (mode de jeu infini) et lance un �cran de fin appropri� si tel est le cas
	 * @return true si le partie est perdue
	 */
	public boolean testLoseInf() {
		if(!this.environment.isSafe(this.frog.getPosition())){
			this.graphic.endGameScreen("YOU LOST",this.score,this.highestScoreEver);
		}
		return false;
	}

	/**
	 * Teste si la partie est perdue (mode de jeu fini) et lance un écran de fin approprié si tel est le cas
	 * @return true si le partie est perdue
	 */
	public boolean testLose() {
		if (!environment.isSafe(frog.getPosition())){
			graphic.endGameScreen("Lose");
			return true;
		}
		return false;
	}

	/**
	 * Teste si la partie est gagnée (mode de jeu fini) et lance un �cran de fin appropri� si tel est le cas
	 * @return true si la partie est gagn�e
	 */
	public boolean testWin() {
		if (environment.isWinningPosition(frog.getPosition())){
			graphic.endGameScreen("Win");
			return true;
		}
		return false;
	}

	/**
	 * getter
	 * @return frog
	 */
	public IFrog getFrog() {
		return frog;
	}

	/**
	 * Actualise l'environnement, affiche la grenouille et gère le score
	 * partie.
	 */
	public void update(boolean gamemodeInf) {
		graphic.clear();
		environment.update();
		this.graphic.add(new Element(frog.getPosition(), Color.GREEN));
		if(gamemodeInf) {
			testLoseInf();
		} else {
			testLose();
			testWin();
		}
	}
}
