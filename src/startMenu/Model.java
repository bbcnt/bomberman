package startMenu;

import game.Game;
import java.util.Observable;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import role.*;

public class Model {

   private Role role;
   private ServerIpObservers serverIpObservers;
   private ScreenObservers screenObservers;
   private String screen;
   private Game game;
   
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

   public void StartGame(String gameName) {
	   AppGameContainer appGc;
		try{
		   game = new Game(gameName, role);
			appGc = new AppGameContainer(game);
			appGc.setDisplayMode(810, 650, false);
			appGc.setShowFPS(false);
			appGc.setMinimumLogicUpdateInterval(150);
			appGc.setTargetFrameRate(60);
			appGc.start();
			
		}
		catch(SlickException c){
			c.printStackTrace();
		}
   }
   
   public Game getGame() {
      return game;
   }
}
