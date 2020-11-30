package environment;

import gameCommons.Game;
import gameCommons.IEnvironmentInf;

public class EnvironmentInf extends Environment implements IEnvironmentInf {

    public EnvironmentInf(Game game) {
        super(game);
        this.lanes.add(new Lane(game, game.height-1));

    }

    @Override
    public void slideRoad(boolean downwards){
        for (Lane lane : lanes){
            lane.slideLane(downwards);
        }
    }

    @Override
    public void addTopLane(){
        this.lanes.add(new Lane(game,game.height));
    }

    @Override
    public void update(boolean slideRoadDownwards) {
        super.update();
        this.slideRoad(slideRoadDownwards);

    }
}
