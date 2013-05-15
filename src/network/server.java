package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class server {
   
  
   /**
    * @param args
    */
   public static void main(String[] args) {
      
      int port = Integer.valueOf(args[0]);
      
      ServerSocket serverSocket = null;
      try {
         serverSocket = new ServerSocket(port);
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      try {
         serverSocket.accept();
         
         
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

   }

}
