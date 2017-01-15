package code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * The menu for the game
 * 
 * @authors Johnny Endrizzi
 * Josh Chatten
 * Mitchell Coovert 
 *
 *
 * State Variables
 *  gameSize: used in all coordinates, can be changed to rescale all displayed objects
 *  gameSizeDiv: based off of gameSize, used in scalable coordinates 
 *  board: contains all the values for the board
 *  
 *  JButton buttonNewG: button used to start a new game
 *  JButton buttonLoad: button used to load a game
 *  JButton buttonHtP: button used to go to how to play 
 *  JButton buttonSettings: button used to go to settings
 *  JButton buttonCredit: button used to go to credits
 *  JButton buttonQuit: button used to quit the game
 *  
 *  JButton buttonEasy: button used to start game easy mode
 *  JButton buttonMed: button used to start game medium mode
 *  JButton buttonHard: button used to start game hard mode
 *  JButton buttonBack: button used to go back to menu after clicking new game 
 *  
 * font: font used for the text
 * Smallfont: font used for the subvalues on the board
 * Bigfont: font used for the buttons 
 * FontCol: current font colour 
 * FontClr: clear font colour
 * FontWhi: white font
 *  
 * BgWindow: default background colour
 * BGWhite: white background
 * BgBoard: Defauld board background colour
 * BgPause: colour of the paused board 
 *  
 *  title: image for the game title
 *  Diff: game difficulty
 *  
 *  
 * Environment Variables
 * 	keylistener: gets key inputs
 * 	mouselistener: gets mouse clicks
 * 	
 * 	
 *  
 * Assumptions
 *	 None
 *
 */
public class MenuPanel extends JPanel implements MouseListener, KeyListener{
	private static final long serialVersionUID = 1L;	
	
	//Board 
	private int gameSize = GUI.gameSize;
	private int gameSizeDiv = (int) (gameSize*0.2);
	Board board;
	
	//Buttons
	protected JButton buttonNewG;
	protected JButton buttonLoad;
	protected JButton buttonHtP;
	protected JButton buttonSettings;
	protected JButton buttonCredit;
	protected JButton buttonQuit;
	
	protected JButton buttonEasy;
	protected JButton buttonMed;
	protected JButton buttonHard;
	protected JButton buttonBack;
	
	//Font
	Font font = new Font("Serif", Font.BOLD, gameSizeDiv/2);
	Font Smallfont = new Font("Serif", Font.BOLD, gameSizeDiv/4);
	Font Bigfont = new Font("Lucida Calligraphy", Font.BOLD, gameSizeDiv/2); //TODO Check if font exists
	Color FontCol = new Color(0,0,0);
	Color FontClr = new Color(200,200,200);
	Color FontWhi = new Color(200,200,200);
	
	//Board colors
	Color BgWindow = new Color(0x53152A); //default background
	Color BGWhite = new Color(0xffffff);
	Color BgBoard = new Color(170, 154, 154, 150); 
	Color BgPause = new Color(200, 200, 200, 0); 
	
	//Background
	private BufferedImage title;
	
	//Misc.
	static int Diff = 0;
	
	
	public MenuPanel(){
		
		GUI.BGI = GUI.BG1;		
		GUI.color = GUI.ThemeCyan;
						
		GUI.But = GUI.But1;
		GUI.ButHover = GUI.But2;
	
		buttons();
		setLayout(null);
		buttonsDiff();
		        
       
	}
	
	/** 
	 * transitions: Sets up buttons
	 * 
	 * exception: none
	 */
	public void buttons(){
		setLayout(null);
		
		buttonNewG("New Game",1);
		buttonLoad("Load Game",2);
		buttonHtP("How to Play",3);
		buttonSettings("Settings",4);
		buttonCredit("Credits",5);
		buttonQuit("Quit",6);
	}
	
	/**
	 * transitions: Sets up difficulty buttons
	 * 
	 * exception: none 
	 */
	public void buttonsDiff(){
		setLayout(null);
		
		buttonEasy("Easy",1);
		buttonMed("Medium",2);
		buttonHard("Hard",3);
		buttonBack("Back",4);
	}
	
