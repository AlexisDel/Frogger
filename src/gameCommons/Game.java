package gameCommons;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import graphicalElements.Element;
import graphicalElements.FroggerGraphic;
import graphicalElements.IFroggerGraphics;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Game {

	public final Random randomGen = new Random();

	// Caracteristique de la partie
	public final int width;
	public final int height;
	public final int minSpeedInTimerLoops;
	public final double defaultDensity;
	public boolean isGameFinished;
	public boolean gamemodeInf;

	//Gestion du score
	public int maxScore;
	public int score;
	public int highestScoreEver;

	//Clock
	private long clockStart;

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
	public Game(IFroggerGraphics graphic, int width, int height, int minSpeedInTimerLoop, double defaultDensity, long clockStart) {
		super();
		this.graphic = graphic;
		this.width = width;
		this.height = height;
		this.minSpeedInTimerLoops = minSpeedInTimerLoop;
		this.defaultDensity = defaultDensity;
		this.clockStart = clockStart;
		this.isGameFinished = false;

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

	private String getElapsedTimeHoursMinutesSecondsFromStart(){
		String format = String.format("%%0%dd", 2);
		long elapsedTime = (System.nanoTime() - clockStart) / 1000000000;
		String seconds = String.format(format, elapsedTime % 60);
		String minutes = String.format(format, (elapsedTime % 3600) / 60);
		String hours = String.format(format, elapsedTime / 3600);
		String time =  hours + ":" + minutes + ":" + seconds;
		return time;
	}

	/**
	 * Teste si la partie est perdue (mode de jeu infini) et lance un �cran de fin appropri� si tel est le cas
	 * @return true si le partie est perdue
	 */
	public boolean testLoseInf() {
		if(!this.environment.isSafe(this.frog.getPosition())){
			this.graphic.endGameScreen("YOU LOST",this.score,this.highestScoreEver, getElapsedTimeHoursMinutesSecondsFromStart());
			isGameFinished = true;
			return true;
		}
		return false;
	}

	/**
	 * Teste si la partie est perdue (mode de jeu fini) et lance un écran de fin approprié si tel est le cas
	 * @return true si le partie est perdue
	 */
	public boolean testLose() {
		if (!environment.isSafe(frog.getPosition())){
			graphic.endGameScreen("Lose",getElapsedTimeHoursMinutesSecondsFromStart());
			isGameFinished = true;
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
			graphic.endGameScreen("Win",getElapsedTimeHoursMinutesSecondsFromStart());
			isGameFinished = true;
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
	public void update(boolean gamemodeInf) throws IOException {
		graphic.clear();
		environment.update();
		this.graphic.add(new Element(frog.getPosition(), FroggerGraphic.frogImage));
		if(gamemodeInf) {
			testLoseInf();
		} else {
			testLose();
			testWin();
		}
	}
}
