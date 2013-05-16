package startMenu;

public abstract class Role {

   private Integer port;
   private String ipAddress;
   /**
    * @return the port
    */
   public Integer getPort() {
      return port;
   }
   /**
    * @param port the port to set
    */
   public void setPort(Integer port) {
      this.port = port;
   }
   /**
    * @return the ipAddress
    */
   public String getIpAddress() {
      return ipAddress;
   }
   /**
    * @param ipAddress the ipAddress to set
    */
   public void setIpAddress(String ipAddress) {
      this.ipAddress = ipAddress;
   }
   
}
