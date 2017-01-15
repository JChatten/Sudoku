package code;

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
 * None
 *
 */

public class ZoneCheck {
	/**
	 * 
	 * @param i
	 * @param j
	 * @param x
	 * @param temp
	 * 
	 * output: checks the 3x3 zone the given cell is in and returns true if invalid 
	 */
	public static boolean zoneCheck(int i, int j, int x, Board temp) {
		for (int k = (i/3)*3; k < (i/3)*3+3; k++) {
			for (int l = (j/3)*3; l < (j/3)*3+3; l++) {
				if (Math.abs(temp.board[k][l].getValue()) == Math.abs(x) && k != i && l != j) {
					return true;
				}
			}
		}
		return false;
	}
}
