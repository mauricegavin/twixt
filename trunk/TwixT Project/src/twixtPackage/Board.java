package twixtPackage;

import java.util.Vector;


public class Board {

	//
	// Test Module
	//
	Test test = new Test(true);
	
	
	static int BOARDSIZE = 24;
	
	Tower board[][];
	RuleMaster boss = new RuleMaster();
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
		
		bridgeList.clear();
	}		
	
	public void placeTower(int x, int y, int owner)
	{
		Tower tower = new Tower(x, y, owner);
		board[x][y] = tower;
	}
	
	public Boolean placeBridge(int x1, int y1, int x2, int y2, int owner)
	{
		if ( boss.canPlaceBridge(x1, y1, x2, y2, owner) )
		{
			boolean add = bridgeList.add(new Bridge(getTower(x1, y1), getTower(x2, y2), owner));
			if (add)
				return true;
			else
			{
				System.err.printf("Error: Cannot add Bridge (x1=%d y1=%d x2=%d y2=%d owner=%d) to bridgeList\n", x1,y1,x2,y2,owner);
				return false;
			}
		}
		else 
		{
			System.out.printf("Illegal Move: Cannot place Bridge (x1=%d y1=%d x2=%d y2=%d owner=%d\n)", x1,y1,x2,y2,owner);
			return false;
		}
	}
	
	public void removeTower(int x, int y, int owner)
	{
		board[x][y] = null;
	}
	
	public void removeBridge(int x1, int y1, int x2, int y2, int owner)
	{
		
		
	}
	
	public Tower getTower(int x, int y)
	{
		return board[x][y];
	}
	/*
	/**
	 * getBridge function returns a bridge between 2 sets of coordinates.<br>
	 * If the Tower doesn't exist then <code>null</code> is returned.<br>
	 * 
	 * @param x1 The x coordinate of the first tower
	 * @param y1 The y coordinate of the first tower
	 * @param x2 The x coordinate of the second tower
	 * @param y2 The y coordinate of the second tower
	 * @return Returns the Bridge at this point, otherwise null
	 */
	/*
	public Bridge getBridge(int x1, int y1, int x2, int y2)
	{
		int index = bridgeList.indexOf( new Bridge(getTower(x1,y1), getTower(x2,y2), id) );
		if (index <= 0)
		{
			Bridge found = (Bridge) bridgeList.get(index);
			if (found.getID() == id)
				return found;
			else
				if(test.getDebugModeOn() == true) System.out.printf("Bridge doesn't belong to this player %d",id);
				return null;
		}
		else if (index == -1) {
			// Bridge not found
			return null;
		}
		else {
			System.err.printf("Error: Unexpected index=%d in getBridge function", index);
			return null;
		}
	}
	*/
	
	/**
	 * getBridge function returns a bridge between 2 sets of coordinates.<br>
	 * If the Tower doesn't exist then <code>null</code> is returned.<br>
	 * 
	 * @param x1 The x coordinate of the first tower
	 * @param y1 The y coordinate of the first tower
	 * @param x2 The x coordinate of the second tower
	 * @param y2 The y coordinate of the second tower
	 * @param id The player who we think owns the Bridge
	 * @return Returns the Bridge at this point if it belongs to id, otherwise null.
	 */
	public Bridge getBridge(int x1, int y1, int x2, int y2, int id)
	{
		int index = bridgeList.indexOf( new Bridge(getTower(x1,y1), getTower(x2,y2), id) );
		if (index >= 0)
		{
			Bridge found = (Bridge) bridgeList.get(index);
			return found;
		}
		else if (index == -1) {
			
			if(test.getDebugModeOn() == true){
				System.out.printf("This Bridge doesn't exist, or it doesn't belong to player %d\n", id);
				if(id == 1)
					index = bridgeList.indexOf( new Bridge(getTower(x1,y1), getTower(x2,y2), 2) );
				else
					index = bridgeList.indexOf( new Bridge(getTower(x1,y1), getTower(x2,y2), 1) );
				if (index == -1) // If it is still -1 then the bridge doesn't exist at all.
					System.out.printf("This Bridge does not exist\n");
				else
					System.out.printf("This Bridge belongs to player %d\n", index);
			}
			return null;
		}
		else {
			System.err.printf("Error: Unexpected index=%d in getBridge function\n", index);
			return null;
		}
	}
	
}