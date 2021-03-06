package twixtPackage;



	public class Tower {

	    // Variables declarations
	    private int playerId;
	    private int xCoordinate;
	    private int yCoordinate;


	    // Default Constructor
	    Tower()
	    {
	    	playerId = xCoordinate = yCoordinate = 0;
	    }

	    // Generic Constructor which takes parameters
	    Tower(int x, int y, int owner)
	    {
	    	assert(xCoordinate>=0&&xCoordinate<=23&&yCoordinate>=0&&yCoordinate<=23&&playerId>=1&&playerId<=2): "Error:  x/y value must be between 0 and 23, and player id must be 1 or 2 ";
	    	xCoordinate = x;
	    	yCoordinate = y;
	    	playerId = owner;
	    }
	    
	    // Functions
	   
	    int getPlayerId()
	    {
	        return playerId;
	    }
	    
	    int getX()
	    {
	        return xCoordinate;
	    }
	    
	    int getY()
	    {
	        return yCoordinate;
	    }
	    
	    void setX(int x){
	    	xCoordinate = x;
	    }
	    
	    void setY(int y){
	    	yCoordinate = y;
	    }
	    
	    void setPlayerID(int id){
	    	playerId = id;
	    }
	    /**
	     * Method to check to see if two towers are equivalent, uses x and y values for both towers. Does not compare player id.
	     * @param otherTower Tower to check against the current tower object
	     * @return A boolean, true if the two are equivalent, false otherwise
	     */
		public boolean compare(Tower otherTower) {
			return (otherTower.getX()==this.xCoordinate&&otherTower.getY()==this.yCoordinate);
		}
	    
	        /*
	        moveCount = moveCount + 1;                  // initial move count starts count at 0
	         * int playerID = 0;
	        //if pie rule has NOT been activated

	        if(!pieRule){

	        if(moveCount%2 == 0){
	            playerID = 2;
	        }
	        else
	            playerID = 1;

	        }

	        else{
	         if(moveCount = 1)
	         * playerID = 1;
	         if(moveCount


	        


	        return playerId;
	    }
	*/
	    }
	    /*
	    Boolean canPlaceTower(int xCoordinate, int yCoordinate)
	    {
	        boolean canPlace = false;

	        if(board[xCoordinate][yCoordinate] == 0){
	            canPlace = true;
	        }

	        return canPlace;


	    }
	    */
/*
	    void placeTower(int playerID, int xCoordinate, int yCoordinate)
	    {

	      if(canPlaceTower(xCoordinate, yCoordinate))
	      {
	      board[xCoordinate][yCoordinate] = playerId;
	      }

	      else
	          System.err.println("Error; Please chose another position");


	    }
*/
	    /*
	    void placeBridge(int playerID, int x1Coordinate, int y1Coordinate, int x2Coordinate, int y2Coordinate){


	                if((board[x1Coordinate][y1Coordinate] == playerID) && (board[x2Coordinate][y2Coordinate]==playerID)){

	                    

	             }
	       


	}
	*/
