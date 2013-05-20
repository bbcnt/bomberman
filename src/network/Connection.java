package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Connection implements Runnable {

   private PrintWriter output;
   private BufferedReader input;
   private Socket socket;
   private Reciever reciever;
   private Thread thread;
   
   
   public Connection(Socket socket, Reciever reciever) {
      this.reciever = reciever;
      this.socket = socket;
      initialiseOutputInput();
      thread = new Thread(this);
      thread.start();
   }
   
   public Connection(InetAddress serverIp, Integer serverPort, Reciever reciever) {
      this.reciever = reciever;
      try {
         this.socket = new Socket(serverIp, serverPort);
         initialiseOutputInput();
         thread = new Thread(this);
         thread.start();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   public void initialiseOutputInput() {
      try {
         output = new PrintWriter(socket.getOutputStream());
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
   }
   
   public void send(String message) {
      output.println(message);
      output.flush();
   }
   
   @Override
   public void run() {
      while (true) {
         try {
            reciever.recieve(input.readLine());
         } catch (Exception e) {
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
