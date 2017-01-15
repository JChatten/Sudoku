package code;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

public class SlaveTest {
	
	Board board1 = new Board(Difficulty.Default);
	Board board2;

	@Test
	public void test() throws FileNotFoundException {
		Slave.save(board1, "saveName");
		board2 = Slave.load("saveName");
		assertTrue(board1.equals(board2));
	}
	
	@Test(expected=FileNotFoundException.class)
	public void testFNFE() throws FileNotFoundException {
		Slave.load("unknown");
	}

}
