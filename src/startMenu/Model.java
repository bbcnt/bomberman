package startMenu;

import java.util.Observable;
import java.util.Observer;

public class Model {

   private Role role;
   private ServerIpObservers serverIpObservers;
   
   class ServerIpObservers extends Observable {
      public void notifyAllObservers() {
         setChanged();
         notifyObservers(role.getIpAddress());
      }
   }
   
   public Model() {
      serverIpObservers = new ServerIpObservers();
   }
   
   public void addServerIpObserver(Observer o) {
      serverIpObservers.addObserver(o);
   }
   
   public Role getRole() {
      return role;
   }
   
   /**
    * @param view the view to set
    */
   public void addView(View view) {
      this.serverIpObservers.addObserver(view);
   }

   public void setRoleToClient() {
      role = new ClientRole();
      serverIpObservers.notifyAllObservers();
   }
   
   public void setRoleToServer() {
      role = new ServerRole();
      serverIpObservers.notifyAllObservers();
   }

}
