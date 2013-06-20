package network;

import java.io.IOException;
import java.net.*;

import role.ServerRole;

/**
 * Cette classe repr�sente un serveur r�seau
 * @author Julien Bignens
 * @author Bruno Carvalho
 * @author Ga�tan Djomnang Yaze
 * @author Marcel Sinniger
 *
 */
public class Server extends NetworkComponent {
   
   private ServerSocket serverSocket;
   private Thread thread;
   private ServerRole serverRole; 
   
   public Server(ServerRole serverRole) {
      this.serverRole = serverRole;
   }
   
   /**
    * Cette m�thode permet de d�marrer le serveur
    * @param port indique le num�ro de port pour le serveur
    */
   public void startServer(int port) {

      try {
         serverSocket = new ServerSocket(port);
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
      thread = new Thread(this);
      thread.start();
      
      System.out.println("Server started");

   }
   
   /**
    * Cette m�thode g�re les nouvelles connextions des clients
    */
   public void run() {
      while (true) {
         try {
            System.out.println("Waiting for connections");
            Socket socket = serverSocket.accept();
            super.getConnections().add(new Connection(socket, serverRole));
            System.out.println("Connection accepted");
            
         } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
   }

}