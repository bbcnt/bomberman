package startMenu;

public class Bomerman {

   /**
    * @param args
    */
   public static void main(String[] args) {

      
      Model model = new Model();
      View view = new View(model);
      model.addView(view);
     
   }

}
