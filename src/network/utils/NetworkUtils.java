package network.utils;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * Cette classe met à disposition certaine méthodes générales qui concernent la partie réseau
 * @author marcel
 *
 */
public class NetworkUtils {
   
   /**
    * Cette classe permet de récupérer l'adress IP de l'interface principale
    * (celui qui est utilisé par la route par défaut)
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
   
   
   
   public static void getPrincipalNetworkInterfaces()
   		throws SocketException {
      //try {
         
	   Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
		while (interfaces.hasMoreElements()){
		    NetworkInterface current = interfaces.nextElement();
		    //System.out.println(current);
		    if (!current.isUp() || current.isLoopback() || current.isVirtual() || current.getDisplayName().contains("Virtual")) continue;
		    Enumeration<InetAddress> addresses = current.getInetAddresses();
		        InetAddress current_addr = addresses.nextElement();
		        if (current_addr instanceof Inet4Address)
		        	  System.out.println(current_addr.getHostAddress());
		        	else if (current_addr instanceof Inet6Address)
		        	  System.out.println(current_addr.getHostAddress());
		        if (current_addr.isLoopbackAddress()) continue;
		        

		    }
		}
         
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
   
   public static void main(String[] args) {
      try {
		getPrincipalNetworkInterfaces();
	} catch (SocketException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }


}
