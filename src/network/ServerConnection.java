package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerConnection implements Runnable {

   private Thread thread = null;
   private PrintWriter output = null;
   private BufferedReader input = null;
   

   public ServerConnection(Socket socket) {
      
      thread = new Thread(this);
      
      try {
         output = new PrintWriter(socket.getOutputStream(), true);
         input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      } catch (IOException e) {
         try {
            socket.close();
         }
         catch (IOException e2) {
            e.printStackTrace();
         }
         e.printStackTrace();
      }
      
      thread.start();
   }
   
   @Override
   public void run() {

      output.println("Connection established!");
      try {
         System.out.println("waiting for a message");
         System.out.println(input.readLine());
         System.out.println("got the message");
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      //output.flush();
      System.out.println("asdf");
   }

}
