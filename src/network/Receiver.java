package network;

/**
 * Interface qui met � disposition une m�thode qui re�oit les messages du r�seau
 * @author marcel
 *
 */
public interface Receiver {
   
   /**
    * Cette m�thode est appel� par l'objet Connection dans le moment o� un message
    * arrive � travers du r�seau.
    * C'est un interface du style java.util.Observer 
    * @param message
    */
   public void recieve(Object message);
}
 