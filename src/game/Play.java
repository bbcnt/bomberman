package game;

//import org.lwjgl.input.Mouse;
import java.util.ArrayList;

import network.Receiver;

import org.lwjgl.opengl.Drawable;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tiled.TileSet;
import org.newdawn.slick.tiled.TiledMap;

import role.Role;

public class Play extends BasicGameState {

	private Image indestructible;
	private Image destructible;
	private Image background;
	private Image[] hero1;
	private Image[] hero2;
	private Player p1;
	private Player p2;
	private boolean[][] movesMatrix = new boolean[Map.WIDTH][Map.HEIGHT];

	private Map map = null;
	private ArrayList<Bomb> bombList = new ArrayList<Bomb>();
	private Player[] playerList;
	
	private Role networkAccess;
	
	public Play(int state, Role networkAccess){
	   this.networkAccess = networkAccess; 
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) 
			throws SlickException {
		
		map = new Map();
		map.initMap();
		
		indestructible = new Image("res/indestructible.png");
		destructible = new Image("res/destructible2.png");
		background = new Image("res/background_tile.png");
		hero1 = new Image[4];
		hero2 = new Image[4];
		
		hero1[0] = new Image("res/hero_down.png");
		hero1[1] = new Image("res/hero_up.png");
		hero1[2] = new Image("res/hero_left.png");
		hero1[3] = new Image("res/hero_right.png");
		
		hero2[0] = new Image("res/hero2_down.png");
		hero2[1] = new Image("res/hero2_up.png");
		hero2[2] = new Image("res/hero2_left.png");
		hero2[3] = new Image("res/hero2_right.png");
		
		p1 = new Player(hero1, 1, 1, 1);
		p2 = new Player(hero2, 25, 17, 2);
		
		playerList = new Player[2];
		playerList[0] = p1;
		playerList[1] = p2;
		
		//playerList = new ArrayList<Player>();
	}

	/*** 
	 * Cette méthode permet de dessiner la carte du jeu en fonction
	 * d'une carte stockée dans une base de données. Elle fait appel à 
	 * la variable map de type Map.
	 * @param Map map, la carte à dessiner
	 * @param Graphics g, nécessaire pour dessiner dans la méthode render
     * @return -
	 */
	private void drawMap(Map map, Graphics g)
	{
		for(int i = 0; i < Map.WIDTH; i++)
		{
			for(int j = 0; j < Map.HEIGHT; j++)
			{				
				switch(map.getConstructibles()[i][j])
				{
				case 0:
					g.drawImage(background, i * Map.ELEMENT_SIZE ,
											j * Map.ELEMENT_SIZE);
					movesMatrix[i][j] = true;
					break;
				case 1:
					g.drawImage(indestructible, i * Map.ELEMENT_SIZE ,
												j * Map.ELEMENT_SIZE);
					movesMatrix[i][j] = false;
					break;
				case 2: 
					g.drawImage(destructible, i * Map.ELEMENT_SIZE ,
											  j * Map.ELEMENT_SIZE);
					movesMatrix[i][j] = false;
					break;
				}
			}
		}
	}
	
