package code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * The Credits for the game
 * 
 * @authors Johnny Endrizzi
 * Josh Chatten
 * Mitchell Coovert 
 *
 *
 * State Variables
 * //Board
 *  gameSize: used in all coordinates, can be changed to rescale all displayed objects, obtained from the controller
 *  gameSizeDiv: based off of gameSize, used in scalable coordinates 
 *  board: contains all the values for the board
 *	
 * //Board creation - Rectangle2D.Double arrays
 *	horiz: the lines in the grid 
 *	vert: the other lines in the grid
 *	box: the 3x3 boxes on the game board
 *	cell: the cells on the game board
 *  
 * //Buttons
 *  buttonBack: button used to go back to the menu 
 *   
 * //Font
 *  font: font used for the text
 *  Smallfont: font used for the subvalues on the board
 *  Bigfont: font used for the buttons 
 *  FontCol: current font colour 
 *  FontClr: clear font colour
 *  FontWhi: white font
 *  
 *  //Misc.
 *  Theme: the current theme 
 *  int OldTheme: Backup for themes to allow canceling
 *	OldBGI: Backup for themes to allow canceling 
 *	OldColor: Backup for themes to allow canceling
 *  OldBut: Backup for themes to allow canceling
 *  OldButH: Backup for themes to allow canceling
 *  
 * //Board colours
 *  BgWindow: default background colour
 *  BGWhite: white background
 *  BgBoard: Defauld board background colour
 *  BgPause: colour of the paused board 
 *  
 * //Images
 *  title: the game title image
 *  creds: the credits text
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
 *	none
 *
 */
public class CreditsPanel extends JPanel implements MouseListener, KeyListener{
	private static final long serialVersionUID = 1L;	
	
	//Board 
	private int gameSize = GUI.gameSize;
	private int gameSizeDiv = (int) (gameSize*0.2);
	Board board;
	
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
	private BufferedImage creds;
		
	public CreditsPanel(){
	
		setLayout(null);
		buttonBack("Back", 1);
        
	}
	
	//====================================================
	/**
	 * transitions: Back button
	 * 
	 * exception: none
	 * 
	 * @param str
	 * @param pos
	 */
	public void buttonBack(String str, int pos){
		ButtonCreds ButtonHandler = new ButtonCreds();
		buttonBack = new JButton(str);
		buttonBack.setIcon(new ImageIcon(GUI.But));
		buttonBack.setPreferredSize(new Dimension(gameSizeDiv*6, gameSizeDiv*2));
		buttonBack.setBounds(GUI.gameSizeX/2 + gameSizeDiv*2, gameSize*3 + gameSizeDiv, gameSizeDiv*5, gameSizeDiv);
		buttonBack.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonBack.setRolloverIcon(new ImageIcon(GUI.ButHover));
		buttonBack.addActionListener(ButtonHandler);
		buttonBack.setFont(Bigfont);
		buttonBack.setForeground(FontWhi);
		add(buttonBack);
		buttonBack.setVisible(true);
	}
	
	//====================================================
	
	/**  
	 * @author Johnny Endrizzi
	 * Josh Chatten
	 * Mitchell Coovert 
	 *
	 * State Variables
	 * 	event: gets the name of the button that has been pressed
	 * 	CL: changes between menu and game layers
	 * 
	 * Environment Variables 
	 * 
	 * Assumptions
	 * 	Will receive a defined button
	 */
	private class ButtonCreds implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			
			String str = String.format("%s", event.getActionCommand());
			if(str.equals("Back")){
		
				GUI.CL.next(GUI.MainPanel);
				repaint();	
			}
			repaint();
		}
	}
	

	/**
	 * transition: Adds the visual elements to the credits GUI including the title and the background
	 * 
	 * exception: none
	 */
	@Override
	public void paintComponent(Graphics g) {
		try {
			title = ImageIO.read(new File("src/code/res/Title.png"));
			creds = ImageIO.read(new File("src/code/res/credits.png"));
		} catch (IOException e) {
		}
		Color BgBoard = new Color(170, 154, 154, 150);
		setBackground(BgBoard);
		
			g.drawImage(GUI.BGI, 0, 0, getWidth(), getHeight(), null);
			g.drawImage(title, 0,0,500,500,null);
			g.drawImage(creds, 150,170,800,800,null);
			Graphics2D g2d = (Graphics2D)g;		
		
			
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
	 * @param e
	 * 
	 * transition: none
	 * input: mouse clicked
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

}
			