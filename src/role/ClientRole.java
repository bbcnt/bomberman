package role;

import network.Client;
import startMenu.Model;


/**
 * Cette class repr�sente le r�le du client pour le joueur
 * @author Julien Bignens
 * @author Bruno Carvalho
 * @author Ga�tan Djomnang Yaze
 * @author Marcel Sinniger
 *
 */
public class ClientRole extends Role {

   Client client;
    
   public ClientRole(Model model) {
      super(model);
   }
   
   /**
    * Cette m�thode permet de se connecter � un serveur
    * @param serverIp
    * @param serverPort
    */
   public void connectServer(String serverIp, Integer serverPort) {
      client = new Client(this);
      client.connect(serverIp, serverPort);
      super.setConnected(true);
      this.getModel().setScreen("connect�");
   }
   
   /* (non-Javadoc)
    * @see role.Role#send()
    */
   @Override
   public void send(Object message) {
      client.sendMessage(message);
   }
   
   /* (non-Javadoc)
    * @see role.Role#amITheServer()
    */
   public boolean amITheServer() {
      return false; 
   }
}
