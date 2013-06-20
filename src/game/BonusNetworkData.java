package game;

import java.io.Serializable;

/**
 * 
 * @author Julien Bignens
 * @author Bruno Carvalho
 * @author Ga�tan Djomnang Yaze
 * @author Marcel Sinniger
 *
 */
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
