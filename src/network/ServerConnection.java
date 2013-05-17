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
   private Socket socket;
   private SenderReceiver sr;
   

   public ServerConnection(Socket socket, SenderReceiver sr) {
      
      this.socket = socket;
      this.sr = sr;
      
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
   
   public void sendMessage(String message) {
      output.println(message);
      output.flush();
   }
   
   @Override
   public void run() {
      while (true) {
         try {
            sr.receive(input.readLine());
         } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            try {
               socket.close();
            } catch (IOException e1) {
               e1.printStackTrace();
            }
         }
      }
   }
}
