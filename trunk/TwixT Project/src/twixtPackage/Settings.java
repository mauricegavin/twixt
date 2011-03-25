package twixtPackage;

import java.io.*;
import java.util.*;

public class Settings{
	
	private static String initialPlayer1Name;
	private static String initialPlayer1Type;
	private static String initialPlayer2Name;
	private static String initialPlayer2Type;
	private static String initialTurnCounter;
	private static String initialBoardSize;
	private static String initialScreenWidth, initialScreenHeight;
	private static String initialIP;
	private static String initialPort;
	private static String initialHost;
	
	private static String player1Name;
	private static String player1Type;
	private static String player2Name;
	private static String player2Type;
	private static String turnCounter;
	private static String boardSize;
	private static String screenWidth, screenHeight;
	private static String ip;
	private static String port;
	private static String host;
	
	
	private static String fileName1 = ("initialSettings.ini");
	private static String fileName2 = ("updatedSettings.ini");

	//private static String file1 = "initialSettings.ini";
	//private static String file2 = "updatedSettings.ini";
	
	public Settings(){
		
		initialPlayer1Name = "Kelly";
		initialPlayer1Type = "0";				//0 for human, 1 for computer
		initialPlayer2Name = "Maurice/Jack";
		initialPlayer2Type = "1";
		initialTurnCounter = "0";
		initialBoardSize = "24";
		initialScreenWidth = "25.5";
		initialScreenHeight = "25.5";
		initialIP = "127.0.0.1";
		initialPort = "8888";
		initialHost = "0";						//0 for local, 1 for network
		
		player1Name = "Kelly";
		player1Type = "0";
		player2Name = "Maurice/Jack";
		player2Type = "1";
		turnCounter = "0";
		boardSize = "24";
		screenWidth = "25.5";
		screenHeight = "25.5";
		ip = "127.0.0.1";
		port = "8888";
		host = "1";
		
	}
	
