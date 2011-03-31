package twixtPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
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
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

/**
 * The SetupView Class is responsible for the setup screen for the game.<br>
 * <p>
 * From here the user configures the game.<br>
 * They choose whether to play Locally or Online.<br>
 * Give each player a type, be it Human or AI.<br>
 * And enter network settings.<br>
 * 
 * @author Maurice
 */

//The class is structured in the format:
// Global Class Variables
// Constructor for GUI
// Listeners for GUI
public class SetupView
{		
	/**
	 * Global settings and game setup screen.<br>
	 */
	Game parentObject;
	JFrame setupFrame = new JFrame("Game Setup"); // Creates the main window
	Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize(); // Gets the resolution of the screen
    Dimension dimensionSuggested = new Dimension(800, 600); // Suggested window size
    Dimension dimensionMin = new Dimension(500, 280); // Minimum window size
    Boolean local = null; // A Boolean which indicates whether the game is being played locally or over a network connection

    /**
     * The SetupView constructor displays the Game Setup window on screen.<br>
     * <p>
     * First it formats the window size, then creates some Components.<br>
     * Next the frame is populated with panels for formatting.<br>
     * Then the components are added to the relevant panels.<br>
     * Listeners are added to components and the frame is made visible.<br>
     */
	SetupView(Game instance) // Constructor
	{
		parentObject = instance;
		//
		// Configuration of the Frame
		//
		setupFrame.setMinimumSize(dimensionMin); // Apply the minimum size to the frame
		setupFrame.setSize(dimensionSuggested); // Apply the suggested size to the frame
		setupFrame.setLocation((screenDimension.width - dimensionMin.width)/3, (screenDimension.height - dimensionMin.height)/4); // Set the location to the upper (Y), middle (X) portion of the screen.
		
		setupFrame.getContentPane().setLayout(new BorderLayout()); // Give the main frame a Border Layout  

					
		//
		// Main GUI Interface
		//
		
		//
		// Components
		//
		
		// Create JLabels. Fields which display text.
		
		JLabel headingLabel1 = new JLabel("Game Type");
		
		JLabel headingLabel2 = new JLabel("Player Configuration");
		
		JLabel headingLabel3 = new JLabel("Network Settings");
		
		// Game Type Labels
		JLabel gtpgLabel1 = new JLabel("Play Locally");
		JLabel gtpgLabel2 = new JLabel("Play Online");
		
		// Player Type Labels
		JLabel ptp1HeadingLabel = new JLabel("Player 1");
		JLabel ptp1bLabel1 = new JLabel("Human");
		JLabel ptp1bLabel2 = new JLabel("AI   ");
		JLabel ptp2HeadingLabel = new JLabel("Player 2");
		JLabel ptp2bLabel1 = new JLabel("Human");
		JLabel ptp2bLabel2 = new JLabel("AI   ");
		
		// Network Settings Labels
		JLabel nspIpLabel = new JLabel("IP Address");
		JLabel nspPortLabel = new JLabel("Port");
		
		// Radio Buttons
		JRadioButton gtpgRadio1 = new JRadioButton();
		JRadioButton gtpgRadio2 = new JRadioButton();
		JRadioButton ptp1bRadio1 = new JRadioButton();
		JRadioButton ptp1bRadio2 = new JRadioButton();
		JRadioButton ptp2bRadio1 = new JRadioButton();
		JRadioButton ptp2bRadio2 = new JRadioButton();
		
		// Text Fields
		JTextField ptp1bTextField = new JTextField(10);
		JTextField ptp2bTextField = new JTextField(10);
		JTextField nspIpField = new JTextField(15);
		JTextField nspPortField = new JTextField(5);
		
		// Sliders
		JSlider ptp1bDifficultySlider = new JSlider(0, 5, 0);
		ptp1bDifficultySlider.setMajorTickSpacing(1);
		ptp1bDifficultySlider.setPaintTicks(true);
		ptp1bDifficultySlider.setSnapToTicks(true);
		//ptp1bDifficultySlider.createStandardLabels(1); // Put numbers under the difficulty
		//ptp1bDifficultySlider.setPaintLabels(true);
		
		JSlider ptp2bDifficultySlider = new JSlider(0, 5, 0);
		ptp2bDifficultySlider.setMajorTickSpacing(1);
		ptp2bDifficultySlider.setPaintTicks(true);
		ptp2bDifficultySlider.setSnapToTicks(true);
		//ptp2bDifficultySlider.createStandardLabels(1);
		//ptp2bDifficultySlider.setPaintLabels(true);
		
		// Buttons
		JButton startButton = new JButton("Start Game");
		
		// Button Groups
		ButtonGroup gtpgGroup = new ButtonGroup();
		gtpgGroup.add(gtpgRadio1);
		gtpgGroup.add(gtpgRadio2);
		
		ButtonGroup ptp1Group = new ButtonGroup();
		ptp1Group.add(ptp1bRadio1);
		ptp1Group.add(ptp1bRadio2);
		
		ButtonGroup ptp2Group = new ButtonGroup();
		ptp2Group.add(ptp2bRadio1);
		ptp2Group.add(ptp2bRadio2);
		
		// Component Initial Values
		ptp1bTextField.setText("Akatosh");
		ptp2bTextField.setText("Zenithar");
		
		// Component Initial States
		ptp1bRadio1.setEnabled(false);
		ptp1bRadio2.setEnabled(false);
		ptp2bRadio1.setEnabled(false);
		ptp2bRadio2.setEnabled(false);
		
		ptp1bTextField.setEnabled(false);
		ptp2bTextField.setEnabled(false);
		nspIpField.setEnabled(false);
		nspPortField.setEnabled(false);

		ptp1bDifficultySlider.setEnabled(false);
		ptp2bDifficultySlider.setEnabled(false);
		
		startButton.setEnabled(false);
		//
		// Formatting 
		//
		
		// Variables
		Color color = new Color(205, 11, 66);
		Border border = BorderFactory.createMatteBorder(20, 10, 20, 10, color); // Give the frame a 20 x 10 border		
		Font headingFont = new Font("Arial" , Font.BOLD, 14);
		
		// Apply Formatting
		headingLabel1.setFont(headingFont);
		headingLabel2.setFont(headingFont);
		headingLabel3.setFont(headingFont);
		
		//
		// Panels & Layout
		//
		
		// This box will fill the frame for layout purposes.
		Box setupViewBox = new Box(0);
		setupViewBox.setBorder(border);
		
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
		startGamePanel.setLayout(new FlowLayout());
		
		// Game Type Panel (gtp)
		JPanel gtpHeading = new JPanel();
		gtpHeading.setLayout(new FlowLayout());
		
		JPanel gtpGames = new JPanel();
		gtpGames.setLayout(new FlowLayout());
		
			// Game Type Panel Games (gtpg)
			JPanel gtpgLabels = new JPanel();
			gtpgLabels.setLayout(new BoxLayout(gtpgLabels, BoxLayout.PAGE_AXIS));
			JPanel gtpgRadio = new JPanel();
			gtpgRadio.setLayout(new BoxLayout(gtpgRadio, BoxLayout.PAGE_AXIS));
		
		// Player Type Panel (ptp)
		JPanel ptpHeading = new JPanel();
		ptpHeading.setLayout(new FlowLayout());
		
		JPanel ptpPlayer1 = new JPanel();
		ptpPlayer1.setLayout(new FlowLayout());
		
			// Player 1 Panel (ptp1)
			JPanel ptp1Heading = new JPanel();
			ptp1Heading.setLayout(new FlowLayout());
			JPanel ptp1Body = new JPanel();
			ptp1Body.setLayout(new BoxLayout(ptp1Body, BoxLayout.LINE_AXIS));
		
				// Player 1 Body (ptp1b)
				JPanel ptp1bLabels = new JPanel();
				ptp1bLabels.setLayout(new BoxLayout(ptp1bLabels, BoxLayout.PAGE_AXIS));
				JPanel ptp1bRadio = new JPanel();
				ptp1bRadio.setLayout(new BoxLayout(ptp1bRadio, BoxLayout.PAGE_AXIS));
				JPanel ptp1bOptions = new JPanel();
				ptp1bOptions.setLayout(new BoxLayout(ptp1bOptions, BoxLayout.PAGE_AXIS));
			
		JPanel ptpPlayer2 = new JPanel();
		ptpPlayer2.setLayout(new FlowLayout());
			
			// Player 2 Panel (ptp2)
			JPanel ptp2Heading = new JPanel();
			ptp2Heading.setLayout(new FlowLayout());
			JPanel ptp2Body = new JPanel();
			ptp2Body.setLayout(new BoxLayout(ptp2Body, BoxLayout.LINE_AXIS));

				// Player 2 Body (ptp2b)
				JPanel ptp2bLabels = new JPanel();
				ptp2bLabels.setLayout(new BoxLayout(ptp2bLabels, BoxLayout.PAGE_AXIS));
				JPanel ptp2bRadio = new JPanel();
				ptp2bRadio.setLayout(new BoxLayout(ptp2bRadio, BoxLayout.PAGE_AXIS));
				JPanel ptp2bOptions = new JPanel();
				ptp2bOptions.setLayout(new BoxLayout(ptp2bOptions, BoxLayout.PAGE_AXIS));
			
		// Network Settings Panel (nsp)
		JPanel nspHeading = new JPanel();
		nspHeading.setLayout(new FlowLayout());
			
		JPanel nspBody = new JPanel();
		nspBody.setLayout(new BoxLayout(nspBody, BoxLayout.PAGE_AXIS));
			JPanel nspbIP = new JPanel();
			nspbIP.setLayout(new FlowLayout());
			JPanel nspbPort = new JPanel();
			nspbPort.setLayout(new FlowLayout());
				
		// Start Panel (stp)
		// No additional panels needed
			
		// Add Panels to the Frame

		setupFrame.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.NORTH);
		setupFrame.add(setupViewBox, BorderLayout.CENTER);
		setupFrame.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.SOUTH);
		
		setupViewBox.add(gameTypePanel);
		setupViewBox.add(new JSeparator(SwingConstants.VERTICAL));
		setupViewBox.add(playerTypePanel);
		setupViewBox.add(new JSeparator(SwingConstants.VERTICAL));
		setupViewBox.add(networkSettingsPanel);
		setupViewBox.add(new JSeparator(SwingConstants.VERTICAL));
		setupViewBox.add(startGamePanel);
		
		gameTypePanel.add(gtpHeading);
		//gameTypePanel.add(new JSeparator(SwingConstants.HORIZONTAL));
		
		gameTypePanel.add(gtpGames);
			gtpGames.add(gtpgLabels);
			gtpGames.add(gtpgRadio);
			
		playerTypePanel.add(ptpHeading);
		//playerTypePanel.add(new JSeparator(SwingConstants.HORIZONTAL));
		
		playerTypePanel.add(ptpPlayer1);
			ptpPlayer1.add(ptp1Heading);
			ptpPlayer1.add(ptp1Body);
				ptp1Body.add(ptp1bLabels);
				ptp1Body.add(ptp1bRadio);
				ptp1Body.add(ptp1bOptions);
		//playerTypePanel.add(new JSeparator(SwingConstants.HORIZONTAL));
				
		playerTypePanel.add(ptpPlayer2);
			ptpPlayer2.add(ptp2Heading);
			ptpPlayer2.add(ptp2Body);
				ptp2Body.add(ptp2bLabels);
				ptp2Body.add(ptp2bRadio);
				ptp2Body.add(ptp2bOptions);
		
		networkSettingsPanel.add(nspHeading);
		//networkSettingsPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
		
		networkSettingsPanel.add(nspBody);
			nspBody.add(nspbIP);
			nspBody.add(nspbPort);
			
		// Add Components to Panels
		
		// Game Type Panel
		gtpHeading.add(headingLabel1);
		
		gtpgLabels.add(gtpgLabel1);
		gtpgLabels.add(gtpgLabel2);
		gtpgRadio.add(gtpgRadio1);
		gtpgRadio.add(gtpgRadio2);
				
		// Player Type Panel
		ptpHeading.add(headingLabel2);
		
		ptp1Heading.add(ptp1HeadingLabel);
		ptp1bLabels.add(ptp1bLabel1);
		ptp1bLabels.add(ptp1bLabel2);
		ptp1bRadio.add(ptp1bRadio1);
		ptp1bRadio.add(ptp1bRadio2);
		ptp1bOptions.add(ptp1bTextField);
		ptp1bOptions.add(ptp1bDifficultySlider);
		
		ptp2Heading.add(ptp2HeadingLabel);
		ptp2bLabels.add(ptp2bLabel1);
		ptp2bLabels.add(ptp2bLabel2);
		ptp2bRadio.add(ptp2bRadio1);
		ptp2bRadio.add(ptp2bRadio2);
		ptp2bOptions.add(ptp2bTextField);
		ptp2bOptions.add(ptp2bDifficultySlider);		
		
		// Network Settings Panel
		nspHeading.add(headingLabel3);
		
		nspbIP.add(nspIpLabel);
		nspbIP.add(nspIpField);
		nspbPort.add(nspPortLabel);
		nspbPort.add(nspPortField);
		
		// Start Panel
		startGamePanel.add(startButton);
		
		//
		// Listeners
		//
		gtpgRadio1.addActionListener(new modeButtonListener(0, 	gtpgRadio1, ptp1bRadio1, ptp1bRadio2, ptp1bTextField, ptp1bDifficultySlider, 
																ptp2bRadio1, ptp2bRadio2, ptp2bTextField, ptp2bDifficultySlider,
																nspIpField, nspPortField, startButton));
		gtpgRadio2.addActionListener(new modeButtonListener(1, 	gtpgRadio1, ptp1bRadio1, ptp1bRadio2, ptp1bTextField, ptp1bDifficultySlider, 
																ptp2bRadio1, ptp2bRadio2, ptp2bTextField, ptp2bDifficultySlider,
																nspIpField, nspPortField, startButton));
		ptp1bRadio1.addActionListener(new modeButtonListener(2, gtpgRadio1, ptp1bRadio1, ptp1bRadio2, ptp1bTextField, ptp1bDifficultySlider, 
																ptp2bRadio1, ptp2bRadio2, ptp2bTextField, ptp2bDifficultySlider,
																nspIpField, nspPortField, startButton));
		ptp1bRadio2.addActionListener(new modeButtonListener(3, gtpgRadio1, ptp1bRadio1, ptp1bRadio2, ptp1bTextField, ptp1bDifficultySlider, 
																ptp2bRadio1, ptp2bRadio2, ptp2bTextField, ptp2bDifficultySlider,
																nspIpField, nspPortField, startButton));
		ptp2bRadio1.addActionListener(new modeButtonListener(4, gtpgRadio1, ptp1bRadio1, ptp1bRadio2, ptp1bTextField, ptp1bDifficultySlider, 
																ptp2bRadio1, ptp2bRadio2, ptp2bTextField, ptp2bDifficultySlider,
																nspIpField, nspPortField, startButton));
		ptp2bRadio2.addActionListener(new modeButtonListener(5, gtpgRadio1, ptp1bRadio1, ptp1bRadio2, ptp1bTextField, ptp1bDifficultySlider, 
																ptp2bRadio1, ptp2bRadio2, ptp2bTextField, ptp2bDifficultySlider,
																nspIpField, nspPortField, startButton));
		nspIpField.addKeyListener(new modeButtonListener(6, 	gtpgRadio1, ptp1bRadio1, ptp1bRadio2, ptp1bTextField, ptp1bDifficultySlider, 
																ptp2bRadio1, ptp2bRadio2, ptp2bTextField, ptp2bDifficultySlider,
																nspIpField, nspPortField, startButton));
		nspPortField.addKeyListener(new modeButtonListener(7, 	gtpgRadio1, ptp1bRadio1, ptp1bRadio2, ptp1bTextField, ptp1bDifficultySlider, 
																ptp2bRadio1, ptp2bRadio2, ptp2bTextField, ptp2bDifficultySlider,
																nspIpField, nspPortField, startButton));
		startButton.addActionListener(new modeButtonListener(8, gtpgRadio1, ptp1bRadio1, ptp1bRadio2, ptp1bTextField, ptp1bDifficultySlider, 
																ptp2bRadio1, ptp2bRadio2, ptp2bTextField, ptp2bDifficultySlider,
																nspIpField, nspPortField, startButton));
		
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
	
	//
	// Listener Implementation
	//
	
	
	class modeButtonListener implements ActionListener, KeyListener
	{
		int mode = -1;
		JRadioButton gtpgRadio1;
		JRadioButton ptp1bRadio1;
		JRadioButton ptp1bRadio2;
		JTextField ptp1bTextField;
		JSlider ptp1bDifficultySlider;
		JRadioButton ptp2bRadio1;
		JRadioButton ptp2bRadio2;
		JTextField ptp2bTextField;
		JSlider ptp2bDifficultySlider;
		JTextField nspIpField;
		JTextField nspPortField;
		JButton startButton;
		
		public modeButtonListener(int _mode,	JRadioButton _gtpgRadio1, JRadioButton _ptp1bRadio1, JRadioButton _ptp1bRadio2, JTextField _ptp1bTextField, JSlider _ptp1bDifficultySlider, 
												JRadioButton _ptp2bRadio1, JRadioButton _ptp2bRadio2, JTextField _ptp2bTextField, JSlider _ptp2bDifficultySlider,
												JTextField _nspIpField, JTextField _nspPortField, JButton _startButton) {
			mode = _mode;
			gtpgRadio1 = _gtpgRadio1;
			ptp1bRadio1 = _ptp1bRadio1;
			ptp1bRadio2 = _ptp1bRadio2;
			ptp1bTextField = _ptp1bTextField;
			ptp1bDifficultySlider = _ptp1bDifficultySlider;
			ptp2bRadio1 = _ptp2bRadio1;
			ptp2bRadio2 = _ptp2bRadio2;
			ptp2bTextField = _ptp2bTextField;
			ptp2bDifficultySlider = _ptp2bDifficultySlider;
			nspIpField = _nspIpField;
			nspPortField = _nspPortField;
			startButton = _startButton;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			switch(mode)
			{
				case 0: // Case 0 is where the user has chosen to Play Locally
					local = true; // Set the Play Local flag to true
					
					ptp1bRadio1.setEnabled(true); // Enable Player 1 configuration
					ptp1bRadio2.setEnabled(true);
					ptp2bRadio1.setEnabled(true); // Enable Player 2 configuration
					ptp2bRadio2.setEnabled(true);
					
					if(ptp2bRadio1.isSelected()) // If the mode is being switched from online to local then re-enable player 2's status
						ptp2bTextField.setEnabled(true);
					if(ptp2bRadio2.isSelected())
						ptp2bDifficultySlider.setEnabled(true);
					
					nspIpField.setEnabled(false); // These fields are never enabled in local play
					nspPortField.setEnabled(false);
					break;
			
				case 1: // Case 1 is where the user has chosen to Play Online
					local = false; // Set the Play Local flag to false, i.e. Play online.
					
					ptp1bRadio1.setEnabled(true); // Enable Player 1 configuration
					ptp1bRadio2.setEnabled(true);
					ptp2bRadio1.setEnabled(false); // We must remove Player 2 as an option since we do not decide on them.
					ptp2bRadio2.setEnabled(false);
					
					if(ptp1bRadio1.isSelected()) // If the mode is being switched from local to online make the player 1 selections carry over
						ptp1bTextField.setEnabled(true);
					if(ptp1bRadio2.isSelected())
						ptp1bDifficultySlider.setEnabled(true);
					ptp2bTextField.setEnabled(false); // This will always be disabled in online mode
					ptp2bDifficultySlider.setEnabled(false); // This will always be disabled in online mode
					
					nspIpField.setEnabled(true); // It is necessary to configure the network in online mode
					nspPortField.setEnabled(true); // Therefore we must enable these input fields
					break;
					
				case 2:	// Case 2 is where the user has chosen Player 1 to be Human
					ptp1bTextField.setEnabled(true);
					ptp1bDifficultySlider.setEnabled(false);
					break;
				
				case 3: // Case 3 is where the user has chosen Player 1 to be AI
					ptp1bTextField.setEnabled(false);
					ptp1bDifficultySlider.setEnabled(true);
					break;
					
				case 4:	// Case 4 is where the user has chosen Player 2 to be Human
					ptp2bTextField.setEnabled(true);
					ptp2bDifficultySlider.setEnabled(false);
					break;
				
				case 5: // Case 5 is where the user has chosen Player 2 to be AI
					ptp2bTextField.setEnabled(false);
					ptp2bDifficultySlider.setEnabled(true);
					break;
				
				case 6: // Case 6 is on the IP address field, it is used to check the correct start button status below
					break;
					
				case 7: // Case 7 is on the Port field, it is used to check the correct start button status below
					break;
					
				case 8: // Case 8 is where the user has chosen to begin the game
					// Any variables that need to be stored should be stored now.
					System.out.println("Firing Start Button for Effect");
					// 1 means Human, 2 means AI, 3 means Network
					int player1Type = 0;
					int player2Type = 0;
					String player1Name=null;
					String player2Name=null;
					String ipAddress = null;
					int portAddress = 0;
					
					Boolean localGame = gtpgRadio1.isSelected(); // True means Local Game, False means Networked Game
					
					Boolean player1Human = ptp1bRadio1.isSelected(); // True means Player 1 is Human, False means AI
					if(player1Human == false) // If Player 1 is AI
					{
						player1Type = 2;
						int difficulty = ptp1bDifficultySlider.getValue();
						player1Name="COMPUTER";
					}
					else
					{
						player1Type = 1;
						player1Name = ptp1bTextField.getText(); // Get Player 1's Name
					}
					
					if(localGame) // Then we need to retrieve Player 2 details also, may ignore the network
					{
						Boolean player2Human = ptp2bRadio1.isSelected();  // True means Player 2 is Human, False means AI
						
						if(player2Human == false) // If Player 2 is AI
						{
							player2Type = 2;
							int difficulty = ptp1bDifficultySlider.getValue();
							player2Name="COMPUTER";
						}
						else
						{
							player2Type = 1;
							player2Name = ptp2bTextField.getText(); // Get Player 2's Name
						}
					}
					else
					{
						ipAddress = nspIpField.getText();
						portAddress = Integer.parseInt(nspPortField.getText());
						player2Type = 3;
						player1Name = ptp1bTextField.getText(); // Get Player 1's Name
					}
					
					parentObject.createNewGame(true, player1Type, player2Type, player1Name, player2Name, ipAddress, portAddress);

					setupFrame.dispose(); // Releases all of the screen resources used by this Window, any memory they consume will be returned to the OS. 
					return;
					
				default:
					break;		
			}
			
			assertStartButtonState();
		}

		@Override
		public void keyTyped(KeyEvent arg0)
		{
			assertStartButtonState();	
		}
		
		void assertStartButtonState()
		{
			if(local == true) // If we are playing locally
			{
				if ( (ptp1bRadio1.isSelected()) || (ptp1bRadio2.isSelected()) ) // and if player 1 has data
				{
					if ( (ptp2bRadio1.isSelected()) || (ptp2bRadio2.isSelected()) ) // and if player 2 has data
					{
						startButton.setEnabled(true); // Then allow the user to start the game
						return;
					}
				}
			}
			else if (local == false)
			{
				if ( (ptp1bRadio1.isSelected()) || (ptp1bRadio2.isSelected()) ) // and if player 1 has data
				{
					if ( (nspIpField.getText().length() > 0) && (nspPortField.getText().length() > 0) ) // and if there is an IP and port
					{
						startButton.setEnabled(true); // Then allow the user to start the game
						return;
					}
				}
			}
			
			startButton.setEnabled(false); // If the above conditions are not satisfied then the game cannot be started
			// And the user is not given the option to start the game.
			return;
		}
		
		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
		}		
	}
	
}
