package twixtPackage;



	public class Tower {

	    // Variables declarations
	    private int playerId;
	    private int[][] board;
	    private int xCoordinate;
	    private int yCoordinate;


	    Tower()
	    {

	    // Variable definitions

	    }

	    // Functions

	    int getPlayerId()
	    {
	        return playerId;
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
	    Boolean canPlaceTower(int xCoordinate, int yCoordinate)
	    {
	        boolean canPlace = false;

	        if(board[xCoordinate][yCoordinate] == 0){
	            canPlace = true;
	        }

	        return canPlace;


	    }

	    void placeTower(int playerID, int xCoordinate, int yCoordinate)
	    {

	      if(canPlaceTower(xCoordinate, yCoordinate))
	      {
	      board[xCoordinate][yCoordinate] = playerId;
	      }

	      else
	          System.err.println("Error; Please chose another position");


	    }

	    void placeBridge(int playerID, int x1Coordinate, int y1Coordinate, int x2Coordinate, int y2Coordinate){


	                if((board[x1Coordinate][y1Coordinate] == playerID) && (board[x2Coordinate][y2Coordinate]==playerID)){

	                    

	             }
	       


	}
}
