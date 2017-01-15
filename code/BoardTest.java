package code;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BoardTest {
	Board board1;
	Board board2;
	Board board3;
	
	Cell[][] testArray1;
	Cell[][] testArray2;
	Cell[][] testArray3;
	
	@Before
	public void init() {
		board1 = new Board();
		board2 = new Board(Difficulty.Default);
		board3 = new Board();
	}

	@Test
	public void testInput() {
		board3 = new Board(board1.board,board1.original,board1.full);
		board3.input(3, 4, 5);
		assertNotSame(board1,board3);
	}

	@Test
	public void testDelete() {
		board3.input(3, 4, 5);
		board3.delete(4,5);
		assertEquals(0,board3.board[4][5].getValue());
	}
	
	@Test
	public void testNewBoard() {
		board3 = new Board(board2.board,board2.original,board2.full);
		board2.newBoard();
		assertNotSame(board3,board2);
	}
	
	@Test
	public void testReset() {
		board3 = new Board(board1.board,board1.original,board1.full);
		board1.input(3,4,5);
		board1.reset();
		assertTrue(board1.equals(board3));
	}
	
	@Test
	public void testInitBoard() {
		
	}
	
	@Test
	public void testHint() {
		
	}
}
