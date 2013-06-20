package network;
 
import java.io.IOException;
import java.net.*;

import role.ClientRole;

/**
 * Cette classe repr�sente le client d'une connexion r�seau
 * @author Julien Bignens
 * @author Bruno Carvalho
 * @author Ga�tan Djomnang Yaze
 * @author Marcel Sinniger
 *
 */
public class Client extends NetworkComponent {
   
   private Thread thread;
   private ClientRole clientRole;
   
   public Client(ClientRole clientRole) {
      this.clientRole = clientRole;
   }
   
   /**
    * Cette m�thode permet de se connecter � un serveur
    * @param serverIpString
    * @param serverPort
    */
   public void connect(String serverIpString, int serverPort) {
      
      InetAddress serverIp = null;
      Socket socket = null;
      
      try {
         serverIp = InetAddress.getByName(serverIpString);
      }
      catch (UnknownHostException e) {
         e.printStackTrace();
         System.exit(1);
      }
      
      try {
         socket = new Socket(serverIp, serverPort);
      }
      catch (IOException e) {
         e.printStackTrace();
         System.exit(1);
      }
      
      super.getConnections().add(new Connection(socket, clientRole));
      
      thread = new Thread(this);
      thread.start();
   }
   
   /**
    * 
    */
   @Override
   public void run() {
   }
}
