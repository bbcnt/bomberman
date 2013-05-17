package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

import startMenu.Model;

public class Client implements Runnable{

   private PrintWriter output;
   private BufferedReader input;
   private Thread thread;
   private SenderReceiver sr;
   private Socket socket;
   
   public Client(SenderReceiver sr) {
      this.sr = sr;
   }
   
   public void connect(String serverIpString, int serverPort) {
      
      InetAddress serverIp = null;
      socket = null;
      
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
