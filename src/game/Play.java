package game;

//import org.lwjgl.input.Mouse;
import java.util.ArrayList;

import org.lwjgl.opengl.Drawable;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tiled.TileSet;
import org.newdawn.slick.tiled.TiledMap;

public class Play extends BasicGameState{

	private Image indestructible;
	private Image background;
	private Image[] hero1;
	private Player p1;
	private boolean[][] movesMatrix = new boolean[Map.WIDTH][Map.HEIGHT];
	int time = 0;

	private Map map = null;
	private ArrayList<Bomb> bombList = new ArrayList<Bomb>();
	//private TiledMap map = null;
	
	public Play(int state){
		
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) 
			throws SlickException {
		
		map = new Map();
		map.initMap();
		indestructible = new Image("res/indestructible.png");
		background = new Image("res/background_tile.png");
		hero1 = new Image[4];
		hero1[0] = new Image("res/hero_down.png");
		hero1[1] = new Image("res/hero_up.png");
		hero1[2] = new Image("res/hero_left.png");
		hero1[3] = new Image("res/hero_right.png");
		
		p1 = new Player(hero1, 5, 5, 1);
		//map = new TiledMap("res/map.tmx");
		
			
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		
		//Contour
		for(int i = 0; i < 27; i++)
		{
			g.drawImage(indestructible, i * 30, 0);
			g.drawImage(indestructible, i * 30, 18 * 30);
			movesMatrix[i][0] = false;
		}
		for(int j = 0; j < 19; j++)
		{
			g.drawImage(indestructible, 0, j * 30);
			g.drawImage(indestructible, 26 * 30, j * 30);
			movesMatrix[0][j] = false;
		}
		
		//Partie chargée BDD
		for(int i = 1; i < 26; i++)
		{
			for(int j = 1; j < 18; j++)
			{
				/*if(map.getData(i,j) == 0)
				{*/
					g.drawImage(background, i * 30, j * 30);
					movesMatrix[i][j] = true;
					
				/*}
				if(map.getData(i,j) == 1)
				{
					g.drawImage(indestructible, i * 30, j * 30);
					movesMatrix[i][j] = false;
				}*/
				
			}
		}
		switch(p1.getOrientation())
		{
		case 0: 	
			g.drawImage(p1.hero_down, p1.X() * 30, (p1.Y() * 30) -15 );
			break;
		case 1: 
			g.drawImage(p1.hero_up, p1.X() * 30, (p1.Y() * 30) -15 );
			break;
		case 2:
			g.drawImage(p1.hero_left, p1.X() * 30, (p1.Y() * 30) -15 );
			break;
		case 3: 
			g.drawImage(p1.hero_right, p1.X() * 30, (p1.Y() * 30) -15 );
			break;
			
		}
		//g.drawImage(p1.hero, p1.X() * 30, (p1.Y() * 30) -15 ); //essayer avec y = 15
		//map.render(0, 0, 0); //Dessin du background
		//map.render(0, 0, 1); //Dessin des indéstructibles
		g.drawImage(new Image("res/score.png"), 0, 571);
		g.drawString(" : " + p1.getBombAmt(), 45, 615);
		g.drawString(" : " + p1.getFirePower(), 200, 615);
		
		if(!bombList.isEmpty())
		{
			for(Bomb b : bombList)
			{
				g.drawImage(b.bomb, b.X() * 30, b.Y() * 30);
			}
		}
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
	
		
		Input input = gc.getInput();
		
		if(input.isKeyDown(Input.KEY_DOWN))
			if(p1.Y() + 1 <= Map.HEIGHT)
				if(movesMatrix[p1.X()][p1.Y() + 1] == true) {
					p1.setY(p1.Y() +1);
					p1.setOrientation(0);
				}

		if(input.isKeyDown(Input.KEY_UP))
			if(p1.Y() - 1 >= 0)
				if(movesMatrix[p1.X()][p1.Y() - 1] == true) {
					p1.setY(p1.Y() - 1);
					p1.setOrientation(1);
				}
		
		if(input.isKeyDown(Input.KEY_LEFT))
			if(p1.X() - 1 >= 0)
				if(movesMatrix[p1.X() - 1][p1.Y()] == true) {
					p1.setX(p1.X() - 1);
					p1.setOrientation(2);
				}
		
		if(input.isKeyDown(Input.KEY_RIGHT))
			if(p1.X() + 1 <= Map.WIDTH)
				if(movesMatrix[p1.X() + 1][p1.Y()] == true) {
					p1.setX(p1.X() + 1);
					p1.setOrientation(3);
				}
		

		
		if(input.isKeyPressed(Input.KEY_SPACE))
		{
			if(p1.getBombAmt() > 0)
			{
				bombList.add(new Bomb(new Image("res/bomb.png"), 5, 
				p1.getFirePower(), p1, p1.X(), p1.Y()));
				p1.setBombAmt(p1.getBombAmt() - 1);
			}
			
		}
	}

	@Override
	public int getID() {
		return 1;
	}

}
