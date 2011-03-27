package twixtPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Server {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int port =4444;
		ServerSocket servSock = null;
		Socket player1=null;
		Socket player2=null;
		Vector<Socket> Observers = new Vector<Socket>();
		try {
			servSock = new ServerSocket(port);
		} catch (IOException e) {
			System.err.println("Server failed to host on port "+port);
			e.printStackTrace();
		}
		try {
			java.net.InetAddress i = java.net.InetAddress.getLocalHost();
			JFrame frame = new JFrame("Server");
			JLabel portLabel = new JLabel("Server is up on port "+port);
			JLabel ipLabel = new JLabel("IP is "+i.getHostAddress());
			Box box = Box.createVerticalBox();
			frame.getContentPane().add(box);
			box.add(ipLabel);
			box.add(portLabel);
			frame.pack();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			PrintWriter out = new PrintWriter(servSock.accept().getOutputStream(),true);
			System.out.println("Server accepted connection");
			Thread.sleep(10000);
			System.out.println("Beginning output");
			out.println("APX12Y12P1");
			out.println("ENP1");
			out.println("APX13Y15P1");
			out.println("ABX12Y12X13Y15P1");
			out.println("RBX12Y12X13Y15P1");
			out.println("PI");
			out.println("FNP1");
			out.println("ENP1");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
