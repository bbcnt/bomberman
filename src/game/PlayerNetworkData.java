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
   private int number;
   private boolean alive;
   private int firePower;
   private int bombAmt;
   /**
    * @return the orientation
    */
   protected int getOrientation() {
      return orientation;
   }
   /**
    * @param orientation the orientation to set
    */
   protected void setOrientation(int orientation) {
      this.orientation = orientation;
   }
   /**
    * @return the posX
    */
   protected int getPosX() {
      return posX;
   }
   /**
    * @param posX the posX to set
    */
   protected void setPosX(int posX) {
      this.posX = posX;
   }
   /**
    * @return the posY
    */
   protected int getPosY() {
      return posY;
   }
   /**
    * @param posY the posY to set
    */
   protected void setPosY(int posY) {
      this.posY = posY;
   }
   /**
    * @return the number
    */
   protected int getNumber() {
      return number;
   }
   /**
    * @param number the number to set
    */
   protected void setNumber(int number) {
      this.number = number;
   }
   /**
    * @return the alive
    */
   protected boolean isAlive() {
      return alive;
   }
   /**
    * @param alive the alive to set
    */
   protected void setAlive(boolean alive) {
      this.alive = alive;
   }
   /**
    * @return the firePower
    */
   protected int getFirePower() {
      return firePower;
   }
   /**
    * @param firePower the firePower to set
    */
   protected void setFirePower(int firePower) {
      this.firePower = firePower;
   }
   /**
    * @return the bombAmt
    */
   protected int getBombAmt() {
      return bombAmt;
   }
   /**
    * @param bombAmt the bombAmt to set
    */
   protected void setBombAmt(int bombAmt) {
      this.bombAmt = bombAmt;
   }
   /**
    * @return the serialversionuid
    */
   protected static long getSerialversionuid() {
      return serialVersionUID;
   }

   
   
}
