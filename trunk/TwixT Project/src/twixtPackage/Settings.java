package twixtPackage;

import java.io.*;
import java.util.*;

public class Settings{
	
	private static String playerName;
	private static String turnCounter;
	private static String boardSize;
	private static String screenWidth, screenHeight;
	private static String ip;
	private static String port;
	private static String fileName1 = ("C:\\Users\\Kelly\\workspace\\TwixT Project\\src\\twixtPackage\\initialSettings.ini");
	private static String fileName2 = ("C:\\Users\\Kelly\\workspace\\TwixT Project\\src\\twixtPackage\\updatedSettings.ini");

	
	public Settings(){
		playerName = "Kelly";
		turnCounter = "0";
		boardSize = "24";
		screenWidth = "25.5";
		screenHeight = "25.5";
		ip = "127.0.0.1";
		port = "8888";
	}
	
	public static void main (String args[]){
		
		Settings ini = new Settings();
		ini.setInitialSettings();
		ini.getInitialSettings();
		Settings ini2 = new Settings();
		ini2.updateSettings(playerName, turnCounter,  boardSize, screenWidth, screenHeight, ip, port);
		ini2.getUpdatedSettings();
		
		
	}
	
	
	@SuppressWarnings("deprecation")
	public void setInitialSettings(){
		try{
			
		Properties initialSettings = new Properties();
		initialSettings.load(new FileInputStream(fileName1));
		initialSettings.list(System.out);
		initialSettings.put("playerName", "Kelly");
		initialSettings.put("turnCounter", "0");
		initialSettings.put("boardSize", "50");
		initialSettings.put("screenWidth", "25.5");
		initialSettings.put("screenHeight", "25.5");
		initialSettings.put("ip", "127.0.0.1");
		initialSettings.put("port", "8888");
		FileOutputStream out = new FileOutputStream(fileName1);
		initialSettings.save(out, "initial Properties");    //no idea what the 'deprecation' is about
		
		}
		catch(Exception e){
			System.err.println("Cris is a spa1");
		}
		
		
	}
	
	@SuppressWarnings("deprecation")
	public void updateSettings(String playerName, String turnCounter, String boardSize, String screenWidth, String screenHeight, String ip, String port){
		try{
			
			Properties updateSettings = new Properties();
			updateSettings.load(new FileInputStream(fileName2));
			updateSettings.list(System.out);
			updateSettings.put("playerName", playerName);
			updateSettings.put("turnCounter", turnCounter);
			updateSettings.put("boardSize", boardSize);
			updateSettings.put("screenWidth", screenWidth);
			updateSettings.put("screenHeight", screenHeight);
			updateSettings.put("ip", ip);
			updateSettings.put("port", port);
			FileOutputStream out = new FileOutputStream(fileName2);
			updateSettings.save(out, "updated Properties");    //no idea what the 'deprecation' is about
			
			}
			catch(Exception e){
				System.err.println(" Cris is a spa 2");
			}
			
		
	}
	
	public void getInitialSettings(){
		try{
			Properties initialSettings = new Properties();
			initialSettings.load(new FileInputStream(fileName1));
			playerName = initialSettings.getProperty("playerName");
		    turnCounter = initialSettings.getProperty("turnCounter");
		    boardSize = initialSettings.getProperty("boardSize");
		    screenWidth = initialSettings.getProperty("screenWidth");
	        screenHeight = initialSettings.getProperty("screenHeight");
	        ip = initialSettings.getProperty("ip");
		    port = initialSettings.getProperty("port");
		    
		    
		    
			int turnCounter1 = Integer.parseInt(turnCounter);
		    int boardSize1 = Integer.parseInt(boardSize);
		    double screenWidth1 = Double.parseDouble(screenWidth);
		    double screenHeight1 = Double.parseDouble(screenHeight);
		    int port1= Integer.parseInt(port);
		  
		}
		catch(Exception e){
			System.err.println("Cris is a spa3");
		}
	}
	
	public void getUpdatedSettings(){
		try{
			Properties updatedSettings = new Properties();
			updatedSettings.load(new FileInputStream(fileName2));
			playerName = updatedSettings.getProperty("playerName");
		    turnCounter = updatedSettings.getProperty("turnCounter");
		    boardSize = updatedSettings.getProperty("boardSize");
		    screenWidth = updatedSettings.getProperty("screenWidth");
	        screenHeight = updatedSettings.getProperty("screenHeight");
	        ip = updatedSettings.getProperty("ip");
		    port = updatedSettings.getProperty("port");
		    
		    
		    
			int turnCounter1 = Integer.parseInt(turnCounter);
		    int boardSize1 = Integer.parseInt(boardSize);
		    double screenWidth1 = Double.parseDouble(screenWidth);
		    double screenHeight1 = Double.parseDouble(screenHeight);
		    int port1= Integer.parseInt(port);
		  
		}
		catch(Exception e){
			System.err.println("Cris is a spa4"+e);
		}
	
}
}
	
	