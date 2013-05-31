package network;

import java.io.IOException;
import java.net.*;

import role.Role;

public class Server extends NetworkComponent {
   
   ServerSocket serverSocket;
   Thread thread;
   Role role; 
   
   public Server(Role role) {
      this.role = role;
   }
   
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
   
   public void run() {
      while (true) {
         try {
            System.out.println("Waiting for connections");
            Socket socket = serverSocket.accept();
            super.getConnections().add(new Connection(socket, role));
            System.out.println("Connection accepted");
            
         } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
   }

   

}
