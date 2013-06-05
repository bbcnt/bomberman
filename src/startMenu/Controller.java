package startMenu;

public class Controller {

   public Controller() {
      
      Model model = new Model();
      View view = new View(model);
      model.addServerIpObserver(view);
      model.addScreenObserver(view);
     
   }
   public static void main(String[] args) {
	   new Controller();
   }

}
