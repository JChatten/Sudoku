package code;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * The Game Board panel
 * 
 * @authors Johnny Endrizzi
 * Josh Chatten
 * Mitchell Coovert 
 *
 *
 * State Variables
 *  //Board
 *  gameSize: used in all coordinates, can be changed to rescale all displayed objects, obtained from the controller
 *  gameSizeDiv: based off of gameSize, used in scalable coordinates 
 *  board: contains all the values for the board
 *  
 *  //Board size 
 *	width: used in grid generation
 *	x1: used in grid generation
 *	y1: used in grid generation
 *  
 *  //Selection
 *  lightHoriz: used to select a row
 *	lightVert: used to select a row
 *	lightBox: used to select a box
 *	lightCellH: used to select a cell
 *	lightCellV: used to select a cell
 *	selectedX: used to keep track of selection
 *	selectedY: used to keep track of selection
 *	
 *	//Board creation - Rectangle2D.Double arrays
 *	horiz: the lines in the grid 
 *	vert: the other lines in the grid
 *	box: the 3x3 boxes on the game board
 *	cell: the cells on the game board
 *  
 *  //Buttons
 *  buttonR: button used to resume after pausing 
 *  buttonNew: button used to start a new game after winning a previous game
 *  buttonExit: button used to quit the game after winning a previous game
 *  buttonSave: button used to save the game 
 *  buttonRestart: button used to generate a new board
 *  buttonNewGame: button used to start a new game
 *  buttonLoad: button used to load a game
 *  buttonHint: button used to use a hint
 *  buttonParty: button used to enable party mode 
 *  buttonMenu: button used to drop down the button menu
 *  buttonPause: button used to pause the game
 *  buttonHome: button used to o back to the menu
 *   
 * //Font
 * font: font used for the text
 * Smallfont: font used for the subvalues on the board
 * fontBold: font used for permanent values
 * Bigfont: font used for the buttons 
 * FontCol: current font colour 
 * FontClr: clear font colour
 * FontWhi: white font
 *  
 *  //Misc.
 *  pauseGame: used to keep track of the games pause state
 *  shifting: used to keep track of the shift key  
 *  code: used in key inputs
 *  unassigned: board unassigned value
 *  ProgMode: used to control Programmer mode
 *  mouseClicked: used to deselect cells
 *  hold: prevents tiles from repainting in party mode 
 *  won: keeps track of the games won/hasn't won status
 *  hints: keeps track of hints
 *  
 * //Board colours
 * BgWindow: default background colour
 * BGWhite: white background
 * BgBoard: Defauld board background colour
 * BgPause: colour of the paused board 
 *  
 *  //Party mode colours
 *  partyPurple: Party mode cell colour
 *  partyLightPurple: Party mode cell colour
 *  partyPink: Party mode cell colour
 *  partyLightPink: Party mode cell colour
 *  partyBlue: Party mode cell colour
 *  partyLightBlue: Party mode cell colour
 *  partyAqua: Party mode cell colour
 *  partyGreen: Party mode cell colour
 *  partyLightGreen: Party mode cell colour
 *  partyRed: Party mode cell colour
 *  partyLightRed: Party mode cell colour
 *  partyOrange: Party mode cell colour
 *  partyLightOrange: Party mode cell colour
 *  partyYellow: Party mode cell colour
 *  partyGold: Party mode cell colour
 *  partyWhite: Party mode cell colour
 *  
 *  partyColourArray: all the colours for party mode cells
 *  
 *  //Labels
 *  labelsArr: generates board labels
 *  
 *  
 * Environment Variables
 * 	keylistener: gets key inputs
 * 	mouselistener: gets mouse clicks
 * 	
 * 	
 *  
 * Assumptions
 *	Gets a valid board
 *
 */
public class GamePanel extends JPanel implements MouseListener, KeyListener{
		
	private static final long serialVersionUID = 1L;
	
	//Board 
	private int gameSize = GUI.gameSize;
	private int gameSizeDiv = (int) (gameSize*0.2);
	static Board board;
	
	//Board size 
	private int width = (int) (gameSize*1.8);
	private int x1 = gameSize;
	private int y1 = gameSize;
	
	//Selection 
	private int lightHoriz;
	private int lightVert;
	private int lightBox;
	private int lightCellH;
	private int lightCellV;
	static int selectedX = 10;
	static int selectedY = 10;
	
	//Board creation 
	Rectangle2D.Double[] horiz = new Rectangle2D.Double[9];
	Rectangle2D.Double[] vert = new Rectangle2D.Double[9];
	Rectangle2D.Double[] box = new Rectangle2D.Double[9];
	Rectangle2D.Double[][] cell = new Rectangle2D.Double[9][9];
	
	//Buttons
	public static JLabel labels;
	protected JButton buttonR;
	protected JButton buttonNew;
	protected JButton buttonExit;
	protected JButton buttonSave;
	protected JButton buttonRestart;
	protected JButton buttonNewGame;
	protected JButton buttonLoad;
	protected JButton buttonHint;
	protected JButton buttonParty;
	protected JButton buttonMenu;
	protected JButton buttonPause;
	protected JButton buttonHome;
	
	boolean menuClicked = false;
	
	//Font
	Font font = new Font("Serif", Font.PLAIN, gameSizeDiv/2);
	Font fontBold = new Font("Serif", Font.BOLD, gameSizeDiv/2);
	Font Smallfont = new Font("Serif", Font.BOLD, gameSizeDiv/4);
	Font Bigfont = new Font("Lucida Calligraphy", Font.BOLD, gameSizeDiv/2);
	Color FontCol = new Color(0,0,0);
	Color FontClr = new Color(200,200,200);
	Color FontWhi = new Color(200,200,200);
	
