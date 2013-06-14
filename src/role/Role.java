package role;

import game.BombNetworkData;
import game.Player;
import game.PlayerNetworkData;
import network.Receiver;
import startMenu.Model;

public abstract class Role implements Receiver {
 
   private Integer port;
   private String ipAddress; 
   private Model model;
   private Boolean connected;
      
   public Role(Model model) {
      this.model = model;
      connected = false;
   }
   
   /**
    * @return the port
    */
   public Integer getPort() {
      return port;
   }
   /**
    * @param port the port to set
    */
   public void setPort(Integer port) {
      this.port = port;
   }
   /**
    * @return the ipAddress
    */
   public String getIpAddress() {
      return ipAddress;
   }
   /**
    * @param ipAddress the ipAddress to set
    */
   public void setIpAddress(String ipAddress) {
      this.ipAddress = ipAddress;
   }

   /**
    * @return the model
    */
   public Model getModel() {
      return model;
   }

   /**
    * @return the connected
    */
   public Boolean getConnected() {
      return connected;
   }

   /**
    * @param connected the connected to set
    */
   protected void setConnected(Boolean connected) {
      this.connected = connected;
   }
   
   @Override
   public void recieve(Object message) {

      Player other = null;
      if (amITheServer()) {
         other = model.getGame().getPlaySession().getP2();
      } else {
         other = model.getGame().getPlaySession().getP1();
      }
      
      if (message instanceof PlayerNetworkData) {
         other.setNetworkData((PlayerNetworkData)message);
      } else if (message instanceof BombNetworkData) {
         BombNetworkData bomb = (BombNetworkData)message;
         int x = bomb.getX();
         int y = bomb.getY();
         int radius = bomb.getRadius();

         model.getGame().getPlaySession().addBombFromNetwork(other, x, y, radius);
      } else if (message instanceof int[][]) {
         int bloc[][] = (int[][])message;
         // Fusion la matrice des blocs
         for (int i = 0; i < bloc.length; i++) {
            for (int y = 0; y < bloc[0].length; y++) {
               if (model.getGame().getPlaySession().getBloc()[i][y] > bloc[i][y]) {
                  model.getGame().getPlaySession().getBloc()[i][y] = bloc[i][y];
               }
            }
         }
      }
   }
   
   public abstract void send(Object message);
   
   public abstract boolean amITheServer();
   
}