	public static void main (String args[]){
		
		//String cwd = System.getProperty("user.dir");
		//System.err.println("The correct path is: "+cwd+System.getProperty("file.separator")+fileName1);
		
		//String fileName1 = (cwd+System.getProperty("file.separator")+file1);
		//String fileName2 = (cwd+System.getProperty("file.separator")+file2);
		
		Settings ini1 = new Settings();
		ini1.setInitialPlayer1Name(initialPlayer1Name);
		ini1.setInitialPlayer1Type(initialPlayer1Type);
		ini1.setInitialPlayer2Name(initialPlayer2Name);
		ini1.setInitialPlayer2Type(initialPlayer2Type);
		ini1.setInitialTurnCounter(initialTurnCounter);
		ini1.setInitialBoardSize(initialBoardSize);
		ini1.setInitialScreenWidth(initialScreenWidth);
		ini1.setInitialScreenHeight(initialScreenHeight);
		ini1.setInitialIP(initialIP);
		ini1.setInitialPort(initialPort);
		ini1.setInitialHost(initialHost);
		ini1.getInitialPlayer1Name();
		ini1.getInitialPlayer1Type();
		ini1.getInitialPlayer2Name();
		ini1.getInitialPlayer2Type();
		ini1.getInitialTurnCounter();
		ini1.getInitialBoardSize();
		ini1.getInitialScreenWidth();
		ini1.getInitialScreenHeight();
		ini1.getInitialIP();
		ini1.getInitialPort();
		ini1.getInitialHost();
		
		
		Settings ini2 = new Settings();
		ini2.updatePlayer1Name(player1Name);
		ini2.updatePlayer1Type(player1Type);
		ini2.updatePlayer2Name(player2Name);
		ini2.updatePlayer2Type(player2Type);
		ini2.updateTurnCounter(turnCounter);
		ini2.updateBoardSize(boardSize);
		ini2.updateScreenWidth(screenWidth);
		ini2.updateScreenHeight(screenHeight);
		ini2.updateIP(ip);
		ini2.updatePort(port);
		ini2.updateHost(host);
		ini2.getUpdatedPlayer1Name();
		ini2.getUpdatedPlayer1Type();
		ini2.getUpdatedPlayer2Name();
		ini2.getUpdatedPlayer2Type();
		ini2.getUpdatedTurnCounter();
		ini2.getUpdatedBoardSize();
		ini2.getUpdatedScreenWidth();
		ini2.getUpdatedScreenHeight();
		ini2.getUpdatedIP();
		ini2.getUpdatedPort();
		ini2.getUpdatedHost();
		
		
		
	}
	
	
	@SuppressWarnings("deprecation")
	public void setInitialPlayer1Name(String initialPlayer1Name){
		
		try{
		Properties initialSettings = new Properties();
		initialSettings.load(new FileInputStream(fileName1));
		initialSettings.list(System.out);
		initialSettings.put("player1Name", initialPlayer1Name);
		FileOutputStream out = new FileOutputStream(fileName1);
		initialSettings.save(out, "initial Properties");
		}
		catch(Exception e){
			System.err.println(e);
			System.err.println("error at setInitialPlayer1Name");
		}
		

	}
@SuppressWarnings("deprecation")
public void setInitialPlayer1Type(String initialPlayer1Type){
		
		try{
		Properties initialSettings = new Properties();
		initialSettings.load(new FileInputStream(fileName1));
		initialSettings.list(System.out);
		initialSettings.put("player1Type", initialPlayer1Type);
		FileOutputStream out = new FileOutputStream(fileName1);
		initialSettings.save(out, "initial Properties");
		}
		catch(Exception e){
			System.err.println(e);
			System.err.println("error at setInitialPlayer1Type");
		}
		

	}
@SuppressWarnings("deprecation")
public void setInitialPlayer2Name(String initialPlayer2Name){
	
	try{
	Properties initialSettings = new Properties();
	initialSettings.load(new FileInputStream(fileName1));
	initialSettings.list(System.out);
	initialSettings.put("player2Name", initialPlayer2Name);
	FileOutputStream out = new FileOutputStream(fileName1);
	initialSettings.save(out, "initial Properties");
	}
	catch(Exception e){
		System.err.println(e);
		System.err.println("error at setInitialPlayer2Name");
	}
	

}
@SuppressWarnings("deprecation")
public void setInitialPlayer2Type(String initialPlayer2Type){
	
	try{
	Properties initialSettings = new Properties();
	initialSettings.load(new FileInputStream(fileName1));
	initialSettings.list(System.out);
	initialSettings.put("player2Type", initialPlayer2Type);
	FileOutputStream out = new FileOutputStream(fileName1);
	initialSettings.save(out, "initial Properties");
	}
	catch(Exception e){
		System.err.println(e);
		System.err.println("error at setInitialPlayer2Type");
	}
	

}
	
	@SuppressWarnings("deprecation")
	public void setInitialTurnCounter(String initialTurnCounter){
		try{
		Properties initialSettings = new Properties();
		initialSettings.load(new FileInputStream(fileName1));
		initialSettings.list(System.out);
		initialSettings.put("turnCounter", initialTurnCounter);
		FileOutputStream out = new FileOutputStream(fileName1);
		initialSettings.save(out, "initial Properties");
		}
		catch(Exception e){
			System.err.println("error at setInitialTurnCounter");
		}
	}
	
	@SuppressWarnings("deprecation")
	public void setInitialBoardSize(String initialBoardSize){
		try{
		Properties initialSettings = new Properties();
		initialSettings.load(new FileInputStream(fileName1));
		initialSettings.list(System.out);
		initialSettings.put("boardSize", initialBoardSize);
		FileOutputStream out = new FileOutputStream(fileName1);
		initialSettings.save(out, "initial Properties");
		}
		catch(Exception e){
			System.err.println("error at setInitialBoardSize");
		}
	}
	
