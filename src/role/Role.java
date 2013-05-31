package role;

import network.Reciever;
import startMenu.Model;

public abstract class Role implements Reciever {

   private Integer port;
   private String ipAddress; 
   private Model model;
   private Boolean connected;
      
   public Role(Model model) {
      this.model = model;
      connected = false;
   }
   
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

   /**
    * @return the model
    */
   public Model getModel() {
      return model;
   }

   /**
    * @return the connected
    */
   public Boolean getConnected() {
      return connected;
   }

   /**
    * @param connected the connected to set
    */
   protected void setConnected(Boolean connected) {
      this.connected = connected;
   }
   
   @Override
   public void recieve(Object message) {
      model.setScreen((String)message);
   }
   
   public abstract void send(String message);
   
}
