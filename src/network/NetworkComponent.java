package network;

import java.util.LinkedList;

/**
 * Cette classe représente un composant réseau
 * @author Julien Bignens
 * @author Bruno Carvalho
 * @author Gaëtan Djomnang Yaze
 * @author Marcel Sinniger
 *
 */
public abstract class NetworkComponent implements Runnable {

   LinkedList<Connection> connections;

   public NetworkComponent() {
      connections = new LinkedList<Connection>();
   }
    
   /**
    * Permet d'envoyer un message contenant un objet quelconque
    * @param message
    */
   public void sendMessage(Object message) {
      for (Connection connection : connections) {
         connection.send(message);
      }
   }

   /**
    * @return the connections
    */
   protected LinkedList<Connection> getConnections() {
      return connections;
   }

}