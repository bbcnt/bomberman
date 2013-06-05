package network;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
 
public class Connection implements Runnable {

   private ObjectOutputStream output;
   private ObjectInputStream input;
   private Socket socket;
   private Receiver reciever;
   private Thread thread;
   
   
   public Connection(Socket socket, Receiver reciever) {
      this.reciever = reciever;
      this.socket = socket;
      initialiseOutputInput();
      thread = new Thread(this);
      thread.start();
   }
   
   public Connection(InetAddress serverIp, Integer serverPort, Receiver reciever) {
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
         output = new ObjectOutputStream(socket.getOutputStream());
         input = new ObjectInputStream(socket.getInputStream());
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
   
   public void send(Object message) {
      try {
         output.writeObject(message);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   @Override
   public void run() {
      while (true) {
         try {
            reciever.recieve(input.readObject());
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
