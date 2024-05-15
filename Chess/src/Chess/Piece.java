package Chess;

import java.util.ArrayList;
/**
 * Piece.java
 * @author Andy Nguyen, Ethan Le, Pranit Agrawal, Krish Tandon, Ajay Saravanan
 * @since 5/14/2024
 * This class sets up the different behaviors of the different pieces
 */

public class Piece {
	private String name;
	private boolean isWhite;
	private int rowCoord;
	private int colCoord;
	public boolean hasMoved;
	public int order;
	
	/**
     * Constructor method for the Piece and sets up the name, the color, and the coordinates of the Piece
     * 
     * @param name - the String value of the name of the piece
     * @param isWhite - the boolean value of whether or not the piece is white
     * @param rowCoord - the int value of the row coordinate of the piece
     * @param colCoord - the int value of the col coordinate of the piece
	 */
	public Piece(String name, boolean isWhite, int rowCoord, int colCoord) {
		this.name = name;
		this.isWhite = isWhite;
		this.rowCoord = rowCoord;
		this.colCoord = colCoord;
		hasMoved = false;
	}
	
	/**
     * Method that returns the name of the piece
     * 
     * @return name - the String value of the name of the piece
	 */
	public String getName() {
		return name;
	}
	
	/**
     * Method that returns the color of the piece
     * 
     * @return the String value of the color of the piece
	 */
	public String getColor() {
		if (isWhite) {
			return "White";
		} else {
			return "Black";
		}
	}
	
	/**
     * Method that returns whether or not the piece has moved
     * 
     * @return hasMoved - the boolean value of whether or not the piece has moved
	 */
	public boolean getHasMoved() {
		return hasMoved;
	}
	
	/**
     * Method that sets whether or not the piece has moved
     * 
     * Postcondition: The hasMoved variable is set to the parameter
     * @param moved - the boolean value of whether or not the piece has moved
	 */
	public void setHasMoved(Boolean moved) {
		hasMoved = moved;
	}
	
	/**
     * Method that returns whether or not the piece is white
     * 
     * @return isWhite - the boolean value of whether or not the piece is white
	 */
	public boolean getIsWhite() {
		return isWhite;
	}
	
	/**
     * Method that sets the row of the piece
     * 
     * Postcondition: The row of the piece is set to the parameter
     * @param row - the int value of the row of the piece
	 */
	public void setRow(int row) {
		this.rowCoord = row;
	}
	
	/**
     * Method that sets the column of the piece
     * 
     * Postcondition: The column of the piece is set to the parameter
     * @param col - the int value of the column of the piece
	 */
	public void setCol(int col) {
		this.colCoord = col;
	}
	
	/**
     * Method that returns row coordinate of the piece
     * 
     * @return rowCoord - the int value of the row coordinate of the piece
	 */
	public int getRow() {
		return rowCoord;
	}
	
	/**
     * Method that returns column coordinate of the piece
     * 
     * @return colCoord - the int value of the column coordinate of the piece
	 */
	public int getCol() {
		return colCoord;
	}
	
	/**
     * Method that returns the order of the piece
     * 
     * @return order - the int value of the order of the piece
	 */
	public int getOrder() {
		return order;
	}
	
	/**
     * Shell method that returns the coordinates of the possible moves the piece can make
     * 
     * @return getMoves - an ArrayList of coordinates of where the piece can move
	 */
	public ArrayList<Coordinate> getMoves(){
		return new ArrayList<Coordinate>();
	}
	
	/**
     * Method that returns all of the possible diagonal coordinates that a piece can move until there is a 
     * piece or wall in the way.
     * 
     * @param rowAdder - the int value of how much and whether the diagonal increases or decreases by row (goes in an upward or downward direction)
     * @param colAdder - the int value of how much and whether the diagonal increase or decrease by column (to the left or to the right)
     * @return result - an ArrayList of all of the possible coordinates that the piece can move in a diagonal and also potential captures in the diagonal
	 */
	public ArrayList<Coordinate> getDiagonalCoords(int rowAdder, int colAdder){
		ArrayList<Coordinate> result = new ArrayList<Coordinate>();
		int currRow = getRow();
		int currCol = getCol();
		Tile[][] theBoard = ChessBoard.getBoard();
		
		if (!(currRow + rowAdder >= 0 && currCol + colAdder >= 0 && currRow + rowAdder < theBoard.length && currCol + colAdder < theBoard[0].length)) {
			return result;
		}
		
		currRow = currRow + rowAdder;
		currCol = currCol + colAdder;
		Tile nextTile = ChessBoard.getBoard()[currRow][currCol];
		
		while (!nextTile.isOccupied() && currRow + rowAdder >= 0 && currCol + colAdder >= 0 && currRow + rowAdder < theBoard.length && currCol + colAdder < theBoard[0].length) {
			result.add(new Coordinate(currRow, currCol));
			
			currRow = currRow + rowAdder;
			currCol = currCol + colAdder;
			nextTile = ChessBoard.getBoard()[currRow][currCol];
			
		}
		
		result.add(new Coordinate(currRow, currCol));
		
		if (nextTile.isOccupied() && !nextTile.getPiece().getColor().equals(getColor())) {
			result.add(new Coordinate(nextTile.getRowCoord(), nextTile.getColCoord()));
		}
		
		return result;
	}
	
