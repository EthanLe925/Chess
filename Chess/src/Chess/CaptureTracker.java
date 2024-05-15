package Chess;

import java.util.ArrayList;

/**
 * CaptureTracker.java
 * @author Andy Nguyen, Ethan Le, Pranit Agrawal, Krish Tandon, Ajay Saravanan
 * @since 5/14/2024
 * This class keeps track of which pieces are captured by the other player and displays it
 */
public class CaptureTracker {
	private boolean whiteTurn;
	private ArrayList<Piece> blackCaptures;
	private ArrayList<Piece> whiteCaptures;
	
	/**
     * Constructor method for the CaptureTracker and initializes arrays that keep track of the captured pieces
     * 
     * Postcondition: the first turn is white (so whiteTurn is set to true), and the captured lists are initialized
	 */
	public CaptureTracker() {
		whiteTurn = true;
		blackCaptures = new ArrayList<Piece>();
		whiteCaptures = new ArrayList<Piece>();	
	}
	
	/**
     * Method that takes in a piece and adds it into the captured lists that corresponds to the piece's color
     * 
     * Postcondition: adds the piece into the captured lists according to the piece color
     * @param p - a Piece that has been captured
	 */
	public void addCapture(Piece p) {
		if (p.getIsWhite()) {
			whiteCaptures.add(p);
		} else {
			blackCaptures.add(p);
		}

	}
	
	/**
     * Method that returns a string of all of the captured pieces that are white.
     * 
     * For example: Pawn --> Pn, Rook --> Rk, Queen --> Qn
     * 
     * @return result - a string that displays all of the white pieces that are captured. The names are displayed with the first letter
     * of the piece and the last letter of the piece.
	 */
	public String getWhiteCaptures() {
		String result = "";
		
		for (Piece p: sortAlphOrder(whiteCaptures)) {
			result += "" + p.getName().charAt(0) + p.getName().charAt(p.getName().length() - 1) + ", ";
		}
		
		if (result.length() > 2) {
			return result.substring(0, result.length() - 2);
			
		} else {
			return result;
			
		}
		
	}
	
	/**
     * Method that returns a string of all of the captured pieces that are black.
     * 
     * For example: Pawn --> Pn, Rook --> Rk, Queen --> Qn
     * 
     * @return result - a string that displays all of the black pieces that are captured. The names are displayed with the first letter
     * of the piece and the last letter of the piece.
	 */
	public String getBlackCaptures() {
		String result = "";
		
		for (Piece p: sortAlphOrder(blackCaptures)) {
			result += "" + p.getName().charAt(0) + p.getName().charAt(p.getName().length() - 1) + ", ";
		}
		
		if (result.length() > 2) {
			return result.substring(0, result.length() - 2);
			
		} else {
			return result;
			
		}
		
		
	}
	
	/**
     * Method that returns whether or not the turn is currently for the player using white pieces.
     * 
     * @return whiteTurn - a boolean variable that tells whether or not if it is the white player's turn.
	 */
	public boolean isWhiteTurn() {
		return whiteTurn;
	}
	
	/**
     * Method that changes the whiteTurn variable and switches the current player turn
     * 
     * Postcondition: The player's turn is switched and goes to the opposite player's turn; whiteTurn becomes opposite
	 */
	public void switchTurn() {
		whiteTurn = !whiteTurn;
	}
	
	/**
     * Method that uses linear sort to sort the input list of Pieces according to the piece's order that is initialized in each Piece.
     * The piece's order goes: Pawn(1) --> Knight(2) --> Bishop(3) --> Rook(4) --> Queen(5) --> King(6).
     * So, Pawns will always be at the beginning of the list, and Knights will always be second, etc.
     * 
     * @param list - an ArrayList of Pieces that needs to be sorted by order
     * @return result - an ArrayList that is sorted by Piece order
	 */
	public ArrayList<Piece> sortAlphOrder(ArrayList<Piece> list){
		ArrayList<Piece> result = list;
		
		for (int i = 0; i < result.size(); i++) {
			for (int j = i + 1; j < result.size(); j++) {
				if (result.get(j).getOrder() < result.get(i).getOrder()) {
					Piece temp = result.get(i);
					result.set(i, result.get(j));
					result.set(j, temp);
					
				}
				
			}
			
		}
		return result;
	}
	

	
}
