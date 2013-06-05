package role;


import network.Server;
import network.utils.NetworkUtils;
import startMenu.Model;

public class ServerRole extends Role {
    
   private Server server;
   
   public ServerRole(Model model) {
      super(model);
      super.setIpAddress(NetworkUtils.getLocalIp());
   }
   
   public void startServer(Integer port) {
      server = new Server(this);
      server.startServer(port);
      super.setConnected(true);
      this.getModel().setScreen("started");
   }
   
   public void send(Object message) {
      server.sendMessage(message);
   }
}
