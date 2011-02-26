package twixtPackage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 * The Interface Class is responsible for the creation of a primary Graphical User Interface.<br>
 * <p>
 * From here the user plays the game. They can connect to or host a games.<br>
 * 
 * @author Maurice
 */

//The class is structured in the format:
//Constructor for main GUI
//Listeners for main GUI
//MenuBar Constructor
//Listeners for MenuBar
public class SetupView
{		
	/**
	 * The settings and game setup screen.<br>
	 */
	JFrame setupFrame = new JFrame("Setup"); // Creates the main window
	Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize(); // Gets the resolution of the screen
    Dimension dimensionSuggested = new Dimension(800, 600); // Suggested window size
    Dimension dimensionMin = new Dimension(500, 280); // Minimum window size

	/**
	 * 
	 */
	SetupView() // Constructor
	{
		//
		// Configuration of the Frame
		//
		setupFrame.setMinimumSize(dimensionMin); // Apply the minimum size to the frame
		setupFrame.setSize(dimensionSuggested); // Apply the suggested size to the frame
		setupFrame.setLocation((screenDimension.width - dimensionMin.width)/2, (screenDimension.height - dimensionMin.height)/4); // Set the location to the upper (Y), middle (X) portion of the screen.
		
		setupFrame.getContentPane().setLayout(new FlowLayout()); // Give the main frame a Border Layout  

					
		//
		// Main GUI Interface
		//
		
		//
		// Components
		//
		
		// Create JLabels. Fields which store text.
		
		JLabel headingLabel1 = new JLabel("Game Type");
		
		JLabel headingLabel2 = new JLabel("Player Configuration");
		
		JLabel headingLabel3 = new JLabel("Network Settings");
		
		JLabel gameTypeLabel1 = new JLabel("Play Locally");
		JLabel gameTypeLabel2 = new JLabel("Play Online");
		
		JRadioButton gameTypeOption1 = new JRadioButton();
		JRadioButton gameTypeOption2 = new JRadioButton();
		
		//JLabel = new JLabel("");
		

		
		//
		// Panels & Layout
		//
		
		// This box will fill the frame for layout purposes.
		Box setupViewBox = new Box(0);

		// The panel which holds the game type
		JPanel gameTypePanel = new JPanel();
		gameTypePanel.setLayout(new BoxLayout(gameTypePanel, BoxLayout.PAGE_AXIS));
		
		// The panel which holds the player types
		JPanel playerTypePanel = new JPanel();
		playerTypePanel.setLayout(new BoxLayout(playerTypePanel, BoxLayout.PAGE_AXIS));
		
		// The panel which holds other options, currently unused.
		//JPanel optionsPanel = new JPanel();
		
		// The panel which holds the network settings
		JPanel networkSettingsPanel = new JPanel();
		networkSettingsPanel.setLayout(new BoxLayout(networkSettingsPanel, BoxLayout.PAGE_AXIS));

		// The panel which holds the start game button
		JPanel startGamePanel = new JPanel();
		gameTypePanel.setLayout(new BorderLayout());
		
		// Game Type Panel Sub Panels
		JPanel subGameTypeHeadingPanel = new JPanel();
		JPanel subGameTypePanel1 = new JPanel();
		subGameTypePanel1.setLayout(new FlowLayout());
		JPanel subGameTypePanel2 = new JPanel();
		subGameTypePanel2.setLayout(new FlowLayout());
		
		// Player Type Panel Sub Panels
		JPanel subPlayerTypeHeadingPanel = new JPanel();
		JPanel subPlayerTypePanel1 = new JPanel();
		subPlayerTypePanel1.setLayout(new FlowLayout());
			
			// Sub Player Type Panel 1 Panels
			JPanel subSubPlayerTypePanel1 = new JPanel();
			subPlayerTypePanel1.setLayout(new FlowLayout());
			JPanel subSubPlayerTypePanel1 = new JPanel();
			subPlayerTypePanel1.setLayout(new FlowLayout());
		
		// Add Panels to the Frame

		setupFrame.add(setupViewBox);
		
		setupViewBox.add(gameTypePanel);
		setupViewBox.add(new JSeparator(SwingConstants.VERTICAL));
		setupViewBox.add(playerTypePanel);
		setupViewBox.add(new JSeparator(SwingConstants.VERTICAL));
		setupViewBox.add(networkSettingsPanel);
		setupViewBox.add(new JSeparator(SwingConstants.VERTICAL));
		setupViewBox.add(startGamePanel);
		
		gameTypePanel.add(subGameTypeHeadingPanel);		
		gameTypePanel.add(subGameTypePanel1);
		gameTypePanel.add(subGameTypePanel2);
		
		playerTypePanel.add(subPlayerTypeHeadingPanel);		
		playerTypePanel.add(subPlayerTypePanel1);
		playerTypePanel.add(subPlayerTypePanel2);
		
		// Add Components to Panels
		
		// Game Type Panel
		subGameTypeHeadingPanel.add(headingLabel1);
		subGameTypePanel1.add(gameTypeLabel1);
		subGameTypePanel1.add(gameTypeOption1);
		subGameTypePanel2.add(gameTypeLabel2);
		subGameTypePanel2.add(gameTypeOption2);
		
		// Player Type Panel
		subPlayerTypeHeadingPanel.add(headingLabel1);
		subPlayerTypePanel1.add(gameTypeLabel1);
		subPlayerTypePanel1.add(gameTypeOption1);
		subPlayerTypePanel2.add(gameTypeLabel2);
		subPlayerTypePanel2.add(gameTypeOption2);
		
		// Network Options Panel
		subGameTypeHeadingPanel.add(headingLabel1);
		subGameTypePanel1.add(gameTypeLabel1);
		subGameTypePanel1.add(gameTypeOption1);
		subGameTypePanel2.add(gameTypeLabel2);
		subGameTypePanel2.add(gameTypeOption2);

		// Player Type Panel
		playerTypePanel.add(headingLabel2);
		
		// Network Settings Panel
		networkSettingsPanel.add(headingLabel3);
		
		// Start Panel
		
		
		//
		// Listeners
		//
		
		
		//
		// End of main GUI Interface Configuration
		//
		
		//
		// Display Interface
		//
		setupFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setupFrame.pack();
		setupFrame.setVisible(true);
	}
	
}
