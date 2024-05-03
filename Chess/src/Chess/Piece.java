package Chess;

public class Piece {
	private String name;
	private boolean isWhite;
	private int rowCoord;
	private int colCoord;
	
	public Piece(String name, boolean isWhite, int rowCoord, int colCoord) {
		this.name = name;
		this.isWhite = isWhite;
		this.rowCoord = rowCoord;
		this.colCoord = colCoord;
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
	
	public void setRow(int row) {
		this.rowCoord = row;
	}
	
	public void setCol(int col) {
		this.colCoord = col;
	}
}
	

class Pawn extends Piece {
	
	public Pawn(boolean isWhite, int rowCoord, int colCoord) {
		super("Pawn", isWhite, rowCoord, colCoord);
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