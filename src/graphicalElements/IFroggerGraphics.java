package graphicalElements;

import gameCommons.Game;
import gameCommons.IFrog;

public interface IFroggerGraphics {
	
	/**
	 * Ajoute l'�l�ment aux �l�ments � afficher
	 * @param e
	 */
    public void add(Element e);
    
    /**
     * Enl�ve tous les �l�ments actuellement affich�s
     */
    public void clear();
    
    /***
     * Actualise l'affichage
     */
    public void repaint();
    
    /**
     * Lie la grenouille � l'environneemnt graphique
     * @param frog
     */
    public void setFrog(IFrog frog);

    /**
     * Lance l'écran de fin de partie
     * @param message
     * @param clk
     */
    public void endGameScreen(String message, String clk);

    /**
     * Lance l'écran de fin de partie
     * @param message
     * @param sc
     * @param highSE
     * @param clk
     */
    public void endGameScreen(String message, int sc, int highSE, String clk);
}
