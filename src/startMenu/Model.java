package startMenu;

import java.util.Observable;
import java.util.Observer;

import role.ClientRole;
import role.Role;
import role.ServerRole;

public class Model {

   private Role role;
   private ServerIpObservers serverIpObservers;
   private ScreenObservers screenObservers;
   private String screen;
   
   class ServerIpObservers extends Observable {
      public void notifyAllObservers() {
         setChanged();
         notifyObservers(role.getIpAddress());
      }
   }
   
   class ScreenObservers extends Observable {
      public void notifyAllObservers() {
         setChanged();
         notifyObservers(screen);
      }
   }
   
   public Model() {
      serverIpObservers = new ServerIpObservers();
      screenObservers = new ScreenObservers();
   }
      
   public Role getRole() {
      return role;
   }
   
   /**
    * @param view the view to set
    */
   public void addServerIpObserver(View view) {
      this.serverIpObservers.addObserver(view);
   }

   /**
    * @param view the view to set
    */
   public void addScreenObserver(View view) {
      this.screenObservers.addObserver(view);
   }
   
   public void setRoleToClient() {
      role = new ClientRole(this);
      serverIpObservers.notifyAllObservers();
   }
   
   public void setRoleToServer() {
      role = new ServerRole(this);
      serverIpObservers.notifyAllObservers();
   }

   /**
    * @return the screen
    */
   public String getScreen() {
      return screen;
   }

   /**
    * @param screen the screen to set
    */
   public void setScreen(String screen) {
      this.screen = screen;
      screenObservers.notifyAllObservers();
   }

   
}
