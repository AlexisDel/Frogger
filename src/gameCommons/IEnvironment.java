package gameCommons;

import util.Case;

import java.io.IOException;

public interface IEnvironment {

	/**
	 * Teste si une case est sure, c'est � dire que la grenouille peut s'y poser
	 * sans mourir
	 * 
	 * @param c
	 *            la case � tester
	 * @return vrai s' il n'y a pas danger
	 */
	boolean isSafe(Case c);

	/**
	 * Teste si la case est une case d' arrivée
	 * 
	 * @param c une case
	 * @return vrai si la case est une case de victoire
	 */
	boolean isWinningPosition(Case c);

	/**
	 * Test si la case est une bonne surprise (pas une trap)
	 * @param c la caseà tester
	 * @return true si la case est surprise mais pas un trap
	 */
	boolean onGoodSurprise(Case c);

	/**
	 * Lis le meilleur temps dans le fichier "HighestScore.txt" et le change si il a été battu.
	 * @param clockStart le temps actuel
	 * @return long le meilleur temps
	 * @throws IOException impossible de lire ou d' écrire dans le fichier
	 */
	long bestTime(long clockStart) throws IOException;

	/**
	 * Lis le meilleur score dans le fichier "HighestScore.txt" et le change si il a été battu.
	 * @return int le meilleur score
	 * @throws IOException impossible de lire ou d' écrire dans le fichier
	 */
	int bestScore() throws IOException;

	/**
	 * Fait monter ou descendre toutes les lignes et leurs voitures
	 * @param downwards boolean true si slide vers le bas, false si vers le haut
	 */
	void slideRoad(boolean downwards);

	/**
	 * Effectue une �tape d' actualisation de l' environnement
	 * Si FrogInf : Gestion du score
	 */
	void update() throws IOException;

}
