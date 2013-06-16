package game;

import java.io.Serializable;

public class BonusNetworkData implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   int[][] bonus;
   
   public BonusNetworkData(int[][] bonus) {
      this.bonus = bonus;
   }

   /**
    * @return the bonus
    */
   public int[][] getBonus() {
      return bonus;
   }

   
}
