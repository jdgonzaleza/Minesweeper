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

		String move = "";
		String moveSplit[] = null;
		while(! (game.getMarkedMines() == numMines)) {
			game.paintBoard();
			System.out.println("Insert your move... \n");
			
			move =sc.nextLine();
			System.out.println(move.split(" ")[2]);
			
			if(move.split(" ")[2].equals("U")) {
				if(game.play(Integer.parseInt(move.split(" ")[0]),Integer.parseInt(move.split(" ")[1])) != -1) {
					System.out.println("asd");
				}else {
					break;
				}
			}else if( move.split(" ")[2].equals("M")) {
				game.markCell(Integer.parseInt(move.split(" ")[0]), Integer.parseInt(move.split(" ")[1]));
			}else {
				System.out.println("please enter a valid command \n<Row Column U/M>");
			}
		}
		if(game.getMarkedMines() == numMines) {
			System.out.println("Congratulations, You've won!");
		}else {
			System.out.println("Keep Trying");
		}
	}

}