	//Party Mode Colours
	Color partyPurple = new Color(232,10,255,255);
	Color partyLightPurple = new Color(241,108,255,255);
	Color partyPink = new Color(255,0,169,255);
	Color partyLightPink = new Color(255,100,200,255);
	Color partyBlue = new Color(0,0,255,255);
	Color partyLightBlue = new Color(0,152,255,255);
	Color partyAqua = new Color(0,255,255,255);
	Color partyGreen = new Color(0,255,0,255);
	Color partyLightGreen = new Color(108,255,0,255);
	Color partyRed = new Color(255,0,0,255);
	Color partyLightRed = new Color(255,53,50,255);
	Color partyOrange = new Color(255,159,0,255);
	Color partyLightOrange = new Color(255,191,68,255);
	Color partyYellow = new Color(255,255,0,255);
	Color partyGold = new Color(233,203,14,255);
	Color partyWhite = new Color(255,255,255,255);
		
		
	//Party mode color array, Don't try to read
	Color[] partyColourArray = new Color[]{partyPurple,partyLightPurple,partyPink,partyLightPink,partyBlue,partyLightBlue,partyAqua,partyGreen,partyLightGreen,partyRed,partyLightRed,partyOrange,partyLightOrange,partyYellow,partyGold,partyWhite};
				
	Color BgWindow = new Color(0x53152A); //default background
	Color BGWhite = new Color(0xffffff);
	Color BgBoard = new Color(170, 154, 154, 150); 
	Color BgPause = new Color(200, 200, 200, 0); 
	
	//Misc.
	boolean pauseGame = false;
	boolean shifting = false;
	int code = 0;
	int unassigned = 0;
	int ProgMode = 0;
	private boolean mouseClicked = false;
	boolean hold = false;
	boolean won = false;
	boolean hints = false;

	//Labels
	JLabel[][] labelsArr = JlabelsMaker();
	
	
	public GamePanel(){
	
		buttons();
		setLayout(null);
		buttonR();
        
        addMouseListener(this);
        addKeyListener(this);
	}
	
	public static void NewBoard(){
		Difficulty DIFF = MenuPanel.getDiff(); 
		board = new Board(DIFF);
	}
	
	/** 
	 * transitions: Sets up buttons
	 * 
	 * exception: none
	 */
	public void buttons(){
		setLayout(null);
		
		buttonRestart("Restart",4,false);
		buttonSave("Save",5,false);
		buttonLoad("Load",6,false);
		buttonMenu("Menu",1,false);
		buttonPause("Pause",3,false);
		buttonParty("Party Mode",7,false);
		buttonNewGame("New Game", 3,false);
		buttonHint("Hint", 8,hints);
		buttonHome("Home",2,false);
		
	}
	
	
	/**
	 * transitions: Drop down Menu button
	 * 
	 * exception: none
	 * 
	 * @param str
	 * @param pos
	 * @param done
	 */
	public void buttonMenu(String str, int pos, boolean done){
		ButtonStuff ButtonHandler = new ButtonStuff();
		buttonMenu = new JButton(str);
		buttonMenu.setIcon(new ImageIcon(GUI.But));
		buttonMenu.setRolloverIcon(new ImageIcon(GUI.ButHover));
		buttonMenu.setPreferredSize(new Dimension(gameSizeDiv*2, gameSizeDiv));
		buttonMenu.setBounds(gameSize*4/9-10, gameSize/2, gameSizeDiv+70, gameSizeDiv+20);
		buttonMenu.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonMenu.addActionListener(ButtonHandler);
		buttonMenu.setForeground(FontWhi);
		add(buttonMenu);
	}
	
	/**
	 * transitions: Home button
	 * 
	 * exception: none
	 * 
	 * @param str
	 * @param pos
	 * @param done
	 */
	public void buttonHome(String str, int pos, boolean done){
		ButtonStuff ButtonHandler = new ButtonStuff();
		buttonHome = new JButton(str);
		buttonHome.setIcon(new ImageIcon(GUI.But));
		buttonHome.setPreferredSize(new Dimension(gameSizeDiv*2, gameSizeDiv));
		buttonHome.setBounds(gameSize*4/9, gameSize/3*pos+80, gameSizeDiv+55, gameSizeDiv);
		buttonHome.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonHome.setRolloverIcon(new ImageIcon(GUI.ButHover));
		buttonHome.addActionListener(ButtonHandler);
		buttonHome.setForeground(FontWhi);
		buttonHome.setVisible(false);
		add(buttonHome);
	}
	
	/**
	 * transitions: New Game button
	 * 
	 * exception: none
	 * 
	 * @param str
	 * @param pos
	 * @param done
	 */
	public void buttonNewGame(String str, int pos, boolean done){
		ButtonStuff ButtonHandler = new ButtonStuff();
		buttonNewGame = new JButton(str);
		buttonNewGame.setIcon(new ImageIcon(GUI.But));
		buttonNewGame.setPreferredSize(new Dimension(gameSizeDiv*2, gameSizeDiv));
		buttonNewGame.setBounds(gameSize*4/9, gameSize/3*pos+80, gameSizeDiv+55, gameSizeDiv);
		buttonNewGame.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonNewGame.setRolloverIcon(new ImageIcon(GUI.ButHover));
		buttonNewGame.addActionListener(ButtonHandler);
		buttonNewGame.setForeground(FontWhi);
		buttonNewGame.setVisible(false);
		add(buttonNewGame);
	}
	