	@SuppressWarnings("deprecation")
	public void setInitialScreenWidth(String initialScreenWidth){
		try{
		Properties initialSettings = new Properties();
		initialSettings.load(new FileInputStream(fileName1));
		initialSettings.list(System.out);
		initialSettings.put("screenWidth", initialScreenWidth);
		FileOutputStream out = new FileOutputStream(fileName1);
		initialSettings.save(out, "initial Properties");
		}
		catch(Exception e){
			System.err.println("error at setInitialScreenWidth");
		}
	}
	
	@SuppressWarnings("deprecation")
	public void setInitialScreenHeight(String initialScreenHeight){
		try{
		Properties initialSettings = new Properties();
		initialSettings.load(new FileInputStream(fileName1));
		initialSettings.list(System.out);
		initialSettings.put("screenHeight", initialScreenHeight);
		FileOutputStream out = new FileOutputStream(fileName1);
		initialSettings.save(out, "initial Properties");
		}
		catch(Exception e){
			System.err.println("error at setInitialScreenHeight");
		}
	}
	
	@SuppressWarnings("deprecation")
	public void setInitialIP(String initialIP){
		try{
		Properties initialSettings = new Properties();
		initialSettings.load(new FileInputStream(fileName1));
		initialSettings.list(System.out);
		initialSettings.put("ip", initialIP);
		FileOutputStream out = new FileOutputStream(fileName1);
		initialSettings.save(out, "initial Properties");
		}
		catch(Exception e){
			System.err.println("error at setInitialIP");
		}
	}
	
	@SuppressWarnings("deprecation")
	public void setInitialPort(String initialPort){
		try{
		Properties initialSettings = new Properties();
		initialSettings.load(new FileInputStream(fileName1));
		initialSettings.list(System.out);
		initialSettings.put("port", initialPort);
		FileOutputStream out = new FileOutputStream(fileName1);
		initialSettings.save(out, "initial Properties");
		}
		catch(Exception e){
			System.err.println("error at setInitialPort");
		}
	}
	@SuppressWarnings("deprecation")
	public void setInitialHost(String initialHost){
		try{
		Properties initialSettings = new Properties();
		initialSettings.load(new FileInputStream(fileName1));
		initialSettings.list(System.out);
		initialSettings.put("host", initialHost);
		FileOutputStream out = new FileOutputStream(fileName1);
		initialSettings.save(out, "initial Properties");
		}
		catch(Exception e){
			System.err.println("error at setInitialHost");
		}
	}

	
	@SuppressWarnings("deprecation")
	public void updatePlayer1Name(String player1Name){
		try{
		Properties updateSettings = new Properties();
		updateSettings.load(new FileInputStream(fileName2));
		updateSettings.list(System.out);
		updateSettings.put("player1Name", player1Name);
		FileOutputStream out = new FileOutputStream(fileName2);
		updateSettings.save(out, "updated Properties");
		}
		catch(Exception e){
			System.err.println("error at updatePlayer1Name");
		}
	}
	@SuppressWarnings("deprecation")
	public void updatePlayer1Type(String playerType){
		try{
		Properties updateSettings = new Properties();
		updateSettings.load(new FileInputStream(fileName2));
		updateSettings.list(System.out);
		updateSettings.put("player1Type", player1Type);
		FileOutputStream out = new FileOutputStream(fileName2);
		updateSettings.save(out, "updated Properties");
		}
		catch(Exception e){
			System.err.println("error at updatePlayer1Type");
		}
	}
	@SuppressWarnings("deprecation")
	public void updatePlayer2Name(String player2Name){
		try{
		Properties updateSettings = new Properties();
		updateSettings.load(new FileInputStream(fileName2));
		updateSettings.list(System.out);
		updateSettings.put("player2Name", player2Name);
		FileOutputStream out = new FileOutputStream(fileName2);
		updateSettings.save(out, "updated Properties");
		}
		catch(Exception e){
			System.err.println("error at updatePlayer2Name");
		}
	}
	@SuppressWarnings("deprecation")
	public void updatePlayer2Type(String player2Type){
		try{
		Properties updateSettings = new Properties();
		updateSettings.load(new FileInputStream(fileName2));
		updateSettings.list(System.out);
		updateSettings.put("player2Type", player2Type);
		FileOutputStream out = new FileOutputStream(fileName2);
		updateSettings.save(out, "updated Properties");
		}
		catch(Exception e){
			System.err.println("error at updatePlayer2Type");
		}
	}
	
