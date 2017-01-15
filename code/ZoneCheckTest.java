package code;

import static org.junit.Assert.*;

import org.junit.Test;

public class ZoneCheckTest {
	
	Board test1 = new Board();
	Board test2= new Board();

	@Test
	public void test() {
		int inc = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				
				test1.board[i][j].setValue(1);
				test2.board[i][j].setValue(inc);
				inc++;
			}
		}
		
		assertTrue(ZoneCheck.zoneCheck(1, 1, 1, test1));
		assertFalse(ZoneCheck.zoneCheck(1, 1, 9, test2));
	}

}
