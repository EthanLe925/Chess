package Chess;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * ChessBoard.java
 * @author Andy Nguyen, Ethan Le, Pranit Agrawal, Krish Tandon, Ajay Saravanan
 * @since 5/14/2024
 * This keeps track of all of the elements of the board, including the tiles, the pieces in the tiles, and the movements.
 * This class also displays the board and the victory screen.
 */
public class ChessBoard{
	private static Tile[][] board = new Tile[8][8];
	private static Tile tileCurrClicked = null;
	private static CaptureTracker myTracker = new CaptureTracker();
	private static boolean kingCaptured = false;
	public static String capturedColor;
	private static boolean isTesting = false;
	

	/**
     * Method that calls the methods to fill the board with tiles and fill the board with the pieces in their respective positions
     * This method also sets the ability for each tile to be clicked.
     * 
     * Postcondition: intializes the board, fills the board with tiles and pieces, and allows the button(tiles) to perform their actions
     * when pressed.
	 */
	public static void setUpBoard() {
		
		initializeBoard();
		setBeginningPieces();
		for (int row = board.length-1; row >= 0; row--) {
			for (int col = 0; col < board[row].length; col++) {		
				board[row][col].setActions();
			}
		}
		
		
	}
	
	/**
     * Method that returns the CaptureTracker initialized in the class
     * 
     * @return myTracker - the CaptureTracker that keeps track of captured pieces
	 */
	public static CaptureTracker getTracker() {
		return myTracker;
	}
	
	/**
     * Method that returns the board of tiles initialized in the class.
     * 
     * @return board - the 2D array of Tiles that represent the board and the positions of all of the Tiles and Pieces
	 */
	public static Tile[][] getBoard(){
		return board;
	}
	
	/**
     * Method that sets up the GUI for the chessBoard and sets up the different aspects of the screen.
     * On the left side of the screen is the actual chess board, which users can click and move the pieces on.
     * On the right side of the screen is the tracker, which displays all of the captured pieces.
     * 
     * Postcondition: The board is displayed 
	 */
	public static void displayBoard() {
		JPanel chessBoard = new JPanel();
		chessBoard.setPreferredSize(new Dimension(2000, 2000));
		 
		GridLayout layout = new GridLayout(8,8);
		chessBoard.setLayout(layout);
		
		for (int row = board.length-1; row >= 0; row--) {
			for (int col = 0; col < board[row].length; col++) {		
				chessBoard.add(board[row][col].getButton(), row, col);
			}
		}
		
		JPanel scoreBoard = new JPanel();
		scoreBoard.setPreferredSize(new Dimension(500, 100));
		
		JLabel label1 = new JLabel("White Pieces Captured: \n" + myTracker.getWhiteCaptures());
		label1.setBounds(0, -480, 1000, 1000);
		
		JLabel label2 = new JLabel("Black Pieces Captured: \n" + myTracker.getBlackCaptures());
		label2.setBounds(0, -300, 10000, 1000);
		
		scoreBoard.setLayout(null);
		scoreBoard.add(label1, BorderLayout.NORTH);
		scoreBoard.add(label2, BorderLayout.SOUTH);
		
		if (isTesting) {
			JLabel currMoves;
			if (tileCurrClicked != null && tileCurrClicked.isOccupied()) {
				currMoves = new JLabel("Possible Moves: " + tileCurrClicked.getPiece().getMoves());
				currMoves.setBounds(0, 0, 1000, 1000);
			} else {
				currMoves = new JLabel("Possible Moves: ");
				currMoves.setBounds(0, 0, 1000, 1000);
			}
			
			scoreBoard.add(currMoves);
		}
	
		
		JFrame frame = new JFrame("Chess Game");
		frame.setSize(2000,1000);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		frame.add(chessBoard);
		frame.add(scoreBoard, BorderLayout.EAST);
		
		
	}
	
	/**
     * Method that sets up the GUI for the screen that shows who wins and sets up the different aspects of the screen when 
     * a King is captured. If the white king is captured, black wins, and vice - versa for white.
     * 
     * Postcondition: The victory screen is displayed 
	 */
	public static void displayWinScreen() {
		JPanel winScreen = new JPanel();
		winScreen.setPreferredSize(new Dimension(2000, 2000));
		
		JLabel myLabel = new JLabel(capturedColor + " Wins!");
		
		winScreen.add(myLabel);
		
		JFrame frame = new JFrame("Win Screen");
		frame.setSize(2000,1000);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		frame.add(winScreen);
		
		
	}
	
	/**
     * Method moves the piece from initRow and initCol on the 2D array of Tiles and moves it to the tile at toRow and toCol,
     * removing the piece from its original coordinate and replacing it at the to coordinates.
     * 
     * If the tile the piece is moving to is occupied, it will remove the piece at the Tile the current piece is moving to and
     * add the captured piece to its respective captured list.
     * 
     * After moving the pieces, it updates the board and displays it.
     * 
     * Postcondition: The piece is moved and the board is displayed again if the king is not captured,
     * if the king is captured, it calls the method to display the win screen.
     * @param initRow - the int value of the row the initial piece is at
     * @param initCol - the int value of the column the initial piece is at
     * @param toRow - the row that the user wishes to move the piece to
     * @param toCol - the column that the user wishes to move the piece to
     * 
	 */
	public static void movePiece(int initRow, int initCol, int toRow, int toCol) {
		Tile toTile = board[toRow][toCol];
		Tile initTile = board[initRow][initCol];
		
		if (toTile.isOccupied()) {
			myTracker.addCapture(toTile.getPiece());
			if (toTile.getPiece().getName().equals("King")) {
				kingCaptured = true;
				if (toTile.getPiece().getIsWhite()) {
					capturedColor = "Black";
				} else {
					capturedColor = "White";
				}
			}
			toTile.removePiece();
			
		}
		
		toTile.addPiece(board[initRow][initCol].getPiece());
		initTile.removePiece();
		myTracker.switchTurn();
		if (kingCaptured == true) {
			displayWinScreen();
		} else {
			displayBoard();
		}
		
		toTile.setActions();
		toTile.getPiece().setRow(toTile.getRowCoord());
		toTile.getPiece().setCol(toTile.getColCoord());
		toTile.getPiece().setHasMoved(true);
		initTile.setActions();


	}
	
