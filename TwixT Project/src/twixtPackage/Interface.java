package twixtPackage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
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
public class Interface
{		
	/**
	 * The main Gui Interface.<br>
	 */
	JFrame mainFrame = new JFrame("TwixT Extreme"); // Creates the main window
	Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize(); // Gets the resolution of the screen
    Dimension dimensionSuggested = new Dimension(800, 600); // Suggested window size
    Dimension dimensionMin = new Dimension(500, 280); // Minimum window size
 
 // These two text areas are used frequently by Reset, and Encryption listeners.
 // For this reason they have been declared outside the Interface object's constructor.
	JTextArea inputField = new JTextArea(2, 25); // Write input
	JTextArea outputField = new JTextArea(2, 25); // Read outputs
	
	JScrollPane inputScrollPane = new JScrollPane(inputField); // Scroll pane to the right of the inputField
	JScrollPane outputScrollPane = new JScrollPane(outputField); // Scroll pane to the right of the outputField
	
	char inputFieldLastChar = ' '; // This char stores the final character in the inputField, so that it can differentiate between letters (encrypt-able inputs) and non letters (non encryt-able inputs), so as to remove output from the output window sensibly.
	
	/**
	 * 
	 */
	Interface() // Constructor
	{
		//
		// Configuration of the Frame
		//
		mainFrame.setMinimumSize(dimensionMin); // Apply the minimum size to the frame
		mainFrame.setSize(dimensionSuggested); // Apply the suggested size to the frame
		mainFrame.setLocation((screenDimension.width - dimensionMin.width)/2, (screenDimension.height - dimensionMin.height)/4); // Set the location to the upper (Y), middle (X) portion of the screen.
		
		mainFrame.getContentPane().setLayout(new BorderLayout()); // Give the main frame a Border Layout  

					
		//
		// Main GUI Interface
		//
		
		//
		// Components
		//
		
		// Create JLabels. Uneditable fields which store text.	
		
	
		// Set properties of the input and output fields.

		
		//
		// Panels & Layout
		//

		
		// The panel which holds the input field

		
		// The panel which holds the output field

		
		// Add Panels to the Frame



		// Add Components to Panels
		

		//
		// Listeners
		//
		
		
		//
		// End of main GUI Interface Configuration
		//
		
		//
		// Display Interface
		//
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	
}