	/**
	 * transitions: New Game button
	 * 
	 * exception: none
	 * 
	 * @param str
	 * @param pos
	 */
	public void buttonNewG(String str, int pos){ 
		ButtonThings ButtonHandler = new ButtonThings();
		buttonNewG = new JButton(str, new ImageIcon(GUI.But));
		buttonNewG.setPreferredSize(new Dimension(gameSizeDiv*2, gameSizeDiv));
		buttonNewG.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonNewG.setBounds(gameSize/6, gameSize/3*pos+gameSize*2/3, gameSizeDiv*5, gameSizeDiv); 
		buttonNewG.setRolloverIcon(new ImageIcon(GUI.ButHover));
		buttonNewG.addActionListener(ButtonHandler);
		buttonNewG.setFont(Bigfont);
		buttonNewG.setForeground(FontWhi);
		add(buttonNewG);
		buttonNewG.setVisible(true);
	}
	/**
	 * transitions: Load Game button
	 * 
	 * exception: none
	 * 
	 * @param str
	 * @param pos
	 */
	public void buttonLoad(String str, int pos){ 
		ButtonThings ButtonHandler = new ButtonThings();
		buttonLoad = new JButton(str, new ImageIcon(GUI.But));
		buttonLoad.setPreferredSize(new Dimension(gameSizeDiv*2, gameSizeDiv));
		buttonLoad.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonLoad.setBounds(gameSize/6, gameSize/3*pos+gameSize*2/3, gameSizeDiv*5, gameSizeDiv);
		buttonLoad.setRolloverIcon(new ImageIcon(GUI.ButHover));
		buttonLoad.addActionListener(ButtonHandler);
		buttonLoad.setFont(Bigfont);
		buttonLoad.setForeground(FontWhi);
		add(buttonLoad);
		buttonLoad.setVisible(true);
	}
	/**
	 * transitions: How to Play button
	 * 
	 * exception: none
	 * 
	 * @param str
	 * @param pos
	 */
	public void buttonHtP(String str, int pos){ 
		ButtonThings ButtonHandler = new ButtonThings();
		buttonHtP = new JButton(str, new ImageIcon(GUI.But));
		buttonHtP.setPreferredSize(new Dimension(gameSizeDiv*2, gameSizeDiv));
		buttonHtP.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonHtP.setBounds(gameSize/6, gameSize/3*pos+gameSize*2/3, gameSizeDiv*5, gameSizeDiv); 
		buttonHtP.setRolloverIcon(new ImageIcon(GUI.ButHover));
		buttonHtP.addActionListener(ButtonHandler);
		buttonHtP.setFont(Bigfont);
		buttonHtP.setForeground(FontWhi);
		add(buttonHtP);
		buttonHtP.setVisible(true);
	}
	/**
	 * transitions: Settings button
	 * 
	 * exception: none
	 * 
	 * @param str
	 * @param pos
	 */
	public void buttonSettings(String str, int pos){ 
		ButtonThings ButtonHandler = new ButtonThings();
		buttonSettings = new JButton(str, new ImageIcon(GUI.But));
		buttonSettings.setPreferredSize(new Dimension(gameSizeDiv*2, gameSizeDiv));
		buttonSettings.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonSettings.setBounds(gameSize/6, gameSize/3*pos+gameSize*2/3, gameSizeDiv*5, gameSizeDiv); 
		buttonSettings.setRolloverIcon(new ImageIcon(GUI.ButHover));
		buttonSettings.addActionListener(ButtonHandler);
		buttonSettings.setFont(Bigfont);
		buttonSettings.setForeground(FontWhi);
		add(buttonSettings);
		buttonSettings.setVisible(true);
	}
	/**
	 * transitions: Credits button
	 * 
	 * exception: none
	 * 
	 * @param str
	 * @param pos
	 */
	public void buttonCredit(String str, int pos){ 
		ButtonThings ButtonHandler = new ButtonThings();
		buttonCredit = new JButton(str, new ImageIcon(GUI.But));
		buttonCredit.setPreferredSize(new Dimension(gameSizeDiv*2, gameSizeDiv));
		buttonCredit.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonCredit.setBounds(gameSize/6, gameSize/3*pos+gameSize*2/3, gameSizeDiv*5, gameSizeDiv); 
		buttonCredit.setRolloverIcon(new ImageIcon(GUI.ButHover));
		buttonCredit.addActionListener(ButtonHandler);
		buttonCredit.setFont(Bigfont);
		buttonCredit.setForeground(FontWhi);
		add(buttonCredit);
		buttonCredit.setVisible(true);
	}
	/**
	 * transitions: Quit button
	 * 
	 * exception: none
	 * 
	 * @param str
	 * @param pos
	 */
	public void buttonQuit(String str, int pos){ 
		ButtonThings ButtonHandler = new ButtonThings();
		buttonQuit = new JButton(str, new ImageIcon(GUI.But));
		buttonQuit.setPreferredSize(new Dimension(gameSizeDiv*2, gameSizeDiv));
		buttonQuit.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonQuit.setBounds(gameSize/6, gameSize/3*pos+gameSize*2/3, gameSizeDiv*5, gameSizeDiv); 
		buttonQuit.setRolloverIcon(new ImageIcon(GUI.ButHover));
		buttonQuit.addActionListener(ButtonHandler);
		buttonQuit.setFont(Bigfont);
		buttonQuit.setForeground(FontWhi);
		add(buttonQuit);
		buttonQuit.setVisible(true);
	}
	
