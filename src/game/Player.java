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
    public synchronized void setX(int x) { posX = x; networkData.setPosX(x);}
    public synchronized void setY(int y) { posY = y; networkData.setPosY(y);}
    public int getBombAmt(){ return bombAmt; }
    public synchronized void setBombAmt(int a){ bombAmt = a; networkData.setBombAmt(a);}
    public int getFirePower() { return firePower; }
    public synchronized void setFirePower(int fp) { firePower = fp; networkData.setFirePower(fp);}
    public int getOrientation() { return orientation; }
    public synchronized void setOrientation(int o) { orientation = o; networkData.setOrientation(o);}
    private synchronized void setAlive(boolean alive) {this.alive = alive; networkData.setAlive(alive);}
    private synchronized void setNumber(int number) {this.number = number; networkData.setNumber(number);}
    
    
    public PlayerNetworkData getNetworkData() { return networkData;}
    public void setNetworkData(PlayerNetworkData networkData) {
       this.networkData = networkData;
       this.setX(networkData.getPosX());
       this.setY(networkData.getPosY());
       this.setAlive(networkData.isAlive());
       this.setBombAmt(networkData.getBombAmt());
       this.setFirePower(networkData.getFirePower());
       this.setNumber(networkData.getNumber());
       this.setOrientation(networkData.getOrientation());
    }

    
}