	/**
	 * transitions: Save Game button
	 * 
	 * exception: none
	 * 
	 * @param str
	 * @param pos
	 * @param done
	 */
	public void buttonSave(String str, int pos, boolean done){
		ButtonStuff ButtonHandler = new ButtonStuff();
		buttonSave = new JButton(str);
		buttonSave.setIcon(new ImageIcon(GUI.But));
		buttonSave.setPreferredSize(new Dimension(gameSizeDiv*2, gameSizeDiv));
		buttonSave.setBounds(gameSize*4/9, gameSize/3*pos+80, gameSizeDiv+55, gameSizeDiv);
		buttonSave.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonSave.setRolloverIcon(new ImageIcon(GUI.ButHover));
		buttonSave.addActionListener(ButtonHandler);
		buttonSave.setForeground(FontWhi);
		buttonSave.setVisible(false);
		add(buttonSave);
	}
	
	/**
	 * transitions: Load Game button
	 * 
	 * exception: none
	 * 
	 * @param str
	 * @param pos
	 * @param done
	 */
	public void buttonLoad(String str, int pos, boolean done){
		ButtonStuff ButtonHandler = new ButtonStuff();
		buttonLoad = new JButton(str, new ImageIcon(GUI.But));
		buttonLoad.setIcon(new ImageIcon(GUI.But));
		buttonLoad.setPreferredSize(new Dimension(gameSizeDiv*2, gameSizeDiv));
		buttonLoad.setBounds(gameSize*4/9, gameSize/3*pos+80, gameSizeDiv+55, gameSizeDiv);
		buttonLoad.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonLoad.setRolloverIcon(new ImageIcon(GUI.ButHover));
		buttonLoad.addActionListener(ButtonHandler);
		buttonLoad.setForeground(FontWhi);
		buttonLoad.setVisible(false);
		add(buttonLoad);
	}
	
	/**
	 * transitions: Restart Game button
	 * 
	 * exception: none
	 * 
	 * @param str
	 * @param pos
	 * @param done
	 */
	public void buttonRestart(String str, int pos, boolean done){
		ButtonStuff ButtonHandler = new ButtonStuff();
		buttonRestart = new JButton(str);
		buttonRestart.setIcon(new ImageIcon(GUI.But));
		buttonRestart.setPreferredSize(new Dimension(gameSizeDiv*2, gameSizeDiv));
		buttonRestart.setBounds(gameSize*4/9, gameSize/3*pos+80, gameSizeDiv+55, gameSizeDiv);
		buttonRestart.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonRestart.setRolloverIcon(new ImageIcon(GUI.ButHover));
		buttonRestart.addActionListener(ButtonHandler);
		buttonRestart.setForeground(FontWhi);
		buttonRestart.setVisible(false);
		add(buttonRestart);
	}
	
	/**
	 * transitions: Pause Game button
	 * 
	 * exception: none
	 * 
	 * @param str
	 * @param pos
	 * @param done
	 */
	public void buttonPause(String str, int pos, boolean done){
		ButtonStuff ButtonHandler = new ButtonStuff();
		buttonPause = new JButton(str);
		buttonPause.setIcon(new ImageIcon(GUI.But));
		buttonPause.setPreferredSize(new Dimension(gameSizeDiv*2, gameSizeDiv));
		buttonPause.setBounds(gameSize*3+80, gameSize/2, gameSizeDiv+55, gameSizeDiv);
		buttonPause.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonPause.setRolloverIcon(new ImageIcon(GUI.ButHover));
		buttonPause.addActionListener(ButtonHandler);
		buttonPause.setForeground(FontWhi);
		add(buttonPause);
	}
	
	/**
	 * transitions: Party Mode button
	 * 
	 * exception: none
	 * 
	 * @param str
	 * @param pos
	 * @param done
	 */
	public void buttonParty(String str, int pos, boolean done){
		ButtonStuff ButtonHandler = new ButtonStuff();
		buttonParty = new JButton(str);
		buttonParty.setIcon(new ImageIcon(GUI.But));
		buttonParty.setPreferredSize(new Dimension(gameSizeDiv*2, gameSizeDiv));
		buttonParty.setBounds(gameSize*4/9, gameSize/3*pos+80, gameSizeDiv+55, gameSizeDiv);
		buttonParty.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonParty.setRolloverIcon(new ImageIcon(GUI.ButHover));
		buttonParty.addActionListener(ButtonHandler);
		buttonParty.setForeground(FontWhi);
		buttonParty.setVisible(false);
		add(buttonParty);
	}
	
