package twixtPackage;

import java.util.Observable;
import java.util.Observer;

public class Game extends Observable
{
	SetupView setupFrame;
	GameView gameFrame;
	private Board mBoard;
	private RuleMaster mRule;
	private int playerTurn=1;//integer representing whose turn it is 1 for player 1, 2 for player 2
	private int numTurns=0;//integer representing the number of turns that have happened
	private int turnStage=0;//integer between 0 to 3 representing which stage of a turn we are at, 0: pi rule, 1: removing bridges, 2: placing a tower or 3: placing bridges and the turn is ready to finish
	private int player1ID=1;
	private int player2ID=2;
	//private boolean hasPlayedPiRule=false;
	public Test test = new Test(true);
	
	public Game(Board board, RuleMaster ruleM)
	{
		mBoard = board;
		mRule = ruleM;
		//setupFrame = new SetupView(this);
		//createNewGame(true);
	}

	public void createNewGame(boolean state) 
	{
		if (state)
		{
			mBoard = new Board();
			mRule = new RuleMaster(mBoard);
			//gameFrame = new GameView(this, mBoard);
			this.addObserver(gameFrame);
		}
		else
			gameFrame = null;
	}
	
	/**
	 * This function controls the mechanics of the current game.<br>
	 * <p>
	 *  It takes accepts moves from the current played and logs the end of turns.<br>
	 */
	public void gameInstance()
	{
		while(mRule.detectEnd() == 0)
		{
			if(playerTurn == 1) // Player 1's Turn
			{
				if(turnStage == 0) // PI Rule
				{
					
				}
				else if(turnStage == 1) // Remove Bridges
				{
					
				}
				else if(turnStage == 2) // Add Towers
				{
					
				}
				else if(turnStage == 3) // Add Bridges
				{
					
				}
			} // End of Player 1 Turn
			else if(playerTurn == 2) // Player 2's Turn
			{
				if(turnStage == 0) // PI Rule
				{
					
				}
				else if(turnStage == 1) // Remove Bridges
				{
					
				}
				else if(turnStage == 2) // Add Towers
				{
					
				}
				else if(turnStage == 3) // Add Bridges
				{
					
				}
			} // End of Player 2 Turn
			numTurns++;
		} // End of While
	} // End of Function
		
	/**
	 * Method to remove a bridge
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @param playerNumber
	 */
	public void removeBridge(int x1, int y1, int x2, int y2, int playerNumber){
		if(turnStage<2){
			if(isMyGo(playerNumber)&&mRule.canRemoveBridge(x1, y1, x2, y2, getRealID(playerNumber)))//if it is the correct players turn and if the move is legal
			{
				mBoard.removeBridge(x1, y1, x2, y2, getRealID(playerNumber));
				turnStage=1;
			}else if(test.getDebugModeOn()){
				System.out.println("Game: removeBridge(): illegal move");
			}
		}else if(test.getDebugModeOn()){
		System.out.println("Game: removeBridge(): Wrong turnStage: ts "+turnStage);
		}
	}
	/**
	 * Method to place a tower
	 * @param x
	 * @param y
	 * @param playerNumber
	 */
	public void placeTower(int x, int y, int playerNumber){
		if(turnStage<3){
			if(isMyGo(playerNumber)&&mRule.canPlaceTower(x, y, getRealID(playerNumber)))//if it is the correct players turn and if the move is legal
			{
				mBoard.placeTower(x, y, getRealID(playerNumber));
				turnStage=3;
			}else if(test.getDebugModeOn()){
				System.out.println("Game: placeTower(): illegal move");
			}
		}else if(test.getDebugModeOn()){
		System.out.println("Game: placeTower(): Wrong turnStage: ts "+turnStage);
		}
	}
	/**
	 * Method to place a bridge
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @param playerNumber
	 */
	public void placeBridge(int x1, int y1, int x2, int y2, int playerNumber){
		if(turnStage==3){
			if(isMyGo(playerNumber)&&mRule.canPlaceBridge(x1, y1, x2, y2, getRealID(playerNumber)))//if it is the correct players turn and if the move is legal
			{
				mBoard.placeBridge(x1, y1, x2, y2, getRealID(playerNumber));
			}else if(test.getDebugModeOn()){
				System.out.println("Game: placeBridge(): illegal move");
			}
		}else if(test.getDebugModeOn()){
			System.out.println("Game: placeBridge(): Wrong turnStage: ts "+turnStage);
		}
	}
	/**
	 * Method to end your current turn
	 * @param playerNumber
	 */
	public void endTurn(int playerNumber){
		if(isMyGo(playerNumber)&&turnStage==3){
			turnStage=0;
			if(playerTurn==1){
				playerTurn=2;
			}else{
				playerTurn=1;
			}
			numTurns++;
		}else if(test.getDebugModeOn()){
			System.out.println("Game: endTurn(): Wrong turnStage/Wrong Player: ts "+turnStage+" playerturn "+playerTurn+" playernumber "+playerNumber);
		}
	}
	/**
	 * Method to do the pi rule, will only apply if it is the players turn, if the player has made no other moves this turn and if it is the second turn
	 * @param playerNumber
	 */
	public void piRule(int playerNumber){
		if(isMyGo(playerNumber)&&turnStage==0&&numTurns==1){
			player1ID=2;
			player2ID=1;
			turnStage=3;
			endTurn(playerNumber);
		}else if(test.getDebugModeOn()){
			System.out.println("Game: piRule(): illegal pi rule: ts "+turnStage+" numturns "+numTurns);
		}
	}
	/**
	 * Method to check and see if it is a players turn
	 * @param playerID 
	 * @return a boolean true if it is the specified payers turn
	 */
	public boolean isMyGo(int playerNumber){
		if(getRealID(playerNumber)==1)
		{
			return(playerTurn==player1ID);
		}	
		if(getRealID(playerNumber)==2)
		{
			return(playerTurn==player2ID);
		}
		return false;
	}
	/**
	 * Method which returns an integer representing which pieces on the board correspond to the given player number
	 * Because of the pi rule player number 1 (meaning the first player) may not have the playerID 1 in abstractions below the game object (board tower etc)
	 * where playerID 1 means left to right and playerID 2 mean up and down.
	 * @return an integer either 1 or 2, or 0 if the input was neither 1 or 2.
	 */
	public int getRealID(int playerNumber){
		if(playerNumber==1){
			return player1ID;
		}
		if(playerNumber==2){
			return player2ID;
		}
		return 0;
	}
	/**
	 * Returns the tower specified by x and y, if no tower is present returns null
	 * @param x
	 * @param y
	 * @return
	 */
	public Tower getTower(int x, int y){
		return mBoard.getTower(x, y);
	}
	/**
	 * Returns the bridge at index i, if there is no bridge at i returns null
	 * @param i
	 * @return
	 */
	public Bridge getBridge(int i){
		return mBoard.getBridge(i);
	}

}