package twixtPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Vector;

public class Game extends Observable
{
	SetupView setupFrame;
	GameView gameFrame;
	private Board mBoard;
	private RuleMaster mRule;
	private ResourceManager resource;
	private int playerTurn=1;//integer representing whose turn it is 1 for player 1, 2 for player 2
	private int numTurns=0;//integer representing the number of turns that have happened
	private int turnStage=0;//integer between 0 to 3 representing which stage of a turn we are at, 0: pi rule, 1: removing bridges, 2: placing a tower or 3: placing bridges and the turn is ready to finish
	private int player1ID=1;
	private int player2ID=2;
	private int gameIsOver=-1;//negative means game is not yet over, 1 means player 1, 2 means player 2
	private Tower lastMoveTower = null;
	private Bridge lastPlacedBridge = null;
	private Bridge lastRemovedBridge = null;
	public Test test = new Test(false);
	
	public Game()
	{
		setupFrame = new SetupView(this);
		resource = new ResourceManager();
		//createNewGame(true,1,2,"Hocho", "Mocho", null, 0);
		//Minimax ai = new Minimax(this);
	}

	public void createNewGame(boolean state, int player1type, int player2type, String player1Name, String player2Name, String ip, int port) 
	{
		if (state)
		{
			mBoard = new Board();
			mRule = new RuleMaster(mBoard);
			gameFrame = new GameView(this, mBoard);
			this.addObserver(gameFrame);
			Socket sock = null;
			BufferedReader in = null;
			PrintWriter out =  null;
			//the following few lines should be uncommented to make use a file in the specified location to do moves
			//NetController n1 = new NetController(sock, this, 1);
			//NetController n2 = new NetController(sock, this, 2);
			//n1.switchToFile("src\\twixtPackage\\OldMoves.txt");
			//n2.switchToFile("src\\twixtPackage\\OldMoves.txt");
			//n1.start();
			//n2.start();
			//this.testEnd();
			
			//MAC Version 
			//the following few lines should be uncommented to make use a file in the specified location to do moves
			/*NetController n1 = new NetController(sock, this, 1);
			NetController n2 = new NetController(sock, this, 2);
			n1.switchToFile("src/twixtPackage/OldMoves.txt");
			n2.switchToFile("src/twixtPackage/OldMoves.txt");
			n1.start();
			n2.start();*/

			if(player2type == 3){ //then it is a network game
				try {
					sock = new Socket(ip,port);
					in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
					String playerNumber=in.readLine();
					out = new PrintWriter(sock.getOutputStream(),true);
					out.println("NM"+player1Name);
					if(playerNumber.matches("PL1")){
						player2type=3;
					}else if(playerNumber.matches("PL2")){
						player2type=player1type;
						player1type=3;
					}else if(playerNumber.matches("PL3")){//then we are an observer
						player1type=3;
						player2type=3;
						NetController n = new NetController(in, this, 1);
						n.setAsObserver(true);
						n.start();
						return;
					}
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			switch(player1type)
			{
				case 1:
					gameFrame.addPlayer1Controller(new HumanController(1,this));
					break;
				case 2:
					AiController computer1 = new AiController(this, 1);
					gameFrame.addPlayer1Controller(computer1);
					this.addObserver(computer1);
					this.setChanged(); // Let player kick the game off with their first move.
					this.notifyObservers();
					break;
				case 3:
					//if(player2type!=3){
					this.addObserver(new NetView(out,this,2));//if player 1 is a networked player, then we need to send player2's move to the server
					//}
					NetController n = new NetController(in, this, 1);
					n.start();
					break;
				default:
					break;
			}
			switch(player2type)
			{
				case 1:
					gameFrame.addPlayer2Controller(new HumanController(2,this));
					break;
				case 2:
					AiController computer2 = new AiController(this, 2);
					gameFrame.addPlayer1Controller(computer2);
					this.addObserver(computer2);
					break;
				case 3:
					//if(player1type!=3){
					this.addObserver(new NetView(out,this,1));//if player 2 is a networked player, then we need to send player1's move to the server
					//}
					NetController n = new NetController(in, this, 2);
					n.start();
					break;
				default:
					break;
			}
		}
		else
			gameFrame = null;
	}
	
	/**
	 * Method to remove a bridge
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @param playerNumber
	 * @return True if the move was played, false otherwise
	 */
	public boolean removeBridge(int x1, int y1, int x2, int y2, int playerNumber){
		if(turnStage<2){
			if(isMyGo(playerNumber)&&mRule.canRemoveBridge(x1, y1, x2, y2, getRealID(playerNumber)))//if it is the correct players turn and if the move is legal
			{
				mBoard.removeBridge(x1, y1, x2, y2, getRealID(playerNumber));//remove the bridge on the current board object
				turnStage=1;
				this.lastRemovedBridge=new Bridge(new Tower(x1,y1,getRealID(playerNumber)),new Tower(x2,y2,getRealID(playerNumber)), getRealID(playerNumber));//Update the parameters that tore the last played move
				this.setChanged();//update observers
				this.notifyObservers();
				return true;
			}else if(test.getDebugModeOn()){
				System.out.println("Game: removeBridge(): illegal move");
			}
		}else if(test.getDebugModeOn()){
		System.out.println("Game: removeBridge(): Wrong turnStage: ts "+turnStage);
		}
		return false;
	}
	/**
	 * Method to place a tower
	 * @param x
	 * @param y
	 * @param playerNumber
	 * @return True if the move was played, false otherwise
	 */
	public boolean placeTower(int x, int y, int playerNumber){
		if(turnStage<3){
			if(isMyGo(playerNumber)&&mRule.canPlaceTower(x, y, getRealID(playerNumber)))//if it is the correct players turn and if the move is legal
			{
				mBoard.placeTower(x, y, getRealID(playerNumber));//place the tower on the current board object
				turnStage=3;
				this.lastMoveTower=new Tower(x,y,getRealID(playerNumber));//Update the parameters that tore the last played move
				this.setChanged();//Update observers
				this.notifyObservers();
				return true;
			}else if(test.getDebugModeOn()){
				System.out.println("Game: placeTower(): illegal move");
			}
		}else if(test.getDebugModeOn()){
		System.out.println("Game: placeTower(): Wrong turnStage: ts "+turnStage);
		}
		return false;
	}
	/**
	 * Method to place a bridge
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @param playerNumber
	 * @return A boolean, true if the move was played false otherwise.
	 */
	public boolean placeBridge(int x1, int y1, int x2, int y2, int playerNumber){
		if(turnStage==3){
			if(isMyGo(playerNumber)&&mRule.canPlaceBridge(x1, y1, x2, y2, getRealID(playerNumber)))//if it is the correct players turn and if the move is legal
			{
				mBoard.placeBridge(x1, y1, x2, y2, getRealID(playerNumber));//Place the Bridge on the cirrent board object
				playSound(2);
				this.lastPlacedBridge=new Bridge(new Tower(x1,y1,getRealID(playerNumber)),new Tower(x2,y2,getRealID(playerNumber)), getRealID(playerNumber));//Update the parameters that tore the last played move
				this.setChanged();//Update the observers
				this.notifyObservers();
				return true;
			}else if(test.getDebugModeOn()){
				System.out.println("Game: placeBridge(): illegal move");
			}
		}else if(test.getDebugModeOn()){
			System.out.println("Game: placeBridge(): Wrong turnStage: ts "+turnStage);
		}
		return false;
	}
	/**
	 * Method to end your current turn
	 * @param playerNumber
	 */
	public void endTurn(int playerNumber){
		if(isMyGo(playerNumber)&&turnStage==3){//if it is my go, and i have at least placed a tower, then it is legal to end my turn
			playSound(1);
			turnStage=0;
			if(playerTurn==1){
				playerTurn=2;
			}else{
				playerTurn=1;
			}
			numTurns++;
			gameIsOver=mRule.detectEnd();
			if(gameIsOver>0){
				turnStage=4;
				if(test.getDebugModeOn())System.out.println("Game: Game is Over, Player "+gameIsOver+" has won");
			}
			if(test.getDebugModeOn())System.out.println("Game: Endturn(): Player "+playerNumber+" has ended their turn "+gameIsOver);
			this.lastMoveTower=null;//Clear all the last move parameters
			this.lastPlacedBridge=null;
			this.lastRemovedBridge=null;
			this.setChanged();
			this.notifyObservers();
		}else if(test.getDebugModeOn()){
			System.out.println("Game: endTurn(): Wrong turnStage/Wrong Player: ts "+turnStage+" playerturn "+playerTurn+" playernumber "+playerNumber);;
		}
	}
	/**
	 * Method to do the pi rule, will only apply if it is the players turn, if the player has made no other moves this turn and if it is the second turn
	 * @param playerNumber
	 * @return True if the move was played, false otherwise
	 */
	public boolean piRule(int playerNumber){
		if(isMyGo(playerNumber)&&turnStage==0&&numTurns==1){//if it is my turn, and there is only 1 turn taken, and it is turnStage zero than it is legal to play the pi rule
			player1ID=2;//switch the player ids
			player2ID=1;//switch the player ids
			turnStage=3;//set the turnstage
			this.setChanged();
			this.notifyObservers();
			return true;
		}else if(test.getDebugModeOn()){
			System.out.println("Game: piRule(): illegal pi rule: ts "+turnStage+" numturns "+numTurns);
		}
		return false;
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
	 * @return The specified tower object
	 */
	public Tower getTower(int x, int y){
		return mBoard.getTower(x, y);
	}
	/**
	 * Returns the bridge at index i, if there is no bridge at i returns null
	 * @param i
	 * @return the specified bridge object
	 */
	public Bridge getBridge(int i){
		return mBoard.getBridge(i);
	}
	/**
	 * Returns an integr greater than zero if the game has ended, 1 if player 1 has won, 2 if player 2 has won. 
	 * @return
	 */
	public int gameIsOver(){
		return gameIsOver;
	}
	/**
	 * Returns true if the pi rule has been played
	 * @return
	 */
	public boolean canPlayPiRule(){
		return(this.numTurns==1&&this.turnStage==0);
	}
	/**
	 * Returns a refernece to the specified bridge, null if it does not exist
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @param playerID
	 * @return
	 */
	public Bridge getBridge(int x1, int y1, int x2, int y2,int playerID) {
		return mBoard.getBridge(x1, y1, x2, y2, playerID);
	}

	public void startNewGame(boolean state)
	{
		if(state)
		{
			setupFrame = new SetupView(this);
		}
	}
	
	void playSound(int index)
	{
		try {
			resource.getAudioObject().play(index);
		} catch (Exception e) {
			System.err.println("Failed to play sound file.");
			e.printStackTrace();
		}
	}
	/**
	 * Return the last placed tower
	 * @return
	 */
	public Tower getLastTower(){
		Tower t = this.lastMoveTower;
		lastMoveTower = null;
		return t;
	}
	/**
	 * Returns the last bridge that has been placed
	 * @return
	 */
	public Bridge getLastPlacedBridge(){
		Bridge b = this.lastPlacedBridge;
		lastPlacedBridge = null;
		return b;
	}
	/**
	 * Returns the last bridge that has been removed
	 * @return
	 */
	public Bridge getLastRemovedBridge(){
		Bridge b = this.lastRemovedBridge;
		lastRemovedBridge = null;
		return b;
	}
	/**
	 * Returns the number of turns that have been played in the game so far
	 * @return
	 */
	public int getNumTurns(){
		return this.numTurns;
	}
	/**
	 * Returns an integer representing what stage of the turn it is: 0: pi rule, 1: removing bridges, 2: placing a tower or 3: placing bridges and the turn is ready to finish
	 * @return
	 */
	public int getTurnStage(){
		return turnStage;
	}
	/**
	 * Returns a reference to the current ruleMaster object
	 * @return
	 */
	public RuleMaster getRuleMaster(){
		return mRule;
	}
	/**
	 * Returns the player whos turn it is.
	 * @return
	 */
	public int getPlayerTurn(){
		return playerTurn;
	}	
	/**
	 * Returns a reference to the current board
	 * @return
	 */
	public Board getBoard(){
		return mBoard;
	}
}