	/**
	 * 	 transitions: Hint button
	 * 
	 * exception: none
	 * 
	 * @param str
	 * @param pos
	 * @param done
	 */
	public void buttonHint(String str, int pos, boolean done){
		ButtonStuff ButtonHandler = new ButtonStuff();
		buttonHint = new JButton(str);
		if (hints) {
			buttonHint.setIcon(new ImageIcon(GUI.ButPressed));
			buttonHint.setRolloverIcon(new ImageIcon(GUI.ButPressed));
			
		} else {
			buttonHint.setIcon(new ImageIcon(GUI.But));
			buttonHint.setRolloverIcon(new ImageIcon(GUI.ButHover));
		}
		buttonHint.setPreferredSize(new Dimension(gameSizeDiv*2, gameSizeDiv));
		buttonHint.setBounds(gameSize*3-50, gameSize/2, gameSizeDiv+55, gameSizeDiv);
		buttonHint.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonHint.addActionListener(ButtonHandler);
		buttonHint.setForeground(FontWhi);
		add(buttonHint);
	}

	
	/**
	 * transitions: Resume button
	 * 
	 * exception: none
	 * 
	 * @param str
	 * @param pos
	 * @param done
	 */
	public void buttonR(){ 
		ButtonStuff ButtonHandler = new ButtonStuff();
		buttonR = new JButton("Resume");
		buttonR.setIcon(new ImageIcon(GUI.But));
		buttonR.setPreferredSize(new Dimension(gameSizeDiv*2, gameSizeDiv));
		buttonR.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonR.setBounds(gameSize*2-gameSizeDiv*3+gameSizeDiv, gameSize*2-gameSizeDiv-gameSizeDiv/2, gameSizeDiv*3, gameSizeDiv*2);	
		buttonR.setRolloverIcon(new ImageIcon(GUI.ButHover));
		buttonR.addActionListener(ButtonHandler);
		buttonR.setForeground(FontWhi);
		add(buttonR);
		buttonR.setVisible(false);
	}
	
	/**
	 * transitions: Win buttons 
	 * 
	 * exception: none
	 * 
	 * @param str
	 * @param pos
	 * @param done
	 */
	public void buttonWin() {
		ButtonStuff ButtonHandler = new ButtonStuff();
		buttonNew = new JButton("New");
		buttonNew.setIcon(new ImageIcon(GUI.But));
		buttonNew.setPreferredSize(new Dimension(gameSizeDiv*2, gameSizeDiv));
		buttonNew.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonNew.setBounds(gameSize-gameSizeDiv*3+gameSizeDiv, gameSize*2-gameSizeDiv-gameSizeDiv/2, gameSizeDiv*3, gameSizeDiv*2);	
		buttonNew.setRolloverIcon(new ImageIcon(GUI.ButHover));
		buttonNew.addActionListener(ButtonHandler);
		buttonNew.setForeground(FontWhi);
		add(buttonNew);
		buttonNew.setVisible(true);
		buttonExit = new JButton("Exit", new ImageIcon(GUI.But));
		buttonExit.setPreferredSize(new Dimension(gameSizeDiv*2, gameSizeDiv));
		buttonExit.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonExit.setBounds(gameSize*2-gameSizeDiv*3+gameSizeDiv, gameSize*2-gameSizeDiv-gameSizeDiv/2, gameSizeDiv*3, gameSizeDiv*2);	
		buttonExit.setRolloverIcon(new ImageIcon(GUI.ButHover));
		buttonExit.addActionListener(ButtonHandler);
		buttonExit.setForeground(FontWhi);
		add(buttonExit);
		buttonExit.setVisible(true);
	}
	
