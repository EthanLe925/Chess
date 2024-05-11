package Chess;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class Tile {
	private boolean isWhite = false;
	private String color;
	private boolean occupied;
	private Piece pieceInTile = null;
	private Icon tileIcon;
	private JButton tileButton;
	private int rowCoord;
	private int colCoord;
	
	public Tile (boolean isWhite, boolean isOccupied, Piece pieceInTile, int rowCoord, int colCoord) {
		this.isWhite = isWhite;
		this.occupied = isOccupied;
		this.pieceInTile = pieceInTile;
		this.rowCoord = rowCoord;
		this.colCoord = colCoord;
		
		if (isWhite) {
			color = "White";
		} else {
			color = "Black";
		}
		
		updateIcon();
				
	}
	
	public void setActions() {
		tileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				if (occupied) {
					System.out.println("" + getColor() + " " + getPiece().getColor() + getPiece().getName());
				} else {
					System.out.println("" + getColor() + " No piece");
				}
				*/
				if (ChessBoard.getCurrClicked() == null) {
					ChessBoard.setCurrClickedTile(rowCoord, colCoord);
					if (!ChessBoard.getCurrClicked().isOccupied()) {
						ChessBoard.setCurrClickedTile(-1, -1);
					}
					System.out.println(ChessBoard.getCurrClicked().getPiece().getMoves());
				} else {
					System.out.println("hello");
					Tile t = ChessBoard.getCurrClicked();
					if (t != Tile.this && isPossibleTile(t.getPiece())) {
						ChessBoard.movePiece(t.getRowCoord(), t.getColCoord(), rowCoord, colCoord);
					}
					//ChessBoard.movePiece(t.getRowCoord(), t.getColCoord(), rowCoord, colCoord);
					ChessBoard.setCurrClickedTile(-1, -1);
					//System.out.println(ChessBoard.getCurrClicked() == null);
				}
			}
		});
	}
	
	public boolean isPossibleTile(Piece p) {
		boolean result = false;
		ArrayList<Coordinate> moves = p.getMoves();
		Coordinate tileCoord = new Coordinate(getRowCoord(), getColCoord());
		
		for (int i = 0; i < moves.size(); i++) {
			if (moves.get(i).equals(tileCoord)) {
				result = true;
			}
		}
		
		return result;
		
	}

	public int getRowCoord() {
		return rowCoord;
	}
	
	public void setRowCoord(int row) {
		this.rowCoord = row;
	}
	
	public int getColCoord() {
		return colCoord;
	}
	
	public void setColCoord(int col) {
		this.colCoord = col;
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
		
		tileButton = new JButton(tileIcon);
		tileButton.setPreferredSize(new Dimension(500,10));
		
	}
	
	public JButton getButton() {
		return tileButton;
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
	
	public Piece getPiece(){
		return pieceInTile;
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
