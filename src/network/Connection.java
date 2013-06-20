package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
 
/**
 * Cette classe repr�sente une connextion ouverte. Cette derni�re est reli�e � un serveur ou un client
 * @author Julien Bignens
 * @author Bruno Carvalho
 * @author Ga�tan Djomnang Yaze
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
    * Constructeur appel� par le serveur
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
    * Constructeur appel� par le client
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
         System.exit(1);
      }
   }
   
   /**
    * Cette m�thode initialise les objets pour la gestion des inputs et outputs
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
            System.exit(1);
         }
         e.printStackTrace();
         System.exit(1);
      }
   }
   
   /**
    * Cette m�thode permet d'envoyer un message (Objet) � travers du r�seau
    * C'est une m�thode du style "notifyAll" de java.util.Observable 
    * @param message
    */
   public void send(Object message) {
      try {
         output.reset();
         output.writeObject(message);
      } catch (IOException e) {
         e.printStackTrace();
         System.exit(1);
      }
   }
   
   /**
    * Cette m�thode g�re les objets re�us
    */
   @Override
   public void run() {
      while (true) {
         try {

            reciever.recieve(input.readObject());
            
         } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
            try {
               if (!socket.isClosed() ) {
                  socket.close();
               }
            } catch (IOException e1) {
                e1.printStackTrace();
                System.exit(1);
            }
         }         
      }
   }

}
