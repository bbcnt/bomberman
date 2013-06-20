package game;

import java.io.Serializable;

/**
 * Cette classe permet de transmettre les donn�es n�cessaires d'un bonus
 * @author Julien Bignens
 * @author Bruno Carvalho
 * @author Ga�tan Djomnang Yaze
 * @author Marcel Sinniger
 *
 */
public class BonusNetworkData implements Serializable {

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
