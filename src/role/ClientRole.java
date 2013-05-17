package role;

import startMenu.Model;
import network.Client;
import network.SenderReceiver;

public class ClientRole extends Role {

   Client client;
   
   public ClientRole(Model model) {
      super(model);
   }
   
   public void connectServer(String serverIp, Integer serverPort) {
      client = new Client(this);
      client.connect(serverIp, serverPort);
      super.setConnected(true);
      this.getModel().setScreen("poke server");
   }

   @Override
   public void send(String message) {
      client.sendMessage(message);
      
   }

}
