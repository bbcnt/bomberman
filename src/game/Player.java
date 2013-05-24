package game;

import org.newdawn.slick.*;

public class Player {

	public Image hero;
	private int posX;
	private int posY;
	private int number;
	
	private boolean alive;
    private int firePower;
    private int bombAmt;
    

	
    public Player(Image image, int x, int y, int number)
    {
    	this.hero = image;
    	posX = x;
    	posY = y;
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
	
}
