package game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import bomberman.DBAccess;

/***
 * Classe permettant de construire la carte du jeu.
 * Récupère les informations sur une BDD SQLite distante.
 */
public class Map {
	
	//this.dbAccess = new DBAccess("jdbc:sqlite:Midas.sqlite3");
	
	public static final int WIDTH  = 27;
	public static final int HEIGHT = 19;
	
	private int[][] constructibles = new int[WIDTH][HEIGHT];
	private int[][] objects        = new int[WIDTH][HEIGHT];
	
	private ArrayList<DBData> mapElements = new ArrayList<DBData> ();

	/***
	 * Constructeur sans paramètre de Map()
	 * Crée le contour de la carte
	 */
	public Map()//dbMap dbMap)
	{
		for(int i = 0; i < WIDTH; i++)
			for(int j = 0; j < HEIGHT; j++)
				constructibles[i][j] = 0; // par défaut, que du sol, aucun bloc
		
		//Contour
		for(int i = 0; i < 27; i++)
		{
			constructibles[i][0] = 1; // 1 = indestructible
			constructibles[i][HEIGHT -1] = 1;
		}
		for(int j = 0; j < 19; j++)
		{
			constructibles[0][j] = 1;
			constructibles[WIDTH-1][j] = 1;
		}
	}
	/** Fonction qui récupère l'information sur la map dans la base de donnée
	 * 
	 * @param String: le nom de la base de données
	 * @return une liste de DBData (int x,int y, int data)
	 */
	/***
	 * Méthode permettant de créer le carte finale.
	 * @return -
	 * @param  -
	 */
	public void initMap()
	{
		mapElements = DBAccess.getMap("Bomberman", 1);
		for(int i = 0; i < mapElements.size(); i++)
			constructibles[mapElements.get(i).getX()][mapElements.get(i).getY()] 
					= mapElements.get(i).getData();
	}
	
	//GETTERS

	
	//SETTERS
	
}
