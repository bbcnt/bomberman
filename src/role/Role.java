package role;

import game.BlocNetworkData;
import game.BombNetworkData;
import game.BonusNetworkData;
import game.Player;
import game.PlayerNetworkData;
import network.Receiver;
import startMenu.Model;

/**
 * Cette class repr�sente un r�le du joueur dans le jeu
 * @author Julien Bignens
 * @author Bruno Carvalho
 * @author Ga�tan Djomnang Yaze
 * @author Marcel Sinniger
 *
 */
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
   
   /**
    * Cette m�thode traite les donn�es re�ues � travers du r�seau
    * @param le massage re�u � travers du r�seau
    */
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
      }
      
      if (message instanceof BombNetworkData) {
         BombNetworkData bomb = (BombNetworkData)message;
         model.getGame().getPlaySession().networkUpdate(bomb);
      }
      
      if (message instanceof BlocNetworkData) {
         int bloc[][] = ((BlocNetworkData)message).getBloc();
         // Fusioner la matrice des blocs
         for (int i = 0; i < bloc.length; i++) {
            for (int y = 0; y < bloc[0].length; y++) {
               if (model.getGame().getPlaySession().getBloc()[i][y] > bloc[i][y]) {
                  model.getGame().getPlaySession().getBloc()[i][y] = bloc[i][y];
               }
            }
         }
      }
      
      if (message instanceof BonusNetworkData) {
         int bloc[][] = ((BonusNetworkData)message).getBonus();
         // Fusioner la matrice des blocs
         for (int i = 0; i < bloc.length; i++) {
            for (int y = 0; y < bloc[0].length; y++) {
               if (model.getGame().getPlaySession().getBonusMatrix()[i][y] > bloc[i][y]) {
                  model.getGame().getPlaySession().getBonusMatrix()[i][y] = bloc[i][y];
               }
            }
         }
      }
   }
   
   /**
    * Cette m�thode permet d'envoyer un message (objet quelconque) � travers du r�seau
    * @param message
    */
   public abstract void send(Object message);
   
   /**
    * Cette m�thode indique si l'application joue la r�le du serveur ou pas
    * @return
    */
   public abstract boolean amITheServer();
   
}
