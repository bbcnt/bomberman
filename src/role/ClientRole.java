package role;

import network.Client;
import startMenu.Model;

public class ClientRole extends Role {

   Client client;
    
   public ClientRole(Model model) {
      super(model);
   }
   
   public void connectServer(String serverIp, Integer serverPort) {
      client = new Client(this);
      client.connect(serverIp, serverPort);
      super.setConnected(true);
      this.getModel().setScreen("connected");
   }
   
   public void send(String message) {
      client.sendMessage(message);
   }
}
