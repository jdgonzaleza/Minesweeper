package MinesweeperTest;

import static org.junit.Assert.assertNotEquals;

import javax.swing.CellRendererPane;

import Minesweeper.Cell;
import Minesweeper.Minesweeper;
import junit.framework.TestCase;

public class MinesweeperTest extends TestCase{
	
	private Minesweeper testGame;
	
	private void setUpScenario1() {
		testGame = new Minesweeper(1, 1, 0);
		Cell[][] testBoard = new Cell[3][3];
		for(int i = 0; i< 3; i++ ){
			for(int j = 0; j< 3; j++){
				testBoard[i][j]= new Cell(0, false);				
			}
		}
		testGame.setBoard(testBoard);	}
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
	
	private void setUpScenario3() {
		testGame = new Minesweeper(5, 5, 5);
	}
	
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
	
	public void testMarkCell() {
		setUpScenario2();
		Cell[][] newBoard = testGame.getBoard();
		assertEquals("The size of the board is incorrect", 10, newBoard.length);
		boolean isMarked = testGame.getBoard()[0][0].isMarked();
		assertEquals(false, isMarked);
		testGame.markCell(0, 0);
		assertEquals(true, testGame.getBoard()[0][0].isMarked());
	}
	
	public void testMarkCell2() {
		setUpScenario2();
		testGame.markCell(0, 0);
		assertEquals(true, testGame.getBoard()[0][0].isMarked());
		testGame.markCell(0, 0);
		assertEquals(false, testGame.getBoard()[0][0].isMarked());
		
	}
	
	public void testAddAdjacentMines() {
		setUpScenario1();
		testGame.getBoard()[1][1].setMyNumber(9);
		testGame.addAdjacentMinesCount();
		int numAdjacent00 = testGame.getBoard()[0][0].getMyNumber();
		assertEquals(1, numAdjacent00);
	}
	
	public void testUncoverCell() {
		setUpScenario1();
		testGame.getBoard()[0][0].setMyNumber(9);
		testGame.addAdjacentMinesCount();
		testGame.uncoverCell(2, 2);
		assertEquals(6, testGame.getUncoveredCells());
		
	}
	
	public void testPlay() {
		setUpScenario1();
		testGame.getBoard()[0][0].setMyNumber(9);
		int num = testGame.play(0, 0);
		assertEquals(-1, num);
	}
	
	public void testPlay2() {
		setUpScenario1();
		testGame.getBoard()[0][0].setMyNumber(9);
		int num = testGame.play(1, 0);
		assertEquals(false, num == -1);
	}
}
