package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

public class CopyOfClient {

   PrintWriter output;
   BufferedReader input;
   
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
      
      Scanner scan = new Scanner(System.in);
      while (true) {
         
         output.println(scan.nextLine());
         output.flush();
         
      }
   }
   
   /**
    * @param args
    */
   public static void main(String[] args) {
      CopyOfClient c = new CopyOfClient();
      c.connect("127.0.0.1", 7777);

   }

}
