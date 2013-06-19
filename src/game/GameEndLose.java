package game;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import sun.java2d.pipe.DrawImage;

public class GameEndLose extends BasicGameState{

	private Image gameLose;
	public GameEndLose(int state){
		
	}
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) 
			throws SlickException {
		
		gameLose = new Image("res/gameOver.png");
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		
		g.drawImage(gameLose, 0, 0);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
	}

	@Override
	public int getID() {
		return 2;
	}

}
