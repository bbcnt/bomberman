package network.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetworkUtils {

   public static String getLocalIp() {
      
      String ipAddress = null;
      
      try {
         ipAddress =  InetAddress.getLocalHost().getHostAddress();
      }
      catch (UnknownHostException e) {
         e.printStackTrace();
         System.exit(1);
      }
      
      return ipAddress;
   }


}