	/**
	 * transitions: Easy Game button
	 * 
	 * exception: none
	 * 
	 * @param str
	 * @param pos
	 */
	public void buttonEasy(String str, int pos){ 
		ButtonThings ButtonHandler = new ButtonThings();
		buttonEasy = new JButton(str, new ImageIcon(GUI.But));
		buttonEasy.setPreferredSize(new Dimension(gameSizeDiv*2, gameSizeDiv));
		buttonEasy.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonEasy.setBounds(gameSize*3-gameSizeDiv*3+gameSizeDiv, gameSize/3*pos+gameSize*2/3, gameSizeDiv*4, gameSizeDiv); 
		buttonEasy.setRolloverIcon(new ImageIcon(GUI.ButHover));
		buttonEasy.addActionListener(ButtonHandler);
		buttonEasy.setFont(Bigfont);
		buttonEasy.setForeground(FontWhi);
		add(buttonEasy);
		buttonEasy.setVisible(false);
	}
	/**
	 * transitions: Medium Game button
	 * 
	 * exception: none
	 * 
	 * @param str
	 * @param pos
	 */
	public void buttonMed(String str, int pos){ 
		ButtonThings ButtonHandler = new ButtonThings();
		buttonMed = new JButton(str, new ImageIcon(GUI.But));
		buttonMed.setPreferredSize(new Dimension(gameSizeDiv*2, gameSizeDiv));
		buttonMed.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonMed.setBounds(gameSize*3-gameSizeDiv*3+gameSizeDiv, gameSize/3*pos+gameSize*2/3, gameSizeDiv*4, gameSizeDiv); 
		buttonMed.setRolloverIcon(new ImageIcon(GUI.ButHover));
		buttonMed.addActionListener(ButtonHandler);
		buttonMed.setFont(Bigfont);
		buttonMed.setForeground(FontWhi);
		add(buttonMed);
		buttonMed.setVisible(false);
	}
	/**
	 * transitions: Hard Game button
	 * 
	 * exception: none
	 * 
	 * @param str
	 * @param pos
	 */
	public void buttonHard(String str, int pos){ 
		ButtonThings ButtonHandler = new ButtonThings();
		buttonHard = new JButton(str);
		buttonHard.setIcon(new ImageIcon(GUI.But));
		buttonHard.setPreferredSize(new Dimension(gameSizeDiv*2, gameSizeDiv));
		buttonHard.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonHard.setBounds(gameSize*3-gameSizeDiv*3+gameSizeDiv,gameSize/3*pos+gameSize*2/3, gameSizeDiv*4, gameSizeDiv); 
		buttonHard.setRolloverIcon(new ImageIcon(GUI.ButHover));
		buttonHard.addActionListener(ButtonHandler);
		buttonHard.setFont(Bigfont);
		buttonHard.setForeground(FontWhi);
		add(buttonHard);
		buttonHard.setVisible(false);
	}
	/**
	 * transitions: Back button
	 * 
	 * exception: none
	 * 
	 * @param str
	 * @param pos
	 */
	public void buttonBack(String str, int pos){ 
		ButtonThings ButtonHandler = new ButtonThings();
		buttonBack = new JButton(str, new ImageIcon(GUI.But));
		buttonBack.setPreferredSize(new Dimension(gameSizeDiv*2, gameSizeDiv));
		buttonBack.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonBack.setBounds(gameSize*3-gameSizeDiv*3+gameSizeDiv,gameSize/3*pos+gameSize*2/3, gameSizeDiv*4, gameSizeDiv); 
		buttonBack.setRolloverIcon(new ImageIcon(GUI.ButHover));
		buttonBack.addActionListener(ButtonHandler);
		buttonBack.setFont(Bigfont);
		buttonBack.setForeground(FontWhi);
		add(buttonBack);
		buttonBack.setVisible(false);
	}
	
	
	/**  
	 * @author Johnny Endrizzi
	 * Josh Chatten
	 * Mitchell Coovert 
	 *
	 * State Variables
	 * 	event: gets the name of the button that has been pressed
	 * 
	 * Environment Variables
	 * 	showInputDialog: gets a name of a save file to create/overwrite
	 * 	showInputDialog: gets a name of a save file to load  
	 * 
	 * Assumptions
	 * 	Will receive a key press
	 */
	private class ButtonThings implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			//JOptionPane.showMessageDialog(null, String.format("%s", event.getActionCommand()));
			
