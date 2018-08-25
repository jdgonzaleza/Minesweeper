package Minesweeper;

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
	
	private boolean marked; 
	
	
	
	public Cell(int myNumber, boolean known) {
		this.myNumber = myNumber;
		this.known = known;
		marked = false;
	}



	public int getMyNumber() {
		return myNumber;
	}



	public void setMyNumber(int myNumber) {
		this.myNumber = myNumber;
	}



	public boolean isKnown() {
		return known;
	}



	public void setKnown(boolean known) {
		this.known = known;
	}
	
	public boolean isMarked() {
		return marked;
	}
	
	public void setMarked(boolean marked) {
		this.marked = marked;
	}
	
	
	
	
}