	/**  
	 * @author Johnny Endrizzi
	 * Josh Chatten
	 * Mitchell Coovert 
	 *
	 * State Variables
	 * 	event: gets the name of the button that has been pressed
	 * 	BgPause: Changes the overlay to block out visibility on pause
	 * 	FontC: Changes the font to block out visibility on pause
	 * 	CL: changes between menu and game layers
	 * 	partyMode: enebles Party mode
	 * 
	 * Environment Variables
	 * 	showInputDialog: gets a name of a save file to create/overwrite
	 * 	showInputDialog: gets a name of a save file to load  
	 * 
	 * Assumptions
	 * 	Will receive a defined button
	 */
	private class ButtonStuff implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			
			String str = String.format("%s", event.getActionCommand());
			if(str.equals("Restart")){
				board.reset();
				hints = false;
				repaint();
				
			}else if(str.equals("New Game")){
				board.newBoard();
				hints = false;
				repaint();
				
			}else if(str.equals("Home")){
				buttonSave.setVisible(false);
				buttonLoad.setVisible(false);
				buttonParty.setVisible(false);
				buttonRestart.setVisible(false);
				buttonHome.setVisible(false);
				buttonLoad.setVisible(false);
				buttonNewGame.setVisible(false);
				buttonHint.setIcon(new ImageIcon(GUI.But));
				buttonHint.setRolloverIcon(new ImageIcon(GUI.But3));
				buttonMenu.setIcon(new ImageIcon(GUI.But));
				menuClicked = !menuClicked;
				GUI.partyMode = false;
				GUI.playMusic();
				hints = false;
				GUI.CL.next(GUI.MainPanel);
				GUI.CL.next(GUI.MainPanel);
				GUI.CL.next(GUI.MainPanel);
				GUI.CL.next(GUI.MainPanel);
				
			}else if(str.equals("Save")){
				String saveName = JOptionPane.showInputDialog(null, String.format("%s", event.getActionCommand()),"Save Game", 1);
				Slave.save(board,saveName);
				
			}else if(str.equals("Load")){
				String loadName = JOptionPane.showInputDialog(null, String.format("%s", event.getActionCommand()),"Load Game", 2);
				try {
					Board temp = Slave.load(loadName);
					board = new Board(temp.board,temp.original,temp.full);
					repaint();
				} catch (FileNotFoundException e) {}
				
			}else if(str.equals("Menu")){
				if (!menuClicked) {
					buttonSave.setVisible(true);
					buttonLoad.setVisible(true);
					buttonParty.setVisible(true);
					buttonRestart.setVisible(true);
					buttonHome.setVisible(true);
					buttonLoad.setVisible(true);
					buttonNewGame.setVisible(true);
					buttonMenu.setIcon(new ImageIcon(GUI.ButPressed));
					menuClicked = !menuClicked;
				} else {
					buttonSave.setVisible(false);
					buttonLoad.setVisible(false);
					buttonParty.setVisible(false);
					buttonRestart.setVisible(false);
					buttonHome.setVisible(false);
					buttonLoad.setVisible(false);
					buttonNewGame.setVisible(false);
					buttonMenu.setIcon(new ImageIcon(GUI.But));
					menuClicked = !menuClicked;
				}
				//GUI.CL.next(GUI.MainPanel);
				
			}else if(str.equals("Pause")){
				BgPause = new Color(200, 200, 200, 252); 
				GUI.FontC = FontClr;
				pauseGame = true;
				buttonR.setVisible(true);
				JlabelsReColor(labelsArr);
				repaint();
				
			}else if(str.equals("Resume")){
				BgPause = new Color(200, 200, 200, 0);
				GUI.FontC = FontCol;
				pauseGame = false;
				buttonR.setVisible(false);
				JlabelsReColor(labelsArr);
				repaint();
				
			} else if (str.equals("Party Mode")) {
				GUI.partyMode = !GUI.partyMode;
				GUI.playMusic();
				repaint();
				
			} else if(str.equals("New")){
				board.newBoard();
				buttonNew.setVisible(false);
				buttonExit.setVisible(false);
				JlabelsReColor(labelsArr);
				won = false;
				hints = false;
				repaint();
				
			} else if(str.equals("Exit")){
				GUI.mp3.close();GUI.f.dispose();

			} else if(str.equals("Hint")) {
				if (board.checkHints()) {
					board.hint();
					board.decHints();
					repaint();
				}
				if (!board.checkHints()){ 
					buttonHint.setIcon(new ImageIcon(GUI.ButPressed));
					buttonHint.setRolloverIcon(new ImageIcon(GUI.ButPressed));
					hints = true;
					buttons();
					repaint();
				}
				
			}else if(str.equals("Pause Game")){
				BgPause = new Color(200, 200, 200, 252); 
				GUI.FontC = FontClr;
				pauseGame = true;
				buttonR.setVisible(true);
				JlabelsReColor(labelsArr);
				repaint();
			}	
		}
	}
	
	/**
	 * transition: Adds the visual elements to the GUI including the board and the labels
	 * 
	 * exception: none
	 */
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		setBackground(BGWhite);
		
		if (GUI.partyMode == true) {
		
			Random back = new Random();
			if (back.nextInt(2) == 0) {
				
			} else {
					g.drawImage(GUI.BGParty, 0, 0, getWidth(), getHeight(), null);
			}
			
			Graphics2D g2d = (Graphics2D)g;		
		
			//boxes		
			int i = 0;
			for (int h=gameSize; h<=gameSize*2.2; h+=gameSizeDiv*3) {
				for (int l=gameSize; l<=gameSize*2.2; l+=gameSizeDiv*3) {
					box[i] = new Rectangle2D.Double(l, h, gameSizeDiv*3, gameSizeDiv*3);	
					i++;
				}
			}
			
			//TODO Remove Spaces 
			
			
			
			
			
			
			
			
			//Board Selection Highlighting
			Rectangle2D rect = new Rectangle2D.Double(gameSize, gameSize, gameSize*1.8, gameSize*1.8);
			g2d.setColor(BgBoard);
			g2d.fill(rect);
			
			//cell selection
			if(mouseClicked || hold){
				if(pauseGame == false){
					g2d.setColor(GUI.color);			
					g2d.fill(box[lightBox]);
					
					//g2d.setColor(color);
					g2d.fill(horiz[lightHoriz]);
					g2d.fill(vert[lightVert]);
							
					//g2d.setColor(color);
					g2d.fill(cell[lightCellV][lightCellH]);
					selectedY = lightCellV;
					selectedX = lightCellH;
				}
				mouseClicked = false;
			}
			

			//Line Drawing
			g2d.setColor(Color.BLACK);
			i = 0;
			for (int y = gameSize; y <= gameSize*2.8; y += gameSize*0.2) {
				if (y == gameSize || y == gameSize + gameSize*0.2*3 || y == gameSize + gameSize*0.2*6 || y == gameSize + gameSize*0.2*9) g2d.setStroke(new BasicStroke(5));
				else g2d.setStroke(new BasicStroke(1));
				g2d.drawLine(x1, y, x1+width, y);
				g2d.drawLine(y, y1, y, y1+width);
				if (i < 9){
					horiz[i] = new Rectangle2D.Double(gameSize, y, gameSize*1.8, gameSize*0.2);
					vert[i] = new Rectangle2D.Double(y, gameSize, gameSize*0.2, gameSize*1.8);
				}
				i++;
			}
			
			//Cells Generation
			for (int x = gameSize; x <= gameSize*2.6; x += gameSizeDiv) {
				for (int y = gameSize; y <= gameSize*2.6; y += gameSizeDiv) {
					cell[(x-gameSize)/gameSizeDiv][(y-gameSize)/gameSizeDiv] = new Rectangle2D.Double(x, y, gameSizeDiv, gameSizeDiv);
				}
			}

			//Value Labeler
			setLayout(null); //using absolute positioning
			for(int lb=0; lb<9; lb++){
				for(int lb2=0; lb2<9; lb2++){
					
					int Xcoord = lb2*gameSizeDiv+gameSize+gameSizeDiv/4; 
					int Ycoord = lb*gameSizeDiv+gameSize+gameSizeDiv/4;
					
					if(board.board[lb][lb2].getValue() == unassigned){
						labelsArr[lb][lb2].setFont(Smallfont);
						labelsArr[lb][lb2].setText(disSub(board.board[lb][lb2].getSub()));
						labelsArr[lb][lb2].setBounds(Xcoord-gameSizeDiv/2+gameSizeDiv/4,Ycoord-gameSizeDiv/2-gameSizeDiv/4,gameSizeDiv,gameSizeDiv*2);	
						add(labelsArr[lb][lb2]);
					}else if(board.board[lb][lb2].getValue() < 0){
						labelsArr[lb][lb2].setFont(fontBold);
						labelsArr[lb][lb2].setText(Integer.toString(board.board[lb][lb2].getValue()*(-1)-ProgMode));
						labelsArr[lb][lb2].setBounds(Xcoord,Ycoord,gameSizeDiv/2,gameSizeDiv/2);
						add(labelsArr[lb][lb2]);
					}else{
						labelsArr[lb][lb2].setFont(font);
						labelsArr[lb][lb2].setText(Integer.toString(board.board[lb][lb2].getValue()-ProgMode));
						labelsArr[lb][lb2].setBounds(Xcoord,Ycoord,gameSizeDiv/2,gameSizeDiv/2);
						add(labelsArr[lb][lb2]);
					}
					setVisible(true); 
				}		
			}
			
			Rectangle2D paused = new Rectangle2D.Double(gameSize, gameSize, gameSize*1.8, gameSize*1.8);
			g2d.setColor(BgPause);
			g2d.fill(paused);
			
			Random numbCells = new Random();
			Random coord = new Random();
			Random col = new Random();
			
			int numberOfCells = numbCells.nextInt(3) + 1;
			for (int  z= 0; z < numberOfCells; z++) {
				g2d.setColor(partyColourArray[col.nextInt(16)]);
				g2d.fill(cell[coord.nextInt(9)][coord.nextInt(9)]);
			}
						
			repaint();
			
						
		} else {
			g.drawImage(GUI.BGI, 0, 0, getWidth(), getHeight(), null);
			
			Graphics2D g2d = (Graphics2D)g;		
		
			//boxes		
			int i = 0;
			for (int h=gameSize; h<=gameSize*2.2; h+=gameSizeDiv*3) {
				for (int l=gameSize; l<=gameSize*2.2; l+=gameSizeDiv*3) {
					box[i] = new Rectangle2D.Double(l, h, gameSizeDiv*3, gameSizeDiv*3);	
					i++;
				}
			}
			
			//Board Selection Highlighting
			Rectangle2D rect = new Rectangle2D.Double(gameSize, gameSize, gameSize*1.8, gameSize*1.8);
			g2d.setColor(BgBoard);
			g2d.fill(rect);
			
			//cell selection
			if(mouseClicked){
				if(pauseGame == false){
					g2d.setColor(GUI.color);			
					g2d.fill(box[lightBox]);
					
					//g2d.setColor(color);
					g2d.fill(horiz[lightHoriz]);
					g2d.fill(vert[lightVert]);
							
					//g2d.setColor(color);
					g2d.fill(cell[lightCellV][lightCellH]);
					selectedY = lightCellV;
					selectedX = lightCellH;
				}
				mouseClicked = false;
			}

			//Line Drawing
			g2d.setColor(Color.BLACK);
			i = 0;
			for (int y = gameSize; y <= gameSize*2.8; y += gameSize*0.2) {
				if (y == gameSize || y == gameSize + gameSize*0.2*3 || y == gameSize + gameSize*0.2*6 || y == gameSize + gameSize*0.2*9) g2d.setStroke(new BasicStroke(5));
				else g2d.setStroke(new BasicStroke(1));
				g2d.drawLine(x1, y, x1+width, y);
				g2d.drawLine(y, y1, y, y1+width);
				if (i < 9){
					horiz[i] = new Rectangle2D.Double(gameSize, y, gameSize*1.8, gameSize*0.2);
					vert[i] = new Rectangle2D.Double(y, gameSize, gameSize*0.2, gameSize*1.8);
				}
				i++;
			}
			
			//Cells Generation
			for (int x = gameSize; x <= gameSize*2.6; x += gameSizeDiv) {
				for (int y = gameSize; y <= gameSize*2.6; y += gameSizeDiv) {
					cell[(x-gameSize)/gameSizeDiv][(y-gameSize)/gameSizeDiv] = new Rectangle2D.Double(x, y, gameSizeDiv, gameSizeDiv);
				}
			}

			//Value Labeler
			setLayout(null); //using absolute positioning
			for(int lb=0; lb<9; lb++){
				for(int lb2=0; lb2<9; lb2++){
					
					int Xcoord = lb2*gameSizeDiv+gameSize+gameSizeDiv/4; 
					int Ycoord = lb*gameSizeDiv+gameSize+gameSizeDiv/4;
					
					if(board.board[lb][lb2].getValue() == unassigned){
						labelsArr[lb][lb2].setFont(Smallfont);
						labelsArr[lb][lb2].setText(disSub(board.board[lb][lb2].getSub()));
						labelsArr[lb][lb2].setBounds(Xcoord-gameSizeDiv/2+gameSizeDiv/4,Ycoord-gameSizeDiv/2-gameSizeDiv/4,gameSizeDiv,gameSizeDiv*2);	
						add(labelsArr[lb][lb2]);
					}else if(board.board[lb][lb2].getValue() < 0){
						labelsArr[lb][lb2].setFont(GUI.boldFont);
						labelsArr[lb][lb2].setText(Integer.toString(board.board[lb][lb2].getValue()*(-1)-ProgMode));
						labelsArr[lb][lb2].setBounds(Xcoord,Ycoord,gameSizeDiv/2,gameSizeDiv/2);
						add(labelsArr[lb][lb2]);
					}else{
						labelsArr[lb][lb2].setFont(font);
						labelsArr[lb][lb2].setText(Integer.toString(board.board[lb][lb2].getValue()-ProgMode));
						labelsArr[lb][lb2].setBounds(Xcoord,Ycoord,gameSizeDiv/2,gameSizeDiv/2);
						add(labelsArr[lb][lb2]);
					}
					setVisible(true); 
				}		
			}
			Rectangle2D paused = new Rectangle2D.Double(gameSize, gameSize, gameSize*1.8, gameSize*1.8);
			g2d.setColor(BgPause);
			g2d.fill(paused);
			
		}
		
		
		
		
	}
	
	/**
	 * 
	 * transition: loads a game
	 * input: name of file to load
	 * 
	 * exception: FileNotFoundException
	 * 
	 * @param loadName
	 * @return
	 */
	public static boolean LoadGame(String loadName){
		try {
			Board temp = Slave.load(loadName);
			board = new Board(temp.board,temp.original,temp.full);
			return true;
		} catch (FileNotFoundException e) {
			return false;
		}
	}
	
	/**
	 * 
	 * @return Label[][]
	 * 
	 * transition: Constructs and fills a double array of Labels and aligns them to the center
	 * 
	 * exception: none
	 */
		private JLabel[][] JlabelsMaker(){
			JLabel[][] Jlabels = new JLabel[9][9];
			
			for(int i=0; i<9; i++){
				for(int j=0; j<9; j++){
					Jlabels[i][j] = new JLabel();
					Jlabels[i][j].setHorizontalAlignment(SwingConstants.CENTER);
					Jlabels[i][j].setVerticalAlignment(SwingConstants.CENTER);
					Jlabels[i][j].setForeground (GUI.FontC);
					Jlabels[i][j].setFont(font);
					add(Jlabels[i][j]);
				}
			}
			return Jlabels;
		}
		
		protected void JReColor(){
			JlabelsReColor(labelsArr);
		}
		
	/**
	 * 
	 * @param Jlabels
	 * 
	 * transition: Recolours the label font 
	 * 
	 * exception: none
	 */
		private void JlabelsReColor(JLabel[][] Jlabels){
			for(int i=0; i<9; i++){
				for(int j=0; j<9; j++){
					Jlabels[i][j].setForeground (GUI.FontC);
				}
			}
		}
		
		/** 
		 * 
		 * @param str A string of up to 9 integers 
		 * @return string of spaced out characters with line breaks 
		 * 
		 * transition: Takes a string of up to 9 subvalues and returns them with spaces and line breaks
		 * 
		 * exception: none
		 */
		public String disSub(int[] subs){
			for (int j = 0; j < subs.length; j++) {
				if (subs[j] == 0) {
					int[] temp = new int[subs.length - 1];
					for (int k = 0; k < j; k ++) {
						temp[k] = subs[k];
					}
					for (int k = j; k < subs.length - 1; k++) {
						temp[k] = subs[k+1];
					}
					subs = new int[temp.length];
					for (int l = 0; l < temp.length; l++) {
						subs[l] = temp[l];
					}
					j--;
				}
			}
			String[] strP = new String[subs.length];
			for (int i = 0; i < subs.length; i++) {
				strP[i] = Integer.toString(subs[i]);
			}
			
			String print = "<html>";
			
			for(int i = 0; i<strP.length; i++){
				if(i==2||i==5){
					print = print + strP[i] + "<br>" ;
				}else{
					print = print + strP[i] + " ";
				}
			}	
			return  print + "</html>";
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
		public void mouseClicked(MouseEvent e) {
			for(int i=0; i<9; i++){
				if (horiz[i].contains(e.getPoint())) {
					lightHoriz = i;
					lightCellH = i;
				}
				if (vert[i].contains(e.getPoint())) {
					lightVert = i;
					lightCellV = i;
				}
				if (box[i].contains(e.getPoint())) {
					lightBox = i;
					mouseClicked = true;
					hold = true;
				}
			}
			requestFocusInWindow();
			if (!GUI.partyMode) {
				repaint();
			}
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
		 * 
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
		 * input: mouse released
		 * 
		 * exception: none
		 */	
		@Override
		public void mouseReleased(MouseEvent arg0) {
		
		}

		/**
		 * 
		 * @param e
		 * 
		 * transition: inputs values
		 * input: key press
		 * 
		 * exception: none
		 */	
		@Override
		public void keyPressed(KeyEvent e) { 
				int val = -1;
				int Yspot = (lightHoriz+1)*gameSizeDiv+gameSize+gameSizeDiv/4; 
				int Xspot = (lightVert+1)*gameSizeDiv+gameSize+gameSizeDiv/4;
				
			if(e.getKeyCode() == KeyEvent.VK_NUMPAD1 ||e.getKeyCode() == 49){
				val = 1;
				code=0;
		    }	
			else if(e.getKeyCode() == KeyEvent.VK_NUMPAD2 ||e.getKeyCode() == 50){
		    	val = 2;
		    	code=0;
		    }
			else if(e.getKeyCode() == KeyEvent.VK_NUMPAD3 ||e.getKeyCode() == 51){
		    	val = 3;
		    	code=0;
		    }
			else if(e.getKeyCode() == KeyEvent.VK_NUMPAD4 ||e.getKeyCode() == 52){
		    	val = 4;
		    	code=0;
		    }
			else if(e.getKeyCode() == KeyEvent.VK_NUMPAD5 ||e.getKeyCode() == 53){
		    	val = 5;
		    	code=0;
		    }
			else if(e.getKeyCode() == KeyEvent.VK_NUMPAD6 ||e.getKeyCode() == 54){
		    	val = 6;
		    	code=0;
		    }
			else if(e.getKeyCode() == KeyEvent.VK_NUMPAD7 ||e.getKeyCode() == 55){
		    	val = 7;
		    	code=0;
		    }
			else if(e.getKeyCode() == KeyEvent.VK_NUMPAD8 ||e.getKeyCode() == 56){
		    	val = 8;
		    	code=0;
		    }
			else if(e.getKeyCode() == KeyEvent.VK_NUMPAD9 ||e.getKeyCode() == 57){
		    	val = 9;
		    	code=0;
		    }
			else if(e.getKeyCode() == KeyEvent.VK_NUMPAD0 ||e.getKeyCode() == 48){
		    	val = 0;
		    	code=0;
		    }
			
			else if(e.getKeyCode() == 8 || e.getKeyCode() == 127){
		    	val = 0;
		    	code=0;
		    }
			
			// Up
			else if(e.getKeyCode() == 38){
				if (lightCellH != 0) {
					lightHoriz--;
					lightCellH--;
			    	code=0;
			    	mouseClicked = true;
			    	for(int i=0; i<9; i++){
				    	if (box[i].contains(new Point(Xspot-gameSizeDiv,Yspot-2*gameSizeDiv))) {
				    		lightBox = i;
							hold = true;
						}
			    	}
			    	repaint();
				}
		    }
			// Down
			else if(e.getKeyCode() == 40){
				if (lightCellH != 8) {
					lightHoriz++;
					lightCellH++;
			    	code=0;
			    	mouseClicked = true;
			    	for(int i=0; i<9; i++){
				    	if (box[i].contains(new Point(Xspot-gameSizeDiv,Yspot))) {
				    		lightBox = i;
							hold = true;
						}
			    	}
			    	repaint();
				}
		    }
			// Left
			else if(e.getKeyCode() == 37){
				if (lightCellV != 0) {
					lightVert--;
					lightCellV--;
			    	code=0;
			    	mouseClicked = true;
			    	for(int i=0; i<9; i++){
				    	if (box[i].contains(new Point(Xspot-2*gameSizeDiv,Yspot-gameSizeDiv))) {
							lightBox = i;
							hold = true;
						}
			    	}
			    	repaint();
				}
		    }
			// Right
			else if(e.getKeyCode() == 39){
				if (lightCellV != 8) {
					lightVert++;
					lightCellV++;
			    	code=0;
			    	mouseClicked = true;
			    	for(int i=0; i<9; i++){
				    	if (box[i].contains(new Point(Xspot,Yspot-gameSizeDiv))) {
							lightBox = i;
							hold = true;
						}
			    	}
			    	repaint();
				}
		    }
			
		    
		    if(e.getKeyCode() == KeyEvent.VK_SHIFT){
		    	shifting = true;
		    }
		    if(val == -1 || selectedY == 10 || selectedX == 10){ return;}
		    
	    	if(shifting == true && pauseGame == false){  
		    	if(board.board[selectedX][selectedY].getValue() == 0 && val != 0){ //check if the cell is locked
		    		if(!board.board[selectedX][selectedY].checkSub(val)) {
		    			board.board[selectedX][selectedY].unassignSub(val);
		    			selectedX = 10;
		    	    	selectedY = 10;
		    		} else {
		    			board.board[selectedX][selectedY].setSub(val);
		    			selectedX = 10;
		    	    	selectedY = 10;
		    		}
		     	} else {
		     		board.board[selectedX][selectedY].clearSub();
		     		selectedX = 10;
		        	selectedY = 10;
		     	}
		    }else if(pauseGame == false){
		    	if(board.board[selectedX][selectedY].getValue() >= 0){ //check for unchangeable values
		    		board.board[selectedX][selectedY].setValue(val);
		    		if (Checker.checker(board)) {
		    			won=true;
		    			buttonWin();
		    			selectedX = 10;
		    	    	selectedY = 10;
		    		}	
		     	}
		    }
	    	hold = false;
	    	
		    if (!GUI.partyMode) {
		    	repaint();
		    }
		}

		/**
		 * 
		 * @param e
		 * 
		 * transition: detect shift key
		 * input: key release
		 * 
		 * exception: none
		 */	
		@Override
		public void keyReleased(KeyEvent e) {
		    if(e.getKeyCode() == KeyEvent.VK_SHIFT){
		    	shifting = false;
		    }
		}

		/**
		 * @param e
		 * 
		 * transition:none
		 * input: key typed
		 * 
		 * exception: none
		 */	
		@Override
		public void keyTyped(KeyEvent arg0) { 
			
		}

}