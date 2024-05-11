package Chess;

import java.util.ArrayList;

public class Piece {
	private String name;
	private boolean isWhite;
	private int rowCoord;
	private int colCoord;
	public boolean hasMoved;
	
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
	
	public ArrayList<Coordinate> getMoves(){
		return new ArrayList<Coordinate>();
	}
	
	public ArrayList<Coordinate> getDiagonalCoords(){
		return new ArrayList<Coordinate>();
	}
}
	

class Pawn extends Piece {

	public Pawn(boolean isWhite, int rowCoord, int colCoord) {
		super("Pawn", isWhite, rowCoord, colCoord);
	}
	
	public ArrayList<Coordinate> getMoves(){
		
		ArrayList<Coordinate> result = new ArrayList<Coordinate>();
		int colorMultiplier;
		ArrayList<Coordinate> diagonalCoords;
		Tile[][] theBoard = ChessBoard.getBoard();
		
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
		
		result.add(new Coordinate(getRow() + 1*colorMultiplier, getCol()));
			
		for (int i = result.size() - 1; i >= 0; i--) {
			if (result.get(i).getRowCoord() >= theBoard.length || result.get(i).getRowCoord() < 0 || result.get(i).getColCoord() >= theBoard[0].length || result.get(i).getColCoord() < 0) {
				result.remove(i);
			}
		}
		
		return result;
	}
	
	public ArrayList<Coordinate> getDiagonalCoords(){
		ArrayList<Coordinate> result = new ArrayList<Coordinate>();
		
		result.add(new Coordinate(getCol() + 1, getRow() + 1));
		result.add(new Coordinate(getCol() + 1, getRow() - 1));
		result.add(new Coordinate(getCol() - 1, getRow() + 1));
		result.add(new Coordinate(getCol() - 1, getRow() - 1));
		
		for (int i = result.size() - 1; i >= 0; i--) {
			int rowCoord = result.get(i).getRowCoord();
			int colCoord = result.get(i).getColCoord();
					
			if (rowCoord >= ChessBoard.getBoard().length || rowCoord < 0 ||colCoord >= ChessBoard.getBoard()[0].length || colCoord < 0) {
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
	
}

class Knight extends Piece {
	public Knight(boolean isWhite, int rowCoord, int colCoord) {
		super("Knight", isWhite, rowCoord, colCoord);
	}
}

class Bishop extends Piece {
	public Bishop(boolean isWhite, int rowCoord, int colCoord) {
		super("Bishop", isWhite, rowCoord, colCoord);
	}
}

class Rook extends Piece {
	public Rook(boolean isWhite, int rowCoord, int colCoord) {
		super("Rook", isWhite, rowCoord, colCoord);
	}
}

class Queen extends Piece {
	public Queen(boolean isWhite, int rowCoord, int colCoord) {
		super("Queen", isWhite, rowCoord, colCoord);
	}
}

class King extends Piece {
	public King(boolean isWhite, int rowCoord, int colCoord) {
		super("King", isWhite, rowCoord, colCoord);
	}
}