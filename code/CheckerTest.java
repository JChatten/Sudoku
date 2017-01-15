package code;

import static org.junit.Assert.*;

import org.junit.Test;

public class CheckerTest {
	
	Board test1 = new Board();
	Board test2 = new Board();

	@Test
	public void test() {
		//Kill me
		//We're so sorry
		test1.board[0][0].setValue(1);
		test1.board[0][1].setValue(2);
		test1.board[0][2].setValue(3);
		test1.board[0][3].setValue(4);
		test1.board[0][4].setValue(5);
		test1.board[0][5].setValue(6);
		test1.board[0][6].setValue(7);
		test1.board[0][7].setValue(8);
		test1.board[0][8].setValue(9);
		test1.board[1][0].setValue(7);
		test1.board[1][1].setValue(8);
		test1.board[1][2].setValue(9);
		test1.board[1][3].setValue(1);
		test1.board[1][4].setValue(2);
		test1.board[1][5].setValue(3);
		test1.board[1][6].setValue(4);
		test1.board[1][7].setValue(5);
		test1.board[1][8].setValue(6);
		test1.board[2][0].setValue(4);
		test1.board[2][1].setValue(5);
		test1.board[2][2].setValue(6);
		test1.board[2][3].setValue(7);
		test1.board[2][4].setValue(8);
		test1.board[2][5].setValue(9);
		test1.board[2][6].setValue(1);
		test1.board[2][7].setValue(2);
		test1.board[2][8].setValue(3);
		test1.board[3][0].setValue(9);
		test1.board[3][1].setValue(1);
		test1.board[3][2].setValue(2);
		test1.board[3][3].setValue(3);
		test1.board[3][4].setValue(4);
		test1.board[3][5].setValue(5);
		test1.board[3][6].setValue(6);
		test1.board[3][7].setValue(7);
		test1.board[3][8].setValue(8);
		test1.board[4][0].setValue(6);
		test1.board[4][1].setValue(7);
		test1.board[4][2].setValue(8);
		test1.board[4][3].setValue(9);
		test1.board[4][4].setValue(1);
		test1.board[4][5].setValue(2);
		test1.board[4][6].setValue(3);
		test1.board[4][7].setValue(4);
		test1.board[4][8].setValue(5);
		test1.board[5][0].setValue(3);
		test1.board[5][1].setValue(4);
		test1.board[5][2].setValue(5);
		test1.board[5][3].setValue(6);
		test1.board[5][4].setValue(7);
		test1.board[5][5].setValue(8);
		test1.board[5][6].setValue(9);
		test1.board[5][7].setValue(1);
		test1.board[5][8].setValue(2);
		test1.board[6][0].setValue(8);
		test1.board[6][1].setValue(9);
		test1.board[6][2].setValue(1);
		test1.board[6][3].setValue(2);
		test1.board[6][4].setValue(3);
		test1.board[6][5].setValue(4);
		test1.board[6][6].setValue(5);
		test1.board[6][7].setValue(6);
		test1.board[6][8].setValue(7);
		test1.board[7][0].setValue(5);
		test1.board[7][1].setValue(6);
		test1.board[7][2].setValue(7);
		test1.board[7][3].setValue(8);
		test1.board[7][4].setValue(9);
		test1.board[7][5].setValue(1);
		test1.board[7][6].setValue(2);
		test1.board[7][7].setValue(3);
		test1.board[7][8].setValue(4);
		test1.board[8][0].setValue(2);
		test1.board[8][1].setValue(3);
		test1.board[8][2].setValue(4);
		test1.board[8][3].setValue(5);
		test1.board[8][4].setValue(6);
		test1.board[8][5].setValue(7);
		test1.board[8][6].setValue(8);
		test1.board[8][7].setValue(9);
		test1.board[8][8].setValue(1);
		assertTrue(Checker.checker(test1));
		assertFalse(Checker.checker(test2));
	}

}
