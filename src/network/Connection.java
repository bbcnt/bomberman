package network;

import game.PlayerNetworkData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
 
/**
 * Cette classe représente une connextion ouverte. Elle est relié à un serveur ou un client
 * @author Julien Bignens
 * @author Bruno Carvalho
 * @author Gaëtan Djomnang Yaze
 * @author Marcel Sinniger
 *
 */
public class Connection implements Runnable {

   private ObjectOutputStream output;
   private ObjectInputStream input;
   private Socket socket;
   private Receiver reciever;
   private Thread thread;
   
   /**
    * Constructeur appelé par le serveur
    * @param socket
    * @param reciever
    */
   public Connection(Socket socket, Receiver reciever) {
      this.reciever = reciever;
      this.socket = socket;
      initialiseOutputInput();
      thread = new Thread(this);
      thread.start();
   }
   
   /**
    * Constructeur appelé par le client
    * @param serverIp
    * @param serverPort
    * @param reciever
    */
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
   
   /**
    * Cette méthode initialiser les objet pour la gestion des input et output
    */
   private void initialiseOutputInput() {
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
   
   /**
    * Cette méthode permet d'envoyer un message (Objet) à traver du réseau
    * C'est une méthode du style "notifyAll" de java.util.Observable 
    * @param message
    */
   public void send(Object message) {
      try {
         System.out.println("SEND: " + (PlayerNetworkData)message);
         output.reset();
         output.writeObject(message);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   /**
    * Cette méthode gère les objets reçus
    */
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
