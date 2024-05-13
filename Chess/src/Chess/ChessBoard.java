package Chess;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class ChessBoard{
	private static Tile[][] board = new Tile[8][8];
	private static Tile tileCurrClicked = null;
	
	public static void setUpBoard() {
		
		initializeBoard();
		setBeginningPieces();
		for (int row = board.length-1; row >= 0; row--) {
			for (int col = 0; col < board[row].length; col++) {		
				board[row][col].setActions();
			}
		}
		
		
	}
	
	public static Tile[][] getBoard(){
		return board;
	}
	
	public static void printBoard() {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board.length; col++) {
				if (board[row][col].isOccupied()) {
					System.out.print(board[row][col].getPiece().getName());
				} else {
					System.out.print("null");
				}
			}

			System.out.println();
		}
	}
	
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
		JButton helloButton = new JButton("Hello");
		helloButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("hi");
			}
		});
		scoreBoard.add(helloButton);

		JFrame frame = new JFrame("Test Frame");
		frame.setSize(2000,1000);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		frame.add(chessBoard);
		frame.add(scoreBoard, BorderLayout.EAST);
		
		
		
	}
	
	
	public static void movePiece(int initRow, int initCol, int toRow, int toCol) {
		Tile toTile = board[toRow][toCol];
		Tile initTile = board[initRow][initCol];
		
		if (toTile.isOccupied()) {
			toTile.removePiece();
		}
		
		toTile.addPiece(board[initRow][initCol].getPiece());
		initTile.removePiece();
		printBoard();
		displayBoard();
		toTile.setActions();
		toTile.getPiece().setRow(toTile.getRowCoord());
		toTile.getPiece().setCol(toTile.getColCoord());
		toTile.getPiece().setHasMoved(true);
		initTile.setActions();

	}
	
	private static void setBeginningPieces() {
		
		for (int col = 0; col < board[0].length; col++) {
			board[1][col].addPiece(new Pawn(false, 1, col));
			board[6][col].addPiece(new Pawn(true, 6, col));
		}	
		
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
	
	public static void setCurrClickedTile(int row, int col) {
		if (row != -1) {
			tileCurrClicked = board[row][col];
		} else {
			tileCurrClicked = null;
		}
		
	}
	
	public static boolean tileCurrClicked() {
		return tileCurrClicked == null;
	}
	
	public static Tile getCurrClicked() {
		return tileCurrClicked;
	}
	
	
}
