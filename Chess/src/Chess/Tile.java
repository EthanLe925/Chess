package Chess;

public class Tile {
	private boolean isWhite = false;
	private boolean occupied;
	private Piece pieceInTile = null;
	
	public Tile (boolean isWhite, boolean isOccupied, Piece pieceInTile) {
		this.isWhite = isWhite;
		this.occupied = isOccupied;
		this.pieceInTile = pieceInTile;
	}

	public void addPiece(Piece p) {
		pieceInTile = p;
		setOccupied(true);
	}
	
	public void removePiece() {
		pieceInTile = null;
		setOccupied(false);
	}
	
	public String getPiece(){
		return pieceInTile.getName();
	}
	
	public void setColor(boolean isWhite) {
		this.isWhite = isWhite;
	}
	
	public boolean getColor() {
		return isWhite;
	}
	
	public boolean isOccupied() {
		return occupied;
	}
	
	public void setOccupied(boolean isOccupied) {
		this.occupied = isOccupied;
	}
	
	
}
