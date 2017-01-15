package code;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CellTest {
	Cell testCell1;
	Cell testCell2;
	Cell testCell3;
	int[] testArray1;
	int[] testArray2;
	int[] testArray3;
	
	
	@Before
	public void initialize() {
		testCell1 = new Cell();
		testCell2 = new Cell(3);
		testCell3 = new Cell(4);
		testArray1 = new int[]{0,0,0,0,0,0,0,0,0};
		testArray2 = new int[]{1,2,3,4,5,6,7,8,9};
		testArray3 = new int[]{1,0,3,0,5,0,7,0,9};
	}
	
	@Test
	public void testSetValue() {
		testCell1.setValue(2);
		assertEquals(2,testCell1.getValue());
	}
	
	@Test
	public void testSetSub() {
		assertArrayEquals(new int[]{0,0,0,0,0,0,0,0,0},testCell1.getSub());
		testCell1.setSub(1);
		testCell1.setSub(2);
		testCell1.setSub(3);
		testCell1.setSub(4);
		testCell1.setSub(5);
		testCell1.setSub(6);
		testCell1.setSub(7);
		testCell1.setSub(8);
		testCell1.setSub(9);
		assertArrayEquals(testArray2,testCell1.getSub());
		
	}
	
	@Test
	public void testGetValue() {
		assertEquals(0,testCell1.getValue());
		assertEquals(3,testCell2.getValue());
		assertEquals(4,testCell3.getValue());
		assertNotSame(null,testCell1.getValue());
	}
	
	@Test
	public void testGetSub() {
		assertArrayEquals(testArray1,testCell1.getSub());
		testCell2.setSub(1);
		testCell2.setSub(2);
		testCell2.setSub(3);
		testCell2.setSub(4);
		testCell2.setSub(5);
		testCell2.setSub(6);
		testCell2.setSub(7);
		testCell2.setSub(8);
		testCell2.setSub(9);
		assertArrayEquals(testArray2,testCell2.getSub());
		testCell3.setSub(1);
		testCell3.setSub(3);
		testCell3.setSub(5);
		testCell3.setSub(7);
		testCell3.setSub(9);
		assertArrayEquals(testArray3,testCell3.getSub());
		assertNotSame(null,testCell1.getSub());
	}
	
	@Test
	public void testUnassignSub() {
		testCell1.setSub(1);
		testCell1.unassignSub(1);
		assertArrayEquals(testArray1,testCell1.getSub());
	}

	@Test
	public void testClearSub() {
		testCell2.setSub(1);
		testCell2.setSub(2);
		testCell2.setSub(3);
		testCell2.setSub(4);
		testCell2.setSub(5);
		testCell2.setSub(6);
		testCell2.setSub(7);
		testCell2.setSub(8);
		testCell2.setSub(9);
		testCell2.clearSub();
		assertArrayEquals(testArray1,testCell2.getSub());
	}

	@Test
	public void testCheckSub() {
		assertTrue(testCell1.checkSub(4));
		assertFalse(testCell1.checkSub(0));
		testCell1.setSub(4);
		assertFalse(testCell1.checkSub(4));
	}

	@Test
	public void testUnassign() {
		testCell1.setValue(4);
		testCell1.unassign();
		assertEquals(0,testCell1.getValue());
	}

}
