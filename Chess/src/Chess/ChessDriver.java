package Chess;

import java.util.Scanner;

/**
 * ChessDriver.java
 * @author Andy Nguyen, Ethan Le, Pranit Agrawal, Krish Tandon, Ajay Saravanan
 * @since 5/14/2024
 * Main method that calls the method to set up the board and display the board, starting up the game.
 */
public class ChessDriver {

	public static void main(String[] args) {
		String input;
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Would you like to run the test program or the actual game?\n(type \'t\' for the test program or \'g\' for the game)");
		input = scan.nextLine();
		while (!input.equals("t") && !input.equals("g")) {
			System.out.println("Please enter a valid input \n (type \'t\' for the test program or \'g\' for the game");
			input = scan.nextLine();
		}
		
		if (input.equals("t")) {
			ChessBoard.setTesting(true);
		} 
		ChessBoard.setUpBoard();
		ChessBoard.displayBoard();

	}

}
