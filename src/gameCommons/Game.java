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
	private int maxScore;
	private int score;
	private int highestScoreEver;

	// Lien aux objets utilis�s
	private IEnvironmentInf environment;
	private IFrogInf frog;
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
	 * Lie l'objet frogInf � la partie
	 *
	 * @param frogInf
	 */
	public void setFrog(IFrogInf frogInf) {
		this.frog = frogInf;
	}

	/**
	 * Lie l'objet environmentInf a la partie
	 *
	 * @param environmentInf
	 */
	public void setEnvironment(IEnvironmentInf environmentInf) {
		this.environment = environmentInf;
	}

	/**
	 * 
	 * @return l'interface graphique
	 */
	public IFroggerGraphics getGraphic() {
		return graphic;
	}

	/**
	 * Teste si la partie est perdue et lance un �cran de fin appropri� si tel
	 * est le cas
	 *
	 * @return true si le partie est perdue
	 */
	public boolean testLose() {
		if(!this.environment.isSafe(this.frog.getPosition())){
			this.graphic.endGameScreen("YOU LOST",this.score,this.highestScoreEver);
		}
		return false;
	}

	/**
	 * Lie le meilleur score dans le fichier "HighestScore.txt" et écrit dedans si le HighestScoreEver a été battu
	 * @param score
	 */
	public void saveAndReadScore(int score) {

		File highestScore = new File("HighestScore.txt");

		//Récupération du meilleur score
		String data = "";
		try {
			if(!highestScore.exists()){
				highestScore.createNewFile();
				FileWriter highestScoreWriter = new FileWriter("HighestScore.txt");
				highestScoreWriter.write("" + 1);
				highestScoreWriter.close();
			}
			Scanner highestScoreReader = new Scanner(highestScore);
			if (highestScoreReader.hasNextLine()) {
				data = highestScoreReader.nextLine();
			}
			highestScoreEver = Integer.parseInt(data);
			highestScoreReader.close();
		} catch (FileNotFoundException e){
			System.out.println("An error occurred.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		//Sauvegarde du meilleur score
		if (score > highestScoreEver) {
			highestScoreEver = score;
			try {
				FileWriter highestScoreWriter = new FileWriter("HighestScore.txt");
				highestScoreWriter.write("" + score);
				highestScoreWriter.close();
			} catch (IOException e){
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
		}
	}

	/**
	 * Actualise l'environnement, affiche la grenouille et gère le score
	 * partie.
	 */
	public void update() {
		graphic.clear();
		environment.update();
		this.graphic.add(new Element(frog.getPosition(), Color.GREEN));

		if(frog.isMovedUp()){
			score++;
			if(score > maxScore){
				maxScore = score;
				environment.addTopLane();
			}
			graphic.clear();
			environment.update(true);
		}

		else if (frog.isMovedDown() && score>0) {
			score--;
			graphic.clear();
			environment.update(false);
		}

		frog.resetBools();
		saveAndReadScore(maxScore);
		testLose();
	}

}
