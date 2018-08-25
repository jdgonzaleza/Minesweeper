package Minesweeper;

import java.util.Random;

public class Minesweeper {

	private int[][] underBoard; // Tablero en el cual se realizan todas las operaciones l'ogicas

	private boolean[][] playBoard;// Tablero en donde que es visto por el usuario

	private Cell[][] board;

	private int numMines;

	private int markedMines; 

	public Minesweeper( int numColumns, int numRows, int numMines ){
		this.numMines = numMines;
		createBoard(numColumns, numRows);
		markedMines = 0; 

	}


	public void createBoard(int numColumns, int numRows){
		underBoard = new int[numRows][numColumns];
		playBoard = new boolean[numRows][numColumns];
		board = new Cell[numRows][numColumns];
		Cell newCell = new Cell(0, false);

		for(int i = 0; i< numRows; i++ ){
			for(int j = 0; j< numColumns; j++){
				board[i][j] = newCell;
			}
		}

	}

	public void bombing(int numBombs){

		int x = 0;
		int y = 0;
		Random r = new Random();
		for (int i = 0;i< numBombs; i++){

			x = r.nextInt(board.length-1);
			y = r.nextInt(board[0].length-1);

			if(board[x][y].getMyNumber() == 0)
				board[x][y].setMyNumber(9);

			for(int j = Math.max(0, x-1); j < Math.min(board.length-1, x+1); j++){
				for(int k = Math.max(0, y-1); j < Math.min(board[0].length-1, y+1); k++){
					if(board[j][k].getMyNumber() != 9){
						board[j][k].setMyNumber(board[j][k].getMyNumber()+1); 
					}
				}
			}
		}

	}

	public void uncoverCell( int row, int column){

		if(!board[row][column].isKnown()){

			board[row][column].setKnown(true);
			if(board[row][column].getMyNumber() != 9){
				for(int i = Math.max(0, row-1); i < Math.min(underBoard.length-1, row+1);i++){
					for(int j =Math.max(0, column-1); j < Math.min(underBoard[0].length-1, column+1); j++ ){
						if(board[i][j].getMyNumber()!=9){
							uncoverCell(i,j);
						}
					}
				}

			}
		}

	}
	
	public int markCell( int row, int column) {
		if(board[row][column].getMyNumber() == 9) {
			markedMines++;
			board[row][column].setMarked(true);
		}
		return markedMines;
	}
	
	public int uncoverMin(int row, int column) {
		if(board[row][column].getMyNumber() == 9) {
			board[row][column].setKnown(true);
			return -1;
		}
		return markedMines;
	}

	public void paintBoard() {
		for (int i = 0; i< board.length-1; i++ ) {
			for(int j = 0; j< board[0].length-1; j++) {
				if(!board[i][j].isKnown()) {
					System.out.println(".  ");
				}else {
					if(board[i][j].getMyNumber() == 0) {
						System.out.println("- ");
					}else if(board[i][j].getMyNumber() == 9) {
						System.out.println("* ");
					}else if(board[i][j].getMyNumber() != 9 && board[i][j].getMyNumber() != 0) {
						System.out.println(board[i][j].getMyNumber()+" ");
					} if(board[i][j].isMarked()) {
						System.out.println("P ");
					}
				}
			}
		}
	}






}