package twixtPackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class ResourceManager {

	private PlayMedia fx;
	
	ResourceManager()
	{
		try {
			fx = new PlayMedia();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	PlayMedia getAudioObject()
	{
		return fx;
	}
	
	static class PlayMedia
	{
		// SET UP
	     private File towerFile;
	     private File bridgeFile;
	     private AudioInputStream stream;
	     static Clip music;	// Clip music must be static. It is the sound file that will be played.
	     // If we later want to stop() that sound file we need to be able to manipulate that instance of Clip.
	     // If it is not static we have no way of retrieving it.
	     
	     /* NOTE: "throws Exception" is important for setup. Your program will NOT compile if
	      * this is not included. */
	     public PlayMedia() throws Exception
	     {
	         // FILE - part of set-up 
	    	towerFile = new File("src\\twixtPackage\\media\\placetower_succeed.wav");
	    	bridgeFile = new File("src\\twixtPackage\\media\\placebridge_succeed.wav");
	     }
	     
	     /* NOTE: "throws Exception" is important for starting. Your program will NOT compile if
	      * this is not included. */
	     public void play(int control) throws Exception 
	     {
	          // SET UP
	          if (control == 1) 
	        	  stream = AudioSystem.getAudioInputStream(towerFile);
	          else if (control == 2)
	        	  stream = AudioSystem.getAudioInputStream(bridgeFile);
	          
	          music = AudioSystem.getClip();
	          
	          // PLAY
	          
	          music.open(stream);
	          music.start();
	     }
	     
	     public void stop()
	     {
	          // STOP
	          
	          music.stop();
	          music.close();
	     }
	}
	
}
