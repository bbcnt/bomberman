package game;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/**
 * Cette classe repr�sente l'�tat d'avoir perdu => "Game Over"
 * @author Julien Bignens
 * @author Bruno Carvalho
 * @author Ga�tan Djomnang Yaze
 * @author Marcel Sinniger
 *
 */
public class GameEndLose extends BasicGameState{

	private Image gameLose;
	public GameEndLose(int state){
		
	}
	/**
	 * M�thode de Slick: Initialisation
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) 
			throws SlickException {
		
		gameLose = new Image("res/gameOver.png");
		
	}

	/**
	 * M�thode de Slick: Mis � jour de la fen�tre
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		
		g.drawImage(gameLose, 0, 0);
	}

	/**
	 * M�thode de Slick: Mis � jour des donn�es
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
	}

	@Override
	public int getID() {
		return 2;
	}

}
