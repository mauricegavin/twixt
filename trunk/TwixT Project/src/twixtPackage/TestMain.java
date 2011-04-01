package twixtPackage;

import java.awt.Dimension;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Just a little class for testing purposes so i can muck about with what we have so far
 * @author Jack
 *
 */
public class TestMain {
	static Test test = new Test(false);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestSever serv = new TestSever(4444);
		serv.start();
	}
	private static void testCanPlaceBridge(int x1,int y1,int x2,int y2,int pID,RuleMaster rm){
		if(test.getDebugModeOn()){
			System.out.println("---------------------------------------------");
			System.out.println("Testing canPlaceBridge");
			System.out.println("Can place "+pID+"bridge between "+x1+" "+y1+" "+x2+" "+y2+" :"+rm.canPlaceBridge(x1, y1, x2, y2, pID));
			System.out.println("---------------------------------------------");
		}
	}
	private static void testCanPlaceTower(int x, int y, int pID, RuleMaster rm){
		if(test.getDebugModeOn()){	
			System.out.println("Testing player "+pID+" canPlaceTower @:"+x+" "+y+" "+rm.canPlaceTower(x, y, pID));
		}
	}
	private static void testPlaceTower(int x, int y, int pID, Board b){
		b.placeTower(x, y, pID);
		if(b.getTower(x, y)!=null){
			if(test.getDebugModeOn()){
			System.out.println("Tower found placed at :"+b.getTower(x, y).getX()+", "+b.getTower(x, y).getY());}
		}
	}
	private static void testPlaceBridge(int x1,int y1,int x2,int y2,int pID, Board b){
		b.placeBridge(x1, y1, x2, y2, pID);
		if(b.getBridge(x1, y1, x2, y2)!=null){
			if(test.getDebugModeOn()){
				System.out.println("bridge found placed @ "+x1+" "+y1+" "+x2+" "+y2);}
		}else{
			if(test.getDebugModeOn()){
			System.out.println("bridge NOT found placed @ "+x1+" "+y1+" "+x2+" "+y2);}
		}
	}

}
