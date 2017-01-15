package code;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.Graphics;
import java.awt.Point;

/**
 * The How to play panel
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
 * //Buttons
 *  buttonBack: button used to go back to the menu  
 * 
 * //Font
 *  font: font used for the text
 *  Smallfont: font used for the subvalues on the board
 *  fontBold: font used for permanent values
 *  Bigfont: font used for the buttons 
 *  FontCol: current font colour 
 *  FontClr: clear font colour
 *  FontWhi: white font
 * 
 * //Board colours
 *  BgWindow: default background colour
 *  BGWhite: white background
 *  BgBoard: Defauld board background colour
 *  BgPause: colour of the paused board 
 * 
 * //Colours 
 *  ColClr: Selection colours
 *  ColSel: Selection colours
 *  
 * //Labels
 *  labelsArr: generates board labels
 *  
 * //Board size 
 *	width: used in grid generation
 *	x1: used in grid generation
 *	y1: used in grid generation
 *
 * //Selection
 *  lightHoriz: used to select a row
 *	lightVert: used to select a row
 *	lightBox: used to select a box
 *	lightCellH: used to select a cell
 *	lightCellV: used to select a cell
 *	selectedX: used to keep track of selection
 *	selectedY: used to keep track of selection
 * 
 * //Selection
 *  lightHoriz: used to select a row
 *	lightVert: used to select a row
 *	lightBox: used to select a box
 *	lightCellH: used to select a cell
 *	lightCellV: used to select a cell
 *	selectedX: used to keep track of selection
 *	selectedY: used to keep track of selection
 *  
 * //Board creation - Rectangle2D.Double arrays
 *	horiz: the lines in the grid 
 *	vert: the other lines in the grid
 *	box: the 3x3 boxes on the game board
 *	cell: the cells on the game board
 *  
 *  
 * Environment Variables
 * 	keylistener: gets key inputs
 * 	mouselistener: gets mouse clicks
 * 	
 * 	
 * //Misc.
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
 * Assumptions
 *	takes blank board
 *
 */
public class HowToPanel extends JPanel implements MouseListener,KeyListener {
	private static final long serialVersionUID = 1L;
	
	//Board 
		private int gameSize = GUI.gameSize;
		private int gameSizeDiv = (int) (gameSize*0.2);
		Board board;

		//Buttons		
		protected JButton buttonBack;
		
		//Font
		Font font = new Font("Serif", Font.PLAIN, gameSizeDiv/2);
		Font Smallfont = new Font("Serif", Font.BOLD, gameSizeDiv/4);
		Font Bigfont = new Font("Lucida Calligraphy", Font.BOLD, gameSizeDiv/2); //TODO Check if font exists
		Font fontBold = new Font("Serif", Font.BOLD, gameSizeDiv/2);
		Color FontCol = new Color(0,0,0);
		Color FontClr = new Color(200,200,200);
		Color FontWhi = new Color(200,200,200);
		
		//Board colors
		Color BgWindow = new Color(0x53152A); //default background
		Color BGWhite = new Color(0xffffff);
		Color BgBoard = new Color(170, 154, 154, 150); 
		Color BgPause = new Color(200, 200, 200, 0); 
		
		//Colours
		Color ColClr = new Color(80,255,80,0);
		Color ColSel = new Color(80,255,80,200);
		
		//Labels
		JLabel[][] labelsArr = JlabelsMaker();
		
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
	
		
	public HowToPanel(){
		board = new Board();
		
		buttonsOpt();
	
        addMouseListener(this);
        addKeyListener(this);
	}
	
	/** 
	 * transitions: Sets up buttons
	 * 
	 * exception: none
	 */
	public void buttonsOpt(){
		setLayout(null);
		
		buttonBack("Back",1);
	}
	/**
	 * 	 transitions: Hint button
	 * 
	 * exception: none
	 * 
	 * @param str
	 * @param pos
	 */
	public void buttonBack(String str, int pos){
		ButtonHandle ButtonHandler = new ButtonHandle();
		buttonBack = new JButton(str);
		buttonBack.setIcon(new ImageIcon(GUI.But));
		buttonBack.setPreferredSize(new Dimension(gameSizeDiv*2, gameSizeDiv));
		buttonBack.setBounds(gameSize*(pos-1)+gameSizeDiv*2, gameSize*3 + gameSizeDiv, gameSizeDiv*5, gameSizeDiv);
		buttonBack.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonBack.setRolloverIcon(new ImageIcon(GUI.ButHover));
		buttonBack.addActionListener(ButtonHandler);
		buttonBack.setFont(Bigfont);
		buttonBack.setForeground(FontWhi);
		add(buttonBack);
		buttonBack.setVisible(true);
	}
	
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
	 * 	Will receive a key press
	 */
	private class ButtonHandle implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			
			String str = String.format("%s", event.getActionCommand());
			if(str.equals("Back")){
				GUI.CL.next(GUI.MainPanel);
				GUI.CL.next(GUI.MainPanel);
				
				repaint();
			}	
			repaint();
		}
	}
	
	/**
	 * transition: Adds the visual elements to the How to Play GUI including the board
	 * 
	 * exception: none
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(BGWhite);
		
		g.drawImage(GUI.BGI, 0, 0, getWidth(), getHeight(), null);
		g.drawImage(GUI.rules,0,0,1080,720,null);
		
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
	 * input: mouse clicked
	 * 
	 * exception: none
	 */
	@Override
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
		mouseClicked = true;
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
	
	/**
	 * 
	 * transition: input values
	 * input: key pressed
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
    			selectedX = 10;
    	    	selectedY = 10;
    		}	
     	}
    }
	hold = false;
	repaint();
	}
	/**
	 * 
	 * transition: none
	 * input: key released
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
	 * 
	 * transition: none
	 * input: key typed
	 * 
	 * exception: none
	 */
	@Override
	public void keyTyped(KeyEvent arg0) {
	
	}
}