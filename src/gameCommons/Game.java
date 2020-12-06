package gameCommons;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import graphicalElements.Element;
import graphicalElements.FroggerGraphic;
import graphicalElements.IFroggerGraphics;

public class Game {

	public final Random randomGen = new Random();

	//Caractéristique de la partie
	public final int width;
	public final int height;
	public final int minSpeedInTimerLoops;
	public final double defaultDensity;
	public boolean isGameFinished;

	public boolean gamemodeInf;
	public boolean twoPlayers;

	//Gestion du score
	public int maxScore;
	public int score;

	//Clock
	private final long clockStart;

	// Lien aux objets utilis�s
	private IEnvironment environment;
	private IFrog frog;
	private IFrog frog2;
	private final IFroggerGraphics graphic;

	/**
	 * 
	 * @param graphic
	 *            l' interface graphique
	 * @param width
	 *            largeur en cases
	 * @param height
	 *            hauteur en cases
	 * @param minSpeedInTimerLoop
	 *            Vitesse minimale, en nombre de tour de timer avant déplacement
	 * @param defaultDensity
	 *            densité de voiture utilisée par défaut pour les routes
	 */
	public Game(IFroggerGraphics graphic, int width, int height, int minSpeedInTimerLoop, double defaultDensity, long clockStart) throws IOException {
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

		File highestScore = new File("HighestScore.txt");
		if(!highestScore.exists()){
			highestScore.createNewFile();
			FileWriter highestScoreWriter = new FileWriter("HighestScore.txt");
			highestScoreWriter.write(1 + " " + 0);
			highestScoreWriter.close();
		}
	}

	/**
	 * Lie l' objet frog à la partie
	 * @param frog la grenouille
	 */
	public void setFrog(IFrog frog) {
		this.frog = frog;
	}

	/**
	 * Lie l' objet frog à la partie
	 * @param frog la deuxième grenouille
	 */
	public void setFrog2(IFrog frog) {
		this.frog2 = frog;
	}

	/**
	 * Lie l' objet environment à la partie
	 * @param environment l' environement de la partie
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
		return hours + ":" + minutes + ":" + seconds;
	}

	private String getElapsedTimeHoursMinutesSeconds(long t){
		String format = String.format("%%0%dd", 2);
		long elapsedTime = t / 1000000000;
		String seconds = String.format(format, elapsedTime % 60);
		String minutes = String.format(format, (elapsedTime % 3600) / 60);
		String hours = String.format(format, elapsedTime / 3600);
		return hours + ":" + minutes + ":" + seconds;
	}

	/**
	 * Teste si la partie est perdue (mode de jeu infini) et lance un �cran de fin approprié si tel est le cas
	 * @return true si le partie est perdue
	 */
	public boolean testLoseInf() throws IOException {
		if(this.environment.isSafe(this.frog.getPosition())){
			int bestScore = environment.bestScore();
			this.graphic.endGameScreen("YOU LOST",this.score,bestScore, getElapsedTimeHoursMinutesSecondsFromStart());
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
		if (environment.isSafe(frog.getPosition())){
			graphic.endGameScreen("Lose", getElapsedTimeHoursMinutesSecondsFromStart());
			isGameFinished = true;
			return true;
		}
		return false;
	}

	/**
	 * Teste si la partie est gagnée (mode de jeu fini) et lance un �cran de fin approprié si tel est le cas
	 * @return true si la partie est gagnée
	 */
	public boolean testWin() throws IOException {
		if (environment.isWinningPosition(frog.getPosition())){
			long bestTime = environment.bestTime(clockStart);
			graphic.endGameScreen("Win", getElapsedTimeHoursMinutesSeconds(bestTime), getElapsedTimeHoursMinutesSecondsFromStart());
			isGameFinished = true;
			return true;
		}
		return false;
	}

	/**
	 * Teste si la partie est perdue (mode de jeu 2 joueurs) et lance un écran de fin approprié si tel est le cas
	 * @return true si le partie est perdue
	 */
	public boolean testLose2Players() {
		if (environment.isSafe(frog.getPosition())){
			graphic.endGameScreen("Joueur 1 a perdu", getElapsedTimeHoursMinutesSecondsFromStart());
			isGameFinished = true;
			return true;
		}
		else if (environment.isSafe(frog2.getPosition())){
			graphic.endGameScreen("Joueur 2 a perdu", getElapsedTimeHoursMinutesSecondsFromStart());
			isGameFinished = true;
			return true;
		}
		return false;
	}

	/**
	 * Teste si la partie est gagnée (mode de jeu 2 joueurs) et lance un �cran de fin approprié si tel est le cas
	 * @return true si la partie est gagnée
	 */
	public boolean testWin2Players() {
		if (environment.isWinningPosition(frog.getPosition())){
			graphic.endGameScreen("Joueur 1 a gagné", getElapsedTimeHoursMinutesSecondsFromStart());
			isGameFinished = true;
			return true;
		}
		else if (environment.isWinningPosition(frog2.getPosition())) {
			graphic.endGameScreen("Joueur 2 a gagné", getElapsedTimeHoursMinutesSecondsFromStart());
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
	 * Actualise l' environnement, affiche la grenouille et gère le score
	 * partie.
	 */
	public void update(boolean gamemodeInf) throws IOException {
		graphic.clear();
		environment.update();
		this.graphic.add(new Element(frog.getPosition(), FroggerGraphic.frogImage));
		if(gamemodeInf) {
			testLoseInf();
		} else {
			if(twoPlayers){
				this.graphic.add(new Element(frog2.getPosition(), FroggerGraphic.frogImage));
				testLose2Players();
				testWin2Players();
			}
			else {
				testLose();
				testWin();
			}
		}
	}
}
