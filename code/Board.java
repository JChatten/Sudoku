package code;


import java.util.Random;

/**
 * 
 * @author Josh Chatten, Mitchell Coovert, Johnny Endrizzi
 * 
 * State Variables
 * board: stores the current values of the game board
 * original: stores the original values of the game board
 * full: stores the generated full correct board for the current board
 * hints: stores the remaining number of hints
 * 
 * Environment Variables
 * None
 * 
 * Assumptions
 * Game board is generated using backTrackGen() prior to any other access programs 
 */

public class Board {
	Cell[][] board = new Cell[9][9];
	Cell[][] original = new Cell[9][9];
	Cell[][] full = new Cell[9][9];
	Difficulty diff = Difficulty.Default;
	int hints = 1;
	int unassigned = 0;
	
	public Board() {
		this.initBoard();
	}
	
	public Board(Difficulty difficulty) {
		this.initBoard();
		diff = difficulty;
		if (diff == Difficulty.Easy) {
			hints = 2;
		} else if (diff == Difficulty.Hard) {
			hints = 0;
		}
		Generation.backTrackGen(this);
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				full[i][j].setValue(board[i][j].getValue());
			}
		}
		Generation.backTrackPlay(this,difficulty);
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j].getValue() != unassigned) {
					board[i][j].setValue(board[i][j].getValue()*(-1));
					original[i][j].setValue(board[i][j].getValue());
				} else {
					original[i][j].unassign();
				}
			}
		}
	}
	
	public Board(Cell[][] save, Cell[][] orig, Cell[][] comp) {
		this.initBoard();
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if (save[i][j].getValue() != unassigned) {
					board[i][j].setValue(save[i][j].getValue());
					//TODO input subs
				} else {
					for (int k = 0; k < 9; k++) {
						board[i][j].unassign();
						if (save[i][j].getSub()[k] != 0) {
							board[i][j].setSub(save[i][j].getSub()[k]);
							
						}
					}
				}
				if (orig[i][j].getValue() != unassigned) {
					original[i][j].setValue(orig[i][j].getValue());
				} else {
					original[i][j].unassign();
				}
				full[i][j].setValue(comp[i][j].getValue());
			}
		}
	}
	
	/**
	 * 
	 * @param input
	 * @param row
	 * @param column
	 * 
	 * transition: value of input is added at position corresponding to row and column
	 * exception: if adding to position that does not exists (POS)
	 */
	public void input(int input, int row, int column) {
		board[row][column].setValue(input);
	}
	
	/**
	 * 
	 * @param row
	 * @param column
	 * 
	 * transition: assigns the value of unassigned (0) to position corresponding to row and column
	 * exception: if removing from position that does not exist(POS)
	 */
	public void delete(int row, int column) {
		board[row][column].unassign();
	}
	
	/**
	 * 
	 * @param difficulty
	 * 
	 * transition: clear the current board and generate new one
	 * exception: none
	 */
	public void newBoard() {
		this.initBoard();
		Generation.backTrackGen(this);
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				full[i][j].setValue(board[i][j].getValue());
			}
		}
		Generation.backTrackPlay(this,diff);
		if (diff == Difficulty.Easy) {
			hints = 2;
		} else if (diff == Difficulty.Hard) {
			hints = 0;
		} else {
			hints = 1;
		}
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j].getValue() != unassigned) {
					board[i][j].setValue(board[i][j].getValue()*(-1));
					original[i][j].setValue(board[i][j].getValue());
				} else {
					original[i][j].setValue(unassigned);
				}
			}
		}
	}
	
	/**
	 * transition: Reset the game board to the original state
	 * exception: none
	 */
	public void reset() {
		if (diff == Difficulty.Easy) {
			hints = 2;
		} else if (diff == Difficulty.Hard) {
			hints = 0;
		} else {
			hints = 1;
		}
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				board[i][j].setValue(original[i][j].getValue());
			}
		}
	}
	
	/**
	 *
	 * Initialize this board with usable cells
	 */
	public void initBoard() {
		for (int k = 0; k < 9; k++) {
			for (int m = 0; m < 9; m++) {
				this.board[k][m] = new Cell();
				this.original[k][m] = new Cell();
				this.full[k][m] = new Cell();
			}
		}
	}
	
	/**
	 * Adds a previously determined value to an empty cell.
	 */
	
	public void hint() {
		Random index = new Random();
		int i = index.nextInt(9);
		int j = index.nextInt(9);
		if (board[i][j].getValue() != unassigned) {
			hint();
		} else {
			board[i][j].setValue(full[i][j].getValue());
		}
	}
	
	/**
	 * Checks whether the board has hints remaining
	 * 
	 * @return True if there are more hints available
	 */
	
	public boolean checkHints() {
		if (hints > 0) return true;
		return false;
	}
	
	/**
	 * Decreases the amount of hints the board has left
	 */
	
	public void decHints() {
		hints = hints - 1;
	}
}

