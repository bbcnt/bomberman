package startMenu;

import network.Client;

public class ClientRole extends Role {

   Client client;
   
   public void connectServer(String serverIp, Integer serverPort) {
      client = new Client();
      client.connect(serverIp, serverPort);
   }

}
