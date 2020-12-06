package gameCommons;

import environment.Environment;
import environment.EnvironmentInf;
import frog.Frog;
import frog.FrogInf;
import graphicalElements.FroggerGraphic;
import graphicalElements.IFroggerGraphics;
import graphicalElements.StartScreenGUI;

import javax.swing.*;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {

		//Caractéristiques fixes du jeu
		int tempo = 100;
		int minSpeedInTimerLoops = 3;
		long clockStart = System.nanoTime();

		//Fenêtre du choix du mode de jeu
		StartScreenGUI startScreenGUI = new StartScreenGUI();
		while (startScreenGUI.startScreen){
			try {
				Thread.sleep(200);
			} catch(InterruptedException ignored) {}
		}
		//Caractéristiques variables du jeu
		int width = startScreenGUI.gameWidth;
		int height = startScreenGUI.gameHeight;
		int pixelByCase = startScreenGUI.pixelByCase;
		double defaultDensity = startScreenGUI.density;

		//Création de l' interface graphique
		IFroggerGraphics graphic = new FroggerGraphic(width, height, pixelByCase);
		//Création de la partie et choix du mode de jeu
		Game game = new Game(graphic, width, height, minSpeedInTimerLoops, defaultDensity, clockStart);
		game.gamemodeInf = startScreenGUI.gamemodeInf;


		//Création de la grenouille et de l' environement
		IFrog frog;
		IEnvironment env;
		if (game.gamemodeInf) {
			frog = new FrogInf(game);
			env = new EnvironmentInf(game);
		} else {
			frog = new Frog(game);
			env = new Environment(game);
		}


		//Liaison de la grenouille et de l' environement
		game.setFrog(frog);
		graphic.setFrog(frog);
		game.setEnvironment(env);
				
		//Boucle principale : l' environnement s' actualise tous les tempo millisecondes
		Timer timer = new Timer(tempo, e -> {
			if (!game.isGameFinished) {
				try {
					game.update(game.gamemodeInf);
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
				graphic.repaint();
			}
		});
		timer.setInitialDelay(0);
		timer.start();
	}
}
