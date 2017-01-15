package code;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The main GUI for the game board
 * 
 * @authors Johnny Endrizzi
 * Josh Chatten
 * Mitchell Coovert 
 *
 *
 * State Variables
 * 	f: The window of the game
 *  gameSize: used in all coordinates, can be changed to rescale all displayed objects
 *  
 *  BGI: set Background image
 *  color: set selection colour
 *  But: set button background
 *  ButHover: set button hover background 
 *  font,smallfont,boldfont: primary fonts for Sudoku
 *  FontCol, FOntClr: Colours for fonts
 *  FontC: set label font colour
 *  BG1-7: Background images
 *  But1-14, ButPressed: Button coloursBG1-7: Background images
 *  mp3: song player
 *  songname, edmsong: background songs
 *  CL: layout of the buttons on the window
 *  MainPanel,GamePanel,MenuPanel,SettingsPanel,HowToPanel,CreditsPanel: primary windows for Sudoku
 *  
 *  
 *  
 * Environment Variables
 * 	keylistener: gets key inputs
 * 	mouselistener: gets mouse clicks
 * 	save: outputs text save file
 * 	load: inputs text save file
 *  
 * Assumptions
 *	Will receive a playable board from Data Structure Module
 *
 */
public class GUI extends JPanel implements MouseListener, KeyListener{
	private static final long serialVersionUID = 1L;
	
	
	static JFrame f;

	//Change board scale - change first number (gameSize must be a multiple of 10)
	protected static int gameSize = 30*10;
	
	//Window size
	static int gameSizeX = gameSize*4;
	static int gameSizeY = gameSize*4;
	
	//Used for game board and cell creation
	static private int gameSizeDiv = (int) (gameSize*0.2);
	
	//Font
	static Font font = new Font("Serif", Font.PLAIN, gameSizeDiv/2);
	static Font Smallfont = new Font("Serif", Font.PLAIN, gameSizeDiv/4);
	static Font boldFont = new Font("Serif", Font.BOLD, gameSizeDiv/2);
	
	//Font Color
	static Color FontCol = new Color(0,0,0);
	static Color FontClr = new Color(200,200,200);
	
	//Colour scheme
	static Color ThemeCyan = new Color(40, 120, 120, 120); //Green
	static Color ThemeMaroon = new Color(84, 19, 46, 120);
	static Color ThemeGreen = new Color(40, 120, 24, 120);
	static Color ThemeBlue = new Color(30, 40, 120, 120);

	static BufferedImage BG1;
	static BufferedImage BG2;
	static BufferedImage BG3;
	static BufferedImage BG4;
	static BufferedImage BG5;
	static BufferedImage BG6;
	static BufferedImage BG7;

	
	
	//Button Colours
	static Image But1 = Toolkit.getDefaultToolkit().createImage("src/code/res/NotSoBoringBlue (C).png"); //Green
	static Image But2 = Toolkit.getDefaultToolkit().createImage("src/code/res/OrangyRed (C).png");
	
	static Image But3 = Toolkit.getDefaultToolkit().createImage("src/code/res/dark blue (R).png");  //Red
	static Image But4 = Toolkit.getDefaultToolkit().createImage("src/code/res/dark orange (R).png");
	
	static Image But5 = Toolkit.getDefaultToolkit().createImage("src/code/res/light pink (B).png"); //Blue
	static Image But6 = Toolkit.getDefaultToolkit().createImage("src/code/res/pinkish (B).png");
	
	static Image But7 = Toolkit.getDefaultToolkit().createImage("src/code/res/burnt orange (G).png");  //Grey
	static Image But8 = Toolkit.getDefaultToolkit().createImage("src/code/res/light orange (G).png");
	
	static Image But9 = Toolkit.getDefaultToolkit().createImage("src/code/res/dark green (M).png");  //Maroon
	static Image But10 = Toolkit.getDefaultToolkit().createImage("src/code/res/bright green (M).png");
	
	static Image But11 = Toolkit.getDefaultToolkit().createImage("src/code/res/light blue (y).png");  //Yellow
	static Image But12 = Toolkit.getDefaultToolkit().createImage("src/code/res/very light blue (y).png");
	
	static Image But13 = Toolkit.getDefaultToolkit().createImage("src/code/res/TEMP (E).png");  //Party
	static Image But14 = Toolkit.getDefaultToolkit().createImage("src/code/res/HyperRainbow (E).png");
	
	static Image ButPressed = Toolkit.getDefaultToolkit().createImage("src/code/res/grey.png");
	static Image title = Toolkit.getDefaultToolkit().createImage("src/code/res/Title.png");
	static Image rules = Toolkit.getDefaultToolkit().createImage("src/code/res/Rules.png");

	// Music
	static MP3 mp3;
	static String songname = "../Audio/bensound-relaxing.mp3"; //Music: www.bensound.com 
	static String edmsong = "src/code/Audio/Araujo.mp3"; //https://soundcloud.com/araujo-3/araujo-miami-2015-edm-sessions
	
