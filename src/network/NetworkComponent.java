package network;

import java.util.LinkedList;

/**
 * Cette classe repr�sente un composant r�seau
 * @author Julien Bignens
 * @author Bruno Carvalho
 * @author Ga�tan Djomnang Yaze
 * @author Marcel Sinniger
 *
 */
public abstract class NetworkComponent implements Runnable {

   LinkedList<Connection> connections;

   /**
    * Un NetworkComponent contient une liste avec toutes les connexions ouvertes
    */
   public NetworkComponent() {
      connections = new LinkedList<Connection>();
   }
    
   /**
    * Permet d'envoyer un message contenant un objet quelconque � toutes les recepteurs
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