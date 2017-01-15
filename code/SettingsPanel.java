package code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
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
 * The Settings Panel
 * 
 * @authors Johnny Endrizzi
 * Josh Chatten
 * Mitchell Coovert 
 *
 *
 * State Variables
 *  gameSize: used in all coordinates, can be changed to rescale all displayed objects
 *  gameSizeDiv: based off of gameSize, used in scalable coordinates 
 *  
 *  buttonOK: accept changes and return to menu
 *  buttonCanc: return to menu and revert changes
 *  buttonApp: accept changes
 *  buttonTGreen: change theme to Green
 *  buttonTRed: change theme to Red
 *  buttonTBlue: change theme to Blue
 *  buttonTGrey: change theme to Grey
 *  buttonTMaroon: change theme to Maroon
 *  buttonTYellow: change theme to Yellow
 *  
 * //Font
 *  font: font used for the text
 *  Smallfont: font used for the subvalues on the board
 *  Bigfont: font used for the buttons 
 *  FontCol: current font colour 
 *  FontClr: clear font colour
 *  FontWhi: white font
 *  
 * //Background Colours 
 *  BgWindow: default background colour
 *  BGWhite: white background
 *  BgBoard: Defauld board background colour
 *  BgPause: colour of the paused board 
 * 
 * //Colours 
 *  ColClr: Selection colours
 *  ColSel: Selection colours
 *  
 *  title: image for the game title
 *  
 *  //Misc.
 *  Theme: the current theme 
 *  int OldTheme: Backup for themes to allow canceling
 *	OldBGI: Backup for themes to allow canceling 
 *	OldColor: Backup for themes to allow canceling
 *  OldBut: Backup for themes to allow canceling
 *  OldButH: Backup for themes to allow canceling
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
public class SettingsPanel extends JPanel implements MouseListener, KeyListener{
	private static final long serialVersionUID = 1L;	
	
	//Board 
	private int gameSize = GUI.gameSize;
	private int gameSizeDiv = (int) (gameSize*0.2);

	//Buttons
	protected JButton buttonOK;
	protected JButton buttonCanc;
	protected JButton buttonApp;

	protected JButton buttonTGreen;
	protected JButton buttonTRed;
	protected JButton buttonTBlue;
	protected JButton buttonTGrey;
	protected JButton buttonTMaroon;
	protected JButton buttonTYellow;
	
	
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
	
	Color ColClr = new Color(80,255,80,0);
	Color ColSel = new Color(80,255,80,200);
	
	//Background
	private BufferedImage title;
	
	//Misc.
	int Theme = 1;
	int OldTheme = 1;
	
	BufferedImage OldBGI = GUI.BG1;
	Color OldColor = GUI.ThemeCyan;
	Image OldBut = GUI.But1;
	Image OldButH = GUI.But2;	
	
	
	public SettingsPanel(){
	
		buttonsOpt();
		buttonsTheme();
		        
	}
	
	/** 
	 * transitions: Sets up buttons
	 * 
	 * exception: none
	 */
	public void buttonsOpt(){
		setLayout(null);
		
		buttonOK("OK",1);
		buttonCanc("Cancel",2);
		buttonApp("Apply",3);
	
	}

	/** 
	 * transitions: Sets up theme buttons
	 * 
	 * exception: none
	 */
	public void buttonsTheme(){
		setLayout(null);
		
		buttonTGreen("Garden",1);
		buttonTRed("Uluru",2);
		buttonTBlue("Aurora",3);
		buttonTGrey("Space",4);
		buttonTMaroon("Sunset",5);
		buttonTYellow("Desert",6);
	}
	
	//====================================================
	

