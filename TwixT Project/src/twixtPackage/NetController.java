package twixtPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class NetController extends Thread implements Controller{
	private int myID;
	private Game mGame;
	private Socket mSocket;
	private BufferedReader in;
	public Test test = new Test(true);
	public boolean connected=true;
	public NetController(Game game,Socket sock, int playerID){
		mGame= game;
		myID = playerID;
		mSocket = sock;
		try {
			in = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void run(){
		while(connected){
			String input = "DEFAULT";
			try {
				input = in.readLine();
				doMove(input);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			doMove(input);
		}
	}
	private void doMove(String input){
		input=input.toUpperCase();
		if(test.getDebugModeOn())System.out.println("Input from network: "+input);
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
			if(test.getDebugModeOn())System.out.println("End Turn, player: "+myID);
			mGame.endTurn(myID);
		}else if(inArray[0]=='F'){//end game/
			if(test.getDebugModeOn())System.out.println("End Game, player: "+myID);		
		}else if(inArray[0]=='P'){//pi rule
			if(test.getDebugModeOn())System.out.println("Pi rule, player: "+myID);
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
		if(test.getDebugModeOn())System.out.println("removing bridge: "+x1+" "+y1+" "+x2+" "+y2+" player: "+myID);
		mGame.removeBridge(x1, y1, x2, y2, myID);
	}
	private void addTower(char[] input){
		int x = toInt(input[3],input[4]);
		int y = toInt(input[6],input[7]);
		if(test.getDebugModeOn())System.out.println("Placin Tower: "+x+" "+y+" player: "+myID);
		mGame.placeTower(x, y, myID);
	}
	private void addBridge(char[] input){
		int x1 = toInt(input[3],input[4]);
		int y1 = toInt(input[6],input[7]);
		int x2 = toInt(input[9],input[10]);
		int y2 = toInt(input[12],input[13]);
		if(test.getDebugModeOn())System.out.println("adding bridge: "+x1+" "+y1+" "+x2+" "+y2+" player: "+myID);
		mGame.placeBridge(x1, y1, x2, y2, myID);
	}
}
