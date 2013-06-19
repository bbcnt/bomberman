package game;

import org.newdawn.slick.*;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.state.*;

public class GameEndWin extends BasicGameState{

	public String mouse ="No input";
	Image ready = null;
	Image background = null;
	public GameEndWin(int state){
		
	}
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) 
			throws SlickException {
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		int posX = Mouse.getX();
		int posY = Mouse.getY();
		
		if((posX > 300) && posX < 501 && (posY > 220 && posY < 300))
		{
			if(Mouse.isButtonDown(0))
			{
				sbg.enterState(1);
			}
		}
			
	}

	@Override
	public int getID() {
		return 0;
	}

}
