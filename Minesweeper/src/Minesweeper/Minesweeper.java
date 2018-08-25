package Minesweeper;

import java.util.Random;
/**
 * This class corresponds to the one who helds the board and all its functionalities.
 * The board is made up with a 2D array of "Cell" objects.
 * 
 */
public class Minesweeper {
	
	//----------------------
	//  	ATRIBUTES
	//----------------------
	
	/** The 2D array of cells. This array represent the playing board*/
	private Cell[][] board;
	
	/** This atribute represents the number of marked mines in the game*/
	private int markedMines;
	
	/**This atribute represent the number of cells that where marked and do not correspond to a mine*/
	private int incoMarks;
	
	/**This atribute represent the number of uncovered cells in the game. This atribute is primarily used in testing */
	private int uncoveredCells;

	/**The constructor method that initialize the atributes of the class.
	 * Also it creates the playing board with all its components
	 * */
	public Minesweeper( int numColumns, int numRows, int numMines ){
		createBoard(numColumns, numRows);
		addMines(numMines);
		addAdjacentMinesCount();
		markedMines = 0; 
		incoMarks =0;
		uncoveredCells = 0;

	}
	/**
	 * Getter method for the incoMarks atribute
	 * */
	public int getIncoMarks() {
		return incoMarks;
	}
	
	/**
	 * Getter method for the markedMines atribute
	 * */
	public int getMarkedMines() {
		return markedMines;
	}
	
	/**
	 * Getter method for the uncoveredCells atribute
	 * */
	public int getUncoveredCells() {
		return uncoveredCells;
	}

	/**
	 * Getter method for the board.
	 * It is used in the testing.
	 * */
	public Cell[][] getBoard(){
		return board;
	}
	
	/**
	 * Setter method for the board
	 * */
	public void setBoard(Cell[][] pBoard) {
		board = pBoard;
	}
	
	/**
	 * This method creates the ceels 2D array.
	 * the cells at this point are empty.
	 * */
	public void createBoard(int numColumns, int numRows){
		board = new Cell[numRows][numColumns];
		for(int i = 0; i< numRows; i++ ){
			for(int j = 0; j< numColumns; j++){
				board[i][j]= new Cell(0, false);				
			}
		}

	}

	/**
	 * This method adds mines to the recently created board.
	 * In this case the number 9 represents the bomb.
	 * */
	public void addMines(int numBombs){
		int x = 0;
		int y = 0;
		Random r = new Random();
		for (int i = 0;i< numBombs; i++){
			while(true) {
				x = r.nextInt(board.length-1);
				y = r.nextInt(board[0].length-1);
				if(board[x][y].getMyNumber() != 9) {
					board[x][y].setMyNumber(9);	
					break;
				}
			} 
		}

	}
	
	/**
	 * This method assigns the number of adjecent mines to the empty cells.
	 * this number can go from 0 to 8.
	 * */
	public void addAdjacentMinesCount() {

		for(int i = 0; i< board.length-1; i++) {
			for(int j = 0; j< board[0].length-1; j++) {
				if(board[i][j].getMyNumber() !=9) {
					int count = 0;
					for(int p = i-1; p<=i+1; p++) {
						for(int q = j-1; q<= j+1; q++) {
							if(p>=0 && p<board.length && q>= 0 && q < board[0].length) {
								if(board[p][q].getMyNumber() == 9) {
									count++;
								}
							}
						}
					}
					board[i][j].setMyNumber(count);
				}
			}
		}
	}
	
	/**
	 * This recursive method uncover the cell that was select and the its adjecent empty cells 
	 * */
	public void uncoverCell( int row, int column){
		
		if(!board[row][column].isKnown()){

			board[row][column].setKnown(true);
			if(board[row][column].getMyNumber() != 9){
				for(int i = Math.max(0, row-1); i < Math.min(board.length-1, row+1);i++){
					for(int j =Math.max(0, column-1); j < Math.min(board[0].length-1, column+1); j++ ){
						if(board[i][j].getMyNumber()!=9){
							uncoveredCells++;
							uncoverCell(i,j);
						}
					}
				}

			}
		}
	}

	/**
	 * This method is used in order to mark the cells that where "flagged" or marked by the user.
	 * Also, in order to end the game, this method is also used to unmark a previously marked cell 
	 * */
	public void markCell( int row, int column) {
		if(board[row][column].isKnown()) {
			System.out.println("Please try with another cell, this one is already uncovered");
		}else {
			if(board[row][column].isMarked()) {
				board[row][column].setMarked(false);
				if(board[row][column].getMyNumber() == 9) {
					markedMines--;	
				}else {
					incoMarks--;
				}
			}else{
				if(board[row][column].getMyNumber() == 9) {
					markedMines++;	
				}else {
					incoMarks++;
				}
				board[row][column].setMarked(true);
			}
		}
		

	}
	
	/**
	 * This method is used in order to play the minesweeper game. 
	 * If the user selected a mine then the method return -1, which means that the game is over.
	 * If the user selected an empty cell then the cell and its empty adjacent cells are uncovered.
	 * */
	public int play(int row, int column) {
		if(board[row][column].getMyNumber() == 9) {
			board[row][column].setKnown(true);
			return -1;
		}else {
			uncoverCell(row, column);
			return markedMines;
		}
	}
	
	/**
	 * This method is used in order to draw the board in the console.
	 * */

	public void drawBoard() {
		for (int i = 0; i< board.length-1; i++ ) {
			for(int j = 0; j< board[0].length-1; j++) {
				if(board[i][j].isMarked()) {
					System.out.print("P  ");
				}else if(!board[i][j].isKnown()) {
					System.out.print(".  " );
				}else{
					if(board[i][j].getMyNumber() == 0) {
						System.out.print("_  ");
					}else if(board[i][j].getMyNumber() == 9) {
						System.out.print("*  ");
					}else if(board[i][j].getMyNumber() != 9 && board[i][j].getMyNumber() != 0) {
						System.out.print(board[i][j].getMyNumber()+"  ");
					}
				}
			}

			System.out.println();
		}
	}
	/**
	 * This method is used in order to draw the mines when the user lost the game. 
	 * */
	public void drawMines() {
		for(int i = 0; i< board.length-1;i++) {
			for(int j = 0; j< board[0].length; j++) {
				if(board[i][j].getMyNumber() == 9) {
					board[i][j].setKnown(true);
				}
			}
		}
	}






}