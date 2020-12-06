package graphicalElements;

import gameCommons.IFrog;

public interface IFroggerGraphics {
	
	/**
	 * Ajoute l'�l�ment aux �l�ments � afficher
	 * @param e un élément graphique
	 */
    void add(Element e);
    
    /**
     * Enl�ve tous les �l�ments actuellement affich�s
     */
    void clear();
    
    /***
     * Actualise l' affichage
     */
    void repaint();
    
    /**
     * Lie la grenouille � l'environneemnt graphique
     * @param frog la grenouille
     */
    void setFrog(IFrog frog);

    /**
     * Lie la deuxième grenouille � l'environneemnt graphique
     * @param frog la deuxième grenouille
     */
    void setFrog2(IFrog frog);

    /**
     * Lance l'écran de fin de partie
     * @param message un message à affiché
     * @param clk le temps actuel
     */
    void endGameScreen(String message, String clk);

    /**
     * Lance l'écran de fin de partie
     * @param message un message à affiché
     * @param bestTime le meilleur temps
     * @param clk le temps de la partie
     */
    void endGameScreen(String message, String bestTime, String clk);

    /**
     * Lance l'écran de fin de partie
     * @param message un message à affiché
     * @param sc le score de la partie
     * @param highSE le meilleur score
     * @param clk le temps de la partie
     */
    void endGameScreen(String message, int sc, int highSE, String clk);
}