	/**
     * Method that creates and puts the pieces in their respective starting positions on the board
     * 
     * Postcondition: The board is set up with all of the pieces in their correct spots.
	 */
	private static void setBeginningPieces() {
		
		setPawns(1, 7);
		setPawns(6, 7);
		
		board[0][0].addPiece(new Rook(false, 0, 0));
		board[0][7].addPiece(new Rook(false, 0, 7));
		board[7][0].addPiece(new Rook(true, 7, 0));
		board[7][7].addPiece(new Rook(true, 7, 7));
		
		board[0][1].addPiece(new Knight(false, 0, 1));
		board[0][6].addPiece(new Knight(false, 0, 6));
		board[7][1].addPiece(new Knight(true, 7, 1));
		board[7][6].addPiece(new Knight(true, 7, 6));
		
		board[0][2].addPiece(new Bishop(false, 0, 2));
		board[0][5].addPiece(new Bishop(false, 0, 5));
		board[7][2].addPiece(new Bishop(true, 7, 2));
		board[7][5].addPiece(new Bishop(true, 7, 5));
		
		board[0][3].addPiece(new Queen(false, 0, 3));
		board[7][3].addPiece(new Queen(true, 7, 3));
		
		board[0][4].addPiece(new King(false, 0, 4));
		board[7][4].addPiece(new King(true, 7, 4));
	}
	
	/**
     * Method that uses recursion to set the line of pawns up on the board.
     * 
     * Postcondition: The pawns are set up all in a row in the respective rowLocation.
     * If the rowLocation is 1, the pawns are all black. Otherwise, the pawns are White.
     * @param rowLocation - the row that the pawns are going to be set up in
     * @param firstColLocation - the last column that the pawn is going to appear in
     * 
	 */
	
	private static int setPawns(int rowLocation, int lastColLocation) {
		boolean isWhite = true;
		if (rowLocation == 1) {
			isWhite = false;
		}
		
		if (lastColLocation > 0) {
			board[rowLocation][lastColLocation].addPiece(new Pawn(isWhite, rowLocation, lastColLocation));
			return setPawns(rowLocation, lastColLocation - 1);
			
		} else {
			board[rowLocation][lastColLocation].addPiece(new Pawn(isWhite, rowLocation, lastColLocation));
			return 0;
		}
		
	}
	
	/**
     * Method that sets up the board with all of its Tiles
     *
     * Postcondition: The board has a tile and each tile is alternating color from tile to tile to create a chessBoard pattern
     * The tiles do not initially have pieces in them.
	 */
	private static void initializeBoard() {
		
		for (int row = 0; row < board.length; row++) {
			if (row % 2 == 0) {
				for (int col = 0; col < board[row].length; col++) {
					if(col % 2 == 0) {
						board[row][col] = new Tile(true, false, null, row, col);
					} else {
						board[row][col] = new Tile(false, false, null, row, col);
					}
				}
			} else {
				for (int col = 0; col < board[row].length; col++) {
					if (col % 2 == 1) {
						board[row][col] = new Tile(true, false, null, row, col);
					} else {
						board[row][col] = new Tile(false, false, null, row, col);
					}
				}
			}
		}
	}
	
	/**
     * Method that sets the variable tileCurrClicked to the tile that is currently clicked
     * 
     * Postcondition: sets the currentlyClicked tile to the row and column location. 
     * If the input row is -1, then it sets the currently clicked tile to null.
     * @param row - the int value of the row that the currently clicked tile is in
     * @param col - the int value of the column that the currently clicked tile is in
	 */
	public static void setCurrClickedTile(int row, int col) {
		if (row != -1) {
			tileCurrClicked = board[row][col];
		} else {
			tileCurrClicked = null;
		}
		
	}
	
	/**
     * Method that returns whether or not if a tile is currentlyClicked
     * 
     * @return true or false - whether or not there is a tile that is currentlyClicked;
	 */
	public static boolean tileCurrClicked() {
		return tileCurrClicked == null;
	}
	
	/**
     * Method that returns the currently clicked tile
     * 
     * @return tileCurrClicked - the currently clicked tile
	 */
	public static Tile getCurrClicked() {
		return tileCurrClicked;
	}
	
	/**
     * Method that sets whether or not the game is a test game or not
     * 
     * Postcondition: isTesting is set to the parameter
     * @param isTest - the boolean variable that sets whether or not the game is in testing mode
	 */
	public static void setTesting(boolean isTest) {
		isTesting = isTest;
	}
	
	/**
     * Method that returns whether or not the game is a test game or not
     * 
     * @return isTesting - the boolean variable of whether or not the game is currently being tested
	 */
	public static boolean getTesting() {
		return isTesting;
	}
	
	
}
