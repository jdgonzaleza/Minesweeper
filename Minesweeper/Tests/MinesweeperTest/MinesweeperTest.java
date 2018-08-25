package MinesweeperTest;

import Minesweeper.Cell;
import Minesweeper.Minesweeper;
import junit.framework.TestCase;

/**
 * 
 * This class tests the most important methos that constructs the minesweeper game.
 * 
 * */

public class MinesweeperTest extends TestCase{
	
	/**Instance used in order to test the methods*/
	private Minesweeper testGame;
	
	/** Testing scenario # 1*/
	private void setUpScenario1() {
		testGame = new Minesweeper(1, 1, 0);
		Cell[][] testBoard = new Cell[3][3];
		for(int i = 0; i< 3; i++ ){
			for(int j = 0; j< 3; j++){
				testBoard[i][j]= new Cell(0, false);				
			}
		}
		testGame.setBoard(testBoard);	}

	/** Testing scenario # 2*/
	private void setUpScenario2() {
		testGame = new Minesweeper(1, 1, 0);
		Cell[][] testBoard = new Cell[10][10];
		for(int i = 0; i< 10; i++ ){
			for(int j = 0; j< 10; j++){
				testBoard[i][j]= new Cell(0, false);				
			}
		}
		testGame.setBoard(testBoard);
	}

	/** Testing scenario # 1*/
	private void setUpScenario3() {
		testGame = new Minesweeper(5, 5, 5);
	}
	
	/** 
	 * Tests if the mines are added correctly into the board.
	 * */
	public void testAddMines() {
		setUpScenario3();
		int count = 0;
		for(int i = 0; i< testGame.getBoard().length-1; i++) {
			for(int j = 0; j< testGame.getBoard().length-1; j++) {
				if(testGame.getBoard()[i][j].getMyNumber() == 9)
					count++;
			}
		}
		assertEquals("the number of bombs in the board is not the one that was expected", 5, count);
	}
	
	/**
	 * Tests if the cell that the user placed as marked is in fact marked. 
	 * */
	public void testMarkCell() {
		setUpScenario2();
		Cell[][] newBoard = testGame.getBoard();
		assertEquals("The size of the board is incorrect", 10, newBoard.length);
		boolean isMarked = testGame.getBoard()[0][0].isMarked();
		assertEquals(false, isMarked);
		testGame.markCell(0, 0);
		assertEquals(true, testGame.getBoard()[0][0].isMarked());
	}
	/**
	 * Tests if a cell that was previously marked can be unmarked.
	 * */
	public void testMarkCell2() {
		setUpScenario2();
		testGame.markCell(0, 0);
		assertEquals(true, testGame.getBoard()[0][0].isMarked());
		testGame.markCell(0, 0);
		assertEquals(false, testGame.getBoard()[0][0].isMarked());
		
	}
	
	/**
	 * Tests if the method that adds the number of adjecent mines works correctly
	 * */
	public void testAddAdjacentMines() {
		setUpScenario1();
		testGame.getBoard()[1][1].setMyNumber(9);
		testGame.addAdjacentMinesCount();
		int numAdjacent00 = testGame.getBoard()[0][0].getMyNumber();
		assertEquals(1, numAdjacent00);
	}
	
	/**
	 * Tests if there is the correct amount of uncovered cells when an empty cell is selected.
	 * */
	public void testUncoverCell() {
		setUpScenario1();
		testGame.getBoard()[0][0].setMyNumber(9);
		testGame.addAdjacentMinesCount();
		testGame.uncoverCell(2, 2);
		assertEquals(6, testGame.getUncoveredCells());
		
	}
	/**
	 * Tests if the game ends when the user hits a mine.
	 * */
	public void testPlay() {
		setUpScenario1();
		testGame.getBoard()[0][0].setMyNumber(9);
		int num = testGame.play(0, 0);
		assertEquals(-1, num);
	}
	
	/**
	 * Tests if the game keeps going if the user selected an empty cell.
	 * */
	public void testPlay2() {
		setUpScenario1();
		testGame.getBoard()[0][0].setMyNumber(9);
		int num = testGame.play(1, 0);
		assertEquals(false, num == -1);
	}
}
