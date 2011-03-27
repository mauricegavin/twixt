package twixtPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

public class NetView implements Observer{
	private Socket mSocket;
	private PrintWriter out;
	private int myID;
	private Game mGame;
	private boolean endTurn = false;//true if i have played a move but not yet sent an end turn string
	public Test test = new Test(true);
	
	public NetView(Socket sock, Game game, int playerID){
		mSocket = sock;
		mGame = game;
		myID = playerID;
		mGame.addObserver(this);
		try {
			out = new PrintWriter(mSocket.getOutputStream(), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		//check to see if it is my turn
		//if(test.getDebugModeOn())System.out.println("NetView p"+myID+" checking to see if it is my go");
		String output;
		Bridge tempBridge;
		Tower tempTower;
		int num;
		if(mGame.isMyGo(myID)){
			//if(test.getDebugModeOn())System.out.println("NetView p"+myID+" it is my go");
			endTurn=true;//flag that denotes that you have taken a turn and will have to send the end turn string
			//check for PI rule
				if(mGame.getNumTurns()==1&&mGame.getRealID(myID)!=myID){//if it is the second go and is my go and the player id's are switched then pi rule has been played by me
					output="PI";
					if(test.getDebugModeOn())System.out.println(output);
					out.println(output);
				}
			//check to see if any bridges need to be removed,
				tempBridge = mGame.getLastRemovedBridge();
				if(tempBridge!=null){
					output = "RB";//if so send RB moves
					output = bridgeParser(output, tempBridge);
					if(test.getDebugModeOn())System.out.println(output);
					out.println(output);
					tempBridge=null;
				}
			//check to see if any tower have been placed
				tempTower = mGame.getLastTower();
				if(tempTower!=null){
					output = "AP";//if so send AP
					output = towerParser(output, tempTower);
					if(test.getDebugModeOn())System.out.println(output);
					out.println(output);
				}
			//check to see if any bridges have been placed
				tempBridge=mGame.getLastPlacedBridge();
				if(tempBridge!=null){
					output="AB";//if so send AB
					output = bridgeParser(output,tempBridge);
					if(test.getDebugModeOn())System.out.println(output);
					out.println(output);
				}
		}else if(endTurn){//if it is not my turn check to see if i need to end my turn
			//System.out.println("NetView p"+myID+" It is nt my go, sending endTurn");
			endTurn = false;//if end turn is true, then it means that i have taken a go recently but not yet sent the end turn string
			if(mGame.gameIsOver()==myID){//if i have ended the game
				output="FNP"+myID;//then send FN
				if(test.getDebugModeOn())System.out.println(output);
				out.println(output);
			}
			output = "EN"+myID;
			if(test.getDebugModeOn())System.out.println(output);
			out.println(output);//send the end turn string
		}
		//else do nothing
		
	}
	private String zeroStuffer(String output, int num){
		if(num<10){
			output = output+"0"+num;
		}else{
			output = output+num;
		}
		return output;
	}
	private String towerParser(String output, Tower t){
		int num;
		output = output +"X";
		num = t.getX();
		output=zeroStuffer(output,num);
		output = output+"Y";
		num=t.getY();
		output=zeroStuffer(output,num);
		output = output +"P"+myID;
		return output;
	}
	private String bridgeParser(String output, Bridge b){
		int num;
		output = output+"X";
		num = b.getStart().getX();
		output=zeroStuffer(output,num);
		output = output + "Y";
		num = b.getStart().getY();
		output=zeroStuffer(output,num);
		output = output +"X";
		num = b.getEnd().getX();
		output=zeroStuffer(output, num);
		output = output+"Y";
		num = b.getEnd().getY();
		output=zeroStuffer(output, num);
		output = output+"P"+myID;
		return output;
	}
}
