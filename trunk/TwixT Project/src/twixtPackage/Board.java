package twixtPackage;

import java.util.Vector;


public class Board {

	static int BOARDSIZE = 24;
	
	Tower board[][];
	
	//TODO board needs an vector of bridges
	Vector bridgeList = new Vector();
	
	
	/**
	 * Constructor for Board object<br>
	 * Initialises the board to a 2d array of <b>Towers</b>
	 */
	public Board()
	{
		board = new Tower[BOARDSIZE][BOARDSIZE];
		for(int i = 0; i < BOARDSIZE; i++)
		{
			for(int j = 0; j < BOARDSIZE; j++)
			{
				board[i][j] = null;
			}
		}
	}		
	
	public void placeTower(int x, int y, int owner)
	{
		Tower tower = new Tower(x, y, owner);
		board[x][y] = tower;
	}
	
	public void removeTower(int x, int y, int owner)
	{
		board[x][y] = null;
	}
	
	public Tower getTower(int x, int y)
	{
		return board[x][y];
	}
	
	/**
	 * getBridge function returns a bridge between 2 sets of coordinates.<br>
	 * If the Tower doesn't exist then <code>null</code> is returned.<br>
	 * 
	 * @param x1 The x coordinate of the first tower
	 * @param y1 The y coordinate of the first tower
	 * @param x2 The x coordinate of the second tower
	 * @param y2 The y coordinate of the second tower
	 */
	public Bridge getBridge(int x1, int y1, int x2, int y2)
	{
		
		// Needs to be looked at, incomplete
		return Bridge;
	}
	
	public Bridge getBridge(int x1, int y1, int x2, int y2, int id)
	{
		// Needs to be looked at, incomplete
		return Bridge; 
	}
	
	//public Boolean isBridgeHere(int x1, int y1, int x2, int y2);

}
