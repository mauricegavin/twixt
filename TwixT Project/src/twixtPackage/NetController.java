package twixtPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class NetController extends Thread implements Controller{
	private int myID;
	private Game mGame;
	private BufferedReader in;
	private boolean observer=false;
	public Test test = new Test(false);
	public boolean connected=true;
	public NetController(BufferedReader reader,Game game, int playerID){
		mGame= game;
		myID = playerID;
		if(reader!=null){
		in = reader;
		}
		
	}
	public void run(){
		while(connected){
			String input = "DEFAULT";
			if(observer||mGame.isMyGo(myID)){
				try {
					input = in.readLine();
					if(input!=null){
						//if(test.getDebugModeOn())System.out.println("NetController going to do move: "+input);
						doMove(input);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//doMove(input);
			}
		}
	}
	private void doMove(String input){
		input=input.toUpperCase();
		if(test.getDebugModeOn())System.out.println("NetController: Input from network: "+input);
		char[] inArray = input.toCharArray();
		if(inArray[0]=='R'){//remove bridge
			removeBridge(inArray);
		}else if(inArray[0]=='A'){//add tower or add bridge
			if(inArray[1]=='P'){//add tower(peg)
				addTower(inArray);
			}else if(inArray[1]=='B'){//add bridge
				addBridge(inArray);
			}
		}else if(inArray[0]=='E'){//end turn
			if(test.getDebugModeOn())System.out.println("NetController: End Turn, player: "+myID);
			mGame.endTurn(myID);
			if(observer){//if this client is running as an observer, then you have to switch the playerID of this object after ending a turn
				this.switchPlayerID();
			}
		}else if(inArray[0]=='F'){//end game/
			if(test.getDebugModeOn())System.out.println("NetController: End Game, player: "+myID);		
		}else if(inArray[0]=='P'){//pi rule
			if(test.getDebugModeOn())System.out.println("NetController: Pi rule, player: "+myID);
			mGame.piRule(myID);
		}
	}
	private int toInt(char msd, char lsd){
		int x = Integer.parseInt(msd+"");
		x = x*10+Integer.parseInt(lsd+"");
		return x;
	}
	private void removeBridge(char[] input){
		int x1 = toInt(input[3],input[4]);
		int y1 = toInt(input[6],input[7]);
		int x2 = toInt(input[9],input[10]);
		int y2 = toInt(input[12],input[13]);
		if(test.getDebugModeOn())System.out.println("NetController: removing bridge: "+x1+" "+y1+" "+x2+" "+y2+" player: "+myID);
		mGame.removeBridge(x1, y1, x2, y2, myID);
	}
	private void addTower(char[] input){
		int x = toInt(input[3],input[4]);
		int y = toInt(input[6],input[7]);
		if(test.getDebugModeOn())System.out.println("NetController: Placing Tower: "+x+" "+y+" player: "+myID);
		mGame.placeTower(x, y, myID);
	}
	private void addBridge(char[] input){
		int x1 = toInt(input[3],input[4]);
		int y1 = toInt(input[6],input[7]);
		int x2 = toInt(input[9],input[10]);
		int y2 = toInt(input[12],input[13]);
		if(test.getDebugModeOn())System.out.println("NetController: adding bridge: "+x1+" "+y1+" "+x2+" "+y2+" player: "+myID);
		mGame.placeBridge(x1, y1, x2, y2, myID);
	}
	public void switchToFile(String fileName){
		//File f = new File(fileName);
		try {
			in = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setAsObserver(boolean bool){
		if(test.getDebugModeOn())System.out.println("NetController: Set as observer: "+bool);
		observer=bool;
	}
	private void switchPlayerID(){
		if(myID==1){
			myID=2;
		}else if(myID==2){
			myID=1;
		}
		if(test.getDebugModeOn())System.out.println("NetController: switchPlayer: Now controlling player "+myID);
	}
}
