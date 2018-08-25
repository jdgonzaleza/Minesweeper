package Minesweeper;

/**
 * This class corresponds to the one who helds the information of the cells that
 * make the playing board.
 * 
 */

public class Cell {
	/**
	 * Represents the number of mine adjacent to the cell. 
	 * If the number is 9 the cell contains a mine. 
	 * If the number is 0 there is no mine adjacent to the cell.
	 * */
	private int myNumber; 

	/**
	 * Dictates whether the cell has been uncovered or not 
	 * */
	private boolean known;

	/**
	 * Dictates if the cell is marked or not.
	 * */
	private boolean marked; 


	/**
	 * Constructor method for class.
	 * */
	public Cell(int myNumber, boolean known) {
		this.myNumber = myNumber;
		this.known = known;
		marked = false;
	}


	/**
	 * Getter method for the myNumber atribute
	 * */
	public int getMyNumber() {
		return myNumber;
	}


	/**
	 * Setter method for the myNumber atribute
	 * */
	public void setMyNumber(int pnum) {
		myNumber = pnum;
	}


	/**
	 * Getter method for the known atribute
	 * */
	public boolean isKnown() {
		return known;
	}


	/**
	 * Setter method for the known atribute
	 * */
	public void setKnown(boolean pKnown) {
		known = pKnown;
	}
	/**
	 * Getter method for the marked atribute
	 * */
	public boolean isMarked() {
		return marked;
	}

	/**
	 * Setter method for the marked atribute
	 * */
	public void setMarked(boolean pMarked) {
		marked = pMarked;
	}




}
