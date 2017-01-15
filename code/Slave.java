package code;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * 
 * @author Josh Chatten, Mitchell Coovert, Johnny Endrizzi
 * 
 * State Variables:
 * None
 * 
 * Environment Variables:
 * None
 * 
 * Assumptions:
 * Assume any file in the saves folder contains correct save data
 *
 */

public class Slave {

	/**
	 * 
	 * @param saveName
	 * 
	 * transitions: saves game
	 * output: text file
	 * 
	 * exception: none
	 */
	public static void save(Board board, String saveName) {
		(new File("src/code/saves")).mkdirs();
		String sf = String.format("src/code/saves/%s.sav",saveName);
		PrintStream saveFile;
		try {
			saveFile = new PrintStream(new File(sf));
			for (int i=0; i<9; i++){ 
				for (int j=0; j<9; j++){
					saveFile.print(board.board[i][j].getValue()+" ");
					saveFile.print(board.original[i][j].getValue()+" ");
					saveFile.print(board.full[i][j].getValue()+" ");
					for (int k = 0; k < 9; k++) {
						saveFile.print(board.board[i][j].getSub()[k]+" ");
					} saveFile.println();
				}
			}
			saveFile.close();
			
		} catch (FileNotFoundException e) {
			//File is created if not found so cannot be thrown
		}
	}
	
	/**
	 * @param loadName
	 * @return
	 * @throws FileNotFoundException
	 * 
	 * transitions: loads game
	 * input: text file
	 * 
	 * exception: exceptions if loading a file that does not exist (FileNotFoundException), will ignore the exception and continue if it occurs
	 * 
	 */
	public static Board load(String loadName) throws FileNotFoundException {
		String lf = String.format("src/code/saves/%s.sav",loadName);
		Scanner loadFile = new Scanner(new File(lf));
		Cell[][] current = new Cell[9][9];
		Cell[][] original = new Cell[9][9];
		Cell[][] full = new Cell[9][9];
		String str;
		
		
		for (int i=0; i<9; i++){ //transfer data to board from file
			for (int j=0; j<9; j++){
				str = loadFile.next();
				current[i][j] = new Cell();
				current[i][j].setValue(Integer.parseInt(str));
				str = loadFile.next();
				original[i][j] = new Cell();
				original[i][j].setValue(Integer.parseInt(str));
				str = loadFile.next();
				full[i][j] = new Cell();
				full[i][j].setValue(Integer.parseInt(str));
				for (int k=0; k<9; k++){
					str = loadFile.next();
					if (Integer.parseInt(str) != 0) {
						current[i][j].setSub(Integer.parseInt(str));
					}
				}
			}
		}
		loadFile.close();
		Board board = new Board(current, original,full);
		return board;
		}

}
