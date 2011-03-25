package twixtPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Observable;
import java.util.Observer;

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
public class GameView implements Observer
{		
	/**
	 * The main Gui Interface.<br>
	 */
	JFrame gameFrame = new JFrame("TwixT Extreme"); // Creates the main window
	Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize(); // Gets the resolution of the screen
    Dimension dimensionSuggested = new Dimension(800, 600); // Suggested window size
    Dimension dimensionMin = new Dimension(540, 620); // Minimum window size
	BoardView boardGraphics;
	Board board;
	HumanController player1;
	HumanController player2;
	
	/**
	 * The GameView Class is responsible for the game screen for the game.<br>
	 * <p>
	 * From here the user plays the game.<br>
	 * They place and remove bridges and towers.<br>
	 * Indicate the end of their turn.<br>
	 * Play the PI Rule.<br>
	 * 
	 * @author Maurice
	 */
	GameView(Board _board) // Constructor
	{
		board = _board;
		boardGraphics = new BoardView(board, dimensionMin);
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
		JLabel textualButtonInformation = new JLabel("Would you like to play the PI Rule?");
		
		// Text Fields
		
		// Buttons
		JButton piButtonAffirm= new JButton("Gimme you Towers");
		JButton piButtonRegect = new JButton("No");
		JButton endTurnButton = new JButton("End Turn");
		
		// Component Initial Values
		
		// Component Initial States
		piButtonAffirm.setVisible(true);
		piButtonRegect.setVisible(true);
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
		
		endTurnPanel.add(textualButtonInformation);
		endTurnPanel.add(piButtonAffirm);
		endTurnPanel.add(piButtonRegect);
		endTurnPanel.add(endTurnButton);
		
		//
		// Listeners
		//
		endTurnButton.addActionListener( new buttonClickListener(1));

		//
		// End of main GUI Interface Configuration
		//
		
		//
		// Display Interface
		//
		endTurnButton.setEnabled(true);
		gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		gameFrame.pack();
		gameFrame.setVisible(true);
		showPiRuleOption(endTurnButton, piButtonAffirm, piButtonRegect);
	}	
	
	public void addPlayer1Controller(HumanController p1){
		boardGraphics.addPlayer1Controller(p1);
		player1=p1;
	}
	public void addPlayer2Controller(HumanController p2){
		boardGraphics.addPlayer2Controller(p2);
		player2=p2;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		boardGraphics.repaint();
	}
	
	public void showPiRuleOption(JButton endTurnButton, JButton piButtonAffirm, JButton piButtonRegect)
	{	
		endTurnButton.setEnabled(false);
		piButtonAffirm.setVisible(true);
		piButtonRegect.setVisible(true);
	}
	
	// Implementation of the Listeners
	
	/**
	 * This Listener deals with 3 button cases.<br>
	 * <p>
	 * Case 1: End Turn. Ends the players turn.
	 */
	class buttonClickListener implements ActionListener
	{
		int mode = -1;
		
		public buttonClickListener(int _mode)
		{
			mode = _mode;
		}

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			switch(mode)
			{
			case 1:
				if(player1 != null){
					player1.endTurn();
				}
				if(player2 != null){
					player2.endTurn();
				}
				break;
			case 2:
				break;
			case 3:
				break;
			}
		
		}
	}
}