	//misc variables	
	static boolean partyMode = false;
	
	//Set Themes
	static BufferedImage BGI = BG1;
	static BufferedImage LastBG;
	static Image BGParty = BG7;
	
	static Color color = ThemeCyan;
	static Color FontC = FontCol;
	
	static Image But = But1;
	static Image ButHover = But2;
	
	
	//Panel Creation
	static CardLayout CL = new CardLayout();
	static final JPanel MainPanel = new JPanel();
	static GamePanel GamePanel = new GamePanel();
	static MenuPanel MenuPanel = new MenuPanel();
	static SettingsPanel SettingsPanel = new SettingsPanel();
	static HowToPanel HowToPanel = new HowToPanel();
	static CreditsPanel CreditsPanel = new CreditsPanel();

	/**
	 *  transition: Creates the GUI and imports the background images
	 *  
	 *  exceptions: none
	 */
	public GUI() throws IOException {
		

		BG1 = ImageIO.read(new File("src/code/res/garden.jpg")); //Garden
		BG2 = ImageIO.read(new File("src/code/res/uluru.jpg")); //Uluru
		BG3 = ImageIO.read(new File("src/code/res/Aurora_borealis.jpg")); //Aurora borealis
		BG4 = ImageIO.read(new File("src/code/res/galaxy.jpg")); //Galaxy
		BG5 = ImageIO.read(new File("src/code/res/Sunset.jpg")); //Sunset
		BG6 = ImageIO.read(new File("src/code/res/sand.jpg")); //Desert
		BG7 = ImageIO.read(new File("src/code/res/edmcrowd.jpg"));
		BGI = BG1;
		BGParty = BG7;
		
		f = new JFrame("Sudoku!");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MainPanel.setLayout(CL);
		
		MainPanel.add(MenuPanel, "Main Menu");
		MainPanel.add(GamePanel, "Sudoku");
		MainPanel.add(SettingsPanel, "Settings");
		MainPanel.add(HowToPanel,"How To");
		MainPanel.add(CreditsPanel,"Credits");
		
		mp3 = new MP3(songname);
		mp3.play();
		
		
		addKeyListener(this);
		addMouseListener(this);
		
		setFocusable(true);
		
		
		f.add(MainPanel);
		
		f.setSize(gameSizeX,gameSizeY);	
		f.setVisible(true);
		f.setResizable(false);
		
		
	
	}
	
	/**
	 * Repaints the buttons depending on the settings chosen
	 */
	