	/**
	 * 	 transitions: OK button
	 * 
	 * exception: none
	 * 
	 * @param str
	 * @param pos
	 */
	public void buttonOK(String str, int pos){
		ButtonWhat ButtonHandler = new ButtonWhat();
		buttonOK = new JButton(str);
		buttonOK.setIcon(new ImageIcon(GUI.But));
		buttonOK.setPreferredSize(new Dimension(gameSizeDiv*2, gameSizeDiv));
		buttonOK.setBounds(gameSize*(pos-1)+gameSizeDiv*1, gameSize*3 + gameSizeDiv, gameSizeDiv*5, gameSizeDiv);
		buttonOK.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonOK.setRolloverIcon(new ImageIcon(GUI.ButHover));
		buttonOK.addActionListener(ButtonHandler);
		buttonOK.setFont(Bigfont);
		buttonOK.setForeground(FontWhi);
		add(buttonOK);
		buttonOK.setVisible(true);
	}
	/**
	 * 	 transitions: Cancel button
	 * 
	 * exception: none
	 * 
	 * @param str
	 * @param pos
	 */
	public void buttonCanc(String str, int pos){
		ButtonWhat ButtonHandler = new ButtonWhat();
		buttonCanc = new JButton(str);
		buttonCanc.setIcon(new ImageIcon(GUI.But));
		buttonCanc.setPreferredSize(new Dimension(gameSizeDiv*2, gameSizeDiv));
		buttonCanc.setBounds(gameSize*(pos-1)+gameSizeDiv*2, gameSize*3 + gameSizeDiv, gameSizeDiv*5, gameSizeDiv);
		buttonCanc.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonCanc.setRolloverIcon(new ImageIcon(GUI.ButHover));
		buttonCanc.addActionListener(ButtonHandler);
		buttonCanc.setFont(Bigfont);
		buttonCanc.setForeground(FontWhi);
		add(buttonCanc);
		buttonCanc.setVisible(true);
	}
	
