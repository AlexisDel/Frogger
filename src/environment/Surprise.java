package environment;

import gameCommons.Game;
import graphicalElements.Element;
import graphicalElements.FroggerGraphic;
import util.Case;

import java.awt.*;

public class Surprise extends Car{

    public boolean surpriseIsTrap;

    public Surprise(Game game, Case leftPosition, boolean leftToRight, boolean isTrap) {
        super(game, leftPosition, leftToRight);
        this.surpriseIsTrap = isTrap;
    }

    @Override
    public boolean isGoodSurprise() {
        return !this.surpriseIsTrap;
    }

    @Override
    public boolean isTrap(){
        return this.surpriseIsTrap;
    }

    @Override
    public void addToGraphics() {
        Image surpriseImage = FroggerGraphic.surpriseImage;

        game.getGraphic().add(new Element(leftPosition.absc, leftPosition.ord, surpriseImage));
    }
}