	@SuppressWarnings("deprecation")
	public void updateTurnCounter(String turnCounter){
		try{
		Properties updateSettings = new Properties();
		updateSettings.load(new FileInputStream(fileName2));
		updateSettings.list(System.out);
		updateSettings.put("turnCounter", turnCounter);
		FileOutputStream out = new FileOutputStream(fileName2);
		updateSettings.save(out, "updated Properties");
		}
		catch(Exception e){
			System.err.println("error at updateTurnCounter");
		}
	}
	
	@SuppressWarnings("deprecation")
	public void updateBoardSize(String boardSize){
		try{
		Properties updateSettings = new Properties();
		updateSettings.load(new FileInputStream(fileName2));
		updateSettings.list(System.out);
		updateSettings.put("boardSize", boardSize);
		FileOutputStream out = new FileOutputStream(fileName2);
		updateSettings.save(out, "updated Properties");
		}
		catch(Exception e){
			System.err.println("error at updateBoardSize");
		}
	}
	
	@SuppressWarnings("deprecation")
	public void updateScreenWidth(String screenWidth){
		try{
		Properties updateSettings = new Properties();
		updateSettings.load(new FileInputStream(fileName2));
		updateSettings.list(System.out);
		updateSettings.put("screenWidth", screenWidth);
		FileOutputStream out = new FileOutputStream(fileName2);
		updateSettings.save(out, "updated Properties");
		}
		catch(Exception e){
			System.err.println("error at updateScreenWidth");
		}
	}
	
	@SuppressWarnings("deprecation")
	public void updateScreenHeight(String screenHeight){
		try{
		Properties updateSettings = new Properties();
		updateSettings.load(new FileInputStream(fileName2));
		updateSettings.list(System.out);
		updateSettings.put("screenHeight", screenHeight);
		FileOutputStream out = new FileOutputStream(fileName2);
		updateSettings.save(out, "updated Properties");
		}
		catch(Exception e){
			System.err.println("error at updateScreenHeight");
		}
	}
	
	@SuppressWarnings("deprecation")
	public void updateIP(String ip){
		try{
		Properties updateSettings = new Properties();
		updateSettings.load(new FileInputStream(fileName2));
		updateSettings.list(System.out);
		updateSettings.put("ip", ip);
		FileOutputStream out = new FileOutputStream(fileName2);
		updateSettings.save(out, "updated Properties");
		}
		catch(Exception e){
			System.err.println("error at updateIP");
		}
	}
	@SuppressWarnings("deprecation")
	public void updatePort(String port){
		try{
		Properties updateSettings = new Properties();
		updateSettings.load(new FileInputStream(fileName2));
		updateSettings.list(System.out);
		updateSettings.put("port", port);
		FileOutputStream out = new FileOutputStream(fileName2);
		updateSettings.save(out, "updated Properties");
		}
		catch(Exception e){
			System.err.println("error at updatePort");
		}
	}
	@SuppressWarnings("deprecation")
	public void updateHost(String Host){
		try{
		Properties updateSettings = new Properties();
		updateSettings.load(new FileInputStream(fileName2));
		updateSettings.list(System.out);
		updateSettings.put("host", Host);
		FileOutputStream out = new FileOutputStream(fileName2);
		updateSettings.save(out, "updated Properties");
		}
		catch(Exception e){
			System.err.println("error at updateHost");
		}
	}

