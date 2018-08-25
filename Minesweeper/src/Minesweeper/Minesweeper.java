package Minesweeper;

import java.util.Random;

public class Minesweeper {

	private int[][] underBoard; // Tablero en el cual se realizan todas las operaciones l'ogicas

	private boolean[][] playBoard;// Tablero en donde que es visto por el usuario

	private int numMines;

	private int numSeenCells; // The number of cells that have been opened. 

	public Minesweeper( int numColumns, int numRows, int numMines ){
		this.numMines = numMines;
		createBoard(numColumns, numRows);

	}


	public void createBoard(int numColumns, int numRows){
		underBoard = new int[numRows][numColumns];
		playBoard = new boolean[numRows][numColumns];
		for(int i = 0; i< numRows; i++ ){
			for(int j = 0; j< numColumns; j++){
				underBoard[i][j]=0;
				playBoard[i][j]=false;
			}
		}

	}

	public void bombing(int numBombs){

		int x = 0;
		int y = 0;
		Random r = new Random();
		for (int i = 0;i< numBombs; i++){

			x = r.nextInt(underBoard.length-1);
			y = r.nextInt(underBoard[0].length-1);

			if(underBoard[x][y] == 0){

				underBoard[x][y]= 9;
			}

			for(int j = Math.max(0, x-1); j < Math.min(underBoard.length-1, x+1); j++){
				for(int k = Math.max(0, y-1); j < Math.min(underBoard[0].length-1, y+1); k++){
					if(underBoard[j][k] != 9){
						underBoard[j][k] =underBoard[j][k]+1;
					}
				}
			}
		}

	}
	
	public void uncover( int row, int column){
		
		if(!playBoard[row][column]){
			
			playBoard[row][column] = true;
			if(underBoard[row][column] != 9){
				for(int i = Math.max(0, row-1); i < Math.min(underBoard.length-1, row+1);i++){
					for(int j =Math.max(0, column-1); j < Math.min(underBoard[0].length-1, column+1); j++ ){
						if(underBoard[i][j]!=9){
							uncover(i,j);
						}
					}
				}
				
			}
		}
		
	}
	






}