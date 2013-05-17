package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class Server implements Runnable {
   
   ServerSocket serverSocket;
   Thread thread;
   ServerConnection serverConnection;
   SenderReceiver sr;
   
   public Server(SenderReceiver sr) {
      this.sr = sr;
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
            serverConnection = new ServerConnection(socket, sr);
            System.out.println("Connection accepted");
            
         } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
   }


   /**
    * @return the serverConnection
    */
   public ServerConnection getServerConnection() {
      return serverConnection;
   }
   

}
