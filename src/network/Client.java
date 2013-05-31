package network;


import java.io.IOException;
import java.net.*;

import role.Role;

public class Client extends NetworkComponent {
   
   Socket socket;
   Thread thread;
   Role role;
   
   public Client(Role role) {
      this.role = role;
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
      
      super.getConnections().add(new Connection(socket, role));
      
      thread = new Thread(this);
      thread.start();
   }
   
   @Override
   public void run() {
   }
}
