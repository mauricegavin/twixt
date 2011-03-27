package twixtPackage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.UnknownHostException;

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
		ServerSocket servSock;
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
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
