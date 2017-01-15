package code;

/**
 * 
 * 	@author Josh Chatten, Mitchell Coovert, Johnny Endrizzi
 * 
 * 	State Variables
 * 	subs: stores which of the possible subscript values are active
 * 	value: stores the current value of the cell
 * 
 *  Environment Variables
 *  None
 *  
 *  Assumptions
 *  Assume all values given to cell are from 1 to 9
 * 
 */

public class Cell {
	private int[] subs;
	private int value;
	int unassigned = 0;
	
	protected Cell() {
		value = unassigned;
		subs = new int[]{0,0,0,0,0,0,0,0,0};
	}
	 
	 protected Cell(int number) {
		 value = number;
		 subs = new int[]{0,0,0,0,0,0,0,0,0};
	 }
	 
	 /**
	  * 
	  * @return The current value of the cell
	  */
	 
	 protected int getValue() {
		 return value;
	 }
	 
	 /**
	  * 
	  * @return The current value of the subscript
	  */
	 
	 protected int[] getSub() {
		 return subs;
	 }
	 
	 /**
	  * Assign the value a new int
	  * @param newNumber
	  */
	 
	 protected void setValue(int newNumber) {
		 value = newNumber;
	 }
	 
	 /**
	  * Add the specified subscript to the cell's set of subscripts
	  * @param index
	  */
	 
	 protected void setSub(int index) {
		 subs[index - 1] = index;
	 }
	 
	 /**
	  * Remove the specified subscript to the cell's set of subscripts
	  * @param index
	  */
	 
	 protected void unassignSub(int index) {
		 subs[index - 1] = unassigned;
	 }
	 
	 /**
	  * Remove all values from the cell's set of subscripts
	  */
	 
	 protected void clearSub() {
		 subs = new int[]{0,0,0,0,0,0,0,0,0};
	 }
	 
	 /**
	  * 
	  * @param value
	  * @return True if the specified value is part of the cell's set of subscripts
	  */
	 
	 protected boolean checkSub(int value) {
		 for (int i = 0; i < 9; i++) {
			 if (subs[i] == value) {
				 return false;
			 }
		 } return true;
	 }
	 
	 /**
	  * set the current value of the cell to unassigned
	  */
	 
	 protected void unassign() {
		 value = unassigned;
	 }
}
