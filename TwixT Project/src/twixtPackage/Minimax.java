package twixtPackage;

import java.util.Vector;

public class Minimax
{
	static Test test = new Test(true);
	private Game game;
	private Board testingBoard; // As opposed to the Game's mBoard (Master Board)
	private RuleMaster rules;
	private int densestRegion = -1;
	private int playerID = -1;
	
	Minimax(Game _game, int _playerID)
	{
		game = _game;
		playerID = _playerID;
		testingBoard = new Board(game.getBoard()); // Use copy constructor to get a full copy of the Board
		rules = new RuleMaster(testingBoard); // Create a new RuleMaster using this new RuleMaster
	}
	
	public int[] calculateBestMove(Game game, int playerID)
	{
		assert(playerID==1 || playerID==2): "Error: Invalid playerID";
		
		testingBoard = new Board(game.getBoard());
		double max = 0;
		double temp = 0;
		int x1 = 0, y1 = 0;
		densestRegion = detectDensestRegion();
		if(test.getDebugModeOn()) System.out.println("Densest Region is region " + densestRegion);
		
		for(int i = 0; i < 24; i++)
		{
			for(int j = 0; j < 24; j++)
			{
				temp = quantifyMove(i,j) + (Math.random()); // Get a value for how good the move is and add an element of randomness so that it isn't the same everytime.
				if ( (temp > max) && (game.getRuleMaster().canPlaceTower(i, j, playerID)) ) // If the move stored in temp is better than any previous move then keep it.
				{
					max = temp;
					x1 = i;
					y1 = j;
				}
			}
		}
		if(test.getDebugModeOn())System.out.printf("Best move for Player %d: \nScore: %g \nMove Co-ords: (%d, %d)",playerID,max, x1,y1);
		
		int[] optimalCoords = {x1,y1};
		return optimalCoords;
	}
	
	/**
	 * Determines the quality of move.
	 * 
	 * @return An double between 0 and 10, 0 being the worst and 10 being the best.
	 */
	private double quantifyMove(int x1, int y1)
	{
		assert(x1 > 23 || y1 > 23): "Error: X or Y out of bounds";
		double calcQuality = 0;
		
		if(isInDensestRegion(x1, y1))
			calcQuality += 0.05;
		
		calcQuality += rawPositionOnBoard(x1, y1, playerID);
		calcQuality += potentialToBuildABridge(x1, y1, playerID);
		
		return calcQuality;
	}
	
	private double rawPositionOnBoard(int x1, int y1, int playerID)
	{
		double weight = 0.05;
		double quality = 0;
		
		if(playerID == 1)
		{
			quality += Math.abs(Math.abs(x1-11.5)-12)/4; // Moves in the middle are valued most
			quality += Math.abs(Math.abs(y1-11.5)-12)/3; // Note the division by 3, not 4, we do not wish to penalise extremer moves on the Y-Axis
		}
		else // Player 2
		{
			quality += Math.abs(Math.abs(y1-11.5)-12)/4; // Moves in the middle are valued most
			quality += Math.abs(Math.abs(x1-11.5)-12)/3; // Note the division by 3, not 4, we do not wish to penalise extremer moves on the X-Axis
		}

		return quality*weight; // Scale it using its importance relative to other methods of quantification.
	}

