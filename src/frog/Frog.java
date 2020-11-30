package frog;

import gameCommons.Game;
import gameCommons.IFrog;
import util.Case;
import util.Direction;

public class Frog implements IFrog {
	
	private Game game;
	private int absc;
	private int ord;
	private Direction lastDirection;

	public Frog(Game game) {
		this.game = game;
		this.absc = game.width/2;
		this.ord = 0;
		this.lastDirection = null;
	}

	@Override
	public Case getPosition() {
		return new Case(absc,ord);
	}

	@Override
	public Direction getDirection() {
		return lastDirection;
	}

	@Override
	public void move(Direction key) {
		lastDirection = key;
		switch (key){
			case up:
				if (ord == game.height-1){
					break;
				}
				ord ++;
				break;
			case down:
				if (ord == 0){
					break;
				}
				ord --;
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
}
