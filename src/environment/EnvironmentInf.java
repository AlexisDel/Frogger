package environment;

import gameCommons.Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class EnvironmentInf extends Environment {

    public EnvironmentInf(Game game) {
        super(game);
        this.lanes.add(new Lane(game, game.height-1));

    }

    /**
     * Fait monter ou descendre toutes les lignes et leurs voitures
     * @param downwards
     */
    public void slideRoad(boolean downwards){
        for (Lane lane : lanes){
            lane.slideLane(downwards);
        }
    }

    /**
     * Ajoute une ligne tout en haut de la grille
     */
    public void addTopLane(){
        this.lanes.add(new Lane(game,game.height));
    }

    /**
     * Lit le meilleur score dans le fichier "HighestScore.txt" et écrit dedans si le HighestScoreEver a été battu
     */
    public int bestScore() throws IOException {

        File highestScore = new File("HighestScore.txt");

        //Récupération du meilleur score
        Scanner highestScoreReader = new Scanner(highestScore);
        String line = highestScoreReader.nextLine();
        int bestScore = Integer.parseInt(line.split(" ")[0]);

        if (game.score > bestScore){
            FileWriter highestScoreWriter = new FileWriter("HighestScore.txt");
            highestScoreWriter.write(game.score + " " + line.split(" ")[1]);
            highestScoreWriter.close();
            bestScore = game.score;
        }
        return bestScore;
    }

    @Override
    public void update() throws IOException {
        super.update();

        if(game.getFrog().isMovedUp()){
            game.score++;
            if(game.score > game.maxScore){
                game.maxScore = game.score;
                this.addTopLane();
            }
            this.slideRoad(true);
        }
        else if (game.getFrog().isMovedDown() && game.score > 0) {
            game.score--;
            this.slideRoad(false);
        }

        game.getFrog().resetBools();
    }
}