	/***
	 * Permet d'afficher l'inventaire des joueurs
	 * @param g, variable Graphics pour le dessin
	 * @throws SlickException
	 */
	private void drawInventory(Graphics g) throws SlickException
	{
		g.drawImage(new Image("res/score.png"), 0, 570);
		
		g.drawString(" : " + p1.getBombAmt(), 45, 615);
		g.drawString(" : " + p1.getFirePower(), 200, 615);
		
		g.drawString(" : " + p2.getBombAmt(), 450, 615);
		g.drawString(" : " + p2.getFirePower(), 590, 615);
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
	   		
		drawMap(map, g);
		drawInventory(g);
		
		for(Player p : playerList)
		{
			switch(p.getOrientation())
			{
			case 0: 	
				g.drawImage(p.hero_down, p.X() * Map.ELEMENT_SIZE, 
						                (p.Y() * Map.ELEMENT_SIZE) -15 );
				break;
			case 1: 
				g.drawImage(p.hero_up, p.X() * Map.ELEMENT_SIZE, 
						              (p.Y() * Map.ELEMENT_SIZE) -15 );
				break;
			case 2:
				g.drawImage(p.hero_left, p.X() * Map.ELEMENT_SIZE, 
						                (p.Y() * Map.ELEMENT_SIZE) -15 );
				break;
			case 3: 
				g.drawImage(p.hero_right, p.X() * Map.ELEMENT_SIZE, 
						                 (p.Y() * Map.ELEMENT_SIZE) -15 );
				break;
				
			}
		}
				
		if(!(bombList.isEmpty()))
		{
			for(Bomb b : bombList)
			{
				if(!b.getExploded())
				{
					g.drawImage(b.bomb, b.X() * Map.ELEMENT_SIZE, 
							            b.Y() * Map.ELEMENT_SIZE);
					movesMatrix[b.X()][b.Y()] = false;
				}

			}
		}
			
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
	   
		Input input = gc.getInput();
		Boolean hasChanged = false;
		
		if(input.isKeyDown(Input.KEY_DOWN))
			if(p1.Y() + 1 <= Map.HEIGHT)
				if(movesMatrix[p1.X()][p1.Y() + 1] == true) {
					p1.setY(p1.Y() +1);
					p1.setOrientation(0);
					hasChanged = true;
				}

		if(input.isKeyDown(Input.KEY_UP))
			if(p1.Y() - 1 >= 0)
				if(movesMatrix[p1.X()][p1.Y() - 1] == true) {
					p1.setY(p1.Y() - 1);
					p1.setOrientation(1);
					hasChanged = true;
				}
		
		if(input.isKeyDown(Input.KEY_LEFT))
			if(p1.X() - 1 >= 0)
				if(movesMatrix[p1.X() - 1][p1.Y()] == true) {
					p1.setX(p1.X() - 1);
					p1.setOrientation(2);
					hasChanged = true;
				}
		
		if(input.isKeyDown(Input.KEY_RIGHT))
			if(p1.X() + 1 <= Map.WIDTH)
				if(movesMatrix[p1.X() + 1][p1.Y()] == true) {
					p1.setX(p1.X() + 1);
					p1.setOrientation(3);
					hasChanged = true;
				}
		
		//TEST 
		
		if(input.isKeyDown(Input.KEY_S))
			if(p2.Y() + 1 <= Map.HEIGHT)
				if(movesMatrix[p2.X()][p2.Y() + 1] == true) {
					p2.setY(p2.Y() +1);
					p2.setOrientation(0);
					hasChanged = true;
				}

		if(input.isKeyDown(Input.KEY_W))
			if(p2.Y() - 1 >= 0)
				if(movesMatrix[p2.X()][p2.Y() - 1] == true) {
					p2.setY(p2.Y() - 1);
					p2.setOrientation(1);
					hasChanged = true;
				}
		
		
		if(input.isKeyPressed(Input.KEY_SPACE))
		{
			if(p1.getBombAmt() > 0)
			{
				bombList.add(new Bomb(new Image("res/bomb.png"), 5, 
				p1.getFirePower(), p1, p1.X(), p1.Y()));
				p1.setBombAmt(p1.getBombAmt() - 1);
				hasChanged = true;
			}	
		}
		if(!bombList.isEmpty())
		{
			if(bombList.get(0).getExploded() == true) {
				bombList.remove(0);
				hasChanged = true;
			}
		}
		
		if (hasChanged) {
		    networkAccess.send(this.p1.getNetworkData());
		}
		
	}

	@Override
	public int getID() {
		return 1;
	}
	
	public Player getP1() {
	   return p1;
	}
	
	public Player getP2() {
	   return p2;
	}
	

}
 