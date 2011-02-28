package twixtPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
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
		
		setupFrame.getContentPane().setLayout(new BorderLayout()); // Give the main frame a Border Layout  

					
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
		
		// Radio Buttons.
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
		
		JSlider ptp2bDifficultySlider = new JSlider(0, 5, 0);
		ptp2bDifficultySlider.setMajorTickSpacing(1);
		ptp2bDifficultySlider.setPaintTicks(true);
		ptp2bDifficultySlider.setSnapToTicks(true);
		
		// Buttons
		JButton startButton = new JButton("Start Game");

		//
		// Panels & Layout
		//
		
		// Formatting Variables
		Color color = new Color(205, 11, 66);
		Border border = BorderFactory.createMatteBorder(20, 10, 20, 10, color); // Give the frame a 20 x 10 border		
		
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
		//gtpHeading.setBorder(BorderFactory.createEmptyBorder(0, 0, -130, 0));
		
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
