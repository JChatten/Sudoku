package code;

import java.util.Random;

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
 * backTrackGen is passed an empty board object, backTrackPlay is passed a full board
 */

public class Generation {
	
	static int unassigned = 0;
		
	/**
	 * transition: Use backtracking algorithm to generate board.board, also solves board.board and determines uniqueness
	 * exception: none
	 */
	public static void backTrackGen(Board board)  {
		boolean stop = false;
		again: while (!stop) {
			Random numb = new Random();
			int tries = 0;
			for (int i = 0;i<9;i++) {
				jloop:
				for (int j = 0;j<9;j++) {
					
					int x = numb.nextInt(9)+1;
					board.board[i][j].setValue(x);
					
					loop: for (int l = 0;l<9;l++) {
						if ((board.board[i][l].getValue() == x && l != j) || (board.board[l][j].getValue() == x && l != i) ||  ZoneCheck.zoneCheck(i,j,x,board)){
							board.board[i][j].unassign();
							j--;
							tries++;
							if (tries >= 1000) {
								continue again;
							}

							continue jloop;
							
						} else {
							if (l < 8) { 
								continue loop;
							}

							if (j<8){
								if(!backTrackSolve(i,j+1,board))
									j--;
									continue jloop;
									
							} else if (i <8)
								if(!backTrackSolve(i+1,0,board))
									j--;
									continue jloop;
						}
					}
				}
			} stop = true;
		}
	}
	
	

	
	/**
	 * 
	 * @param difficulty
	 * 
	 * transition: takes a full board.board and removes values to a playable state
	 * exception:none
	 */
	public static void backTrackPlay(Board board, Difficulty difficulty) {
		//create list of all indexes in the board.board; first digit is row, second digit is column; 0-88 excluding numbers ending in 9
		int end;
		if (difficulty == Difficulty.Easy) {
			end = 2;
		}
		else if (difficulty == Difficulty.Medium) {
			end = 56;
		}
		else if (difficulty == Difficulty.Hard) {
			end = 61;
		}
		else {
			end = 56;
		}
		
		int[] list = new int[81];
		int diff = 0;
		for (int i = 0; i < 89; i++) {
			if (i < 9) {
				list[i] = i;
			} else { 
				if (i % 10 != 9){
					if (i > 81) {
						list[i-8] = i;
					} else {
						list[i-diff] = i;
					}
				} else {
					diff++;
				}
			}
		}
		shuffleArray(list);
		
		jloop: for (int i = 0; i < 81; i++) {
			int div = list[i] / 10;
			int mod = list[i] % 10;
			int temp = board.board[div][mod].getValue();
			int count = 0;
			while (i < end) {
				board.board[div][mod].unassign();
				if (backTrackSolve(0,0,board)) {
					count++;
					if (count == 1) {
						if (backTrackSolve(0,0,board,temp)) {
							end++;
							continue jloop;
						} else {
							continue jloop;
						}
					} 
				}
			}
		}		
	}
	
	/**
	 * 
	 * @param array
	 * 
	 * transition: Shuffle the array of indexes for creating the playable board.board 
	 * exception: none
	 */
	private static void shuffleArray(int[] array) {
		Random rnd = new Random();
	    for (int i = array.length - 1; i > 0; i--)
	    {
	      int index = rnd.nextInt(i + 1);
	      // Simple swap
	      int a = array[index];
	      array[index] = array[i];
	      array[i] = a;
	    }
	}
	
	/**
	 * 
	 * @param row
	 * @param col
	 * @param board.board
	 * 
	 * output: Returns true if the given board.board can be solved and false if it can not
	 * exception: none
	 */
	public static boolean backTrackSolve(int row, int col, Board board)  {
		Board temp = new Board(board.board, board.original,board.full);
		int[] array = new int[]{1,2,3,4,5,6,7,8,9};
		shuffleArray(array);
		int count = 0;
		iloop: for (int i = row; i < 9; i++) {
			jloop :for (int j = col; j < 9; j++) {
				if (temp.board[i][j].getValue() == unassigned ) {
						loop :for (int l = 0; l < 9; l++) {
							if (count > 8) {
								return false;
							}
							temp.board[i][j].setValue(array[count]);
							if ((temp.board[i][l].getValue() == array[count] && l != j) || (temp.board[l][j].getValue() == array[count] && l != i) ||  ZoneCheck.zoneCheck(i,j,array[count],temp)) {
								temp.board[i][j].unassign();
								if (j == 0){
									i--; j = 8; count++; continue iloop;
								}
								else {
									j--;
									count++;
									continue jloop;
								}
							} else {
								if (l<8) {
									continue loop;
								} else {
									if (j<8){
										if(backTrackSolve(i,j+1,temp)) {
											return true;
										} else {
											continue jloop;
										}
										
									} else {
										if (i <8) {
											if(backTrackSolve(i+1,0,temp)) {
												return true;
											} else {
												i--;
												continue iloop;
											}
										}
									
									}
								}
							}
						}
				} 
				return true;
			}
		} 
		return false;
	}
	
	/**
	 * 
	 * @param row
	 * @param col
	 * @param board.board
	 * @param index
	 * 
	 * output: Returns true if the board.board can be solved and false if it can't, also takes in extra arg to exclude checking for the same number twice
	 * exception: none
	 */
	public static boolean backTrackSolve(int row, int col, Board board, int index)  {
		Board temp = new Board(board.board, board.original,board.full);
		int[] array = new int[8];
		//Skips over the number that was placed in the original board.board
		forloop: for (int burp = 1; burp < 10; burp ++) {
			if (burp < index) {
				array[burp - 1] = burp;
			}
			if (burp == index) {
				continue forloop;
			}
			if (burp > index) {
				array[burp - 2] = burp;
			}
		}
			
		shuffleArray(array);
		int count = 0;
		iloop: for (int i = row; i < 9; i++) {
			jloop :for (int j = col; j < 9; j++) {
				if (temp.board[i][j].getValue() == unassigned ) {
						loop :for (int l = 0; l < 9; l++) {
							if (count > 7) return false;
							temp.board[i][j].setValue(array[count]);
							if ((temp.board[i][l].getValue() == array[count] && l != j) || (temp.board[l][j].getValue() == array[count] && l != i) ||  ZoneCheck.zoneCheck(i,j,array[count],temp)) {
								temp.board[i][j].unassign();
								count++;
								l = 0;
								continue loop;
							} else {
								if (l<8) {
									continue loop;
								} else {
									if (j<8){
										if(backTrackSolve(i,j+1,temp)) {
										} else {
											continue jloop;
										}
										
									} else {
										if (i <8) {
											if(backTrackSolve(i+1,0,temp)) {
											} else {
												continue iloop;
											}
										}
									
									}
								}
							}
						}
				} 
			}
		} 
		return true;
	}
	
}