	/**
     * Method that returns a list that is filtered through of the coordinates that are not possible to move to
     * 
     * @param list - an ArrayList of potential coordinates
     * @return result - an ArrayList of all of the actual coordinates the piece can move to
	 */
	public ArrayList<Coordinate> filterCoordinates(ArrayList<Coordinate> list){
		ArrayList<Coordinate> result = list;
		Tile[][] theBoard = ChessBoard.getBoard();
		
		for (int i = result.size() - 1; i >= 0; i--) {
			int currRow = result.get(i).getRowCoord();
			int currCol = result.get(i).getColCoord();
			
			if (currRow >= theBoard.length || currRow < 0 || currCol >= theBoard[0].length || currCol < 0) {
				result.remove(i);
			}
			
		}
		
		for (int i = result.size() - 1; i >= 0; i--) {
			int currRow = result.get(i).getRowCoord();
			int currCol = result.get(i).getColCoord();
			Tile currTile = ChessBoard.getBoard()[currRow][currCol];

			if (currTile.isOccupied() && currTile.getPiece().getIsWhite() == getIsWhite()) {
				result.remove(i);
			}
			
		}
		
		
		
		return result;
	}
	
	/**
     * Method that returns the string value of the name of the piece
     * 
     * @return the string value of the name of the piece
	 */
	public String toString() {
		return getName();
	}
}
	
/**
 * Piece.java
 * @author Andy Nguyen, Ethan Le, Pranit Agrawal, Krish Tandon, Ajay Saravanan
 * @since 5/14/2024
 * This class extends the Piece class and defines the different actions that a pawn can perform
 */
class Pawn extends Piece {

	/**
     * Constructor method for the Pawn and sets up the name, the color, and the coordinates of the Piece.
     * This also sets the pawn to its order: 1.
     * 
     * @param isWhite - the boolean value of whether or not the piece is white
     * @param rowCoord - the int value of the row coordinate of the piece
     * @param colCoord - the int value of the col coordinate of the piece
	 */
	public Pawn(boolean isWhite, int rowCoord, int colCoord) {
		super("Pawn", isWhite, rowCoord, colCoord);
		order = 1;
	}
	
	/**
     * @Override method that gets the possible moves that a pawn can do.
     * A pawn can only move forward by one (or two if its in its first turn) and can capture diagonally.
     * 
     * @return result - an ArrayList of coordinates of where the pawn can move
	 */
	public ArrayList<Coordinate> getMoves(){
		
		ArrayList<Coordinate> result = new ArrayList<Coordinate>();
		int colorMultiplier;
		ArrayList<Coordinate> diagonalCoords;
		
		if (getColor().equals("White")) {
			colorMultiplier = -1;
		} else {
			colorMultiplier = 1;
		}
		
		if (getHasMoved() == false) {
			result.add(new Coordinate(getRow() + 2*colorMultiplier, getCol()));

		}
		
		diagonalCoords = getDiagonalCoords();
		for (int i = 0; i < diagonalCoords.size(); i++) {
			result.add(diagonalCoords.get(i));
		}
		
		if (!ChessBoard.getBoard()[getRow() + 1 * colorMultiplier][getCol()].isOccupied()) {
			result.add(new Coordinate(getRow() + 1 * colorMultiplier, getCol()));
		}

		result = filterCoordinates(result);
		
		return result;
	}
	
	
	/**
     * @Override method that gets the possible diagonal moves of where the pawn can move.
     * A pawn can only move diagonal when it is capturing
     * 
     * @return result - an ArrayList of diagonal coordinates of where the pawn can move
	 */
	public ArrayList<Coordinate> getDiagonalCoords(){
		ArrayList<Coordinate> result = new ArrayList<Coordinate>();
		
		result.add(new Coordinate(getRow() + 1, getCol() + 1));
		result.add(new Coordinate(getRow() + 1, getCol() - 1));
		result.add(new Coordinate(getRow() - 1, getCol() + 1));
		result.add(new Coordinate(getRow() - 1, getCol() - 1));
		
		result = filterCoordinates(result);
		
		for (int i = result.size() - 1; i >= 0; i--) {
			int currRow = result.get(i).getRowCoord();
			int currCol = result.get(i).getColCoord();
			Tile currTile = ChessBoard.getBoard()[currRow][currCol];
			
			if (!currTile.isOccupied() || currTile.isOccupied() && currTile.getPiece().getIsWhite() == getIsWhite()) {
				result.remove(i);
			}
		}
		
		return result;
	}
	
	
}

