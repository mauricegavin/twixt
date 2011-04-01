package twixtPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Acceptor extends Thread{
	private Server mServ;
	public Acceptor(Server server){
		mServ=server;
	}
	public void run(){
		Socket sock = null;
		PrintWriter obs = null;
		String move = null;
		int i;
		while(mServ.isOnline()){
			try {
				sock = mServ.accept();
				obs = new PrintWriter(sock.getOutputStream());
				i=0;
				move = mServ.getMove(i);
				while(move!=null){
					obs.println(move);
					i++;
					move = mServ.getMove(i);
				}
				mServ.addObserver(obs);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
