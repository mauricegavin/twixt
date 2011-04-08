package twixtPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
/**
 * Class which is designed to run alongside the Server thread and accepts observers and connects them to the game
 * @author Jack
 *
 */
public class Acceptor extends Thread{
	private Server mServ;
	Test test = new Test(false);
	public Acceptor(Server server){
		mServ=server;
	}
	public void run(){
		Socket sock = null;
		PrintWriter obs = null;
		String move = null;
		int i;
		while(mServ.isOnline()){//While the server is online 
			try {
				sock = mServ.accept();//keep accepting sockets
				if(test.getDebugModeOn())System.out.println("Acceptor: New observer has joined the game");
				obs = new PrintWriter(sock.getOutputStream(),true);//grab the output to the socket
				obs.println("PL3");//Tells the observer they are player 3, and therefore an observer
				i=0;
				move = mServ.getMove(i);//get the first move played in the game (if it ha been played prior to the obs connecting)
				while(move!=null){//while there are still moves not read from the list of old moves
					if(test.getDebugModeOn())System.out.println("Acceptor: Sending old moves:"+move);
					obs.println(move);//keep sending those moves(in the correct order) to the newly connected observer
					i++;
					move = mServ.getMove(i);
				}
				mServ.addObserver(obs);//add the output to the new connection to a list of observers
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
