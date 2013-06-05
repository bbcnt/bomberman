package game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Map {
	
	DBAccess dbAccess;
	this.dbAccess = new DBAccess("jdbc:sqlite:Midas.sqlite3");
	
	public static final int WIDTH = 27;
	public static final int HEIGHT = 19;
	
	public static final int TILE_SIZE = 30;
	
	private int[][]     data           = new int[WIDTH][HEIGHT];
	private boolean[][] destructible   = new boolean[WIDTH][HEIGHT];
	private boolean[][] indestructible = new boolean[WIDTH][HEIGHT];
	//private boolean[][] bonus = new Bonus[WIDTH][HEIGHT];

	public Map()//dbMap dbMap)
	{
		for(int i = 0; i < 27; i++)
		{
			for(int j = 0; j < 19; j++)
			{
				data[i][j] = 0;
				destructible[i][j] = false;
				indestructible[i][j] = false;
			}
		}
	}
	
	public void initMap()
	{
		int var = 0;
		for(int x= 0; x < 27; x++)
		{
			for(int y = 0; y < 19; y++)
			{
				//data[x][y] = récupérer le x, y de la base de données.
				data[x][y] = var++ % 2;
				
				if(data[x][y] == 0)
				{
					indestructible[x][y] = false;
					
				}
				else	
				{
					indestructible[x][y] = true;
				}
			}
		}
	}
	
	//GETTERS
	public int getData(int x, int y)
	{
		return data[x][y];
	}
	public boolean getDestructible(int x, int y)
	{
		return destructible[x][y];
	}
	public boolean getIndestructible(int x, int y)
	{
		return indestructible[x][y];
	}
	
	//SETTERS
	public void setData(int x, int y, int i)
	{
		data[x][y] = i;
	}
	public void setDestructible(int x, int y, boolean b)
	{
		destructible[x][y] = b;
	}
	public void setIndestructible(int x, int y, boolean b)
	{
		indestructible[x][y] = b;
	}
	
	
}
