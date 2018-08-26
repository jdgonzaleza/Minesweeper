package Minesweeper;

import java.util.Scanner;

public class Main {

	public static Minesweeper game;
	
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.println("Insert the number of Rows \n");
		int rows = sc.nextInt();

		System.out.println("Insert the number columns \n ");
		int columns = sc.nextInt();

		System.out.println("Insert the number of mines \n");
		int numMines = sc.nextInt();
		sc.nextLine();
		game = new Minesweeper(columns+1, rows+1, numMines);
		System.out.println("\nTo make a move please follow these instructions:");
		System.out.println("1) Introduce the cordinates separated by a blank space. Example: x-cor y-cor");
		System.out.println("2) In the same line, introduce the type of move you'll do. U for uncover, M for marking ");
		System.out.println("Complete example: 1 2 U  " );
		System.out.println("Other example: 1 2 M  ");
		System.out.println("\n");
		String move = "";
		while(! (game.getMarkedMines() == numMines && game.getIncoMarks() ==0)) {
			game.drawBoard();
			System.out.println("\nInsert your move... \n");

			move =sc.nextLine();

			if(move.split(" ")[2].equals("U")) {
				if(game.play(Integer.parseInt(move.split(" ")[0])-1,Integer.parseInt(move.split(" ")[1])-1) != -1) {
				}else {
					break;
				}
			}else if( move.split(" ")[2].equals("M")) {
				game.markCell(Integer.parseInt(move.split(" ")[0])-1, Integer.parseInt(move.split(" ")[1])-1);
			}else {
				System.out.println("please enter a valid command \n<Row Column U/M>");
			}
		}
		if(game.getMarkedMines() == numMines) {
			System.out.println("Congratulations, You've won!");
		}else {
			game.drawMines();
			System.out.println("Keep Trying");
		}
		game.drawBoard();
		sc.close();

	}

}
