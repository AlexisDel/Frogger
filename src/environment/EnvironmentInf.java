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
            game.highestScoreEver = Integer.parseInt(data);
            highestScoreReader.close();
        } catch (FileNotFoundException e){
            System.out.println("Error : file not found.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error : can't write in file.");
            e.printStackTrace();
        }

        //Sauvegarde du meilleur score
        if (game.score > game.highestScoreEver) {
            game.highestScoreEver = score;
            try {
                FileWriter highestScoreWriter = new FileWriter("HighestScore.txt");
                highestScoreWriter.write("" + score);
                highestScoreWriter.close();
            } catch (IOException e){
                System.out.println("Error : can't write in file.");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update() {
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

        saveAndReadScore(game.maxScore);
    }
}
