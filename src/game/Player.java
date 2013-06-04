package game;

import java.io.Serializable;

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
   
   private PlayerNetworkData networkData;

	
    public Player(Image[] image, int x, int y, int number)
    {
      networkData = new PlayerNetworkData();
      networkData.setPosX(x);
      networkData.setPosY(y);
      networkData.setAlive(true);
      networkData.setBombAmt(1);
      networkData.setFirePower(1);
      networkData.setNumber(number);
      networkData.setOrientation(0);
       
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
    public void setX(int x) { posX = x; networkData.setPosX(x);}
    public void setY(int y) { posY = y; networkData.setPosY(y);}
    public int getBombAmt(){ return bombAmt; }
    public void setBombAmt(int a){ bombAmt = a; networkData.setBombAmt(a);}
    public int getFirePower() { return firePower; }
    public void setFirePower(int fp) { firePower = fp; networkData.setFirePower(fp);}
    public int getOrientation() { return orientation; }
    public void setOrientation(int o) { orientation = o; networkData.setOrientation(o);}
    public PlayerNetworkData getNetworkData() {
       return networkData;
    }
    public void setNetworkData(PlayerNetworkData networkData) {
       this.networkData = networkData;
       networkData.setPosX(networkData.getPosX());
       networkData.setPosY(networkData.getPosY());
       networkData.setAlive(networkData.isAlive());
       networkData.setBombAmt(networkData.getBombAmt());
       networkData.setFirePower(networkData.getFirePower());
       networkData.setNumber(networkData.getNumber());
       networkData.setOrientation(networkData.getOrientation());
    }
	
}
