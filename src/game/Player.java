package game;

import org.newdawn.slick.*;

public class Player {
	
	public Image hero_down;
	public Image hero_up;
	public Image hero_left;
	public Image hero_right;
	private int orientation; // 0 up, 1 left, 2 down, 3 right
	private int posX;
	private int posY;
	private int number;
	
	private boolean alive;
    private int firePower;
    private int bombAmt;
    

	
    public Player(Image[] image, int x, int y, int number)
    {
    	hero_down = image[0];
    	hero_up = image[1];
    	hero_left = image[2];
    	hero_right = image[3];
    	posX = x;
    	posY = y;
    	orientation = 0;
    	this.number = number;
    	alive = true;
    	firePower = 1;
    	bombAmt = 1;
    	
    }
  
    
    public int X() { return posX;}
    public int Y() { return posY;}
    public void setX(int x) { posX = x;}
    public void setY(int y) { posY = y;}
    public int getBombAmt(){ return bombAmt; }
    public void setBombAmt(int a){ bombAmt = a; }
    public int getFirePower() { return firePower; }
    public void setFirePower(int fp) { firePower = fp; }
    public int getOrientation() { return orientation; }
    public void setOrientation(int o) { orientation = o; }
	
}
