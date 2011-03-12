package twixtPackage;

import java.util.Vector;


public class Board {

	//
	// Test Module
	//
	Test test = new Test(true);
	
	
	static int BOARDSIZE = 24;
	
	private Tower board[][];
	private Vector<Bridge> bridgeList = new Vector<Bridge>();
	
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
		if(x<0||x>23||y<0||y>23||owner<1||owner>2){
			System.err.println("Invalid input data for tower placement, board not updated");
		}else{
			Tower tower = new Tower(x, y, owner);
			board[x][y] = tower;
		}
	}
	
	public Boolean placeBridge(int x1, int y1, int x2, int y2, int owner)//TODO Board should not ask rulemaster, game object should
	{
//		if ( boss.canPlaceBridge(x1, y1, x2, y2, owner) )
//		{
			boolean add = bridgeList.add(new Bridge(getTower(x1, y1), getTower(x2, y2), owner));
			if (add)
				return true;
			else
			{
				System.err.printf("Error: Cannot add Bridge (x1=%d y1=%d x2=%d y2=%d owner=%d) to bridgeList\n", x1,y1,x2,y2,owner);
				return false;
			}
//		}
//		else 
//		{
//			System.out.printf("Illegal Move: Cannot place Bridge (x1=%d y1=%d x2=%d y2=%d owner=%d\n)", x1,y1,x2,y2,owner);
//			return false;
//		}
	}
	
	public void removeTower(int x, int y, int owner)//we need this method? isn't this an illegal move?
	{
		if(x<0||x>23||y<0||y>23||owner<1||owner>2){
			System.err.println("Invalid input data for tower removal, board not updated");
		}else{
			board[x][y] = null;
		}
	}
	/**
	 * Removes the bridge specified by the input parameters from the board, does nothing if the bridge os not present on the board
	 * @param x1 x coordinate of the start of the bridge
	 * @param y1 y coordinate of the start of the bridge 
	 * @param x2 x coordinate of the end of the bridge
	 * @param y2 y coordinate of the end of the bridge
	 * @param owner the player who owns the bridge
	 */
	public void removeBridge(int x1, int y1, int x2, int y2, int owner)
	{
		if(x1<0||x1>23||y1<0||y1>23||x2<0||x2>23||y2<0||y2>23||owner<1||owner>2){
			System.err.println("Invalid input data for bridge removal, board not updated");
		}else{
			Bridge b = getBridge(x1, y1, x2, y2, owner);// b is the bridge to be removed
			bridgeList.remove(b);//then remove b from the bridgeList
		}
	}
	
	public Tower getTower(int x, int y)
	{
		if(x<0||x>23||y<0||y>23){
			return null;
		}else{
			return board[x][y];
		}
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
	 * getBridge method which returns the bridge at the specified index
	 * @param i the index of the bridge to be returned
	 * @return A bridge object
	 */
	public Bridge getBridge(int i)
	{
		if(i<0||bridgeList.isEmpty()){
			return null;
		}else if(i<bridgeList.size()){
			return bridgeList.get(i);
		}
		return null;
	}
	/**
	 * getBridge function which returns a bridge object regardless of player id.
	 * If the bridge doesn't exist then returns null.
	 * @param x1 The x coordinate of the first tower
	 * @param y1 The y coordinate of the first tower
	 * @param x2 The x coordinate of the second tower
	 * @param y2 The y coordinate of the second tower
	 */
	public Bridge getBridge(int x1, int y1, int x2, int y2)//TODO try updating to make use of the compare method in tower
	{
		int i = 0;//counter
		Bridge currentBridge;
		Tower start;//one end of the bridge
		Tower end;//the other end of the bridge
		while(i!=bridgeList.size()){//iterate thru the list and check all the bridges against the one we're looking for
			currentBridge=(Bridge) bridgeList.elementAt(i);
			start = currentBridge.getStart();
			if((start.getX()==x1)&&(start.getY()==y1)){//if the start of the bridge matches one of the xy pairs...
				end = currentBridge.getEnd();
				if(end.getX()==x2&&end.getY()==y2){//...check the end of the bridge against the xy pair
					return currentBridge;
				}
			}else if(start.getX()==x2&&start.getY()==y2){//if the start of the bridge matches one of the xy pairs...
				end = currentBridge.getEnd();
				if(end.getX()==x1&&end.getY()==y1){//...check the end of the bridge against the xy pair
					return currentBridge;
				}
			}
			i++;
		}
		return null;
	}
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
	/**
	 * Method to return a copy of the current vector of Bridges
	 * @return a Vector of Bridge Objects
	 */
	public Vector<Bridge> getBridgeList(){
		return (Vector<Bridge>) this.bridgeList.clone();
	}
	
}