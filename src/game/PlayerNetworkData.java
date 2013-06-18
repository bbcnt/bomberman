package game;

import java.io.Serializable;

public class PlayerNetworkData implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   
   private int orientation; // 0 up, 1 left, 2 down, 3 right
   private int posX;
   private int posY;
   private boolean alive;
   private int firePower;
   private int bombAmt;
   
   
   public PlayerNetworkData(Player p) {
      orientation = p.getOrientation();
      posX = p.X();
      posY = p.Y();
      alive = p.alive;
      firePower = p.getFirePower();
      bombAmt = p.getBombAmt();
   }
   
   
   /**
    * @return the orientation
    */
   protected int getOrientation() {
      return orientation;
   }

   /**
    * @return the posX
    */
   protected int getPosX() {
      return posX;
   }

   /**
    * @return the posY
    */
   protected int getPosY() {
      return posY;
   }


   /**
    * @return the alive
    */
   protected boolean isAlive() {
      return alive;
   }

   /**
    * @return the firePower
    */
   protected int getFirePower() {
      return firePower;
   }

   /**
    * @return the bombAmt
    */
   protected int getBombAmt() {
      return bombAmt;
   }

   /**
    * @return the serialversionuid
    */
   protected static long getSerialversionuid() {
      return serialVersionUID;
   }
  
}
