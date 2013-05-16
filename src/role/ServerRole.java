package role;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import network.Server;
import network.utils.NetworkUtils;

public class ServerRole extends Role {
   
   private Server server;

   public ServerRole() {
      super.setIpAddress(NetworkUtils.getLocalIp());
   }
   
   public void startServer(int port) {
      server = new Server();
      server.startServer(port);
   }
   
}
