package game;

import org.newdawn.slick.*;

/**
 * 
 * @author Julien Bignens
 * @author Bruno Carvalho
 * @author Gaëtan Djomnang Yaze
 * @author Marcel Sinniger
 *
 */
public class Player {
   
   public Image hero_down;
   public Image hero_up;
   public Image hero_left;
   public Image hero_right;
   private int orientation; // 0 up, 1 left, 2 down, 3 right
   private int posX;
   private int posY;
   private int number;
   
   public boolean alive;
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
      alive = true;
      firePower = 2;
      bombAmt = 1;
      this.number = number;
      
      
    }
  
    
    public int X() { return posX;}
    public int Y() { return posY;}
    public synchronized void setX(int x) { posX = x;}
    public synchronized void setY(int y) { posY = y;}
    public int getBombAmt(){ return bombAmt; }
    public synchronized void setBombAmt(int a){ bombAmt = a;}
    public int getFirePower() { return firePower; }
    public synchronized void setFirePower(int fp) { firePower = fp;}
    public int getOrientation() { return orientation; }
    public int getNumber() { return number; }
    public synchronized void setNumber(int n) { number = n; }
    public synchronized void setOrientation(int o) { orientation = o;}
    public synchronized void setAlive(boolean alive) {this.alive = alive;}
    
    public void networkUpdate(PlayerNetworkData networkData) {
       this.setX(networkData.getPosX());
       this.setY(networkData.getPosY());
       this.setAlive(networkData.isAlive());
       this.setBombAmt(networkData.getBombAmt());
       this.setFirePower(networkData.getFirePower());
       this.setOrientation(networkData.getOrientation());
       this.setNumber(networkData.getNumber());
    }

    
}
