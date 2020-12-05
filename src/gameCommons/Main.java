package gameCommons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Timer;

import environment.Environment;
import environment.EnvironmentInf;
import frog.Frog;
import frog.FrogInf;
import graphicalElements.FroggerGraphic;
import graphicalElements.IFroggerGraphics;
import graphicalElements.StartScreenGUI;

public class Main {

	public static void main(String[] args) throws IOException {

		//Caract�ristiques du jeu
		int tempo = 100;
		int minSpeedInTimerLoops = 3;
		double defaultDensity = 0.1;

		long clockStart = System.nanoTime();

		//Fenêtre du choix du mode de jeu
		StartScreenGUI startScreenGUI = new StartScreenGUI();
		while (StartScreenGUI.startScreen){
			try {
				Thread.sleep(200);
			} catch(InterruptedException e) {}
		}
		int width = startScreenGUI.gameWidth;
		int height = startScreenGUI.gameHeight;
		int pixelByCase = StartScreenGUI.pixelByCase;

		//Cr�ation de l'interface graphique
		IFroggerGraphics graphic = new FroggerGraphic(width, height, pixelByCase);
		//Cr�ation de la partie
		Game game = new Game(graphic, width, height, minSpeedInTimerLoops, defaultDensity, clockStart);
		game.gamemodeInf = StartScreenGUI.gamemodeInf;


		//Création de la grenouille et de l'environement
		IFrog frog;
		IEnvironment env;
		if (game.gamemodeInf) {
			frog = new FrogInf(game);
			env = new EnvironmentInf(game);
		} else {
			frog = new Frog(game);
			env = new Environment(game);
		}


		//Lisaison de la grenouille et de l'environement
		game.setFrog(frog);
		graphic.setFrog(frog);
		game.setEnvironment(env);
				
		//Boucle principale : l'environnement s'acturalise tous les tempo milisecondes
		Timer timer = new Timer(tempo, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!game.isGameFinished) {
					try {
						game.update(game.gamemodeInf);
					} catch (IOException ioException) {
						ioException.printStackTrace();
					}
					graphic.repaint();
				}
			}
		});
		timer.setInitialDelay(0);
		timer.start();
	}
}