	public String getInitialPlayer1Name(){
		try{
			
			Properties initialSettings = new Properties();
			initialSettings.load(new FileInputStream(fileName1));
			player1Name = initialSettings.getProperty("player1Name");
			
		}
		catch(Exception e){
			System.err.println("Error at getInitialPlayer1Name");
		}
		
		return player1Name;
	}
	public String getInitialPlayer1Type(){
		try{
			
			Properties initialSettings = new Properties();
			initialSettings.load(new FileInputStream(fileName1));
			player1Type = initialSettings.getProperty("player1Type");
			
		}
		catch(Exception e){
			System.err.println("Error at getInitialPlayer1Type");
		}
		
		return player1Type;
	}
	public String getInitialPlayer2Name(){
		try{
			
			Properties initialSettings = new Properties();
			initialSettings.load(new FileInputStream(fileName1));
			player2Name = initialSettings.getProperty("player2Name");
			
		}
		catch(Exception e){
			System.err.println("Error at getInitialPlayer2Name");
		}
		
		return player2Name;
	}
	public String getInitialPlayer2Type(){
		try{
			
			Properties initialSettings = new Properties();
			initialSettings.load(new FileInputStream(fileName1));
			player2Type = initialSettings.getProperty("player2Type");
			
		}
		catch(Exception e){
			System.err.println("Error at getInitialPlayer2Type");
		}
		
		return player2Type;
	}


public int getInitialTurnCounter(){
	try{
		
		Properties initialSettings = new Properties();
		initialSettings.load(new FileInputStream(fileName1));
		turnCounter = initialSettings.getProperty("turnCounter");
		
	}
	catch(Exception e){
		System.err.println("Error at getInitialTurnCounter");
	}
	
	int turnCounter1 = Integer.parseInt(turnCounter);
	return turnCounter1;
}


public int getInitialBoardSize(){
	try{
		
		Properties initialSettings = new Properties();
		initialSettings.load(new FileInputStream(fileName1));
		boardSize = initialSettings.getProperty("boardSize");
		
	}
	catch(Exception e){
		System.err.println("Error at getInitialBoardSize");
	}
	
	int boardSize1 = Integer.parseInt(boardSize);
	return boardSize1;
}


public double getInitialScreenWidth(){
	try{
		
		Properties initialSettings = new Properties();
		initialSettings.load(new FileInputStream(fileName1));
		screenWidth = initialSettings.getProperty("screenWidth");
		
	}
	catch(Exception e){
		System.err.println("Error at getScreenWidth");
	}
	
	double screenWidth1 = Double.parseDouble(screenWidth);
	return screenWidth1;
}


public double getInitialScreenHeight(){
	try{
		
		Properties initialSettings = new Properties();
		initialSettings.load(new FileInputStream(fileName1));
		screenHeight = initialSettings.getProperty("screenHeight");
		
	}
	catch(Exception e){
		System.err.println("Error at getScreenHeight");
	}
	
	double screenHeight1 = Double.parseDouble(screenHeight);
	return screenHeight1;
}


public String getInitialIP(){
	try{
		
		Properties initialSettings = new Properties();
		initialSettings.load(new FileInputStream(fileName1));
		ip = initialSettings.getProperty("ip");
		
	}
	catch(Exception e){
		System.err.println("Error at getIP");
	}
	
	return ip;
}


public int getInitialPort(){
	try{
		
		Properties initialSettings = new Properties();
		initialSettings.load(new FileInputStream(fileName1));
		port = initialSettings.getProperty("port");
		
	}
	catch(Exception e){
		System.err.println("Error at getPort");
	}
	
	int port1= Integer.parseInt(port);
	return port1;
}
public int getInitialHost(){
	try{
		
		Properties initialSettings = new Properties();
		initialSettings.load(new FileInputStream(fileName1));
		host = initialSettings.getProperty("host");
		
	}
	catch(Exception e){
		System.err.println("Error at getHost");
	}
	
	int host1= Integer.parseInt(host);
	return host1;
}

