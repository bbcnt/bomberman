package role;


import network.Server;
import network.utils.NetworkUtils;
import startMenu.Model;

/**
 * Cette class repr�sente le r�le du serveur pour le joueur
 * @author Julien Bignens
 * @author Bruno Carvalho
 * @author Ga�tan Djomnang Yaze
 * @author Marcel Sinniger
 *
 */
public class ServerRole extends Role {
    
   private Server server;
   
   public ServerRole(Model model) {
      super(model);
      super.setIpAddress(NetworkUtils.getLocalIp());
   }
   
   /**
    * Cette m�thode permet de d�marrer le serveur
    * @param port
    */
   public void startServer(Integer port) {
      server = new Server(this);
      server.startServer(port);
      super.setConnected(true);
      this.getModel().setScreen("D�marr�");
   }
   
   /* (non-Javadoc)
    * @see role.Role#send()
    */
   @Override
   public synchronized void send(Object message) {
      server.sendMessage(message);
   }
   
   /* (non-Javadoc)
    * @see role.Role#amITheServer()
    */
   @Override
   public boolean amITheServer() {
      return true;
   }   
   
}
