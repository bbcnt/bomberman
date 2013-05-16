package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

public class Client implements Runnable{

   PrintWriter output;
   BufferedReader input;
   Thread thread;
   
   public void connect(String serverIpString, int serverPort) {
      
      InetAddress serverIp = null;;
      Socket socket = null;
      
      try {
         serverIp = InetAddress.getByName(serverIpString);
      }
      catch (UnknownHostException e) {
         e.printStackTrace();
         System.exit(1);
      }
      
      try {
         socket = new Socket(serverIp, serverPort);
      }
      catch (IOException e) {
         e.printStackTrace();
         System.exit(1);
      }
      
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
      
      thread = new Thread(this);
      thread.start();
   }

   @Override
   public void run() {
      Scanner scan = new Scanner(System.in);
      while (true) {
         
         output.println(scan.nextLine());
         output.flush();
         
      }
      
   }

}
