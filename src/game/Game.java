package game;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tiled.TiledMap;

import role.Role;

public class Game extends StateBasedGame {

	public Image background = null;
	public static final String gameName = "Bomberman 2013";
	
	public static final int gameStart = 0;
	public static final int play      = 1;
	public static final int gameEnd   = 2;
	
	private Play playSession;
	private Role networkAccess;

	
	public Game(String gameName, Role networkAccess)
	{
		super(gameName); //Titre du jeu
		this.networkAccess = networkAccess;
		this.addState(new GameStart(gameStart));
		playSession = new Play(play, networkAccess);
		this.addState(playSession);
		this.addState(new GameEnd(gameEnd));
	}
	public void initStatesList(GameContainer gc) throws SlickException{
		this.getState(gameStart).init(gc, this);
		this.getState(play).init(gc, this);
		this.getState(gameEnd).init(gc, this);
		this.enterState(play); //Permet de choisir le premier state à afficher
				
	}
	/*public static void main(String[] args) {
		AppGameContainer appGc;
		try{
			appGc = new AppGameContainer(new Game(gameName));
			appGc.setDisplayMode(810, 650, false);
			appGc.setShowFPS(false);
			appGc.setMinimumLogicUpdateInterval(150);
			appGc.setTargetFrameRate(60);
			appGc.start();
			
		}catch(SlickException e)
		{
			e.printStackTrace();
		}
		
	}*/

	public Play getPlaySession() {
	   return playSession;
	}
	
}
