package Chess;

import java.util.ArrayList;

public class Piece {
	private String name;
	private boolean isWhite;
	private int rowCoord;
	private int colCoord;
	public boolean hasMoved;
	public int order;
	
	public Piece(String name, boolean isWhite, int rowCoord, int colCoord) {
		this.name = name;
		this.isWhite = isWhite;
		this.rowCoord = rowCoord;
		this.colCoord = colCoord;
		hasMoved = false;
	}
	
	public String getName() {
		return name;
	}
	
	public String getColor() {
		if (isWhite) {
			return "White";
		} else {
			return "Black";
		}
	}
	
	public boolean getHasMoved() {
		return hasMoved;
	}
	
	public void setHasMoved(Boolean moved) {
		hasMoved = moved;
	}
	
	public boolean getIsWhite() {
		return isWhite;
	}
	
	public void setRow(int row) {
		this.rowCoord = row;
	}
	
	public void setCol(int col) {
		this.colCoord = col;
	}
	
	public int getRow() {
		return rowCoord;
	}
	
	public int getCol() {
		return colCoord;
	}
	
	public int getOrder() {
		return order;
	}
	
	public ArrayList<Coordinate> getMoves(){
		return new ArrayList<Coordinate>();
	}
	
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
	
	public String toString() {
		return getName();
	}
}
	

class Pawn extends Piece {

	public Pawn(boolean isWhite, int rowCoord, int colCoord) {
		super("Pawn", isWhite, rowCoord, colCoord);
		order = 1;
	}
	
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

class Knight extends Piece {
	public Knight(boolean isWhite, int rowCoord, int colCoord) {
		super("Knight", isWhite, rowCoord, colCoord);
		order = 2;
	}
	
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

class Bishop extends Piece {
	public Bishop(boolean isWhite, int rowCoord, int colCoord) {
		super("Bishop", isWhite, rowCoord, colCoord);
		order = 3;
	}
	
	public ArrayList<Coordinate> getMoves(){
		ArrayList<Coordinate> result = new ArrayList<Coordinate>();
		result.addAll(getDiagonalCoords(1, 1));
		result.addAll(getDiagonalCoords(-1, -1));
		result.addAll(getDiagonalCoords(-1, 1));
		result.addAll(getDiagonalCoords(1, -1));
		
		return filterCoordinates(result);
	}
	
}

class Rook extends Piece {
	public Rook(boolean isWhite, int rowCoord, int colCoord) {
		super("Rook", isWhite, rowCoord, colCoord);
		order = 4;
	}
	
	public ArrayList<Coordinate> getMoves(){
		ArrayList<Coordinate> result = new ArrayList<Coordinate>();
		result.addAll(getDiagonalCoords(0, 1));
		result.addAll(getDiagonalCoords(1, 0));
		result.addAll(getDiagonalCoords(-1, 0));
		result.addAll(getDiagonalCoords(0, -1));
		
		return filterCoordinates(result);
		 
	}
	
}

class Queen extends Piece {
	public Queen(boolean isWhite, int rowCoord, int colCoord) {
		super("Queen", isWhite, rowCoord, colCoord);
		order = 5;
	}
	
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

class King extends Piece {
	public King(boolean isWhite, int rowCoord, int colCoord) {
		super("King", isWhite, rowCoord, colCoord);
		order = 6;
	}
	public ArrayList<Coordinate> getMoves(){
		ArrayList<Coordinate> result = new ArrayList<Coordinate>();
		result.addAll(getDiagonalCoords());
		result.add(new Coordinate (getRow(), getCol() +1));
		result.add(new Coordinate (getRow(), getCol() -1));
		result.add(new Coordinate (getRow() + 1, getCol()));
		result.add(new Coordinate (getRow() - 1, getCol()));
		

		return filterCoordinates(result);
	}
	
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