	public String getUpdatedPlayer1Name(){
		try{
			
			Properties updatedSettings = new Properties();
			updatedSettings.load(new FileInputStream(fileName2));
			player1Name = updatedSettings.getProperty("player1Name");
			
		}
		catch(Exception e){
			System.err.println("Error at getUpdatedPlayer1Name");
		}
		
		return player1Name;
	}
	public String getUpdatedPlayer1Type(){
		try{
			
			Properties updatedSettings = new Properties();
			updatedSettings.load(new FileInputStream(fileName2));
			player1Type= updatedSettings.getProperty("player1Type");
			
		}
		catch(Exception e){
			System.err.println("Error at getUpdatedPlayer1Type");
		}
		
		return player1Type;
	}
	public String getUpdatedPlayer2Name(){
		try{
			
			Properties updatedSettings = new Properties();
			updatedSettings.load(new FileInputStream(fileName2));
			player2Name = updatedSettings.getProperty("player2Name");
			
		}
		catch(Exception e){
			System.err.println("Error at getUpdatedPlayer2Name");
		}
		
		return player2Name;
	}
	public String getUpdatedPlayer2Type(){
		try{
			
			Properties updatedSettings = new Properties();
			updatedSettings.load(new FileInputStream(fileName2));
			player2Type = updatedSettings.getProperty("player2Type");
			
		}
		catch(Exception e){
			System.err.println("Error at getUpdatedPlayer2Type");
		}
		
		return player2Type;
	}


public int getUpdatedTurnCounter(){
	try{
		
		Properties updatedSettings = new Properties();
		updatedSettings.load(new FileInputStream(fileName2));
		turnCounter = updatedSettings.getProperty("turnCounter");
		
	}
	catch(Exception e){
		System.err.println("Error at getUpdatedTurnCounter");
	}
	
	int turnCounter1 = Integer.parseInt(turnCounter);
	return turnCounter1;
}


public int getUpdatedBoardSize(){
	try{
		
		Properties updatedSettings = new Properties();
		updatedSettings.load(new FileInputStream(fileName2));
		boardSize = updatedSettings.getProperty("boardSize");
		
	}
	catch(Exception e){
		System.err.println("Error at getUpdatedBoardSize");
	}
	
	int boardSize1 = Integer.parseInt(boardSize);
	return boardSize1;
}


public double getUpdatedScreenWidth(){
	try{
		
		Properties updatedSettings = new Properties();
		updatedSettings.load(new FileInputStream(fileName2));
		screenWidth = updatedSettings.getProperty("screenWidth");
		
	}
	catch(Exception e){
		System.err.println("Error at getUpdatedScreenWidth");
	}
	
	double screenWidth1 = Double.parseDouble(screenWidth);
	return screenWidth1;
}


public double getUpdatedScreenHeight(){
	try{
		
		Properties updatedSettings = new Properties();
		updatedSettings.load(new FileInputStream(fileName2));
		screenHeight = updatedSettings.getProperty("screenHeight");
		
	}
	catch(Exception e){
		System.err.println("Error at getUpdatedScreenHeight");
	}
	
	double screenHeight1 = Double.parseDouble(screenHeight);
	return screenHeight1;
}


public String getUpdatedIP(){
	try{
		
		Properties updatedSettings = new Properties();
		updatedSettings.load(new FileInputStream(fileName2));
		ip = updatedSettings.getProperty("ip");
		
	}
	catch(Exception e){
		System.err.println("Error at getUpdatedIP");
	}
	
	return ip;
}


public int getUpdatedPort(){
	try{
		
		Properties updatedSettings = new Properties();
		updatedSettings.load(new FileInputStream(fileName2));
		port = updatedSettings.getProperty("port");
		
	}
	catch(Exception e){
		System.err.println("Error at getUpdatedPort");
	}
	
	int port1= Integer.parseInt(port);
	return port1;
}
public int getUpdatedHost(){
	try{
		
		Properties updatedSettings = new Properties();
		updatedSettings.load(new FileInputStream(fileName2));
		host = updatedSettings.getProperty("host");
		
	}
	catch(Exception e){
		System.err.println("Error at getUpdatedHost");
	}
	
	int host1= Integer.parseInt(host);
	return host1;
}
}



	
	