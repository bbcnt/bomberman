package startMenu;

public class Controller {

   public Controller() {
      
      Model model = new Model();
      View view = new View(model);
      model.addView(view);
     
   }

}
