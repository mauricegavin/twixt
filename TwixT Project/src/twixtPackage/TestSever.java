package twixtPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 * Server made to test bug cases, se up to play moves out of a file
 * @author Jack
 *
 */
public class TestSever extends Thread{

	/**
	 * @param args
	 */
	private boolean online = false;
	Vector<String> oldMoves = new Vector<String>();
	Vector<PrintWriter> Observers = new Vector<PrintWriter>();
	ServerSocket servSock = null;
	int port;
	//public static void main(String[] args) {
	public TestSever(int newPort){
		port = newPort;
	}
	public void run(){
		Test test = new Test(true);
		java.net.InetAddress i = null;
		try {
			i = java.net.InetAddress.getLocalHost();
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		//Set up the window to display port and ip
		JFrame frame = new JFrame("Server");
		JLabel portLabel = new JLabel("Server is up on port "+port);
		JLabel ipLabel = new JLabel("IP is "+i.getHostAddress());
		Box box = Box.createVerticalBox();
		frame.getContentPane().add(box);
		box.add(ipLabel);
		box.add(portLabel);
		frame.pack();
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		//initialise variables
		Socket player1=null;
		PrintWriter p1Out = null;
		BufferedReader p1In = null;
		Socket player2=null;
		PrintWriter p2Out = null;
		BufferedReader p2In = null;
		String player1Name;
		String player2Name;
		String move;
		File f = new File("src/twixtPackage/OldMoves.txt");
		BufferedReader fileIn = null;
		try {
			fileIn = new BufferedReader(new FileReader(f));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		String testString;
		
		Vector<String> oldMoves = new Vector<String>();
		Vector<Socket> Observers = new Vector<Socket>();
		online=true;
		try{
		//wait for someone to connect
		servSock = new ServerSocket(port);
		player1 = servSock.accept();
		if(test.getDebugModeOn())System.out.println("p1 accepted connection");
		//tell them they are plaer1
		p1In = new BufferedReader(new InputStreamReader(player1.getInputStream()));
		p1Out=new PrintWriter(player1.getOutputStream(),true);
		if(test.getDebugModeOn())System.out.println("p1out: PL1");
		p1Out.println("PL3");
		//wait for someone else to connect
		//servSock = new ServerSocket(port);
		player2 = servSock.accept();
		if(test.getDebugModeOn())System.out.println("p2 accepted connection");
		//tell them they are player 2
		p2In = new BufferedReader(new InputStreamReader(player2.getInputStream()));
		p2Out=new PrintWriter(player2.getOutputStream(),true);
		if(test.getDebugModeOn())System.out.println("p2out: PL2");
		p2Out.println("PL3");
		player1Name = p1In.readLine();
		if(test.getDebugModeOn())System.out.println("p1in:"+player1Name);
		player2Name = p2In.readLine();
		if(test.getDebugModeOn())System.out.println("p2in:"+player2Name);
		//start the acceptor waiting for observers
		//tell p1 about p2
		if(test.getDebugModeOn())System.out.println("p1out:"+player2Name);
		p1Out.println(player2Name);
		//tell p2 about p1
		if(test.getDebugModeOn())System.out.println("p2out:"+player1Name);
		p2Out.println(player1Name);
		//wait for p1 to do move
		while(online){
			System.out.println("Online "+online);
			move=fileIn.readLine();
			while(!move.matches("EN1")){//keep reading from player 1 until they end thier turn
				if(test.getDebugModeOn())System.out.println("p1in:"+move);
				//send to p2 & obs
				if(test.getDebugModeOn())System.out.println("p2out:"+move);
				p2Out.println(move);
				p1Out.println(move);
				sendMoveToObs(move);
				oldMoves.add(move);
				move=fileIn.readLine();
			}
			if(test.getDebugModeOn())System.out.println("p1in:"+move);
			//send to p2 & obs
			if(test.getDebugModeOn())System.out.println("p2out:"+move);
			p2Out.println(move);
			p1Out.println(move);
			sendMoveToObs(move);
			oldMoves.add(move);
			//wait for p2 to do move
			move=fileIn.readLine();
			while(!move.matches("EN2")){//keep reading from player 2 until they end their turn
				if(test.getDebugModeOn())System.out.println("p2in:"+move);
				//send to p1 & obs
				if(test.getDebugModeOn())System.out.println("p1out:"+move);
				p1Out.println(move);
				p2Out.println(move);
				sendMoveToObs(move);
				oldMoves.add(move);
				move=fileIn.readLine();
			}
			if(test.getDebugModeOn())System.out.println("p2in:"+move);
			//send to p1 & obs
			if(test.getDebugModeOn())System.out.println("p1out:"+move);
			p1Out.println(move);
			p2Out.println(move);
			sendMoveToObs(move);
			oldMoves.add(move);
			//repeat from wait for p1 to do move until end of game or player disc
		}
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
	}
	public boolean isOnline(){
		return online;
	}
	public String getMove(int i){
		return oldMoves.elementAt(i);
	}
	public void addObserver(PrintWriter obs){
		this.Observers.add(obs);
	}
	public Socket accept() throws IOException{
			return servSock.accept();
	}
	private void sendMoveToObs(String move){
		if(!Observers.isEmpty()){
			for(int i = 0; i < Observers.capacity(); i++){
				Observers.elementAt(i).println(move);
			}
		}
	}
}