	public static void buttonRepaint() {
		
		//MenuPanel Buttons
		MenuPanel.buttonNewG.setIcon(new ImageIcon(GUI.But));
		MenuPanel.buttonLoad.setIcon(new ImageIcon(GUI.But));
		MenuPanel.buttonHtP.setIcon(new ImageIcon(GUI.But));
		MenuPanel.buttonSettings.setIcon(new ImageIcon(GUI.But));
		MenuPanel.buttonCredit.setIcon(new ImageIcon(GUI.But));
		MenuPanel.buttonQuit.setIcon(new ImageIcon(GUI.But));
		
		MenuPanel.buttonEasy.setIcon(new ImageIcon(GUI.But));
		MenuPanel.buttonMed.setIcon(new ImageIcon(GUI.But));
		MenuPanel.buttonHard.setIcon(new ImageIcon(GUI.But));
		MenuPanel.buttonBack.setIcon(new ImageIcon(GUI.But));
		
		//MenuPanel Hover
		MenuPanel.buttonNewG.setRolloverIcon(new ImageIcon(GUI.ButHover));
		MenuPanel.buttonLoad.setRolloverIcon(new ImageIcon(GUI.ButHover));
		MenuPanel.buttonHtP.setRolloverIcon(new ImageIcon(GUI.ButHover));
		MenuPanel.buttonSettings.setRolloverIcon(new ImageIcon(GUI.ButHover));;
		MenuPanel.buttonCredit.setRolloverIcon(new ImageIcon(GUI.ButHover));
		MenuPanel.buttonQuit.setRolloverIcon(new ImageIcon(GUI.ButHover));
		
		MenuPanel.buttonEasy.setRolloverIcon(new ImageIcon(GUI.ButHover));
		MenuPanel.buttonMed.setRolloverIcon(new ImageIcon(GUI.ButHover));
		MenuPanel.buttonHard.setRolloverIcon(new ImageIcon(GUI.ButHover));
		MenuPanel.buttonBack.setRolloverIcon(new ImageIcon(GUI.ButHover));
		
		//GamePanel Buttons
		GamePanel.buttonR.setIcon(new ImageIcon(GUI.But));
		GamePanel.buttonSave.setIcon(new ImageIcon(GUI.But));
		GamePanel.buttonRestart.setIcon(new ImageIcon(GUI.But));
		GamePanel.buttonNewGame.setIcon(new ImageIcon(GUI.But));
		GamePanel.buttonLoad.setIcon(new ImageIcon(GUI.But));
		GamePanel.buttonHint.setIcon(new ImageIcon(GUI.But));
		GamePanel.buttonParty.setIcon(new ImageIcon(GUI.But));
		GamePanel.buttonMenu.setIcon(new ImageIcon(GUI.But));
		GamePanel.buttonPause.setIcon(new ImageIcon(GUI.But));
		GamePanel.buttonHome.setIcon(new ImageIcon(GUI.But));
		
		//GamePanel Hover
		GamePanel.buttonR.setRolloverIcon(new ImageIcon(GUI.ButHover));
		GamePanel.buttonSave.setRolloverIcon(new ImageIcon(GUI.ButHover));
		GamePanel.buttonRestart.setRolloverIcon(new ImageIcon(GUI.ButHover));
		GamePanel.buttonNewGame.setRolloverIcon(new ImageIcon(GUI.ButHover));
		GamePanel.buttonLoad.setRolloverIcon(new ImageIcon(GUI.ButHover));
		GamePanel.buttonHint.setRolloverIcon(new ImageIcon(GUI.ButHover));
		GamePanel.buttonParty.setRolloverIcon(new ImageIcon(GUI.ButHover));
		GamePanel.buttonMenu.setRolloverIcon(new ImageIcon(GUI.ButHover));
		GamePanel.buttonPause.setRolloverIcon(new ImageIcon(GUI.ButHover));
		GamePanel.buttonHome.setRolloverIcon(new ImageIcon(GUI.ButHover));
		

		
		//SettingsPanel Buttons
		SettingsPanel.buttonOK.setIcon(new ImageIcon(GUI.But));
		SettingsPanel.buttonCanc.setIcon(new ImageIcon(GUI.But));
		SettingsPanel.buttonApp.setIcon(new ImageIcon(GUI.But));
		
		SettingsPanel.buttonTGreen.setIcon(new ImageIcon(GUI.But));
		SettingsPanel.buttonTRed.setIcon(new ImageIcon(GUI.But));
		SettingsPanel.buttonTBlue.setIcon(new ImageIcon(GUI.But));
		SettingsPanel.buttonTGrey.setIcon(new ImageIcon(GUI.But));
		SettingsPanel.buttonTMaroon.setIcon(new ImageIcon(GUI.But));		
		SettingsPanel.buttonTYellow.setIcon(new ImageIcon(GUI.But));
		
		//SettingsPanel Hover
		SettingsPanel.buttonOK.setRolloverIcon(new ImageIcon(GUI.ButHover));
		SettingsPanel.buttonCanc.setRolloverIcon(new ImageIcon(GUI.ButHover));
		SettingsPanel.buttonApp.setRolloverIcon(new ImageIcon(GUI.ButHover));
		
		SettingsPanel.buttonTGreen.setRolloverIcon(new ImageIcon(GUI.ButHover));
		SettingsPanel.buttonTRed.setRolloverIcon(new ImageIcon(GUI.ButHover));
		SettingsPanel.buttonTBlue.setRolloverIcon(new ImageIcon(GUI.ButHover));
		SettingsPanel.buttonTGrey.setRolloverIcon(new ImageIcon(GUI.ButHover));
		SettingsPanel.buttonTMaroon.setRolloverIcon(new ImageIcon(GUI.ButHover));		
		SettingsPanel.buttonTYellow.setRolloverIcon(new ImageIcon(GUI.ButHover));
		
		HowToPanel.buttonBack.setIcon(new ImageIcon(GUI.But));
		HowToPanel.buttonBack.setRolloverIcon(new ImageIcon(GUI.ButHover));
		
	}
	
	/**
	 * Runs the game
	 * 
	 * @param args
	 * @throws Exception
	 */
	
	public static void main(String[] args) throws Exception{
		@SuppressWarnings("unused")
		GUI gui = new GUI();
	}
	
	/**
	 * Switches the background song
	 */
	
	public static void playMusic() {
		
		if (partyMode) {
			mp3.close();
			mp3 = new MP3(edmsong);
	        mp3.play();
		} else {
			mp3.close();
			mp3 = new MP3(songname);
	        mp3.play();
		}
	}
	
	/**
	 * 
	 * @param e
	 * 
	 * transition: cell select 
	 * input: mouse click
	 * 
	 * exception: none
	 */	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	
	/**
	 * 
	 * @param e
	 * 
	 * transition: none
	 * input: mouse entered
	 * 
	 * exception: none
	 */	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	/**
	 * 
	 * @param e
	 * 
	 * transition: none
	 * input: mouse exited
	 * 
	 * exception: none
	 */	
	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	/**
	 * @param e
	 * 
	 * transition: none
	 * input: mouse pressed
	 * 
	 * exception: none
	 */	
	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	/**
	 * @param e
	 * 
	 * transition: none
	 * input: mouse release
	 * 
	 * exception: none
	 */	
	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
	

	@Override
	public void keyPressed(KeyEvent arg0) {
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
}
