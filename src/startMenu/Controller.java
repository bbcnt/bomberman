package startMenu;

/**
 * Ce le contr�ler de la fen�tre principale
 * @author Julien Bignens
 * @author Bruno Carvalho
 * @author Ga�tan Djomnang Yaze
 * @author Marcel Sinniger
 *
 */
public class Controller {

   /**
    * Ouvrir la fen�tre principale
    */
   public Controller() {
      
      Model model = new Model();
      View view = new View(model);
      model.addServerIpObserver(view);
      model.addScreenObserver(view);
      model.addStartObserver(view);
     
   }

}
