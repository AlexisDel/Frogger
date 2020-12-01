package frog;

import gameCommons.Game;
import util.Direction;

public class FrogInf extends Frog{

    private boolean movedUp;
    private boolean movedDown;

    public FrogInf(Game game) {
        super(game);
    }

    @Override
    public void move(Direction key) {
        lastDirection = key;
        switch (key){
            case up: movedUp = true;
                break;
            case down: movedDown = true;
                break;
            case left:
                if (absc == 0){
                    break;
                }
                absc --;
                break;
            case right:
                if (absc == game.width-1){
                    break;
                }
                absc ++;
                break;
        }
    }

    @Override
    public void resetBools() {
        movedDown = false;
        movedUp = false;
    }

    @Override
    public boolean isMovedUp() {
        return movedUp;
    }

    @Override
    public boolean isMovedDown() {
        return movedDown;
    }
}