/**
 * Knight.java
 * @author Andy Nguyen, Ethan Le, Pranit Agrawal, Krish Tandon, Ajay Saravanan
 * @since 5/14/2024
 * This class extends the Piece class and defines the different actions that a knight can perform
 */

class Knight extends Piece {
	/**
     * Constructor method for the Knight and sets up the name, the color, and the coordinates of the Piece.
     * This also sets the knight to its order: 2.
     * 
     * @param isWhite - the boolean value of whether or not the piece is white
     * @param rowCoord - the int value of the row coordinate of the piece
     * @param colCoord - the int value of the col coordinate of the piece
	 */
	public Knight(boolean isWhite, int rowCoord, int colCoord) {
		super("Knight", isWhite, rowCoord, colCoord);
		order = 2;
	}
	
	/**
     * @Override method that gets the possible moves that a knight can do.
     * A knight can only move in an L shape.
     * 
     * @return result - an ArrayList of coordinates of where the knight can move
	 */
	public ArrayList<Coordinate> getMoves(){
		ArrayList<Coordinate> result = new ArrayList<Coordinate>();

		result.add(new Coordinate(getRow() + 2, getCol() + 1));
		result.add(new Coordinate(getRow() + 2, getCol() - 1));
		result.add(new Coordinate(getRow() - 2, getCol() + 1));
		result.add(new Coordinate(getRow() - 2, getCol() - 1));
		
		result.add(new Coordinate(getRow() + 1, getCol() + 2));
		result.add(new Coordinate(getRow() + 1, getCol() - 2));
		result.add(new Coordinate(getRow() - 1, getCol() + 2));
		result.add(new Coordinate(getRow() - 1, getCol() - 2));
		
		return filterCoordinates(result);
	}
	
	
}

/**
 * Bishop.java
 * @author Andy Nguyen, Ethan Le, Pranit Agrawal, Krish Tandon, Ajay Saravanan
 * @since 5/14/2024
 * This class extends the Piece class and defines the different actions that a bishop can perform
 */
class Bishop extends Piece {
	/**
     * Constructor method for the Bishop and sets up the name, the color, and the coordinates of the Piece.
     * This also sets the Bishop to its order: 3.
     * 
     * @param isWhite - the boolean value of whether or not the piece is white
     * @param rowCoord - the int value of the row coordinate of the piece
     * @param colCoord - the int value of the col coordinate of the piece
	 */
	public Bishop(boolean isWhite, int rowCoord, int colCoord) {
		super("Bishop", isWhite, rowCoord, colCoord);
		order = 3;
	}
	
	/**
     * @Override method that gets the possible moves that a Bishop can do.
     * A bishop can only move diagonally
     * 
     * @return result - an ArrayList of coordinates of where the bishop can move
	 */
	public ArrayList<Coordinate> getMoves(){
		ArrayList<Coordinate> result = new ArrayList<Coordinate>();
		result.addAll(getDiagonalCoords(1, 1));
		result.addAll(getDiagonalCoords(-1, -1));
		result.addAll(getDiagonalCoords(-1, 1));
		result.addAll(getDiagonalCoords(1, -1));
		
		return filterCoordinates(result);
	}
	
}


/**
 * Rook.java
 * @author Andy Nguyen, Ethan Le, Pranit Agrawal, Krish Tandon, Ajay Saravanan
 * @since 5/14/2024
 * This class extends the Piece class and defines the different actions that a Rook can perform
 */
class Rook extends Piece {
	/**
     * Constructor method for the Rook and sets up the name, the color, and the coordinates of the Piece.
     * This also sets the Rook to its order: 4.
     * 
     * @param isWhite - the boolean value of whether or not the piece is white
     * @param rowCoord - the int value of the row coordinate of the piece
     * @param colCoord - the int value of the col coordinate of the piece
	 */
	public Rook(boolean isWhite, int rowCoord, int colCoord) {
		super("Rook", isWhite, rowCoord, colCoord);
		order = 4;
	}
	
