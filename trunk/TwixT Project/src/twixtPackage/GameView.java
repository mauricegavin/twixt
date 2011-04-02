package twixtPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
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
	Test test = new Test(true);
	/**
	 * The main Gui Interface.<br>
	 */
	JFrame gameFrame = new JFrame("TwixT Extreme"); // Creates the main window
	Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize(); // Gets the resolution of the screen
    Dimension dimensionSuggested = new Dimension(800, 600); // Suggested window size
    Dimension dimensionMin = new Dimension(540, 620); // Minimum window size
	BoardView boardGraphics;
	Game game;
	Board board;
	HumanController player1;
	HumanController player2;
	AiController computer1;
	AiController computer2;
	
	// Additional Frames
	JFrame endGameFrame;
	
	// JLabels
	public JLabel consoleLabel = new JLabel("Good luck!");
	private JLabel textualButtonInformation = new JLabel("Would you like to play the PI Rule?");
	
	// Buttons
	private JButton piButtonAffirm = new JButton("Gimme you Towers");
	private JButton piButtonRegect = new JButton("No");
	private JButton endTurnButton = new JButton("End Turn");
	private JButton newGameButton = new JButton("New Game");
	
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
	GameView(Game _game, Board _board) // Constructor
	{
		game = _game;
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
		
		// Text Fields
		
		// Buttons

		
		// Component Initial Values
		
		// Component Initial States
		textualButtonInformation.setVisible(false);
		piButtonAffirm.setVisible(false);
		piButtonRegect.setVisible(false);
		endTurnButton.setEnabled(true);
		newGameButton.setVisible(false);

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
		endTurnPanel.add(newGameButton);
		
		//
		// Listeners
		//
		endTurnButton.addActionListener( new buttonClickListener(1));
		endTurnButton.addKeyListener( new buttonClickListener(1));
		piButtonAffirm.addActionListener( new buttonClickListener(2));
		piButtonRegect.addActionListener( new buttonClickListener(3));
		newGameButton.addActionListener(new buttonClickListener(4));

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
	
	public void addPlayer1Controller(HumanController p1){
		boardGraphics.addPlayer1Controller(p1);
		player1=p1;
	}
	public void addPlayer2Controller(HumanController p2){
		boardGraphics.addPlayer2Controller(p2);
		player2=p2;
	}
	public void addPlayer1Controller(AiController p1){
		boardGraphics.addPlayer1Controller(p1);
		computer1 = p1;
	}
	public void addPlayer2Controller(AiController p2){
		boardGraphics.addPlayer2Controller(p2);
		computer2 = p2;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		boardGraphics.repaint();
		
		if(game.canPlayPiRule())
		{
			showPiRuleOption(true);
		}
		else
		{
			showPiRuleOption(false);
		}
		
		if(game.gameIsOver() == 1)
		{
			showEndGame(1);
		}
		else if (game.gameIsOver() == 2)
		{
			showEndGame(2);
		}
		
		// Update console window with meaningful messages
		if(game.getPlayerTurn() == 1)
		{
			consoleLabel.setText("Player 1's Turn");
		}
		else if(game.getPlayerTurn() == 2)
		{
			consoleLabel.setText("Player 2's Turn");
		}
	}

	public void showPiRuleOption(Boolean state)
	{	
		if(state)
		{
			endTurnButton.setEnabled(false);
			textualButtonInformation.setVisible(true);
			piButtonAffirm.setVisible(true);
			piButtonRegect.setVisible(true);
		}
		else
		{
			endTurnButton.setEnabled(true);
			textualButtonInformation.setVisible(false);
			piButtonAffirm.setVisible(false);
			piButtonRegect.setVisible(false);
		}
	}

	void showEndGame(int whoWon)
	{
		endTurnButton.setVisible(false);
		newGameButton.setVisible(true);
		URL url = null;
		try {
			int rand = (int) (Math.random() * (3) ); // Generates a random in the range 0 to 2
			
			if(rand == 0)
				url = new URL("http://i246.photobucket.com/albums/gg100/vicgal/mz_4592812_bodyshot_300x400-39.gif");
			else if(rand == 1)
				url = new URL("http://i94.photobucket.com/albums/l108/diabolo_jo/125381shiningowned8gl9eqh9.gif");
			else if(rand == 2)
				url = new URL("http://www.threadbombing.com/data/media/2/winning.gif");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Icon icon = new ImageIcon(url);
		JLabel label = new JLabel(icon);
		 
		endGameFrame = new JFrame("Game Over");
		endGameFrame.setUndecorated(true);
		endGameFrame.getContentPane().add(label);
		endGameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		endGameFrame.pack();
		endGameFrame.setLocationRelativeTo(null);
		endGameFrame.setVisible(true);
	}
	
	// Implementation of the Listeners
	
	/**
	 * This Listener deals with 3 button cases.<br>
	 * <p>
	 * Case 1: End Turn. Ends the players turn.
	 */
	class buttonClickListener implements ActionListener, KeyListener
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
			case 1: // End Turn Case
				if(player1 != null){
					player1.endTurn();
				}
				if(player2 != null){
					player2.endTurn();
				}
				break;
			case 2: // Yes, implement PI Rule
				showPiRuleOption(false);
				if(player1!=null)player1.piRule();
				if(player2!=null)player2.piRule();
				break;
			case 3: // No, do not implement PI Rule
				showPiRuleOption(false);
				break;
			case 4: // Start New Game
				gameFrame.setVisible(false);
				endGameFrame.setVisible(false);
				game.startNewGame(true);
				break;
			default:
				break;
			}
		
		}

		// Thinking of putting in an Undo Function
		@Override
		public void keyPressed(KeyEvent key)
		{
			if (key.getKeyCode() == 8)
			{
				if(test.getDebugModeOn())System.out.println("Detected Backspace Key press.");
			}
			else if((key.getKeyCode() == 117) || (key.getKeyCode() == 85))
			{
				if(test.getDebugModeOn())System.out.println("Detected U Key press.");
			}
			else if((key.getKeyChar() == ' ') || (key.getKeyChar() == '\n'))
			{
				if(player1 != null){
					player1.endTurn();
				}
				if(player2 != null){
					player2.endTurn();
				}
				if(test.getDebugModeOn())System.out.println("Detected Return Key press.");
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}

