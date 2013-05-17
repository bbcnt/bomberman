package role;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import startMenu.Model;

import network.SenderReceiver;
import network.Server;
import network.ServerConnection;
import network.utils.NetworkUtils;

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
   
   @Override
   public void send(String message) {
      server.getServerConnection().sendMessage(message);
   }
     
}
