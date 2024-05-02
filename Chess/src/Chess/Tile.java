package Chess;
import java.awt.*;
import javax.swing.*;

public class Tile {
	private boolean isWhite = false;
	private String color;
	private boolean occupied;
	private Piece pieceInTile = null;
	private Icon tileIcon;
	
	public Tile (boolean isWhite, boolean isOccupied, Piece pieceInTile) {
		this.isWhite = isWhite;
		this.occupied = isOccupied;
		this.pieceInTile = pieceInTile;
		
		if (isWhite) {
			color = "White";
		} else {
			color = "Black";
		}
		
		updateIcon();
		
	}
	
	public Icon getIcon() {
		return tileIcon;
	}
	
	public void updateIcon() {
		if(occupied) {
			tileIcon = new ImageIcon(color + "_" + pieceInTile.getColor() + pieceInTile.getName() + ".png");
		} else {
			tileIcon = new ImageIcon(color + "_Blank" + ".png");
		}
		Image img = ((ImageIcon)tileIcon).getImage();
		Image newImg = img.getScaledInstance(100,100,java.awt.Image.SCALE_SMOOTH);
		tileIcon = new ImageIcon(newImg);
		
		
	}

	public void addPiece(Piece p) {
		pieceInTile = p;
		setOccupied(true);
		updateIcon();
	}
	
	public void removePiece() {
		pieceInTile = null;
		setOccupied(false);
		updateIcon();
	}
	
	public String getPiece(){
		return pieceInTile.getName();
	}
	
	public void setColor(boolean isWhite) {
		this.isWhite = isWhite;
		if (isWhite) {
			color = "White";
		} else {
			color = "Black";
		}
		updateIcon();
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
