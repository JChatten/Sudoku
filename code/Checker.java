package code;
/**
 * 
 * @author Josh Chatten, Mitchell Coovert, Johnny Endrizzi
 * 
 * State Variables
 * None
 * 
 * Environment Variables
 * None
 * 
 * Assumptions
 * Checker is passed a full board
 */

public class Checker {
	
	static int unassigned = 0;
	
	/**
	 * 
	 * @param board
	 * @return true if the board is solved correctly, false otherwise
	 */
	
	public static boolean checker(Board board) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board.board[i][j].getValue() == unassigned) {
					return false;
				}
				if (!ZoneCheck.zoneCheck(i,j,board.board[i][j].getValue(),board)) {
					loop: for (int l = 0; l < 9; l++) {
						if ((Math.abs(board.board[i][l].getValue()) == Math.abs(board.board[i][j].getValue()) && l != j) || (Math.abs(board.board[l][j].getValue()) == Math.abs(board.board[i][j].getValue()) && l != i)){
							return false;
						}
						else {
							continue loop;
						}
					}
				} else return false;
			}
		}
		return true;
	}
}
