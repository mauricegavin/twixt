package twixtPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

import twixtPackage.SetupView.modeButtonListener;

/**
 * The GameView Class is responsible for the creation of a Graphical User Interface in which to play the game.<br>
 * <p>
 * You interact with the on screen board to add towers and add and remove bridges.<br>
 * Indicate that your own turn is finished.<br>
 * Plan ahead while waiting for the other player to move.<br>
 * A JLabel displays turn information.<br>
 * 
 * @author Maurice
 */

//The class is structured in the format:
//Constructor for main GUI
//Listeners for main GUI
//MenuBar Constructor
//Listeners for MenuBar
public class GameView
{		
	/**
	 * The main Gui Interface.<br>
	 */
	JFrame gameFrame = new JFrame("TwixT Extreme"); // Creates the main window
	Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize(); // Gets the resolution of the screen
    Dimension dimensionSuggested = new Dimension(800, 600); // Suggested window size
    Dimension dimensionMin = new Dimension(540, 600); // Minimum window size
	BoardView boardGraphics = new BoardView(dimensionMin);
	
	/**
	 * 
	 */
	GameView() // Constructor
	{
		//
		// Configuration of the Frame
		//
		gameFrame.setMinimumSize(dimensionMin); // Apply the minimum size to the frame
		gameFrame.setSize(dimensionSuggested); // Apply the suggested size to the frame
		gameFrame.setLocation((screenDimension.width - dimensionMin.width)/2, (screenDimension.height - dimensionMin.height)/4); // Set the location to the upper (Y), middle (X) portion of the screen.
		
		gameFrame.getContentPane().setLayout(new BorderLayout()); // Give the main frame a Border Layout  
		
		//
		// Main GUI Interface
		//
		
		//
		// Components
		//
		
		// Create JLabels. Fields which display text.
		JLabel consoleLabel = new JLabel("Good luck!");
		
		// Text Fields
		
		// Buttons
		JButton endTurnButton = new JButton("End Turn");
		
		// Component Initial Values
		
		// Component Initial States
		endTurnButton.setEnabled(false);

		//
		// Formatting 
		//
		
		// Variables
		
		// Apply Formatting
		
		//
		// Panels & Layout
		//
	
		JPanel gameViewPanel = new JPanel();
		gameViewPanel.setLayout(new BorderLayout());
		
		JPanel consolePanel = new JPanel();
		consolePanel.setLayout(new FlowLayout());
		
		JPanel endTurnPanel = new JPanel();
		endTurnPanel.setLayout(new FlowLayout());
		// Add Panels to the Frame
		
		gameFrame.add(gameViewPanel, BorderLayout.CENTER);
		
		gameViewPanel.add(consolePanel, BorderLayout.NORTH);
		gameViewPanel.add(endTurnPanel, BorderLayout.SOUTH);
		
		// Add Components to Panels
		
		gameViewPanel.add(boardGraphics, BorderLayout.CENTER);
		
		consolePanel.add(consoleLabel);
		endTurnPanel.add(endTurnButton);
		
		//
		// Listeners
		//

		//
		// End of main GUI Interface Configuration
		//
		
		//
		// Display Interface
		//
		gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		gameFrame.pack();
		gameFrame.setVisible(true);
	}
	
}