			String str = String.format("%s", event.getActionCommand());
			if(str.equals("New Game")){
				buttonEasy.setVisible(true);
				buttonMed.setVisible(true);
				buttonHard.setVisible(true);
				buttonBack.setVisible(true);				
			}if(str.equals("Load Game")){
				String loadName = JOptionPane.showInputDialog(null, String.format("%s", event.getActionCommand()),"Load Game", 2);
				if (GamePanel.LoadGame(loadName) == true){GUI.CL.next(GUI.MainPanel);}
				else{
					Object[] Options = {"OK"};
					JOptionPane.showOptionDialog(null, "Savefile not Found", "Sudoku!", JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, null,Options,Options[0]);
				}				
				
			}else if(str.equals("How to Play")){
				GUI.CL.next(GUI.MainPanel);
				GUI.CL.next(GUI.MainPanel);
				GUI.CL.next(GUI.MainPanel);
			}else if(str.equals("Settings")){
				GUI.CL.next(GUI.MainPanel);
				GUI.CL.next(GUI.MainPanel);
			}else if(str.equals("Credits")){
				GUI.CL.next(GUI.MainPanel);
				GUI.CL.next(GUI.MainPanel);
				GUI.CL.next(GUI.MainPanel);
				GUI.CL.next(GUI.MainPanel);
			}else if(str.equals("Quit")){
				Object[] Options = {"Quit","Back to safety"};
				int N = JOptionPane.showOptionDialog(null, "Are you Sure you want to Quit?", "Sudoku!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null,Options,Options[1]);
				if(N == 0){GUI.mp3.close();GUI.f.dispose();}
				else if(N==1){
					buttonEasy.setVisible(false);
					buttonMed.setVisible(false);
					buttonHard.setVisible(false);
					buttonBack.setVisible(false);
					}
				
			}else if(str.equals("Easy")){
				Diff = 1;
				GamePanel.NewBoard();
				buttonEasy.setVisible(false);
				buttonMed.setVisible(false);
				buttonHard.setVisible(false);
				buttonBack.setVisible(false);
				GUI.CL.next(GUI.MainPanel);
			}else if(str.equals("Medium")){
				Diff = 2;
				GamePanel.NewBoard();
				buttonEasy.setVisible(false);
				buttonMed.setVisible(false);
				buttonHard.setVisible(false);
				buttonBack.setVisible(false);
				GUI.CL.next(GUI.MainPanel);
			}else if(str.equals("Hard")){
				Diff = 3;
				GamePanel.NewBoard();
				buttonEasy.setVisible(false);
				buttonMed.setVisible(false);
				buttonHard.setVisible(false);
				buttonBack.setVisible(false);
				GUI.CL.next(GUI.MainPanel);
			}else if(str.equals("Back")){
				buttonEasy.setVisible(false);
				buttonMed.setVisible(false);
				buttonHard.setVisible(false);
				buttonBack.setVisible(false);
				//button.setVisible(false);
			}
		}
	}
	
	/**
	 * transition: gets difficulty level
	 * 
	 * exception: none
	 * 
	 * @return
	 */
	public static Difficulty getDiff() {
		if (Diff == 1){ return Difficulty.Easy;}
		else if (Diff == 2){ return Difficulty.Medium;}
		else if (Diff == 3){ return Difficulty.Hard;}
		else{ return Difficulty.Default;}
	}	
	
	/**
	 * transition: Adds the visual elements to the Menu GUI including the title and the Background
	 * 
	 * exception: none
	 */
	@Override
	public void paintComponent(Graphics g) {
		try {
			title = ImageIO.read(new File("src/code/res/Title.png"));
		} catch (IOException e) { System.out.println("RUINED!");
		}
		
		g.drawImage(GUI.BGI, 0, 0, getWidth(), getHeight(), null);
		g.drawImage(title, 50,30,500,500,null);
//		Graphics2D g2d = (Graphics2D)g;		
		
	}

	/**
	 * 
	 * transition: none
	 * input: key pressed
	 * 
	 * exception: none
	 */
	@Override
	public void keyPressed(KeyEvent arg0) {

	}
	/**
	 * 
	 * transition: none
	 * input: key released
	 * 
	 * exception: none
	 */
	@Override
	public void keyReleased(KeyEvent arg0) {

	}
	/**
	 * 
	 * transition: none
	 * input: key typed
	 * 
	 * exception: none
	 */
	@Override
	public void keyTyped(KeyEvent arg0) {

	}

	/**
	 * 
	 * @param e
	 * 
	 * transition: repaints the gui
	 * input: mouse click
	 * 
	 * exception: none
	 */	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		repaint();
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
	 * input: mouse pressed
	 * 
	 * exception: none
	 */	
	@Override
	public void mouseReleased(MouseEvent arg0) {

	}
}