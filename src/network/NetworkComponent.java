package network;

import java.util.LinkedList;

import startMenu.Model;

public abstract class NetworkComponent implements Runnable {

   Model model;
   LinkedList<Connection> connections;

   public NetworkComponent() {
      connections = new LinkedList<Connection>();
   }
   
   public void sendMessage(String message) {
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