	/**
	 * 	 transitions: Apply button
	 * 
	 * exception: none
	 * 
	 * @param str
	 * @param pos
	 */
	public void buttonApp(String str, int pos){
		ButtonWhat ButtonHandler = new ButtonWhat();
		buttonApp = new JButton(str);
		buttonApp.setIcon(new ImageIcon(GUI.But));
		buttonApp.setPreferredSize(new Dimension(gameSizeDiv*2, gameSizeDiv));
		buttonApp.setBounds(gameSize*(pos-1)+gameSizeDiv*3, gameSize*3 + gameSizeDiv, gameSizeDiv*5, gameSizeDiv);
		buttonApp.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonApp.setRolloverIcon(new ImageIcon(GUI.ButHover));
		buttonApp.addActionListener(ButtonHandler);
		buttonApp.setFont(Bigfont);
		buttonApp.setForeground(FontWhi);
		add(buttonApp);
		buttonApp.setVisible(true);
	}
	

	
	//====================================================
	/**
	 * 	 transitions: Set Green theme button
	 * 
	 * exception: none
	 * 
	 * @param str
	 * @param pos
	 */
	public void buttonTGreen(String str, int pos){
		ButtonWhat ButtonHandler = new ButtonWhat();
		buttonTGreen = new JButton(str);
		buttonTGreen.setIcon(new ImageIcon(GUI.But));
		buttonTGreen.setPreferredSize(new Dimension(gameSizeDiv*2, gameSizeDiv));
		buttonTGreen.setBounds(gameSize*2+gameSizeDiv, gameSize/3*pos+gameSizeDiv*2, gameSizeDiv*3, gameSizeDiv*3);
		buttonTGreen.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonTGreen.setRolloverIcon(new ImageIcon(GUI.ButHover));
		buttonTGreen.addActionListener(ButtonHandler);
		buttonTGreen.setFont(Bigfont);
		buttonTGreen.setForeground(FontWhi);
		add(buttonTGreen);
		buttonTGreen.setVisible(true);
	}
	/**
	 * 	 transitions: Set Red theme button
	 * 
	 * exception: none
	 * 
	 * @param str
	 * @param pos
	 */
	public void buttonTRed(String str, int pos){
		ButtonWhat ButtonHandler = new ButtonWhat();
		buttonTRed = new JButton(str);
		buttonTRed.setIcon(new ImageIcon(GUI.But));
		buttonTRed.setPreferredSize(new Dimension(gameSizeDiv*2, gameSizeDiv));
		buttonTRed.setBounds(gameSize*3, gameSize/3*pos+gameSizeDiv*2, gameSizeDiv*3, gameSizeDiv*3);
		buttonTRed.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonTRed.setRolloverIcon(new ImageIcon(GUI.ButHover));
		buttonTRed.addActionListener(ButtonHandler);
		buttonTRed.setFont(Bigfont);
		buttonTRed.setForeground(FontWhi);
		add(buttonTRed);
		buttonTRed.setVisible(true);
	}
	/**
	 * 	 transitions: Set Blue theme button
	 * 
	 * exception: none
	 * 
	 * @param str
	 * @param pos
	 */
	public void buttonTBlue(String str, int pos){
		ButtonWhat ButtonHandler = new ButtonWhat();
		buttonTBlue = new JButton(str);
		buttonTBlue.setIcon(new ImageIcon(GUI.But));
		buttonTBlue.setPreferredSize(new Dimension(gameSizeDiv*2, gameSizeDiv));
		buttonTBlue.setBounds(gameSize*2+gameSizeDiv, gameSize/3*pos+gameSizeDiv*2, gameSizeDiv*3, gameSizeDiv*3);
		buttonTBlue.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonTBlue.setRolloverIcon(new ImageIcon(GUI.ButHover));
		buttonTBlue.addActionListener(ButtonHandler);
		buttonTBlue.setFont(Bigfont);
		buttonTBlue.setForeground(FontWhi);
		add(buttonTBlue);
		buttonTBlue.setVisible(true);
	}
	/**
	 * 	 transitions: Set Grey theme button
	 * 
	 * exception: none
	 * 
	 * @param str
	 * @param pos
	 */
	public void buttonTGrey(String str, int pos){
		ButtonWhat ButtonHandler = new ButtonWhat();
		buttonTGrey = new JButton(str);
		buttonTGrey.setIcon(new ImageIcon(GUI.But));
		buttonTGrey.setPreferredSize(new Dimension(gameSizeDiv*2, gameSizeDiv));
		buttonTGrey.setBounds(gameSize*3, gameSize/3*pos+gameSizeDiv*2, gameSizeDiv*3, gameSizeDiv*3);
		buttonTGrey.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonTGrey.setRolloverIcon(new ImageIcon(GUI.ButHover));
		buttonTGrey.addActionListener(ButtonHandler);
		buttonTGrey.setFont(Bigfont);
		buttonTGrey.setForeground(FontWhi);
		add(buttonTGrey);
		buttonTGrey.setVisible(true);
	}
	/**
	 * 	 transitions: Set Maroon theme button
	 * 
	 * exception: none
	 * 
	 * @param str
	 * @param pos
	 */
	public void buttonTMaroon(String str, int pos){
		ButtonWhat ButtonHandler = new ButtonWhat();
		buttonTMaroon = new JButton(str);
		buttonTMaroon.setIcon(new ImageIcon(GUI.But));
		buttonTMaroon.setPreferredSize(new Dimension(gameSizeDiv*2, gameSizeDiv));
		buttonTMaroon.setBounds(gameSize*2+gameSizeDiv, gameSize/3*pos+gameSizeDiv*2, gameSizeDiv*3, gameSizeDiv*3);
		buttonTMaroon.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonTMaroon.setRolloverIcon(new ImageIcon(GUI.ButHover));
		buttonTMaroon.addActionListener(ButtonHandler);
		buttonTMaroon.setFont(Bigfont);
		buttonTMaroon.setForeground(FontWhi);
		add(buttonTMaroon);
		buttonTMaroon.setVisible(true);
	}
	/**
	 * 	 transitions: Set Yellow theme button
	 * 
	 * exception: none
	 * 
	 * @param str
	 * @param pos
	 */
	public void buttonTYellow(String str, int pos){ 
		ButtonWhat ButtonHandler = new ButtonWhat();
		buttonTYellow = new JButton(str);
		buttonTYellow.setIcon(new ImageIcon(GUI.But));
		buttonTYellow.setPreferredSize(new Dimension(gameSizeDiv*2, gameSizeDiv));
		buttonTYellow.setBounds(gameSize*3, gameSize/3*pos+gameSizeDiv*2, gameSizeDiv*3, gameSizeDiv*3);
		buttonTYellow.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonTYellow.setRolloverIcon(new ImageIcon(GUI.ButHover));
		buttonTYellow.addActionListener(ButtonHandler);
		buttonTYellow.setFont(Bigfont);
		buttonTYellow.setForeground(FontWhi);
		add(buttonTYellow);
		buttonTYellow.setVisible(true);
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
	private class ButtonWhat implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			
			String str = String.format("%s", event.getActionCommand());
			if(str.equals("OK")){
				//TODO Save to config
				OldTheme = Theme;
				OldBGI = GUI.BGI;		
				OldColor = GUI.color;
				OldBut = GUI.But;
				OldButH = GUI.ButHover;
				
				GUI.CL.next(GUI.MainPanel);
				GUI.CL.next(GUI.MainPanel);
				GUI.CL.next(GUI.MainPanel);
				repaint();
				
			}if(str.equals("Cancel")){
				GUI.BGI = GUI.LastBG;
				Theme = OldTheme;
				GUI.BGI = OldBGI;		
				GUI.color = OldColor;
				GUI.But = OldBut;
				GUI.ButHover = OldButH;
				
				GUI.CL.next(GUI.MainPanel);
				GUI.CL.next(GUI.MainPanel);
				GUI.CL.next(GUI.MainPanel);
				GUI.buttonRepaint();
				
				repaint();
							
			}else if(str.equals("Apply")){
				OldTheme = Theme;
				OldBGI = GUI.BGI;		
				OldColor = GUI.color;
				OldBut = GUI.But;
				OldButH = GUI.ButHover;
				
				repaint();
				
			}else if(str.equals("Garden")){
				Theme = 1;
				
				GUI.BGI = GUI.BG1;		
				GUI.color = GUI.ThemeCyan;
								
				GUI.But = GUI.But1;
				GUI.ButHover = GUI.But2;
				
				GUI.buttonRepaint();
				
			}else if(str.equals("Uluru")){
				Theme = 2;
				
				GUI.BGI = GUI.BG2;		
				GUI.color = GUI.ThemeCyan;
								
				GUI.But = GUI.But3;
				GUI.ButHover = GUI.But4;
				
				GUI.buttonRepaint();
				
			}else if(str.equals("Aurora")){
				Theme = 3;
				
				GUI.BGI = GUI.BG3;		
				GUI.color = GUI.ThemeCyan;
								
				GUI.But = GUI.But5;
				GUI.ButHover = GUI.But6;
				
				GUI.buttonRepaint();
				
			}else if(str.equals("Space")){
				Theme = 4;
				
				GUI.BGI = GUI.BG4;		
				GUI.color = GUI.ThemeCyan;
								
				GUI.But = GUI.But7;
				GUI.ButHover = GUI.But8;
				
				GUI.buttonRepaint();
				
			}else if(str.equals("Sunset")){
				Theme = 5;
				
				GUI.BGI = GUI.BG5;		
				GUI.color = GUI.ThemeCyan;
								
				GUI.But = GUI.But9;
				GUI.ButHover = GUI.But10;

				GUI.buttonRepaint();
				
			}else if(str.equals("Desert")){
				Theme = 6;
				
				GUI.BGI = GUI.BG6;		
				GUI.color = GUI.ThemeCyan;
								
				GUI.But = GUI.But11;
				GUI.ButHover = GUI.But12;

				GUI.buttonRepaint();
				
			}else if(str.equals("default")){
				GUI.gameSizeX = gameSize*4;
				GUI.gameSizeY = gameSize*4;
				
				repaint();
				GUI.buttonRepaint();
				
			}else if(str.equals("1280 x 960")){
				GUI.gameSizeX = 1280;
				GUI.gameSizeY = 960;
				
				repaint();
				GUI.buttonRepaint();
				
			}else if(str.equals("1280 x 1024")){
				GUI.gameSizeX = 1280;
				GUI.gameSizeY = 1024;
				
				repaint();
				GUI.buttonRepaint();
				
			}else if(str.equals("1280 x 720")){
				GUI.gameSizeX = 1280;
				GUI.gameSizeY = 720;
				
				repaint();
				GUI.buttonRepaint();
				
			}else if(str.equals("1920 x 1080")){
				GUI.gameSizeX = 1920;
				GUI.gameSizeY = 1080;
				
				repaint();
				GUI.buttonRepaint();
				
			}else if(str.equals("4096 x 2304")){
				GUI.gameSizeX = 4096;
				GUI.gameSizeY = 2304;
				
				repaint();
				GUI.buttonRepaint();
				
			}
			repaint();
		}
	}
	

	/**
	 * transition: Adds the visual elements to the Settings GUI including the title and the highlight boxes
	 * 
	 * exception: none
	 */
	@Override
	public void paintComponent(Graphics g) {
		try {
			title = ImageIO.read(new File("src/code/res/Title.png"));
		} catch (IOException e) {
		}
		Color BgBoard = new Color(170, 154, 154, 150);
		setBackground(BgBoard);
		
		
	
			g.drawImage(GUI.BGI, 0, 0, getWidth(), getHeight(), null);
			g.drawImage(title, 0,0,500,500,null);
			Graphics2D g2d = (Graphics2D)g;		
		
			g2d.setColor(ColClr);
			Rectangle2D BoxGreen = new Rectangle2D.Double(gameSize*2+gameSizeDiv-3, gameSize/3+gameSizeDiv*2-3, gameSizeDiv*3+10, gameSizeDiv*3+10);
			g2d.fill(BoxGreen);
			
			Rectangle2D BoxRed = new Rectangle2D.Double(gameSize*3-3, gameSize/3*2+gameSizeDiv*2-3, gameSizeDiv*3+10, gameSizeDiv*3+10);
			g2d.fill(BoxRed);
			
			Rectangle2D BoxBlue = new Rectangle2D.Double(gameSize*2+gameSizeDiv-3, gameSize/3*3+gameSizeDiv*2-3, gameSizeDiv*3+10, gameSizeDiv*3+10);
			g2d.fill(BoxBlue);
		
			Rectangle2D BoxGrey = new Rectangle2D.Double(gameSize*3-3, gameSize/3*4+gameSizeDiv*2-3, gameSizeDiv*3+10, gameSizeDiv*3+10);
			g2d.fill(BoxGrey);
			
			Rectangle2D BoxMaroon = new Rectangle2D.Double(gameSize*2+gameSizeDiv-3, gameSize/3*5+gameSizeDiv*2-3, gameSizeDiv*3+10, gameSizeDiv*3+10);
			g2d.fill(BoxMaroon);
			
			Rectangle2D BoxYellow = new Rectangle2D.Double(gameSize*3-3, gameSize/3*6+gameSizeDiv*2-3, gameSizeDiv*3+10, gameSizeDiv*3+10);
			g2d.fill(BoxYellow);
			
		if(Theme == 1){
			g2d.setColor(ColClr);
			g2d.fill(BoxRed);
			g2d.fill(BoxBlue);
			g2d.fill(BoxGrey);
			g2d.fill(BoxMaroon);
			g2d.fill(BoxYellow);
			g2d.setColor(ColSel);
			g2d.fill(BoxGreen);
		}else if(Theme == 2){
			g2d.setColor(ColClr);
			g2d.fill(BoxGreen);
			g2d.fill(BoxBlue);
			g2d.fill(BoxGrey);
			g2d.fill(BoxMaroon);
			g2d.fill(BoxYellow);
			g2d.setColor(ColSel);
			g2d.fill(BoxRed);
		}else if(Theme == 3){
			g2d.setColor(ColClr);
			g2d.fill(BoxGreen);
			g2d.fill(BoxRed);
			g2d.fill(BoxGrey);
			g2d.fill(BoxMaroon);
			g2d.fill(BoxYellow);
			g2d.setColor(ColSel);
			g2d.fill(BoxBlue);
		}else if(Theme == 4){
			g2d.setColor(ColClr);
			g2d.fill(BoxGreen);
			g2d.fill(BoxRed);
			g2d.fill(BoxBlue);
			g2d.fill(BoxMaroon);
			g2d.fill(BoxYellow);
			g2d.setColor(ColSel);
			g2d.fill(BoxGrey);
		}else if(Theme == 5){
			g2d.setColor(ColClr);
			g2d.fill(BoxGreen);
			g2d.fill(BoxRed);
			g2d.fill(BoxBlue);
			g2d.fill(BoxGrey);
			g2d.fill(BoxYellow);
			g2d.setColor(ColSel);
			g2d.fill(BoxMaroon);
		}else if(Theme == 6){
			g2d.setColor(ColClr);
			g2d.fill(BoxGreen);
			g2d.fill(BoxRed);
			g2d.fill(BoxBlue);
			g2d.fill(BoxGrey);
			g2d.fill(BoxMaroon);
			g2d.setColor(ColSel);
			g2d.fill(BoxYellow);
		}			
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
			