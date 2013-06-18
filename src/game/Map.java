package game;

import java.util.ArrayList;

import bomberman.DBAccess;

/***
 * Classe permettant de construire la carte du jeu.
 * R�cup�re les informations sur une BDD SQLite distante.
 */
public class Map {
	
	//this.dbAccess = new DBAccess("jdbc:sqlite:Midas.sqlite3");
	
	public static final int WIDTH  = 27;
	public static final int HEIGHT = 19;
	public static final int ELEMENT_SIZE = 30;
	
	private int[][] constructibles = new int[WIDTH][HEIGHT];
	
	private ArrayList<DBData> mapElements = new ArrayList<DBData> ();

	/***
	 * Constructeur sans param�tre de Map()
	 * Cr�e le contour de la carte
	 */
	public Map()
	{
		for(int i = 0; i < WIDTH; i++)
			for(int j = 0; j < HEIGHT; j++)
				constructibles[i][j] = 0; // par d�faut, que du sol, aucun bloc
		
		//Contour
		for(int i = 0; i < WIDTH; i++)
		{
			constructibles[i][0] = 1; // 1 = indestructible
			constructibles[i][HEIGHT -1] = 1;
		}
		for(int j = 0; j < HEIGHT; j++)
		{
			constructibles[0][j] = 1;
			constructibles[WIDTH-1][j] = 1;
		}
	}
	/***
	 * M�thode permettant de cr�er le carte finale.
	 * @return -
	 * @param  -
	 */
	public void initMap()
	{
		mapElements = DBAccess.getMap("Bomberman", 1);
		
		for(int index = 0; index < mapElements.size(); index++)
		{
			constructibles[mapElements.get(index).getX()]
						  [mapElements.get(index).getY()] 
								  = mapElements.get(index).getData();
		}
		
	}
	/***
	 * Permet de r�cup�rer la matrice contenant les points stock�s dans la BD.
	 * @param -
	 * @return Tableau int[][] des �l�ments � construire
	 */
	public int[][] getConstructibles() {
		return constructibles;
	}
	
	/***
	 * Permet de modifier la matrice contenant les points stock�s dans la BD.
	 * @param point � modifier int[][]
	 * @return -
	 */
	public void setConstructibles(int[][] constructibles) {
		this.constructibles = constructibles;
	}

}
