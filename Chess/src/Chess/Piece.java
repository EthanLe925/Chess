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