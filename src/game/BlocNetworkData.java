package game;

import java.io.Serializable;

public class BlocNetworkData implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   private int[][] bloc;
   
   public BlocNetworkData(int[][] bloc) {
      this.bloc = bloc; 
   }

   /**
    * @return the bloc
    */
   public int[][] getBloc() {
      return bloc;
   }


}
