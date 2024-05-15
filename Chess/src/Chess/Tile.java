package Chess;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

/**
 * Tile.java
 * @author Andy Nguyen, Ethan Le, Pranit Agrawal, Krish Tandon, Ajay Saravanan
 * @since 5/14/2024
 * This class sets up the tiles of each of the tiles on the board
 */

public class Tile {
	private boolean isWhite = false;
	private String color;
	private boolean occupied;
	private Piece pieceInTile = null;
	private Icon tileIcon;
	private JButton tileButton;
	private int rowCoord;
	private int colCoord;
	
	/**
     * Constructor method for the Tile and sets up the color, if its occupied, the piece that is in the tile, and its coordinates
     * 
     * @param isWhite - the boolean value of whether or not the piece is white
     * @param isOccupied - the boolean value of whether or not a piece is in the tile
     * @param pieceInTile - the Piece in the tile
     * @param rowCoord - the int value of the row coordinate of the piece
     * @param colCoord - the int value of the col coordinate of the piece
	 */
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
	
	/**
     * Method that sets the actions for when the button is clicked. 
     * If no button has been clicked and the tile is occupied, it sets the currently clicked button to the tile. 
     * If a button has been clicked prior, then it checks if the piece in the currently clicked tile can be moved to the
     * tile just clicked
     * 
     * Postcondition: moves the piece from tile to tile accordingly to user input
	 */
	public void setActions() {
		tileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (ChessBoard.getCurrClicked() == null) {
					ChessBoard.setCurrClickedTile(rowCoord, colCoord);
					
					if (ChessBoard.getCurrClicked().isOccupied() && ChessBoard.getCurrClicked().getPiece().getIsWhite() != ChessBoard.getTracker().isWhiteTurn()) {
						ChessBoard.setCurrClickedTile(-1, -1);
					} else if (!ChessBoard.getCurrClicked().isOccupied()) {
						ChessBoard.setCurrClickedTile(-1, -1);
					}
					
				} else {
					System.out.println("hello");
					Tile t = ChessBoard.getCurrClicked();
					if (t != Tile.this && isPossibleTile(t.getPiece())) {
						ChessBoard.movePiece(t.getRowCoord(), t.getColCoord(), rowCoord, colCoord);
					}
					ChessBoard.setCurrClickedTile(-1, -1);
				}
			}
		});
	}
	
	/**
     * Method that checks if the piece can move to the tile. It checks by getting the possible coordinates 
     * the piece can move to and seeing if the coordinates of this tile align with one of the coordinates of the
     * possible moves.
     * 
     * @param p - the Piece that is being checked
     * @return result - the boolean value of whether or not the piece can move to this tile
	 */
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

	/**
     * Method that returns the row coordinate of the tile
     * 
     * @return rowCoord - the int value of the row coordinate
	 */
	public int getRowCoord() {
		return rowCoord;
	}
	
	/**
     * Method that sets the row coordinate of the tile to the input row
     * 
     * Postcondition: the row coordinate of this tile is changed according to the parameter
     * @param row - the int value of the row that is being set
	 */
	public void setRowCoord(int row) {
		this.rowCoord = row;
	}
	
	/**
     * Method that returns the column coordinate of the tile
     * 
     * @return colCoord - the int value of the column coordinate
	 */
	public int getColCoord() {
		return colCoord;
	}
	
	/**
     * Method that sets the column coordinate of the tile to the input column
     * 
     * Postcondition: the column coordinate of this tile is changed according to the parameter
     * @param col - the int value of the column that is being set
	 */
	public void setColCoord(int col) {
		this.colCoord = col;
	}
	
	/**
     * Method that returns the icon of the piece in the tile
     * 
     * @return tileIcon - the Icon of the tile;
	 */
	public Icon getIcon() {
		return tileIcon;
	}
	
	/**
     * Method that sets and updates the icon of the tile.
     * If the tile is black, the background of the tile is black, and the same for white.
     * If there is a piece in the tile, the tile's icon is updated to have an icon with the background color of the tile
     * and the piece on it with the piece's color accordingly matching.
     * 
     * Postcondition: The icon of the tile is changed
	 */
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
	
	/**
     * Method that returns the button of the button corresponding to the tile
     * 
     * @return tileButton - the JButton of the tile
	 */
	public JButton getButton() {
		return tileButton;
	}

	/**
     * Method that puts the input piece into the tile.
     * 
     * Precondition: the tile is empty
     * Postcondition: The piece is put in the tile, the tile is occupied, and the icon is updated
     * @param p - the piece
	 */
	public void addPiece(Piece p) {
		pieceInTile = p;
		setOccupied(true);
		updateIcon();
	}
	
	/**
     * Method that removes the piece from the tile.
     * 
     * Postcondition: The piece is removed from the tile, the tile is now unoccupied, and the icon is updated
	 */
	public void removePiece() {
		pieceInTile = null;
		setOccupied(false);
		updateIcon();
	}
	
	/**
     * Method that returns the piece in the tile
     * 
     * @return pieceInTile - the Piece in the tile
	 */
	public Piece getPiece(){
		return pieceInTile;
	}
	
	/**
     * Method that sets the color of the Tile
     * 
     * Precondition: the tile is empty
     * Postcondition: The piece is put in the tile, the tile is occupied, and the icon is updated
     * @param p - the piece
	 */
	public void setColor(boolean isWhite) {
		this.isWhite = isWhite;
		if (isWhite) {
			color = "White";
		} else {
			color = "Black";
		}
		updateIcon();
	}
	
	/**
     * Method that returns whether or not the tile is white
     * 
     * @return isWhite - the boolean value of whether or not the tile is white
	 */
	public boolean getColor() {
		return isWhite;
	}
	
	/**
     * Method that returns whether or not the tile is occupied
     * 
     * @return occupied - the boolean value of whether or not the tile is occupied
	 */
	public boolean isOccupied() {
		return occupied;
	}
	
	/**
     * Method that sets whether or not the tile is occupied
     * 
     * @param isOccupied - the boolean value of whether or not the tile is occupied
	 */
	public void setOccupied(boolean isOccupied) {
		this.occupied = isOccupied;
	}
	
	
}