	/**
	 * Calculates a score for a potential Tower placement depending on #Bridge connections.
	 * <p>
	 * @param x1 X coord of Tower
	 * @param y1 Y coord of Tower
	 * @param playerID The player's ID
	 * @return A weighted score of the moves quality with respect to Bridge Placement.
	 */
	private double potentialToBuildABridge(int x1, int y1, int playerID)
	{
		testingBoard.placeTower(x1, y1, playerID);
		int quality = 0;
		double weight = 0.4;
		
		int j = -2;
		while(j <= 2)
		{
			//if(test.getDebugModeOn()) System.err.println("j mod 2 is " +j%2);
			if (j%2 == 0)
			{ 	// If player is Player 1 then we want to encourage movement on the Y.
				// Therefore we reward these moves with extra 'quality'
				if(rules.canPlaceBridge(x1, y1, x1-1, y1+j, playerID, testingBoard))
				{
					if(playerID == 1) quality = quality + 2; // Reward player 1 for this type of move
					else quality++; // No additional bonus for player 2
				}
				if(rules.canPlaceBridge(x1, y1, x1+1, y1+j, playerID, testingBoard)) 
				{
					if(playerID == 1) quality = quality + 2;
					else quality++; 
				}
				//if(test.getDebugModeOn()) System.out.println(quality);
			}
			else
			{
				if(rules.canPlaceBridge(x1, y1, x1-2, y1+j, playerID, testingBoard))
				{
					if(playerID == 2) quality = quality + 2;
					else quality++; 
				}
				if(rules.canPlaceBridge(x1, y1, x1+2, y1+j, playerID, testingBoard))
				{
					if(playerID == 2) quality = quality + 2;
					else quality++; 
				}
			}
			j++;
			if(j == 0) // We want to skip the zero condition, as towers cannot connect with towers directly horizontal or vertical to themselves.
				j++;
		}

		if(test.getDebugModeOn() && (quality > 0) ) System.out.println("Potential to to place "+quality+" bridge(s).");
		testingBoard.removeTower(x1, y1, playerID); // We are finished testing with it and don't want it on our testing Board
		return quality*weight; // Scale it using its importance relative to other methods of quantification.
	}
	
	/**
	 * The function calculates a densest region on the board.<br>
	 * <p>
	 * The board is virtually split into 4 equal quadrants and the towers in each is summed.<br>
	 * The region with the most towers is said to be the densest.<br>
	 * 
	 * Legend:<br>
	 * 1 = Top Left<br>
	 * 2 = Top Right<br>
	 * 3 = Bottom Left<br>
	 * 4 = Bottom Right<br>
	 * @return Densest region
	 */
	private int detectDensestRegion()
	{
		int region = -1;
		int max = -1;
		int[] temp = new int[4];
		
		// Calculate the density of region 1
		for(int i = 0; i < (testingBoard.BOARDSIZE/2); i++){
			for(int j = 0; j < (testingBoard.BOARDSIZE/2); j++){
				if(testingBoard.getTower(i, j) != null) temp[0]++;
			}
		}
		
		if(temp[0] > max)
		{
			region = 1;
			max = temp[0];
		}
		
		// Calculate the density of region 2
		for(int i = (testingBoard.BOARDSIZE/2); i < testingBoard.BOARDSIZE; i++){
			for(int j = 0; j < (testingBoard.BOARDSIZE/2); j++){
				if(testingBoard.getTower(i, j) != null) temp[1]++;
			}
		}
		
		if(temp[1] > max)
		{
			region = 2;
			max = temp[1];
		}
		
		// Calculate the density of region 3
		for(int i = 0; i < (testingBoard.BOARDSIZE/2); i++){
			for(int j = (testingBoard.BOARDSIZE/2); j < testingBoard.BOARDSIZE; j++){
				if(testingBoard.getTower(i, j) != null) temp[2]++;
			}
		}
		
		if(temp[2] > max)
		{
			region = 3;
			max = temp[2];
		}
		
		// Calculate the density of region 4
		for(int i = (testingBoard.BOARDSIZE/2); i < testingBoard.BOARDSIZE; i++){
			for(int j = (testingBoard.BOARDSIZE/2); j < testingBoard.BOARDSIZE; j++){
				if(testingBoard.getTower(i, j) != null) temp[3]++;
			}
		}
		
		if(temp[3] > max)
		{
			region = 4;
			max = temp[3];
		}
		
		if(region == -1)
		{
			if(test.getDebugModeOn()) System.out.println("Densest region is random");
			return (int) (Math.random()*4);
		}
		else
		{
			if(test.getDebugModeOn()) System.out.println("Densest region is "+region);
			return region;	
		}
	}

	private boolean isInDensestRegion(int x1, int y1)
	{
		if(x1 <= 11) // Lefthand side of the board
		{
			if(y1 <= 11)
			{
				if(densestRegion == 1)
					return true;
			}
			else
				if(densestRegion == 3)
					return true;
		}
		else // Righthand side of the board
		{
			if(y1 <= 11)
			{
				if(densestRegion == 2)
					return true;
			}
			else
				if(densestRegion == 4)
					return true;
		}
		return false;
	}
}