	/**
     * @Override method that gets the possible moves that a Rook can do.
     * A rook can only move to all available spaces to the left, right up, and down.
     * 
     * @return result - an ArrayList of coordinates of where the rook can move
	 */
	public ArrayList<Coordinate> getMoves(){
		ArrayList<Coordinate> result = new ArrayList<Coordinate>();
		result.addAll(getDiagonalCoords(0, 1));
		result.addAll(getDiagonalCoords(1, 0));
		result.addAll(getDiagonalCoords(-1, 0));
		result.addAll(getDiagonalCoords(0, -1));
		
		return filterCoordinates(result);
		 
	}
	
}

/**
 * Queen.java
 * @author Andy Nguyen, Ethan Le, Pranit Agrawal, Krish Tandon, Ajay Saravanan
 * @since 5/14/2024
 * This class extends the Piece class and defines the different actions that a Queen can perform
 */
class Queen extends Piece {
	/**
     * Constructor method for the Queen and sets up the name, the color, and the coordinates of the Piece.
     * This also sets the Rook to its order: 5.
     * 
     * @param isWhite - the boolean value of whether or not the piece is white
     * @param rowCoord - the int value of the row coordinate of the piece
     * @param colCoord - the int value of the col coordinate of the piece
	 */
	public Queen(boolean isWhite, int rowCoord, int colCoord) {
		super("Queen", isWhite, rowCoord, colCoord);
		order = 5;
	}
	
	/**
     * @Override method that gets the possible moves that a Queen can do.
     * A Queen can only move diagonally and forward, backward, left and right in all available spaces.
     * 
     * @return result - an ArrayList of coordinates of where the queen can move
	 */
	public ArrayList<Coordinate> getMoves(){
		ArrayList<Coordinate> result = new ArrayList<Coordinate>();
		result.addAll(getDiagonalCoords(0, 1));
		result.addAll(getDiagonalCoords(1, 0));
		result.addAll(getDiagonalCoords(-1, 0));
		result.addAll(getDiagonalCoords(0, -1));
		result.addAll(getDiagonalCoords(1, 1));
		result.addAll(getDiagonalCoords(-1, -1));
		result.addAll(getDiagonalCoords(-1, 1));
		result.addAll(getDiagonalCoords(1, -1));
		
		return filterCoordinates(result);
	}
}

/**
 * King.java
 * @author Andy Nguyen, Ethan Le, Pranit Agrawal, Krish Tandon, Ajay Saravanan
 * @since 5/14/2024
 * This class extends the Piece class and defines the different actions that a King can perform
 */
class King extends Piece {
	/**
     * Constructor method for the King and sets up the name, the color, and the coordinates of the Piece.
     * This also sets the Rook to its order: 6.
     * 
     * @param isWhite - the boolean value of whether or not the piece is white
     * @param rowCoord - the int value of the row coordinate of the piece
     * @param colCoord - the int value of the col coordinate of the piece
	 */
	public King(boolean isWhite, int rowCoord, int colCoord) {
		super("King", isWhite, rowCoord, colCoord);
		order = 6;
	}
	
	/**
     * @Override method that gets the possible moves that a King can do.
     * A King can only move diagonally and forward, backward, left and right one space
     * 
     * @return result - an ArrayList of coordinates of where the queen can move
	 */
	public ArrayList<Coordinate> getMoves(){
		ArrayList<Coordinate> result = new ArrayList<Coordinate>();
		result.addAll(getDiagonalCoords());
		result.add(new Coordinate (getRow(), getCol() +1));
		result.add(new Coordinate (getRow(), getCol() -1));
		result.add(new Coordinate (getRow() + 1, getCol()));
		result.add(new Coordinate (getRow() - 1, getCol()));
		

		return filterCoordinates(result);
	}
	
	/**
     * @Override method that gets the possible diagonal moves of where the King can move.
     * A King can only move diagonal one space
     * 
     * @return result - an ArrayList of diagonal coordinates of where the king can move
	 */
	public ArrayList<Coordinate> getDiagonalCoords(){
		ArrayList<Coordinate> result = new ArrayList<Coordinate>();
		
		result.add(new Coordinate(getRow() + 1, getCol() + 1));
		result.add(new Coordinate(getRow() + 1, getCol() - 1));
		result.add(new Coordinate(getRow() - 1, getCol() + 1));
		result.add(new Coordinate(getRow() - 1, getCol() - 1));
		
		result = filterCoordinates(result);
		
		for (int i = result.size() - 1; i >= 0; i--) {
			int currRow = result.get(i).getRowCoord();
			int currCol = result.get(i).getColCoord();
			Tile currTile = ChessBoard.getBoard()[currRow][currCol];
			
			if (currTile.isOccupied() && currTile.getPiece().getIsWhite() == getIsWhite()) {
				result.remove(i);
			}
		}
		
		return result;
	}
}