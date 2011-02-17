package twixtPackage;


public class Board {

	static int BOARDSIZE = 24;
	
	Tower board[][];
	
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
	
	public Bridge getBridge(int x, int y)
	{
		// Needs to be looked at, incomplete
		return Bridge; 
	}
	
	//public Boolean isBridgeHere(int x1, int y1, int x2, int y2);

}
