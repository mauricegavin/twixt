package twixtPackage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

public class AiController implements Controller, Observer
{
	public Test test = new Test(true);
	private int myID;
	private Game mGame;
	private Minimax ai;
	
	/**
	 * Default constructor for AiController object
	 */
	AiController()
	{
		myID = 0;
		mGame = null;
		ai = null;
	}



	public AiController(Game game, int playerID)
	{
		mGame= game;
		myID = playerID;
		ai = new Minimax(game, myID);
	}
	
	public void run()
	{
		if(mGame.isMyGo(myID))
		{
			doMove();
		}
	}
	
	public void doMove()
	{
		if (mGame.getPlayerTurn() == myID)
		{
			if(test.getDebugModeOn())System.out.println("It is AI's turn to move. (ID "+myID+")");
			int[] suggestedMove = ai.calculateBestMove(mGame, myID);
			addTower(suggestedMove);
			mGame.endTurn(myID);
		}
	}

	private void removeBridge(char[] input){
		// TODO tell AI that it can remove bridges now
		//if(test.getDebugModeOn())System.out.println("removing bridge: "+x1+" "+y1+" "+x2+" "+y2+" player: "+myID);
		//mGame.removeBridge(x1, y1, x2, y2, myID);
	}
	private void addTower(int[] input){
		int x = input[0];
		int y = input[1];
		
		if(test.getDebugModeOn())System.out.println("Placing Tower: "+x+" "+y+" player: "+myID);
		mGame.placeTower(x, y, myID);
	}
	private void addBridge(char[] input){
		// TODO tell AI that you are ready to play bridges
		//if(test.getDebugModeOn())System.out.println("adding bridge: "+x1+" "+y1+" "+x2+" "+y2+" player: "+myID);
		//mGame.placeBridge(x1, y1, x2, y2, myID);
	}

	@Override
	public void update(Observable arg0, Object arg1)
	{
		doMove();
	}

	
}
