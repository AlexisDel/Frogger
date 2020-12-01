package environment;

import java.util.ArrayList;

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
    public void update() {
        for (Lane lane : lanes) {
            lane.update();
        }
    }

}
