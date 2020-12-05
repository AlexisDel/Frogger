package environment;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import gameCommons.Game;
import gameCommons.IEnvironment;
import util.Case;

public class Environment implements IEnvironment {

    Game game;
    ArrayList<Lane> lanes;

    public Environment(Game game) {
        this.game = game;
        this.lanes = new ArrayList<>();

        for (int i = 1; i < game.height - 1; i++) {
            this.lanes.add(new Lane(game,i));
        }
    }

    @Override
    public boolean isSafe(Case c) {
        for (Lane lane : lanes) {
            for (Car car : lane.cars) {
                if (car.isCarOnThisCase(c)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean isWinningPosition(Case c) {
        if (c.ord == game.height - 1) {
            return true;
        }
        return false;
    }

    @Override
    public long bestTime(long clockStart) throws IOException {
        File highestScore = new File("HighestScore.txt");

        //Récupération du meilleur temps
        Scanner highestScoreReader = new Scanner(highestScore);
        String line = highestScoreReader.nextLine();
        long bestTime = Long.parseLong(line.split(" ")[1]);
        long actualTime = System.nanoTime() - clockStart;

        //Sauvegarde du meilleur temps
        if (actualTime < bestTime || bestTime == 0){
            FileWriter highestScoreWriter = new FileWriter("HighestScore.txt");
            highestScoreWriter.write(line.split(" ")[0] + " " + actualTime);
            highestScoreWriter.close();
            bestTime = actualTime;
        }
        return bestTime;
    }

    /**
     * DONT USE IT
     */
    @Override
    public int bestScore() throws IOException {
        return 0;
    }

    @Override
    public void update() throws IOException {
        for (Lane lane : lanes) {
            lane.update();
        }
    }

}
