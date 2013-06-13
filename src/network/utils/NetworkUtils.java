package network.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * Cette classe met � disposition certaine m�thodes g�n�rales qui concernent la partie r�seau
 * @author marcel
 *
 */
public class NetworkUtils {
   
   /**
    * Cette classe permet de r�cup�rer l'adress IP de l'interface principale
    * (celui qui est utilis� par la route par d�faut)
    * @return
    */
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
   
   public static void getPrincipalNetworkInterfaces() {
      //try {
         
         
        // for (Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces(); e.hasMoreElements();)
        //    final NetworkInterface i = e.nextElement();
         
           // if (!i.isLoopback() &&
           //     !i.isVirtual()) {
            //   System.out.println(e.nextElement().getInetAddresses().);
            //}

         
      //} catch (SocketException e) {
         // TODO Auto-generated catch block
        // e.printStackTrace();
      //}
   }
   
   public static void main(String[] args) {
      getPrincipalNetworkInterfaces();
   }


}
