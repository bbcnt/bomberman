package startMenu;

import java.net.InetAddress;
import java.net.UnknownHostException;

import network.utils.NetworkUtils;

public class ServerRole extends Role {

   public ServerRole() {
      super.setIpAddress(NetworkUtils.getLocalIp());
   }
   

}
